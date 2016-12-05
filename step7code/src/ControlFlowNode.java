import java.io.*;
import java.lang.*;
import java.util.*;

class ControlFlowNode {

	private List<IRNode> instrList = new ArrayList<IRNode>();
	private List<ControlFlowNode> successorList = new ArrayList<ControlFlowNode>();
	private List<ControlFlowNode> predecessorList = new ArrayList<ControlFlowNode>();

	public ControlFlowNode() {
		this.instrList = new ArrayList<IRNode>();
		this.successorList = new ArrayList<ControlFlowNode>();
		this.predecessorList = new ArrayList<ControlFlowNode>();
	}

	public ControlFlowNode(ControlFlowNode cfNode) {
		this.instrList = cfNode.getInstrList();
		this.successorList = cfNode.getSuccessorList();
		this.predecessorList = cfNode.getPredecessorList();
	}

	public List<ControlFlowNode> getPredecessorList() {
		return this.predecessorList;
	}

	public void clearSuccessorList() {
		this.successorList.clear();
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
	
	public List<ControlFlowNode> getSuccessorList () { 
		return this.successorList;
	}

	public void setSuccessorList (List<ControlFlowNode> succList) { 
		this.successorList = succList;
	}

	public void appendSuccList(ControlFlowNode cfNode) {
		this.successorList.add(cfNode);
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
			for(IRNode tempNode : cfnode.getInstrList()) {
				//IRNode node = cfnode.getFirstNode();
				tempNode.printNode();
			}
		}
	}

	public void printPredList() {

		for(ControlFlowNode cfnode : this.predecessorList) {
			for(IRNode tempNode : cfnode.getInstrList()) {
				//IRNode node = cfnode.getFirstNode();
				tempNode.printNode();
			}
		}
	}

	public List<IRNode> getInstrList() {
		return this.instrList;
	}
}