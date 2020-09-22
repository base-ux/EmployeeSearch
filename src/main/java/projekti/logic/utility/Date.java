package projekti.logic.utility;

import java.text.DateFormat;
import java.util.Calendar;
import org.springframework.stereotype.Service;

@Service
public class Date {

    public String date() {
        DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.FULL);
        Calendar cal = Calendar.getInstance();
        return dateFormat.format(cal.getTime());
    }
}
