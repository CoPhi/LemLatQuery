/**
 * 
 */
package it.cnr.ilc.angelo.lemlat.query;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * @author Angelo Del Grosso
 *
 */
public class QueryStringFormatter {

	private String baseURL = ""; 
	private String queryBase;
	private StringBuilder query = new StringBuilder();
	
	/**
	 * 
	 */
	public QueryStringFormatter(String qBase) {
		this.queryBase = qBase;
	}
	
	public void addQuery(String queryString, String queryValue) throws UnsupportedEncodingException  {
		query.append(queryString+"="+URLEncoder.encode(queryValue, "UTF-8")+"&");
	}
	
	public String getQueryString(){
		return getBaseURL()+"?"+getQuery().toString();
	}

	/**
	 * @return the queryBase
	 */
	public String getQueryBase() {
		return queryBase;
	}

	/**
	 * @param queryBase the queryBase to set
	 */
	public void setQueryBase(String queryBase) {
		this.queryBase = queryBase;
	}

	/**
	 * @return the query
	 */
	public StringBuilder getQuery() {
		return query;
	}

	/**
	 * @param query the query to set
	 */
	public void setQuery(StringBuilder query) {
		this.query = query;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		
		QueryStringFormatter qsf = new QueryStringFormatter("http://www.ilc.cnr.it");
		qsf.setBaseURL("/lemlat/cgi-bin/LemLat_cgi.cgi");
		qsf.addQuery("World+Form", "canis");
		System.out.println(qsf.getQueryBase() + qsf.getQueryString());
		

	}

	/**
	 * @return the baseURL
	 */
	public String getBaseURL() {
		return baseURL;
	}

	/**
	 * @param baseURL the baseURL to set
	 */
	public void setBaseURL(String baseURL) {
		this.baseURL = baseURL;
	}

	
}
