// The MIT License (MIT)
// 
// Copyright (c) 2018 Robert C. Seacord
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

package ser03j;

import java.io.NotSerializableException;

public final class Singleton extends Number {
  private static final long serialVersionUID = 1L;
  private static final Singleton INSTANCE = new Singleton();

  public static Singleton getInstance() {
    return INSTANCE;
  }

  private Singleton() {
    // Perform security checks and parameter validation
  }

  private int balance = 1000;

  protected int getBalance() {
    return this.balance;
  }

  @Override
  public double doubleValue() {
    return 0;
  }

  @Override
  public float floatValue() {
    return 0;
  }

  @Override
  public int intValue() {
    return 0;
  }

  @Override
  public long longValue() {
    return 0;
  }

} // end class Singleton


























//  /**
//   * @param out not used, but required to match signature
//   */
//  @SuppressWarnings("static-method")
//  private void writeObject(java.io.ObjectOutputStream out) throws IOException {
//    throw new NotSerializableException("Not serializable");
//  }
//  
//  /**
//   * @param in not used, but required to match signature 
//   * @throws IOException to prevent deserialization
//   * @throws ClassNotFoundException not used, but required to match signature
//   */
//  @SuppressWarnings("static-method")
//  private void readObject(java.io.ObjectInputStream in) throws IOException, ClassNotFoundException {
//    throw new NotSerializableException("Not deserializable");
//  }
//  
//  @SuppressWarnings({ "unused", "static-method" })
//  private void readObjectNoData() throws ObjectStreamException {
//     throw new NotSerializableException("Not deserializable");
//  }

