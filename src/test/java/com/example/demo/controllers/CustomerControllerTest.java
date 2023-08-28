package com.example.demo.controllers;

import com.example.demo.domain.Customer;
import com.example.demo.services.CustomerService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.hamcrest.Matchers.equalTo;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * unit tests for CustomerController
 */
class CustomerControllerTest {

    @Mock
    private CustomerService customerService;

    @InjectMocks
    private CustomerController customerController;

    MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        mockMvc = MockMvcBuilders
                .standaloneSetup(customerController)
                .build();
    }

    @Test
    void shouldCreateNewCar() throws Exception {
        final Customer customer = getNewCustomer();

        when(customerService.createNewCustomer(customer)).thenReturn(customer);

        mockMvc.perform(post(CustomerController.BASE_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(customer)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id", equalTo(1)))
                .andExpect(jsonPath("$.firstName", equalTo("Bob")))
                .andExpect(jsonPath("$.lastName", equalTo("Builder")));
    }

    private Customer getNewCustomer() {
        return Customer.builder()
                .id(1L)
                .firstName("Bob")
                .lastName("Builder")
                .email("Bob@Bob.nl")
                .street("Bob street")
                .postalCode("1234AA")
                .houseNumber("1")
                .phoneNumber("1234567890").build();
    }

    public static String asJsonString(final Object o) {
        try{
            return new ObjectMapper().writeValueAsString(o);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}