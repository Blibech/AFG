package com.AFG.service;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.AFG.dao.HibernateUtil;
import com.AFG.dao.UserHome;
import com.AFG.persistance.User;

public class UserService {
	
private UserHome dao;
	
	
	public UserService()
	{dao=new UserHome();}
	
	public List<User> rechercheTousUser() {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction tx = null;
		List<User> user = null;
		try {
			tx = session.beginTransaction();

			user = (List<User>) dao.findAll();
			tx.commit();
		} catch (RuntimeException ex) {
			if (tx != null)
				tx.rollback();
			ex.printStackTrace();
		}
		return user;
	}
	
	public User RechercheParLoginMP(String log, String mp){
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction tx = null;
		User user = null;
		try {
			tx = session.beginTransaction();
	
			user = (User) dao.findByLoginPassWord(log, mp);
			tx.commit();
		} catch (RuntimeException ex) {
			if (tx != null)
				tx.rollback();
			ex.printStackTrace();
		}
			return user;
	}

	public void ajouterUser(User u) {
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

	public User RechercheParLogin(String login) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction tx = null;
		User u=null;
		try {
			tx = session.beginTransaction();
			u=dao.findByLogin(login);
			tx.commit();
		} catch (RuntimeException ex) {
			if (tx != null)
				tx.rollback();
			ex.printStackTrace();
		}
		return u;
	}

	public void suprimerUser(User u) {
		
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction tx = null;

		try {
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

	public void modifierUser(User u) {
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
