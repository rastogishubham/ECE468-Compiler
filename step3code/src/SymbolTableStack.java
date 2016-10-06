import java.io.*;
import java.lang.*;
import java.util.*;

public class SymbolTableStack {
	private static int BlockScopeNum = 1;
	private static Stack <SymbolTable> SymbolStack = new Stack <SymbolTable>();
	//private static int x = 0;
	
	public static void pushNewSymbolTable(String Scope) {
		SymbolTable tempTable = new SymbolTable(Scope, BlockScopeNum);
		SymbolStack.push(tempTable);
		if(Scope.equals("BLOCK"))
			BlockScopeNum++;
	}
	public static void popTopSymbolTable() {
		SymbolTable tempTable = SymbolStack.pop();
	}
	public static void addSymbol(String type, String name, String value) {
		SymbolTable tempTable = SymbolStack.pop();
		tempTable.insertNewVariable(type, name, value);
		SymbolStack.push(tempTable);
	}
	public static void printTopSymbolTable() {
		SymbolTable tempTable = SymbolStack.pop();
		tempTable.printTable();	
	}
}
