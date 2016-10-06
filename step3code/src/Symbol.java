public class Symbol { 
	private String type; 
	//private String name; 
	private String value; 

	public Symbol(String typeIn, String valueIn) {  
		this.type = typeIn; 
	//	this.name = nameIn; 
		this.value = valueIn; 
	}

	public String getType() { 
		return type; 
	}

	public void setType(String tempType) { 
		this.type  = tempType; 
	}
	
	/*public String getName() { 
		return name; 
	}
	public void setName(String tempName) { 
		this.name = tempName; 
	}*/
	
	public String getValue() { 
		return value; 
	}
	
	public void setValue(String setValue) {  
		return value; 
	}
}
