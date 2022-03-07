package es.ucm.fdi.iw.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
@DiscriminatorValue("EMPLEADO")
public class Empleado extends User{

    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY) 
    private long id; 

}
