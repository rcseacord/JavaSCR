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

package ERR03J;

class Dimensions {
  private int l, w, h;
  private static final int PAD = 2;
  static public final int MAX_DIM = 12;

  private Dimensions(int l, int w, int h) throws VolumeException {
    // Validate invariants
    if (l > MAX_DIM - PAD || w > MAX_DIM - PAD || h > MAX_DIM - PAD) {
      throw new VolumeException("Volume exceeded in ctor"); //$NON-NLS-1$
    }
    this.l = l;
    this.w = w;
    this.h = h;
  }

  protected int getVolumePackage(int weight) throws WeightException {
    this.l += PAD;
    this.w += PAD;
    this.h += PAD;
    if (weight <= 0 || weight > 20)
      throw new WeightException("Package overweight"); //$NON-NLS-1$
    int volume = this.l * this.w * this.h; // 12 * 12 * 12 = 1728
    this.l -= PAD;
    this.w -= PAD;
    this.h -= PAD; // Revert
    return volume;
  }

  public static void main(String[] args) throws VolumeException {
    Dimensions d = new Dimensions(10, 10, 10);

    try {
      System.out.println(d.getVolumePackage(21));
    } catch (WeightException e) {
      System.out.println(e.getMessage() + ": lighten re-weigh"); //$NON-NLS-1$
    }
    
    try {
      System.out.println(d.getVolumePackage(19)); // 2744 instead of 1728
    } catch (WeightException e) {
      System.out.println(e.getMessage() + ": lighten re-weigh"); //$NON-NLS-1$
    }
  } // end main
}  // end Class Dimensions





































/*
private int getVolumePackage(int weight) throws WeightException {
  if (weight <= 0 || weight > 20)
    throw new WeightException("Package overweight"); //$NON-NLS-1$
  return (this.l + PAD) * (this.w + PAD) * (this.h + PAD);
}
*/