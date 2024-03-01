package tp.models.entities.ranking;

import tp.models.entities.entidad.Entidad;
import java.util.List;

public interface Criterio {
  public void generarReporte(List<Entidad> entidades);
}

