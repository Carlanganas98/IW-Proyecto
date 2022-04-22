package es.ucm.fdi.iw.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import javax.persistence.*;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import antlr.collections.List;
import es.ucm.fdi.iw.LocalData;
import es.ucm.fdi.iw.model.Reparacion;
import es.ucm.fdi.iw.model.TextoTaller;
import es.ucm.fdi.iw.model.User;
import es.ucm.fdi.iw.model.Reparacion.ESTADO;
import es.ucm.fdi.iw.model.User.Role;
import org.commonmark.node.*;
import org.commonmark.parser.Parser;
import org.commonmark.renderer.html.HtmlRenderer;


/**
 *  Site administration.
 *
 *  Access to this end-point is authenticated.
 */
@Controller
@RequestMapping("admin")
public class AdminController {

	private static final Logger log = LogManager.getLogger(AdminController.class);

    private EntityManager entityManager;
    
    @Autowired
    private LocalData localData;

    private File baseFolder;


    @PersistenceContext
    public void setEntityManager(EntityManager em){
        this.entityManager = em;
    }

	@GetMapping("/")
    public String index(Model model) {
        TypedQuery<User> consultaAlumnos= entityManager.createNamedQuery("User.allUsers", User.class);
        ArrayList<User> lista= (ArrayList<User>) consultaAlumnos.getResultList();
        ArrayList<User> listaTrabajadores= new ArrayList<User>();
        ArrayList<User> listaClientes= new ArrayList<User>();

        for(User user : lista){
            if(user.hasRole(Role.CLIENTE)){
                listaClientes.add(user);
                log.info("UN CLIENTE");
            }else{
                listaTrabajadores.add(user);
                log.info("UN TRABAJADOR");
            }           
        }
        model.addAttribute("trabajadores", listaTrabajadores);
        model.addAttribute("clientes", listaClientes);
        return "admin";
    }

    @GetMapping("/borrarId")
    @Transactional
    public String borrarId(Model model, @RequestParam long id){

        User u = entityManager.find(User.class, id);
        u.setEnabled(false);
        return index(model);
    }
    
    @Transactional
    @PostMapping("/editarUsuario")
    public String editarTrabajador(Model model, @RequestParam long id, @RequestParam String firstName, @RequestParam String lastName) {

        User u = entityManager.find(User.class, id);
        u.setFirstName(firstName);
        u.setLastName(lastName);
        
        return index(model);
    }
    @GetMapping("/editarInicio")
    public String editarInicio(Model model) {
        return "editarInicio";
    }

    @GetMapping("/solicitudesReparacion")
    public String solicitudesReparacion(Model model) {

        TypedQuery<Reparacion> consultaAlumnos= entityManager.createNamedQuery("Reparacion.allReparaciones", Reparacion.class);
        ArrayList<Reparacion> lista= (ArrayList<Reparacion>) consultaAlumnos.getResultList();

        model.addAttribute("reparaciones", lista);
        return "solicitudesReparacion";
    }

    
    @Transactional
    @PostMapping("/editarInicioTexto")
    public String editarInicioTexto(Model model, @RequestParam String texto) {
        Parser parser = Parser.builder().build();
        Node document = parser.parse(texto);
        HtmlRenderer renderer = HtmlRenderer.builder().build();
        texto = renderer.render(document);  // "<p>This is <em>Sparta</em></p>\n"
        
        long id = 1;
        TextoTaller txt = entityManager.find(TextoTaller.class, id);

        txt.setTexto(texto);

        return "editarInicio";
    }
    @Transactional
    @PostMapping("/editarInicioTitulo")
    public String editarInicioTitulo(Model model, @RequestParam String titulo) {
        Parser parser = Parser.builder().build();
        Node document = parser.parse(titulo);
        HtmlRenderer renderer = HtmlRenderer.builder().build();
        titulo = renderer.render(document);  // "<p>This is <em>Sparta</em></p>\n"
        
        long id = 1;
        TextoTaller txt = entityManager.find(TextoTaller.class, id);

        txt.setTitulo(titulo);

        return "editarInicio";
    }

    @Transactional
    @PostMapping("/editarInicioLogo")
    public String setPic(@RequestParam("logo") MultipartFile logo, HttpServletResponse response, HttpSession session, Model model) throws IOException {
        
        System.out.println(logo);
		log.info("Cambiando foto del logo");
		File f = localData.getFile("img","logo.png");

		if (logo.isEmpty()) {
			log.info("failed to upload logo: emtpy file?");
		} else {
			try (BufferedOutputStream stream =
					new BufferedOutputStream(new FileOutputStream(f))) {
				byte[] bytes = logo.getBytes();
				stream.write(bytes);
                log.info("Uploaded logo into {}!", f.getAbsolutePath());
			} catch (Exception e) {
                response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
				log.warn("Error uploading the logo" + " ", e);
			}
		}

		return "editarInicio";
    }

 
}
