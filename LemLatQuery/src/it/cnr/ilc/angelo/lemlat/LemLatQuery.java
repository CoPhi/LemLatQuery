/**
 * 
 */
package it.cnr.ilc.angelo.lemlat;

import it.cnr.ilc.angelo.lemlat.consts.*;
import it.cnr.ilc.angelo.lemlat.parse.ParseReader;
import it.cnr.ilc.angelo.lemlat.query.LemLatBaseSearch;
import it.cnr.ilc.angelo.lemlat.query.QueryStringFormatter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

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

	public BufferedReader perform(String wordForm) throws IOException, UnsupportedEncodingException {

		QueryStringFormatter formatter = new QueryStringFormatter(LemLatConsts.getHost());
		formatter.setBaseURL(LemLatConsts.getBaseurl());
		formatter.addQuery(LemLatConsts.getKeyquery(), wordForm);

		LemLatBaseSearch lemlatSearch = new LemLatBaseSearch(formatter);

		return lemlatSearch.queryPerform();
	}

	public static void main(String[] args) {

		BufferedReader reader = null;
		ParseReader parse = null;

		try {
			reader = new LemLatQuery().perform(args[0]);
			parse = new ParseReader(reader);
			parse.HTMLparse();
			//System.err.println(parse.getDoc().html());
			parse.buildResult();
			//System.err.println(parse.getResult().getQueryForm());
			System.err.println(parse.getResult().getCount().intValue());
			

		}catch (NullPointerException npe){
			npe.printStackTrace();

		} catch (UnsupportedEncodingException ue) {
			ue.printStackTrace();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		} finally{
			try {
				reader.close();
			} catch (Exception e2) {
				// TODO: handle exception
			}
		}


	}

}
