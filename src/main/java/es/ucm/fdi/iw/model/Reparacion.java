package es.ucm.fdi.iw.model;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.*;

import lombok.Data;

@Entity
@Data
@NamedQueries({
    @NamedQuery(name="Reparaciones.listadoReparaciones",
    query="SELECT r FROM Reparacion r "
            + "WHERE r.empleado = :mecanico"),

    @NamedQuery(name="Reparacion.allReparaciones",
    query="SELECT r "
            + "FROM Reparacion r"),
    @NamedQuery(name="Reparacion.reparacionesPorPropietario",
    query="SELECT r "
            + "FROM Reparacion r where r.vehiculo "
            + "IN (SELECT v FROM Vehiculo v WHERE v.propietario = :usuario)" )
})
public class Reparacion {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "gen")
    @SequenceGenerator(name = "gen", sequenceName = "gen")
    private long id;

    public enum ESTADO { ACEPTADO, PENDIENTE, RECHAZADO};

    // @ManyToOne
    // private User cliente;
    //Sobera ya que al cliente accederemos a traves de la clase vehiculo

    @ManyToOne
    private User empleado;
    
    @ManyToOne
    private Vehiculo vehiculo;
    
    @OneToMany
    @JoinColumn(name="servicio_id")
    private List<Servicio> servicios;
    
    private LocalDateTime fechaInicio;
    private LocalDateTime fechaFin;

    @Enumerated(value = EnumType.STRING)
    private ESTADO estado = ESTADO.PENDIENTE;
    
    private String descripcion;

}
