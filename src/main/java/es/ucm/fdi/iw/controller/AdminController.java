package es.ucm.fdi.iw.controller;

import java.util.ArrayList;

import javax.persistence.*;
import javax.transaction.Transactional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import antlr.collections.List;
import es.ucm.fdi.iw.model.User;
import es.ucm.fdi.iw.model.User.Role;

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
    
    @PersistenceContext
    public void setEntityManager(EntityManager em){
        this.entityManager = em;
    }

	@GetMapping("/")
    public String index(Model model) {
        TypedQuery<User> consultaAlumnos= entityManager.createNamedQuery("User.allUsers", User.class);
        ArrayList<User> lista= (ArrayList<User>) consultaAlumnos.getResultList();
        for(User user : lista){
            if(user.hasRole(Role.CLIENTE)){
                user.setRoles("CLIENTE");
            }else{
                user.setRoles("EMPLEADO");
            }
        }
        model.addAttribute("users", lista);
        return "admin";
    }
    
    @Transactional
    @PostMapping("/editarTrabajador")
    public String editarTrabajador(Model model, @RequestParam long id, @RequestParam String firstName) {
        User u = entityManager.find(User.class, id);
        u.setFirstName(firstName);
        //model.addAttribute("users", lista);
        return index(model);
    }
}
