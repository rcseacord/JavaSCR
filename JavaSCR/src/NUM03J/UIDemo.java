package NUM03J;

class UIDemo
{
   public static void main(String[] args)
   {
      int x = Integer.MAX_VALUE;
      int y = Integer.MAX_VALUE+1;
      System.out.printf("%d %d%n", x, y); //$NON-NLS-1$
      System.out.printf("x compared to y: %d%n", Integer.compare(x, y)); //$NON-NLS-1$
      System.out.printf("x compared to y: %d%n", Integer.compareUnsigned(x, y)); //$NON-NLS-1$
      System.out.printf("y divided by x: %d%n", y/x); //$NON-NLS-1$
      System.out.printf("y divided by x: %d%n", Integer.divideUnsigned(y, x)); //$NON-NLS-1$
      System.out.printf("x+y: %s%n", Integer.toString(x+y)); //$NON-NLS-1$
      System.out.printf("x+y: %s%n", Integer.toUnsignedString(x+y)); //$NON-NLS-1$
      System.out.printf("parse(\"2147483647\"): %d%n", Integer.parseUnsignedInt("2147483647")); //$NON-NLS-1$ //$NON-NLS-2$
      System.out.printf("parse(\"2147483648\"): %d%n", Integer.parseUnsignedInt("2147483648")); //$NON-NLS-1$ //$NON-NLS-2$
      System.out.printf("parse(\"-2147483648\"): %d%n", Integer.parseUnsignedInt("-2147483648")); //$NON-NLS-1$ //$NON-NLS-2$
   }
}