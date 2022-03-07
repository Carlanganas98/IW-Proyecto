package es.ucm.fdi.iw.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
@DiscriminatorValue("CLIENTE")
public class Cliente extends User{

    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY) 
    private long id;

    // private String nombre;
    // private String apellidos;
    // private String correo;
    // private String contrasenya;

}
