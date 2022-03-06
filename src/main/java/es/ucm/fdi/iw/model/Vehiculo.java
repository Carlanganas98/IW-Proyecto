package es.ucm.fdi.iw.model;
import java.util.List;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import lombok.Data;

@Entity
@Data
// @Inheritance(strategy = InheritanceType.SINGLE_TABLE)
// @DiscriminatorColumn(discriminatorType = DiscriminatorType.STRING, name = "TYPE")
public class Vehiculo {

    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY) 
    private long id; // Matrícula
    private String tipo; // Coche o Moto
    private String modelo;
    //private int anyo;
    
    @OneToMany(targetEntity = Reparacion.class)
    @JoinColumn(name="vehiculo_id")
    private List<Reparacion> lista_reparaciones;
    
    @ManyToOne(targetEntity = Cliente.class)
    private Cliente cliente;
    
    // private User usuario;


    // @ManyToOne(targetEntity=User.class) 
    // User getPropietario() { return this.usuario; }

}
