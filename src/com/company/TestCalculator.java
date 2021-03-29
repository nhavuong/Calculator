package com.company;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class TestCalculator {
    Calculator c;

    @BeforeEach
    public void init() {
        c = new Calculator();
    }

    @Test
    public void testPlus() {
        String s = "1 + 2";

        Calculator c = new Calculator();
        s = s.replaceAll("\\s+", "");
        String regex = "(?<=[-+*/\\(\\)])|(?=[-+*/\\(\\)])";
        String[] input = s.split(regex);

        ArrayList<String> convert = c.convertInfix(input);

        // Format for output
        DecimalFormat df = new DecimalFormat("0.##");
        String array[] = new String[convert.size()];
        for (int j = 0; j < convert.size(); j++) {
            array[j] = convert.get(j);
        }

        double result = c.evalRPN(array);
        Assertions.assertEquals(3, result);
    }

    @Test
    public void testMultiple() {
        String s = "4*5/2";

        Calculator c = new Calculator();
        s = s.replaceAll("\\s+", "");
        String regex = "(?<=[-+*/\\(\\)])|(?=[-+*/\\(\\)])";
        String[] input = s.split(regex);

        ArrayList<String> convert = c.convertInfix(input);

        // Format for output
        DecimalFormat df = new DecimalFormat("0.##");
        String array[] = new String[convert.size()];
        for (int j = 0; j < convert.size(); j++) {
            array[j] = convert.get(j);
        }

        double result = c.evalRPN(array);
        Assertions.assertEquals(10, result);
    }

    @Test
    public void testDecimal() {
        String s = "-.32       /.5";

        Calculator c = new Calculator();
        s = s.replaceAll("\\s+", "");
        String regex = "(?<=[-+*/\\(\\)])|(?=[-+*/\\(\\)])";
        String[] input = s.split(regex);

        ArrayList<String> convert = c.convertInfix(input);

        // Format for output
        DecimalFormat df = new DecimalFormat("0.##");
        String array[] = new String[convert.size()];
        for (int j = 0; j < convert.size(); j++) {
            array[j] = convert.get(j);
        }

        double result = c.evalRPN(array);
        Assertions.assertEquals(-0.64, result);
    }

    @Test
    public void testComplex() {
        String s = "(4-2)*3.5";

        Calculator c = new Calculator();
        s = s.replaceAll("\\s+", "");
        String regex = "(?<=[-+*/\\(\\)])|(?=[-+*/\\(\\)])";
        String[] input = s.split(regex);

        ArrayList<String> convert = c.convertInfix(input);

        // Format for output
        DecimalFormat df = new DecimalFormat("0.##");
        String array[] = new String[convert.size()];
        for (int j = 0; j < convert.size(); j++) {
            array[j] = convert.get(j);
        }

        double result = c.evalRPN(array);
        Assertions.assertEquals(7, result);
    }

}
