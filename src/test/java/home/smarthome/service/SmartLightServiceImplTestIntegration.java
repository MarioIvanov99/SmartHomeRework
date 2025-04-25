package home.smarthome.service;


import home.smarthome.model.DeviceType;
import home.smarthome.model.LightingMode;
import home.smarthome.model.SmartLight;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.EnumMap;

import static junit.framework.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class SmartLightServiceImplTestIntegration {

    SmartLightService smartLightService;

    SmartLight smartLight;

    @BeforeEach
    void setUp() {
        smartLightService = new SmartLightServiceImpl();

        EnumMap<LightingMode, BigDecimal> lumensPerMode = new EnumMap<>(LightingMode.class);
        lumensPerMode.put(LightingMode.STRONG, BigDecimal.valueOf(1));
        lumensPerMode.put(LightingMode.MEDIUM, BigDecimal.valueOf(0.75));
        lumensPerMode.put(LightingMode.DIM, BigDecimal.valueOf(0.5));

        smartLight = new SmartLight("Smart Light", LocalDate.of(2020, 1, 1), BigDecimal.valueOf(0.5), DeviceType.BUILT_IN, 100, LightingMode.STRONG, lumensPerMode);
    }

    @Test
    void testgetLumenOutputReturnsCorrectValue_INTEGRATION() {
        smartLight.setLightingMode(LightingMode.MEDIUM);
        BigDecimal actual = smartLightService.getLumenOutput(smartLight);
        BigDecimal expected = new BigDecimal("75.00");
        assertEquals(expected, actual);
    }
}