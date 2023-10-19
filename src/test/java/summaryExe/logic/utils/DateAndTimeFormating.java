package summaryExe.logic.utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateAndTimeFormating {
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
    public static String formatTimeRange(int startHour, int startMinutes, int endHour, int endMinutes) {
        if (startHour < 0 || startHour > 23 || endHour < 0 || endHour > 23 ||
                startMinutes < 0 || startMinutes > 59 || endMinutes < 0 || endMinutes > 59) {
            return "Invalid time input";
        }

        String startAmPm = (startHour < 12) ? "am" : "pm";
        String endAmPm = (endHour < 12) ? "am" : "pm";

        // Convert to 12-hour time format
        if (startHour > 12) {
            startHour -= 12;
        }

        if (endHour > 12) {
            endHour -= 12;
        }

        String formattedTime = String.format("%d:%02d %s  -  %d:%02d %s", startHour, startMinutes, startAmPm, endHour, endMinutes, endAmPm);
        return formattedTime;
    }
}
