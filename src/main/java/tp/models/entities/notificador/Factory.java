package tp.models.entities.notificador;

import tp.models.entities.notificador.mail.EstrategiaDeMail;
import tp.models.entities.notificador.wpp.EstrategiaDeWPP;

public class Factory {

    public static EstrategiaDeNotificacion crear(String tipo) throws NoExisteFormatoException {
        EstrategiaDeNotificacion estrategiaDeNotificacion = null;
        if (tipo != null)
            switch (tipo) {
                case "MAIL" : estrategiaDeNotificacion = new EstrategiaDeMail(); break;
                case "WHATSAPP" : estrategiaDeNotificacion = new EstrategiaDeWPP(); break;
                default: throw new NoExisteFormatoException();
            }

        return estrategiaDeNotificacion;
    }

}
