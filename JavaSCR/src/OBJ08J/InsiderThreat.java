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

package OBJ08J;

import OBJ08J.Coordinates;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class InsiderThreat {

	public static void main(String[] args) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		
		Coordinates ic = new Coordinates();
		
	    System.out.println("(" + ic.getX() + "," + ic.getY() + ")");
		
		final Method methods[] = Coordinates.class.getDeclaredMethods();

		// Enumerate methods
		for (int i = 0; i < methods.length; ++i) {
			System.out.println("Method: " + methods[i]);
		}
		
	    Method m = Coordinates.class.getDeclaredMethod("access$1", Coordinates.class);
	    Integer x = (Integer) m.invoke(null, ic);	    
	    m = Coordinates.class.getDeclaredMethod("access$0", Coordinates.class);
	    Integer y = (Integer) m.invoke(null, ic);
	    System.out.println("(" + x + "," + y + ")");
	    
	    LambdaScopeTest lst = new LambdaScopeTest();
		final Method lmethods[] = LambdaScopeTest.class.getDeclaredMethods();

		// Enumerate methods
		for (int i = 0; i < lmethods.length; ++i) {
			System.out.println("Method: " + lmethods[i]);
		}
		
	    m = LambdaScopeTest.class.getDeclaredMethod("access$0", LambdaScopeTest.class);
	    x = (Integer) m.invoke(null, lst);	
	    System.out.println(x);   
	}
}