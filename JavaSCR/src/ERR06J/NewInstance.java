// The MIT License (MIT)
// 
// Copyright (c) 2016 Robert C. Seacord
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

package ERR06J;

import java.io.IOException;

public class NewInstance {
  private static Throwable throwable;

  public NewInstance() throws Throwable {
    throw throwable;
  }

  public static synchronized void undeclaredThrow(Throwable throwable) {
    // These exceptions should not be passed
    if (throwable instanceof IllegalAccessException || throwable instanceof InstantiationException) {
      // Unchecked, no declaration required
      throw new IllegalArgumentException();
    }

    NewInstance.throwable = throwable;
    try {
      // Next line throws the Throwable argument passed in above,
      // even though the throws clause of class.newInstance fails
      // to declare that this may happen
      NewInstance.class.newInstance();
     } 
    catch (InstantiationException | IllegalAccessException e) {
      // Unreachable 
    } 
    finally { // Avoid memory leak
      NewInstance.throwable = null;
    }
  }

  public static void main(String[] args) {
    // No declared checked exceptions
    try {
      NewInstance.undeclaredThrow(new Exception("Generic Exception"));
    } 
    catch (Exception e) {
      e.printStackTrace();
    }
    try {
      NewInstance.undeclaredThrow(new IOException("IOException"));
    } 
    catch (Exception e) {
      if (e instanceof IOException) {
        System.err.println("IOException occurred");
        e.printStackTrace();
      } 
      else {
        e.printStackTrace();
      }
    }
  } // end main
} // end class newInstance































































/*
//The MIT License (MIT)
//
//Copyright (c) 2016 Robert C. Seacord
//
//Permission is hereby granted, free of charge, to any person obtaining a copy
//of this software and associated documentation files (the "Software"), to deal
//in the Software without restriction, including without limitation the rights
//to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
//copies of the Software, and to permit persons to whom the Software is
//furnished to do so, subject to the following conditions:
//
//The above copyright notice and this permission notice shall be included in all
//copies or substantial portions of the Software.
//
//THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
//IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
//FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
//AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
//LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
//OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
//SOFTWARE.

package ERR06J;

import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class NewInstance {
private static Throwable throwable;

public NewInstance() throws Throwable {
 throw throwable;
}

public static synchronized void undeclaredThrow(Throwable throwable) throws InvocationTargetException {
 // These exceptions should not be passed
 if (throwable instanceof IllegalAccessException || throwable instanceof InstantiationException) {
   // Unchecked, no declaration required
   throw new IllegalArgumentException();
 }

 NewInstance.throwable = throwable;
 try {
      NewInstance.class.getConstructor().newInstance();
 } 
 catch (InstantiationException | IllegalAccessException e) {
   // Unreachable 
 } 
 catch (NoSuchMethodException | SecurityException e) {
    e.printStackTrace();
 } 
 finally { // Avoid memory leak
   NewInstance.throwable = null;
 }
}

public static void main(String[] args) {
 // No declared checked exceptions
 try {
   NewInstance.undeclaredThrow(new Exception("Generic Exception"));
 } 
 catch (Exception e) {
   e.printStackTrace();
 }
 try {
   NewInstance.undeclaredThrow(new IOException("IOException"));
 } 
 catch (Exception e) {
   if (e instanceof IOException) {
     System.err.println("IOException occurred");
     e.printStackTrace();
   } 
   else {
     e.printStackTrace();
   }
 }
} // end main
} // end class newInstance
*/