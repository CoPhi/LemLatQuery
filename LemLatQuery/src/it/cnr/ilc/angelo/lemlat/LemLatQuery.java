/**
 * 
 */
package it.cnr.ilc.angelo.lemlat;

import it.cnr.ilc.angelo.lemlat.consts.*;
import it.cnr.ilc.angelo.lemlat.query.LemLatBaseSearch;
import it.cnr.ilc.angelo.lemlat.query.QueryStringFormatter;

import java.io.BufferedReader;

/**
 * @author Angelo Del Grosso
 *
 */
public class LemLatQuery {

	/**
	 * 
	 */
	public LemLatQuery() {
		// TODO Auto-generated constructor stub
	}

	public BufferedReader perform(String wordForm) throws Exception{
		QueryStringFormatter formatter = new QueryStringFormatter(LemLatConsts.getHost());
		formatter.setBaseURL(LemLatConsts.getBaseurl());
		formatter.addQuery(LemLatConsts.getKeyquery(), wordForm);

		LemLatBaseSearch lemlatSearch = new LemLatBaseSearch(formatter);
		return lemlatSearch.queryPerform();
	}

	public static void main(String[] args) throws Exception {


		BufferedReader reader = new LemLatQuery().perform(args[0]);

		String line = null;
		while( (line = reader.readLine()) != null) {
			System.out.println("HTML_UNPARSED_LINE++>>>    " + line);
		}

	}

}
