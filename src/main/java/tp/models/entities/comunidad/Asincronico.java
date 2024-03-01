package tp.models.entities.comunidad;

import tp.models.entities.notificador.Notificacion;

import java.util.*;

public class Asincronico implements ConfiguracionRecepcion{
    private List<Notificacion> listaNotificacionesEnviar;
    private List<Integer> horarios;

    public Asincronico() {
        listaNotificacionesEnviar = new ArrayList<>();

        ConfiguracionDiferida.getInstancia();
        ConfiguracionDiferida.agregarAsincronico(this);
    }

    public void setHorarios(List<Integer> listaHorarios){
        horarios = listaHorarios;
    }

    public void gestionarNotificacion(Notificacion unaNotificacion) {
        listaNotificacionesEnviar.add(unaNotificacion);
    }

    public void enviarNotificaciones(int horario) {
        if (!listaNotificacionesEnviar.isEmpty() && horarios.contains(horario)){

            listaNotificacionesEnviar.forEach(noti -> noti.getEstrategiaDeNotificacion().notificar(noti));
            listaNotificacionesEnviar.clear();
        }
    }

    public void agregarHorario(int horario) {
        horarios.add(horario);
    }
}
