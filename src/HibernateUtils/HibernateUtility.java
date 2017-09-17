package HibernateUtils;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
 
public class HibernateUtility 
{
    private HibernateUtility(){}
      
    private static SessionFactory sessionFactory = null;
     
    private static final SessionFactory makeSessionFactory()
    {
        try {
              if (sessionFactory==null)
            	  sessionFactory = new Configuration().configure().buildSessionFactory();
        } catch (HibernateException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
      return sessionFactory;    
    }
     
    public static synchronized final SessionFactory getSessionFactory()
    {
       return   makeSessionFactory();
    }
    
    public void InitializeTables(){
    	SessionFactory sessionFactory = getSessionFactory();
    }
}