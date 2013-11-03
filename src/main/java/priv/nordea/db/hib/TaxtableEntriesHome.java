package priv.nordea.db.hib;

// Generated Jun 1, 2013 9:24:39 AM by Hibernate Tools 3.4.0.CR1

import java.util.List;
import javax.naming.InitialContext;

import org.jboss.logging.Logger;
import org.hibernate.LockMode;
import org.hibernate.SessionFactory;
import static org.hibernate.criterion.Example.create;

/**
 * Home object for domain model class TaxtableEntries.
 * @see priv.nordea.db.hib.TaxtableEntries
 * @author Hibernate Tools
 */
public class TaxtableEntriesHome {

	private static final Logger log = Logger.getLogger(TaxtableEntriesHome.class);

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

	public void persist(TaxtableEntries transientInstance) {
		log.debug("persisting TaxtableEntries instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(TaxtableEntries instance) {
		log.debug("attaching dirty TaxtableEntries instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(TaxtableEntries instance) {
		log.debug("attaching clean TaxtableEntries instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(TaxtableEntries persistentInstance) {
		log.debug("deleting TaxtableEntries instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public TaxtableEntries merge(TaxtableEntries detachedInstance) {
		log.debug("merging TaxtableEntries instance");
		try {
			TaxtableEntries result = (TaxtableEntries) sessionFactory
					.getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public TaxtableEntries findById(int id) {
		log.debug("getting TaxtableEntries instance with id: " + id);
		try {
			TaxtableEntries instance = (TaxtableEntries) sessionFactory
					.getCurrentSession().get(
							"priv.nordea.db.hib.TaxtableEntries", id);
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

	public List<TaxtableEntries> findByExample(TaxtableEntries instance) {
		log.debug("finding TaxtableEntries instance by example");
		try {
			List<TaxtableEntries> results = (List<TaxtableEntries>) sessionFactory
					.getCurrentSession()
					.createCriteria("priv.nordea.db.hib.TaxtableEntries")
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
