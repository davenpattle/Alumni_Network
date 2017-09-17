package DatabaseSchema;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="jobapplicantlist")
public class JobApplicantList implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	@ManyToOne
	@JoinColumn(name="jobid")
	private JobList job_id;
	@ManyToOne
	@JoinColumn(name="userid")
	private UserProfile user_id;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public JobList getJob_id() {
		return job_id;
	}
	public void setJob_id(JobList job_id) {
		this.job_id = job_id;
	}
	public UserProfile getUser_id() {
		return user_id;
	}
	public void setUser_id(UserProfile user_id) {
		this.user_id = user_id;
	}

}
