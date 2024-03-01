package tp.server.middlewares;

import io.javalin.config.JavalinConfig;
import io.javalin.http.Context;
import tp.controllers.FactoryController;
import tp.controllers.UbicacionController;
import tp.controllers.entidad.EntidadController;
import tp.models.entities.comunidad.Rol;
import tp.models.entities.comunidad.RolPersona;
import tp.models.entities.comunidad.Usuario;
import tp.server.exceptions.AccessDeniedException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static io.javalin.apibuilder.ApiBuilder.get;
import static io.javalin.apibuilder.ApiBuilder.post;
import static tp.models.entities.comunidad.RolPersona.ADMIN;
import static tp.models.entities.comunidad.RolPersona.BASICO;

public class AuthMiddleware {
    public static void apply(JavalinConfig config) {
        config.accessManager(((handler, context, routeRoles) -> {

            Usuario usuario = context.sessionAttribute("Usuario");
            if (!pathPublic(context)) {
                RolPersona userRole = getUserRoleType(context);
                if (usuario == null || routeRoles.isEmpty() || !routeRoles.contains(userRole)) {
                    throw new AccessDeniedException();
                }
                else {
                    handler.handle(context);
                }
            }
            else {
                handler.handle(context);
            }
        }));
    }

    private static RolPersona getUserRoleType(Context context) {
        return context.sessionAttribute("tipo_rol") != null ?
                RolPersona.valueOf(context.sessionAttribute("tipo_rol").toString()) : null;
    }





    private  static Boolean pathPublic(Context context) {
        List<String> rutas = Arrays.asList("/", "/login", "/create", "/create/carga-de-datos", "/create/carga-de-datos/finalizar",
                "/provincias", "/admin/usuarios","/admin/entidades", "/admin/tipos-entidades", "/admin/tipos-servicio", "/admin/ranking/prueba");

        // Verificar si la ruta es de municipios
        if (context.path().startsWith("/municipios/")) {
            return true; // Ruta de municipios, no securizar
        }

        // Verificar si la ruta es de localidades
        if (context.path().startsWith("/localidades/")) {
            return true; // Ruta de localidades, no securizar
        }

        if (context.path().startsWith("/usuario/existe/")) {
            return true; // Ruta de localidades, no securizar
        }

        if (context.path().startsWith("/create/es-valida/")) {
            return true; // Ruta de localidades, no securizar
        }

        if(context.path().startsWith("/usuario/cerrarSesion")){
            return true;
        }

        if (context.path().startsWith("/admin/usuario/")) {
            return true; // Ruta de localidades, no securizar
        }

        if (context.path().startsWith("/admin/entidad")) {
            return true; // Ruta de localidades, no securizar
        }

        if (context.path().startsWith("/admin/tipo-entidad")) {
            return true; // Ruta de localidades, no securizar
        }

        if (context.path().startsWith("/admin/tipo-servicio")) {
            return true; // Ruta de localidades, no securizar
        }

        if (context.path().startsWith("/establecimiento/comunidad/")) {
            return true; // Ruta de localidades, no securizar
        }

        return !rutas.stream().noneMatch(ruta -> context.path().equals(ruta));
    }
}
