package es.ucm.fdi.iw.model;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

import lombok.Data;
import lombok.ToString;

@Entity
@Data
@NamedQueries({
    @NamedQuery(name="verVehiculos",
    query="select v from Vehiculo v WHERE v.activo = TRUE"),
    
    @NamedQuery(name="verVehiculoR",
    query="select v from Vehiculo v WHERE v.propietario = :usuario"),

    @NamedQuery(name="verVehiculoDetallado",
    query="SELECT v FROM Vehiculo v WHERE v.propietario = :propietario AND v.id = :idVehiculo")

})
@ToString
public class Vehiculo {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "gen")
    @SequenceGenerator(name = "gen", sequenceName = "gen")
    private long id;

    private String matricula;
    private String tipo; // Coche o Moto
    private String modelo;
    private int anyo;
    private boolean activo;
    
    @OneToMany
    @JoinColumn(name="vehiculo_id")
    private List<Reparacion> reparaciones;
    
    @ManyToOne
    private User propietario;

}
