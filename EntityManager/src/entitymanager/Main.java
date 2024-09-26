/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package entitymanager;

/**
 *
 * @author sun watcha
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        EntityManager entityManager = new EntityManager();
        entityManager.createData();
        entityManager.printAllCustomer();
        entityManager.printCustomerByCity("Bangkok");
    }
    
}
