
package com.cs190.project.UserConfiguration;

import java.util.HashMap;
import java.util.Map;

public class Timer {

    private String timeOn;
    private double duration;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public String getTimeOn() {
        return timeOn;
    }

    public void setTimeOn(String timeOn) {
        this.timeOn = timeOn;
    }

    public double getDuration() {
        return duration;
    }

    public void setDuration(double duration) {
        this.duration = duration;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperties(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
