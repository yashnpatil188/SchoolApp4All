import java.text.SimpleDateFormat;
    import java.util.Calendar;
    import java.util.Date;
public class CurrentDate {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try{
		Calendar cal = Calendar.getInstance();
	    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	    String strDate = sdf.format(cal.getTime());
	    System.out.println("Current date in String Format: "+strDate);

//	    SimpleDateFormat sdf1 = new SimpleDateFormat();
//	    sdf1.applyPattern("dd/MM/yyyy HH:mm:ss.SS");
//	    Date date = sdf1.parse(strDate);
//	    System.out.println("Current date in Date Format: "+date);
		}
		catch(Exception e){
			System.out.println("Exception e :: "+e);
		}

	}

}
