package Manager;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import DatabaseSchema.UserExpertiseList;
import HibernateUtils.HibernateUtility;

public class UserExpertiseListManager {
private static UserExpertiseListManager instance = new UserExpertiseListManager();	
	
	private UserExpertiseListManager() {
		// Hidden Constructor
	}

	public static UserExpertiseListManager getInstance() {
		return instance;
	}
	
	public void AddUserExpertise(UserExpertiseList uel){
		Session session = HibernateUtility.getSessionFactory().openSession();
		Transaction tx = null;
		try {
		   tx = session.beginTransaction();
		   session.save(uel);
		   tx.commit();
		}
		catch (Exception e) {
		   if (tx!=null) tx.rollback();
		   e.printStackTrace(); 
		}finally {
		   session.close();
		}
	}
	
	public void UpdateUserExpertise(UserExpertiseList uel){
		Session session = HibernateUtility.getSessionFactory().openSession();
		Transaction tx = null;
		try {
		   tx = session.beginTransaction();
		   session.update(uel);
		   tx.commit();
		}
		catch (Exception e) {
		   if (tx!=null) tx.rollback();
		   e.printStackTrace(); 
		}finally {
		   session.close();
		}
	}
	
	public List<UserExpertiseList> GetAllUserExpertises(int userId){
		Session session = HibernateUtility.getSessionFactory().openSession();
		Transaction tx = null;
		List<UserExpertiseList> expertise = null;
		try {
		   tx = session.beginTransaction();
		   
		   Query query = session.createQuery("FROM UserExpertiseList as uel WHERE uel.u_id.id = :userid");
		   query.setParameter("userid", userId);
		   expertise = query.list();
		   
		   return expertise;
		}
		catch (Exception e) {
		   if (tx!=null) tx.rollback();
		   e.printStackTrace(); 
		}finally {
		   session.close();
		}
		return null;
	}
	
	public UserExpertiseList GetUserExpertiseByID(int userId,int expertiseId){
		Session session = HibernateUtility.getSessionFactory().openSession();
		Transaction tx = null;
		UserExpertiseList expertise = null;
		try {
		   tx = session.beginTransaction();
		   
		   Query query = session.createQuery("FROM UserExpertiseList as uel WHERE uel.u_id.id = :userid and uel.e_id.c_id = :expertiseid");
		   query.setParameter("userid", userId);
		   query.setParameter("expertiseid", expertiseId);
		   expertise = (UserExpertiseList) query.uniqueResult();
		   
		   return expertise;
		}
		catch (Exception e) {
		   if (tx!=null) tx.rollback();
		   e.printStackTrace(); 
		}finally {
		   session.close();
		}
		return null;
	}
	
	public void DeleteUserExpertise(UserExpertiseList uel){
		Session session = HibernateUtility.getSessionFactory().openSession();
		Transaction tx = null;
		try {
		   tx = session.beginTransaction();
		   session.delete(uel);
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
