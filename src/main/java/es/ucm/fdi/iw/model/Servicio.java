package es.ucm.fdi.iw.model;

import javax.persistence.*;


import lombok.Data;

@Entity
@Data

public class Servicio {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "gen")
    @SequenceGenerator(name = "gen", sequenceName = "gen")
    private long id; 

    private String info;
    private double precio;
    private int num_horas;

    @ManyToOne
    private Reparacion reparacion;

}
