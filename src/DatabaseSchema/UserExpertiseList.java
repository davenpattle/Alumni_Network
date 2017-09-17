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
@Table(name="userexpertiselist")
public class UserExpertiseList implements Serializable{
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
	@JoinColumn(name="expertiseid")
	private ExpertiseList e_id;
	@Column(name="level")
	private String level;
	
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
	public ExpertiseList getE_id() {
		return e_id;
	}
	public void setE_id(ExpertiseList e_id) {
		this.e_id = e_id;
	}
	public String getLevel() {
		return level;
	}
	public void setLevel(String level) {
		this.level = level;
	}
}
