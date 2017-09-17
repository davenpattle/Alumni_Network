package Manager;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import DatabaseSchema.UserInstituteList;
import HibernateUtils.HibernateUtility;

public class UserInstituteListManager {
private static UserInstituteListManager instance = new UserInstituteListManager();	
	
	private UserInstituteListManager() {
		// Hidden Constructor
	}

	public static UserInstituteListManager getInstance() {
		return instance;
	}
	
	public void AddUserInstitute(UserInstituteList uil){
		Session session = HibernateUtility.getSessionFactory().openSession();
		Transaction tx = null;
		try {
		   tx = session.beginTransaction();
		   session.save(uil);
		   tx.commit();
		}
		catch (Exception e) {
		   if (tx!=null) tx.rollback();
		   e.printStackTrace(); 
		}finally {
		   session.close();
		}
	}
	
	public void UpdateUserInstitute(UserInstituteList uil){
		Session session = HibernateUtility.getSessionFactory().openSession();
		Transaction tx = null;
		try {
		   tx = session.beginTransaction();
		   session.update(uil);
		   tx.commit();
		}
		catch (Exception e) {
		   if (tx!=null) tx.rollback();
		   e.printStackTrace(); 
		}finally {
		   session.close();
		}
	}
	

	public List<UserInstituteList> GetAllUserInstitutes(int userId){
		Session session = HibernateUtility.getSessionFactory().openSession();
		Transaction tx = null;
		List<UserInstituteList> institute = null;
		try {
		   tx = session.beginTransaction();
		   
		   Query query = session.createQuery("FROM UserInstituteList as uil WHERE uil.u_id.id = :userid");
		   query.setParameter("userid", userId);
		   institute =  query.list();
		   
		   return institute;
		}
		catch (Exception e) {
		   if (tx!=null) tx.rollback();
		   e.printStackTrace(); 
		}finally {
		   session.close();
		}
		return null;
	}
	
	public UserInstituteList GetUserInstituteByID(int userId,int instituteId){
		Session session = HibernateUtility.getSessionFactory().openSession();
		Transaction tx = null;
		UserInstituteList institute = null;
		try {
		   tx = session.beginTransaction();
		   
		   Query query = session.createQuery("FROM UserInstituteList as uil WHERE uil.u_id.id = :userid and uil.i_id.i_id = :instituteid");
		   query.setParameter("userid", userId);
		   query.setParameter("instituteid", instituteId);
		   institute = (UserInstituteList) query.uniqueResult();
		   
		   return institute;
		}
		catch (Exception e) {
		   if (tx!=null) tx.rollback();
		   e.printStackTrace(); 
		}finally {
		   session.close();
		}
		return null;
	}
	
	public void DeleteUserInstitute(UserInstituteList uil){
		Session session = HibernateUtility.getSessionFactory().openSession();
		Transaction tx = null;
		try {
		   tx = session.beginTransaction();
		   session.delete(uil);
		   tx.commit();
		}
		catch (Exception e) {
		   if (tx!=null) tx.rollback();
		   e.printStackTrace(); 
		}finally {
		   session.close();
		}
	}
	
	public List<UserInstituteList> GetUsersByInstituteID(int instituteId){
		Session session = HibernateUtility.getSessionFactory().openSession();
		Transaction tx = null;
		List<UserInstituteList> institute = null;
		try {
		   tx = session.beginTransaction();
		   
		   Query query = session.createQuery("FROM UserInstituteList as uil WHERE uil.i_id.i_id = :instituteid");
		   query.setParameter("instituteid", instituteId);
		   institute = (List<UserInstituteList>) query.list();
		   
		   return institute;
		}
		catch (Exception e) {
		   if (tx!=null) tx.rollback();
		   e.printStackTrace(); 
		}finally {
		   session.close();
		}
		return null;
	}
}
