import java.io.*;
import java.lang.*;
import java.util.*;

class ControlFlowNode {

	private List<IRNode> instrList = new ArrayList<IRNode>();
	private List<ControlFlowNode> successorList = new ArrayList<ControlFlowNode>();
	private List<ControlFlowNode> predecessorList = new ArrayList<ControlFlowNode>();

	public void getPredecessorList() {
		return this.predecessorList
	}

	public void setPredecessorList(List<ControlFlowNode> predList) {
		this.predecessorList = predList;
	}

	public void appendPredList(ControlFlowNode cfNode) {
		this.predecessorList.add(cfNode);
	}

	public void setInstrList (List<IRNode> list) { 
		this.instrList = list; 
	}

	public void appendInstrList(IRNode tempNode) {
		this.instrList.add(tempNode);
	}

	public IRNode getFirstNode() {
		return this.instrList.get(0);
	}

	public List<IRNode> getSuccessorList () { 
		return this.successorList;
	}

	public void setSuccessorList (List<ControlFlowNode> succList) { 
		this.successorList = succList;
	}

	public void printCFNode() {
		for(IRNode node : this.instrList) {
			node.printNode();
		}
	}

	public IRNode getLastNode() {
		return this.instrList.get(this.instrList.size() - 1);
	}

	public void printSuccessorList() {

		for(ControlFlowNode cfnode : this.successorList) {
			IRNode node = cfnode.getFirstNode();
			node.printNode();
		}
	}

	public void printPredList() {

		for(ControlFlowNode cfnode : this.predecessorList) {
			IRNode node = cfnode.getFirstNode();
			node.printNode();
		}
	}

	public List<IRNode> getInstrList() {
		return this.instrList();
	}
}