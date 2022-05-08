package utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateGenerator {

    public LocalDate getCurrentDate() {
        return LocalDate.now();
    }

    public String currentDateAsString() {
        DateTimeFormatter localDateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate date = getCurrentDate();
        return date.format(localDateFormatter);
    }

}
