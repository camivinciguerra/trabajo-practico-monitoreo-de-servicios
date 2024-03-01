package tp.controllers.incidentes.model;

import lombok.Builder;
import lombok.Getter;
import tp.models.entities.servicios.Incidente;
import tp.models.entities.servicios.Servicio;

import java.util.List;

@Builder
@Getter
public class ServicioFiltradoModel {
    private String name;
    private String tipoServicio;
    private List<IncidenteFiltradoModel> incidentes;
}
