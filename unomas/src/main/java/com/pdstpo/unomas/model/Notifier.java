package com.pdstpo.unomas.model;

import java.util.ArrayList;
import java.util.List;

public class Notifier {
    private final List<INotificationStrategy> strategies;

    public Notifier() {
        strategies = new ArrayList<>();
        strategies.add(new EmailNotification());
        strategies.add(new PushNotification());
    }

    public void notify(Notification notification) {
        for (INotificationStrategy strategy : strategies) {
            strategy.send(notification);
        }
    }
}
