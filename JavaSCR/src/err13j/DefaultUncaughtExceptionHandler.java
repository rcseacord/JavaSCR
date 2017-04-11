package err13j;

import java.io.Console;
import java.io.IOException;

public class DefaultUncaughtExceptionHandler {
  static Console format;

  public static void main(String... args) throws IOException {

    // Register a default uncaught exception handler for your program.
    Thread.setDefaultUncaughtExceptionHandler((Thread t, Throwable e) -> {
      System.err.println("Global exception handler");
      System.err.printf("Thread: %s\n", t.getId());
      System.err.printf("Exception: %s: %s\n", e.getClass().getName(), e.getMessage());
      System.err.println("Stack Trace: ");
      e.printStackTrace(System.err);
      System.err.printf("Thread status: %s\n", t.getState());
    });

    // Create a new thread and set an uncaught exception handler for it.
    Thread thread = new Thread(() -> {
      throw new RuntimeException("Task thread runtime exception.");
    });
    thread.setUncaughtExceptionHandler((Thread t, Throwable e) -> {
      System.err.println("Thread specific exception handler");
      System.err.printf("Thread: %s\n", t.getId());
      System.err.printf("Exception: %s: %s\n", e.getClass().getName(), e.getMessage());
      System.err.println("Stack Trace:");
      e.printStackTrace(System.err);
      System.err.printf("Thread status: %s\n", t.getState());
    });
    thread.start();

    // Wait for input before proceeding.
    System.out.println("Press Enter to continue.");
    @SuppressWarnings("unused")
    int read = System.in.read();

    // This RuntimeException should be caught by the default uncaught exception
    // handler.
    throw new IOException("Main thread runtime exception.");
  }
}
