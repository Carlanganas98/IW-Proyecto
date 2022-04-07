package es.ucm.fdi.iw.controller;

import javax.persistence.EntityManager;
import es.ucm.fdi.iw.LocalData;
import es.ucm.fdi.iw.model.Message;
import es.ucm.fdi.iw.model.Reparacion;
import es.ucm.fdi.iw.model.Servicio;
import es.ucm.fdi.iw.model.Transferable;
import es.ucm.fdi.iw.model.User;
import es.ucm.fdi.iw.model.Vehiculo;
import es.ucm.fdi.iw.model.Reparacion.ESTADO;
import es.ucm.fdi.iw.model.User.Role;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

public class EmpleadoController {
    

    private static final Logger log = LogManager.getLogger(RootController.class);

    @Autowired
    private EntityManager entityManager;

    
    /**
     * Returns JSON with count of unread messages 
     */
	@GetMapping(path = "unread", produces = "application/json")
	@ResponseBody
	public String checkUnread(HttpSession session) {
		long userId = ((User)session.getAttribute("u")).getId();		
		long unread = entityManager.createNamedQuery("Message.countUnread", Long.class)
			.setParameter("userId", userId)
			.getSingleResult();
		session.setAttribute("unread", unread);
		return "{\"unread\": " + unread + "}";
    }
    
    /**
     * Posts a message to a user.
     * @param id of target user (source user is from ID)
     * @param o JSON-ized message, similar to {"message": "text goes here"}
     * @throws JsonProcessingException
     */
    @PostMapping("/{id}/msg")
	@ResponseBody
	@Transactional
	public String postMsg(@PathVariable long id, 
			@RequestBody JsonNode o, Model model, HttpSession session) 
		throws JsonProcessingException {
		
		String text = o.get("message").asText();
		User u = entityManager.find(User.class, id);
		User sender = entityManager.find(
				User.class, ((User)session.getAttribute("u")).getId());
		model.addAttribute("user", u);
		
		// construye mensaje, lo guarda en BD
		Message m = new Message();
		m.setRecipient(u);
		m.setSender(sender);
		m.setDateSent(LocalDateTime.now());
		m.setText(text);
		entityManager.persist(m);
		entityManager.flush(); // to get Id before commit
		
		ObjectMapper mapper = new ObjectMapper();
		/*
		// construye json: método manual
		ObjectNode rootNode = mapper.createObjectNode();
		rootNode.put("from", sender.getUsername());
		rootNode.put("to", u.getUsername());
		rootNode.put("text", text);
		rootNode.put("id", m.getId());
		String json = mapper.writeValueAsString(rootNode);
		*/
		// persiste objeto a json usando Jackson
		String json = mapper.writeValueAsString(m.toTransfer());
		
		log.info("Sending a message to {} with contents '{}'", id, json);

		messagingTemplate.convertAndSend("/user/"+u.getUsername()+"/queue/updates", json);
		return "{\"result\": \"message sent.\"}";
	}	


    @GetMapping("/reparaciones")
    public String reparaciones(Model model) {

		List<Vehiculo> lista_vehiculos = null; 

		lista_vehiculos = entityManager.createNamedQuery("verVehiculos", Vehiculo.class).getResultList();
		//log.info("ESTAMOS EN VER VEHIOCULOS CONTROLLER" + lista_vehiculos);
		model.addAttribute("vehiculos", lista_vehiculos); 

        return "reparaciones";
    }

    @GetMapping("/gestionarReparaciones")
    public String gestionarReparaciones(Model model, HttpSession session)
	{
		List<Reparacion> lista_reparaciones = null;
		TypedQuery<Reparacion> query;
		User empleado = entityManager.find(User.class, ((User)session.getAttribute("u")).getId());

        query = entityManager.createNamedQuery("Reparaciones.listadoReparaciones", Reparacion.class);
		query.setParameter("mecanico", empleado);
		lista_reparaciones = query.getResultList();
		//log.info("PRIMER CLIENTE CON UNA  REPARACION:" + " " + lista_reparaciones.get(0).getVehiculo().getPropietario().getFirstName());

		model.addAttribute("reparaciones_empleado", lista_reparaciones);

        return "gestionarReparaciones";
	}

	@Transactional
    @PostMapping("/aceptarReparacion")
    public String solicitudesReparacionAceptar(Model model, @RequestParam long id_reparacion, HttpSession session, @RequestParam(required=true) List<String> info, @RequestParam(required=true) List<Double> precio)
    {
        User u = entityManager.find(User.class, ((User)session.getAttribute("u")).getId());
        Reparacion rep = entityManager.find(Reparacion.class, id_reparacion);
        rep.setEstado(ESTADO.ACEPTADO);
        rep.setEmpleado(u);

        int num_servicios = info.size();
        if (num_servicios > 0)
        {
            //log.info("NUMERO DE SERVICIOS: " + num_servicios);
            for (int i = 0; i < num_servicios; ++i)
            {
                Servicio s = new Servicio();
                s.setInfo(info.get(i));
                s.setPrecio(precio.get(i));
                s.setReparacion(rep);

                entityManager.persist(s);
                entityManager.flush();
            }
        }
        else
            log.info("[ERROR]: NO HAS INTRODUCIDO NINGÚN SERVICIO EN LA REPARACIÓN");
            

        return gestionarReparaciones(model, session);
    }

    @Transactional
    @PostMapping("/rechazarReparacion")
    public String solicitudesReparacionRechazar(Model model, @RequestParam long id_reparacion, HttpSession session) {

        User u = entityManager.find(User.class, ((User)session.getAttribute("u")).getId());
        Reparacion rep = entityManager.find(Reparacion.class, id_reparacion);
        rep.setEstado(ESTADO.RECHAZADO);
        rep.setEmpleado(u);

        return gestionarReparaciones(model, session);
    }

    @Transactional
    @PostMapping("/reparacionPendiente")
    public String solicitudesReparacionPendiente(Model model, @RequestParam long id_reparacion, HttpSession session)
    {
        User u = entityManager.find(User.class, ((User)session.getAttribute("u")).getId());
        Reparacion rep = entityManager.find(Reparacion.class, id_reparacion);
        rep.setEstado(ESTADO.PENDIENTE);
        rep.setEmpleado(u);

        return gestionarReparaciones(model, session);
    }

    @GetMapping(path = "/verServicios", produces = "application/json")
    @Transactional // para no recibir resultados inconsistentes
	@ResponseBody  // para indicar que no devuelve vista, sino un objeto (jsonizado)
    public List<Servicio> verServiciosDeUnaReparacion(Model model, HttpSession session, @RequestParam long id_reparacion)
    {
        Reparacion rep = entityManager.find(Reparacion.class, id_reparacion);
        List<Servicio> lista_servicios = null;
        TypedQuery<Servicio> query;
        
        query = entityManager.createNamedQuery("Servicio.ServiciosDeUnaReparacion", Servicio.class);
		query.setParameter("reparacion", rep);
		lista_servicios = query.getResultList();

        log.info("ID REPARACION: " + id_reparacion);
        log.info("UN PRECIO: " + lista_servicios.get(0).getInfo());

        model.addAttribute("lista_servicios", lista_servicios);

        return lista_servicios;
    }

}
