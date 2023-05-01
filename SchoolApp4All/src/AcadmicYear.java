
public class AcadmicYear {
	public static void main(String[] args) {
		String date = "01/06/2015";
		String academicYear = "";
		String month = "";
		String year = "";
		boolean retFlag = false;
		try{
			month = date.substring(date.indexOf("/")+1,date.lastIndexOf("/"));
			year  = date.substring(date.lastIndexOf("/")+1);
			
			if(month.contains("06") || month.contains("07") || month.contains("08") || month.contains("09") || 
					month.contains("10") || month.contains("11") || month.contains("12")){
				academicYear = year +"-"+ (Integer.parseInt(year.substring(2))+1);
			}
			else{
				academicYear = (Integer.parseInt(year)-1) +"-"+ (Integer.parseInt(year.substring(2)));
			}
    	}catch(Exception e){
    		System.out.println("Academic Year Exception == "+e);
    	}
	}

}
