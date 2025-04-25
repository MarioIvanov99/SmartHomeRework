package home.smarthome.util;

import home.smarthome.exception.InvalidCleaningAreaException;
import home.smarthome.exception.InvalidPowerConsumptionException;
import home.smarthome.exception.InvalidProductionDateException;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Validator {
    public static void checkNotNegative(BigDecimal value, String fieldName) {
        if (value.compareTo(BigDecimal.ZERO) < 0) {
            throw new InvalidPowerConsumptionException(fieldName + " cannot be negative.");
        }
    }

    public static void checkNotNegative(int value, String fieldName) {
        if (value < 0) {
            throw new InvalidCleaningAreaException(fieldName + " cannot be negative.");
        }
    }

    public static void checkProductionDate(LocalDate date) {
        if (date.isAfter(LocalDate.now())) {
            throw new InvalidProductionDateException("Production date cannot be in the future.");
        }
    }
}
