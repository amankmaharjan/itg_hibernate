package org.itglance.hibernate.main;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.itglance.hibernate.entity.Android;
import org.itglance.hibernate.entity.IPhone;
import org.itglance.hibernate.entity.Mobile;

public class Hibernate {

	SessionFactory sf = new Configuration().configure().buildSessionFactory();

	public void insert() {
		try {

			Session session = sf.openSession();
			session.beginTransaction();

			Android android = new Android();
			android.setModel("Celkon Q455");
			android.setOSName("Kitkat");

			IPhone iphone = new IPhone();
			iphone.setModel("IPhone 7");
			iphone.setOSName("idk");

			session.save(iphone);
			session.save(android);
			session.getTransaction().commit();
			session.close();

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}

	public void update() {
		Session session = sf.openSession();
		session.beginTransaction();

		Mobile mobileUpdate = session.get(Mobile.class, 1);
		System.out.println("mobile info to be updated");
		System.out.println(mobileUpdate.toString());

		if (mobileUpdate != null) {
			String className = mobileUpdate.getClass().getSimpleName();
			if (className.equalsIgnoreCase("Android")) {
				Android androidUpdate = session.get(Android.class, mobileUpdate.getMobileId());
				androidUpdate.setModel("Samsung J2");
				androidUpdate.setOSName("Jellybin");
				session.update(androidUpdate);
			} else {
				IPhone iphoneUpdate = session.get(IPhone.class, mobileUpdate.getMobileId());
				iphoneUpdate.setOSName("i dont know");
				iphoneUpdate.setModel("IPhone 6");
				session.update(iphoneUpdate);
			}
		}
		session.getTransaction().commit();
		session.close();
	}

	public void delete() {
		Session session = sf.openSession();
		session.beginTransaction();
		Mobile mobileDelete = session.get(Mobile.class, 1);
		System.out.println("mobile info to be deleted");
		System.out.println(mobileDelete.toString());
		if (mobileDelete != null) {
			session.delete(mobileDelete);
		}
		session.getTransaction().commit();
		session.close();
	}

	public void display() {
		Session session = sf.openSession();
		session.beginTransaction();
		@SuppressWarnings("unchecked")
		List<Mobile> mobileList = session.createQuery("from Mobile").getResultList();
		System.out.println("mobile info");
		for (Mobile mobile : mobileList) {
			System.out.println(mobile.toString());
		}
		session.close();
	}

	public static void main(String args[]) {

		Hibernate hibernate = new Hibernate();
		System.out.println("Mobile Insert");
		hibernate.insert();
		hibernate.display();
		System.out.println("Mobile Update");
		hibernate.update();
		hibernate.display();
		System.out.println("Mobile Delete");
		hibernate.delete();
		hibernate.display();
	}

}
