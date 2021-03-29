Problem Summary:
Write a calculator program. The program should let a user enter a math problem as a string and get an answer.

Idea:
Convert an infix expression to reverse polish notation expression by using Shunting-yard algorithm developed by Dijkstra. 
Then evaluate the expression using stack. 
This is a O(n) time and O(n) space algorithm.

Some Examples:

    1 + 2                  => 3

    4*5/2                  => 10

    -.32       /.5         => -0.64

    (4-2)*3.5              => 7

    19 + cinnamon          => INVALID INPUT

    stop                   => exit


CASE: "-5+-8--11*2" => Can't handle. my function convertInfix(String[] exprTokens) will return [5, -, +, 8, -, -, 11, 2, *, -] -> WRONG

It should be [-, 5, -, 8, +, -, 11, 2, *, -]

Run by IntelliJ IDEA 

Reference: https://www.geeksforgeeks.org/java-program-to-implement-shunting-yard-algorithm/
