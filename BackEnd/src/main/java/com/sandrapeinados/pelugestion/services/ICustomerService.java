package com.sandrapeinados.pelugestion.services;

import com.sandrapeinados.pelugestion.models.Customer;
import com.sandrapeinados.pelugestion.models.Job;

import java.util.List;

public interface ICustomerService {
    Customer saveCustomer(Customer customer);
    List<Customer> getCustomers();
    Customer getCustomerById(Long id);
    void deleteCustomer(Long id);
    Customer updateCustomer(Customer customer);
    Customer getCustomerDetails(Long id);

    List<Customer> getCustomersByName(String name);

}
