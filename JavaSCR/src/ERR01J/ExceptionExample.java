package ERR01J;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class ExceptionExample { 

	static FileInputStream fis;

	public static void main(String[] args) throws FileNotFoundException {
		setFis(new FileInputStream(System.getenv("APPDATA") + "\\" + args[0]));
	}

	public static FileInputStream getFis() {
		return fis;
	}

	public static void setFis(FileInputStream fis) {
		ExceptionExample.fis = fis;
	}

}
