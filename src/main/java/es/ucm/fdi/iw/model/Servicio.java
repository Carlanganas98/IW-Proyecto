package es.ucm.fdi.iw.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.Data;

@Entity
@Data
public class Servicio {

    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY) 
    private long id; 

    private String info;
    private String precio;
    private int num_horas;

    @ManyToOne(targetEntity = Reparacion.class)
    private Reparacion reparacion;

}
