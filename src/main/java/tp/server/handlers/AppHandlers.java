package tp.server.handlers;

import io.javalin.Javalin;
import tp.server.exceptions.AccessDeniedException;

import java.util.Arrays;

public class AppHandlers {
    private IHandler[] handlers = new IHandler[]{
            new AccessDeniedHandler(),
    };

    public static void applyHandlers(Javalin app) {
        Arrays.stream(new AppHandlers().handlers).toList().forEach(handler -> handler.setHandle(app));
    }
}
