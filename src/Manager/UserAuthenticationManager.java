package Manager;

import javax.persistence.NoResultException;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import DatabaseSchema.UserAuthenticationList;
import HibernateUtils.HibernateUtility;

public class UserAuthenticationManager {
private static UserAuthenticationManager instance = new UserAuthenticationManager();	
	
	private UserAuthenticationManager() {
		// Hidden Constructor
	}

	public static UserAuthenticationManager getInstance() {
		return instance;
	}
	
	public UserAuthenticationList CheckUserNameAvailability(String user_name){
		Session session = HibernateUtility.getSessionFactory().openSession();
		Transaction tx = null;
		UserAuthenticationList user = null;
		try {
		   tx = session.beginTransaction();
		   
		   Query query = session.createQuery("FROM UserAuthenticationList as ual WHERE ual.user_name = :username");
		   query.setParameter("username", user_name);
		   user = (UserAuthenticationList) query.uniqueResult();
		   
		   return user;
		}
		catch(NoResultException e){
			return null;
		}
		catch (Exception e) {
		   if (tx!=null) tx.rollback();
		   e.printStackTrace(); 
		}finally {
		   session.close();
		}
		return null;
	}
	
	public UserAuthenticationList VerifyUser(String user_name,String password){
		Session session = HibernateUtility.getSessionFactory().openSession();
		Transaction tx = null;
		UserAuthenticationList user = null;
		try {
		   tx = session.beginTransaction();
		   
		   Query query = session.createQuery("FROM UserAuthenticationList as ual WHERE ual.user_name = :username and ual.password = :password");
		   query.setParameter("username", user_name);
		   query.setParameter("password", password);
		   user = (UserAuthenticationList) query.uniqueResult();
		   
		   return user;
		}
		catch(NoResultException e){
			return null;
		}
		catch (Exception e) {
		   if (tx!=null) tx.rollback();
		   e.printStackTrace(); 
		}finally {
		   session.close();
		}
		return null;
	}
	
	public void RegisterUser(UserAuthenticationList ual){
		Session session = HibernateUtility.getSessionFactory().openSession();
		Transaction tx = null;
		try {
		   tx = session.beginTransaction();
		   session.save(ual);
		   tx.commit();
		}
		catch (Exception e) {
		   if (tx!=null) tx.rollback();
		   e.printStackTrace(); 
		}finally {
		   session.close();
		}
	}
	
	public void UpdateUser(UserAuthenticationList ual){
		Session session = HibernateUtility.getSessionFactory().openSession();
		Transaction tx = null;
		try {
		   tx = session.beginTransaction();
		   session.update(ual);
		   tx.commit();
		}
		catch (Exception e) {
		   if (tx!=null) tx.rollback();
		   e.printStackTrace(); 
		}finally {
		   session.close();
		}
	}
	
	public UserAuthenticationList GetUser(String user_name){
		Session session = HibernateUtility.getSessionFactory().openSession();
		Transaction tx = null;
		UserAuthenticationList user = null;
		try {
		   tx = session.beginTransaction();
		   
		   Query query = session.createQuery("FROM UserAuthenticationList as ual WHERE ual.user_name = :username");
		   query.setParameter("username", user_name);
		   user = (UserAuthenticationList) query.uniqueResult();
		   
		   return user;
		}
		catch(NoResultException e){
			return null;
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
