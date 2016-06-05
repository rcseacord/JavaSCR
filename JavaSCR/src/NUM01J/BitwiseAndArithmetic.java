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

package NUM01J;

public class BitwiseAndArithmetic {

	// The >>>= operator is a logical right shift; it fills the leftmost  
	// bits with zeroes, regardless of the number's original sign.
	static int divideLogicalRightShift(int x) {
		return x >>>= 2;
	}
	
	// Arithmetic right shift truncates the resulting value towards negative 
	// infinity, whereas integer division truncates toward zero.
	static int divideArithmeticRightShift(int x) {
		return x >>= 2;
	}
	
	static int divide(int x) {
		return x /= 4;
	}

	public static void main(String[] args) {
		System.out.println("Logical Right Shift: " + divideLogicalRightShift(-50));
		System.out.println("Arithmetic Right Shift: " + divideArithmeticRightShift(-50));
		System.out.println("Divide: " + divide(-50));
	}
}
