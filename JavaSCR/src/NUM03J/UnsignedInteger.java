// The MIT License (MIT)
// 
// Copyright (c) 2015 Secure Coding Institute
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

package NUM03J;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class UnsignedInteger {

	private static DataOutputStream dos;

	public static int getIntegerWrong(DataInputStream dis) throws IOException {
		return dis.readInt();
	}

	public static long getInteger(DataInputStream dis) throws IOException {
		return dis.readInt() & 0xFFFFFFFFL; // mask with 32 one-bits
	}

	public static void main(String[] args) {
		try {
			// create output stream from file
			FileOutputStream os = new FileOutputStream("test.txt");		
	
			dos = new DataOutputStream(os);	
			
			// write out two identical "pretend" unsigned int values 
			dos.writeInt(0xFFFFFFFF); // 4294967295
			dos.writeInt(0xFFFFFFFF); // 4294967295
	
			// create input stream from file input stream
			FileInputStream is = new FileInputStream("test.txt");
			
			// create data input stream
			DataInputStream dis = new DataInputStream(is);

			int int_a = getIntegerWrong(dis);
			System.out.println("incorrect value = " + int_a);

			long long_a = getInteger(dis);
			System.out.println("correct value = " + long_a);
		} 
		catch (IOException e) {
			// if any I/O error occurs
			System.err.println("IOException " + e.getMessage());
		}
	}

}
