package org.com.maauli;
import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import org.com.accesser.DBValidate;
import org.com.accesser.SessionData;

import java.io.*;

public class ReadFileExample {
	static DBValidate dbvalidate = new DBValidate();
	static Common commonObj = new Common();
	public static void main(String[] args) throws Exception {
		JFrame f = new JFrame("Backup Data Import in progress");
		try {
			String currentDir = "", absolutePath = "", aSQLScriptFilePath = "";
			String dburl = args[0].substring(0, args[0].indexOf(" "));
			String dbName = args[0].substring(args[0].indexOf(" ")+1);
			
			JFileChooser fileChooser = new JFileChooser();
			int returnValue = fileChooser.showOpenDialog(null);
			if(returnValue == 0){
				currentDir = fileChooser.getCurrentDirectory().toString();
				aSQLScriptFilePath = fileChooser.getSelectedFile().toString();
			}
			
			if(!aSQLScriptFilePath.equalsIgnoreCase("")) {
				// We need to provide file path as the parameter:
				// double backquote is to avoid compiler interpret words
				// like \test as \t (ie. as a escape sequence)
				File file = new File(aSQLScriptFilePath);

				BufferedReader br = new BufferedReader(new FileReader(file));

				dbvalidate.connectDatabaseForSQL(dburl);
				dbvalidate.truncateAllTablesForSQL();
				String st;
				int i = 1;
				
				f.setBounds(commonObj.screeWidth()/2 - 150, commonObj.screeHeight()/2, 90, 25);
			    f.setSize(400, 0);
			    f.setResizable(false);
			    f.setVisible(true);
			    f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			    
			    JOptionPane.showMessageDialog(null, "Before while");
			    
				while ((st = br.readLine()) != null)
				{
					if (st.startsWith("INSERT") && st.startsWith("INSERT INTO `config_data`")) {
						f.setTitle("Backup imported " + i);
						dbvalidate.updateRemarkResultMapForSQL(st);
						i++;
					}
				}
				f.setVisible(false);
				JOptionPane.showMessageDialog(null, "Backup data imported successfully..");
			}
		}
		catch(Exception e) {
			f.setVisible(false);
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
	}
}