package acc03j;

public class RestrictedPackages {

  public static void main(String[] args) {
    System.out.println(java.security.Security.getProperty("package.access"));
  }

}
