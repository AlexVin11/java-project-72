package hexlet.code.util;

import java.time.format.DateTimeFormatter;

public class TimestampFormatter {
    public static final String TIMESTAMP_FORMAT_PATTERN = "dd/MM/yyyy HH:mm";
    public static DateTimeFormatter formatterType1 = formatter(TIMESTAMP_FORMAT_PATTERN);

    public static DateTimeFormatter formatter(String pattern) {
        return DateTimeFormatter.ofPattern(pattern);
    }
}
