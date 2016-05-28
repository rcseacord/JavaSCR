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

package IDS17J;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;

class XXE {
  private static void receiveXMLStreamBad(InputStream inStream,
      DefaultHandler defaultHandler) throws ParserConfigurationException,
      SAXException, IOException {
    SAXParserFactory factory = SAXParserFactory.newInstance();
    SAXParser saxParser = factory.newSAXParser();
    saxParser.parse(inStream, defaultHandler);
  }
  
  private static void receiveXMLStream(InputStream inStream,
      DefaultHandler defaultHandler) throws ParserConfigurationException,
      SAXException, IOException {
    SAXParserFactory factory = SAXParserFactory.newInstance();
    SAXParser saxParser = factory.newSAXParser();

    // Create an XML reader to set the entity resolver.
    XMLReader reader = saxParser.getXMLReader();
    reader.setEntityResolver(new CustomResolver());
    reader.setContentHandler(defaultHandler);

    InputSource is = new InputSource(inStream);
    reader.parse(is);
  }


  public static void main(String[] args) throws ParserConfigurationException,
      SAXException, IOException {

    try (FileInputStream fis = new FileInputStream("src/IDS17J/evil.xml")) {
      receiveXMLStreamBad(fis, new DefaultHandler());
    } catch (IOException ex) {
      System.err.println("Malformed URL Exception: " + ex);
      Throwable[] suppressed = ex.getSuppressed();
      for (int i = 0; i < suppressed.length; i++) {
        System.err.println("suppressed exception: " + suppressed[i].toString());
      }
    }
    
    try (FileInputStream fis = new FileInputStream("src/IDS17J/evil.xml")) {
      receiveXMLStream(fis, new DefaultHandler());
    } catch (IOException ex) {
      System.err.println("Malformed URL Exception: " + ex);
      Throwable[] suppressed = ex.getSuppressed();
      for (int i = 0; i < suppressed.length; i++) {
        System.err.println("suppressed exception: " + suppressed[i].toString());
      }
    }
  }
}