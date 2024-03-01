package tp.models.entities.builders;

import tp.models.entities.entidad.Entidad;
import tp.models.entities.entidad.Establecimiento;
import tp.models.entities.services.georef.Localidad;
import tp.models.entities.services.georef.Municipio;
import tp.models.entities.services.georef.Provincia;
import tp.models.entities.servicios.Servicio;


import java.util.List;

public class EstablecimientoBuilder {

    private Establecimiento establecimiento;

    public EstablecimientoBuilder() {
        this.establecimiento = new Establecimiento();
    }

    public EstablecimientoBuilder nombre(String nombre) {
        this.establecimiento.setNombre(nombre);
        return this;
    }

    public EstablecimientoBuilder provincia(Provincia provincia) {
        this.establecimiento.setProvincia(provincia);
        return this;
    }

    public EstablecimientoBuilder localidad(Localidad localidad) {
        this.establecimiento.setLocalidad(localidad);
        return this;
    }

    public EstablecimientoBuilder municipio(Municipio municipio) {
        this.establecimiento.setMunicipio(municipio);
        return this;
    }

    public EstablecimientoBuilder servicios(List<Servicio> servicios) {
        this.establecimiento.setServicios(servicios);
        return this;
    }

    public EstablecimientoBuilder entidad(Entidad entidad) {
        this.establecimiento.setEntidadALaQuePertenece(entidad);
        return this;
    }

    public Establecimiento build() {
        return this.establecimiento;
    }
}
