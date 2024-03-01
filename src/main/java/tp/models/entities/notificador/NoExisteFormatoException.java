package tp.models.entities.notificador;

public class NoExisteFormatoException extends Throwable {
    public NoExisteFormatoException() {
        super("No existe el formato que está queriendo instanciar");
    }
}
