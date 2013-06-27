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
 * Home object for domain model class SqliteSequence.
 * @see priv.nordea.db.hib.SqliteSequence
 * @author Hibernate Tools
 */
public class SqliteSequenceHome {

	private static final Log log = LogFactory.getLog(SqliteSequenceHome.class);

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

	public void persist(SqliteSequence transientInstance) {
		log.debug("persisting SqliteSequence instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(SqliteSequence instance) {
		log.debug("attaching dirty SqliteSequence instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(SqliteSequence instance) {
		log.debug("attaching clean SqliteSequence instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(SqliteSequence persistentInstance) {
		log.debug("deleting SqliteSequence instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public SqliteSequence merge(SqliteSequence detachedInstance) {
		log.debug("merging SqliteSequence instance");
		try {
			SqliteSequence result = (SqliteSequence) sessionFactory
					.getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public SqliteSequence findById(priv.nordea.db.hib.SqliteSequenceId id) {
		log.debug("getting SqliteSequence instance with id: " + id);
		try {
			SqliteSequence instance = (SqliteSequence) sessionFactory
					.getCurrentSession().get(
							"priv.nordea.db.hib.SqliteSequence", id);
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

	public List<SqliteSequence> findByExample(SqliteSequence instance) {
		log.debug("finding SqliteSequence instance by example");
		try {
			List<SqliteSequence> results = (List<SqliteSequence>) sessionFactory
					.getCurrentSession()
					.createCriteria("priv.nordea.db.hib.SqliteSequence")
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
