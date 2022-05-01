// The MIT License (MIT)
// 
// Copyright (c) 2022 Robert C. Seacord
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

package equality;

// This example defines a CaseInsensitiveString class that
// includes a String and overrides the equals() method. The CaseInsensitiveString 
// class knows about ordinary strings, but the String class has no knowledge of 
// case-insensitive strings. Consequently, the CaseInsensitiveString.equals() method 
// should not attempt to interoperate with objects of the String class.

import java.util.Objects;
import com.google.common.testing.*;

final class caseInsensitiveString {
  private String s;

  private caseInsensitiveString(String s) {
    if (s == null) {
      throw new NullPointerException();
    }
    this.s = s;
  }

  // This method violates symmetry
  @Override
  public boolean equals(Object o) {
		if (o instanceof caseInsensitiveString) {
			return s.equalsIgnoreCase(((caseInsensitiveString)o).s);
		}
		return o instanceof String && s.equalsIgnoreCase((String)o);
	}

  @Override
  public int hashCode() {
    return Objects.hash(s);
  }

  public static void main(String[] args) {
    caseInsensitiveString cis = new caseInsensitiveString("Java");
    caseInsensitiveString cis_lc = new caseInsensitiveString("java");
    String s = "java";

    // By operating on String objects, the CaseInsensitiveString.equals() method
    // violates the second contract requirement (symmetry). Because of the asymmetry,
    // given a String object s and a CaseInsensitiveString object cis that differ
    // only in case, cis.equals(s)) returns true, while s.equals(cis) returns false.
    System.out.println(cis.equals(s)); // caseInsensitiveString.equals returns true
    System.out.println(s.equals(cis)); // String.equals returns false

    try {
      EqualsTester et = new EqualsTester()
          .addEqualityGroup(cis_lc, cis)
          .addEqualityGroup(new caseInsensitiveString("hello, world"), new caseInsensitiveString("Hello, World"))
          .addEqualityGroup(new caseInsensitiveString("CamelCase"), new caseInsensitiveString("camelcase"))
          .addEqualityGroup(new caseInsensitiveString("Fred"), new caseInsensitiveString("fred"))
          .testEquals();
    }
    catch (NoClassDefFoundError ncdfe) {
      ncdfe.printStackTrace();
     }
  }
} // end class caseInsensitiveString








































//  @Override
//  public boolean equals(Object o) {
//    if (this == o) return true;
//    if (o == null || getClass() != o.getClass()) return false;
//    caseInsensitiveString that = (caseInsensitiveString) o;
//    return s.equalsIgnoreCase(that.s);
//  }

//  @Override
//  public int hashCode() {
//    return Objects.hash(s.toLowerCase());
//  }