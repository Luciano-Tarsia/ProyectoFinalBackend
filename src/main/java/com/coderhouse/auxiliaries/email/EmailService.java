package com.coderhouse.auxiliaries.email;

import com.coderhouse.model.Order;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;

@Service
@RequiredArgsConstructor
public class EmailService {

    private final JavaMailSender javaMailSender;

    public void sendEmail(Order order) {
        var message = new SimpleMailMessage();
        message.setTo("lucianocoderhouse@gmail.com");

        message.setSubject("Orden de compra");
        message.setText("Resumen de orden de compra \n" +
                "Fecha y hora de creacion de la orden" + order.getDate() + "\n" +
                "Numero de orden:" + order.getId() + "\n" +
                "Listado de productos" + order.getListOfItems().toString()
        );

        javaMailSender.send(message);
    }

}
