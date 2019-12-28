// The MIT License (MIT)
//
// Copyright (c) 2020 Robert C. Seacord
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

package ssrf;

import java.net.*;
import java.util.stream.IntStream;

@SuppressWarnings("SpellCheckingInspection")
class URI2IP {

  /** Check if the given IP address lies within the subnet given in CIDR notation.
   *  Classless Inter-Domain Routing
   *  The notation is constructed from an IP address, a slash ('/') character, and a decimal number.
   *  The number is the count of leading 1 bits in the subnet mask.
   *  Larger values here indicate smaller networks.
   *
   * Supports IPv4 and IPv6.
   *
   * @param ipString the IP as string
   * @param cidrString the subnet in CIDR notation
   * @return true if the IP lies within the subnet, false otherwise
   */
  @SuppressWarnings("SameParameterValue")
  private static boolean cidrMatch(String ipString, String cidrString) throws UnknownHostException {
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

  @SuppressWarnings("SameParameterValue")
  private static String Uri2Ip(String uri) throws URISyntaxException, UnknownHostException {
    URI yuri = new URI(uri);
    InetAddress address = InetAddress.getByName(yuri.getHost());
    return address.getHostAddress();
  }

  public static void main(String[] args) throws URISyntaxException {
    // Equivalency issues
    System.out.println("Equivalency issues");

    // Relative URI references
    URI yuri1 = new URI("http://example.com/intro#chap1");
    URI yuri2 = new URI("intro#chap1");
    if (yuri1.normalize() == yuri2.normalize()) {
      System.out.println(yuri1.normalize() + " equals " + yuri2.normalize());
    }
    else {
      System.out.println(yuri1.normalize() + " does not equal " + yuri2.normalize());
    }

    // RFC2396-Sensitive Comparison. RFC2396 does not authorize the removal of the /./ and b/../ fragments
    // except in the case of relative URI references, but that this is arguably an inconsistency and that
    // software often does so anyhow.  %7A = URL encoded z.
    yuri1 = new URI("example://a/b/c/%7A");
    yuri2 = new URI("eXAMPLE://a/./b/../b/c/%7a");
    if (yuri1.normalize() == yuri2.normalize()) {
      System.out.println(yuri1.normalize() + " equals " + yuri2.normalize());
    }
    else {
      System.out.println(yuri1.normalize() + " does not equal " + yuri2.normalize());
    }

    // %-Escaping Issues
    // Software applying RFC2396's rules would not find these equivalent, since the %2f is being used explicitly to
    // escape the special semantics in URIs of the / character.
    yuri1 = new URI("http://a/b");
    yuri2 = new URI("http://a%2fb");
    if (yuri1.normalize() == yuri2.normalize()) {
      System.out.println(yuri1.normalize() + " equals " + yuri2.normalize());
    }
    else {
      System.out.println(yuri1.normalize() + " does not equal " + yuri2.normalize());
    }

    // Software applying RFC2396's rules might consider these equivalent, since %61 encodes the character a in both
    // ASCII and UTF-8, but context becomes significant. RFC2396 does not constrain the character-to-octet mapping
    // scheme used in URIs. If the second URI had been generated on a machine in which the EBCDIC character-to-octet
    // mapping was in use, the %61 would encode the character / (quite naturally, since / must be encoded but a need
    // never be).
    yuri1 = new URI("http://dir/a");
    yuri2 = new URI("http://dir/%61");
    if (yuri1.normalize() == yuri2.normalize()) {
      System.out.println(yuri1.normalize() + " equals " + yuri2.normalize());
    }
    else {
      System.out.println(yuri1.normalize() + " does not equal " + yuri2.normalize());
    }

    // Scheme-Sensitive Processing
    yuri1 = new URI("http://example.com/");
    yuri2 = new URI("http://example.com:80/");
    if (yuri1.normalize() == yuri2.normalize()) {
      System.out.println(yuri1.normalize() + " equals " + yuri2.normalize());
    }
    else {
      System.out.println(yuri1.normalize() + " does not equal " + yuri2.normalize());
    }

    System.exit(0);
  }
}








//    System.out.println(URI2IP.Uri2Ip("https://www.nccgroup.com"));
//
//    System.out.println(new URI("http://169.254.0.0/").getHost());
//    System.out.println(InetAddress.getByName("127.0.0.1").getHostAddress());
//    System.out.println(InetAddress.getByName("127.0.0").getHostAddress());
//    System.out.println(InetAddress.getByName("127.0").getHostAddress());
//    System.out.println(InetAddress.getByName("127").getHostAddress());
//
//    URI yuri = new URI("127.0.0.1");
//    System.out.println(yuri);
//    System.out.println(yuri.normalize());
//    yuri = new URI("1");
//    System.out.println(yuri);
//    System.out.println(yuri.normalize());
//    yuri = new URI("123.123.123");
//    System.out.println(yuri);
//    System.out.println(yuri.normalize());
//
//    if (cidrMatch("127.0.0.1", "127.0.0.0/8")) {
//      System.out.println("127.0.0.1 is in the range of 127.0.0.0/8");
//    }
//    else {
//      System.out.println("127.0.0.1 is NOT in the range of 127.0.0.0/8");
//    }
//    if (cidrMatch("123.123.123", "127.0.0.0/8")) {
//      System.out.println("123.123.123 is in the range of 127.0.0.0/8");
//    }
//    else {
//      System.out.println("123.123.123 is NOT in the range of 127.0.0.0/8");
//    }
//    if (cidrMatch("169.254.0.0", "127.0.0.0/8")) {
//      System.out.println("169.254.0.0 is in the range of 127.0.0.0/8");
//    }
//    else {
//      System.out.println("169.254.0.0 is NOT in the range of 127.0.0.0/8");
//    }
//
//    try {
//      URL earl = new URL("https://10.0.0.33 %0D%0AHELO nccgroup.com%0D%0AMAIL FROM…:25/");
//      System.out.println("earl Query is: " + earl.getQuery());
//      System.out.println("earl Path is: " + earl.getPath());
//      System.out.println("earl UserInfo is: " + earl.getUserInfo());
//      System.out.println("earl Authority is: " + earl.getAuthority());
//      System.out.println("earl Host is: " + earl.getHost());
//      System.out.println("earl Port is: " + earl.getPort());
//      System.out.println("earl Protocol is: " + earl.getProtocol());
//      System.out.println("earl File is: " + earl.getFile());
//      System.out.println("earl Ref is: " + earl.getRef());
//      earl.getContent();
//    } catch (IOException e) {
//      e.printStackTrace();
//    }
//  }
//}