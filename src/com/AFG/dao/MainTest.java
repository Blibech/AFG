package com.AFG.dao;

import org.hibernate.Session;
import com.AFG.persistance.User;

public class MainTest {
	public static void main(String[] args) {
		UserHome dao = new UserHome() ;
		Session session=HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		User u = dao.findById(10100);
		System.out.println("-User : "+u);
		session.getTransaction().commit();
		System.out.println("End **");
	}
}
