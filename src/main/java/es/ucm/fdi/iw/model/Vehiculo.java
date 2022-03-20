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

import lombok.Data;

@Entity
@Data
@NamedQueries({
    @NamedQuery(name="verVehiculos",
    query="SELECT v FROM Vehiculo v WHERE v.propietario = :propietario"),
    
    @NamedQuery(name="verVehiculoDetallado",
    query="SELECT v FROM Vehiculo v WHERE v.propietario = :propietario AND v.id = :idVehiculo")

})

public class Vehiculo {

    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY) 
    private long id;
    
    private String matricula;
    private String tipo; // Coche o Moto
    private String modelo;
    private int anyo;
    
    @OneToMany
    @JoinColumn(name="vehiculo_id")
    private List<Reparacion> lista_reparaciones;
    
    @ManyToOne
    private User propietario;

}
