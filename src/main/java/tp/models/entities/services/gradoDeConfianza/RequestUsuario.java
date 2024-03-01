package tp.models.entities.services.gradoDeConfianza;

import lombok.Builder;

import java.util.List;
@Builder
public class RequestUsuario {
    private List<UsuarioModelConfianza> usuarioModelConfianzas;
    private List<IncidenteModelConfianza> incidentes;
}
