package util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {

	public DateUtil() {
		// TODO Auto-generated constructor stub
	}

	public long calculateDaysRemaining(String endDateStr) {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date endDate = dateFormat.parse(endDateStr);
            
            long timeDiff = endDate.getTime() - (new Date().getTime());
            long daysRemaining = timeDiff / (1000 * 60 * 60 * 24);
            
            return daysRemaining;
        } catch (ParseException e) {
            e.printStackTrace();
            return -1; // Return -1 if there is an error parsing the dates
        }
    }
}
