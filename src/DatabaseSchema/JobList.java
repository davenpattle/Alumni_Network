package DatabaseSchema;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="joblist")
public class JobList implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	@ManyToOne
	@JoinColumn(name="companyid")
	private CompanyList c_id;
	@Column(name="type")
	private int type;
	@Column(name="description")
	private String description;
	@Column(name="postdate")
	@Temporal(TemporalType.DATE)
	private Date post_date;
	@Column(name="minexp")
	private int minexp;
	@Column(name="maxexp")
	private int maxexp;
	@Column(name="requirements")
	private String requirements;
	@Column(name="location")
	private String location;
	@ManyToOne
	@JoinColumn(name="categoryid")
	private JobCategoryList category;
	@Column(name="status")
	private int status;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public CompanyList getC_id() {
		return c_id;
	}
	public void setC_id(CompanyList c_id) {
		this.c_id = c_id;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Date getPost_date() {
		return post_date;
	}
	public void setPost_date(Date post_date) {
		this.post_date = post_date;
	}
	public int getMinexp() {
		return minexp;
	}
	public void setMinexp(int minexp) {
		this.minexp = minexp;
	}
	public int getMaxexp() {
		return maxexp;
	}
	public void setMaxexp(int maxexp) {
		this.maxexp = maxexp;
	}
	public String getRequirements() {
		return requirements;
	}
	public void setRequirements(String requirements) {
		this.requirements = requirements;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public JobCategoryList getCategory() {
		return category;
	}
	public void setCategory(JobCategoryList category) {
		this.category = category;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	
	
}
