package es.ucm.fdi.iw.controller;

import java.util.ArrayList;

import javax.persistence.*;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import antlr.collections.List;
import es.ucm.fdi.iw.model.User;

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
        model.addAttribute("users", lista);
        return "admin";
    }


    @GetMapping("/borrarId")
    @Transactional
    public String borrarId(Model model, @RequestParam long id){

        User u = entityManager.find(User.class, id);
        u.setEnabled(false);
        return index(model);
    }
}
