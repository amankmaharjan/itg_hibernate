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
		
		employee1.setFname("Hari");
		employee1.setLname("Prasad");
		
		Department department1 = new Department();
		
		department1.setDepartmentName("Human Resource");
		employee1.setDepartment(department1);
		department1.getEmployeeList().add(employee1);
		
		
		session.save(department1);
		
		session.getTransaction().commit();
		session.close();
	}

	public void update() {
		Session session = sf.openSession();
		session.beginTransaction();
		Employee employeeUpdate = session.get(Employee.class, 1);
		System.out.println("student info to be updated");
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

	@SuppressWarnings({ "deprecation", "unchecked" })
	public void display() {
		Session session = sf.openSession();
		session.beginTransaction();
		List<Employee> listOfEmployee = session.createCriteria(Employee.class).list();
		System.out.println("employee information");
		System.out.println(listOfEmployee.toString());
		List<Department> listOfDepartment = session.createCriteria(Department.class).list();
		System.out.println("department information");
		for (Department ddepartmentList : listOfDepartment) {
			//List<Employee> departmentEmployee = ddepartmentList.getEmployeeList();
			System.out.println(ddepartmentList.getEmployeeList().size());
			
		}
		session.close();
	}

	public static void main(String args[]) {
		Hibernate hibernate = new Hibernate();

		System.out.println("employee insert");
		hibernate.insert();
		hibernate.display();

		System.out.println("employee update");
		// hibernate.update();
		// hibernate.display();

		System.out.println("employee delete");
		// hibernate.delete();
		// hibernate.display();
	}

}
