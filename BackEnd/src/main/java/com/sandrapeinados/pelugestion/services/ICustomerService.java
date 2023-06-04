package com.sandrapeinados.pelugestion.services;

import com.sandrapeinados.pelugestion.models.Customer;
import com.sandrapeinados.pelugestion.models.Job;

import java.util.List;

public interface ICustomerService {
    Customer saveCustomer(Customer customer);

    List<Customer> getCustomers();

}
