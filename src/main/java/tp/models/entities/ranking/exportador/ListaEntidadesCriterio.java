package tp.models.entities.ranking.exportador;

import tp.models.entities.entidad.Entidad;
import lombok.Setter;

import java.util.List;

public class ListaEntidadesCriterio {


    String nombreDeCriterio;

    @Setter
    String prefijoArchivo;

    @Setter
    List<Entidad> listaEntidades;

    public ListaEntidadesCriterio(String unNombreCriterio){
        this.nombreDeCriterio = unNombreCriterio;
    }

}
