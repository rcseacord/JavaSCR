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

package ser09j;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public enum Singleton {
  INSTANCE;
  int value;

  static void serialize(Object o) throws IOException {
    try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("tempdata.ser"))) {
      oos.writeObject(o);
    }
  }

  public int getValue() {
    return this.value;
  }

  public void setValue(int value) {
    this.value = value;
  }

  public static void main(String[] args) throws IOException {
    Singleton.INSTANCE.setValue(42);
    System.out.println("Singleton.INSTANCE = " + Singleton.INSTANCE.getValue());
    serialize(Singleton.INSTANCE);
  }
}