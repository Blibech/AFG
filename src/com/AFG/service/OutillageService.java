package com.AFG.service;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.AFG.dao.HibernateUtil;
import com.AFG.dao.OutillageHome;
import com.AFG.persistance.Fournisseur;
import com.AFG.persistance.Outillage;

public class OutillageService {
	
private OutillageHome dao;
	
	
	public OutillageService()
	{dao=new OutillageHome();}
	
	private List<Outillage> elemine(List<Outillage> list){
		String code;
		String code2;
		for(int i=0;i<list.size();i++){
			code = list.get(i).getCode().substring(0, 11);
			for(int j=i+1;j<list.size();j++){
				code2 = list.get(j).getCode().substring(0, 11);
				if(code2.equals(code)){
					list.remove(j);
					j--;
				}
			}
		}
		return list;
	}
	
	public List<Outillage> rechercheTousOutillageLasy() {
		return elemine(rechercheTousOutillage());
	}
	
	public List<Outillage> rechercheTousOutillage() {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction tx = null;
		List<Outillage> outillage = null;
		try {
			tx = session.beginTransaction();

			outillage = (List<Outillage>) dao.findAll();
			tx.commit();
		} catch (RuntimeException ex) {
			if (tx != null)
				tx.rollback();
			ex.printStackTrace();
		}
		return outillage;
	}

	public List<Outillage> rechercheTousOutillageParCode(String code) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction tx = null;
		List<Outillage> outillage = null;
		try {
			tx = session.beginTransaction();

			outillage = (List<Outillage>) dao.findAllByCode(code);
			tx.commit();
		} catch (RuntimeException ex) {
			if (tx != null)
				tx.rollback();
			ex.printStackTrace();
		}
		return outillage;
	}
	
	public void ajouterOutillage(Outillage u) {
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

	public void suprimerOutillage(Outillage obj) {
		
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

	public void modifierOutillage(Outillage u) {
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
	
	public List<Outillage> rechercheOutillageParFournisseur(Fournisseur fournisseur) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction tx = null;
		List<Outillage> outillages = null;
		try {
			tx = session.beginTransaction();

			outillages = (List<Outillage>) dao.findByFournisseur(fournisseur);
			tx.commit();
		} catch (RuntimeException ex) {
			if (tx != null)
				tx.rollback();
			ex.printStackTrace();
		}
		return outillages;
	}
	
}
