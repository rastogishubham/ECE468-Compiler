import java.io.*;
import java.lang.*;
import java.util.*;

class Register {

	private String regName;
	private String regValue;
	private boolean dirty;
	private boolean free; 

	public Register(String regName) {
		this.regName = regName;
		this.dirty = false;
		this.free = false; 
		this.regValue = "";
	}
	public String getRegName() {
		return this.regName;
	}
	public String getRegValue() {
		return this.regValue;
	}
	public boolean getDirty() {
		return this.dirty;
	}
	public boolean getFree() {
		 return this.free; 
	}
	public void setRegName(String regName) {
		this.regName = regName;
	}
	public void setRegValue(String regValue) {
		this.regValue = regValue;
	}
	public void setDirty(boolean dirty) {
		this.dirty = dirty;
	}

	public void setFree(boolean isFree) {
		 this.free = isFree; 
	}
}