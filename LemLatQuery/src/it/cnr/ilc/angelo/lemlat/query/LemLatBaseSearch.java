/**
 * 
 */
package it.cnr.ilc.angelo.lemlat.query;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.StringReader;

import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;

/**
 * @author Angelo Del Grosso
 *
 */
public class LemLatBaseSearch {

	private QueryStringFormatter query = null;

	/**
	 * 
	 */
	public LemLatBaseSearch(QueryStringFormatter qStringFormatter) {
		// TODO Auto-generated constructor stub
		//System.err.println("in LemLatBaseSearch");
		this.query = qStringFormatter;
	}

	public BufferedReader queryPerform() throws IOException{
		//System.err.println("in queryPerform");
		BufferedReader reader =null;
		URL baseUrl = new URL(query.getQueryBase()+query.getQueryString());

		//System.err.println(baseUrl.toExternalForm());

		HttpURLConnection connection = (HttpURLConnection) baseUrl.openConnection();
		connection.setDoOutput(true);


		//PrintStream ps = new PrintStream(connection.getOutputStream());
		//ps.println(getQuery().getQueryString());
		//ps.println("");
		//Map<String, List<String>> header = connection.getHeaderFields();
		//ps.close();

		DataInputStream input = new DataInputStream(connection.getInputStream());

		reader = new BufferedReader(new InputStreamReader(input));  
		//reader =  new BufferedReader(new StringReader(new String(String.valueOf(connection.getResponseCode()))));
		return reader;
	}

	/**
	 * @return the query
	 */
	public QueryStringFormatter getQuery() {
		return query;
	}

	/**
	 * @param query the query to set
	 */
	public void setQuery(QueryStringFormatter query) {
		this.query = query;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception{

		QueryStringFormatter formatter = new QueryStringFormatter("http://www.ilc.cnr.it/lemlat/");
		formatter.setBaseURL("cgi-bin/LemLat_cgi.cgi/LemLat_cgi.cgi");
		formatter.addQuery("World+Form", "canis");

		LemLatBaseSearch lemlatSearch = new LemLatBaseSearch(formatter);
		BufferedReader reader = lemlatSearch.queryPerform();

		String line = null;
		while( (line = reader.readLine()) != null) {
			System.out.println(line);
		}

	}

}
