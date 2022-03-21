package es.ucm.fdi.iw.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

import lombok.Data;

@Entity
@Data
public class Reparacion {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "gen")
    @SequenceGenerator(name = "gen", sequenceName = "gen")
    private long id;

    //@ManyToOne
    //private User cliente;
    //Sobera ya que al cliente accederemos a traves de la clase vehiculo

    @ManyToOne
    private User empleado;
    
    @ManyToOne
    private Vehiculo vehiculo;
    
    @OneToMany
    @JoinColumn(name="servicio_id")
    private List<Servicio> lista_servicios;
    
    private Date fecha_inicio;
    private Date fecha_fin;
    private String estado;
    
}
