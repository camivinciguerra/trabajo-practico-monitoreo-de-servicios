package tp.models.entities.builders;

import tp.models.entities.comunidad.Comunidad;
import tp.models.entities.comunidad.CondicionDeMiembro;
import tp.models.entities.comunidad.Miembro;
import tp.models.entities.comunidad.Rol;
import tp.models.entities.notificador.NoExisteFormatoException;

public class MiembroBuilder {
    private Miembro miembro = new Miembro();

    public MiembroBuilder estrategiaDeNotificacion(String estrategiaDeNotificacion) throws NoExisteFormatoException {
        miembro.setEstrategia(estrategiaDeNotificacion);
        return this;
    }

    public MiembroBuilder comunidad(Comunidad comunidad){
        miembro.setComunidad(comunidad);
        return this;
    }

    public MiembroBuilder rol(Rol rol){
        miembro.setRol(rol);
        return this;
    }

    public MiembroBuilder condicionMiembre(CondicionDeMiembro condicionDeMiembro){
        miembro.setCondicionDeMiembro(condicionDeMiembro);
        return this;
    }

    public Miembro build(){
        return this.miembro;
    }
}
