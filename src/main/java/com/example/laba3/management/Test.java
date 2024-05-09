package com.example.laba3.management;

import javax.management.AttributeChangeNotification;
import javax.management.Notification;
import javax.management.NotificationBroadcasterSupport;
import javax.management.NotificationListener;

public class Test extends NotificationBroadcasterSupport implements TestMBean {

	private long sequenceNumber = 1L;
    private String message = "Hello World";
     
    public Test() {
        addNotificationListener(new NotificationListener() {
            @Override
            public void handleNotification(Notification notification, Object handback) {
                System.out.println("*** Handling new notification ***");
                System.out.println("Message: " + notification.getMessage());
                System.out.println("Seq: " + notification.getSequenceNumber());
                System.out.println("*********************************");
            }
        }, null, null);
    }

    @Override
    public String getMessage() {
        return this.message;
    }

    @Override
    public void sayHello() {
        System.out.println(message);
    }

    @Override
    public void setMessage(String message) {
        String oldMessage = this.message;
        this.message = message;
        Notification n = new AttributeChangeNotification(this, sequenceNumber++, System.currentTimeMillis(), "Message changed", "Message", "String", oldMessage, this.message);
        sendNotification(n);
    }
}
