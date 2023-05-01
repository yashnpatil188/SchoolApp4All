import java.text.DateFormatSymbols;

import org.com.maauli.Common;

public class dateToWord {

	// ////////////////////////////////////datetowords//////////////////////////
    /*static String string;

    static String st0[] = {"", "first", "second", "third", "fourth", "fifth", "sixth", "seventh",
            "eighth", "nineth",};
    
    static String st1[] = {"", "one", "two", "three", "four", "five", "six", "seven",
        "eight", "nine",};

    static String st2[] = {"hundred", "thousand"};

    static String st3[] = {"tenth", "eleventh", "twelfth", "thirteenth", "fourteenth",
        "fifteenth", "sixteenth", "seventeenth", "eighteenth", "ninteenth",};

    static String st4[] = {"twenty", "thirty", "forty", "fifty", "sixty", "seventy",
        "eighty", "ninety"};
    
    static String st5[] = {"twentieth", "thirtieth", "fortieth", "fiftieth", "sixtieth", "seventieth",
            "eightieth", "ninetieth"};
    
    static String st6[] = {"ten", "eleven", "twelve", "thirteen", "fourteen",
            "fifteen", "sixteen", "seventeen", "eighteen", "ninteen",};

    public static String dateToWords(int day, int month, int year) {

        System.out.println(day + "/" + month + "/" + year);
//        Common cm = new Common();
        String date2word =
            convert(day,"day") + " " + getMonth(month) + " " + convert(year,"year");
        return date2word;
    }

    public static String getMonth(int month) {

        return new DateFormatSymbols().getMonths()[month - 1];
    }

    public static String convert(int number, String dateType) {

        int n = 1;
        int word;
        string = "";
        while (number != 0) {
            switch (n) {
                case 1:
                    word = number % 100;
                    pass(word,dateType);
                    if (number > 100 && number % 100 != 0 && !dateType.equalsIgnoreCase("year")) {
                        show("and ");
                    }
                    number /= 100;
                    break;

                case 2:
                    word = number % 10;
                    if (word != 0) {
                    	if(dateType.equalsIgnoreCase("year") && number > 19){
	                        show(" ");
	                        show(st2[0]);
                    	}
                    	if(n == 2 && number < 20){
                    		show(" ");
                            pass(number,dateType);
                    	}
                    	else {
                    		show(" ");
                            pass(word,dateType);
                            number /= 10;
                    	}
                    }
                    break;

                case 3:
                    word = number % 100;
                    if (word != 0 && (dateType.equalsIgnoreCase("year") && n == 3 && number > 19)) {
                        show(" ");
                        show(st2[1]);
                        show(" ");
                        word = word / 10;
                        pass(word,dateType);
                    }
                    number /= 100;
                    break;

                case 4:
                    word = number % 100;
                    if (word != 0) {
                        show(" ");
                        show(st2[2]);
                        show(" ");
                        pass(word,dateType);
                    }
                    number /= 100;
                    break;

                case 5:
                    word = number % 100;
                    if (word != 0) {
                        show(" ");
                        show(st2[3]);
                        show(" ");
                        pass(word,dateType);
                    }
                    number /= 100;
                    break;

            }
            n++;
        }
        return string;
    }

    public static void pass(int number, String dateType) {

        int word, q;
        if (number < 10) {
        	if(dateType.equalsIgnoreCase("day")){
        		show(st0[number]);
        	} else{
        		show(st1[number]);
        	}
            
        }
        if (number > 9 && number < 20) {
        	if(dateType.equalsIgnoreCase("year")){
        		show(st6[number - 10]);
        	}
        	else {
        		show(st3[number - 10]);
        	}
        }
        if (number > 19) {
            word = number % 10;
            if (word == 0) {
                q = number / 10;
                if(dateType.equalsIgnoreCase("day")){
                	show(st5[q - 2]);
                }
                else {
                	show(st4[q - 2]);
                }
                
            } else {
                q = number / 10;
                if(dateType.equalsIgnoreCase("day")){
            		show(st0[word]);
            	} else{
            		show(st1[word]);
            	}
//                show(st1[word]);
                show(" ");
                show(st4[q - 2]);
            }
        }
    }

    public static void show(String s) {

        String st;
        st = string;
        string = s;
        string += st;
    }*/
    
	///////////////////Date to word without th ////////////////////////////////
	
	static String string;

	static String st1[] = {"", "one", "two", "three", "four", "five", "six", "seven",
        "eight", "nine",};

	static String st2[] = {"hundred", "thousand"};

	static String st3[] = {"ten", "eleven", "twelve", "thirteen", "fourteen",
        "fifteen", "sixteen", "seventeen", "eighteen", "ninteen",};

	static String st4[] = {"twenty", "thirty", "forty", "fifty", "sixty", "seventy",
        "eighty", "ninety"};

    public static String dateToWords(int day, int month, int year) {

        System.out.println(day + "/" + month + "/" + year);
//        Common cm = new Common();
        String date2word =
            convert(day) + " " + getMonth(month) + " " + convert(year);
        return date2word;
    }

    public static String getMonth(int month) {

        return new DateFormatSymbols().getMonths()[month - 1];
    }

    public static String convert(int number) {

        int n = 1;
        int word;
        string = "";
        while (number != 0) {
            switch (n) {
                case 1:
                    word = number % 100;
                    pass(word);
                    if (number > 100 && number % 100 != 0) {
                        show("and ");
                    }
                    number /= 100;
                    break;

                case 2:
                    word = number % 10;
                    if (word != 0) {
                        show(" ");
                        show(st2[0]);
                        show(" ");
                        pass(word);
                    }
                    number /= 10;
                    break;

                case 3:
                    word = number % 100;
                    if (word != 0) {
                        show(" ");
                        show(st2[1]);
                        show(" ");
                        pass(word);
                    }
                    number /= 100;
                    break;

                case 4:
                    word = number % 100;
                    if (word != 0) {
                        show(" ");
                        show(st2[2]);
                        show(" ");
                        pass(word);
                    }
                    number /= 100;
                    break;

                case 5:
                    word = number % 100;
                    if (word != 0) {
                        show(" ");
                        show(st2[3]);
                        show(" ");
                        pass(word);
                    }
                    number /= 100;
                    break;

            }
            n++;
        }
        return string;
    }

    public static void pass(int number) {

        int word, q;
        if (number < 10) {
            show(st1[number]);
        }
        if (number > 9 && number < 20) {
            show(st3[number - 10]);
        }
        if (number > 19) {
            word = number % 10;
            if (word == 0) {
                q = number / 10;
                show(st4[q - 2]);
            } else {
                q = number / 10;
                show(st1[word]);
                show(" ");
                show(st4[q - 2]);
            }
        }
    }

    public static void show(String s) {

        String st;
        st = string;
        string = s;
        string += st;
    }

    public static void main(String[] args) {
		// TODO Auto-generated method stub
		
//		Common cm = new Common();
		System.out.println(dateToWords(23, 01, 1998));

	}
}
