package com.AFG.dao;
// Generated 29 avr. 2018 13:58:34 by Hibernate Tools 5.1.0.Alpha1

import java.util.List;
//import javax.naming.InitialContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.LockMode;
import org.hibernate.SessionFactory;

import com.AFG.persistance.Demande;

import static org.hibernate.criterion.Example.create;

/**
 * Home object for domain model class Demande.
 * @see com.AFG.dao.Demande
 * @author Hibernate Tools
 */
public class DemandeHome {

	private static final Log log = LogFactory.getLog(DemandeHome.class);

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

	public void persist(Demande transientInstance) {
		log.debug("persisting Demande instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(Demande instance) {
		log.debug("attaching dirty Demande instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Demande instance) {
		log.debug("attaching clean Demande instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(Demande persistentInstance) {
		log.debug("deleting Demande instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Demande merge(Demande detachedInstance) {
		log.debug("merging Demande instance");
		try {
			Demande result = (Demande) sessionFactory.getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public Demande findById(java.lang.Integer id) {
		log.debug("getting Demande instance with id: " + id);
		try {
			Demande instance = (Demande) sessionFactory.getCurrentSession().get(Demande.class, id);
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
	public List<Demande> findByExample(Demande instance) {
		log.debug("finding Demande instance by example");
		try {
			List<Demande> results = (List<Demande>) sessionFactory.getCurrentSession()
					.createCriteria(Demande.class).add(create(instance)).list();
			log.debug("find by example successful, result size: " + results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<Demande> findAll () {
		Criteria crit = sessionFactory.getCurrentSession().createCriteria(Demande.class);
		return crit.list();
	}
}
