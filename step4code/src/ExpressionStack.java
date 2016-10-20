import java.io.*;
import java.lang.*;
import java.util.*;

public class PemdasTree {

    private Stack operatorStack = new Stack();
    private Hashtable<String, int> OperatorTable = new Hashtable<String, int>();

    public String createExprStack(String expression) {
        String postFixExpr;
        int precedence;
        for(int i = 0; i < expression.length(); i++) {
            char token = expression.charAt(i);
            if(OperatorTable.containsKey(token)) {
                
            }
        }
    }

    public void createOperatorTable() {
        OperatorTable.put("*", 3);
        OperatorTable.put("/", 3);
        OperatorTable.put("-", 2);
        OperatorTable.put("+", 2);
    }

}