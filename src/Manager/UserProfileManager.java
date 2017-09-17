package Manager;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import DatabaseSchema.UserCompanyList;
import DatabaseSchema.UserInstituteList;
import DatabaseSchema.UserProfile;
import HibernateUtils.HibernateUtility;

public class UserProfileManager implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static UserProfileManager instance = new UserProfileManager();	
	
	private UserProfileManager() {
		// Hidden Constructor
	}

	public static UserProfileManager getInstance() {
		return instance;
	}

	public UserProfile getUserByID(int id){
		Session session = HibernateUtility.getSessionFactory().openSession();
		Transaction tx = null;
		UserProfile user = null;
		try {
		   tx = session.beginTransaction();
		   
		   Query query = session.createQuery("FROM UserProfile as up WHERE up.id = :id");
		   query.setParameter("id",id);
		   user = (UserProfile) query.uniqueResult();
		   
		   return user;
		}
		catch (Exception e) {
		   if (tx!=null) tx.rollback();
		   e.printStackTrace(); 
		}finally {
		   session.close();
		}
		return user;
	}
	
	public void AddUser(UserProfile user){
		Session session = HibernateUtility.getSessionFactory().openSession();
		Transaction tx = null;
		try {
		   tx = session.beginTransaction();
		   session.save(user);
		   tx.commit();
		}
		catch (Exception e) {
		   if (tx!=null) tx.rollback();
		   e.printStackTrace(); 
		}finally {
		   session.close();
		}
	}
	
	public void UpdateUser(UserProfile user){
		Session session = HibernateUtility.getSessionFactory().openSession();
		Transaction tx = null;
		try {
		   tx = session.beginTransaction();
		   session.update(user);
		   tx.commit();
		}
		catch (Exception e) {
		   if (tx!=null) tx.rollback();
		   e.printStackTrace(); 
		}finally {
		   session.close();
		}
	}
	
	public List<UserProfile> GetUserAlumni(UserProfile user){
		
		// Get User Institutes
		List<UserInstituteList> institutes = UserInstituteListManager.getInstance().GetAllUserInstitutes(user.getId());
		
		// Get User Companies
		List<UserCompanyList> companies = UserCompanyListManager.getInstance().GetAllUserCompanies(user.getId());
		
		List<UserInstituteList> i_users = new ArrayList<>();
		
		for(int i=0;i<institutes.size();i++){
			i_users.addAll(UserInstituteListManager.getInstance().GetUsersByInstituteID(institutes.get(i).getI_id().getId()));
		}
		
		List<UserCompanyList> c_users = new ArrayList<>();
		
		for(int i=0;i<companies.size();i++){
			c_users.addAll(UserCompanyListManager.getInstance().GetUsersByCompanyID(companies.get(i).getC_id().getId()));
		}
		
		// All User First Deg Alumni
		Set<Integer> userIDs = new HashSet<>();
		
		for(int i=0;i<i_users.size();i++){
			userIDs.add(i_users.get(i).getU_id().getId());
		}
		for(int i=0;i<c_users.size();i++){
			userIDs.add(c_users.get(i).getU_id().getId());
		}
		
		// Getting First Deg Alumni Institutes
		List<Integer> list = new ArrayList(userIDs);
		
		Set<Integer> alumni_i = new HashSet<>();
		
		for(int i=0;i<list.size();i++){
			List<UserInstituteList> a_i = new ArrayList<>();
			
			a_i = UserInstituteListManager.getInstance().GetAllUserInstitutes(list.get(i));
			
			for(int j=0;j<a_i.size();j++){
				alumni_i.add(a_i.get(j).getI_id().getId());
			}
		}
		
		// Getting First Deg Alumni Companies
		Set<Integer> alumni_c = new HashSet<>();
		
		for(int i=0;i<list.size();i++){
			List<UserCompanyList> a_c = new ArrayList<>();
			
			a_c = UserCompanyListManager.getInstance().GetAllUserCompanies(list.get(i));
			
			for(int j=0;j<a_c.size();j++){
				alumni_c.add(a_c.get(j).getC_id().getId());
			}
		}
		
		List<Integer> a_i = new ArrayList(alumni_i);
		List<Integer> a_c = new ArrayList(alumni_c);
		
		// Getting Alumni's Alumni
		Set<Integer> i_alumni = new HashSet();
		
		for(int i=0;i<a_i.size();i++){
			List<UserInstituteList> i_a = UserInstituteListManager.getInstance().GetUsersByInstituteID(a_i.get(i));
			
			for(int j=0;j<i_a.size();j++){
				i_alumni.add(i_a.get(j).getU_id().getId());
			}
		}
		
		Set<Integer> c_alumni = new HashSet();
		
		for(int i=0;i<a_c.size();i++){
			List<UserCompanyList> c_a = UserCompanyListManager.getInstance().GetUsersByCompanyID(a_c.get(i));
			
			for(int j=0;j<c_a.size();j++){
				c_alumni.add(c_a.get(j).getU_id().getId());
			}
		}
		
		userIDs.addAll(i_alumni);
		userIDs.addAll(c_alumni);
		
		list = new ArrayList(userIDs);
		
		List<UserProfile> alumni = new ArrayList<>();
		
		for(int i=0;i<list.size();i++){
			alumni.add(UserProfileManager.getInstance().getUserByID(list.get(i)));
		}
		
		return alumni;
	} 
}
