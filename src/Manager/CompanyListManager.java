package Manager;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.search.FullTextSession;
import org.hibernate.search.Search;
import org.hibernate.search.query.dsl.QueryBuilder;

import DatabaseSchema.CompanyList;
import HibernateUtils.HibernateUtility;

public class CompanyListManager implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static CompanyListManager instance = new CompanyListManager();	
	
	private CompanyListManager() {
		// Hidden Constructor
	}

	public static CompanyListManager getInstance() {
		return instance;
	}

	public CompanyList getCompanyByID(int id){
		Session session = HibernateUtility.getSessionFactory().openSession();
		Transaction tx = null;
		CompanyList company = null;
		try {
		   tx = session.beginTransaction();
		   
		   Query query = session.createQuery("FROM CompanyList as cl WHERE cl.c_id = :id");
		   query.setParameter("id",id);
		   company = (CompanyList) query.uniqueResult();
		   
		   return company;
		}
		catch (Exception e) {
		   if (tx!=null) tx.rollback();
		   e.printStackTrace(); 
		}finally {
		   session.close();
		}
		return company;
	}
	
	public void AddCompany(CompanyList company){
		Session session = HibernateUtility.getSessionFactory().openSession();
		Transaction tx = null;
		try {
		   tx = session.beginTransaction();
		   session.save(company);
		   tx.commit();
		}
		catch (Exception e) {
		   if (tx!=null) tx.rollback();
		   e.printStackTrace(); 
		}finally {
		   session.close();
		}
	}
	
	public void UpdateCompany(CompanyList company){
		Session session = HibernateUtility.getSessionFactory().openSession();
		Transaction tx = null;
		try {
		   tx = session.beginTransaction();
		   session.update(company);
		   tx.commit();
		}
		catch (Exception e) {
		   if (tx!=null) tx.rollback();
		   e.printStackTrace(); 
		}finally {
		   session.close();
		}
	}
	
	public List<CompanyList> GetCompaniesByKeyword(String keyword){
		Session session = HibernateUtility.getSessionFactory().openSession();
		Transaction tx = null;
		List<CompanyList> company = null;
		try {
		   tx = session.beginTransaction();
		   
		   FullTextSession fullTextSession = Search.getFullTextSession(session);
		   QueryBuilder queryBuilder = fullTextSession.getSearchFactory().buildQueryBuilder().forEntity(CompanyList.class).get();
		   
		   org.apache.lucene.search.Query luceneQuery = queryBuilder.phrase().onField("companyname").andField("companyalias").sentence(keyword).createQuery();
		   
		   Query fullTextQuery = fullTextSession.createFullTextQuery(luceneQuery);
		   company = fullTextQuery.list();
		   
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
