package com.example.demo.services;

import com.example.demo.domain.Customer;

public interface CustomerService {

    /**
     * @param customer customer to be created
     * @return the created customer
     */
    Customer createNewCustomer(final Customer customer);

    /**
     * @param id id of customer to delete
     */
    void deleteCustomerById(final Long id);

    /**
     * method to patch an existing customer
     * @param id id of customer to patch
     * @param customer customer with changed values
     * @return the patched customer
     */
    Customer patchCustomer(final Long id, final Customer customer);
}
