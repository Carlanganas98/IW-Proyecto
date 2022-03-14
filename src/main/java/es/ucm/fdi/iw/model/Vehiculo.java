package es.ucm.fdi.iw.model;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import lombok.Data;

@Entity
@Data
public class Vehiculo {

    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY) 
    private long id;
    
    private String matricula;
    private String tipo; // Coche o Moto
    private String modelo;
    //private int anyo;
    
    @OneToMany
    @JoinColumn(name="vehiculo_id")
    private List<Reparacion> lista_reparaciones;
    List<Reparacion> getReparaciones(){
        return this.lista_reparaciones;
    }
    
    @ManyToOne(targetEntity = User.class)
    private User propietario;
    User getPropietario(){
        return this.propietario;
    }
}
