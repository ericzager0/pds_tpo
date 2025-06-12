package com.pdstpo.unomas.model;

public class EmailNotification implements INotificationStrategy{

    private final IEmailNotificationAdapter adapter;

    public EmailNotification() {
        this.adapter = new JavaMail();
    }

    @Override
    public void send(Notification notification) {
        adapter.send(notification);
    }
}
