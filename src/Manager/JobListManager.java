package Manager;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import DatabaseSchema.JobApplicantList;
import DatabaseSchema.JobList;
import HibernateUtils.HibernateUtility;

public class JobListManager {
	private static final long serialVersionUID = 1L;
	private static JobListManager instance = new JobListManager();	
	
	private JobListManager() {
		// Hidden Constructor
	}

	public static JobListManager getInstance() {
		return instance;
	}
	
	public List<JobList> GetAllJobs(int c_id,int exp){
		Session session = HibernateUtility.getSessionFactory().openSession();
		Transaction tx = null;
		List<JobList> job = null;
		try {
		   tx = session.beginTransaction();
		   
		   Query query = session.createQuery("FROM JobList as jl where jl.c_id.id = :company and jl.minexp >= :exp");
		   query.setParameter("company", c_id);
		   query.setParameter("exp", exp);
		   job = query.list();
		   
		   return job;
		}
		catch (Exception e) {
		   if (tx!=null) tx.rollback();
		   e.printStackTrace(); 
		}finally {
		   session.close();
		}
		return null;
	}
	
	public JobList GetJobById(int j_id){
		Session session = HibernateUtility.getSessionFactory().openSession();
		Transaction tx = null;
		JobList job = null;
		try {
		   tx = session.beginTransaction();
		   
		   Query query = session.createQuery("FROM JobList as jl where jl.id = :job");
		   query.setParameter("job", j_id);
		   job = (JobList)query.uniqueResult();
		   
		   return job;
		}
		catch (Exception e) {
		   if (tx!=null) tx.rollback();
		   e.printStackTrace(); 
		}finally {
		   session.close();
		}
		return null;
	}
	
	public void ApplyJobForUser(JobApplicantList applicant){
		Session session = HibernateUtility.getSessionFactory().openSession();
		Transaction tx = null;
		try {		   
		   tx = session.beginTransaction();
		   session.save(applicant);
		   tx.commit();
		}
		catch (Exception e) {
		   if (tx!=null) tx.rollback();
		   e.printStackTrace(); 
		}finally {
		   session.close();
		}
	}
}
