package com.company;

import java.util.*;

public class Calculator {

    /*
    I use Shunting Yard Algorithm to CONVERT the infix notation to reverse polish notation.
    Then imitate stack by using array to evaluate reverse polish notation.
    */

    public static ArrayList<String> convertInfix(String[] exprTokens){
        ArrayList<String> infix = new ArrayList<String>();
        Stack<String> operatorStack = new Stack<String>();

        for(String op : exprTokens){
            op = op.trim();
            //if its an operand , simply append to output
            if(!isOperator(op)){
                infix.add(op);
            }
            //if its an operator
            else{
                //if its a left parenthesis then push it to stack
                if(op.equals("(")){
                    operatorStack.push("(");
                }
                //other wise if it is a right parenthesis then pop the stack until we see a matching left parenthesis
                else if(op.equals(")")){
                    while(!operatorStack.peek().equals("(") && !operatorStack.isEmpty()){
                        infix.add(operatorStack.pop());
                    }

                    //if we do not have a matching left parenthesis then it's a malformed expression
                    if(operatorStack.isEmpty() || !operatorStack.peek().equals("(")){
                        return null;
                    }
                    //otherwise we found a matching left. Just pop it out
                    else{
                        operatorStack.pop();
                    }
                }
                //otherwise its an operator
                else{
                    //keep popping out element from stack and append in output as long as we see a higher precedence operator
                    //in the top of stack
                    while(
                            !operatorStack.isEmpty()
                                    && (
                                    (isLeftAssociative(op) && getPrecedence(op) <= getPrecedence(operatorStack.peek()))
                                            || (!isLeftAssociative(op) && getPrecedence(op) < getPrecedence(operatorStack.peek()))
                            )
                    ){
                        infix.add(operatorStack.pop());
                    }
                    //ow add the operator
                    operatorStack.push(op);
                }
            }
        }

        //if there are left over element sin stack then append them in the output
        while(!operatorStack.isEmpty()){
            infix.add(operatorStack.pop());
        }

        return infix;
    }

    private static int getPrecedence(String op){
        if(op.equals("+") || op.equals("-")){
            return 2;
        }
        if(op.equals("*") || op.equals("/")){
            return 3;
        }

        return 0;
    }

    private static boolean isLeftAssociative(String op){
        if(op.equals("+") || op.equals("-") || op.equals("*") || op.equals("/")){
            return true;
        }
        return false;
    }

    private static boolean isOperator(String op){
        return op.matches("[-+*/()]");
    }

    public double evalRPN(String[] tokens) {
        Stack <Double> stack = new Stack<>();

        for(int i=0; i<tokens.length; i++){
            if(tokens[i].equals("+")){
                stack.push(stack.pop() + stack.pop());
            }
            else if(tokens[i].equals("-")){
                if(stack.size() == 1)
                {
                    stack.push(stack.pop() * -1);
                }
                else {
                    stack.push(-stack.pop() + stack.pop());
                }
            }
            else if(tokens[i].equals("*")){
                stack.push(stack.pop()*stack.pop());
            }
            else if(tokens[i].equals("/")){
                double a= stack.pop();
                stack.push(stack.pop()/a);
            }
            else
                stack.push(Double.parseDouble(tokens[i]));
        }
        return stack.pop();
    }
}
