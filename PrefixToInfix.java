import java.util.Scanner;

public class PrefixToInfix {

    public static void main(String[] args) {
        Scanner inp = new Scanner(System.in);
        while (true) {
            System.out.println("Dwse Prefix Morfh:");
            String input = inp.nextLine();
            StringDoubleEndedQueueImpl<String> q = new StringDoubleEndedQueueImpl<String>();

            // Check if the expression starts with an operator
            if (!isOperator(input.charAt(0))) {
                System.out.println("Invalid Prefix Form... Expression must start with an operator... Try again.");
                continue;
            }

            boolean isValid = true;

            // read the prefix in reverse order
            for (int i = input.length() - 1; i >= 0; i--) {
                char currentChar = input.charAt(i);

                // Check if character is a valid symbol (number or operator)
                if (!Character.isDigit(currentChar) && !isOperator(currentChar)) {
                    System.out.println("Invalid Prefix Form... Expression contains invalid symbols... Try again.");
                    isValid = false;
                    break;
                }

                // check if symbol is operator
                if (isOperator(currentChar)) {

                    // check if there are enough elements in the queue
                    if (q.size() < 2) {
                        System.out.println("Incorrect Prefix Form... Not enough operands... Try again.");
                        isValid = false;
                        break;
                    }
                    // remove two operands from queue
                    String op1 = q.removeFirst();
                    String op2 = q.removeFirst();

                    // concat the operands and operator
                    String temp = "(" + op1 + currentChar + op2 + ")";

                    // Add String temp back to queue
                    q.addFirst(temp);
                } else {

                    // add the operand to the queue
                    q.addFirst(currentChar + "");
                }
            }

            if (!isValid) {
                continue;
            }

            // check if there are unused operands
            if (q.size() != 1) {
                System.out.println("Incorrect Prefix Form... Unused operands... Try again.");
                continue;
            }

            // queue contains the Infix expression
            System.out.println("Infix expression is");
            System.out.println(q.removeFirst());
            break;
        }
        inp.close();
    }

    // Check if character is operator
    private static boolean isOperator(char x) {
        switch (x) {
            case '+':
            case '-':
            case '/':
            case '*':
                return true;
        }
        return false;
    }
}