import java.io.*;
import java.lang.*;
import java.util.*;

//import com.sun.org.apache.xalan.internal.xsltc.runtime.Node;

//import javax.xml.soap.Node;

public class PemdasTree 
{
	int iter; 
    private Stack <Node> pointerStack = new Stack<Node>();
    String [] operators = {"+", "-", "*", "/", "^"}; 
    
    public static void main (String [] args) 
    { 	
    	String expression = " a b + c d e + * *"; 
    	PemdasTree run = new PemdasTree(); 
    	run.parseExpression(expression);
    }
    
    public Node parseExpression(String expression) 
    { 
    	Node tree; 
        String [] parts = expression.split(" "); 
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
    

}