import java.io.*;
import java.lang.*;
import java.util.*;

class IRList {
	
	private List <IRNode> ListIR = new ArrayList<IRNode>();

	public void appendIRNode(String Opcode, String Operand1, String Operand2, String Result) {
			IRNode IR = new IRNode(Opcode, Operand1, Operand2, Result);
			ListIR.add(IR);
	}
	public void appendIRNode(IRNode tempNode) {
			ListIR.add(tempNode);
	}
	public IRNode getIRNode(int index) {
		return ListIR.get(index);
	}
	public IRNode getIRNode() {
		return ListIR.get(ListIR.size() - 1);
	}
	public void printList() {
		for(int i = 0; i < this.ListIR.size(); i ++) {
			IRNode tempNode = ListIR.get(i);
			tempNode.printNode();
		}
	}
	public int getSize() { 
		return ListIR.size();
	}
	public void clear() {
		this.ListIR.clear();
	}
	public List <IRNode> getList() {
		return this.ListIR;
	}
	public void addAll(List <IRNode> tempList) {
		this.ListIR.addAll(tempList);
	}
}