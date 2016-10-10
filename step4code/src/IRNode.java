import java.io.*;
import java.lang.*;
import java.util.*;

class IRNode {
	private String Opcode;
	private String Operand1;
	private String Operand2;
	private String Result;
	
	public IRNode(String Opcode, String Operand1, String Operand2, String Result) {
			this.Opcode = Opcode;
			this.Operand1 = Operand1;
			this.Operand2 = Operand2;
			this.Result = Result;
	}

	public String getOpcode() {
		return this.Opcode;
	}
	public void setOpcode(String Opcode) {
		this.Opcode = Opcode;
	}
	public String getOperand1() {
		return this.Operand1;
	}
	public void setOperand1(String Operand1) {
		this.Opcode = Operand1;
	}
	public String getOperand2() {
		return this.Operand2;
	}
	public void setOperand2(String Operand2) {
		this.Opcode = Operand2;
	}
	public String getResult() {
		return this.Result;
	}
	public void setResult(String Result) {
		this.Opcode = Result;
	}
}
