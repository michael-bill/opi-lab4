package com.example.laba3.management;

import javax.management.AttributeChangeNotification;
import javax.management.Notification;
import javax.management.NotificationBroadcasterSupport;
import javax.management.NotificationListener;

public class PointAmountTracker extends NotificationBroadcasterSupport implements PointAmountTrackerMBean {

	private long sequenceNumber = 1L;
	private int amount;
	private int correctAmount;
	private int incorrectCounter;
	
    public PointAmountTracker() {
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
	public int getAmount() {
		return amount;
	}

	@Override
	public int getCorrectAmount() {
		return correctAmount;
	}

	@Override
	public void click(boolean result) {
		amount++;
		if (result) {
			correctAmount++;
			incorrectCounter = 0;
		} else if (++incorrectCounter == 3) {
	        Notification n = new AttributeChangeNotification(this, sequenceNumber++, System.currentTimeMillis(), "Three incorrect clicks", "incorrectCounter", "int", 2, 3);
	        sendNotification(n);
	        incorrectCounter = 0;
		}
	}
}
