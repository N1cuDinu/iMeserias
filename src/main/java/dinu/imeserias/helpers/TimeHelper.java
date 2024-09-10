package dinu.imeserias.helpers;

import lombok.experimental.Helper;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.Calendar;
@Component
public class TimeHelper {
    public Timestamp getActualTime(){
        Calendar cal = Calendar.getInstance();
        java.sql.Timestamp timestamp = new java.sql.Timestamp(cal.getTimeInMillis());
        return timestamp;
    }
}
