package home.smarthome.model;

import home.smarthome.util.Validator;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
public abstract class Device {
    private final String model;
    private final LocalDate productionDate;
    private final BigDecimal powerConsumption;
    private final DeviceType deviceType;

    public Device(String model, LocalDate productionDate, BigDecimal powerConsumption, DeviceType deviceType) {
        Validator.checkProductionDate(productionDate);
        Validator.checkNotNegative(powerConsumption, "Power consumption");

        this.model = model;
        this.productionDate = productionDate;
        this.powerConsumption = powerConsumption;
        this.deviceType = deviceType;
    }
}