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
	
	public void insertNewVariable(String type, String name, String value, String regType) { 
		String [] name_array = name.split(",");
		String tempValue = "";
		for(int i = 0; i < name_array.length; i ++) {
			if(regType != null) {
				if(regType.equals("P")) {
					tempValue = Integer.toString(Listener.paramNum);
					Listener.paramNum += 1;
				}
				else if(regType.equals("L")) {
					tempValue = Integer.toString(Listener.localNum);
					Listener.localNum += 1;
				}
			}
			String tempName = "$" + regType + tempValue;
			if(VariableTable.containsKey(name_array[i])) {
				System.out.println("DECLARATION ERROR " + name_array[i]);
				throw new RuntimeException();
			}
			else {
				Symbol tempSymbol = new Symbol(type, value, tempName);
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
				System.out.println("name " + tempName + " type " + tempSymbol.getType() + " tempReg " + tempSymbol.getTempName());
			else
				System.out.println("name " + tempName + " type " + tempSymbol.getType() + " value " + tempSymbol.getValue() + " tempReg " + tempSymbol.getTempName());
    		}
			System.out.println();
	}

	public List<String> getNameList() {
		return this.nameList;
	}

	public String getScope() {
		return this.scope;
	}
} 
