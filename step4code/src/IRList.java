import java.io.*;
import java.lang.*;
import java.util.*;

class IRList {
	private IRNode IR;
	private IRList next;

	public IRList() {
		this.IR = null;
		this.next = null;
	}
	public void creatNode(String Opcode, String Operand1, String Operand2, String Result) {
			IR = new IRNode(Opcode, Operand1, Operand2, Result);
			next = null;
	}
	public IRNode getIRNode() {
		return this.IR;
	}
	public void setIRNode(String Opcode, String Operand1, String Operand2, String Result) {
		this.IR.setOpcode(Opcode);
		this.IR.setOperand1(Operand1);
		this.IR.setOperand2(Operand2);
		this.IR.setResult(Result);
	}
	public IRList getNextNode() {
		return this.next;
	}
}