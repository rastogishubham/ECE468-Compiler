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

	public int ensure(List <IRNode> allocatedIRList, String operand, HashSet<String> outSet, int localVarCount, int paramCount) { // checks whether the register 
		for(int i = 0; i < this.regNum; i++) {
			if(regFile.get(i).getRegValue().equals(operand))
				return i;
		}
		int regNum = allocate(allocatedIRList, operand);
		IRNode tempNode = new IRNode("STOREF", operand, "$T" + Integer.toString(regNum + 1));

	}
	public int allocate(List <IRNode> allocatedIRList, String operand, HashSet<String> outSet, int localVarCount, int paramCount) { 
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
	public int isFree(HashSet<String> outSet) {
		for(int i = 0; i < this.regFile.size(); i++) {
			Register tempReg = regFile.get(i);
			int tempDirty = tempReg.getDirty();
			if(!tempDirty && !outSet.contains(tempReg.getRegValue()))
				return i;
		}
		return -1;
	}


	public void free(HashSet<String> outSet, Register freeReg, List<IRNode> allocatedIRList, int localVarCount, int paramCount) {
		if(freeReg.getDirty() && outSet.contains(freeReg.getRegValue)) {
			String regValue = freeReg.getRegValue();
			String regName = freeReg.getRegName();
			int stackVal;
			IRNode tempNode;

			if(regValue.contains("$T")) {
				int regNum = Integer.parseInt(regValue.replace("$T", ""));
				stackVal = localVarCount + regNum;
				tempNode = new IRNode("STOREF", regValue, "$-" + Integer.toString(stackVal));
			}
			else if(regValue.contains("$L")) {
				stackVal = Integer.parseInt(regValue.replace("$L", ""));
				tempNode = new IRNode("STOREF", regValue, "$-" + Integer.toString(stackVal));
			}
			else if(regValue.contains("$P")) {
				stackVal = Integer.parseInt(regValue.replace("$P", ""));
				tempNode = new IRNode("STOREF", regValue, "$" + Integer.toString(6 + paramCount - stackVal));
			}
			else {
				tempNode = new IRNode("STOREF", Interger.parseInt(regName.replace("r", "")) + 1, regValue;
			}
			allocatedIRList.add(tempNode);
		}
	} 
}