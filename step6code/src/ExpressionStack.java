import java.io.*;
import java.lang.*;
import java.util.*;

public class ExpressionStack {

    private Stack <String> operatorStack = new Stack<String>();
    private Hashtable<String, Integer> OperatorTable = new Hashtable<String, Integer>();
    private String postFixExpr = "";
    private List <String> tokenList = new ArrayList <String> ();

    private void createTokenList(String expression) {
        String replaceExp = expression;
        int subPos = 0;
        for(int i = 0; i < expression.length(); i++) {
            String curr = Character.toString(expression.charAt(i));
            String prev = "";
            if(i == expression.length())
                break;
            if(i > 0)
                prev = Character.toString(expression.charAt(i - 1));
            
            if(prev.matches("[A-Za-z0-9]") && curr.equals("("))
                i = findCloseParenPos(replaceExp, i);
            else if(curr.matches("[\\*\\/\\+\\-\\(\\)]")) {
                addtoList(expression, subPos, i);
                subPos = i;
                addtoList(expression, subPos, i + 1);
                subPos++;
            }
        }
        if(subPos < expression.length())
            tokenList.add(expression.substring(subPos, expression.length()));
    }

    private int findCloseParenPos(String expression, int openPos) {
        int numParen = 0;
        for(int i = openPos + 1; i < expression.length(); i++) {
            String curr = Character.toString(expression.charAt(i));
            if(curr.equals("("))
                numParen++;
            if(curr.equals(")")) {
                if(numParen == 0)
                    return i;
                else
                    numParen--;
            }
        }
        return 0;
    }
    private void addtoList(String expression, int begin, int end) {
        if(!expression.substring(begin, end).equals(""))
            tokenList.add(expression.substring(begin, end));
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