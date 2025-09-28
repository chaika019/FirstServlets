package utils;

import lombok.experimental.UtilityClass;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Optional;

@UtilityClass
public class LocalDateFormatter {
    private static final String Pattern = "yyyy-MM-dd";
    private static final DateTimeFormatter Formatter = DateTimeFormatter.ofPattern(Pattern);

    public LocalDate format(String date) {
        return LocalDate.parse(date, Formatter);
    }

    public boolean isValid(String date) {
        try {
            return Optional.ofNullable(date)
                    .map(LocalDateFormatter::format)
                    .isPresent();
        } catch (DateTimeParseException exception) {
            return false;
        }
    }
}
