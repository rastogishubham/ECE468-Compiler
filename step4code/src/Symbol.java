public class Symbol { 
	private String type; 
	private String value; 

	public Symbol(String typeIn, String valueIn) {  
		this.type = typeIn;  
		this.value = valueIn; 
	}
	public String getType() { 
		return type; 
	}
	public void setType(String tempType) { 
		this.type  = tempType; 
	}
	public String getValue() { 
		return value; 
	}
	public String setValue(String setValue) {  
		return value; 
	}
}
