package hexlet.code.util;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

public class TimestampFormatter {
    private static final String CREATED_AT_FORMAT = "dd/MM/yyyy HH:mm";

    public static String dateFormatter(Timestamp dateTime) {
        Date date = new Date(dateTime.getTime());
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(CREATED_AT_FORMAT);
        return simpleDateFormat.format(date);
    }
}
