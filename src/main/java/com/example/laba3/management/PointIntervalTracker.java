package com.example.laba3.management;

import java.util.Arrays;
import java.util.stream.Collectors;

public class PointIntervalTracker implements PointIntervalTrackerMBean {

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
		String interval = Arrays.asList(days != 0L ? days + " days" : null, hours != 0L ? hours + " hours" : null, minutes != 0L ? minutes + " minutes" : null, seconds != 0L ? seconds + " seconds" : null).stream().filter(item -> item != null).collect(Collectors.joining(" "));
		return interval;
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
