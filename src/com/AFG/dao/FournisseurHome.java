package com.AFG.dao;
// Generated 29 avr. 2018 13:58:34 by Hibernate Tools 5.1.0.Alpha1

import java.util.List;
//import javax.naming.InitialContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.LockMode;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;

import com.AFG.persistance.Fournisseur;

import static org.hibernate.criterion.Example.create;

/**
 * Home object for domain model class Fournisseur.
 * @see com.AFG.dao.Fournisseur
 * @author Hibernate Tools
 */
public class FournisseurHome {

	private static final Log log = LogFactory.getLog(FournisseurHome.class);

	private final SessionFactory sessionFactory = getSessionFactory();

	protected SessionFactory getSessionFactory() {
		try {
			return HibernateUtil.getSessionFactory();
			//return (SessionFactory) new InitialContext().lookup("SessionFactory");
		} catch (Exception e) {
			log.error("Could not locate SessionFactory in JNDI", e);
			throw new IllegalStateException("Could not locate SessionFactory in JNDI");
		}
	}

	public void persist(Fournisseur transientInstance) {
		log.debug("persisting Fournisseur instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(Fournisseur instance) {
		log.debug("attaching dirty Fournisseur instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Fournisseur instance) {
		log.debug("attaching clean Fournisseur instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(Fournisseur persistentInstance) {
		log.debug("deleting Fournisseur instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Fournisseur merge(Fournisseur detachedInstance) {
		log.debug("merging Fournisseur instance");
		try {
			Fournisseur result = (Fournisseur) sessionFactory.getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public Fournisseur findById(java.lang.Integer id) {
		log.debug("getting Fournisseur instance with id: " + id);
		try {
			Fournisseur instance = (Fournisseur) sessionFactory.getCurrentSession().get(Fournisseur.class, id);
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
	public List<Fournisseur> findByExample(Fournisseur instance) {
		log.debug("finding Fournisseur instance by example");
		try {
			List<Fournisseur> results = (List<Fournisseur>) sessionFactory.getCurrentSession()
					.createCriteria(Fournisseur.class).add(create(instance)).list();
			log.debug("find by example successful, result size: " + results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<Fournisseur> findAll () {
		Criteria crit = sessionFactory.getCurrentSession().createCriteria(Fournisseur.class);
		return crit.list();
	}
	
	public Fournisseur findByNameLastname(String nom, String prenom){
		Fournisseur result = (Fournisseur) sessionFactory
				.getCurrentSession()
				.createCriteria(Fournisseur.class)
				.add(Restrictions.eq("nom",nom))
				.add(Restrictions.eq("prenom",prenom))
				.uniqueResult();
			return result;
	}
	
	
	@SuppressWarnings("unchecked")
	public List<Fournisseur> findByName(String nom){
		List<Fournisseur> result = (List<Fournisseur>) sessionFactory
				.getCurrentSession()
				.createCriteria(Fournisseur.class)
				.add(Restrictions.like("nom",nom,MatchMode.ANYWHERE))
				.list();
			return result;
	}
}
