/**
 * 
 */
package it.cnr.ilc.angelo.lemlat;

import it.cnr.ilc.angelo.lemlat.consts.*;
import it.cnr.ilc.angelo.lemlat.parse.ParseReader;
import it.cnr.ilc.angelo.lemlat.query.LemLatBaseSearch;
import it.cnr.ilc.angelo.lemlat.query.QueryStringFormatter;
import it.cnr.ilc.angelo.lemlat.utils.PrintHandler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * @author Angelo Del Grosso
 *
 */
public class LemLatQuery {

	//FIXME FIX FIX FIX
	private static Map<String, List<String>> analysis = null;
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

	public static StringBuilder analysisStringBuider(){
		return PrintHandler.StringBuiderAnalysis(analysis);
	}
	
	public static void main(String[] args) {

		BufferedReader reader = null;
		ParseReader parse = null;

		try {
			System.out.println(args[0]);
			reader = new LemLatQuery().perform(args[0]);
			parse = new ParseReader(reader);
			parse.HTMLparse();
			//System.err.println(parse.getDoc().html());
			parse.buildResult();
			//System.err.println(parse.getResult().getQueryForm());
			//System.err.println(parse.getResult().getCount().intValue());
			
			PrintHandler.printAnalysis(parse.getResult().getQueryLemmasMorphos());
			
			//FIXME solo a scopo di test.. Togliere lo static
			analysis = parse.getResult().getQueryLemmasMorphos();
			
			

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
