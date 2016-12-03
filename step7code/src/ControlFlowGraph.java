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
			
			/*for(IRNode node : workList) {


				node.printNode();
			}*/

			generateLeaderTable(workList);
		//	System.out.println("Map \n");
		//	printMap();
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
			System.out.print("Pooji partner can not compile");
			node.printCFNode();
			leaderTable.put(workList.get(i), node); 
		}
		node = new ControlFlowNode();

		instrList = lastInstrList(workList.get(i), irList.getIRNode(), irList); 
		node.setInstrList(instrList);
		leaderTable.put(workList.get(i), node);
		System.out.print("Pooji partner can not compile");
			node.printCFNode();
	}
	public List<IRNode> createInstrList (IRNode leader1, IRNode leader2, IRList irList) { 
		List<IRNode> instrList = new ArrayList<IRNode>();
		IRNode temp;
		for (int i = leader1.getLineNum(); i < leader2.getLineNum(); i++) {
			 temp = irList.getIRNode(i-1);
			 instrList.add(temp); 
		}

		return instrList; 
	}

	public List<IRNode> lastInstrList (IRNode leader1, IRNode leader2 ,IRList irList) { 
		List<IRNode> instrList = new ArrayList<IRNode>();
		IRNode temp; 
		for (int i = leader1.getLineNum(); i <= end; i++) {
			 temp = irList.getIRNode(i-1); 
			 instrList.add(temp); 
		}

		return instrList; 
	}

	public void printMap() {
		System.out.println("Printing control flow group");
		for(Map.Entry<IRNode, ControlFlowNode> entry : leaderTable.entrySet()) {
			IRNode tempNode = entry.getKey();
			ControlFlowNode cfnode = entry.getValue();
			System.out.println("Printing leader node: ");
			tempNode.printNode();
			cfnode.printCFNode();

		}
	}

}