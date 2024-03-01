package tp.models.entities.entidad;

import tp.models.entities.comunidad.Persona;
import lombok.Getter;
import lombok.Setter;
import tp.models.entities.persistencia.Persistente;
import tp.models.entities.ranking.exportador.Exportador;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Entity
@Table(name = "empresa")
@Getter
@Setter
public class Empresa extends Persistente {

    @Column(name = "nombre")
    private String nombre;

    @Transient
    private Persona responsableDesignado;

    @OneToMany
    @JoinColumn(name = "empresa_id", referencedColumnName = "id")
    private List<Entidad> listaDeEntidades;

    public Empresa(){
        this.listaDeEntidades = new ArrayList<>();
    }

    public void agregarEntidades(Entidad ... entidades) {
        Collections.addAll(this.listaDeEntidades, entidades);
    }

    public List<Entidad> obtenerEntidadesPropias(List<Entidad> unaListaDeEntidades){
        return unaListaDeEntidades.stream().filter( unaEntidad -> this.listaDeEntidades.contains(unaEntidad)).toList();
    }

    public void solicitarInformeSemanal(String formato){
        Exportador exportador = Exportador.getInstancia();
        exportador.generarInformeSemanal(this,formato);
    }
}
