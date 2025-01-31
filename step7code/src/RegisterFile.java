import java.io.*;
import java.lang.*;
import java.util.*;

class RegisterFile {
	private List <Register> regFile = new ArrayList<Register>();
	private int regNum;
	private Hashtable <String, String> spillTable = new Hashtable <String, String>();
	public RegisterFile(int regNum) {
		for(int i = 0; i < regNum; i++) {
			Register reg = new Register("r" + Integer.toString(i));
			this.regFile.add(reg);
		}
		this.regNum = regNum;
	}

	public void clearRegs(List<IRNode> allocatedIRList, int localVarCount, int paramCount) {

		for(int i = this.regNum - 1; i >= 0; i--) { 
			if(regFile.get(i).getDirty())
				free(i, allocatedIRList, localVarCount, paramCount);
		}

		this.regFile.clear();
		for(int i = 0; i < this.regNum; i++) {
			Register reg = new Register("r" + Integer.toString(i));
			this.regFile.add(reg);
		}
	}

	public int ensure(List <IRNode> allocatedIRList, String operand, HashSet<String> outSet, String inst, int localVarCount, int paramCount) { // checks whether the register 
		for(int i = this.regNum - 1; i >= 0; i--) {
			if(regFile.get(i).getRegValue().equals(operand)) {
				return i;
			}
		}
		int regNumber = allocate(allocatedIRList, operand, outSet, inst, localVarCount, paramCount);
		IRNode tempNode;

		if(spillTable.containsKey(operand)) {
			if(Listener.typeTable.containsKey(operand)) {
				String storeOp = "STORE";
				if(Listener.typeTable.get(operand).equals("INT"))
					storeOp += "I";
				else
					storeOp += "F";
				tempNode = new IRNode(storeOp, spillTable.get(operand), "", "$T" + Integer.toString(regNumber + 1));
			}	
			else
				tempNode = new IRNode("STOREF", spillTable.get(operand), "", "$T" + Integer.toString(regNumber + 1));
		}
		else if(operand.contains("$T")) { 
			int regNum = Integer.parseInt(operand.replace("$T", ""));
			int stackVal = localVarCount + regNum;
			tempNode = new IRNode("STOREF", "$-" + Integer.toString(stackVal), "", "$T" + Integer.toString(regNumber + 1));
		}
		else {
			if(Listener.typeTable.containsKey(operand)) {
				String storeOp = "STORE";
				if(Listener.typeTable.get(operand).equals("INT"))
					storeOp += "I";
				else
					storeOp += "F";
				tempNode = new IRNode(storeOp, operand, "", "$T" + Integer.toString(regNumber + 1));
			}	
			else
				tempNode = new IRNode("STOREF", operand, "", "$T" + Integer.toString(regNumber + 1));
		}
		allocatedIRList.add(tempNode);
		return regNumber;
	}
	public int allocate(List <IRNode> allocatedIRList, String operand, HashSet<String> outSet, String inst, int localVarCount, int paramCount) { 
		int fileSize = this.regFile.size(); 
		boolean isFree = false; 
		int allocateIndex = 0; 
		for(int i = fileSize - 1; i >= 0; i--) { // if a register is available sets allocate index
			if(regFile.get(i).getFree() == true && !isFree) {  //isFree
				allocateIndex = i; 
				isFree = true;
				break;
			} 
		}

		if (!isFree){ // if all the registers are taken check outset to spill registers
			for(int i = 0; i < regFile.size(); i++) { 
				Register regfromFile = regFile.get(i);
				String op = regfromFile.getRegValue(); 
				if(!outSet.contains(op) && !isFree){ 
					isFree = true; 
					allocateIndex = i; 
				}
			}
			free(allocateIndex, allocatedIRList, localVarCount, paramCount);
		}

		String name = "r" + Integer.toString(allocateIndex); 
		Register reg = new Register(name); 
		reg.setRegValue(operand);
		reg.setDirty(false);
		reg.setFree(false);
		regFile.set(allocateIndex, reg);

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

	public void free(int freeRegNum, List<IRNode> allocatedIRList, int localVarCount, int paramCount) {
		
		Register freeReg = regFile.get(freeRegNum);

		if(freeReg.getDirty()) {
			String regValue = freeReg.getRegValue();
			String regName = freeReg.getRegName();
			int stackVal;
			IRNode tempNode;

			if(regValue.contains("$T")) {
				int regNum = Integer.parseInt(regValue.replace("$T", ""));
				stackVal = localVarCount + regNum;

				tempNode = new IRNode("STOREF", regName, "", "$-" + Integer.toString(stackVal));//"$T" + Integer.toString(freeRegNum + 1), "", regValue);
				spillTable.put(regValue, "$-" + Integer.toString(stackVal));
			}
			else if(regValue.contains("$L")) {
				stackVal = Integer.parseInt(regValue.replace("$L", ""));
				tempNode = new IRNode("STOREF", regName, "", "$-" + Integer.toString(stackVal));
				spillTable.put(regValue, "$-" + Integer.toString(stackVal));
			}
			else if(regValue.contains("$P")) {
				stackVal = Integer.parseInt(regValue.replace("$P", ""));
				tempNode = new IRNode("STOREF", regName, "", "$" + Integer.toString(6 + paramCount - stackVal));
				spillTable.put(regValue, "$" + Integer.toString(6 + paramCount - stackVal));
			}
			else {
				tempNode = new IRNode("STOREF", regName, "", regValue);
				spillTable.put(Integer.toString(Integer.parseInt(regName.replace("r", "")) + 1), regValue);
			}
			allocatedIRList.add(tempNode);
		}
		freeReg.setFree(true);
		freeReg.setDirty(false);
		freeReg.setRegValue("");
	} 

	public void setDirty(int regNum) {
		Register reg = regFile.get(regNum);
		reg.setDirty(true);
		regFile.set(regNum, reg);
	}

	public List <Register> getRegFile() {
		return this.regFile;
	}
}