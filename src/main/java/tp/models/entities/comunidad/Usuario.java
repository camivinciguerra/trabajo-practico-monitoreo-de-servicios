package tp.models.entities.comunidad;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import tp.models.entities.misc.ExcepcionDefinidaPorUsuario;
import tp.models.entities.persistencia.Persistente;
import tp.models.entities.validador.Validador;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@Table(name = "usuario")
public class Usuario extends Persistente {

  @Column(name = "nombreUsuario")
  private String nombreUsuario;

  @Column(name = "contrasenia")
  private String contrasenia;


  private LocalDate fechaDeAlta;
}
