import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class DateIncrease {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			String date = "27/02/2010";
			String incDate;
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			Calendar c = Calendar.getInstance();

			c.setTime(sdf.parse(date));
			int maxDay = c.getActualMaximum(Calendar.DAY_OF_MONTH);
			maxDay = 7;
			System.out.println("maxDay :: " + maxDay);
			for (int co = 0; co < maxDay; co++) {
				if (co == 0) {
					incDate = sdf.format(c.getTime());
				} else {
					c.add(Calendar.DATE, 1);
					incDate = sdf.format(c.getTime());
				}
				System.out.println(incDate);
			}

		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
