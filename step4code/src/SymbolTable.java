import java.util.*; 

public class SymbolTable {  

	private Hashtable<String, Symbol> VariableTable = new Hashtable<String, Symbol>(); 
	private String scope;
	private List <String> nameList = new ArrayList<String>();

	public SymbolTable(String scope, int BlockScopeNum) {
		this.scope = scope;
		if(scope.equals("BLOCK"))
			this.scope = scope + " " + Integer.toString(BlockScopeNum);
	}
	
	public void insertNewVariable(String type, String name, String value) { 
		String [] name_array = name.split(",");
		for(int i = 0; i < name_array.length; i ++) {
			if(VariableTable.containsKey(name_array[i])) {
				System.out.println("DECLARATION ERROR " + name_array[i]);
				throw new RuntimeException();
			}
			else {
				Symbol tempSymbol = new Symbol(type, value);
				VariableTable.put(name_array[i], tempSymbol);
				nameList.add(name_array[i]);
			}
		}
	}
	public void printTable() {
		System.out.println("Symbol table " + this.scope);
		for(int i = 0; i < this.nameList.size(); i++) {
			String tempName = nameList.get(i);
			Symbol tempSymbol = VariableTable.get(tempName);
			if(!tempSymbol.getType().equals("STRING"))
				System.out.println("name " + tempName + " type " + tempSymbol.getType());
			else
				System.out.println("name " + tempName + " type " + tempSymbol.getType() + " value " + tempSymbol.getValue());
    		}
			System.out.println();
	}
} 
