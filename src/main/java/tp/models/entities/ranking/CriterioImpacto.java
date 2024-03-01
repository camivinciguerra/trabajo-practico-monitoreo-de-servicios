package tp.models.entities.ranking;


import lombok.Getter;
import tp.models.entities.entidad.Entidad;
import tp.models.entities.ranking.exportador.Exportador;
import tp.models.entities.ranking.exportador.ListaEntidadesCriterio;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class CriterioImpacto implements Criterio {

  @Getter
  List<Entidad> ultimaListaCriterioImpacto = new ArrayList<>();

  public void generarReporte(List<Entidad> entidades) {
    Collections.sort(entidades, Comparator.comparingInt(Entidad::getCantAfectados).reversed());
    ListaEntidadesCriterio listaEntidadesCriterio = new ListaEntidadesCriterio("Criterio Impacto");
    listaEntidadesCriterio.setListaEntidades(entidades);
    listaEntidadesCriterio.setPrefijoArchivo("ListadoDeImpacto");

    Exportador.getInstancia().agregarListaEntidadesCriterio(listaEntidadesCriterio);
    ultimaListaCriterioImpacto = entidades;

  }
}
