package SSRF;

import java.net.InetAddress;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.UnknownHostException;
import java.util.stream.IntStream;

class URI2IP {

  /** Check if the given IP address lies within the subnet given in CIDR notation.
   *
   * Supports IPv4 and IPv6.
   *
   * @param ipString the IP as string
   * @param cidrString the subnet in CIDR notation
   * @return true if the IP lies within the subnet, false otherwise
   */
  static boolean cidrMatch(String ipString, String cidrString) throws UnknownHostException {
    String[] parts = cidrString.split("/");

    byte[] ip = InetAddress.getByName(ipString).getAddress();
    byte[] subnet = InetAddress.getByName(parts[0]).getAddress();

    if (ip.length != subnet.length) {
      // can't compare IPv6 with IPv4 address
      return false;
    }

    if (parts.length < 2) {
      // can only do this now since there are multiple string representations of the same IP address
      return java.util.Arrays.equals(ip, subnet);
    } else {
      int bits;
      try {
        bits =  Integer.parseInt(parts[1]);
      } catch (NumberFormatException nfe) {
        throw new IllegalArgumentException("Invalid CIDR notation: " + cidrString);
      }
      if (bits < 0 || bits > ip.length * 8) {
        throw new IllegalArgumentException("Invalid CIDR notation: " + cidrString);
      }
      if (bits == 0) {
        return false;
      }

      if (IntStream.range(0, bits/8).anyMatch(i -> ip[i] != subnet[i])) {
        return false;
      }

      if (bits % 8 != 0) {
        // compare remaining bits
        int nextByte = bits/8;
        return ip[nextByte] >> (8 - bits % 8) == (subnet[nextByte] >> (8 - bits % 8));
      }
    }

    return true;
  }

  static String Uri2Ip(String uri) throws URISyntaxException, UnknownHostException {
    URI yuri = new URI(uri);
    InetAddress address = InetAddress.getByName(yuri.getHost());
    return address.getHostAddress();
  }

  public static void main(String[] args) throws URISyntaxException, UnknownHostException {
    System.out.println(URI2IP.Uri2Ip("https://www.nccgroup.com"));
    URI yuri = new URI("127.0.0.1");
    System.out.println(yuri);
    System.out.println(yuri.normalize());
    yuri = new URI("1");
    System.out.println(yuri);
    System.out.println(yuri.normalize());
    yuri = new URI("123.123.123");
    System.out.println(yuri);
    System.out.println(yuri.normalize());

    if (cidrMatch("127.0.0.1", "127.0.0.0/8")) {
      System.out.println("127.0.0.1 is in the range of 127.0.0.0/8");
    }
    else {
      System.out.println("127.0.0.1 is NOT in the range of 127.0.0.0/8");
    }
    if (cidrMatch("123.123.123", "127.0.0.0/8")) {
      System.out.println("123.123.123 is in the range of 127.0.0.0/8");
    }
    else {
      System.out.println("123.123.123 is NOT in the range of 127.0.0.0/8");
    }
    if (cidrMatch("169.254.0.0", "127.0.0.0/8")) {
      System.out.println("169.254.0.0 is in the range of 127.0.0.0/8");
    }
    else {
      System.out.println("169.254.0.0 is NOT in the range of 127.0.0.0/8");
    }
  }
}