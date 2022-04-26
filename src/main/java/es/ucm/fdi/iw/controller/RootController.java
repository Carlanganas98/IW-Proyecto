package es.ucm.fdi.iw.controller;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;

import javax.persistence.EntityManager;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;
import org.springframework.http.HttpStatus;

import es.ucm.fdi.iw.LocalData;
import es.ucm.fdi.iw.model.Reparacion;
import es.ucm.fdi.iw.model.TextoTaller;
import es.ucm.fdi.iw.model.User;

/**
 *  Non-authenticated requests only.
 */




@Controller
public class RootController {

    @ResponseStatus(
        value=HttpStatus.FORBIDDEN, 
        reason="No eres administrador, y éste no es tu perfil")  // 403
    public static class ReparacionSinPermisoException extends RuntimeException {}

	private static final Logger log = LogManager.getLogger(RootController.class);

    
	@Autowired
    private LocalData localData;

    @Autowired
    private EntityManager entityManager;

    private static InputStream defaultVehiclePic() {
	    return new BufferedInputStream(Objects.requireNonNull(
            ClienteController.class.getClassLoader().getResourceAsStream(
                "static/img/default-vehicle-pic.jpg")));
    }

    @GetMapping("{id}/vehiclepic")
    public StreamingResponseBody vehiclepic(@PathVariable long id) throws IOException {
        File f = localData.getFile("vehicle", ""+id+".jpg");
        InputStream in = new BufferedInputStream(f.exists() ?
            new FileInputStream(f) : RootController.defaultVehiclePic());
        return os -> FileCopyUtils.copy(in, os);
    }


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
        model.addAttribute("titulo", texto.getTitulo()); 

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
    @GetMapping("/chat/{id}")//id es el id de la reparacion de este chat
    public String chat(@PathVariable long id, Model model, HttpSession session) {
        model.addAttribute("destination", (long)id); 
        Reparacion rep = entityManager.find(Reparacion.class, id);
        model.addAttribute("reparacion", rep);

        if(  rep.getEmpleado() != null && ( (User)session.getAttribute("u") ).getId() == rep.getEmpleado().getId() || (  (User) session.getAttribute("u") ).getId() == rep.getVehiculo().getPropietario().getId() ){
            return "chat";
        }else{
            throw new ReparacionSinPermisoException();
        }
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
