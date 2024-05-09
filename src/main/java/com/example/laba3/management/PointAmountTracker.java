package com.example.laba3.management;

import com.example.laba3.utils.MBeanRegistryUtil;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.Initialized;
import javax.enterprise.event.Observes;
import javax.inject.Named;
import javax.management.AttributeChangeNotification;
import javax.management.Notification;
import javax.management.NotificationBroadcasterSupport;

@Named
@ApplicationScoped
public class PointAmountTracker extends NotificationBroadcasterSupport implements PointAmountTrackerMBean {

	public void init(@Observes @Initialized(ApplicationScoped.class) Object unused) {
		MBeanRegistryUtil.registerBean(this, "PointAmountTrackerMBean");
	}

	private long sequenceNumber = 1L;
	private int amount;
	private int correctAmount;
	private int incorrectCounter;
	
    public PointAmountTracker() {
        addNotificationListener((notification, handback) -> {
            System.out.println("*** Handling new notification ***");
            System.out.println("Message: " + notification.getMessage());
            System.out.println("Seq: " + notification.getSequenceNumber());
            System.out.println("*********************************");
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
	        Notification n = new AttributeChangeNotification(
					this,
					sequenceNumber++,
					System.currentTimeMillis(),
					"Three incorrect clicks",
					"incorrectCounter",
					"int",
					2,
					3
			);
	        sendNotification(n);
	        incorrectCounter = 0;
		}
	}
}
