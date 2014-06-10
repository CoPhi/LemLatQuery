/**
 * 
 */
package it.cnr.ilc.angelo.lemlat.parse;

import it.cnr.ilc.angelo.lemlat.entity.LemLatResult;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * @author Angelo Del Grosso
 *
 */
public class ParseReader {

	private Reader reader = null;
	private Document doc = null;

	private LemLatResult result = null;

	/**
	 * 
	 */
	public ParseReader(Reader reader) {
		// TODO Auto-generated constructor stub
		this.reader = reader;

	}

	private String parse() throws IOException{
		StringBuilder content = new StringBuilder();
		String line = null;
		if(this.reader.getClass().isInstance((BufferedReader) reader))
			while( (line = ((BufferedReader) reader).readLine()) != null) {
				//System.out.println("HTML_UNPARSED_LINE++>>>    " + line);
				content.append(line);
			}
		return content.toString();
	}

	public void HTMLparse() throws IOException{
		String html = parse();
		//System.err.println(html);
		this.doc = Jsoup.parse(html);
	}

	public void buildResult() throws NullPointerException{
		if(null==result)
			result = new LemLatResult();
		else 
			throw new NullPointerException("result object already extists!");
		result.setQueryForm(extractQueryForm());
		result.setCount(exractCount());
		result.setQueryLemmasMorphos(extractAnalysis());

	}



	private Integer exractCount() {
		//FIXME adding exception handling
		Integer count = null;
		int cnt = doc.select("table>tbody>tr>th[style=background-color: #999999;color: white]").size();
		count = new Integer(cnt);
		//System.err.println("exractCount: "+ count.intValue());
		return count;
	}

	private Map<String, List<String>> extractAnalysis() {
		//FIXME exception if doc does not exist
		Map<String, List<String>> analysis = null;
		String lemma = null;
		List<String> morpho = null;
		int count = getResult().getCount().intValue();
		Elements tableAnalysis = doc.select("table>tbody>tr>td>table");
		//System.err.println(tableAnalysis.size());
		if(count == tableAnalysis.size() ){
			analysis = new LinkedHashMap<String, List<String>>();
			Element TbodyAnalysis = null;
			while (count>0) {
				TbodyAnalysis = tableAnalysis.get(count -1);
				lemma = "_"+count+"-> " + extractLemma(TbodyAnalysis);
				morpho = extractMorpho(TbodyAnalysis);
				//System.err.println(TbodyAnalysis.html());
//				for (String morf : morpho) {
//					System.err.println("****"+morf+"****");
//				}
				analysis.put(lemma, morpho);
				count--;
			}
		}

		return analysis;
	}

	private List<String> extractMorpho(Element TbodyAnalysis) {
		//FIXME add exception handling
		List<String> morpho = null;
		morpho = new ArrayList<>();
		Elements liMorpho = null;
		liMorpho =	TbodyAnalysis.select("tbody>tr>td>ol>li:has(b)");
		//System.err.println(liMorpho.size());
		// NOTA: non tutta la morfologia è gestita con lo stesso albero DOM
		if (liMorpho.size() == 0){
			liMorpho = TbodyAnalysis.select("tbody>tr>td:has(b)");
		}
		for (Element morphoItem : liMorpho) {
			morpho.add(morphoItem.text());
		}
		return morpho;
	}

	private String extractLemma(Element TbodyAnalysis) {
		String lemma = null;
		Element lemmaElement = null;
		lemmaElement = TbodyAnalysis.select("tbody>tr>td>u").first();
		//FIXME non sempre il lema è unico
		if(null == lemmaElement)
			lemmaElement = TbodyAnalysis.select("tbody>tr>td>ol>li>u").first();
		
		lemma = lemmaElement.parent().text();
		//System.err.println("extractLemma: " +lemma);
		return lemma;
	}

	private String extractQueryForm() {
		String queryForm = null;
		//FIXME exception if doc does not exist
		Element tdForm = doc.select("table>tbody>tr>td[style=font-size: 200%;font-weight: bold]").first();
		queryForm = tdForm.text();
		return queryForm;
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

	/**
	 * @return the doc
	 */
	public Document getDoc() {
		return doc;
	}

	/**
	 * @param doc the doc to set
	 */
	public void setDoc(Document doc) {
		this.doc = doc;
	}


	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Reader reader = null;
		reader = new BufferedReader(new StringReader("<html><body><p>Ciao</p></body></html>"));
		ParseReader parser = new ParseReader(reader);

		try {
			//System.err.println(parser.parse());
			parser.HTMLparse();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			try {
				reader.close();
			} catch (IOException e2) {
				// TODO: handle exception
			}
		}

		System.out.println(parser.getDoc().html());

	}

	/**
	 * @return the result
	 */
	public LemLatResult getResult() {
		return result;
	}

	/**
	 * @param result the result to set
	 */
	public void setResult(LemLatResult result) {
		this.result = result;
	}

}
