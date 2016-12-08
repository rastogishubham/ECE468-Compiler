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

	public void clearRegs(HashSet<String> outSet, List<IRNode> allocatedIRList, int localVarCount, int paramCount) {

		for(int i = 0; i < this.regNum; i++) { 
			if(regFile.get(i).getDirty())
				free(outSet, i, allocatedIRList, localVarCount, paramCount);
		}

		this.regFile.clear();
		for(int i = 0; i < this.regNum; i++) {
			Register reg = new Register("r" + Integer.toString(i));
			this.regFile.add(reg);
		}
	}

	public int ensure(List <IRNode> allocatedIRList, String operand, HashSet<String> outSet, int localVarCount, int paramCount, int noStore, String inst) { // checks whether the register 
		for(int i = this.regNum - 1; i >= 0; i--) {
			if(regFile.get(i).getRegValue().equals(operand)) {
				return i;
			}
		}
		int regNumber = allocate(allocatedIRList, operand, outSet, localVarCount, paramCount, inst, false);
		IRNode tempNode;

		if(spillTable.containsKey(operand)) {
			tempNode = new IRNode("STOREF", spillTable.get(operand), "", "$T" + Integer.toString(regNumber + 1));
		}
		else if(operand.contains("$T")) { 
			int regNum = Integer.parseInt(operand.replace("$T", ""));
			int stackVal = localVarCount + regNum;
			tempNode = new IRNode("STOREF", "$-" + Integer.toString(stackVal), "", "$T" + Integer.toString(regNumber + 1));
		}
		else {
			tempNode = new IRNode("STOREF", operand, "", "$T" + Integer.toString(regNumber + 1));
		}
		allocatedIRList.add(tempNode);
		//if(noStore == 0) {
		//	IRNode tempNode = new IRNode("STOREF", operand, "", "$T" + Integer.toString(regNumber + 1));
		//	allocatedIRList.add(tempNode);
		// }
		return regNumber;
	}
	public int allocate(List <IRNode> allocatedIRList, String operand, HashSet<String> outSet, int localVarCount, int paramCount, String inst, boolean setDirty) { 
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
					allocateIndex = i;  // register index to spill 
				//	System.out.println(";allocateIndex: " + allocateIndex);
				}
			}
			free(outSet, allocateIndex, allocatedIRList, localVarCount, paramCount);
		}

		String name = "r" + Integer.toString(allocateIndex); 
		Register reg = new Register(name); 
		reg.setRegValue(operand);
		if(setDirty == true)
			reg.setDirty(true);
		else
			reg.setDirty(false); 
		
		reg.setFree(false);
		regFile.set(allocateIndex, reg);


		System.out.println(";Instruction is: " + inst);

		System.out.println(";Outset: " + outSet);


		System.out.println(";Printing register file");

		for(Register r : regFile) {
			System.out.println(";RegName: " + r.getRegName());
			System.out.println(";RegVal: " + r.getRegValue());
			System.out.println(";RegDirty: " + r.getDirty());
			System.out.println(";RegFree: " + r.getFree());
			System.out.println("\n");
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

	public void free(HashSet<String> outSet, int freeRegNum, List<IRNode> allocatedIRList, int localVarCount, int paramCount) {
		
		Register freeReg = regFile.get(freeRegNum);

		if(freeReg.getDirty()) { //&& outSet.contains(freeReg.getRegValue())) {
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
	} 

	public List <Register> getRegFile() {
		return this.regFile;
	}
}