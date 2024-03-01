package tp.models.entities.servicios;

import lombok.Getter;
import lombok.Setter;
import tp.models.entities.persistencia.Persistente;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "tipoServicio")
@Getter
@Setter
public class TipoServicio extends Persistente {

    @Column(name = "nombre")
    private String nombre;
}
