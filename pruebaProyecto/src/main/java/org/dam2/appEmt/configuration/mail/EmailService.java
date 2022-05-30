package org.dam2.appEmt.configuration.mail;

import org.dam2.appEmt.utilidades.Constantes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
    
    /**
     * Mail sender 
     */
    @Autowired
    private JavaMailSender mailSender;

    /**
     * 
     * @param toEmail email de receptor
     * @param body token de recuperacion
     * @throws MailException in case of failure when sending a message
     */
    public void sendEmail(String toEmail, String body) throws MailException{

        SimpleMailMessage message = new SimpleMailMessage();

        message.setFrom(Constantes.CORREO_ADMIN);
        message.setTo(toEmail);
        message.setSubject("Codigo recuperacion EMT AJA");
        message.setText("Su codigo de recuperacion es: " + body);

        mailSender.send(message);
    }

}
