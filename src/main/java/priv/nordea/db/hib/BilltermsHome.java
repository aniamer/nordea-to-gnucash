package priv.nordea.db.hib;

// Generated Jun 1, 2013 9:24:39 AM by Hibernate Tools 3.4.0.CR1

import java.util.List;
import javax.naming.InitialContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.SessionFactory;
import static org.hibernate.criterion.Example.create;

/**
 * Home object for domain model class Billterms.
 * @see priv.nordea.db.hib.Billterms
 * @author Hibernate Tools
 */
public class BilltermsHome {

	private static final Log log = LogFactory.getLog(BilltermsHome.class);

	private final SessionFactory sessionFactory = getSessionFactory();

	protected SessionFactory getSessionFactory() {
		try {
			return (SessionFactory) new InitialContext()
					.lookup("SessionFactory");
		} catch (Exception e) {
			log.error("Could not locate SessionFactory in JNDI", e);
			throw new IllegalStateException(
					"Could not locate SessionFactory in JNDI");
		}
	}

	public void persist(Billterms transientInstance) {
		log.debug("persisting Billterms instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(Billterms instance) {
		log.debug("attaching dirty Billterms instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Billterms instance) {
		log.debug("attaching clean Billterms instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(Billterms persistentInstance) {
		log.debug("deleting Billterms instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Billterms merge(Billterms detachedInstance) {
		log.debug("merging Billterms instance");
		try {
			Billterms result = (Billterms) sessionFactory.getCurrentSession()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public Billterms findById(java.lang.String id) {
		log.debug("getting Billterms instance with id: " + id);
		try {
			Billterms instance = (Billterms) sessionFactory.getCurrentSession()
					.get("priv.nordea.db.hib.Billterms", id);
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

	public List<Billterms> findByExample(Billterms instance) {
		log.debug("finding Billterms instance by example");
		try {
			List<Billterms> results = (List<Billterms>) sessionFactory
					.getCurrentSession()
					.createCriteria("priv.nordea.db.hib.Billterms")
					.add(create(instance)).list();
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}
}
