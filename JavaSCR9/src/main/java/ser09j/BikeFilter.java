// The MIT License (MIT)
//
// Copyright (c) 2019 Robert C. Seacord
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

package ser09j;

import java.io.ObjectInputFilter;

class BikeFilter implements ObjectInputFilter {

  @Override
  public Status checkInput(FilterInfo filterInfo) {
    System.out.println("filterInfo.references = " + filterInfo.references()); 
    System.out.println("filterInfo.depth = " + filterInfo.depth()); 
    System.out.println("filterInfo.streamBytes = " + filterInfo.streamBytes());
    System.out.println("filterInfo.serialClass = " + filterInfo.serialClass().getCanonicalName());

    long maxStreamBytes = 78;   // Maximum allowed bytes in the stream.
    long maxDepth = 1;
    long maxReferences = 1;
    if (filterInfo.references() < 0 || filterInfo.depth() < 0 || filterInfo.streamBytes() < 0
        || filterInfo.references() > maxReferences || filterInfo.depth() > maxDepth
        || filterInfo.streamBytes() > maxStreamBytes) {
      return Status.REJECTED;
    }
    Class<?> clazz = filterInfo.serialClass();
    if (clazz != null) {
      if (Bicycle.class == filterInfo.serialClass()) {
        return Status.ALLOWED;
      }
      else {
        return Status.REJECTED;
      }      
    }
    return Status.UNDECIDED;
  } // end checkInput
} // end class BikeFilter