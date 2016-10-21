import java.io.*;
import java.lang.*;
import java.util.*;

//import com.sun.org.apache.xalan.internal.xsltc.runtime.Node;

//import javax.xml.soap.Node;

public class PemdasTree 
{
	/*int iter; 
    private Stack <Node> pointerStack = new Stack<Node>();
    String [] operators = {"+", "-", "*", "/", "^"}; 
    
    public Node parseExpression(String expression) 
    { 
    	Node tree; 
        String [] parts = expression.split(""); 
        iter = 0; 
        boolean operator = false; 
        while(iter < parts.length )
        { 
        	operator = false;
        	for(int n = 0; n < operators.length; n ++) 
        	{
        		if(parts[iter] == operators[n])
        		{
        			operator = true; 
        			System.out.println("This is the operator" + parts[iter]);
        		}
        	}
        	
        	if(operator == false)
        	{
        		Node temp = new Node(); 
        		temp.setValue(parts[iter]);
        		pointerStack.push(temp);
        	}
        	else 
        	{
        		Node tempRight = pointerStack.pop();
        		System.out.println("Left :" + tempRight.getValue());
        		Node tempLeft = pointerStack.pop(); 
        		System.out.println("Right : " + tempLeft.getValue()); 
        		Node link = new Node(); 
        		link.setValue(parts[iter]);
        		System.out.println("Link : " + link.getValue() );
        		link.setLeftNode(tempLeft); 
        		link.setRightNode(tempRight); 
        		pointerStack.push(link); 
        	}
        	iter++; 
        }
        
        tree = pointerStack.pop();
        return tree;
    }
    */

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
        for(int i = 0; i < expression.length(); i++) {
            char token = expression.charAt(i);
            if(this.OperatorTable.containsKey(Character.toString(token))) {

                Node Operand1 = operatorStack.pop();
                Node Operand2 = operatorStack.pop();
                Node tempNode = new Node(Operand1, Operand2, Character.toString(token));
                operatorStack.push(tempNode);
            }
            else {
                Node tempNode = new Node(null, null, Character.toString(token));
                operatorStack.push(tempNode);
            }
        }
        while(!operatorStack.isEmpty()) {
            
        }
        return operatorStack.pop();
    }
}