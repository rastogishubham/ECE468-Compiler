import java.io.*;
import java.lang.*;
import java.util.*;

class RegisterAlloc {
	
	private List <ControlFlowGraph> cfgList = new ArrayList<ControlFlowGraph>();
	private List <IRNode> allocatedIRList = new ArrayList<IRNode>();

	public RegisterAlloc(List<ControlFlowGraph> cfgList) {
		this.cfgList = cfgList;
	}

	public List <IRNode> modifyIRCode() {
		int funcCount = 0;
		RegisterFile regFile = new RegisterFile(4);
		for(int i = 0; i < cfgList.size(); i++) {
			ControlFlowGraph cfg = cfgList.get(i);
			List <IRNode> statementWorkList = cfg.getStatementWorkList();
			HashMap<IRNode, ControlFlowNode> statementTable = cfg.getStatementTable();
			for(int j = 0; j < statementWorkList.size(); j++) {
				IRNode tempNode = statementWorkList.get(j);
				ControlFlowNode cfNode = statementTable.get(tempNode);
				String opcode = tempNode.getOpcode();
				String operand1 = tempNode.getOperand1();
				String operand2 = tempNode.getOperand2();
				String result = tempNode.getResult();
				String inst = tempNode.getNodeVal();

				System.out.println(";Instruction is: " + inst);

				if(opcode.contains("POP") || opcode.contains("READ")) {
					HashSet<String> outSet = cfNode.getOutSet();
					if(cfNode.getPredecessorList().size() > 1 || cfNode.getSuccessorList().size() > 1) {
						regFile.clearRegs(allocatedIRList, Listener.localVarList.get(funcCount), Listener.paramList.get(funcCount));
					}
					if(!operand1.equals("")) {
						int regNum = regFile.allocate(allocatedIRList, operand1, outSet, inst, Listener.localVarList.get(funcCount), Listener.paramList.get(funcCount));
						operand1 = "$T" + Integer.toString(regNum + 1);
						regFile.setDirty(regNum);
					}
					IRNode node = new IRNode(opcode, operand1, operand2, result);
					this.allocatedIRList.add(node);
				}
				else if(opcode.equals("LABEL") || opcode.equals("JUMP") || opcode.equals("RET")) {
					regFile.clearRegs(allocatedIRList, Listener.localVarList.get(funcCount), Listener.paramList.get(funcCount));
					this.allocatedIRList.add(tempNode);

				}

				else if(opcode.equals("GE") || opcode.equals("NE") || opcode.equals("LT") || opcode.equals("EQ") || opcode.equals("GT") || opcode.equals("LE")) {
					HashSet<String> outSet = cfNode.getOutSet();
					if(!operand1.equals("") && !operand1.matches("\\d+$") && !operand1.matches("\\d+\\.\\d+$")) {
						int regNum = regFile.ensure(allocatedIRList, operand1, outSet, inst, Listener.localVarList.get(funcCount), Listener.paramList.get(funcCount));
						operand1 = "$T" + Integer.toString(regNum + 1);
					}
					
					if(!operand2.equals("") && !operand2.matches("\\d+$") && !operand2.matches("\\d+\\.\\d+$")) {
						int regNum = regFile.ensure(allocatedIRList, operand2, outSet, inst, Listener.localVarList.get(funcCount), Listener.paramList.get(funcCount));
						operand2 = "$T" + Integer.toString(regNum + 1);
					}

					if(!result.equals("") && !result.contains("label")) {
						int regNum = regFile.allocate(allocatedIRList, result, outSet, inst, Listener.localVarList.get(funcCount), Listener.paramList.get(funcCount));
						result = "$T" + Integer.toString(regNum + 1);
						regFile.setDirty(regNum);
					}
					
					IRNode node = new IRNode(opcode, operand1, operand2, result);
					this.allocatedIRList.add(node);

					if(!outSet.contains(operand1) && !operand1.equals("") && !operand1.matches("\\d+$") && !operand1.matches("\\d+\\.\\d+$")) {
						int regNum = Integer.parseInt(operand1.replace("$T", "")) - 1;
						regFile.free(regNum, allocatedIRList, Listener.localVarList.get(funcCount), Listener.paramList.get(funcCount));
					}
					if(!outSet.contains(operand2) && !operand2.equals("") && !operand2.matches("\\d+$") && !operand2.matches("\\d+\\.\\d+$")) {
						int regNum = Integer.parseInt(operand2.replace("$T", "")) - 1;
						regFile.free(regNum, allocatedIRList, Listener.localVarList.get(funcCount), Listener.paramList.get(funcCount));
					}
					if(cfNode.getPredecessorList().size() > 1 || cfNode.getSuccessorList().size() > 1) {
						regFile.clearRegs(allocatedIRList, Listener.localVarList.get(funcCount), Listener.paramList.get(funcCount));
					}
					String jumpCode = "";
					if(opcode.equals("GE")) {
						jumpCode = "JGE";
						IRNode jumpNode = new IRNode(jumpCode, "", "", result);
						this.allocatedIRList.add(jumpNode);
					}
					else if(opcode.equals("LE")) {
						jumpCode = "JLE";
						IRNode jumpNode = new IRNode(jumpCode, "", "", result);
						this.allocatedIRList.add(jumpNode);
					}
					else if(opcode.equals("EQ")) {
						jumpCode = "JEQ";
						IRNode jumpNode = new IRNode(jumpCode, "", "", result);
						this.allocatedIRList.add(jumpNode);
					}
					else if(opcode.equals("GT")) {
						jumpCode = "JGT";
						IRNode jumpNode = new IRNode(jumpCode, "", "", result);
						this.allocatedIRList.add(jumpNode);
					}
					else if(opcode.equals("LT")) {
						jumpCode = "JLT";
						IRNode jumpNode = new IRNode(jumpCode, "", "", result);
						this.allocatedIRList.add(jumpNode);
					}
					else if(opcode.equals("NE")) {
						jumpCode = "JNE";
						IRNode jumpNode = new IRNode(jumpCode, "", "", result);
						this.allocatedIRList.add(jumpNode);
					}
				}

				else if(!opcode.contains("LABEL") && !opcode.contains("JSR") && !opcode.contains("JUMP") && !opcode.contains("LINK") && !opcode.contains("RET") && !opcode.equals("WRITES")) {
					HashSet<String> outSet = cfNode.getOutSet();
					if(!operand1.equals("") && !operand1.matches("\\d+$") && !operand1.matches("\\d+\\.\\d+$")) {
						int regNum = regFile.ensure(allocatedIRList, operand1, outSet, inst, Listener.localVarList.get(funcCount), Listener.paramList.get(funcCount));
						operand1 = "$T" + Integer.toString(regNum + 1);
					}
					
					if(!operand2.equals("") && !operand2.matches("\\d+$") && !operand2.matches("\\d+\\.\\d+$")) {
						int regNum = regFile.ensure(allocatedIRList, operand2, outSet, inst, Listener.localVarList.get(funcCount), Listener.paramList.get(funcCount));
						operand2 = "$T" + Integer.toString(regNum + 1);
					}

					if(!result.equals("") && !result.contains("label")) {
						int regNum = regFile.allocate(allocatedIRList, result, outSet, inst, Listener.localVarList.get(funcCount), Listener.paramList.get(funcCount));
						result = "$T" + Integer.toString(regNum + 1);
						regFile.setDirty(regNum);
					}
					
					IRNode node = new IRNode(opcode, operand1, operand2, result);
					this.allocatedIRList.add(node);

					if(!outSet.contains(operand1) && !operand1.equals("") && !operand1.matches("\\d+$") && !operand1.matches("\\d+\\.\\d+$")) {
						int regNum = Integer.parseInt(operand1.replace("$T", "")) - 1;
						regFile.free(regNum, allocatedIRList, Listener.localVarList.get(funcCount), Listener.paramList.get(funcCount));
					}
					if(!outSet.contains(operand2) && !operand2.equals("") && !operand2.matches("\\d+$") && !operand2.matches("\\d+\\.\\d+$")) {
						System.out.println(";Freeing b");
						int regNum = Integer.parseInt(operand2.replace("$T", "")) - 1;
						regFile.free(regNum, allocatedIRList, Listener.localVarList.get(funcCount), Listener.paramList.get(funcCount));
					}

				}
				else {
					if(cfNode.getPredecessorList().size() > 1 || cfNode.getSuccessorList().size() > 1) {
						regFile.clearRegs(allocatedIRList, Listener.localVarList.get(funcCount), Listener.paramList.get(funcCount));
					}
					this.allocatedIRList.add(tempNode);
				}

				HashSet<String> outSet = cfNode.getOutSet();

				System.out.println(";Outset: " + outSet);


				System.out.println(";Printing register file");

				for(Register r : regFile.getRegFile()) {
					System.out.println(";RegName: " + r.getRegName());
					System.out.println(";RegVal: " + r.getRegValue());
					System.out.println(";RegDirty: " + r.getDirty());
					System.out.println(";RegFree: " + r.getFree());
					System.out.println("\n");
				}
			
			}
			regFile.clearRegs(allocatedIRList, Listener.localVarList.get(funcCount), Listener.paramList.get(funcCount));
			funcCount++;
		}
		return this.allocatedIRList;
	}

}