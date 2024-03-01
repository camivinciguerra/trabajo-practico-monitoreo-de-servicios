package tp.models.entities.builders;

import tp.models.entities.servicios.Servicio;
import tp.models.entities.servicios.TipoServicio;

public class ServicioBuilder {

    private Servicio servicio;

    public ServicioBuilder() {
        this.servicio = new Servicio();
    }

    public ServicioBuilder nombre(String nombre) {
        this.servicio.setNombre(nombre);
        return this;
    }

    public ServicioBuilder tipoServicio(TipoServicio tipoServicio) {
        this.servicio.setTipoServicio(tipoServicio);
        return this;
    }

    public ServicioBuilder estaActivo(Boolean estaActivo) {
        this.servicio.setEstaActivo(estaActivo);
        return this;
    }

    public Servicio build() {
        return this.servicio;
    }
}
