package home.smarthome.service;


import home.smarthome.model.LightingMode;
import home.smarthome.model.SmartLight;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.util.EnumMap;

import static junit.framework.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class SmartLightServiceImplTestUnit {

    SmartLightService smartLightService;

    @Mock
    SmartLight smartLight;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        smartLightService = new SmartLightServiceImpl();
    }

    @Test
    void testgetLumenOutputReturnsCorrectValue_UNIT() {
        EnumMap<LightingMode, BigDecimal> lumensPerMode = new EnumMap<>(LightingMode.class);
        lumensPerMode.put(LightingMode.STRONG, BigDecimal.valueOf(1));
        lumensPerMode.put(LightingMode.MEDIUM, BigDecimal.valueOf(0.75));
        lumensPerMode.put(LightingMode.DIM, BigDecimal.valueOf(0.5));

        when(smartLight.getLumensPerMode()).thenReturn(lumensPerMode);
        when(smartLight.getLightingMode()).thenReturn(LightingMode.MEDIUM);
        when(smartLight.getMaxLumens()).thenReturn(100);

        BigDecimal actual = smartLightService.getLumenOutput(smartLight);
        BigDecimal expected = new BigDecimal("75.00");
        assertEquals(expected, actual);

        verify(smartLight).getLumensPerMode();
        verify(smartLight).getLightingMode();
        verify(smartLight).getMaxLumens();
    }
}