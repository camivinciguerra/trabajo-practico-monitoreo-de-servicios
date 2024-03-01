package tp.models.entities.services.georef;


import lombok.Getter;

import javax.persistence.*;

@Entity
@Table(name = "localidad")
@Getter
public class Localidad {

  @Id
  public Long id;

  @Column(name = "nombre")
  public String nombre;

  @ManyToOne
  @JoinColumn(name = "municipio_id", referencedColumnName = "id")
  public Municipio municipio;
}
