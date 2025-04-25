package home.smarthome.service;

import home.smarthome.model.SmartLight;

import java.math.BigDecimal;

public interface SmartLightService {
    BigDecimal getLumenOutput(SmartLight smartLight);
}
