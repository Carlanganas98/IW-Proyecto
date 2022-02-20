package es.ucm.fdi.iw.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 *  Non-authenticated requests only.
 */
@Controller
public class RootController {

	private static final Logger log = LogManager.getLogger(RootController.class);

	@GetMapping("/login")
    public String login(Model model) {
        return "login";
    }

	@GetMapping("/")
    public String index(Model model) {
        return "index";
    }

    @GetMapping("/inicio")
    public String inicio(Model model) {
        return "inicio";
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


}
