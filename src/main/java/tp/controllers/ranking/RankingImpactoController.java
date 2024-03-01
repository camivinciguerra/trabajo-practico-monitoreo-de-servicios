package tp.controllers.ranking;

import io.javalin.http.Context;
import tp.models.entities.comunidad.Persona;
import tp.models.entities.entidad.Entidad;
import tp.models.entities.ranking.CriterioImpacto;
import tp.models.repositories.RepositorioEntidades;
import tp.server.utils.ICrudViewsHandler;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RankingImpactoController implements ICrudViewsHandler {
    @Override
    public void index(Context context) {
    }


    public void cargarRankingImpacto(Context context) {
        Map<String, Object> modelRankingImpacto = this.cargarModelImpacto(context);
        context.render("ranking/rankingImpacto.hbs", modelRankingImpacto);
    }

    public Map<String, Object> cargarModelImpacto(Context context){
        CriterioImpacto criterioCantidadIncidentes = new CriterioImpacto();
        Map<String, Object> modelRankingImpacto = new HashMap<>();
        List<Entidad> entidadades = RepositorioEntidades.getInstancia().all();
        criterioCantidadIncidentes.generarReporte(entidadades);
        List<Entidad> entidadesAMostrar = criterioCantidadIncidentes.getUltimaListaCriterioImpacto();
        modelRankingImpacto.put("entidades", entidadesAMostrar);
        Persona persona = context.sessionAttribute("Persona");
        modelRankingImpacto.put("persona",persona);
        return modelRankingImpacto;
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
}
