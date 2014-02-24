
package com.cs190.project.UserConfiguration;

import java.util.HashMap;
import java.util.Map;

public class Sensors {

    private TempSensor tempSensor;
    private PhSensor phSensor;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public TempSensor getTempSensor() {
        return tempSensor;
    }

    public void setTempSensor(TempSensor tempSensor) {
        this.tempSensor = tempSensor;
    }

    public PhSensor getPhSensor() {
        return phSensor;
    }

    public void setPhSensor(PhSensor phSensor) {
        this.phSensor = phSensor;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperties(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
