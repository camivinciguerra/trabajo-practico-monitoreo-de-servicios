package tp.models.entities.services.georef;

import lombok.Getter;

import javax.persistence.*;

@Entity
@Table(name = "municipio")
@Getter
public class Municipio {

    @Id
    public Long id;

    @Column(name = "nombre")
    public String nombre;

    @ManyToOne
    @JoinColumn(name = "provincia_id",referencedColumnName = "id")
    public Provincia provincia;
}
