package tp.controllers;

import io.javalin.http.Context;
import tp.models.entities.builders.EstablecimientoBuilder;
import tp.models.entities.builders.ServicioBuilder;
import tp.models.entities.comunidad.Comunidad;
import tp.models.entities.comunidad.Persona;
import tp.models.entities.entidad.Entidad;
import tp.models.entities.entidad.Establecimiento;
import tp.models.entities.services.georef.Localidad;
import tp.models.entities.services.georef.Municipio;
import tp.models.entities.services.georef.Provincia;
import tp.models.entities.servicios.Servicio;
import tp.models.entities.servicios.TipoServicio;
import tp.models.repositories.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EstablecimientoController {

    public void establecimientoSegunComunidad(Context context){
        String idComunidad = context.pathParam("idComunidad");
        List<Establecimiento> establecimientos = RepositorioEstablecimientos.getInstancia().all();
        Comunidad comunidad = RepositorioComunidades.getInstancia().findById(Long.parseLong(idComunidad));
        List<Establecimiento> establecimientosFiltrados = comunidad.establecimientosSegunComunidad(establecimientos);

        List<Map<String, Object>> establecimientosData = new ArrayList<>();
        for (Establecimiento establecimiento : establecimientosFiltrados) {
            Map<String, Object> establecimientoMap = new HashMap<>();
            establecimientoMap.put("id", establecimiento.getId());
            establecimientoMap.put("nombre", establecimiento.getNombre());
            establecimientosData.add(establecimientoMap);
        }
        context.json(establecimientosData);
    }

    public void cargaEstablecimiento(Context context){
        Map<String,Object> model = new HashMap<>();
        List<TipoServicio> tipoServicios = RepositorioTiposDeServicio.getInstancia().all();
        model.put("Tipos", tipoServicios);
        List<Entidad> entidades = RepositorioEntidades.getInstancia().all();
        model.put("Entidades", entidades);
        Persona persona = context.sessionAttribute("Persona");
        model.put("persona",persona);
        context.render("establecimientos/cargaEstablecimientos.hbs", model);
    }

    public void crearEstablecimiento(Context context) {

        String idEntidad = context.formParam("entidad");
        Entidad entidad = RepositorioEntidades.getInstancia().findById(Long.parseLong(idEntidad));

        String nombreEstablecimiento = context.formParam("nombre");

        String idProvincia = context.formParam("provincias");
        Provincia provincia = RepositorioProvincias.getInstancia().findById(Long.parseLong(idProvincia));

        String idMunicipio = context.formParam("municipios");
        Municipio municipio = null;
        if (idMunicipio != null) {
            municipio = RepositorioMunicipios.getInstancia().findById(Long.parseLong(idMunicipio));
        }
        String idLocalidad = context.formParam("localidades");
        Localidad localidad = null;
        if (idLocalidad != null){
            localidad = RepositorioLocalidades.getInstancia().findById(Long.parseLong(idLocalidad));
        }

        List<Servicio> servicios = new ArrayList<>();
        int cantidadServicios = Integer.parseInt(context.formParam("cantidadServicios"));
        System.out.println(cantidadServicios);
        for (int i = 0; i <= cantidadServicios - 1; i++) {
            String tipoServicioAdicional = context.formParam("tipoServicio_" + i);
            System.out.println(tipoServicioAdicional);
            TipoServicio tipoServicio = RepositorioTiposDeServicio.getInstancia().findById(Long.parseLong(tipoServicioAdicional));
            String nombreServicioAdicional = context.formParam("servicios_nombre_" + i);
            System.out.println(nombreServicioAdicional);
            Servicio servicio = new ServicioBuilder().nombre(nombreServicioAdicional).tipoServicio(tipoServicio).estaActivo(true).build();
            servicios.add(servicio);
            RepositorioServicios.getInstancia().registrar(servicio);
        }
        Establecimiento establecimiento = new EstablecimientoBuilder()
                .nombre(nombreEstablecimiento)
                .provincia(provincia)
                .municipio(municipio)
                .localidad(localidad)
                .entidad(entidad)
                .servicios(servicios)
                .build();
        entidad.getListaEstablecimientos().add(establecimiento);

        RepositorioEntidades.getInstancia().update(entidad);
        RepositorioEstablecimientos.getInstancia().registrar(establecimiento);
        context.redirect("/inicio");
    }
}
