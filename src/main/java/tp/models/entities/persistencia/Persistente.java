package tp.models.entities.persistencia;

import lombok.Getter;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
@Getter
@MappedSuperclass
public abstract class Persistente {

  @Id
  @GeneratedValue
  private Long id;
}
