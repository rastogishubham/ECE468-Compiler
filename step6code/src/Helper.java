import java.io.*;
import java.lang.*;
import java.util.*;

public class Helper {
	
	public IRList generateFuncCall(String funcCall, String lhs) {
		IRList tempList = new IRList();
		String args = funcCall.split("\\(")[1].split("\\)")[0];
		String func_name = funcCall.split("\\(")[0];
		String [] argList = args.split(",");
		tempList.appendIRNode("PUSH", "", "", "");
		IRList retList = new IRList();
		IRList finalList = new IRList();
		for(int i = 0; i < argList.length; i++) {
			if(!argList[i].matches("(\\d+(?:\\.\\d+)?$)|([A-Za-z]+$)")) {
				retList = parseExp(argList[i], lhs);
				finalList.addAll(retList.getList());
				tempList.appendIRNode("PUSH", "$T" + Integer.toString(Listener.tempRegNum - 1), "", "");
			}
			else {
				String oldArg = argList[i];
				argList[i] = getTempRegName(argList[i]);
				if(argList[i].contains("null"))
					argList[i] = oldArg;
				tempList.appendIRNode("PUSH", argList[i], "", "");
			}
		}
		tempList.appendIRNode("JSR", func_name, "", "");
		for(int i = 0; i < argList.length; i++) {
			tempList.appendIRNode("POP", "", "", "");
		}
		tempList.appendIRNode("POP", "$T" + Integer.toString(Listener.tempRegNum), "", "");
		String resultReg = getTempRegName(lhs);
		if(lhs.contains("null"))
			resultReg = lhs;
		if(Listener.typeTable.get(lhs).equals("FLOAT")) {
			tempList.appendIRNode("STOREF", "$T" + Integer.toString(Listener.tempRegNum), "", resultReg);
		}
		else {
			tempList.appendIRNode("STOREI", "$T" + Integer.toString(Listener.tempRegNum), "", resultReg);
		}
		finalList.addAll(tempList.getList());
		Listener.tempRegNum += 1; 
		return finalList;
	}

	public IRList parseExp(String expression, String result) {
		ExpressionStack expstack = new ExpressionStack();
		String expr = expstack.createExprStack(expression);
		PemdasTree pdt = new PemdasTree();
		Node node = pdt.createBinaryTree(expr);
		IRList tempList = new IRList();

		if(Listener.typeTable.containsKey(result)) {

			if(Listener.typeTable.get(result).equals("INT")) {
				tempList = pdt.inOrderTraverse(tempList, node);
			}
			else {
				tempList = pdt.inOrderTraverseFloat(tempList, node);
			}
		}
		else {
			if(Listener.typeTable.equals("INT")) {
				tempList = pdt.inOrderTraverse(tempList, node);
			}
			else {
				tempList = pdt.inOrderTraverseFloat(tempList, node);
			}
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
            tempTable = Listener.SymbolList.getSymbolTable(pos);
            scope = tempTable.getScope();
            varTable = tempTable.getVariableTable();
        }
        return null;
    }

}