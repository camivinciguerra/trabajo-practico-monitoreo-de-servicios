package tp.controllers.ranking;

import io.javalin.http.Context;
import tp.models.entities.comunidad.Persona;
import tp.models.entities.entidad.Entidad;
import tp.models.entities.ranking.CriterioCantidadIncidentes;
import tp.models.repositories.RepositorioEntidades;
import tp.server.utils.ICrudViewsHandler;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RankingCantidadController implements ICrudViewsHandler {

    @Override
    public void index(Context context) {
        Map<String, Object> modelRankingCantidad = this.cargarRankingSegunCantidad(context);
        context.render("ranking/rankingPrincipal.hbs", modelRankingCantidad);
    }


    public Map<String, Object> cargarRankingSegunCantidad(Context context){
        CriterioCantidadIncidentes criterioCantidadIncidentes = new CriterioCantidadIncidentes();
        Map<String, Object> modelRankingCantidad = new HashMap<>();
        List<Entidad> entidadades = RepositorioEntidades.getInstancia().all();
        criterioCantidadIncidentes.generarReporte(entidadades);
        List<Entidad> entidadesAMostrar = criterioCantidadIncidentes.getUltimaListaCriterioCantidad();
        modelRankingCantidad.put("entidades", entidadesAMostrar);
        Persona persona = context.sessionAttribute("Persona");
        modelRankingCantidad.put("persona",persona);
        return modelRankingCantidad;
    }

    @Override
    public void create(Context context) {

    }

    @Override
    public void save(Context context) throws IOException {

    }

    @Override
    public void show(Context context) {

    }

    @Override
    public void edit(Context context) {

    }

    @Override
    public void update(Context context) {

    }

    @Override
    public void delete(Context context) {

    }

    public void prueba(Context context){
        List<Entidad> entidades = RepositorioEntidades.getInstancia().all();
        CriterioCantidadIncidentes criterioCantidadIncidentes = new CriterioCantidadIncidentes();
        criterioCantidadIncidentes.generarReporte(entidades);
    }
}