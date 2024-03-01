package tp.server.handlers;

import io.javalin.Javalin;
import tp.server.exceptions.AccessDeniedException;

public class AccessDeniedHandler implements IHandler{
    @Override
    public void setHandle(Javalin app) {
        app.exception(AccessDeniedException.class, (e, context) -> {
            context.render("405.hbs");
        });
    }
}
