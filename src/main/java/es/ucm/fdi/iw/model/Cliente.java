package es.ucm.fdi.iw.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
@DiscriminatorValue("CLIENTE")
public class Cliente extends User{
    public Cliente(){
        
    }
    private long id; 

    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY) 
    public long getId() { 
        return id; 
    }
    public void setId(long id) { this.id = id; }
}
