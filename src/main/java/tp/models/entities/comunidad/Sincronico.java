package tp.models.entities.comunidad;

import tp.models.entities.notificador.Notificacion;

public class Sincronico implements ConfiguracionRecepcion {
    public void gestionarNotificacion(Notificacion unaNotificacion){
        unaNotificacion.getEstrategiaDeNotificacion().notificar(unaNotificacion);
    }
}
