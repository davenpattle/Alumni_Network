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
@Table(name="userinstitutelist")
public class UserInstituteList implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	@ManyToOne
	@JoinColumn(name="userid")
	private UserProfile u_id;
	@ManyToOne
	@JoinColumn(name="instituteid")
	private InstituteList i_id;
	@Column(name="degree")
	private String degree;
	@Temporal(TemporalType.DATE)
	@Column(name="startdate")
	private Date start_date;
	@Temporal(TemporalType.DATE)
	@Column(name="enddate")
	private Date end_date;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public UserProfile getU_id() {
		return u_id;
	}
	public void setU_id(UserProfile u_id) {
		this.u_id = u_id;
	}
	public InstituteList getI_id() {
		return i_id;
	}
	public void setI_id(InstituteList i_id) {
		this.i_id = i_id;
	}
	public String getDegree() {
		return degree;
	}
	public void setDegree(String degree) {
		this.degree = degree;
	}
	public Date getStart_date() {
		return start_date;
	}
	public void setStart_date(Date start_date) {
		this.start_date = start_date;
	}
	public Date getEnd_date() {
		return end_date;
	}
	public void setEnd_date(Date end_date) {
		this.end_date = end_date;
	}		
}
