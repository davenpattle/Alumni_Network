package Manager;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.search.FullTextSession;
import org.hibernate.search.Search;
import org.hibernate.search.query.dsl.QueryBuilder;

import DatabaseSchema.InstituteList;
import HibernateUtils.HibernateUtility;

public class InstituteListManager implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static InstituteListManager instance = new InstituteListManager();	
	
	private InstituteListManager() {
		// Hidden Constructor
	}

	public static InstituteListManager getInstance() {
		return instance;
	}

	public InstituteList getInstituteByID(int id){
		Session session = HibernateUtility.getSessionFactory().openSession();
		Transaction tx = null;
		InstituteList institute = null;
		try {
		   tx = session.beginTransaction();
		   
		   Query query = session.createQuery("FROM InstituteList as il WHERE il.i_id = :id");
		   query.setParameter("id",id);
		   institute = (InstituteList) query.uniqueResult();
		   
		   return institute;
		}
		catch (Exception e) {
		   if (tx!=null) tx.rollback();
		   e.printStackTrace(); 
		}finally {
		   session.close();
		}
		return institute;
	}
	
	public void AddInstitute(InstituteList institute){
		Session session = HibernateUtility.getSessionFactory().openSession();
		Transaction tx = null;
		try {
		   tx = session.beginTransaction();
		   session.save(institute);
		   tx.commit();
		}
		catch (Exception e) {
		   if (tx!=null) tx.rollback();
		   e.printStackTrace(); 
		}finally {
		   session.close();
		}
	}
	
	public void UpdateInstitute(InstituteList institute){
		Session session = HibernateUtility.getSessionFactory().openSession();
		Transaction tx = null;
		try {
		   tx = session.beginTransaction();
		   session.update(institute);
		   tx.commit();
		}
		catch (Exception e) {
		   if (tx!=null) tx.rollback();
		   e.printStackTrace(); 
		}finally {
		   session.close();
		}
	}
	
	public List<InstituteList> GetInstitutesByKeyword(String keyword){
		Session session = HibernateUtility.getSessionFactory().openSession();
		Transaction tx = null;
		List<InstituteList> institute = null;
		try {
		   tx = session.beginTransaction();
		   
		   FullTextSession fullTextSession = Search.getFullTextSession(session);
		   QueryBuilder queryBuilder = fullTextSession.getSearchFactory().buildQueryBuilder().forEntity(InstituteList.class).get();
		   
		   org.apache.lucene.search.Query luceneQuery = queryBuilder.phrase().onField("institutename").andField("institutealias").sentence(keyword).createQuery();
		   
		   Query fullTextQuery = fullTextSession.createFullTextQuery(luceneQuery);
		   institute = fullTextQuery.list();
		   
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
