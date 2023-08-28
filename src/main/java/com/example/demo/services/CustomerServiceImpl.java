package com.example.demo.services;

import com.example.demo.domain.Customer;
import com.example.demo.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public Customer createNewCustomer(final Customer customer) {
        return customerRepository.save(customer);
    }

    @Override
    public void deleteCustomerById(final Long id) {
        customerRepository.deleteById(id);
    }

    @Override
    public Customer patchCustomer(final Long id, final Customer customer) {
        return customerRepository.findById(id).map(cs -> {

            //TODO: expand options

            if (customer.getFirstName() != null) {
                cs.setFirstName(customer.getFirstName());
            }
            if (customer.getLastName() != null) {
                cs.setLastName(customer.getLastName());
            }
            if (customer.getEmail() != null) {
                cs.setEmail(customer.getEmail());
            }
            if (customer.getPhoneNumber() != null) {
                cs.setPhoneNumber(customer.getPhoneNumber());
            }

            return customerRepository.save(cs);
        }).orElseThrow(ResourceNotFoundException::new);
    }
}
