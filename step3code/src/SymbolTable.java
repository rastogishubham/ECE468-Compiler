import java.util.*; 

public class SymbolTable {  

	private Hashtable<String, String> VariableTable = new Hashtable<String, String>(); 
	private List<Symbol> symbols = new ArrayList<String>();
	private scope;

	public SymbolTable(String scope){
		this.scope = scope;			
	}
	
	public void insertNewVariable(String type, String name, String value) { 
		String name_array [] = name.split(',');
		for(int i = 0; i < name_array.length(); i ++) {
			if(VaribleTable.containsKey(name_array[i]) {
				System.out.println("Error, variable already present");
			}
			else {
				symbols.add(new Symbol(type, name, value));
				VariableTable.put(name_array[i], type);
			}
		}
	}
} 
