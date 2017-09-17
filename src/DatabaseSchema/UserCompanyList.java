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
@Table(name="usercompanylist")
public class UserCompanyList  implements Serializable{
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
	@JoinColumn(name="companyid")
	private CompanyList c_id;
	@Column(name="position")
	private String position;
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
	public CompanyList getC_id() {
		return c_id;
	}
	public void setC_id(CompanyList c_id) {
		this.c_id = c_id;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
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
