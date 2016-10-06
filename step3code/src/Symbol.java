public class Symbol { 
	String type; 
	String name; 
	String value; 

	public Symbol( String typeIn, String nameIn, String valueIn){  
		type = typeIn; 
		name = nameIn; 
		value = valueIn; 
	}

	public String getType() { 
		return type; 
	}

	public void setType(String tempType) { 
		type  = tempType; 
	}
	
	public String getName() { 
		return name; 
	}
	public void setName(String  tempName) { 
		name = tempName; 
	}
	
	public String getValue() { 
		return value; 
	}
	
	public void setValue(String setValue) {  
		return value; 
	}
}