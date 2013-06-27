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
 * Home object for domain model class Schedxactions.
 * @see priv.nordea.db.hib.Schedxactions
 * @author Hibernate Tools
 */
public class SchedxactionsHome {

	private static final Log log = LogFactory.getLog(SchedxactionsHome.class);

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

	public void persist(Schedxactions transientInstance) {
		log.debug("persisting Schedxactions instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(Schedxactions instance) {
		log.debug("attaching dirty Schedxactions instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Schedxactions instance) {
		log.debug("attaching clean Schedxactions instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(Schedxactions persistentInstance) {
		log.debug("deleting Schedxactions instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Schedxactions merge(Schedxactions detachedInstance) {
		log.debug("merging Schedxactions instance");
		try {
			Schedxactions result = (Schedxactions) sessionFactory
					.getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public Schedxactions findById(java.lang.String id) {
		log.debug("getting Schedxactions instance with id: " + id);
		try {
			Schedxactions instance = (Schedxactions) sessionFactory
					.getCurrentSession().get(
							"priv.nordea.db.hib.Schedxactions", id);
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

	public List<Schedxactions> findByExample(Schedxactions instance) {
		log.debug("finding Schedxactions instance by example");
		try {
			List<Schedxactions> results = (List<Schedxactions>) sessionFactory
					.getCurrentSession()
					.createCriteria("priv.nordea.db.hib.Schedxactions")
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
