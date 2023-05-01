package org.com.maauli;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.Set;

public class ReadProperties {

	public static void main(String[] args) {
		InputStream input = null;
		try {
			Properties prop1 = new Properties();
			input = new FileInputStream("src/org/com/accesser/school.properties");

			// load a properties file
			prop1.load(input);

			Set<Object> keys = prop1.keySet();
			for (Object k : keys) {
				String key = (String) k;
				System.out.println(key + ": " + prop1.getProperty(key));
			}

			System.out.println(prop1.getProperty("STD_SINCE_SANSKARANJURCOLLEGE_SCI"));

		} catch (IOException io) {
			io.printStackTrace();
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

		}
	}
}
