package home.smarthome.service;

import home.smarthome.model.VacuumCleaner;
import home.smarthome.util.Validator;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class VacuumCleanerServiceImpl implements VacuumCleanerService {
    @Override
    public BigDecimal getCleanableArea(VacuumCleaner vacuumCleaner, int targetArea) {
        BigDecimal powerConsumption = vacuumCleaner.isHasMop() ? vacuumCleaner.getPowerConsumption().multiply(BigDecimal.valueOf(2)) : vacuumCleaner.getPowerConsumption();
        BigDecimal maxCleanableArea = vacuumCleaner.getBatteryLevel().divide(powerConsumption, RoundingMode.HALF_UP);
        return BigDecimal.valueOf(targetArea).min(maxCleanableArea);
    }

    @Override
    public void cleanArea(VacuumCleaner vacuumCleaner, int targetArea) {
        Validator.checkNotNegative(targetArea, "Target area");
        BigDecimal cleaned = getCleanableArea(vacuumCleaner, targetArea);
        displayResultMessage(targetArea, cleaned);
        vacuumCleaner.setBatteryLevel(vacuumCleaner.getBatteryLevel().subtract(cleaned.multiply(vacuumCleaner.getPowerConsumption())));
    }

    @Override
    public void displayResultMessage(int targetArea, BigDecimal cleanedArea) {
        if(cleanedArea.equals(BigDecimal.valueOf(targetArea))) {
            System.out.println("Successfully cleaned " + targetArea + " square meter(s).");
        }
        else {
            System.out.println("Battery insufficient. Only " + cleanedArea.intValue() + " square meter(s) were cleaned");
        }
    }
}
