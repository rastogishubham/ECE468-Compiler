import java.io.*;
import java.lang.*;
import java.util.*;

public class ExpressionStack {

    private Stack <String> operatorStack = new Stack<String>();
    private Hashtable<String, Integer> OperatorTable = new Hashtable<String, Integer>();
    private String postFixExpr = "";
    private List <String> tokenList = new ArrayList <String> ();

    public void createTokenList(String expression) {
        String identifier = "";
        for(int i = 0; i < expression.length(); i++) {
            char token = expression.charAt(i);
            if(OperatorTable.containsKey(Character.toString(token))) {
                tokenList.add(identifier);
                tokenList.add(Character.toString(token));
                identifier = "";
            }
            else if(token == '(' || token == ')') {
                tokenList.add(Character.toString(token));
            }
            else {
                identifier += token;
            }
        }
        this.tokenList.add(identifier);
    }

    public String createExprStack(String expression) {
        this.createOperatorTable();
        this.createTokenList(expression);
        for(int i = 0; i < tokenList.size(); i++) {
            String token = tokenList.get(i);
            if(!token.equals(")") && !token.equals("(")) {
                this.compareOperators(token);
            }
            else if(token.equals("(")) {
                this.operatorStack.push(token);
            }
            else {
                String currOperator = this.operatorStack.pop();
                while(!currOperator.equals("(")) {
                    this.postFixExpr += " " + currOperator;
                    currOperator = this.operatorStack.pop();
                }
            }      
        }
        while(!operatorStack.isEmpty()) {
            String val = (String) operatorStack.pop();
            this.postFixExpr += " " + val;
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
            if(stackOperator.equals("(")) {
                this.operatorStack.push(stackOperator);
                this.operatorStack.push(token);
            }
            else {
                stackPrecedence = this.OperatorTable.get(stackOperator);
                if(currPrecedence > stackPrecedence) {
                        this.operatorStack.push(stackOperator);
                        this.operatorStack.push(token);
                    }
                else {
                    this.postFixExpr += " " + stackOperator;
                    this.compareOperators(token);
                }
            }
        }
        else if(this.OperatorTable.containsKey(token) && this.operatorStack.isEmpty()) {
            this.operatorStack.push(token);
        }
        else {
            this.postFixExpr += " " + token;
        }
    }
}