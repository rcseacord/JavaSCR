package ERR01J;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class ExceptionReporterExample {

	private static FileInputStream fis;

	public static void main(String[] args) throws FileNotFoundException {
		try {
			setFis(new FileInputStream(System.getenv("APPDATA") + "\\" + args[0]));
		} catch (IOException ioex) {
			ExceptionReporter er = new ExceptionReporter();
			er.reportException(ioex);
			// Recover from the exception...
		}

	}

	public static FileInputStream getFis() {
		return fis;
	}

	public static void setFis(FileInputStream fis) {
		ExceptionExample.fis = fis;
	}

}
