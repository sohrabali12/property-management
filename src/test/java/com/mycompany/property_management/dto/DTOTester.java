package com.mycompany.property_management.dto;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.meanbean.test.BeanTester;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class DTOTester {

    @Test
    @DisplayName("Tests all DTO's Getter and Setter")
    void testAllDTOs(){

        BeanTester beanTester = new BeanTester();
        beanTester.testBean(PropertyDTO.class);
        beanTester.testBean(CalculatorDTO.class);
        beanTester.testBean(UserDTO.class);
    }
}
