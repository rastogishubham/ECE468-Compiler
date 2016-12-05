import java.io.*;
import java.lang.*;
import java.util.*;

class ControlFlowNode {

	private List<IRNode> instrList = new ArrayList<IRNode>();
	private List<ControlFlowNode> successorList = new ArrayList<ControlFlowNode>();
	private List<ControlFlowNode> predecessorList = new ArrayList<ControlFlowNode>();
	private HashSet<String> genSet = new HashSet<String>();
	
	private HashSet<String> inSet = new HashSet<String>(); 
	private HashSet<String> outSet = new HashSet<String>(); 

	private HashSet<String> killSet = new HashSet<String>();

	public ControlFlowNode() {
	}

	public ControlFlowNode(IRNode node) {
		this.instrList.add(node);
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
		return this.instrList;
	}

	public void appendGenSet(String var) {
		this.genSet.add(var);
	}

	public void setGenSet(HashSet<String> genSet) {
		this.genSet = genSet;
	}

	public HashSet<String> getGenSet()  {
		return this.genSet;
	}

	public void appendKillSet(String var) {
		this.killSet.add(var);
	}

	public void setKillSet(HashSet<String> killSet) {
		this.killSet = killSet;
	}

	public HashSet<String> getKillSet()  {
		return this.killSet;
	}

	public void printGenSet() {
		System.out.println(this.genSet);
	}

	public void printKillSet() {
		System.out.println(this.killSet);
	}

	public void setInSet(HashSet<String> set) {
		 this.inSet = set; 
	}

	public void setOutSet(HashSet<String> set) {
		 this.outSet = set; 
	}

	public HashSet<String> getInSet() { 
		return this.inSet;
	}

	public HashSet<String> getOutSet() {
		return this.outSet; 
	}

	public void printInSet() {
		System.out.println(this.inSet);
	}

	public void printOutSet() {
		System.out.println(this.outSet);
	}
}