package es.ucm.fdi.iw.controller;

import javax.persistence.EntityManager;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import es.ucm.fdi.iw.model.TextoTaller;

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

        TextoTaller texto = new TextoTaller();
        long id = 1;
        texto = entityManager.find(TextoTaller.class, id);
        log.info(texto.getTexto());
        model.addAttribute("texto", texto.getTexto()); 

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
    @GetMapping("/chat/{id}")
    public String chat(@PathVariable long id, Model model, HttpSession session) {
        model.addAttribute("destination", (long)id); 
        return "chat";
    }

   

    @GetMapping("/taller")
    public String taller(Model model) {
        return "taller";
    }

    @GetMapping("/vehiculoDetallado")
    public String vehiculoDetallado(Model model){
        return "vehiculoDetallado";
    }

}
