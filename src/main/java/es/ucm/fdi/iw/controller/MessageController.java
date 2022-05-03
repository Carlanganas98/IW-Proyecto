package es.ucm.fdi.iw.controller;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import es.ucm.fdi.iw.model.Message;
import es.ucm.fdi.iw.model.Reparacion;
import es.ucm.fdi.iw.model.Transferable;
import es.ucm.fdi.iw.model.User;
import es.ucm.fdi.iw.model.Message.Transfer;

/**
 * User-administration controller
 * 
 * @author mfreire
 */
@Controller()
@RequestMapping("message")
public class MessageController {
	
	private static final Logger log = LogManager.getLogger(MessageController.class);
	
	@Autowired 
	private EntityManager entityManager;

	
	@Autowired
	private SimpMessagingTemplate messagingTemplate;
		
	@GetMapping("/")
	public String getMessages(Model model, HttpSession session) {
		model.addAttribute("users", entityManager.createQuery(
			"SELECT u FROM User u").getResultList());
		return "messages";
	}

	/**@GetMapping(path = "/received", produces = "application/json")
	@Transactional // para no recibir resultados inconsistentes
	@ResponseBody  // para indicar que no devuelve vista, sino un objeto (jsonizado)
	public List<Message.Transfer> retrieveMessages(HttpSession session) {
		long userId = ((User)session.getAttribute("u")).getId();		
		User u = entityManager.find(User.class, userId);
		log.info("Generating message list for user {} ({} messages)", 
				u.getUsername(), u.getReceived().size());
		return  u.getReceived().stream().map(Transferable::toTransfer).collect(Collectors.toList());
	}	*/
	
	@GetMapping(path = "/unread", produces = "application/json")
	@ResponseBody
	public String checkUnread(HttpSession session) {
		long userId = ((User)session.getAttribute("u")).getId();		
		long unread = entityManager.createNamedQuery("Message.countUnread", Long.class)
			.setParameter("userId", userId)
			.getSingleResult();
		session.setAttribute("unread", unread);
		return "{\"unread\": " + unread + "}";
	}

	//Una funcion para recibir los mensajes del usuario y de la persona con la que habla en este chat
	@GetMapping(path = "/received/{id}", produces = "application/json")
	@Transactional // para no recibir resultados inconsistentes
	@ResponseBody  // para indicar que no devuelve vista, sino un objeto (jsonizado)
	public List<Message.Transfer> getChatMessages(@PathVariable long id, HttpSession session) {

		long idReparacion = id;
		Reparacion reparacion = ((Reparacion) entityManager.find(Reparacion.class, idReparacion));

		long empleadoID = reparacion.getEmpleado().getId();		
		long propietarioID = reparacion.getVehiculo().getPropietario().getId();
		log.info("Buscando los mensajes de los usuarios {} y {}",empleadoID, propietarioID); 
		
		TypedQuery<Message> query = entityManager.createNamedQuery("Message.getChat", Message.class);
		query.setParameter("recip", empleadoID);
		query.setParameter("sesionU", propietarioID);
		query.setParameter("topic", reparacion);

		TypedQuery<Message> query2 = entityManager.createNamedQuery("Message.getChat", Message.class);
		query2.setParameter("recip", propietarioID);
		query2.setParameter("sesionU", empleadoID);
		query2.setParameter("topic", reparacion);


		List<Message> mensajes1 = query.getResultList();
		List<Message> mensajes2 = query2.getResultList();
		mensajes2.addAll(mensajes1);
		mensajes2.sort(new Comparator<Message>() {

			@Override
			public int compare(Message o1, Message o2) {
				return o1.getDateSent().compareTo(o2.getDateSent());
			}
			
		});
		return  mensajes2.stream().map(Transferable::toTransfer).collect(Collectors.toList());
	}
	

	/**
     * Posts a message to a user.
     * @param id of target user (source user is from ID)
     * @param o JSON-ized message, similar to {"message": "text goes here"}
     * @throws JsonProcessingException
     */
    @PostMapping("/msg/{id}")
	@ResponseBody
	@Transactional
	public String postMsg(@PathVariable long id, 
			@RequestBody JsonNode o, Model model, HttpSession session) 
		throws JsonProcessingException {
		
		long idReparacion = id;
		Reparacion reparacion = ((Reparacion) entityManager.find(Reparacion.class, idReparacion));

		long empleadoID = reparacion.getEmpleado().getId();		
		long propietarioID = reparacion.getVehiculo().getPropietario().getId();

		String text = o.get("message").asText();
		User sender = entityManager.find(
				User.class, ((User)session.getAttribute("u")).getId());
		
		long recipientID;//aqui sabemos si quien lo recibe es el propietario o el empleado
		if(sender.getId() == empleadoID) recipientID = propietarioID;
		else recipientID = empleadoID;

		User recipient = ((User) entityManager.find(User.class, recipientID));


		// construye mensaje, lo guarda en BD
		Message m = new Message();
		m.setRecipient(recipient);
		m.setSender(sender);
		m.setDateSent(LocalDateTime.now());
		m.setText(text);
		m.setTopic(reparacion);
		m.setIdRepa("" + idReparacion);
		entityManager.persist(m);
		entityManager.flush(); // to get Id before commit
		
		ObjectMapper mapper = new ObjectMapper();

		String json = mapper.writeValueAsString(m.toTransfer());
		
		log.info("Sending a message to {} with contents '{}'", id, json);

		messagingTemplate.convertAndSend("/user/"+recipient.getUsername()+"/queue/updates", json);
		return "{\"result\": \"message sent.\"}";
	}	

	@PostMapping("/notifyReparation/")
	@ResponseBody
	@Transactional
	public String postMsg( 
			Model model, HttpSession session) 
		throws JsonProcessingException {

		ObjectMapper mapper = new ObjectMapper();
		Transfer t = new Transfer("Sistema", "", "", "","", "Se ha recibido una nueva solicitud de reparacion", 0);
		String json = mapper.writeValueAsString(t);
	
		log.info("Broadcasting to empleados");

		messagingTemplate.convertAndSend("/topic/empleado", json);

		return "{\"result\": \"message sent.\"}";
	}	

	
}