import java.io.*;
import java.lang.*;
import java.util.*;

class ControlFlowGraph {
	
	private HashMap<IRNode, ControlFlowNode> leaderTable = new HashMap<IRNode, ControlFlowNode>(); 
	private IRList irList = new IRList(); 
	private int end; 
	public ControlFlowGraph (List<IRNode> workList, IRList listIR, int end) {
		this.irList = listIR; 
		this.end = end; 
		if(workList.size() > 0) { 
			generateLeaderTable(workList);
			createSuccessorAndPredecessorList(workList);
			createStatementGraph(workList);
			printGraph(workList);
		}
	}

	public void createStatementGraph(List<IRNode> workList) {
		for(IRNode leaderNode : workList) {
			ControlFlowNode cfNode = new ControlFlowNode(leaderTable.get(leaderNode));
			leaderTable.remove(leaderNode);
			List <IRNode> instrList = cfNode.getInstrList();
			for(int i = 0; i < instrList.size(); i++) {
				ControlFlowNode instrCFNode = new ControlFlowNode();
				if(i == (instrList.size() - 1))
					instrCFNode.setSuccessorList(cfNode.getSuccessorList());
				else if(i == 0)
					instrCFNode.setPredecessorList(cfNode.getPredecessorList());
				else {
					instrCFNode.appendPredList(leaderTable.get)
				}

				instrCFNode.appendInstrList(instrList.get(i));
			}
		}
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
			ControlFlowNode cfnode = leaderTable.get(node);
			System.out.println("Printing the CFNode");
			cfnode.printCFNode();
			System.out.println();
			System.out.println("Printing the ADJ list");
			cfnode.printSuccessorList();
			System.out.println();
			System.out.println("Printing the Pred list");
			cfnode.printPredList();
		}
	}
}