package com.pdstpo.unomas.model;

import org.springframework.beans.factory.annotation.Autowired;

public class EmailNotification implements INotificationStrategy{

    @Autowired
    private IEmailNotificationAdapter adapter;

    @Override
    public void send(Notification notification) {
        adapter.send(notification);
    }
}
