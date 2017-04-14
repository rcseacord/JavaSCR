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

package IDS08J;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.CharBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class LogSearch {

	private static void FindLogEntryBad(String search) {
		// Construct regex dynamically from user string
		String regex = "(.*? +public\\[\\d+\\] +.*" + search + ".*)";
		Pattern searchPattern = Pattern.compile(regex);
		try (FileInputStream fis = new FileInputStream("src/IDS08J/log.txt")) {
			FileChannel channel = fis.getChannel();
			// Get the file's size and map it into memory
			long size = channel.size();
			final MappedByteBuffer mappedBuffer = channel.map(FileChannel.MapMode.READ_ONLY, 0, size);

			Charset charset = Charset.forName("ISO-8859-15");
			final CharsetDecoder decoder = charset.newDecoder();
			// Read file into char buffer
			CharBuffer log = decoder.decode(mappedBuffer);
			Matcher logMatcher = searchPattern.matcher(log);
			while (logMatcher.find()) {
				String match = logMatcher.group();
				if (!match.isEmpty()) {
					System.out.println(match);
				}
			}
		} catch (IOException ex) {
			System.err.println("thrown exception: " + ex.toString());
			Throwable[] suppressed = ex.getSuppressed();
			for (Throwable aSuppressed : suppressed) {
				System.err.println("suppressed exception: " + aSuppressed.toString());
			}
		}
	}

	private static void FindLogEntryQuote(String search) {
		// Construct regex dynamically from user string
		String regex = "(.*? +public\\[\\d+\\] +.*" + Pattern.quote(search) + ".*)";
		Pattern searchPattern = Pattern.compile(regex);
		try (FileInputStream fis = new FileInputStream("src/IDS08J/log.txt")) {
			FileChannel channel = fis.getChannel();
			// Get the file's size and map it into memory
			long size = channel.size();
			final MappedByteBuffer mappedBuffer = channel.map(FileChannel.MapMode.READ_ONLY, 0, size);

			Charset charset = Charset.forName("ISO-8859-15");
			final CharsetDecoder decoder = charset.newDecoder();
			// Read file into char buffer
			CharBuffer log = decoder.decode(mappedBuffer);
			Matcher logMatcher = searchPattern.matcher(log);
			while (logMatcher.find()) {
				String match = logMatcher.group();
				if (!match.isEmpty()) {
					System.out.println(match);
				}
			}
		} catch (IOException ex) {
			System.err.println("thrown exception: " + ex.toString());
			Throwable[] suppressed = ex.getSuppressed();
			for (Throwable aSuppressed : suppressed) {
				System.err.println("suppressed exception: " + aSuppressed.toString());
			}
		}
	}

	public static void main(String[] args) {
		String arg = ".*)|(.*";
		System.out.println("FindLogEntryBad(arg)");
		FindLogEntryBad(arg); // matches everything
		System.out.println("++++++++++++++++++++++");

		System.out.println("FindLogEntryQuote(arg)");
		FindLogEntryQuote(arg); // matches nothing
		System.out.println("++++++++++++++++++++++");

		System.out.println("FindLogEntryQuote(\"\")");
		FindLogEntryQuote(""); // matches lines containing "public"
		System.out.println("++++++++++++++++++++++");
	}
}
