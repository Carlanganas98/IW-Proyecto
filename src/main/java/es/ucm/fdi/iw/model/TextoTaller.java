package es.ucm.fdi.iw.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;

import lombok.Data;
import lombok.ToString;


@Entity
@Data
@ToString
public class TextoTaller {

    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY) 
    private long id; 

    private String titulo;
    private String texto;

    @Lob
    private byte[] imagen;

}
