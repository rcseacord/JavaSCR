// The MIT License (MIT)
// 
// Copyright (c) 2017 Robert C. Seacord
// 
// Permission is hereby granted, free of charge, to any person obtaining a copy
// of this software and associated documentation files (the "Software"), to deal
// in the Software without restriction, including without limitation the rights
// to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
// copies of the Software, and to permit persons to whom the Software is
// furnished to do so, subject to the following conditions:
// 
// The above copyright notice and this permission notice shall be included in all
// copies or substantial portions of the Software.
// 
// THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
// IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
// FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
// AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
// LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
// OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
// SOFTWARE.

package err13j;

import java.io.IOException;

class DefaultUncaughtExceptionHandler {
  
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
