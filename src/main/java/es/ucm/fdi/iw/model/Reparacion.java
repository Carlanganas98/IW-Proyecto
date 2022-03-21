package es.ucm.fdi.iw.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.*;


import lombok.Data;

@Entity
@Data
@NamedQueries({
    @NamedQuery(name="Reparacion.allReparaciones",
    query="SELECT r "
            + "FROM Reparacion r")
})
public class Reparacion {

    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY) 
    private long id;

    //@ManyToOne(targetEntity = User.class)
    //private User cliente;
    //Sobera ya que al cliente accederemos a traves de la clase vehiculo


    // ¿Esto sobra? o ¿@ManyToMany?
    //Creo que esta bien ya que habra muchas reparaciones para 1 empleado
    //y cada reparacion no tendra mas de 1 empleado
    @ManyToOne(targetEntity = User.class)
    private User empleado;
    
    @ManyToOne
    private Vehiculo vehiculo;
    Vehiculo getVehiculo(){
        return this.vehiculo;
    }

    
    @OneToMany
    @JoinColumn(name="servicio_id")
    private List<Servicio> lista_servicios;
    Servicio getServicios(){
        return this.getServicios();
    }
    
    private Date fecha_inicio;
    private Date fecha_fin;
    private String estado;
    
    private String descripcion;

}
