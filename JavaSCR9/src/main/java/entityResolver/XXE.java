// The MIT License (MIT)
// 
// Copyright (c) 2025 Robert C. Seacord
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

package entityResolver;

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
	private static void receiveXMLStreamBad(InputStream inStream, DefaultHandler defaultHandler)
			throws ParserConfigurationException, SAXException, IOException {
		SAXParserFactory factory = SAXParserFactory.newInstance();
		SAXParser saxParser = factory.newSAXParser();
		saxParser.parse(inStream, defaultHandler);
	}

	private static void receiveXMLStream(InputStream inStream, DefaultHandler defaultHandler)
			throws ParserConfigurationException, SAXException, IOException {
		SAXParserFactory factory = SAXParserFactory.newInstance();
		SAXParser saxParser = factory.newSAXParser();

		// Create an XML reader to set the entity resolver.
		XMLReader reader = saxParser.getXMLReader();
		reader.setEntityResolver(new CustomResolver());
		reader.setContentHandler(defaultHandler);

		InputSource is = new InputSource(inStream);
		reader.parse(is);
	}

	public static void main(String[] args) throws ParserConfigurationException {

		try (FileInputStream fis = new FileInputStream("JavaSCR9/src/main/java/entityResolver/evil.xml")) {
            // w/o custom resolver allows exploit
			receiveXMLStreamBad(fis, new DefaultHandler());
		} 
		catch (SAXException | IOException ex) {
			ex.printStackTrace();
			Throwable[] suppressed = ex.getSuppressed();
			for (Throwable aSuppressed : suppressed) {
				System.err.println("suppressed exception: " + aSuppressed.toString()); 
			}
		}

		try (FileInputStream fis = new FileInputStream("JavaSCR9/src/main/java/entityResolver/good.xml")) {
            // custom resolver allows valid use case
			receiveXMLStream(fis, new DefaultHandler());
		} catch (SAXException | IOException ex) {
			ex.printStackTrace();
			Throwable[] suppressed = ex.getSuppressed();
			for (Throwable aSuppressed : suppressed) {
				System.err.println("suppressed exception: " + aSuppressed.toString()); 
			}
		}
		
		try (FileInputStream fis = new FileInputStream("JavaSCR9/src/main/java/entityResolver/evil.xml")) {
            // custom resolver prevents exploit
			receiveXMLStream(fis, new DefaultHandler());
		} 
		catch (SAXException | IOException ex) {
			ex.printStackTrace();
			Throwable[] suppressed = ex.getSuppressed();
			for (Throwable aSuppressed : suppressed) {
				System.err.println("suppressed exception: " + aSuppressed.toString()); 
			}
		}
	}
}