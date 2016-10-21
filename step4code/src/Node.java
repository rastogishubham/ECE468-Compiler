
import java.io.*;
import java.lang.*;
import java.util.*;


public class Node 
{
	private Node left; 
	private Node right; 
	private String value; 

	public Node (Node rightNode, Node leftNode, String val) {
		this.left = leftNode;
		this.right = rightNode;
		this.value = val;
	}

	public void setLeftNode(Node leftTemp) 
	{
		this.left = leftTemp; 
	}
	
	public void setRightNode(Node rightTemp)
	{
		this.right = rightTemp;
	}

	public void setValue(String valueTemp)
	{
		this.value = valueTemp; 
	}
	
	public String getValue()
	{
		return value;
	}
	
}