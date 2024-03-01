package tp.models.entities.ranking.exportador;

import lombok.Getter;

import java.util.*;

public class Documento {

    @Getter
    private Map<String, List<String>> datos;

    public void agregarDato(String unaClave, String ... unasEntidades) {
        Collections.addAll(this.datos.get(unaClave), unasEntidades);
    }
}