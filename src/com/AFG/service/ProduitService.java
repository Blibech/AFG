package com.AFG.service;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.AFG.dao.HibernateUtil;
import com.AFG.dao.ProduitHome;
import com.AFG.persistance.Produit;

public class ProduitService {
	
private ProduitHome dao;
	
	
	public ProduitService()
	{dao=new ProduitHome();}
	
	public List<Produit> rechercheTousProduit() {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction tx = null;
		List<Produit> produit = null;
		try {
			tx = session.beginTransaction();

			produit = (List<Produit>) dao.findAll();
			tx.commit();
		} catch (RuntimeException ex) {
			if (tx != null)
				tx.rollback();
			ex.printStackTrace();
		}
		return produit;
	}
	
	public List<Produit> rechercheParTout(String affectation, String designation, String client, String emplacement) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction tx = null;
		List<Produit> produit = null;
		try {
			tx = session.beginTransaction();

			produit = (List<Produit>) dao.findByAll(affectation,designation,client,emplacement);
			tx.commit();
		} catch (RuntimeException ex) {
			if (tx != null)
				tx.rollback();
			ex.printStackTrace();
		}
		return produit;
	}

	public void ajouterProduit(Produit u) {
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

	public void suprimerProduit(Produit objet) {
		
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction tx = null;

		try {
			// Ouverture d’une transaction
			tx = session.beginTransaction();
			dao.delete(objet);
			tx.commit();

		} catch (RuntimeException ex) {
			// Rollback de la transaction en cas d’erreurs
			if (tx != null)
				tx.rollback();

			ex.printStackTrace();
		}
	}

	public void modifierProduit(Produit objet) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction tx = null;

		try {
			// Ouverture d’une transaction
			tx = session.beginTransaction();
			dao.attachDirty(objet);
			tx.commit();

		} catch (RuntimeException ex) {
			// Rollback de la transaction en cas d’erreurs
			if (tx != null)
				tx.rollback();

			ex.printStackTrace();
		}
	}
	
	public Produit RechercheParAffectation(String affectation){
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction tx = null;
		Produit obj = null;
		try {
			tx = session.beginTransaction();
			obj=dao.findByAffectation(affectation);
			tx.commit();
		} catch (RuntimeException ex) {
			if (tx != null)
				tx.rollback();
			ex.printStackTrace();
		}
		return obj;

	}
	
}
