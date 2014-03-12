/**
 * 
 */
package it.cnr.ilc.angelo.lemlat.parse;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;

/**
 * @author Angelo Del Grosso
 *
 */
public class ParseReader {

	Reader reader = null;

	/**
	 * 
	 */
	public ParseReader(Reader reader) {
		// TODO Auto-generated constructor stub
		this.reader = reader;
	}

	public String parse() throws IOException{
		StringBuilder content = new StringBuilder();
		String line = null;
		if(this.reader.getClass().isInstance((BufferedReader) reader))
			while( (line = ((BufferedReader) reader).readLine()) != null) {
				//System.out.println("HTML_UNPARSED_LINE++>>>    " + line);
				content.append(line);
			}
		return content.toString();
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		ParseReader parser = new ParseReader(new StringReader("Ciao"));
		parser.parse();

	}

	/**
	 * @return the reader
	 */
	public Reader getReader() {
		return reader;
	}

	/**
	 * @param reader the reader to set
	 */
	public void setReader(Reader reader) {
		this.reader = reader;
	}

}
