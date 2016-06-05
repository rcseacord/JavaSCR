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

public class newInstance {
	  private static Throwable throwable;
	 
	  private newInstance() throws Throwable {
	    throw throwable;
	  }
	 
	  public static synchronized void undeclaredThrow(Throwable throwable) {
	    // These exceptions should not be passed
	    if (throwable instanceof IllegalAccessException ||
	        throwable instanceof InstantiationException) {
	      // Unchecked, no declaration required
	      throw new IllegalArgumentException();
	    }
	 
	    newInstance.throwable = throwable;
	    try {
	      // Next line throws the Throwable argument passed in above,
	      // even though the throws clause of class.newInstance fails
	      // to declare that this may happen
	      newInstance.class.newInstance();
	    } catch (InstantiationException e) { /* Unreachable */
	    } catch (IllegalAccessException e) { /* Unreachable */
	    } finally { // Avoid memory leak
	      newInstance.throwable = null;
	    }
	  }
	 
	  public static void main(String[] args) {  
	    // No declared checked exceptions
	    newInstance.undeclaredThrow(
	        new Exception("Any checked exception"));
	  }
	}
