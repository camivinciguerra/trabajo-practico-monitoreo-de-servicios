package tp.models.entities.servicios;

import tp.models.entities.comunidad.Comunidad;
import tp.models.entities.comunidad.Miembro;
import java.time.format.DateTimeFormatter;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import tp.models.entities.notificador.Notificacion;
import tp.models.entities.persistencia.Persistente;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.time.Duration;
import java.time.LocalDateTime;

@Entity
@Table(name = "incidente")
@Getter
@Setter
public class Incidente extends Persistente {

    @ManyToOne
    @JoinColumn(name = "comunidad_id", referencedColumnName = "id")
    private Comunidad comunidad;

    @Enumerated(EnumType.STRING)
    @Column(name = "estado")
    private Estado estado;

    @Column(name = "fechaApertura", columnDefinition = "TIMESTAMP")
    private LocalDateTime fechaApertura;

    @Column(name = "fechaCierre", columnDefinition = "TIMESTAMP")
    private LocalDateTime fechaCierre;

    @Column(name = "descripcion", columnDefinition = "text")
    private String descripcion;

    @ManyToOne
    @JoinColumn(name = "miembroDeApertura_id", referencedColumnName = "id")
    private Miembro miembroDeApertura;

    @ManyToOne
    @JoinColumn(name = "miembroDeCierre_id", referencedColumnName = "id")
    private Miembro miembroDeCierre;

    @Transient
    private Notificacion notificacion = new Notificacion();

    @Column(name = "descripcionLugar")
    private String descripcionLugar;

    @ManyToOne
    @JoinColumn(name = "servicio_id", referencedColumnName = "id")
    private Servicio servicio;

    public void aperturaDeIncidente(String descripcionDelIncidente, String lugar, LocalDateTime unaFechaApertura){
        String asunto = "Apertura de incidente" + " en " + lugar;
        setDescripcionLugar(lugar);
        setDescripcion(descripcionDelIncidente);
        setFechaApertura(LocalDateTime.now());
        notificacion.setAsunto(asunto);
        notificacion.setCuerpo(descripcionDelIncidente);
    }

    public void cierreDeIncidente(LocalDateTime unaFechaCierre, Miembro miembro){
        String asunto = "Cierre de incidente" + " en " + descripcionLugar;
        setFechaCierre(unaFechaCierre);
        notificacion.setAsunto(asunto);
        notificacion.setCuerpo(descripcion);
        this.setEstado(Estado.CERRADO);
        this.setMiembroDeCierre(miembro);
    }

    public Duration getDuracion() {
        if (this.getFechaCierre() != null) {
            return Duration.between(this.getFechaApertura(), this.getFechaCierre());
        }
        return Duration.ZERO;
    }

    public Boolean isAbierto(){
        return this.getEstado().toString().equals("ABIERTO");
    }

    public String formatDate(){
        return this.fechaApertura.format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm"));
    }

}

