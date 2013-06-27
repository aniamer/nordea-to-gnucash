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
 * Home object for domain model class BudgetAmounts.
 * @see priv.nordea.db.hib.BudgetAmounts
 * @author Hibernate Tools
 */
public class BudgetAmountsHome {

	private static final Log log = LogFactory.getLog(BudgetAmountsHome.class);

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

	public void persist(BudgetAmounts transientInstance) {
		log.debug("persisting BudgetAmounts instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(BudgetAmounts instance) {
		log.debug("attaching dirty BudgetAmounts instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(BudgetAmounts instance) {
		log.debug("attaching clean BudgetAmounts instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(BudgetAmounts persistentInstance) {
		log.debug("deleting BudgetAmounts instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public BudgetAmounts merge(BudgetAmounts detachedInstance) {
		log.debug("merging BudgetAmounts instance");
		try {
			BudgetAmounts result = (BudgetAmounts) sessionFactory
					.getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public BudgetAmounts findById(int id) {
		log.debug("getting BudgetAmounts instance with id: " + id);
		try {
			BudgetAmounts instance = (BudgetAmounts) sessionFactory
					.getCurrentSession().get(
							"priv.nordea.db.hib.BudgetAmounts", id);
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

	public List<BudgetAmounts> findByExample(BudgetAmounts instance) {
		log.debug("finding BudgetAmounts instance by example");
		try {
			List<BudgetAmounts> results = (List<BudgetAmounts>) sessionFactory
					.getCurrentSession()
					.createCriteria("priv.nordea.db.hib.BudgetAmounts")
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
