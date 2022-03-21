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

    //@ManyToOne(targetEntity = User.class)
    //private User cliente;
    //Sobera ya que al cliente accederemos a traves de la clase vehiculo


    // ¿Esto sobra? o ¿@ManyToMany?
    //Creo que esta bien ya que habra muchas reparaciones para 1 empleado
    //y cada reparacion no tendra mas de 1 empleado
    @ManyToOne(targetEntity = User.class)
    private User empleado;

    
    @ManyToOne(targetEntity = Vehiculo.class)
    private Vehiculo vehiculo;
    Vehiculo getVehiculo(){
        return this.vehiculo;
    }

    
    @OneToMany(targetEntity = Servicio.class)
    @JoinColumn(name="servicio_id")
    private List<Servicio> lista_servicios;
    Servicio getServicios(){
        return this.getServicios();
    }
    
    private Date fecha_inicio;
    private Date fecha_fin;

}
