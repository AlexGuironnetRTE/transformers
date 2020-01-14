package org.transformers.opensmartgridplatform;

public class IntervalReading {

    private String value;
    /** the dateTime as milliseconds from the epoch */ //TODO Check if milli or seconds
    private long timeStamp;

    public IntervalReading(String value, long timeStamp) {
        this.value = value;
        this.timeStamp = timeStamp;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
