package com.AFG.service;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.AFG.dao.HibernateUtil;
import com.AFG.dao.DemandeHome;
import com.AFG.persistance.Demande;

public class DemandeService {
	
private DemandeHome dao;
	
	
	public DemandeService()
	{dao=new DemandeHome();}
	
	public List<Demande> rechercheTousDemande() {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction tx = null;
		List<Demande> demande = null;
		try {
			tx = session.beginTransaction();

			demande = (List<Demande>) dao.findAll();
			tx.commit();
		} catch (RuntimeException ex) {
			if (tx != null)
				tx.rollback();
			ex.printStackTrace();
		}
		return demande;
	}

	public void ajouterDemande(Demande u) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();

			 dao.persist(u);
			tx.commit();
		} catch (RuntimeException ex) {
			if (tx != null)
				tx.rollback();
			ex.printStackTrace();
		}
	}

	public void suprimerDemande(int id) {
		
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction tx = null;

		try {Demande u=new Demande();
		u.setReference(id);
			// Ouverture d’une transaction
			tx = session.beginTransaction();
			dao.delete(u);
			tx.commit();

		} catch (RuntimeException ex) {
			// Rollback de la transaction en cas d’erreurs
			if (tx != null)
				tx.rollback();

			ex.printStackTrace();
		}
	}

	public void modifierDemande(Demande u) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction tx = null;

		try {
			// Ouverture d’une transaction
			tx = session.beginTransaction();
			dao.attachDirty(u);
			tx.commit();

		} catch (RuntimeException ex) {
			// Rollback de la transaction en cas d’erreurs
			if (tx != null)
				tx.rollback();

			ex.printStackTrace();
		}
	}
	
}
