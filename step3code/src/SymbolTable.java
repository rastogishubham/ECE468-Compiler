import java.util.*; 

public class SymbolTable {  

	private Hashtable<String, Symbol> VariableTable = new Hashtable<String, Symbol>(); 
	private String scope;

	public SymbolTable(String scope, int BlockScopeNum){
		this.scope = scope;
		if(scope.equals("BLOCK"))
			this.scope = scope + Integer.toString(BlockScopeNum);
	}
	
	public void insertNewVariable(String type, String name, String value) { 
		String [] name_array = name.split(",");
		for(int i = 0; i < name_array.length; i ++) {
			if(VariableTable.containsKey(name_array[i])) {
				System.out.println("Error, variable with same name already present");
			}
			else {
				Symbol tempSymbol = new Symbol(type, value);
				VariableTable.put(name_array[i], tempSymbol);
			}
		}
	}
} 
