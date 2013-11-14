package priv.nordea.db.hib;

// Generated Jun 1, 2013 9:24:39 AM by Hibernate Tools 3.4.0.CR1

import java.util.List;

import javax.naming.InitialContext;

import org.jboss.logging.Logger;
import org.hibernate.LockMode;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import priv.nordea.db.hib.util.HibernateUtil;
import static org.hibernate.criterion.Example.create;

/**
 * Home object for domain model class Commodities.
 * @see priv.nordea.db.hib.Commodities
 * @author Hibernate Tools
 */
public class CommoditiesHome {

	private static final Logger log = Logger.getLogger(CommoditiesHome.class);

	private final SessionFactory sessionFactory = getSessionFactory();

	protected SessionFactory getSessionFactory() {
		try {
			return new HibernateUtil().getSessionFactory();
		} catch (Exception e) {
			log.error("Could not locate SessionFactory in JNDI", e);
			throw new IllegalStateException(
					"Could not locate SessionFactory in JNDI");
		}
	}

	public void persist(Commodities transientInstance) {
		log.debug("persisting Commodities instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(Commodities instance) {
		log.debug("attaching dirty Commodities instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Commodities instance) {
		log.debug("attaching clean Commodities instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(Commodities persistentInstance) {
		log.debug("deleting Commodities instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Commodities merge(Commodities detachedInstance) {
		log.debug("merging Commodities instance");
		try {
			Commodities result = (Commodities) sessionFactory
					.getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public Commodities findById(java.lang.String id) {
		log.debug("getting Commodities instance with id: " + id);
		try {
			Commodities instance = (Commodities) sessionFactory
					.getCurrentSession().get("priv.nordea.db.hib.Commodities",
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

	public List<Commodities> findByExample(Commodities instance) {
		log.debug("finding Commodities instance by example");
		try {
			Session session = sessionFactory.getCurrentSession();
			Transaction beginTransaction = session.beginTransaction();
			List<Commodities> results = (List<Commodities>) sessionFactory
					.getCurrentSession()
					.createCriteria("priv.nordea.db.hib.Commodities")
					.add(create(instance)).list();
			log.debug("find by example successful, result size: "
					+ results.size());
			beginTransaction.commit();
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}
}
