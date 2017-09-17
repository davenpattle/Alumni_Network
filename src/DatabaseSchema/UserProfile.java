package DatabaseSchema;

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
@Table(name="userprofile")
@Indexed
public class UserProfile {
	
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	@Column(name="fname")
	@Field(name="f_name",index=Index.YES,store=Store.YES,analyze = Analyze.YES,analyzer = @Analyzer(definition="AutoCompleteAnalyzer"))
	private String f_name;
	@Column(name="lname")
	@Field(name="l_name",index=Index.YES,store=Store.YES,analyze = Analyze.YES,analyzer = @Analyzer(definition="AutoCompleteAnalyzer"))
	private String l_name;
	@Column(name="email")
	private String email;
	@Column(name="location")
	@Field(name="location",index=Index.YES,store=Store.YES,analyze = Analyze.YES,analyzer = @Analyzer(definition="AutoCompleteAnalyzer"))
	private String location;
	@Column(name="contact")
	private String contact;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getF_name() {
		return f_name;
	}
	public void setF_name(String f_name) {
		this.f_name = f_name;
	}
	public String getL_name() {
		return l_name;
	}
	public void setL_name(String l_name) {
		this.l_name = l_name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getContact() {
		return contact;
	}
	public void setContact(String contact) {
		this.contact = contact;
	}
}
