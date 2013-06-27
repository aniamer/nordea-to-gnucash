package priv.nordea.db.hib;

// Generated Jun 1, 2013 9:24:39 AM by Hibernate Tools 3.4.0.CR1

import java.util.List;
import javax.naming.InitialContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Hibernate;
import org.hibernate.LockMode;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import priv.nordea.db.hib.util.HibernateUtil;
import static org.hibernate.criterion.Example.create;

/**
 * Home object for domain model class Accounts.
 * @see priv.nordea.db.hib.Accounts
 * @author Hibernate Tools
 */
public class AccountsHome {

	private static final Log log = LogFactory.getLog(AccountsHome.class);

	private final SessionFactory sessionFactory = getSessionFactory();

	protected SessionFactory getSessionFactory() {
		try {
			return HibernateUtil.getSessionFactory();
		} catch (Exception e) {
			log.error("Could not locate SessionFactory in JNDI", e);
			throw new IllegalStateException(
					"Could not locate SessionFactory in JNDI");
		}
	}

	public void persist(Accounts transientInstance) {
		log.debug("persisting Accounts instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(Accounts instance) {
		log.debug("attaching dirty Accounts instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Accounts instance) {
		log.debug("attaching clean Accounts instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(Accounts persistentInstance) {
		log.debug("deleting Accounts instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Accounts merge(Accounts detachedInstance) {
		log.debug("merging Accounts instance");
		try {
			Accounts result = (Accounts) sessionFactory.getCurrentSession()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public Accounts findById(java.lang.String id) {
		log.debug("getting Accounts instance with id: " + id);
		try {
			Accounts instance = (Accounts) sessionFactory.getCurrentSession()
					.get("priv.nordea.db.hib.Accounts", id);
			if (instance == null) {
				log.debug("get successful, no instance found");
			} else {
				log.debug("get successful, instance found");
			}
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<Accounts> findByExample(Accounts instance) {
		log.debug("finding Accounts instance by example");
		try {
			Session session = sessionFactory.getCurrentSession();
			Transaction tx = session.beginTransaction();
			List<Accounts> results = (List<Accounts>)session.createCriteria("priv.nordea.db.hib.Accounts")
					.add(create(instance)).list();
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}
	
	public List<Accounts> findAll() {
		log.debug("finding Accounts instance by example");
		try {
			Session session = sessionFactory.getCurrentSession();
			Transaction tx = session.beginTransaction();
			List<Accounts> results = (List<Accounts>)session.createQuery("from " +Accounts.class.getName()).list();
			log.debug("find All successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}
}
