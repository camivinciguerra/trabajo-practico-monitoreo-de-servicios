package tp.models.entities.services.georef;

import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "provincia")
@Getter
public class Provincia {

    @Id
    public Long id;

    @Column(name = "nombre")
    public String nombre;
}
