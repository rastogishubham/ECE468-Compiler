
import java.io.*;
import java.lang.*;
import java.util.*;


public class Node 
{
	Node left; 
	Node right; 
	String value; 
	
	public static void main (String [] args) 
	{
		
	}


	public void setLeftNode(Node leftTemp) 
	{
		left = leftTemp; 
	}
	
	public void setRightNode(Node rightTemp)
	{
		right = rightTemp;
	}

	public void setValue(String valueTemp)
	{
		value = valueTemp; 
	}
	
	public String getValue()
	{
		return value;
	}
	
}