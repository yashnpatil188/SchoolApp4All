package org.com.accesser;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws IOException {
//		FileReader inputReader = new FileReader("org\\com\\accesser\\yash.txt");
		InputStream inputStream = Main.class.getResourceAsStream("/yash.txt");
		InputStreamReader inputReader = new InputStreamReader(inputStream);
		BufferedReader reader = new BufferedReader(inputReader);
		String line = null;
		while((line = reader.readLine()) != null){
			System.out.println(line);
		}
		reader.close();

	}

}
