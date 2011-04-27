package javaExtensions;

import java.util.Random;


public class SerialVersionUIDEx {
	public static String getSerialVersionUID() {
		return new Long(new Random().nextLong()).toString();
	}
}
