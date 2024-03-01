package tp.models.entities.builders;

import tp.models.entities.comunidad.Miembro;
import tp.models.entities.comunidad.Persona;
import tp.models.entities.comunidad.Usuario;
import tp.models.entities.entidad.Entidad;
import tp.models.entities.servicios.TipoServicio;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class PersonaBuilder {
    private Persona persona = new Persona();

    public PersonaBuilder nombre(String nombre){
        persona.setNombreApellido(nombre);
        return this;
    }

    public PersonaBuilder numero(String numero){
        persona.setNumeroDeContacto(numero);
        return this;
    }
    public PersonaBuilder mail(String mail){
        persona.setMailDeContacto(mail);
        return this;
    }

    public PersonaBuilder usuario(Usuario usuario){
        persona.setUsuario(usuario);
        return this;
    }

    public PersonaBuilder membresias(Set<Miembro> membresias){
        this.persona.setMembresias(new HashSet<>());
        this.persona.getMembresias().addAll(membresias);
        return this;
    }

    public PersonaBuilder tipoServicios(List<TipoServicio> tipoServicios){
        this.persona.setTiposDeServiciosDeInteres(tipoServicios);
        return this;
    }

    public PersonaBuilder entidades(List<Entidad> entidads){
        this.persona.setEntidadesDeInteres(entidads);
        return this;
    }

    public Persona build(){
        return this.persona;
    }

}
