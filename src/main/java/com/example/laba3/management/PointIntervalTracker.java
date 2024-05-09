package com.example.laba3.management;

import com.example.laba3.utils.MBeanRegistryUtil;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.Destroyed;
import javax.enterprise.context.Initialized;
import javax.enterprise.event.Observes;
import javax.inject.Named;
import java.util.Arrays;
import java.util.stream.Collectors;

@Named
@ApplicationScoped
public class PointIntervalTracker implements PointIntervalTrackerMBean {

	public void init(@Observes @Initialized(ApplicationScoped.class) Object unused) {
		MBeanRegistryUtil.registerBean(this, "PointIntervalTrackerMBean");
	}

	public void destroy(@Observes @Destroyed(ApplicationScoped.class) Object unused) {
		MBeanRegistryUtil.unregisterBean(this);
	}

	private static long DAY = 86400000L;
	private static long HOUR = 3600000L;
	private static long MIN = 60000L;
	private static long SEC = 1000L;
	
	private long firstClick = 0L;
	private int amount;
	private long interval = 0L;
	
	@Override
	public String getInterval() {
		long days = interval / DAY;
		long hours = (interval - days * DAY) / HOUR;
		long minutes = (interval - days * DAY - hours * HOUR) / MIN;
		long seconds = (interval - days * DAY - hours * HOUR - minutes * MIN) / SEC;
        return Arrays.asList(days != 0L ? days + " days" : null, hours != 0L ? hours + " hours" : null, minutes != 0L ? minutes + " minutes" : null, seconds != 0L ? seconds + " seconds" : null).stream().filter(item -> item != null).collect(Collectors.joining(" "));
	}

	@Override
	public void click() {
		amount++;
		if (firstClick == 0L) {
			firstClick = System.currentTimeMillis();
		} else {
			interval = (System.currentTimeMillis() - firstClick) / (amount > 1 ? amount - 1 : 1);
		}
	}
}
