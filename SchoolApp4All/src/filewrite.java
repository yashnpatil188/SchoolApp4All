import java.io.*;

import org.com.maauli.Common;
public class filewrite
{
    public static void main(String[] args) throws IOException
    {
    	//Common cm = new Common();
    	//System.out.println("User==>"+cm.fileWriter("Yashpal"));
try{
    	File f = new File("/yash.txt");
        FileWriter fw = new FileWriter(f,false);
        if(f.exists())
        {
           String str = "Yashpal";
           //String strappend = ",";
           fw.write(str);
           fw.flush();
           fw.close();
           System.out.println("File write procedure completed..."); 
        } 
}catch(Exception e){System.out.println("Exception "+e);}
    }
}
