package tp.models.entities.ranking;

import lombok.Getter;
import tp.models.entities.entidad.Entidad;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import tp.models.entities.ranking.exportador.Exportador;
import tp.models.entities.ranking.exportador.ListaEntidadesCriterio;

public class CriterioTiempoDeCierre implements Criterio {

  @Getter
  List<Entidad> ultimaListaCriterioTiempo = new ArrayList<>();
  public void generarReporte(List<Entidad> entidades) {
    Collections.sort(entidades, Comparator.comparingInt(Entidad::promedioDeCierreDeIncidentes).reversed());
    ListaEntidadesCriterio listaEntidadesCriterio = new ListaEntidadesCriterio("Criterio Tiempo de Cierre");
    listaEntidadesCriterio.setListaEntidades(entidades);
    listaEntidadesCriterio.setPrefijoArchivo("ListadoTiempoDeCierre_");
    Exportador.getInstancia().agregarListaEntidadesCriterio(listaEntidadesCriterio);
    ultimaListaCriterioTiempo = entidades;
  }
}