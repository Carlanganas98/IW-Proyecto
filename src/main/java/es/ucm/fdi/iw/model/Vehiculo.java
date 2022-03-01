package es.ucm.fdi.iw.model;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;

import lombok.Data;

@Entity
@Data
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(discriminatorType = DiscriminatorType.STRING, name = "TYPE")
public class Vehiculo {
    public Vehiculo(){

    }
    private long id; 
    // private User usuario;

    // @ManyToOne(targetEntity=User.class) 
    // User getPropietario() { return this.usuario; }

    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY) 
    public long getId() { 
        return id; 
    }
    public void setId(long id) { this.id = id; }
}
