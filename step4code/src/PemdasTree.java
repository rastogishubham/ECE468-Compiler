import java.io.*;
import java.lang.*;
import java.util.*;

public class PemdasTree 
{

    private Stack <Node> operatorStack = new Stack<Node>();
    private Hashtable<String, Integer> OperatorTable = new Hashtable<String, Integer>();

    public void createOperatorTable() {
        this.OperatorTable.put("*", 3);
        this.OperatorTable.put("/", 3);
        this.OperatorTable.put("-", 2);
        this.OperatorTable.put("+", 2);
    }

    public Node createBinaryTree(String expression) {
        this.createOperatorTable();
        String [] tokenArray = expression.split(" ");
        for(int i = 0; i < tokenArray.length; i++) {
            String token = tokenArray[i];
            if(this.OperatorTable.containsKey(token)) {

                Node Operand1 = operatorStack.pop();
                Node Operand2 = operatorStack.pop();
                Node tempNode = new Node(Operand1, Operand2, token);
                operatorStack.push(tempNode);
            }
            else {
                Node tempNode = new Node(null, null, token);
                operatorStack.push(tempNode);
            }
        }
        return operatorStack.pop();
    }
}