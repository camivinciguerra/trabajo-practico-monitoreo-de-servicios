package tp.models.entities.comunidad;

import io.javalin.security.RouteRole;

public enum RolPersona implements RouteRole {
    ADMIN,
    BASICO,
    RESPONSABLE
}
