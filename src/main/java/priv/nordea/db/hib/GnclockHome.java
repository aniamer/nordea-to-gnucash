package priv.nordea.db.hib;

// Generated Jun 1, 2013 9:24:39 AM by Hibernate Tools 3.4.0.CR1

import java.util.List;
import javax.naming.InitialContext;

import org.jboss.logging.Logger;
import org.hibernate.LockMode;
import org.hibernate.SessionFactory;
import static org.hibernate.criterion.Example.create;

/**
 * Home object for domain model class Gnclock.
 * @see priv.nordea.db.hib.Gnclock
 * @author Hibernate Tools
 */
public class GnclockHome {

	private static final Logger log = Logger.getLogger(GnclockHome.class);

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

	public void persist(Gnclock transientInstance) {
		log.debug("persisting Gnclock instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(Gnclock instance) {
		log.debug("attaching dirty Gnclock instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Gnclock instance) {
		log.debug("attaching clean Gnclock instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(Gnclock persistentInstance) {
		log.debug("deleting Gnclock instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Gnclock merge(Gnclock detachedInstance) {
		log.debug("merging Gnclock instance");
		try {
			Gnclock result = (Gnclock) sessionFactory.getCurrentSession()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public Gnclock findById(priv.nordea.db.hib.GnclockId id) {
		log.debug("getting Gnclock instance with id: " + id);
		try {
			Gnclock instance = (Gnclock) sessionFactory.getCurrentSession()
					.get("priv.nordea.db.hib.Gnclock", id);
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

	public List<Gnclock> findByExample(Gnclock instance) {
		log.debug("finding Gnclock instance by example");
		try {
			List<Gnclock> results = (List<Gnclock>) sessionFactory
					.getCurrentSession()
					.createCriteria("priv.nordea.db.hib.Gnclock")
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
