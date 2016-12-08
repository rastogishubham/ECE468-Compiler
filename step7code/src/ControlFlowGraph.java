import java.io.*;
import java.lang.*;
import java.util.*;

class ControlFlowGraph {
	
	private HashMap<IRNode, ControlFlowNode> leaderTable = new HashMap<IRNode, ControlFlowNode>();
	private HashMap<IRNode, ControlFlowNode> statementTable = new HashMap<IRNode, ControlFlowNode>();
	private IRList irList = new IRList(); 
	private int end; 
	private List<IRNode> statementWorkList = new ArrayList<IRNode>();
	 
	public ControlFlowGraph (List<IRNode> workList, IRList listIR, int end) {
		this.irList = listIR; 
		this.end = end; 
		if(workList.size() > 0) {
			generateLeaderTable(workList);
			createSuccessorAndPredecessorList(workList);
			this.statementWorkList = createStatementGraph(workList);
			createLivenessSet(statementWorkList);
		}
	}

	public List<IRNode> getStatementWorkList() {
		return this.statementWorkList;
	}

	public HashMap<IRNode, ControlFlowNode> getStatementTable() {
		return this.statementTable;
	}

	public List<IRNode> createStatementGraph(List<IRNode> workList) {
		List <IRNode> newWorkList = new ArrayList<IRNode>();
		for(IRNode leaderNode : workList) {
			ControlFlowNode cfNode = new ControlFlowNode(leaderTable.get(leaderNode));
			List <IRNode> instrList = cfNode.getInstrList();
			for(int i = 0; i < instrList.size(); i++) {
				ControlFlowNode instrCFNode = new ControlFlowNode(instrList.get(i));
				if(i != 0 && i == (instrList.size() - 1)) {
					if(!instrList.get(i - 1).getNodeVal().matches("RET\\s+$"))
						instrCFNode.appendPredList(statementTable.get(instrList.get(i - 1)));
				}
				else if(i == 0) {
					List<ControlFlowNode> cfPredList = cfNode.getPredecessorList();
					for(ControlFlowNode tempNode : cfPredList) {
						IRNode lastNode = tempNode.getLastNode();
						if(!lastNode.getNodeVal().matches("RET\\s+$")) {
							ControlFlowNode predNode = statementTable.get(lastNode);
							if(predNode != null) {
								instrCFNode.appendPredList(predNode);
							}
							else {
								instrCFNode.appendPredList(leaderTable.get(lastNode));
							}
						}
					}
				}
				else {
					if(!instrList.get(i - 1).getNodeVal().matches("RET\\s+$"))
						instrCFNode.appendPredList(statementTable.get(instrList.get(i - 1)));
				}
				//instrCFNode.appendInstrList(instrList.get(i));
				statementTable.put(instrList.get(i), instrCFNode);
				newWorkList.add(instrList.get(i));
			}
		}
		for(IRNode leaderNode : workList) {
			ControlFlowNode cfNode = new ControlFlowNode(leaderTable.get(leaderNode));
			List <IRNode> instrList = cfNode.getInstrList();
			for(int i = 0; i < instrList.size(); i++) {
				IRNode instrNode = instrList.get(i);
				String instruction = instrNode.getNodeVal();
				ControlFlowNode newNode = statementTable.get(instrNode);
				if(!instruction.matches("RET\\s+$") && (i == instrList.size() - 1)) {
					for(ControlFlowNode tempNode : cfNode.getSuccessorList()) {
						IRNode succIRNode = tempNode.getFirstNode();
						ControlFlowNode succNode = statementTable.get(succIRNode);
						newNode.appendSuccList(succNode);
					}
				}
				else if(!instruction.matches("RET\\s+$")) {
					IRNode succNode = instrList.get(i + 1);
					ControlFlowNode statementNode = statementTable.get(succNode);
					newNode.appendSuccList(statementNode);
				}
				List <HashSet<String>> genAndKill = new ArrayList<HashSet<String>>(); 
				genAndKill = createGenAndKill(instrList.get(i));
				newNode.setGenSet(genAndKill.get(0));
				newNode.setKillSet(genAndKill.get(1));
				statementTable.put(instrNode, newNode);
			}
		}
		return newWorkList; 
	}

	public void generateLeaderTable (List<IRNode> workList) {
		List<IRNode> instrList = new ArrayList<IRNode>();
		ControlFlowNode node = new ControlFlowNode(); 
		int i = 0;
		for(i = 0; i < workList.size() - 1; i++) {
			node = new ControlFlowNode();
			IRNode tempNode = workList.get(i);
			instrList = createInstrList(workList.get(i), workList.get(i+1), irList); 
			node.setInstrList(instrList);
			leaderTable.put(workList.get(i), node); 
		}
		node = new ControlFlowNode();
		instrList = lastInstrList(workList.get(i), irList.getIRNode(), irList); 
		node.setInstrList(instrList);
		leaderTable.put(workList.get(i), node);
	}

	public List<IRNode> createInstrList(IRNode leader1, IRNode leader2, IRList irList) { 
		List<IRNode> instrList = new ArrayList<IRNode>();
		IRNode temp;
		for (int i = leader1.getLineNum(); i < leader2.getLineNum(); i++) {
			 temp = irList.getIRNode(i-1);
			 instrList.add(temp); 
		}
		return instrList; 
	}

	public List<IRNode> lastInstrList(IRNode leader1, IRNode leader2, IRList irList) { 
		List<IRNode> instrList = new ArrayList<IRNode>();
		IRNode temp; 
		for (int i = leader1.getLineNum(); i < this.end; i++) {
			 temp = irList.getIRNode(i-1); 
			 instrList.add(temp); 
		}
		return instrList; 
	}

	public void createSuccessorAndPredecessorList(List<IRNode> workList) {
		for(int i = 0; i < workList.size(); i++) {
			List <ControlFlowNode> successorList = new ArrayList<ControlFlowNode>();
			IRNode node = workList.get(i);
			ControlFlowNode cfnode = leaderTable.get(node);
			IRNode lastNode = cfnode.getLastNode();
			String instruction = lastNode.getNodeVal();
			if(instruction.matches("JUMP label[0-9]+\\s+$")) {
				IRNode jumpIRNode = new IRNode("LABEL", lastNode.getOperand1(), "", "");
				int lineNum = Listener.labelTable.get("LABEL " + lastNode.getOperand1());
				jumpIRNode.setLineNum(lineNum);
				ControlFlowNode jumpNode = leaderTable.get(jumpIRNode);
				successorList.add(jumpNode);
				cfnode.setSuccessorList(successorList);
				leaderTable.put(node, cfnode);
				jumpNode.appendPredList(cfnode);
				leaderTable.put(jumpIRNode, jumpNode);
			}
			else if(instruction.matches("(LE|LT|GE|GT|EQ|NE).*$")) {
				IRNode branchIRNode = new IRNode("LABEL", lastNode.getResult(), "", "");
				int lineNum = Listener.labelTable.get("LABEL " + lastNode.getResult());
				branchIRNode.setLineNum(lineNum);
				ControlFlowNode branchNode = leaderTable.get(branchIRNode);
				successorList.add(branchNode);
				if(i + 1 < workList.size()) {
					IRNode nextNode = workList.get(i + 1);
					ControlFlowNode nextCFNode = leaderTable.get(nextNode);
					successorList.add(nextCFNode);
					nextCFNode.appendPredList(cfnode);
					leaderTable.put(nextNode, nextCFNode);
				}
				cfnode.setSuccessorList(successorList);
				leaderTable.put(node, cfnode);
				branchNode.appendPredList(cfnode);
				leaderTable.put(branchIRNode, branchNode);
			}
			else if(i + 1 < workList.size()) {
				IRNode nextNode = workList.get(i + 1);
				ControlFlowNode nextCFNode = leaderTable.get(nextNode);
				successorList.add(nextCFNode);
				cfnode.setSuccessorList(successorList);
				leaderTable.put(node, cfnode);
				nextCFNode.appendPredList(cfnode);
				leaderTable.put(nextNode, nextCFNode);
			}
		}
	}

	public void printGraph(List<IRNode> workList) {
		for(int i = 0; i < workList.size(); i++) {
			IRNode node = workList.get(i);
			ControlFlowNode cfnode = statementTable.get(node);
			System.out.println();
			System.out.println();
			System.out.println("Printing the CFNode");
			cfnode.printCFNode();
			System.out.println("Printing the Succ list");
			cfnode.printSuccessorList();
			System.out.println("Printing the Pred list");
			cfnode.printPredList();
			System.out.println("Printing the Gen Set");
			cfnode.printGenSet();
			System.out.println("Printing the Kill Set");
			cfnode.printKillSet();
			System.out.println("Printing In Set");
			cfnode.printInSet();
			System.out.println("Printing Out Set");
			cfnode.printOutSet();
			System.out.println();
			System.out.println();
		}
	}

	public void printBlockGraph(List<IRNode> workList) {
		for(int i = 0; i < workList.size(); i++) {
			IRNode node = workList.get(i);
			ControlFlowNode cfnode = leaderTable.get(node);
			System.out.println("Printing the CFNode");
			cfnode.printCFNode();
			System.out.println("Printing the Succ list");
			cfnode.printSuccessorList();
			System.out.println("Printing the Pred list");
			cfnode.printPredList();
		}
	}

	public List<HashSet<String>> createGenAndKill(IRNode node) {
		List<HashSet<String>> genAndKill = new ArrayList<HashSet<String>>(); 
		HashSet <String> genSet = new HashSet<String>();
		HashSet <String> killSet = new HashSet<String>();
		String opcode = node.getOpcode();
		String operand1 = node.getOperand1(); 
		String operand2 = node.getOperand2(); 
		String result = node.getResult(); 
		if(opcode.contains("STORE")) {

			if(!operand1.matches("^\\d+"))
				genSet.add(operand1);
			if(!result.matches("^\\d+"))
				killSet.add(result);
		}
		else if(opcode.contains("POP") && !operand1.equals("") && !operand1.matches("^\\d+")) 
			killSet.add(operand1);
		else if(opcode.contains("PUSH") && !operand1.equals("") && !operand1.matches("^\\d+"))
			genSet.add(operand1);
		else if(opcode.contains("WRITE") && !operand1.matches("^\\d+"))
			genSet.add(operand1);
		else if(opcode.contains("READ") && !operand1.matches("^\\d+"))
			killSet.add(operand1);
		else if(opcode.contains("JSR")) {
			SymbolTable tempTable = Listener.SymbolList.getSymbolTable(0);
			Hashtable<String, Symbol> variableTable = tempTable.getVariableTable();
			for(String vars : variableTable.keySet())
				genSet.add(vars);
		}
		else if(opcode.matches("(NE)|(LE)|(EQ)|(GT)|(LT).*")) {
			if(!operand1.matches("^\\d+"))
				genSet.add(operand1);
			if(!operand2.matches("^\\d+"))
				genSet.add(operand2);
		}
		else if(opcode.contains("RET") || opcode.contains("JUMP") || opcode.contains("LABEL") || opcode.contains("LINK")) {
		}
		else {
			if(!operand1.matches("^\\d+") && !operand1.equals(""))
				genSet.add(operand1);
			if(!operand2.matches("^\\d+") && !operand2.equals("")) 
				genSet.add(operand2);
			if(!result.matches("^\\d+") && !result.equals(""))
				killSet.add(result); 
		}
		genAndKill.add(genSet); 
		genAndKill.add(killSet); 

		return genAndKill;
	}

	public void createLivenessSet (List<IRNode> statementWorkList) {

		boolean isEqual = false;

		for(int i = statementWorkList.size() - 1 ; i >= 0 ; i--) { 
			HashSet<String> inSet = new HashSet<String>(); 
			HashSet<String> outSet = new HashSet<String>(); 
			IRNode irnode = statementWorkList.get(i); 
			ControlFlowNode node = statementTable.get(irnode); 
			
			List<ControlFlowNode> successorList = node.getSuccessorList(); 

			for(ControlFlowNode cfNode : successorList) {
				outSet.addAll(cfNode.getInSet()); 
			} 

			inSet.addAll(outSet);
			inSet.removeAll(node.getKillSet());
			inSet.addAll(node.getGenSet());

			if(irnode.getNodeVal().matches("RET\\s+$")) {
				SymbolTable tempTable = Listener.SymbolList.getSymbolTable(0);
				Hashtable<String, Symbol> variableTable = tempTable.getVariableTable();
				for(String vars : variableTable.keySet())
					outSet.add(vars);
				inSet.addAll(outSet);
				inSet.removeAll(node.getKillSet());
				inSet.addAll(node.getGenSet());
			}

			 node.setInSet(inSet);
			 node.setOutSet(outSet); 
		}

		while(isEqual != true) {
			isEqual = true;
			for(int i = statementWorkList.size() - 1 ; i >= 0 ; i--) {
				HashSet<String> inSet = new HashSet<String>(); 
				HashSet<String> outSet = new HashSet<String>();
				IRNode irnode = statementWorkList.get(i); 
				ControlFlowNode node = statementTable.get(irnode);

				List<ControlFlowNode> successorList = node.getSuccessorList();
				for(ControlFlowNode cfNode : successorList) {
					outSet.addAll(cfNode.getInSet()); 
				}
				inSet.addAll(outSet);
				inSet.removeAll(node.getKillSet());
				inSet.addAll(node.getGenSet());

				if(irnode.getNodeVal().matches("RET\\s+$")) {
					SymbolTable tempTable = Listener.SymbolList.getSymbolTable(0);
					Hashtable<String, Symbol> variableTable = tempTable.getVariableTable();
					for(String vars : variableTable.keySet())
						outSet.add(vars);
					inSet.addAll(outSet);
					inSet.removeAll(node.getKillSet());
					inSet.addAll(node.getGenSet());
				}

				if(!node.getInSet().equals(inSet) || !node.getOutSet().equals(outSet))
					isEqual = false;
				node.setInSet(inSet);
			 	node.setOutSet(outSet); 
			}
		}
	}
}