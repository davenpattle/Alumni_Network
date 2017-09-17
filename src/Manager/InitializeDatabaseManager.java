package Manager;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.search.FullTextSession;
import org.hibernate.search.Search;

import HibernateUtils.HibernateUtility;

public class InitializeDatabaseManager {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static InitializeDatabaseManager instance = new InitializeDatabaseManager();	
	
	private InitializeDatabaseManager() {
		// Hidden Constructor
	}

	public static InitializeDatabaseManager getInstance() {
		return instance;
	}
	
	public void InitializeDatabase(){
		SessionFactory sessionFactory = HibernateUtility.getSessionFactory();	
	}
	
	public void InitializeIndex(){
		Session session = HibernateUtility.getSessionFactory().openSession();
		FullTextSession fullTextSession = Search.getFullTextSession(session);
		try {
			fullTextSession.createIndexer().startAndWait();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
