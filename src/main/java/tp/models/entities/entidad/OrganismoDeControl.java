package tp.models.entities.entidad;

import tp.models.entities.comunidad.Persona;
import lombok.Getter;
import lombok.Setter;
import tp.models.entities.servicios.TipoServicio;
import tp.models.entities.persistencia.Persistente;
import tp.models.entities.ranking.exportador.Exportador;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "organismoControl")
@Getter
@Setter
public class OrganismoDeControl extends Persistente {

    @Column(name = "nombre")
    private String nombre;

    @Transient
    private Persona responsableDesignado;

    @ManyToOne
    @JoinColumn(name = "tipoServicio_id", referencedColumnName = "id")
    private TipoServicio servicioQueRegula;


    @Transient
    private List<Empresa> listaEmpresasQueRegula;

    public OrganismoDeControl(){
        this.listaEmpresasQueRegula = new ArrayList<>();
    }

    public void agregarEmpresaAControlar(Empresa empresa){
        listaEmpresasQueRegula.add(empresa);
    }



    public List<Entidad> obtenerEntidadesCorrespondientes(List<Entidad> listaEntidades){
        return this.listaEmpresasQueRegula.stream().flatMap(unaEmpresa -> unaEmpresa.obtenerEntidadesPropias(listaEntidades).stream()).toList();
    }


    public void solicitarInformeSemanal(String formato){
        Exportador exportador = Exportador.getInstancia();
        exportador.generarInformeSemanal(this,formato);
    }
}
