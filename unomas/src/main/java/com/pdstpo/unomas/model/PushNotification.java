package com.pdstpo.unomas.model;

public class PushNotification implements INotificationStrategy {

    @Override
    public void send(Notification notification) {
        System.out.println("Se envía notificación push a " + notification.getTo().getUsername());
    }
}
