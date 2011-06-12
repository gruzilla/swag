package swag;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

public abstract class Logger {

	public static void writeLog(String msg) {
		
		String filename = new String();

		filename = "..//logs//swag.log";
		
		try {
			PrintWriter out = new PrintWriter(new FileWriter(filename, true));
			out.println(new Date().toString()+": "+msg);
			out.close();
		} catch (IOException e){
			e.printStackTrace();
		}
	}
	
}
