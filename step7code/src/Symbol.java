public class Symbol { 
	private String type; 
	private String value; 
	private String tempName;

	public Symbol(String typeIn, String valueIn, String tempName) {  
		this.type = typeIn;  
		this.value = valueIn; 
		this.tempName = tempName;
	}
	public String getType() { 
		return this.type; 
	}
	public void setType(String tempType) { 
		this.type  = tempType; 
	}
	public String getValue() { 
		return this.value; 
	}
	public String setValue(String setValue) {  
		return this.value; 
	}
	public String getTempName() { 
		return this.tempName;
	}
	public void setTempName(String name) { 
		this.tempName = name; 
	}
}
