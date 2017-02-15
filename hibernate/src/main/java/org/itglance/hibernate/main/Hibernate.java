package org.itglance.hibernate.main;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.itglance.hibernate.entity.Student;
import org.itglance.hibernate.entity.Subject;

public class Hibernate {

	SessionFactory sf = new Configuration().configure().buildSessionFactory();

	public void insert() {
		try {
			Session session = sf.openSession();
			session.beginTransaction();

			Student student1 = new Student();
			student1.setFname("Ram");
			student1.setLname("Prasad");

			Subject subjects1 = new Subject();
			subjects1.setSubjectName("Math");

			Subject subjects2 = new Subject();
			subjects2.setSubjectName("Science");

			student1.getSubjects().add(subjects1);
			student1.getSubjects().add(subjects2);

			session.save(student1);

			session.getTransaction().commit();
			session.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}

	public void update() {
		Session session = sf.openSession();
		session.beginTransaction();
		Student studentUpdate = session.get(Student.class, 1);

		System.out.println("student info to be updated");
		System.out.println(studentUpdate.toString());

		if (studentUpdate != null) {
			studentUpdate.setFname("Hari");
			studentUpdate.setLname("Sharma");

			for (Subject subjectsList : studentUpdate.getSubjects()) {
				System.out.println("subject name " + subjectsList.getSubjectName());
				// subjectsList.setSubjectName("new subject");
				// studentUpdate.getSubjects().add(subjectsList);
			}

		}
		session.getTransaction().commit();
		session.close();
	}

	public void delete() {
		Session session = sf.openSession();
		session.beginTransaction();
		Student studentDelete = session.get(Student.class, 1);
		System.out.println("student info to be deleted");
		System.out.println(studentDelete.toString());
		if (studentDelete != null) {
			session.delete(studentDelete);
		}
		session.getTransaction().commit();
		session.close();
	}

	public void display() {
		Session session = sf.openSession();
		session.beginTransaction();
		@SuppressWarnings("unchecked")
		List<Student> displayStudent = session.createQuery("from Student").getResultList();
		System.out.println("student info");
		System.out.println(displayStudent.toString());
		session.close();
	}
	
	public void displayfromSubject(){
		Session session = sf.openSession();
		session.beginTransaction();
		@SuppressWarnings("unchecked")
		List<Subject> displaySubject = session.createQuery("from Subject").getResultList();
		System.out.println("student info using subject");
		for (Subject subjectList : displaySubject){
			System.out.println(subjectList.getSubjectName());
			for (Student studentList : subjectList.getStudents()){
				System.out.println(studentList.getFname());
				System.out.println(studentList.getLname());
			}
		}
		session.close();
	}

	public static void main(String args[]) {

		Hibernate hibernate = new Hibernate();

		System.out.println("studnet insert");
		hibernate.insert();
		hibernate.display();
		
		hibernate.displayfromSubject();
		//hibernate.update();
		//hibernate.display();
		//hibernate.delete();
		//hibernate.display();
	}

}
