package com.AFG.service;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.AFG.dao.HibernateUtil;
import com.AFG.dao.FournisseurHome;
import com.AFG.persistance.Fournisseur;

public class FournisseurService {
	
private FournisseurHome dao;
	
	
	public FournisseurService()
	{dao=new FournisseurHome();}
	
	public List<Fournisseur> rechercheTousFournisseur() {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction tx = null;
		List<Fournisseur> fournisseur = null;
		try {
			tx = session.beginTransaction();

			fournisseur = (List<Fournisseur>) dao.findAll();
			tx.commit();
		} catch (RuntimeException ex) {
			if (tx != null)
				tx.rollback();
			ex.printStackTrace();
		}
		return fournisseur;
	}

	public void ajouterFournisseur(Fournisseur u) {
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

	public void suprimerFournisseur(Fournisseur obj) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction tx = null;

		try {
			// Ouverture d’une transaction
			tx = session.beginTransaction();
			dao.delete(obj);
			tx.commit();

		} catch (RuntimeException ex) {
			// Rollback de la transaction en cas d’erreurs
			if (tx != null)
				tx.rollback();

			ex.printStackTrace();
		}
	}

	public void modifierFournisseur(Fournisseur u) {
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

	public Fournisseur RechercheParNomPrenom(String nom, String prenom) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction tx = null;
		Fournisseur four = null;
		try {
			tx = session.beginTransaction();
	
			four = (Fournisseur) dao.findByNameLastname(nom, prenom);
			tx.commit();
		} catch (RuntimeException ex) {
			if (tx != null)
				tx.rollback();
			ex.printStackTrace();
		}
			return four;
	}
	
	public List<Fournisseur> RechercheParNom(String nom) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction tx = null;
		List<Fournisseur> fours = null;
		try {
			tx = session.beginTransaction();
	
			fours = (List<Fournisseur>) dao.findByName(nom);
			tx.commit();
		} catch (RuntimeException ex) {
			if (tx != null)
				tx.rollback();
			ex.printStackTrace();
		}
			return fours;
	}
	
}
