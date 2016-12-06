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

	public int ensure(List <IRNode> allocatedIRList, String operand, HashSet<String> outSet, int localVarCount, int paramCount, int noStore) { // checks whether the register 
		for(int i = 0; i < this.regNum; i++) {
			if(regFile.get(i).getRegValue().equals(operand))
				return i;
		}
		int regNumber = allocate(allocatedIRList, operand, outSet, localVarCount, paramCount);
		if(noStore == 0) {
			IRNode tempNode = new IRNode("STOREF", operand, "", "$T" + Integer.toString(regNumber + 1));
			allocatedIRList.add(tempNode);
		}
		return regNumber;
	}
	public int allocate(List <IRNode> allocatedIRList, String operand, HashSet<String> outSet, int localVarCount, int paramCount) { 
		int fileSize = this.regFile.size(); 
		boolean isFree = false; 
		int allocateIndex = 0; 
		for(int i = 0; i < fileSize; i++) { // if a register is available sets allocate index
			if(regFile.get(i).getFree() && !isFree) {  //isFree
				allocateIndex = i; 
				isFree = true;
				break;
			} 
		}

		String name = "r" + Integer.toString(allocateIndex); 
		Register reg = new Register(name); 
		reg.setRegValue(operand); 
		reg.setDirty(true); 
		reg.setFree(false);
		regFile.set(allocateIndex, reg);

		if (!isFree){ // if all the registers are taken check outset to spill registers

			for(Register regfromFile : regFile) { 
				String op = regfromFile.getRegValue(); 
				if(!outSet.contains(op) && !isFree){ 
					isFree = true; 
					allocateIndex = regFile.indexOf(regfromFile);  // register index to spill 
				}
			}
			free(outSet, regFile.get(allocateIndex), allocatedIRList, localVarCount, paramCount);
		}
	 	return allocateIndex;
	}
	public int isFree(HashSet<String> outSet) {
		for(int i = 0; i < this.regFile.size(); i++) {
			Register tempReg = regFile.get(i);
			boolean tempDirty = tempReg.getDirty();
			if(!tempDirty && !outSet.contains(tempReg.getRegValue()))
				return i;
		}
		return -1;
	}


	public void free(HashSet<String> outSet, Register freeReg, List<IRNode> allocatedIRList, int localVarCount, int paramCount) {
		if(freeReg.getDirty() && outSet.contains(freeReg.getRegValue())) {
			String regValue = freeReg.getRegValue();
			String regName = freeReg.getRegName();
			int stackVal;
			IRNode tempNode;

			if(regValue.contains("$T")) {
				int regNum = Integer.parseInt(regValue.replace("$T", ""));
				stackVal = localVarCount + regNum;
				tempNode = new IRNode("STOREF", regValue, "", "$-" + Integer.toString(stackVal));
			}
			else if(regValue.contains("$L")) {
				stackVal = Integer.parseInt(regValue.replace("$L", ""));
				tempNode = new IRNode("STOREF", regValue, "", "$-" + Integer.toString(stackVal));
			}
			else if(regValue.contains("$P")) {
				stackVal = Integer.parseInt(regValue.replace("$P", ""));
				tempNode = new IRNode("STOREF", regValue, "", "$" + Integer.toString(6 + paramCount - stackVal));
			}
			else {
				tempNode = new IRNode("STOREF", Integer.toString(Integer.parseInt(regName.replace("r", "")) + 1), "", regValue);
			}
			allocatedIRList.add(tempNode);
		}
	} 
}