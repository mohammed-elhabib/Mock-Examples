package example.db;


import org.junit.jupiter.api.Test;

import javax.persistence.EntityManager;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class CustomerReaderTest {
    //Class to be tested
    private CustomerReader customerReader;

    //Dependencies
    private EntityManager entityManager;

    public void setup(){
        customerReader = new CustomerReader();
        entityManager = mock(EntityManager.class);
        customerReader.setEntityManager(entityManager);
    }

    @Test
    public void happyPathScenario(){
        this.setup();
        Customer sampleCustomer = new Customer();
        sampleCustomer.setFirstName("Susan");
        sampleCustomer.setLastName("Ivanova");
        when(entityManager.find(Customer.class,1L)).thenReturn(sampleCustomer);
        String fullName = customerReader.findFullName(1L);
        assertEquals("Susan Ivanova",fullName);
    }
    @Test
    public void customerNotPresentInDb(){
        this.setup();
        when(entityManager.find(Customer.class,1L)).thenReturn(null);
        assertThrows(Exception.class,()->customerReader.findFullName(1L));
        //   String fullName = customerReader.findFullName(1L);
       // assertEquals("",fullName);
    }
}
