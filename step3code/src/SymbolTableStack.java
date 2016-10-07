import java.io.*;
import java.lang.*;
import java.util.*;

public class SymbolTableStack {
	private int BlockScopeNum = 1;
	private List <SymbolTable> SymbolStack = new ArrayList<SymbolTable>();
	//private static int x = 0;
	
	public void pushNewSymbolTable(String Scope) {
		SymbolTable tempTable = new SymbolTable(Scope, BlockScopeNum);
		//SymbolStack.push(tempTable);
		SymbolStack.add(tempTable);
		if(Scope.equals("BLOCK")) {
			BlockScopeNum++;
		}
	}
	/*public static void popTopSymbolTable() {
		SymbolTable tempTable = SymbolStack.pop();
	}*/
	public void addSymbol(String type, String name, String value) {
		SymbolTable tempTable = SymbolStack.get(SymbolStack.size() - 1);//SymbolStack.pop();
		tempTable.insertNewVariable(type, name, value);
		//SymbolStack.push(tempTable);
		SymbolStack.remove(SymbolStack.size() - 1);
		SymbolStack.add(tempTable);
	}
	/*public static void printTopSymbolTable() {
		SymbolTable tempTable = SymbolStack.pop();
		tempTable.printTable();	
	}*/
	public void printSymbolStack() {
		for(int i = 0; i < SymbolStack.size(); i++) {
			SymbolTable tempTable = SymbolStack.get(i);
			tempTable.printTable();
		}
	}
}
