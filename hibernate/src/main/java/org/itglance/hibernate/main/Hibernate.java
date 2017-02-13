package org.itglance.hibernate.main;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.itglance.hibernate.entity.Department;
import org.itglance.hibernate.entity.Employee;

public class Hibernate {

	SessionFactory sf = new Configuration().configure().buildSessionFactory();

	public void insert() {
		Session session = sf.openSession();
		session.beginTransaction();
		Employee employee1 = new Employee();
		Employee employee2 = new Employee();

		employee1.setFname("Hari");
		employee1.setLname("Prasad");

		employee2.setFname("Ram");
		employee2.setLname("Sharma");

		Department department1 = new Department();

		department1.setDepartmentName("Human Resource");
		employee1.setDepartment(department1);
		employee2.setDepartment(department1);
		department1.getEmployeeList().add(employee1);
		department1.getEmployeeList().add(employee2);

		session.save(department1);

		session.getTransaction().commit();
		session.close();
	}

	public void update() {
		Session session = sf.openSession();
		session.beginTransaction();
		Employee employeeUpdate = session.get(Employee.class, 1);
		
		
		System.out.println("employee update info");
		System.out.println(employeeUpdate.toString());

		if (employeeUpdate != null) {
			employeeUpdate.setFname("Ram");
			employeeUpdate.setLname("Krisha");
			employeeUpdate.getDepartment().setDepartmentName("Marketing");
			session.update(employeeUpdate);

		}
		
		session.getTransaction().commit();
		session.close();
	}

	public void delete() {
		Session session = sf.openSession();
		session.beginTransaction();
		Employee employeetDelete = session.get(Employee.class, 1);
		System.out.println("employee info to be deleted");		
		System.out.println(employeetDelete.toString());
		if (employeetDelete != null) {
			session.delete(employeetDelete);
		}
		
		session.getTransaction().commit();
		session.close();
	}

	@SuppressWarnings("unchecked")
	public void displayfromDepartment() {
		Session session = sf.openSession();
		session.beginTransaction();
		List<Department> listOfEmployee = session.createQuery("from Department").getResultList();
		System.out.println("employee information from department entity");
		for (Department getDepartment : listOfEmployee) {
			System.out.println("department " + getDepartment.getDepartmentName());
			for (Employee employeeDepartment : getDepartment.getEmployeeList()) {
				System.out.println("first name " + employeeDepartment.getFname());
				System.out.println("last name " + employeeDepartment.getLname());
			}
		}
		session.close();
	}

	@SuppressWarnings("unchecked")
	public void displayfromEmployee() {
		Session session = sf.openSession();
		session.beginTransaction();
		List<Employee> listOfEmployee = session.createQuery("from Employee").getResultList();
		System.out.println("employee information from employee entity");
		System.out.println(listOfEmployee.toString());
	}

	public static void main(String args[]) {
		
		Hibernate hibernate = new Hibernate();

		System.out.println("employee insert");
		hibernate.insert();
		hibernate.displayfromEmployee();
		hibernate.displayfromDepartment();

		System.out.println("employee update");
		hibernate.update();
		hibernate.displayfromEmployee();
		hibernate.displayfromDepartment();

		System.out.println("employee delete");
		hibernate.delete();
		hibernate.displayfromEmployee();
		hibernate.displayfromDepartment();
	}

}
