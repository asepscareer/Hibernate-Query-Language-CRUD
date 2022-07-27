package com.hql.demo;

import com.hql.demo.entity.Employee;
import com.hql.demo.util.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

public class App {
    public static void main(String[] args) {

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {

            // Try in Order

            createEmployee(session);
//            getEmployee(session);
//            getEmployeebyId(session);
//            updateEmployeeById(session);
//            deleteEmployeeById(session);

        } catch (HibernateException e) {
            e.printStackTrace();
        }
    }

    private static void createEmployee(Session session) {
        session.beginTransaction();

        Employee employee = new Employee();
        employee.setName("Asep Saputra");
        employee.setEmail("asep@gmail.com");

        Integer id = (Integer) session.save(employee);
        System.out.println("Employee is created  with Id::" + id);
        session.getTransaction().commit();
    }

    private static void getEmployee(Session session) {
        String hql = "FROM Employee";
        Query query = session.createQuery(hql);
        final List<Employee> result = (List<Employee>) query.list();
        for (final Employee employee : result) {
            System.out.println(employee.toString());
        }
    }

    private static void getEmployeebyId(Session session) {
        Employee employee = session.get(Employee.class, 6);
        if (employee != null) {
            System.out.println(employee.getName());
        } else {
            System.out.println("Employee doesn't exist with provideded Id..");
        }
    }

    private static void deleteEmployeeById(Session session) {
        Employee employee = session.get(Employee.class, 5);
        if (employee != null) {
            session.beginTransaction();

            session.delete(employee);
            session.getTransaction().commit();
        } else {
            System.out.println("Employee doesn't exist with provideded Id..");
        }
    }

    private static void updateEmployeeById(Session session) {
        Employee employee = session.get(Employee.class, 5);
        if (employee != null) {
            employee.setName("Who I am?!");
            employee.setEmail("xxx@gmail.com");
            session.beginTransaction();

            session.update(employee);
            session.getTransaction().commit();
        } else {
            System.out.println("Employee doesn't exist with provideded Id..");
        }
    }

}
