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

package EXP04J;

import java.util.HashSet;

public class LongSet {

	public static void main(String[] args) {
		HashSet<Long> s = new HashSet<Long>();
		for (long i = 0; i < 100; i++) {
			s.add(i);
			s.remove(i - 1); // tries to remove an Integer
		}
		System.out.println(s.size());

		for (long i = 0; i < 100; i++) {
			s.add(i);
			s.remove((long)(i - 1)); // removes a Short
		}
		System.out.println(s.size());
		
		for (long i = 0; i < 100; i++) {
			s.add(i);
			s.remove((short)(i - 1)); // tries to remove an Integer
		}
		System.out.println(s.size());

	}

}
