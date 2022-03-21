package es.ucm.fdi.iw.controller;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import es.ucm.fdi.iw.model.User;
import es.ucm.fdi.iw.model.Vehiculo;

/**
 *  Non-authenticated requests only.
 */
@Controller
public class RootController {

	private static final Logger log = LogManager.getLogger(RootController.class);

    @Autowired
    private EntityManager entityManager;

	@GetMapping("/login")
    public String login(Model model) {
        return "login";
    }

	@GetMapping("/")
    public String index(Model model) {
        return "index";
    }

    @GetMapping("/registro")
    public String registro(Model model) {
        return "registro";
    }

    @GetMapping("/profile")
    public String profile(Model model) {
        return "profile";
    }
    @GetMapping("/chat")
    public String chat(Model model) {
        return "chat";
    }

    @GetMapping("/reparaciones")
    public String reparaciones(Model model) {
        return "reparaciones";
    }

    @GetMapping("/taller")
    public String taller(Model model) {
        return "taller";
    }
        
    @GetMapping("/vehiculoDetallado")
    public String vehiculoDetallado(Model model, HttpSession session, @RequestParam long idVehiculo)
    {
        User u = entityManager.find(User.class, ((User)session.getAttribute("u")).getId());
        Vehiculo vehiculo = null;

        vehiculo = (Vehiculo) entityManager.createNamedQuery("verVehiculos").setParameter("propietario", u).setParameter("idVehiculo", idVehiculo).getSingleResult();
        //vehiculo.setId(idVehiculo);
        //vehiculo.setPropietario(u);

        model.addAttribute("vehiculo", vehiculo);
        
        return "vehiculoDetallado";
    }

}
