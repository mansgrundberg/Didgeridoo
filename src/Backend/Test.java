package Backend;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class Test {
	public static void main(String[] args) {
		System.out.println(System.currentTimeMillis());
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(Long.parseLong("1578579308000"));
		SimpleDateFormat format = new SimpleDateFormat("dd-MM-yy HH:mm:ss");
		format.setTimeZone(TimeZone.getTimeZone("CET"));
		
		System.out.println(format.format(calendar.getTime()));
		

	}
}
