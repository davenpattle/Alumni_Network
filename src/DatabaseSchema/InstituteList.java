package DatabaseSchema;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.search.annotations.Analyze;
import org.hibernate.search.annotations.Analyzer;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Index;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.Store;

@Entity
@Table(name="institutelist")
@Indexed
public class InstituteList implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int i_id;
	
	@Column(name="institutename")
	@Field(name="institutename",index=Index.YES,store=Store.YES,analyze = Analyze.YES,analyzer = @Analyzer(definition="AutoCompleteAnalyzer"))
	private String i_name;
	
	@Column(name="institutealias")
	@Field(name="institutename",index=Index.YES,store=Store.YES,analyze = Analyze.YES,analyzer = @Analyzer(definition="AutoCompleteAnalyzer"))
	private String i_alias;

	public int getId() {
		return i_id;
	}

	public void setId(int i_id) {
		this.i_id = i_id;
	}

	public String getI_name() {
		return i_name;
	}

	public void setI_name(String i_name) {
		this.i_name = i_name;
	}

	public String getI_alias() {
		return i_alias;
	}

	public void setI_alias(String c_alias) {
		this.i_alias = c_alias;
	}
	
}
