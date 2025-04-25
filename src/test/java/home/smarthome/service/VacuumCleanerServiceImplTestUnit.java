package home.smarthome.service;

import home.smarthome.exception.InvalidCleaningAreaException;
import home.smarthome.model.VacuumCleaner;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.awt.peer.TextAreaPeer;
import java.math.BigDecimal;

import static junit.framework.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class VacuumCleanerServiceImplTestUnit {

    VacuumCleanerService vacuumCleanerService;

    @Mock
    VacuumCleanerService mockVacuumCleanerService;

    @Mock
    VacuumCleaner vacuumCleaner;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        vacuumCleanerService = new VacuumCleanerServiceImpl();
        mockVacuumCleanerService = spy(vacuumCleanerService);
    }

    @Test
    void testGetCleanableAreaReturnsTargetAreaIfBatteryIsEnough_UNIT() {
        when(vacuumCleaner.isHasMop()).thenReturn(true);
        when(vacuumCleaner.getBatteryLevel()).thenReturn(BigDecimal.valueOf(100));
        when(vacuumCleaner.getPowerConsumption()).thenReturn(BigDecimal.valueOf(1));

        int expected = 10;
        BigDecimal actual = vacuumCleanerService.getCleanableArea(vacuumCleaner, expected);
        assertEquals(expected, actual.intValue());

        verify(vacuumCleaner).isHasMop();
        verify(vacuumCleaner).getBatteryLevel();
        verify(vacuumCleaner).getPowerConsumption();
    }

    @Test
    void testGetCleanableAreaReturnsMaxCleanableAreaIfBatteryIsNotEnough_UNIT() {
        when(vacuumCleaner.isHasMop()).thenReturn(true);
        when(vacuumCleaner.getBatteryLevel()).thenReturn(BigDecimal.valueOf(100));
        when(vacuumCleaner.getPowerConsumption()).thenReturn(BigDecimal.valueOf(11));

        int expected = 10;
        BigDecimal actual = vacuumCleanerService.getCleanableArea(vacuumCleaner, expected);
        assertNotEquals(expected, actual.intValue());

        verify(vacuumCleaner).isHasMop();
        verify(vacuumCleaner).getBatteryLevel();
        verify(vacuumCleaner).getPowerConsumption();
    }

    @Test
    void testCleanAreaThrowsExceptionIfTargetAreaIsNegative_UNIT() {
        assertThrows(InvalidCleaningAreaException.class, () -> vacuumCleanerService.cleanArea(vacuumCleaner, -1));
    }

    @Test
    void testCleanAreaCorrectlyUpdatesBattery_UNIT() {
        when(vacuumCleaner.getBatteryLevel()).thenReturn(BigDecimal.valueOf(100));
        when(vacuumCleaner.getPowerConsumption()).thenReturn(BigDecimal.valueOf(1));
        when(mockVacuumCleanerService.getCleanableArea(vacuumCleaner, 10)).thenReturn(BigDecimal.valueOf(10));
        Mockito.doNothing().when(mockVacuumCleanerService).displayResultMessage(10, BigDecimal.valueOf(10));
        Mockito.doNothing().when(vacuumCleaner).setBatteryLevel(BigDecimal.valueOf(90));

        mockVacuumCleanerService.cleanArea(vacuumCleaner, 10);

        verify(vacuumCleaner, times(2)).getBatteryLevel();
        verify(vacuumCleaner, times(2)).getPowerConsumption();
        verify(mockVacuumCleanerService).getCleanableArea(vacuumCleaner, 10);
        verify(mockVacuumCleanerService).displayResultMessage(10, BigDecimal.valueOf(10));
        verify(vacuumCleaner).setBatteryLevel(BigDecimal.valueOf(90));
    }
}