import java.io.*;
import java.lang.*;
import java.util.*;

public class ExpressionStack {

    private Stack <String> operatorStack = new Stack<String>();
    private Hashtable<String, Integer> OperatorTable = new Hashtable<String, Integer>();
    private String postFixExpr = "";

    public String createExprStack(String expression) {
        this.createOperatorTable();
        for(int i = 0; i < expression.length(); i++) {
            char token = expression.charAt(i);
            //System.out.println("token " + token);
            if(token != ')' && token != '(') {
                this.compareOperators(Character.toString(token));
            }
            else if(token == '(') {
                this.operatorStack.push(Character.toString(token));
                //System.out.println("1 Pushed " + token + " on the stack");
            }
            else {
                String currOperator = this.operatorStack.pop();
                //System.out.println("1 Popped " + currOperator + " off the stack");
                while(!currOperator.equals("(")) {
                    this.postFixExpr += currOperator;
                  //  System.out.println("1 postFixExpr "+ this.postFixExpr);
                    currOperator = this.operatorStack.pop();
                 //   System.out.println("2 Popped " + currOperator + " off the stack");
                }
            }      
        }
        while(!operatorStack.isEmpty()) {
            String val = (String) operatorStack.pop();
            //System.out.println("3 Popped" + val + " off the stack");
            this.postFixExpr += val;
            //System.out.println("1 postFixExpr "+ this.postFixExpr);
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
        if(this.OperatorTable.containsKey(token) && !this.operatorStack.isEmpty()) {
            currPrecedence = this.OperatorTable.get(token);
            stackOperator = (String) this.operatorStack.pop();
            //System.out.println("4 Popped " + stackOperator + " off the stack");
            if(stackOperator.equals("(")) {
                this.operatorStack.push(stackOperator);
               // System.out.println("2 Pushed " + stackOperator + " on the stack");
                this.operatorStack.push(token);
              //  System.out.println("3 Pushed " + token + " on the stack");
            }
            else {
                stackPrecedence = this.OperatorTable.get(stackOperator);
                if(currPrecedence > stackPrecedence) {
                        this.operatorStack.push(stackOperator);
                        this.operatorStack.push(token);
                    }
                else {
                    this.postFixExpr += stackOperator;
                    this.compareOperators(token);
                }
            }
        }
        else if(this.OperatorTable.containsKey(token) && this.operatorStack.isEmpty()) {
            this.operatorStack.push(token);
        }
        else {
            this.postFixExpr += token;
        }
    }
}