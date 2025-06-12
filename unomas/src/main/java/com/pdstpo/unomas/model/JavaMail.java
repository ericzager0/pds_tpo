package com.pdstpo.unomas.model;

public class JavaMail implements IEmailNotificationAdapter{

    @Override
    public void send(Notification notification) {
        System.out.println("Se envía notificación email a " + notification.getTo().getUsername() + ". Mensaje: " + notification.getMessage());
    }
}
