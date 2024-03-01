package tp.models.entities.services.fusionDeComunidades;

import lombok.Builder;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
@Getter
@Builder
public class PropuestaDeFusionModel {
    private ArrayList<ComunidadModel> comunidadesAFusionar;

    public String fechaPropuesta;
}
