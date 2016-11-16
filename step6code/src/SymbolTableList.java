import java.io.*;
import java.lang.*;
import java.util.*;

public class SymbolTableList {
	private int BlockScopeNum = 1;
	private List <SymbolTable> SymbolList = new ArrayList<SymbolTable>();
	
	public void pushNewSymbolTable(String Scope) {
		SymbolTable tempTable = new SymbolTable(Scope, BlockScopeNum);
		SymbolList.add(tempTable);
		if(Scope.equals("BLOCK")) {
			BlockScopeNum++;
		}
	}
	public void addSymbol(String type, String name, String value) {
		SymbolTable tempTable = SymbolList.get(SymbolList.size() - 1);
		tempTable.insertNewVariable(type, name, value, null);
		SymbolList.remove(SymbolList.size() - 1);
		SymbolList.add(tempTable);
	}
	public void addSymbolLocalParam(String type, String name, String value, String regType) {
		SymbolTable tempTable = SymbolList.get(SymbolList.size() - 1);
		tempTable.insertNewVariable(type, name, value, regType);
		SymbolList.remove(SymbolList.size() - 1);
		SymbolList.add(tempTable);
	}
	public void printSymbolList() {
		for(int i = 0; i < SymbolList.size(); i++) {
			SymbolTable tempTable = SymbolList.get(i);
			tempTable.printTable();
		}
	}
	public SymbolTable getSymbolTable(int index) {
		return this.SymbolList.get(index);
	}
	public SymbolTable getSymbolTable() {
		return this.SymbolList.get(this.SymbolList.size() - 1);
	}
	public int getListLen() {
		return this.SymbolList.size() - 1;
	}
}
