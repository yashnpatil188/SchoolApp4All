import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		String dateFrom = "02/09/2015";
		String dateTo = "10/09/2015";

		try {

			Date dateF = formatter.parse(dateFrom);
			System.out.println(dateF);
			System.out.println(formatter.format(dateF));
			
			Date dateT = formatter.parse(dateTo);
			System.out.println(dateT);
			System.out.println(formatter.format(dateT));
			
			int numberOfDays = 0;
	    	try {
//	    		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
	    		
				Date a1 = formatter.parse(formatter.format(dateF));
				Date a2 = formatter.parse(formatter.format(dateT));
				numberOfDays = (int) ((a2.getTime()-a1.getTime()) / (60*60*24*1000) );
				System.out.println("numberOfDays :: "+numberOfDays);
				
				for(int i = 0 ; i <= numberOfDays ; i++){
					Calendar cal = Calendar.getInstance();
					cal.setTime( formatter.parse( dateFrom ) );
					if(i != 0){
						cal.add( Calendar.DATE, 1 );
					}
					System.out.println(formatter.format(cal.getTime()).toString());
					dateFrom = formatter.format(cal.getTime());
				}
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} catch (ParseException e) {
			e.printStackTrace();
		}

	}

}
