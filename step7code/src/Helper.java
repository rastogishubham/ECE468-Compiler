import java.io.*;
import java.lang.*;
import java.util.*;

public class Helper {
	
	public IRList generateFuncCall(String funcCall, String lhs) {
		IRList tempList = new IRList();
		String func_name = funcCall.split("\\(")[0];
		List <String> argList = createArgList(funcCall);
		tempList.appendIRNode("PUSH", "", "", "");

		IRList retList = new IRList();
		IRList finalList = new IRList();
		String lhsType = Listener.typeTable.get(lhs);
		lhsType = (lhsType == null) ? "null" : lhsType;
		String newLhs = "";
		if(lhsType.equals("null"))
			newLhs = lhs;
		else
			newLhs = lhsType;
		for(int i = 0; i < argList.size(); i++) {
			String currArg = argList.get(i);
			
			if(!currArg.matches("(\\d+(?:\\.\\d+)?$)|([A-Za-z]+$)") && !currArg.matches("\\w+\\(.*\\)$")) {
				retList = parseExp(currArg, lhs, 0);
				finalList.addAll(retList.getList());
				tempList.appendIRNode("PUSH", "$T" + Integer.toString(Listener.tempRegNum - 1), "", "");
			}
			else if(currArg.matches("\\w+\\(.*\\)$")) {
				retList = generateFuncCall(currArg, newLhs);
				finalList.addAll(retList.getList());
				tempList.appendIRNode("PUSH", "$T" + Integer.toString(Listener.tempRegNum - 1), "", "");
			}
			else {
				String oldArg = currArg;
				currArg = getTempRegName(currArg);
				if(currArg.contains("null"))
					currArg = oldArg;
				tempList.appendIRNode("PUSH", currArg, "", "");
			}
		}
		
		tempList.appendIRNode("JSR", func_name, "", "");
		for(int i = 0; i < argList.size(); i++) {
			tempList.appendIRNode("POP", "", "", "");
		}
		tempList.appendIRNode("POP", "$T" + Integer.toString(Listener.tempRegNum), "", "");
		String resultReg = getTempRegName(lhs);
		if(lhs.contains("null"))
			resultReg = lhs;
		if(lhsType.equals("FLOAT"))
			tempList.appendIRNode("STOREF", "$T" + Integer.toString(Listener.tempRegNum), "", resultReg);
		else if(lhsType.equals("INT"))
			tempList.appendIRNode("STOREI", "$T" + Integer.toString(Listener.tempRegNum), "", resultReg);
		finalList.addAll(tempList.getList());
		Listener.tempRegNum += 1; 
		return finalList;
	}

	public IRList parseExp(String expression, String result, int isRetType) {
		ExpressionStack expstack = new ExpressionStack();
		String expr = expstack.createExprStack(expression);
		PemdasTree pdt = new PemdasTree();
		Node node = pdt.createBinaryTree(expr);
		IRList tempList = new IRList();
		String resultType = Listener.typeTable.get(result);
		resultType = (resultType == null) ? "null" : resultType;
		String storeOpcode = "STORE";
		String dataType = "";
		if(result.equals("INT") || resultType.equals("INT")) 
			dataType = "I";
		else
			dataType = "F";
		storeOpcode += dataType;
		if(resultType.equals("null"))
			resultType = result;
		
		if(node.getLeftNode() == null && node.getRightNode() == null) {
			if(expression.matches("\\d+(?:\\.\\d+)?$")) {
				tempList.appendIRNode(storeOpcode, expression, "", "$T" + Integer.toString(Listener.tempRegNum));
			}
			else if(expression.matches("\\w+\\(.*\\)$")) {
				tempList = generateFuncCall(expression, resultType);
				Listener.tempRegNum -= 1;
				//tempList.appendIRNode(storeOpcode, "$T" + Integer.toString(Listener.tempRegNum), )
			}
			else {
				String tempReg = getTempRegName(expression);
				if(tempReg.contains("null"))
					tempReg = expression;
				tempList.appendIRNode(storeOpcode, tempReg, "", "$T" + Integer.toString(Listener.tempRegNum));
			}
			if(isRetType == 1)
				tempList.appendIRNode(storeOpcode, "$T" + Integer.toString(Listener.tempRegNum), "", "$R");
			Listener.tempRegNum += 1;
		}
		else {
			tempList = pdt.inOrderTraverse(tempList, node, dataType);
		}
		return tempList;
	}

	public String getTempRegName(String operand) {
        SymbolTable tempTable = Listener.SymbolList.getSymbolTable();
        String scope = tempTable.getScope();
        Hashtable <String, Symbol> varTable = tempTable.getVariableTable();
        int pos = Listener.SymbolList.getListLen();
        while(pos >= 0) {
            if(!scope.equals("GLOBAL") && !scope.contains("BLOCK"))
                pos = 1;
            if(varTable.containsKey(operand)) {
                Symbol tempSymbol = varTable.get(operand);
                return tempSymbol.getTempName();
            }
            pos--;
            if(pos >= 0) {
            	tempTable = Listener.SymbolList.getSymbolTable(pos);
            	scope = tempTable.getScope();
            	varTable = tempTable.getVariableTable();
        	}
        }
        return "null";
    }
    private List<String> createArgList(String args) {
        String replaceExp = args;
        int subPos = 0;
        int numParen = 0;
        int closeParenPos = 0;
        int openParenPos = 0;
        List <String> argList = new ArrayList <String> ();
        for(int i = 0; i < args.length(); i++) {
            String curr = Character.toString(replaceExp.charAt(i));
            String prev = "";
            if(i == args.length())
                break;
            if(i > 0)
                prev = Character.toString(replaceExp.charAt(i - 1));
            
            if(prev.matches("[A-Za-z0-9]") && curr.equals("(")) {
                closeParenPos = findCloseParenPos(replaceExp, i);
                openParenPos = i;
                break;
            }
        }
        subPos = openParenPos + 1;
        for(int i = openParenPos + 1; i < closeParenPos; i++) {
        	String curr = Character.toString(replaceExp.charAt(i));
	        if(curr.equals("("))
	            numParen++;
	        else if(curr.equals(")") && numParen > 0)
	        	numParen--;
	        if(curr.equals(",") && numParen == 0) {
                argList = addtoList(replaceExp, subPos, i, argList);
                subPos = i + 1;
            }
        }
        if(subPos < replaceExp.length())
            argList.add(replaceExp.substring(subPos, replaceExp.length() - 1));
       	return argList;
    }
    private List<String> addtoList(String expression, int begin, int end, List<String> argList) {
        if(!expression.substring(begin, end).equals(""))
            argList.add(expression.substring(begin, end));
        return argList;
    }
    
    private int findCloseParenPos(String expression, int openPos) {
        int numParen = 0;
        for(int i = openPos + 1; i < expression.length(); i++) {
            String curr = Character.toString(expression.charAt(i));
            if(curr.equals("("))
                numParen++;
            if(curr.equals(")")) {
                if(numParen == 0)
                    return i;
                else
                    numParen--;
            }
        }
        return 0;
    }

    public String findReturnType(String expression) {
    	ExpressionStack expstack = new ExpressionStack();
    	List <String> tokenList = expstack.getTokenList();
    	for(int i = 0; i < tokenList.size(); i++) {
    		String token = tokenList.get(i);
    		if(token.matches("\\d+$"))
    			return "INT";
    		else if(token.matches("\\d+\\.\\d+$"))
    			return "FLOAT";
    		else if(token.matches("[A-Za-z][A-Za-z0-9]{0,30}$"))
    			return Listener.typeTable.get(token);
    		else if(token.matches("[A-Za-z][A-Za-z0-9]{0,30}\\(.*$"))
    			return Listener.funcTable.get(token);
    	}
    	return "null";
    }

    public void createLeaderSet(IRList tempList) {
    	IRNode tempNode = new IRNode("", "", "", "");
    	for(int i = 0; i < tempList.getSize(); i++) {
    		tempNode = tempList.getIRNode(i);
    		String instruction = tempNode.getNodeVal();

    		if(instruction.matches("LABEL [A-Za-z][A-Za-z0-9]{0,30}\\s+$") && !instruction.matches("LABEL label[0-9]+\\s+$")) {
    			List<IRNode> workList = createWorkList(Listener.leaderSet);
    			ControlFlowGraph cfg = new ControlFlowGraph(workList, tempList, tempNode.getLineNum());
    			Listener.cfgList.add(cfg);
    			Listener.leaderSet.clear();
    			Listener.leaderSet.add(tempNode);
    		}
    		else if(instruction.matches("(LE|LT|GE|GT|EQ|NE).*$")) {
    			IRNode targetNode = new IRNode("LABEL", tempNode.getResult(), "", "");
    			targetNode.setLineNum(Listener.labelTable.get("LABEL " + tempNode.getResult()));
    			Listener.leaderSet.add(targetNode);
    			if(i < tempList.getSize()) {
    				Listener.leaderSet.add(tempList.getIRNode(i + 1));
    			}
    		}
    		else if(instruction.matches("JUMP.*$")) {
    			IRNode targetNode = new IRNode("LABEL", tempNode.getOperand1(), "", "");
    			targetNode.setLineNum(Listener.labelTable.get("LABEL " + tempNode.getOperand1()));
    			Listener.leaderSet.add(targetNode);
    		}
    	}
    	List<IRNode> workList = createWorkList(Listener.leaderSet);
    	ControlFlowGraph cfg = new ControlFlowGraph(workList, tempList, tempNode.getLineNum());
    	Listener.cfgList.add(cfg);
    }

    public List<IRNode> createWorkList(Set<IRNode> leaderSet) {

    	List<IRNode> leaderList = new ArrayList<IRNode>(leaderSet);
    	Collections.sort(leaderList, new Comparator<IRNode>() {
    		@Override
    		public int compare(IRNode irnode1, IRNode irnode2) {
    			return irnode1.getLineNum() - irnode2.getLineNum();
    		}
    	});

    	return leaderList;
    }

    public void printSet() {
    	for(IRNode node : Listener.leaderSet) {
    		System.out.println("Set value is: " + node.getLineNum() + " " +  node.getNodeVal());
    	}
    	System.out.println();
    }

    public void printSet(Set<IRNode> leaderSet) {
    	for(IRNode node : leaderSet) {
    		System.out.println("Set value is: " + node.getLineNum() + " " +  node.getNodeVal());
    	}
    	System.out.println();
    }

    public List<IRList> enumerateProg(List <IRList> listIR) {
    	int lineNum = 1;
    	List <IRList> flatList = new ArrayList <IRList>();
    	IRList flatIRList = new IRList();
    	for(int i = 0; i < listIR.size(); ++i) {
    		IRList tempList = listIR.get(i);
    		for(int j = 0; j < tempList.getSize(); ++j) {
    			IRNode tempNode = tempList.getIRNode(j);

    			if(tempNode.getOperand1().matches("label[0-9]+$") && tempNode.getOpcode().equals("LABEL")) {
    				Listener.labelTable.put("LABEL " + tempNode.getOperand1(), lineNum);
    			}
    			tempNode.setLineNum(lineNum);
    			tempList.setIRNode(j, tempNode);

    			++lineNum;
    		}
    		flatIRList.addAll(tempList.getList());
    	}
    	flatList.add(flatIRList);
    return flatList;
    }
}