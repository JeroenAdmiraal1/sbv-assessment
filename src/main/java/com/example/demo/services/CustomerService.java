package com.example.demo.services;

import com.example.demo.domain.Customer;

public interface CustomerService {

    Customer createNewCustomer(final Customer customer);

    void deleteCustomerById(final Long id);

    Customer patchCustomer(final Long id, final Customer customer);
}
