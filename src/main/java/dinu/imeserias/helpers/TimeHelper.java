package dinu.imeserias.helpers;

import java.sql.Timestamp;
import java.util.Calendar;

public class TimeHelper {
    public Timestamp getActualTime(){
        Calendar cal = Calendar.getInstance();
        java.sql.Timestamp timestamp = new java.sql.Timestamp(cal.getTimeInMillis());
        return timestamp;
    }
}
