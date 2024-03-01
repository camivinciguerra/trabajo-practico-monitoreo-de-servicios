package tp.models.entities.notificador.mail;

import tp.models.entities.notificador.EstrategiaDeNotificacion;
import tp.models.entities.notificador.Notificacion;

import javax.mail.*;
import javax.mail.internet.*;
import java.util.Properties;


public class EstrategiaDeMail implements EstrategiaDeNotificacion {

    @Override
    public void notificar(Notificacion notificacion) {
        String mail = notificacion.getMailDeContacto();
        String asunto = notificacion.getAsunto();
        String cuerpo = notificacion.getCuerpo();

        this.enviarMail(mail, asunto, cuerpo);
    }

    public void enviarMail(String destinatario, String asunto, String cuerpo) {
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");

        // Credenciales del remitente
        final String remitente = "sistemaserviciosdds@gmail.com";
        final String contrasenia = "vrgxvecnifuesxfg";

        // Crea la sesión de mail
        Session session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(remitente, contrasenia);
            }
        });

        try {
            Message message = new MimeMessage(session);

            message.setFrom(new InternetAddress(remitente));

            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(destinatario));

            message.setSubject(asunto);

            message.setText(cuerpo);

            Transport.send(message);

            System.out.println("Correo electrónico enviado correctamente.");
        } catch (MessagingException e) {

            System.out.println("Error al enviar el correo electrónico: " + e.getMessage());

        }
    }

}
