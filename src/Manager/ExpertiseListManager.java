package Manager;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.search.FullTextSession;
import org.hibernate.search.Search;
import org.hibernate.search.query.dsl.QueryBuilder;

import DatabaseSchema.ExpertiseList;
import HibernateUtils.HibernateUtility;

public class ExpertiseListManager implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static ExpertiseListManager instance = new ExpertiseListManager();	
	
	private ExpertiseListManager() {
		// Hidden Constructor
	}

	public static ExpertiseListManager getInstance() {
		return instance;
	}

	public ExpertiseList getExpertiseByID(int id){
		Session session = HibernateUtility.getSessionFactory().openSession();
		Transaction tx = null;
		ExpertiseList expertise = null;
		try {
		   tx = session.beginTransaction();
		   
		   Query query = session.createQuery("FROM ExpertiseList as el WHERE el.e_id = :id");
		   query.setParameter("id",id);
		   expertise = (ExpertiseList) query.uniqueResult();
		   
		   return expertise;
		}
		catch (Exception e) {
		   if (tx!=null) tx.rollback();
		   e.printStackTrace(); 
		}finally {
		   session.close();
		}
		return expertise;
	}
	
	public void AddExpertise(ExpertiseList expertise){
		Session session = HibernateUtility.getSessionFactory().openSession();
		Transaction tx = null;
		try {
		   tx = session.beginTransaction();
		   session.save(expertise);
		   tx.commit();
		}
		catch (Exception e) {
		   if (tx!=null) tx.rollback();
		   e.printStackTrace(); 
		}finally {
		   session.close();
		}
	}
	
	public void UpdateExpertise(ExpertiseList expertise){
		Session session = HibernateUtility.getSessionFactory().openSession();
		Transaction tx = null;
		try {
		   tx = session.beginTransaction();
		   session.update(expertise);
		   tx.commit();
		}
		catch (Exception e) {
		   if (tx!=null) tx.rollback();
		   e.printStackTrace(); 
		}finally {
		   session.close();
		}
	}
	
	public List<ExpertiseList> GetExpertisesByKeyword(String keyword){
		Session session = HibernateUtility.getSessionFactory().openSession();
		Transaction tx = null;
		List<ExpertiseList> expertise = null;
		try {
		   tx = session.beginTransaction();
		   
		   FullTextSession fullTextSession = Search.getFullTextSession(session);
		   QueryBuilder queryBuilder = fullTextSession.getSearchFactory().buildQueryBuilder().forEntity(ExpertiseList.class).get();
		   
		   org.apache.lucene.search.Query luceneQuery = queryBuilder.phrase().onField("expertisename").sentence(keyword).createQuery();
		   
		   Query fullTextQuery = fullTextSession.createFullTextQuery(luceneQuery);
		   expertise = fullTextQuery.list();
		   
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
}
