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

package IDS16J;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.StringReader;

import javax.xml.XMLConstants;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;

class OnlineStore {

	private static void createXMLStreamBad(final BufferedOutputStream outStream, final String quantity)
			throws IOException {
		String xmlString = "<item>\n<description>Widget</description>\n" + "<price>500</price>\n" + "<quantity>" //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				+ quantity + "</quantity></item>"; //$NON-NLS-1$
		outStream.write(xmlString.getBytes());
		outStream.flush();
	}

	// Validate quantity before including in XML String
	private static void createXMLStream(final BufferedOutputStream outStream, final String quantity)
			throws IOException, NumberFormatException {
		// Write XML string only if quantity is an unsigned integer (count).
		int count = Integer.parseUnsignedInt(quantity);

		String xmlString = "<item>\n<description>Widget</description>\n" + "<price>500</price>\n" + "<quantity>" + count //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				+ "</quantity></item>"; //$NON-NLS-1$
		outStream.write(xmlString.getBytes());
		outStream.flush();
	}

	private static void createXMLStreamDTD(final BufferedOutputStream outStream, final String quantity)
			throws IOException {
		String xmlString;
		xmlString = "<order><item>\n<description>Widget</description>\n" + "<price>500.0</price>\n" + "<quantity>" + quantity //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				+ "</quantity></item></order>"; //$NON-NLS-1$
		InputSource xmlStream = new InputSource(new StringReader(xmlString));

		// Build a validating SAX parser using our schema
		SchemaFactory sf = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
		DefaultHandler defHandler = new DefaultHandler() {
			@Override
      public void warning(SAXParseException s) throws SAXParseException {
				throw s;
			}

			@Override
      public void error(SAXParseException s) throws SAXParseException {
				throw s;
			}

			@Override
      public void fatalError(SAXParseException s) throws SAXParseException {
				throw s;
			}
		};
		StreamSource ss = new StreamSource(new File("src/IDS16J/schema.xsd")); //$NON-NLS-1$
		try {
			Schema schema = sf.newSchema(ss);
			SAXParserFactory spf = SAXParserFactory.newInstance();
			spf.setSchema(schema);
			SAXParser saxParser = spf.newSAXParser();

			// Create an XML reader to set the entity resolver
			XMLReader reader = saxParser.getXMLReader();
			reader.setEntityResolver(new CustomResolver());
			saxParser.parse(xmlStream, defHandler);
		} catch (ParserConfigurationException x) {
			throw new IOException("Unable to validate XML", x); //$NON-NLS-1$
		} catch (SAXException x) {
			throw new IOException("Invalid quantity", x); //$NON-NLS-1$
		}

		// XML is valid, proceed
		outStream.write(xmlString.getBytes());
		outStream.flush();
	}

	public static void main(String[] args) {
		// Unvalidated input
		try (ByteArrayOutputStream baos = new ByteArrayOutputStream();
				BufferedOutputStream bos = new BufferedOutputStream(baos)) {
			createXMLStreamBad(bos, "1"); //$NON-NLS-1$
			createXMLStreamBad(bos, "1</quantity><price>1.0</price><quantity>1"); //$NON-NLS-1$
		} catch (Exception ex) {
			System.err.println("thrown exception: " + ex.toString()); //$NON-NLS-1$
			Throwable[] suppressed = ex.getSuppressed();
			for (Throwable aSuppressed : suppressed) {
				System.err.println("suppressed exception: " + aSuppressed.toString()); //$NON-NLS-1$
			}
		}

		// Schema validated
		try (ByteArrayOutputStream baos = new ByteArrayOutputStream();
				BufferedOutputStream bos = new BufferedOutputStream(baos)) {
			createXMLStreamDTD(bos, "1"); //$NON-NLS-1$
			createXMLStreamDTD(bos, "1</quantity><price>1.0</price><quantity>1"); //$NON-NLS-1$
		} catch (IOException ex) {
			System.err.println("thrown exception: " + ex.toString()); //$NON-NLS-1$
			Throwable[] suppressed = ex.getSuppressed();
			for (Throwable aSuppressed : suppressed) {
				System.err.println("suppressed exception: " + aSuppressed.toString()); //$NON-NLS-1$
			}
		} // end catch (IOException ex)

		// Schema validation
		try (ByteArrayOutputStream baos = new ByteArrayOutputStream();
				BufferedOutputStream bos = new BufferedOutputStream(baos)) {
			createXMLStreamDTD(bos,
					"0</quantity></item><item><description>Widget</description><price>0</price><quantity>100"); //$NON-NLS-1$
		} catch (IOException ex) {
			System.err.println("thrown exception: " + ex.toString()); //$NON-NLS-1$
			Throwable[] suppressed = ex.getSuppressed();
			for (Throwable aSuppressed : suppressed) {
				System.err.println("suppressed exception: " + aSuppressed.toString()); //$NON-NLS-1$
			}
		} // end catch (IOException ex)
		
		// Input validation
		try (ByteArrayOutputStream baos = new ByteArrayOutputStream();
				BufferedOutputStream bos = new BufferedOutputStream(baos)) {
			createXMLStream(bos, "1"); //$NON-NLS-1$
			createXMLStream(bos, "1</quantity><price>1.0</price><quantity>1"); //$NON-NLS-1$
		} catch (Exception ex) {
			System.err.println("thrown exception: " + ex.toString()); //$NON-NLS-1$
			Throwable[] suppressed = ex.getSuppressed();
			for (Throwable aSuppressed : suppressed) {
				System.err.println("suppressed exception: " + aSuppressed.toString()); //$NON-NLS-1$
			}
		} // end catch (IOException ex)	
		
		// Input validation
		try (ByteArrayOutputStream baos = new ByteArrayOutputStream();
				BufferedOutputStream bos = new BufferedOutputStream(baos)) {
			createXMLStream(bos, 
					"0</quantity></item><item><description>Widget</description><price>0</price><quantity>100"); //$NON-NLS-1$
		} catch (Exception ex) {
			System.err.println("thrown exception: " + ex.toString()); //$NON-NLS-1$
			Throwable[] suppressed = ex.getSuppressed();
			for (Throwable aSuppressed : suppressed) {
				System.err.println("suppressed exception: " + aSuppressed.toString()); //$NON-NLS-1$
			}
		} // end catch (IOException ex)	
		
	} // end main
} // end class
