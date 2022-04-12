package es.ucm.fdi.iw.model;

import javax.persistence.*;

import lombok.Data;

@Entity
@Data
// @NamedQueries({
//     @NamedQuery(name= "Servicio.ServiciosDeUnaReparacion",
//     query= "SELECT s FROM Servicio s "
//             + "WHERE s.reparacion = :reparacion")
// })
public class Servicio
{
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "gen")
    @SequenceGenerator(name = "gen", sequenceName = "gen")
    private long id; 

    private String info;
    private double precio;
    private int numHoras;

    @ManyToOne
    private Reparacion reparacion;

}
