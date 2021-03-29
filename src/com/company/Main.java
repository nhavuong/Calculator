package com.company;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args){

        /*

        Some Examples:
        1 + 2                  => 3
        4*5/2                  => 10
        -.32       /.5         => -0.64
        (4-2)*3.5              => 7
        19 + cinnamon          => INVALID INPUT
        stop                   => exit


        CASE: "-5+-8--11*2" => Can't handle. my function convertInfix(String[] exprTokens) will return [5,-,+,8,-,-,11,2,*,-] -> WRONG
        it should be [-,5,-,8,+,-,11,2,*,-]

        */

        //Declare the object and initialize with predefined standard input object
        Scanner sc = new Scanner((System.in));
        boolean fail = false;
        do {
            System.out.println("Please Enter Input Expression: (Type \"stop\" to exit) ");
            String s = sc.nextLine();
            Calculator cal = new Calculator();

            if(s.equalsIgnoreCase("stop"))
            {
                fail = true;
                System.out.println("Bye bye");
                System.exit(0);
            }

            // Check Invalid input (using regex to match any character that [a-zA-z]
            if (s.matches(".*[a-zA-Z].*")) {
                System.out.println("\n=============================");
                System.out.println("***INVALID INPUT***");
                System.out.println("\n=============================");
            }
            else {
                // replacing all space and using regex to split input string to Array String.
                s = s.replaceAll("\\s+", "");
                String regex = "(?<=[-+*/\\(\\)])|(?=[-+*/\\(\\)])";
                String[] input = s.split(regex);

                ArrayList<String> convert = cal.convertInfix(input);
                // Format for output
                DecimalFormat df = new DecimalFormat("0.##");

                System.out.println("\n=============================");
                String array[] = new String[convert.size()];
                for (int j = 0; j < convert.size(); j++) {
                    array[j] = convert.get(j);
                }
                double eval = cal.evalRPN(array);
                System.out.println(String.format("Result =  %s", df.format(eval)));
                System.out.println("\n=============================");
            }

        } while(!fail);
    }
}
