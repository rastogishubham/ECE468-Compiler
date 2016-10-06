import java.util.*; 

public class SymbolTable { 

	public SymbolTable(){ 
		Hashtable<String, String> keys = new Hashtable<String, String>(); 
		List<Symbol> symbols = new ArrayList<String>
			
	}  
	
	public Symbol getSymbol(int i){ 
		symbols.get(i); 
	}
	 
	
	public void addSymbol( String type, String name, String value) { 
		symbols.add(new Symbol(type, name, value));
	}
	
	public addHashKey(String key) { 
		keys.put(key, "garbage"); 
	}
} 
