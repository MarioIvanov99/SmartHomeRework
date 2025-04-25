package home.smarthome.service;

import home.smarthome.model.SmartLight;

import java.math.BigDecimal;

public class SmartLightServiceImpl implements SmartLightService {
    @Override
    public BigDecimal getLumenOutput(SmartLight smartLight) {
        return smartLight.getLumensPerMode().get(smartLight.getLightingMode()).multiply(BigDecimal.valueOf(smartLight.getMaxLumens()));
    }
}
