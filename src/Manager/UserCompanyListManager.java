package Manager;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import DatabaseSchema.UserCompanyList;
import HibernateUtils.HibernateUtility;

public class UserCompanyListManager {
private static UserCompanyListManager instance = new UserCompanyListManager();	
	
	private UserCompanyListManager() {
		// Hidden Constructor
	}

	public static UserCompanyListManager getInstance() {
		return instance;
	}
	
	public void AddUserCompany(UserCompanyList ucl){
		Session session = HibernateUtility.getSessionFactory().openSession();
		Transaction tx = null;
		try {
		   tx = session.beginTransaction();
		   session.save(ucl);
		   tx.commit();
		}
		catch (Exception e) {
		   if (tx!=null) tx.rollback();
		   e.printStackTrace(); 
		}finally {
		   session.close();
		}
	}
	
	public void UpdateUserCompany(UserCompanyList ucl){
		Session session = HibernateUtility.getSessionFactory().openSession();
		Transaction tx = null;
		try {
		   tx = session.beginTransaction();
		   session.update(ucl);
		   tx.commit();
		}
		catch (Exception e) {
		   if (tx!=null) tx.rollback();
		   e.printStackTrace(); 
		}finally {
		   session.close();
		}
	}
	
	public List<UserCompanyList> GetAllUserCompanies(int userId){
		Session session = HibernateUtility.getSessionFactory().openSession();
		Transaction tx = null;
		List<UserCompanyList> company = null;
		try {
		   tx = session.beginTransaction();
		   
		   Query query = session.createQuery("FROM UserCompanyList as ucl WHERE ucl.u_id.id = :userid");
		   query.setParameter("userid", userId);
		   company = query.list();
		   
		   return company;
		}
		catch (Exception e) {
		   if (tx!=null) tx.rollback();
		   e.printStackTrace(); 
		}finally {
		   session.close();
		}
		return null;
	}
	
	public UserCompanyList GetUserCompanyByID(int userId,int companyId){
		Session session = HibernateUtility.getSessionFactory().openSession();
		Transaction tx = null;
		UserCompanyList company = null;
		try {
		   tx = session.beginTransaction();
		   
		   Query query = session.createQuery("FROM UserCompanyList as ucl WHERE ucl.u_id.id = :userid and ucl.c_id.c_id = :companyid");
		   query.setParameter("userid", userId);
		   query.setParameter("companyid", companyId);
		   company = (UserCompanyList) query.uniqueResult();
		   
		   return company;
		}
		catch (Exception e) {
		   if (tx!=null) tx.rollback();
		   e.printStackTrace(); 
		}finally {
		   session.close();
		}
		return null;
	}
	
	public void DeleteUserCompany(UserCompanyList ucl){
		Session session = HibernateUtility.getSessionFactory().openSession();
		Transaction tx = null;
		try {
		   tx = session.beginTransaction();
		   session.delete(ucl);
		   tx.commit();
		}
		catch (Exception e) {
		   if (tx!=null) tx.rollback();
		   e.printStackTrace(); 
		}finally {
		   session.close();
		}
	}
	
	public List<UserCompanyList> GetUsersByCompanyID(int companyId){
		Session session = HibernateUtility.getSessionFactory().openSession();
		Transaction tx = null;
		List<UserCompanyList> company = null;
		try {
		   tx = session.beginTransaction();
		   
		   Query query = session.createQuery("FROM UserCompanyList as ucl WHERE ucl.c_id.c_id = :companyid");
		   query.setParameter("companyid", companyId);
		   company = (List<UserCompanyList>) query.list();
		   
		   return company;
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
