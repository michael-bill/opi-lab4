package com.example.laba3.management;

public interface PointAmountTrackerMBean {

    int getAmount();

    int getCorrectAmount();

    void click(boolean result);
    
}
