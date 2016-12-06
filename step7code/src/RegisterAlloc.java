import java.io.*;
import java.lang.*;
import java.util.*;

class RegisterAlloc {
	
	private List <ControlFlowGraph> cfgList = new ArrayList<ControlFlowGraph>();
	private List <IRNode> allocatedIRList = new ArrayList<IRNode>();

	public RegisterAlloc(List<ControlFlowGraph> cfgList) {
		this.cfgList = cfgList;
	}

	/*public List <IRNode> modifyIRCode() {
		
	}*/

}