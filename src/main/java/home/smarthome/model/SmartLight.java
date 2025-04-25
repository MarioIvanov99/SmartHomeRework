package home.smarthome.model;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.EnumMap;

@Getter
@Setter
public class SmartLight extends Device {
    private final int maxLumens;
    private LightingMode lightingMode;
    private final EnumMap<LightingMode, BigDecimal> lumensPerMode;

    public SmartLight(String model, LocalDate productionDate, BigDecimal powerConsumption, DeviceType deviceType, int maxLumens, LightingMode lightingMode, EnumMap<LightingMode, BigDecimal> lumensPerMode) {
        super(model, productionDate, powerConsumption, deviceType);
        this.maxLumens = maxLumens;
        this.lightingMode = lightingMode;
        this.lumensPerMode = lumensPerMode;
    }
}