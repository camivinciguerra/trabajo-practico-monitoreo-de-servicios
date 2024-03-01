package tests;

import tp.models.entities.comunidad.Comunidad;
import tp.models.entities.comunidad.Miembro;
import tp.models.entities.entidad.Entidad;
import tp.models.entities.entidad.Establecimiento;
import tp.models.entities.notificador.mail.EstrategiaDeMail;
import tp.models.entities.notificador.wpp.EstrategiaDeWPP;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import tp.models.entities.servicios.Servicio;

import static org.mockito.Mockito.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class EnvioDeNotisTest {

    private Comunidad comunidad;
    private Miembro franco;
    private Miembro chiara;
    private Entidad entidad;
    private Establecimiento establecimiento;
    private Servicio servicio;

    @Test
    public void envioWPP(){

        String destinarario = "1154655876";
        String mensaje = "";
        EstrategiaDeWPP whatsapp = mock(EstrategiaDeWPP.class);
        when(whatsapp.enviarMensaje(destinarario, mensaje)).thenReturn("OK");
        Assertions.assertEquals("OK", whatsapp.enviarMensaje(destinarario, mensaje));
    }
    
    @Test 
    public void envioMail(){
        String mailTest = "fmarsico03@gmail.com";
        String asunto = "Test";
        String mensaje = "Funciona Test?";

        EstrategiaDeMail mail = new EstrategiaDeMail();
        mail.enviarMail(mailTest,asunto,mensaje);
    }

}
