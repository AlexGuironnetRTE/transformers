package org.transformers.opensmartgridplatform;

public class IntervalReading {

    public IntervalReading(String value, long timeStamp) {
        this.value = value;
        this.timeStamp = timeStamp;
    }

    public String value;

    /** the dateTime as milliseconds from the epoch */ //TODO Check if milli or seconds
    public long timeStamp;

}
