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
 * Home object for domain model class Recurrences.
 * @see priv.nordea.db.hib.Recurrences
 * @author Hibernate Tools
 */
public class RecurrencesHome {

	private static final Log log = LogFactory.getLog(RecurrencesHome.class);

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

	public void persist(Recurrences transientInstance) {
		log.debug("persisting Recurrences instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(Recurrences instance) {
		log.debug("attaching dirty Recurrences instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Recurrences instance) {
		log.debug("attaching clean Recurrences instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(Recurrences persistentInstance) {
		log.debug("deleting Recurrences instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Recurrences merge(Recurrences detachedInstance) {
		log.debug("merging Recurrences instance");
		try {
			Recurrences result = (Recurrences) sessionFactory
					.getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public Recurrences findById(int id) {
		log.debug("getting Recurrences instance with id: " + id);
		try {
			Recurrences instance = (Recurrences) sessionFactory
					.getCurrentSession().get("priv.nordea.db.hib.Recurrences",
							id);
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

	public List<Recurrences> findByExample(Recurrences instance) {
		log.debug("finding Recurrences instance by example");
		try {
			List<Recurrences> results = (List<Recurrences>) sessionFactory
					.getCurrentSession()
					.createCriteria("priv.nordea.db.hib.Recurrences")
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
