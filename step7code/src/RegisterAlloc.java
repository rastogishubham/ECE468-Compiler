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

				if(opcode.contains("STORE")) {
					HashSet<String> outSet = cfNode.getOutSet();
					if(!operand1.equals("")) {
						int regNum = regFile.ensure(allocatedIRList, operand1, outSet, Listener.localVarList.get(funcCount), Listener.paramList.get(funcCount), 1);
					}
					if(!operand2.equals("")) {
						int regNum = regFile.ensure(allocatedIRList, operand2, outSet, Listener.localVarList.get(funcCount), Listener.paramList.get(funcCount), 1);
					}
					if(!result.equals("")) {
						int regNum = regFile.allocate(allocatedIRList, result, outSet, Listener.localVarList.get(funcCount), Listener.paramList.get(funcCount));
					}
				}

				else if(!opcode.contains("LABEL") && !opcode.contains("JSR") && !opcode.contains("JUMP") && !opcode.contains("LINK") && !opcode.contains("RET")) {
					HashSet<String> outSet = cfNode.getOutSet();
					if(!operand1.equals("")) {
						int regNum = regFile.ensure(allocatedIRList, operand1, outSet, Listener.localVarList.get(funcCount), Listener.paramList.get(funcCount), 0);
						operand1 = "$T" + Integer.toString(regNum + 1);
					}
					if(!operand2.equals("")) {
						int regNum = regFile.ensure(allocatedIRList, operand2, outSet, Listener.localVarList.get(funcCount), Listener.paramList.get(funcCount), 0);
						operand2 = "$T" + Integer.toString(regNum + 1);
					}
					if(!result.equals("") && !result.contains("label")) {
						int regNum = regFile.allocate(allocatedIRList, result, outSet, Listener.localVarList.get(funcCount), Listener.paramList.get(funcCount));
						result = "$T" + Integer.toString(regNum + 1);
					}

					IRNode node = new IRNode(opcode, operand1, operand2, result);
					this.allocatedIRList.add(node);
				}
				else {
					this.allocatedIRList.add(tempNode);
				}	
			}
			funcCount++;
		}
		return this.allocatedIRList;
	}

}