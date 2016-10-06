import java.util.*; 

public class SymbolTable {  

	private Hashtable<String, Symbol> VariableTable = new Hashtable<String, Symbol>(); 
	private scope;

	public SymbolTable(String scope){
		this.scope = scope;			
	}
	
	public void insertNewVariable(String type, String name, String value) { 
		String name_array [] = name.split(',');
		for(int i = 0; i < name_array.length(); i ++) {
			if(VaribleTable.containsKey(name_array[i]) {
				throw RunTimeException("Error, variable already present");
			}
			else {
				Symbol tempSymbol = new tempSymbol(type, value);
				VariableTable.put(name_array[i], tempSymbol);
			}
		}
	}
} 
