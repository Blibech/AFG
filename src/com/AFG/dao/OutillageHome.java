package com.AFG.dao;
// Generated 29 avr. 2018 13:58:34 by Hibernate Tools 5.1.0.Alpha1

import java.util.List;
//import javax.naming.InitialContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.LockMode;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;

import com.AFG.persistance.Fournisseur;
import com.AFG.persistance.Outillage;

import static org.hibernate.criterion.Example.create;

/**
 * Home object for domain model class Outillage.
 * @see com.AFG.dao.Outillage
 * @author Hibernate Tools
 */
public class OutillageHome {

	private static final Log log = LogFactory.getLog(OutillageHome.class);

	private final SessionFactory sessionFactory = getSessionFactory();

	protected SessionFactory getSessionFactory() {
		try {
			//return (SessionFactory) new InitialContext().lookup("SessionFactory");
			return HibernateUtil.getSessionFactory();
		} catch (Exception e) {
			log.error("Could not locate SessionFactory in JNDI", e);
			throw new IllegalStateException("Could not locate SessionFactory in JNDI");
		}
	}

	public void persist(Outillage transientInstance) {
		log.debug("persisting Outillage instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(Outillage instance) {
		log.debug("attaching dirty Outillage instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Outillage instance) {
		log.debug("attaching clean Outillage instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(Outillage persistentInstance) {
		log.debug("deleting Outillage instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Outillage merge(Outillage detachedInstance) {
		log.debug("merging Outillage instance");
		try {
			Outillage result = (Outillage) sessionFactory.getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public Outillage findById(java.lang.Integer id) {
		log.debug("getting Outillage instance with id: " + id);
		try {
			Outillage instance = (Outillage) sessionFactory.getCurrentSession().get(Outillage.class, id);
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

	@SuppressWarnings("unchecked")
	public List<Outillage> findByExample(Outillage instance) {
		log.debug("finding Outillage instance by example");
		try {
			List<Outillage> results = (List<Outillage>) sessionFactory.getCurrentSession()
					.createCriteria(Outillage.class).add(create(instance)).list();
			log.debug("find by example successful, result size: " + results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<Outillage> findAll () {
		Criteria crit = sessionFactory.getCurrentSession().createCriteria(Outillage.class);
		return crit.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<Outillage> findAllByCode(String code) {
		code = code.substring(0, 11);
		List<Outillage> result = sessionFactory
				.getCurrentSession()
				.createCriteria(Outillage.class)
				.add(Restrictions.like("code",code+"%")).list();
		return result;                
	}
	
	@SuppressWarnings("unchecked")
	public List<Outillage> findByFournisseur(Fournisseur object) {
		List<Outillage> result = sessionFactory
				.getCurrentSession()
				.createCriteria(Outillage.class)
				.add(Restrictions.eq("fournisseur",object)).list();
		return result;                
	}
}
