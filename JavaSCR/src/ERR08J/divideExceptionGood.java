package ERR08J;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import ERR01J.ExceptionReporter;

public class divideExceptionGood {
	public static int division(int totalSum, int totalNumber) throws IOException {
		int average = totalSum / totalNumber;
		// Additional operations that may throw IOException...
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("div.result")));
		out.write(average);
		out.close();
		return average;
	}

	public static void main(String[] args) {
		try {
			System.out.println(division(200, 5));
			System.out.println(division(200, 0)); // Divide by zero
		} catch (ArithmeticException | IOException ex) {
			ExceptionReporter er = new ExceptionReporter();
			er.reportException(ex);
		}
	}
}
