import java.io.*;
import java.lang.*;
import java.util.*;

public class ExpressionStack {

    private Stack <String> operatorStack = new Stack<String>();
    private Hashtable<String, Integer> OperatorTable = new Hashtable<String, Integer>();
    private String postFixExpr;

    public String createExprStack(String expression) {
        this.createOperatorTable();
        for(int i = 0; i < expression.length(); i++) {
            char token = expression.charAt(i);
            System.out.println("token " + token);
            if(token != ')' && token != '(') {
                this.compareOperators(Character.toString(token));
            }
            else if(token == '(') {
                this.operatorStack.push(Character.toString(token));
                System.out.println("1 Pushed " + token + "onto stack");
            }
            else {
                System.out.println("x");
                String currOperator = this.operatorStack.pop();
                System.out.println("Popped " + currOperator + "onto stack");
                while(!currOperator.equals("(")) {
                    this.postFixExpr += currOperator;
                    currOperator = this.operatorStack.pop();
                    System.out.println("Popped " + token + "onto stack");
                    System.out.println("currOperator " + currOperator);
                }
            }      
        }
        return this.postFixExpr;
    }

    public void createOperatorTable() {
        this.OperatorTable.put("*", 3);
        this.OperatorTable.put("/", 3);
        this.OperatorTable.put("-", 2);
        this.OperatorTable.put("+", 2);
    }

    public void compareOperators(String token) {
        String stackOperator;
        int currPrecedence;
        int stackPrecedence;
        //System.out.println("a0");
        if(this.OperatorTable.containsKey(token) && !this.operatorStack.isEmpty()) {
            //System.out.println("a1");
            currPrecedence = this.OperatorTable.get(token);
            stackOperator = (String) this.operatorStack.pop();
            System.out.println("2 Popped " + stackOperator + "onto stack");
            stackPrecedence = this.OperatorTable.get(stackOperator);
            if(stackPrecedence > currPrecedence) {
                    this.operatorStack.push(token);
                    System.out.println("3 Pushed " + token + "onto stack");
                    this.operatorStack.push(stackOperator);
                    System.out.println("4 Pushed " + stackOperator + "onto stack");
                }
            else {
                this.postFixExpr += stackOperator;
                this.compareOperators(token);
            }
        }
        else if(this.OperatorTable.containsKey(token) && this.operatorStack.isEmpty()) {
            this.operatorStack.push(token);
            System.out.println("5 Pushed " + token + "onto stack");
            //System.out.println("a2");
        }
        else {
            this.postFixExpr += token;
        }
    }
}