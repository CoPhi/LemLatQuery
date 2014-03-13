/**
 * 
 */
package it.cnr.ilc.angelo.lemlat.entity;

import java.util.List;
import java.util.Map;

/**
 * @author Angelo Del Grosso
 *
 */
public class LemLatResult {

	private String queryForm="";
	private Map<String, List<String>> queryLemmasMorphos=null; // ciascun lemma associato alla forma ha molteplici lettura morfologiche. Esempio: 
	private Integer count = null;
	/**
	 * 
	 */
	public LemLatResult() {
		// TODO Auto-generated constructor stub
	}

	
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}



	/**
	 * @return the queryForm
	 */
	public String getQueryForm() {
		return queryForm;
	}



	/**
	 * @param queryForm the queryForm to set
	 */
	public void setQueryForm(String queryForm) {
		this.queryForm = queryForm;
	}



	/**
	 * @return the queryLemmasMorphos
	 */
	public Map<String, List<String>> getQueryLemmasMorphos() {
		return queryLemmasMorphos;
	}



	/**
	 * @param queryLemmasMorphos the queryLemmasMorphos to set
	 */
	public void setQueryLemmasMorphos(Map<String, List<String>> queryLemmasMorphos) {
		this.queryLemmasMorphos = queryLemmasMorphos;
	}



	/**
	 * @return the count
	 */
	public Integer getCount() {
		return count;
	}



	/**
	 * @param count the count to set
	 */
	public void setCount(Integer count) {
		this.count = count;
	}

}
