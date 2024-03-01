package tp.models.entities.ranking.exportador;

import tp.models.entities.entidad.Empresa;
import tp.models.entities.entidad.Entidad;
import tp.models.entities.entidad.OrganismoDeControl;

import java.util.ArrayList;
import java.util.List;

public class Exportador {
    private List<ListaEntidadesCriterio> listaEntidadesCriterios = new ArrayList<>();

    private static Exportador instancia = null;

    public static Exportador getInstancia() {
        if (instancia == null) {
            instancia = new Exportador();
        }
        return instancia;
    }

    public void agregarListaEntidadesCriterio(ListaEntidadesCriterio unaListaEntidadesCriterio){
        listaEntidadesCriterios.add(unaListaEntidadesCriterio);
    }

    Documento generarDocumento(List<Entidad> listaEntidades, String criterioCorrespondiente){
        Documento unDocumento = new Documento();
        unDocumento.agregarDato("Criterio Correspondiente: ",criterioCorrespondiente);

        Integer i = 1;

        for(Entidad entidad :listaEntidades)
        {
            unDocumento.agregarDato(i.toString(), entidad.getNombre());
            i++;
        }
        return unDocumento;
    }

    public void exportar(Documento documento,String formatoDeExportacion,String nombreDelArchivo){
        EstrategiaDeExportacion estrategia = FactoryFormatoDeExportacion.crear(formatoDeExportacion, nombreDelArchivo);
        estrategia.exportarFormato(documento);
    }

    public void generarInformeSemanal(Empresa empresa, String formato){
        this.listaEntidadesCriterios.forEach(listaEntidadCriterio -> {
                    Documento unDocumento =
                            generarDocumento(empresa.obtenerEntidadesPropias
                                            (listaEntidadCriterio.listaEntidades),
                                    listaEntidadCriterio.nombreDeCriterio);
                    this.exportar(unDocumento, formato, listaEntidadCriterio.prefijoArchivo
                            + empresa.getNombre());});
    }

    public void generarInformeSemanal(OrganismoDeControl organismo, String formato){
        this.listaEntidadesCriterios.forEach(listaEntidadCriterio -> {
                    Documento unDocumento =
                            generarDocumento(organismo.obtenerEntidadesCorrespondientes
                                            (listaEntidadCriterio.listaEntidades),
                                    listaEntidadCriterio.nombreDeCriterio);
                    this.exportar(unDocumento, formato,listaEntidadCriterio.prefijoArchivo
                            + organismo.getNombre());}
                );
    }
}

