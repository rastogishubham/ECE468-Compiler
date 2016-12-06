import java.io.*;
import java.lang.*;
import java.util.*;

class RegisterFile {
	private List <Register> regFile = new ArrayList<Register>();
	private int regNum;
	public RegisterFile(int regNum) {
		for(int i = 0; i < regNum; i++) {
			Register reg = new Register("r" + Integer.toString(i));
			this.regFile.add(reg);
		}
		this.regNum = regNum;
	}

	public Register getRegister(int index) {
		 return regFile.get(index); 
	}

	public int ensure(List <IRNode> allocatedIRList, String operand) { // checks whether the register 
		for(int i = 0; i < this.regNum; i++) {
			if(regFile.get(i).getRegValue().equals(operand))
				return i;
		}
		int regNum = allocate(allocatedIRList, operand);
	}
	public int allocate(List <IRNode> allocatedIRList, String operand) { 
		int fileSize = this.regFile.getSize(); 
		int isFree = 0; 
		for(int i = 0; i < fileSize; i++) {
			if(regFile.getFree()) {  
				isFree = i; 
			} 
		}

		if(isFree != 0) {

		}
	}
	//public int 


	public void free(Register r, List<String> outSet) {
		if(r.getDirty()) {
			for(String var : outSet) {
				String register = r.getRegister() + r.getDirty();
				if(register.equals(var)) {
					r.setFree(true); 
		 		 	r.setRegName(""); 
		 		 	r.setRegValue(""); 
		 		}
		 	}
		}
	} 
}