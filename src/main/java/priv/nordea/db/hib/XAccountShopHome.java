package priv.nordea.db.hib;

// Generated Jun 1, 2013 9:24:39 AM by Hibernate Tools 3.4.0.CR1

import static org.hibernate.criterion.Example.create;

import java.util.List;

import org.jboss.logging.Logger;
import org.hibernate.LockMode;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import priv.nordea.db.hib.util.HibernateUtil;

/**
 * Home object for domain model class Transactions.
 * @see priv.nordea.db.hib.Transactions
 * @author Hibernate Tools
 */
public class XAccountShopHome {

	private static final Logger log = Logger.getLogger(XAccountShopHome.class);

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

	public void persist(XAccountShop accountShopInstance) {
		log.debug("persisting XAccountShop instance");
		try {
			sessionFactory.getCurrentSession().persist(accountShopInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(XAccountShop instance) {
		log.debug("attaching dirty XAccountShop instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(XAccountShop instance) {
		log.debug("attaching clean XAccountShop instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(XAccountShop persistentInstance) {
		log.debug("deleting XAccountShop instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Transactions merge(XAccountShop detachedInstance) {
		log.debug("merging XAccountShop instance");
		try {
			Transactions result = (Transactions) sessionFactory
					.getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public XAccountShop findById(java.lang.String id) {
		log.debug("getting XAccountShop instance with id: " + id);
		try {
			XAccountShop instance = (XAccountShop) sessionFactory
					.getCurrentSession().get("priv.nordea.db.hib.XAccountShop",
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
	public List<XAccountShop> findByShopName(String shopName){
		log.debug("finding XAccountShop instance by account name");
		try{
			Session session = sessionFactory.getCurrentSession();
			session.beginTransaction();
			List<XAccountShop> result = (List<XAccountShop>)session.
					createCriteria(XAccountShop.class).
					add(Restrictions.like("shopName", shopName,MatchMode.ANYWHERE)).list();
			session.close();
			return result;
		}catch (RuntimeException re){
			log.error("find by account name failed", re);
			throw re;
		}
	}
	public List<XAccountShop> findByAccountName(String accountName){
		log.debug("finding XAccountShop instance by account name");
		try{
			Session session = sessionFactory.getCurrentSession();
			session.beginTransaction();
			List<XAccountShop> result = (List<XAccountShop>)session.
					createCriteria(XAccountShop.class).
					add(Restrictions.eq("accountName", accountName)).list();
			return result;
		}catch (RuntimeException re){
			log.error("find by account name failed", re);
			throw re;
		}
	}
	public List<XAccountShop> findByExample(XAccountShop instance) {
		log.debug("finding XAccountShop instance by account name");
		try {
			List<XAccountShop> results = (List<XAccountShop>) sessionFactory
					.getCurrentSession()
					.createCriteria("priv.nordea.db.hib.XAccountShop")
					.add(create(instance)).list();
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by account name failed", re);
			throw re;
		}
	}

}
