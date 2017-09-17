package DatabaseSchema;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.lucene.analysis.core.LowerCaseTokenizerFactory;
import org.apache.lucene.analysis.miscellaneous.RemoveDuplicatesTokenFilterFactory;
import org.apache.lucene.analysis.ngram.EdgeNGramFilterFactory;
import org.hibernate.search.annotations.Analyze;
import org.hibernate.search.annotations.Analyzer;
import org.hibernate.search.annotations.AnalyzerDef;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Index;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.Parameter;
import org.hibernate.search.annotations.Store;
import org.hibernate.search.annotations.TokenFilterDef;
import org.hibernate.search.annotations.TokenizerDef;

@Entity
@Table(name="companylist")
@Indexed
@AnalyzerDef(name = "AutoCompleteAnalyzer",
tokenizer = @TokenizerDef(factory = LowerCaseTokenizerFactory.class),
filters = {@TokenFilterDef(factory = EdgeNGramFilterFactory.class,params = {@Parameter(name="minGramSize",value="2"),@Parameter(name="maxGramSize",value="1024")}),
@TokenFilterDef(factory = RemoveDuplicatesTokenFilterFactory.class)}
)
public class CompanyList implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int c_id;
	
	@Column(name="companyname")
	@Field(name="companyname",index=Index.YES,store=Store.YES,analyze = Analyze.YES,analyzer = @Analyzer(definition="AutoCompleteAnalyzer"))
	private String c_name;
	
	@Column(name="companyalias")
	@Field(name="companyalias",index=Index.YES,store=Store.YES,analyze = Analyze.YES,analyzer = @Analyzer(definition="AutoCompleteAnalyzer"))
	private String c_alias;

	public int getId() {
		return c_id;
	}

	public void setId(int c_id) {
		this.c_id = c_id;
	}

	public String getC_name() {
		return c_name;
	}

	public void setC_name(String c_name) {
		this.c_name = c_name;
	}

	public String getC_alias() {
		return c_alias;
	}

	public void setC_alias(String c_alias) {
		this.c_alias = c_alias;
	}
	
}
