package tp.models.entities.comunidad;

import io.javalin.security.RouteRole;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

public enum Rol{
    ADMINISTRADOR,
    USUARIOBASICO
}
