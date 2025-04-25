package home.smarthome.service;

import home.smarthome.model.VacuumCleaner;

import java.math.BigDecimal;

public interface VacuumCleanerService {
    BigDecimal getCleanableArea(VacuumCleaner vacuumCleaner, int targetArea);
    void cleanArea(VacuumCleaner vacuumCleaner, int targetArea);
    void displayResultMessage(int targetArea, BigDecimal cleanedArea);
}
