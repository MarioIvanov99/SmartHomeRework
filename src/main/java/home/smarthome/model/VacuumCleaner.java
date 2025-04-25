package home.smarthome.model;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;


@Getter
@Setter
public class VacuumCleaner extends Device {
    private BigDecimal batteryLevel;
    private boolean hasMop;

    public VacuumCleaner(boolean hasMop, String model, LocalDate productionDate, BigDecimal powerConsumption, DeviceType deviceType) {
        super(model, productionDate, powerConsumption, deviceType);
        this.batteryLevel = BigDecimal.valueOf(100);
        this.hasMop = hasMop;
    }
}