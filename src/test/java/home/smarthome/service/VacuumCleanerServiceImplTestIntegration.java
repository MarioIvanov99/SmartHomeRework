package home.smarthome.service;

import home.smarthome.exception.InvalidCleaningAreaException;
import home.smarthome.model.DeviceType;
import home.smarthome.model.VacuumCleaner;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;

import static junit.framework.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class VacuumCleanerServiceImplTestIntegration {

    VacuumCleanerService vacuumCleanerService;
    VacuumCleaner vacuumCleaner;

    @BeforeEach
    void setUp() {
        vacuumCleanerService = new VacuumCleanerServiceImpl();
        vacuumCleaner = new VacuumCleaner(false, "test", LocalDate.now(), BigDecimal.valueOf(10), DeviceType.MOVABLE);
    }

    @Test
    void testGetCleanedAreaReturnsTargetAreaIfBatteryIsEnough_INTEGRATION() {
        BigDecimal actual = vacuumCleanerService.getCleanableArea(vacuumCleaner, 8);
        BigDecimal expected = new BigDecimal(8);
        assertEquals(expected, actual);
    }

    @Test
    void testGetCleanedAreaReturnsMaxCleanableAreaIfBatteryIsNotEnough_INTEGRATION() {
        BigDecimal actual = vacuumCleanerService.getCleanableArea(vacuumCleaner, 11);
        BigDecimal expected = new BigDecimal(10);
        assertEquals(expected, actual);
    }

    @Test
    void testCleanAreaThrowsExceptionIfTargetAreaIsNegative_INTEGRATION() {
        assertThrows(InvalidCleaningAreaException.class, () -> vacuumCleanerService.cleanArea(vacuumCleaner, -1));
    }

    @Test
    void testCleanAreaCorrectlyUpdatesBattery_INTEGRATION() {
        vacuumCleanerService.cleanArea(vacuumCleaner, 10);
        BigDecimal actual = vacuumCleaner.getBatteryLevel();
        BigDecimal expected = new BigDecimal(0);
        assertEquals(expected, actual);
    }
}