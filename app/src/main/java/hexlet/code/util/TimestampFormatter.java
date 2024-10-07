package hexlet.code.util;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

public class TimestampFormatter {
    public static final String TIMESTAMP_FORMAT_PATTERN = "dd/MM/yyyy HH:mm";

    public static String dateFormatter(Timestamp dateTime) {
        Date date = new Date(dateTime.getTime());
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(TIMESTAMP_FORMAT_PATTERN);
        return simpleDateFormat.format(date);
    }
}
