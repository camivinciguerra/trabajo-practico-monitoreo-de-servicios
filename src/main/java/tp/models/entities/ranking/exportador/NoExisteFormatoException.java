package tp.models.entities.ranking.exportador;

public class NoExisteFormatoException extends RuntimeException {
    public NoExisteFormatoException() {
        super("No existe el formato que está queriendo instanciar");
    }
}
