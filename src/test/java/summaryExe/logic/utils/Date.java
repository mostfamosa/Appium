package summaryExe.logic.utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Date {
    public static String getTodayDate() {
        LocalDate currentDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return currentDate.format(formatter);
    }

    public static String getTomorrowDate() {
        LocalDate currentDate = LocalDate.now();
        LocalDate tomorrowDate = currentDate.plusDays(1);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return tomorrowDate.format(formatter);
    }
}
