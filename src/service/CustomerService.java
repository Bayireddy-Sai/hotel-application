package service;

import model.Customer;

import java.util.*;

public class CustomerService {

    private final HashMap<String,Customer> customers=new HashMap<>();
    private static CustomerService Instance ;
    private CustomerService(){}
    public static CustomerService getInstance(){
        if (Instance == null){
            Instance = new CustomerService();
        }
        return Instance;
    }




    public void addCustomer(String email,String firstName,String lastName){
        Customer customer=new Customer(firstName,lastName,email);
        customers.put(email,customer);



    }
    public Customer getCustomer(String customerEmail){
        return customers.get(customerEmail);



    }
    public Collection<Customer> getAllCustomers(){
        return new ArrayList<>(this.customers.values());
    }


}
