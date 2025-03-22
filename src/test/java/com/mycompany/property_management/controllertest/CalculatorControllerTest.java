package com.mycompany.property_management.controllertest;

import com.mycompany.property_management.controller.CalculatorController;
import com.mycompany.property_management.dto.CalculatorDTO;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

@ExtendWith(MockitoExtension.class)
class CalculatorControllerTest {

    @InjectMocks
    private CalculatorController calculatorController;

    static Double num1;
    static Double num2;

    @BeforeAll
    static void beforeAll(){
        System.out.println("Before All");
        num1 = 3.5;
        num2 = 3.5;
    }

    @BeforeEach
    void init(){
        System.out.println("Before Each");
    }

    @AfterEach
    void destroy(){
        System.out.println("After Each");
    }

    @AfterAll
    static void afterAll(){
        System.out.println("After All");
        num1 = null;
        num2 = null;
    }

    @Test
    @DisplayName("Test Add Success Scenario")
    @Tag("Add")
    void testAddFuction_Success(){
        Double result = calculatorController.add(num1, num2);
        // ExpectedResult is 8.5

        assertEquals(7.0, result);
    }

    @Test
    @DisplayName("Test Add Failure Scenario")
    @Tag("Add")
    void testAddFuction_Failure(){
        Double result = calculatorController.add(num1-0.5, num2);
        // ExpectedResult is 7.0

        assertNotEquals(7.0, result);
    }

    @Test
    @DisplayName("Test Subtraction for Num1 > Num2 Scenario")
    @Tag("Sub")
    void testSubFuction_num1_gt_num2(){
        Double result = calculatorController.subtract(num1+1.5, num2);
        // ExpectedResult is 7.0

        assertEquals(1.5, result);
    }

    @Test
    @DisplayName("Test Subtraction for Num1 < Num2 Scenario")
    @Tag("Sub")
    void testSubFuction_num1_lt_num2(){
        Double result = calculatorController.subtract(num1-0.5, num2);
        // ExpectedResult is 7.0

        assertNotEquals(1.5, result);
    }

    @Test
    @DisplayName("Test Multiplication Scenario")
    void testMultiply_Function(){
        CalculatorDTO calculatorDTO = new CalculatorDTO();
        calculatorDTO.setNum1(num1);
        calculatorDTO.setNum2(num2);
        calculatorDTO.setNum3(3.5);
        calculatorDTO.setNum4(3.5);
        ResponseEntity<Double> re = calculatorController.multiply(calculatorDTO);

        assertEquals(150.0625, re.getBody());
        assertEquals(HttpStatus.OK, re.getStatusCode(), "Expected status code to be OK(200)");

    }
}
