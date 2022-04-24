package es.ucm.fdi.iw.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import lombok.Data;
import lombok.ToString;

@Entity
@Data
@NamedQueries({
    @NamedQuery(name="Reparaciones.reparacionesAGestionar",
    query="SELECT r FROM Reparacion r "
            + "WHERE (r.empleado = :mecanico OR r.estado = 'PENDIENTE') AND r.activo = TRUE"),

    @NamedQuery(name="Reparaciones.reparacionesAceptadas",
    query="SELECT r FROM Reparacion r "
            + "WHERE r.empleado = :mecanico AND r.estado = 'ACEPTADO' AND r.activo = TRUE"),

    @NamedQuery(name="Reparacion.allReparaciones",
    query="SELECT r "
            + "FROM Reparacion r"),
    @NamedQuery(name="Reparacion.reparacionesPorPropietario",
    query="SELECT r "
            + "FROM Reparacion r where r.vehiculo "
            + "IN (SELECT v FROM Vehiculo v WHERE v.propietario = :usuario)" )
})
@ToString
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
    @JoinColumn(name="reparacion_id")
    private List<Servicio> servicios;

    @OneToMany
    @JoinColumn(name = "order_id")
    private List<Message> sent = new ArrayList<>();
    
    private LocalDateTime fechaInicio;
    private LocalDateTime fechaFin;

    @Enumerated(value = EnumType.STRING)
    private ESTADO estado = ESTADO.PENDIENTE;
    
    private String descripcion;
    private boolean activo;

    

}
