package com.example.demo.controllers;

import com.example.demo.domain.Car;
import com.example.demo.domain.CarMakeType;
import com.example.demo.services.CarService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.math.BigDecimal;

import static org.hamcrest.Matchers.equalTo;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Unit tests for CarController
 */
class CarControllerTest {

    @Mock
    private CarService carService;

    @InjectMocks
    private CarController carController;

    MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        mockMvc = MockMvcBuilders
                .standaloneSetup(carController)
                .build();
    }

    @Test
    void shouldCreateNewCar() throws Exception {
        final Car car = getNewCar();

        when(carService.createNewCar(car)).thenReturn(car);

        mockMvc.perform(post(CarController.BASE_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(car)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id", equalTo(1)))
                .andExpect(jsonPath("$.model", equalTo("model Bob")))
                .andExpect(jsonPath("$.version", equalTo("version Bob")));
    }

    public static String asJsonString(final Object o) {
        try{
            return new ObjectMapper().writeValueAsString(o);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private Car getNewCar() {
        return Car.builder()
                .id(1L)
                .makeType(CarMakeType.LEXUS)
                .model("model Bob")
                .version("version Bob")
                .numberOfDoors(4)
                .grossPrice(BigDecimal.valueOf(1000L))
                .nettPrice(BigDecimal.valueOf(900L))
                .hp(1000L).build();
    }
}