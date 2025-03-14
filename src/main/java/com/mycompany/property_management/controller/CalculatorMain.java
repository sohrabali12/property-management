package com.mycompany.property_management.controller;

public class CalculatorMain {
    public static void main(String[] args) {
        CalculatorController controller = new CalculatorController();
        Double add = controller.add(4.5, 7.8);
        System.out.println(add);
    }
}
