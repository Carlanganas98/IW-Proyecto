package es.ucm.fdi.iw.model;

import java.util.ArrayList;
import java.util.Date;
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
public class Reparacion {

    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY) 
    private long id;

    @ManyToOne(targetEntity = Cliente.class)
    private Cliente cliente;
    
    @ManyToOne(targetEntity = Empleado.class)
    private Empleado empleado;

    @ManyToOne(targetEntity = Vehiculo.class)
    private Vehiculo vehiculo;
    
    @OneToMany(targetEntity = Servicio.class)
    @JoinColumn(name="servicio_id")
    private List<Servicio> lista_servicios;
    
    private Date fecha_inicio;
    private Date fecha_fin;

}
