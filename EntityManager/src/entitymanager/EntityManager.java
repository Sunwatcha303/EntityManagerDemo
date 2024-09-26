/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entitymanager;

import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * @author sun watcha
 */
public class EntityManager {

    private void persist(Object... objects) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("entitymanagerdemoPU");
        javax.persistence.EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        try {
            for (Object object : objects) {
                em.persist(object);
            }
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }

    private List<Customer> findAllCustomer() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("entitymanagerdemoPU");
        javax.persistence.EntityManager em = emf.createEntityManager();
        String jpql = "SELECT c FROM Customer c ORDER BY c.id";
        Query query = em.createQuery(jpql);
        List<Customer> customerList = (List<Customer>) query.getResultList();
        em.close();
        return customerList;
    }

    private List<Customer> findCustomerByCity(String city) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("entitymanagerdemoPU");
        javax.persistence.EntityManager em = emf.createEntityManager();
        String jpql = "SELECT c FROM Customer c WHERE c.id IN (SELECT a.customerFk.id FROM Address a WHERE a.city = :city) ORDER BY c.id";
        Query query = em.createQuery(jpql, Customer.class);
        query.setParameter("city", city);
        List<Customer> customerList = (List<Customer>) query.getResultList();
        em.close();
        return customerList;
    }

    public void createData() {
        Address bangkok1 = new Address(1L, "123/4 Viphavadee Rd.", "Bangkok", "TH", "10900");
        Address bangkok2 = new Address(2L, "123/5 Viphavadee Rd.", "Bangkok", "TH", "10900");
        Address nonthaburi = new Address(3L, "543/21 Fake Rd.", "Nonthaburi", "TH", "20900");
        Address pathumthani = new Address(4L, "678/90 Unreal Rd.", "Pathumthani", "TH", "30500");

        Customer jh = new Customer(1L, "John", "Henry", "jh@mail.com");
        Customer mj = new Customer(2L, "Marry", "Jane", "mj@mail.com");
        Customer pp = new Customer(3L, "Peter", "Parker", "pp@mail.com");
        Customer bw = new Customer(4L, "Bruce", "Wayn", "bw@mail.com");

        jh.setAddress(bangkok1);
        mj.setAddress(bangkok2);
        pp.setAddress(nonthaburi);
        bw.setAddress(pathumthani);

        bangkok1.setCustomerFk(jh);
        bangkok2.setCustomerFk(mj);
        nonthaburi.setCustomerFk(pp);
        pathumthani.setCustomerFk(bw);

        persist(jh, mj, pp, bw, bangkok1, bangkok2, nonthaburi, pathumthani);
    }

    public void printAllCustomer() {
        List<Customer> list = findAllCustomer();
        for (Customer c : list) {
            System.out.println(c);
            System.out.println("------------------------------------------\n");
            System.out.println("------------------------------------------");
        }
    }

    public void printCustomerByCity(String city) {
        List<Customer> list = findCustomerByCity(city);
        for (Customer c : list) {
            System.out.println(c);
            System.out.println("------------------------------------------\n");
            System.out.println("------------------------------------------");
        }
    }
}
