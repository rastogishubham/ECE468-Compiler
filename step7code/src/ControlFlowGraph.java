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
			createAdjacencyList(workList);
			printGraph(workList);
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

	public void createAdjacencyList(List<IRNode> workList) {
		for(int i = 0; i < workList.size(); i++) {
			List <ControlFlowNode> adjList = new ArrayList<ControlFlowNode>();
			IRNode node = workList.get(i);
			ControlFlowNode cfnode = leaderTable.get(node);
			IRNode lastNode = cfnode.getLastNode();
			String instruction = lastNode.getNodeVal();
			if(instruction.matches("JUMP label[0-9]+\\s+$")) {
				IRNode jumpIRNode = new IRNode("LABEL", lastNode.getOperand1(), "", "");
				int lineNum = Listener.labelTable.get("LABEL " + lastNode.getOperand1());
				jumpIRNode.setLineNum(lineNum);
				ControlFlowNode jumpNode = leaderTable.get(jumpIRNode);
				adjList.add(jumpNode);
				cfnode.setAdjacencyList(adjList);
				leaderTable.put(node, cfnode);
				jumpNode.appendPredList(cfnode);
				leaderTable.put(jumpIRNode, jumpNode);
			}
			else if(instruction.matches("(LE|LT|GE|GT|EQ|NE).*$")) {
				IRNode branchIRNode = new IRNode("LABEL", lastNode.getResult(), "", "");
				int lineNum = Listener.labelTable.get("LABEL " + lastNode.getResult());
				branchIRNode.setLineNum(lineNum);
				ControlFlowNode branchNode = leaderTable.get(branchIRNode);
				adjList.add(branchNode);
				if(i + 1 < workList.size()) {
					IRNode nextNode = workList.get(i + 1);
					ControlFlowNode nextCFNode = leaderTable.get(nextNode);
					adjList.add(nextCFNode);
					nextCFNode.appendPredList(cfnode);
					leaderTable.put(nextNode, nextCFNode);
				}
				cfnode.setAdjacencyList(adjList);
				leaderTable.put(node, cfnode);
				branchNode.appendPredList(cfnode);
				leaderTable.put(branchIRNode, branchNode);
			}
			else if(i + 1 < workList.size()) {
				IRNode nextNode = workList.get(i + 1);
				ControlFlowNode nextCFNode = leaderTable.get(nextNode);
				adjList.add(nextCFNode);
				cfnode.setAdjacencyList(adjList);
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
			cfnode.printADJList();
			System.out.println();
			System.out.println("Printing the Pred list");
			cfnode.printPredList();
		}
	}
}