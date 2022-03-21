package es.ucm.fdi.iw.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

import lombok.Data;

@Entity
@Data
@NamedQueries({
    // @NamedQuery(name="Reparaciones.listadoReparaciones",
    //         query="SELECT r, v FROM Reparacion r INNER JOIN Vehiculo v ON r.vehiculo = v"
    //                 + "WHERE r.empleado = :mecanico"),
                    
    @NamedQuery(name="Reparacion.allReparaciones",
    query="SELECT r "
            + "FROM Reparacion r")
})
public class Reparacion {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "gen")
    @SequenceGenerator(name = "gen", sequenceName = "gen")
    private long id;

    public enum ESTADO { ACEPTADO, PENDIENTE, RECHAZADO};

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

    @Enumerated(value = EnumType.STRING)
    private ESTADO estado = ESTADO.PENDIENTE;
    
    private String descripcion;

}
