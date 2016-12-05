import java.io.*;
import java.lang.*;
import java.util.*;

public class Listener extends MicroBaseListener {
	
	public static SymbolTableList SymbolList = new SymbolTableList();

	private String Var_type;
	
	public static int tempRegNum = 1;
	public static int labelNum = 1;
	public static int paramNum = 1;
	public static int localNum = 1;

	public int retType = 0;

	public static List <String> funcList = new ArrayList<String>();
	private TinyConverter tiny = new TinyConverter(); 
	private List <IRList> ListIR = new ArrayList<IRList>();
	public static Hashtable <String, String> typeTable = new Hashtable<String, String>();
	public static Hashtable <String, String> funcTable = new Hashtable<String, String>();
	private Hashtable <String, String> logOperatorTable = new Hashtable<String, String>();
	private Stack <String> labelStack = new Stack<String>();
	private Stack <String> exitLabelStack = new Stack<String>();
	private Hashtable <String, String> registerTypeTable = new Hashtable<String, String>();
	public static Set <IRNode>  leaderSet = new HashSet <IRNode>();
	public static List <ControlFlowGraph> cfgList = new ArrayList <ControlFlowGraph>();
	public static Hashtable <String, Integer> labelTable = new Hashtable <String, Integer> ();

	public Listener() {
		createLogicalTable();
	}

	private void createLogicalTable() {
		logOperatorTable.put(">", "LE");
		logOperatorTable.put(">=", "LT");
		logOperatorTable.put("<", "GE");
		logOperatorTable.put("<=", "GT");
		logOperatorTable.put("!=", "EQ");
		logOperatorTable.put("=", "NE");
	}
	@Override
	public void enterPgm_body(MicroParser.Pgm_bodyContext ctx) {
		Listener.SymbolList.pushNewSymbolTable("GLOBAL");	
	}
	@Override
	public void exitPgm_body(MicroParser.Pgm_bodyContext ctx) {
		SymbolTable tempTable = Listener.SymbolList.getSymbolTable(0);
		List nameList = tempTable.getNameList();
		Hashtable<String, Symbol> tempVarTable = tempTable.getVariableTable();
		Helper help = new Helper();

		ListIR = help.enumerateProg(ListIR);

		System.out.println("Printing List: ");
		System.out.println();

		for(IRList list : ListIR) {
			for(IRNode node : list.getList()) {
				node.printNode();
			}
		}

		for(int i = 0; i < ListIR.size(); i++) {
			IRList tempList = ListIR.get(i);
			help.createLeaderSet(tempList);
		}

		System.out.println("\n\nLength of cfgList: " + Listener.cfgList.size());

		for(int i = 0; i < nameList.size(); i ++) {
			String varType = tempVarTable.get(nameList.get(i)).getType();
			if(varType.equals("STRING")) {
				String value = tempVarTable.get(nameList.get(i)).getValue();
				System.out.println("str " + nameList.get(i) + " " + value);
			}
			else {
				System.out.println("var " + nameList.get(i));
			}
		}
		for(int i = 0; i < ListIR.size(); i++) {
			IRList tempList = ListIR.get(i);
			tiny.printTinyCode(tempList);
		}
		System.out.println("end");
	}
	@Override
	public void enterString_decl(MicroParser.String_declContext ctx) {
		String name = ctx.getText().split(":=")[0].split("STRING")[1];
		String Value = ctx.getText().split(":=")[1].split(";")[0];
		SymbolTable tempTable = Listener.SymbolList.getSymbolTable();
		if(!tempTable.getScope().equals("GLOBAL")) {
			Listener.SymbolList.addSymbolLocalParam("STRING", name, Value, "L");
		}
		else {
			Listener.SymbolList.addSymbol("STRING", name, Value);
		}
		Listener.typeTable.put(name, "STRING");
	}
	@Override
	public void exitVar_decl(MicroParser.Var_declContext ctx) {
		String name_list = ctx.getText().split("(FLOAT)|(INT)")[1].split(";")[0];
		SymbolTable tempTable = Listener.SymbolList.getSymbolTable();
		if(!tempTable.getScope().equals("GLOBAL")) {
			Listener.SymbolList.addSymbolLocalParam(Var_type, name_list, null, "L");
		}
		else {
			Listener.SymbolList.addSymbol(Var_type, name_list, null);
		}
	}
	@Override
	public void enterVar_type(MicroParser.Var_typeContext ctx) {
		Var_type = ctx.getText();
	}
	@Override
	public void exitParam_decl(MicroParser.Param_declContext ctx) {
		String name = ctx.getText().split("(FLOAT)|(INT)")[1];
		Listener.SymbolList.addSymbolLocalParam(Var_type, name, null, "P");
		String paramlist = ctx.getChild(1).getText();
		String [] param_list = paramlist.split(",");
		 for(int i = 0; i < param_list.length; i ++) {
		 	Listener.typeTable.put(param_list[i], ctx.getChild(0).getText());
		 }
	}
	@Override
	public void enterFunc_decl(MicroParser.Func_declContext ctx) {
		Listener.paramNum = 1;
		localNum = 1;
		Listener.tempRegNum = 1;
		String funcRetVal = ctx.getChild(1).getText();
		if(funcRetVal.equals("FLOAT"))
			retType = 2;
		else if(funcRetVal.equals("INT"))
			retType = 1;
		else
			retType = 0;
		String name = ctx.getText().split("FUNCTION")[1].split("(FLOAT)|(VOID)|(INT)")[1].split("\\(")[0];
		Listener.SymbolList.pushNewSymbolTable(name);

		IRList tempList = new IRList();
		funcList.add(name);
		funcTable.put(name, funcRetVal);
		tempList.appendIRNode("LABEL", name, "", "");
		tempList.appendIRNode("LINK", "", "", "");
		tempList.printList();
		ListIR.add(tempList);
	}
	@Override 
	public void enterIf_stmt(MicroParser.If_stmtContext ctx) {

		Listener.SymbolList.pushNewSymbolTable("BLOCK");
		String expression = ctx.getChild(2).getText();
		String operand = "";
		String val = "";
		String operator = "";
		String labelName = "label" + Integer.toString(labelNum);
		IRList tempList = new IRList();
		String storeOpcode = "STORE";
		if(!expression.equals("TRUE")) {
			String [] expressionArray = expression.split("((?=[!<>=])|(?<=[!<>=]))");
			operand = expressionArray[0];
			val = expressionArray[expressionArray.length - 1];
			if(expressionArray.length == 4) 
				operator = expressionArray[1] + expressionArray[2];
			else
				operator = expressionArray[1];

		}
		if(Listener.typeTable.containsKey(operand)) {
			if(Listener.typeTable.get(operand).equals("INT"))
				storeOpcode += "I";
			else
				storeOpcode += "F";

			if(val.matches("(\\d+(?:\\.\\d+)?$)|([A-Za-z]+$)")) {
				tempList.appendIRNode(storeOpcode, val, "", ("$T" + Integer.toString(Listener.tempRegNum)));
			}
			else {
				Helper Help = new Helper();
				tempList = Help.parseExp(val, operand, 0);
			}
			String operand_old = operand;
			Helper Help = new Helper();
			operand = Help.getTempRegName(operand);
			if(operand.contains("null"))
				operand = operand_old;
			tempList.appendIRNode(logOperatorTable.get(operator), operand, ("$T" + Integer.toString(Listener.tempRegNum)), labelName);
		}
		else if(operand.equals("")) {
			tempList.appendIRNode("STOREI", "1", "", ("$T" + Integer.toString(Listener.tempRegNum)));
			Listener.tempRegNum += 1;
			tempList.appendIRNode("STOREI", "1", "", ("$T" + Integer.toString(Listener.tempRegNum)));
		}
		else {
			Helper Help = new Helper();
			String returnType = Help.findReturnType(operand);
			tempList = Help.parseExp(operand, returnType, 0);
			IRList newTempList = Help.parseExp(val, returnType, 0);
			tempList.addAll(newTempList.getList());
			String operand_old = operand;
			operand = Help.getTempRegName(operand);
			if(operand.contains("null"))
				operand = operand_old;
			Listener.tempRegNum -= 1;
			tempList.appendIRNode(logOperatorTable.get(operator), ("$T" + Integer.toString(Listener.tempRegNum - 1)), ("$T" + Integer.toString(Listener.tempRegNum)), labelName);
		}
		tempList.printList();
		ListIR.add(tempList);
		labelStack.push(labelName);
		Listener.tempRegNum += 1;
		Listener.labelNum += 1;
		exitLabelStack.push("label" + Integer.toString(labelNum));
		Listener.labelNum += 1;
	}
	@Override 
	public void exitIf_stmt(MicroParser.If_stmtContext ctx) {
		String labelName = exitLabelStack.pop();
		IRList tempList = new IRList();
		tempList.appendIRNode("LABEL", labelName, "", "");
		tempList.printList();
		ListIR.add(tempList);
	}
	@Override
	public void enterElse_part(MicroParser.Else_partContext ctx) {
		IRList tempList = new IRList();
		String labelName = exitLabelStack.peek();
		tempList.appendIRNode("JUMP", labelName, "", "");
		labelName = labelStack.pop();
		tempList.appendIRNode("LABEL", labelName, "", "");
		ListIR.add(tempList);
		tempList.printList();
		IRList retList = new IRList();
		if(!ctx.getText().equals("")) {
			Listener.SymbolList.pushNewSymbolTable("BLOCK");
			labelName = "label" + Integer.toString(Listener.labelNum);
			labelStack.push(labelName);
			String expression = ctx.getChild(2).getText();
			String operand = "";
			String val = "";
			String operator = "";
			String storeOpcode = "STORE";
			if(!expression.equals("TRUE")) {
				String [] expressionArray = expression.split("((?=[!<>=])|(?<=[!<>=]))");
				operand = expressionArray[0];
				val = expressionArray[expressionArray.length - 1];
				if(expressionArray.length == 4) 
					operator = expressionArray[1] + expressionArray[2];
				else
					operator = expressionArray[1];
			}
			if(Listener.typeTable.containsKey(operand)) {

				if(Listener.typeTable.get(operand).equals("INT"))
					storeOpcode += "I";
				else
					storeOpcode += "F";

				if(val.matches("(\\d+(?:\\.\\d+)?$)|([A-Za-z]+$)")) {
					retList.appendIRNode(storeOpcode, val, "", ("$T" + Integer.toString(Listener.tempRegNum)));
				}
				else {
					Helper Help = new Helper();
					retList = Help.parseExp(expression, operand, 0);
				}
				String operand_old = operand;
				Helper Help = new Helper();
				operand = Help.getTempRegName(operand);
				if(operand.contains("null"))
					operand = operand_old;
				retList.appendIRNode(logOperatorTable.get(operator), operand, ("$T" + Integer.toString(Listener.tempRegNum)), labelName);
			}
			else if(operand.equals("")){
				retList.appendIRNode("STOREI", "1", "", ("$T" + Integer.toString(Listener.tempRegNum)));
				Listener.tempRegNum += 1;
				retList.appendIRNode("STOREI", "1", "", ("$T" + Integer.toString(Listener.tempRegNum)));
				retList.appendIRNode("NE", ("$T" + Integer.toString((Listener.tempRegNum - 1))), ("$T" + Integer.toString(Listener.tempRegNum)), labelName);
			}
			else {
				Helper Help = new Helper();
				String returnType = Help.findReturnType(operand);
				retList = Help.parseExp(operand, returnType, 0);
				IRList newTempList = Help.parseExp(val, returnType, 0);
				retList.addAll(newTempList.getList());
				String operand_old = operand;
				operand = Help.getTempRegName(operand);
				if(operand.contains("null"))
					operand = operand_old;
				Listener.tempRegNum -= 1;
				retList.appendIRNode(logOperatorTable.get(operator), ("$T" + Integer.toString(Listener.tempRegNum - 1)), ("$T" + Integer.toString(Listener.tempRegNum)), labelName);
			}
			Listener.tempRegNum += 1;
			Listener.labelNum += 1;
			labelName = "label" + Integer.toString(Listener.labelNum);
		}
		retList.printList();
		ListIR.add(retList);
	}
	@Override
	public void enterDo_while_stmt(MicroParser.Do_while_stmtContext ctx) {
		Listener.SymbolList.pushNewSymbolTable("BLOCK");
		IRList tempList = new IRList();
		String labelName = "label" + Integer.toString(Listener.labelNum);
		Listener.labelNum += 1;
		String outLabel = "label" + Integer.toString(Listener.labelNum);
		labelStack.push(outLabel);
		Listener.labelNum += 1;
		tempList.appendIRNode("LABEL", labelName, "", "");
		tempList.printList();
		ListIR.add(tempList);
		exitLabelStack.push(labelName);
	}
	@Override
	public void exitDo_while_stmt(MicroParser.Do_while_stmtContext ctx) {
		IRList tempList = new IRList();
		String expression = ctx.getChild(5).getText();
		String operand = "";
		String val = "";
		String operator = "";
		String labelName = labelStack.peek();
		String storeOpcode = "STORE";
		if(!expression.equals("TRUE")) {
			String [] expressionArray = expression.split("((?=[!<>=])|(?<=[!<>=]))");
			operand = expressionArray[0];
			val = expressionArray[expressionArray.length - 1];
			if(expressionArray.length == 4) 
				operator = expressionArray[1] + expressionArray[2];
			else
				operator = expressionArray[1];
		}
		if(Listener.typeTable.containsKey(operand)) {
			if(Listener.typeTable.get(operand).equals("INT"))
				storeOpcode += "I";
			else
				storeOpcode += "F";
			String oldVal = val;
			Helper Help = new Helper();
			val = Help.getTempRegName(val);
			if(val.contains("null"))
				val = oldVal;
			tempList.appendIRNode(storeOpcode, val, "", ("$T" + Integer.toString(Listener.tempRegNum)));
		}
		else {
			tempList.appendIRNode("STOREI", "1", "", ("$T" + Integer.toString(Listener.tempRegNum)));
			Listener.tempRegNum += 1;
			tempList.appendIRNode("STOREI", "1", "", ("$T" + Integer.toString(Listener.tempRegNum)));
		}
		String operand_old = operand;
		Helper Help = new Helper();
		operand = Help.getTempRegName(operand);
		if(operand.contains("null"))
			operand = operand_old;
		tempList.appendIRNode(logOperatorTable.get(operator), operand, ("$T" + Integer.toString(Listener.tempRegNum)), labelName);
		labelName = exitLabelStack.pop();
		tempList.appendIRNode("JUMP", labelName, "", "");	
		labelName = labelStack.pop();
		tempList.appendIRNode("LABEL", labelName, "", "");
		tempList.printList();
		ListIR.add(tempList);
		Listener.tempRegNum += 1;
	}
	@Override 
	public void enterAssign_expr(MicroParser.Assign_exprContext ctx) {
		String expression = ctx.getText().split(":=")[1];
		String result = ctx.getText().split(":=")[0];
		IRList tempList = new IRList();
		Helper Help = new Helper();
		String storeOpcode = "STORE";
		if(!expression.matches("\\w+\\(.*\\)$")) {
			if(Listener.typeTable.get(result).equals("INT"))
				storeOpcode += "I";
			else
				storeOpcode += "F";
			tempList = Help.parseExp(expression, result, 0);
			Listener.tempRegNum -= 1;
			String resultReg = Help.getTempRegName(result);
			if(resultReg.contains("null"))
				resultReg = result;
			tempList.appendIRNode(storeOpcode, "$T" + Integer.toString(Listener.tempRegNum), "", resultReg);
			Listener.tempRegNum += 1;
		}
		else
			tempList = Help.generateFuncCall(ctx.getChild(2).getText(), result);
		tempList.printList(); 
		ListIR.add(tempList);
	}
	@Override 
	public void enterWrite_stmt(MicroParser.Write_stmtContext ctx) { 
		IRList tempList = new IRList();
		String [] varList = ctx.getChild(2).getText().split(",");
		for(int i = 0; i < varList.length; i++) {
			String old_val = varList[i];
			Helper Help = new Helper();
			varList[i] = Help.getTempRegName(varList[i]);
			if(varList[i].contains("null"))
				varList[i] = old_val;
			if(Listener.typeTable.get(old_val).equals("INT")) 
				tempList.appendIRNode("WRITEI", varList[i], "", "");
			else if(Listener.typeTable.get(old_val).equals("FLOAT"))
				tempList.appendIRNode("WRITEF", varList[i], "", "");
			else
				tempList.appendIRNode("WRITES", varList[i], "", "");
		}
		tempList.printList();
		ListIR.add(tempList);
	}
	@Override 
	public void enterVar_decl(MicroParser.Var_declContext ctx) {
		String varlist = ctx.getChild(1).getText();
		String [] var_list = varlist.split(",");
		 for(int i = 0; i < var_list.length; i ++)
		 	Listener.typeTable.put(var_list[i], ctx.getChild(0).getText());
	}
	@Override
	public void enterRead_stmt(MicroParser.Read_stmtContext ctx) {
		IRList tempList = new IRList();
		String [] varList = ctx.getChild(2).getText().split(",");
		Helper Help = new Helper();
		for(int i = 0; i < varList.length; i++) {
			String old_val = varList[i];
			varList[i] = Help.getTempRegName(varList[i]);
			if(varList[i].contains("null"))
				varList[i] = old_val;
			if(Listener.typeTable.get(old_val).equals("INT")) 
				tempList.appendIRNode("READI", varList[i], "", "");
			else if(Listener.typeTable.get(old_val).equals("FLOAT"))
				tempList.appendIRNode("READF", varList[i], "", "");
			else
				tempList.appendIRNode("READS", varList[i], "", "");
		}
		tempList.printList();
		ListIR.add(tempList);
	}
	@Override public void exitFunc_decl(MicroParser.Func_declContext ctx) {
		IRList lastList = ListIR.get(ListIR.size() - 1);
		IRNode lastNode = lastList.getIRNode(lastList.getSize() - 1);
		if(!lastNode.getOpcode().equals("RET")) {
			IRList tempList = new IRList();
			tempList.appendIRNode("RET", "", "", "");
			tempList.printList();
			ListIR.add(tempList);
		}
		System.out.println();
	}
	@Override public void enterReturn_stmt(MicroParser.Return_stmtContext ctx) {
		String expression = ctx.getChild(1).getText();
		IRList tempList = new IRList();
		ExpressionStack expstack = new ExpressionStack();
		String expr = expstack.createExprStack(expression);
		PemdasTree pdt = new PemdasTree();
		Node node = pdt.createBinaryTree(expr);
		Helper Help = new Helper();
		String storeOpcode = "STORE";
		String resultType = "";
		if(retType == 1) {
			storeOpcode += "I";
			resultType = "INT";
		}
		else {
			storeOpcode += "F";
			resultType = "FLOAT";
		}
		if(!expression.matches("\\w+\\(.*\\)$")) 
			tempList = Help.parseExp(expression, resultType, 1);
		else {
			tempList = Help.generateFuncCall(expression, resultType);
			tempList.appendIRNode(storeOpcode, "$T" + Integer.toString(Listener.tempRegNum - 1), "", "$R");
		}
		if(!(node.getLeftNode() == null && node.getRightNode() == null)) {
			Listener.tempRegNum -= 1;
			tempList.appendIRNode(storeOpcode, "$T" + Integer.toString(Listener.tempRegNum), "", "$R");
			Listener.tempRegNum += 1;
		}
		tempList.appendIRNode("RET", "", "", "");
		tempList.printList();
		ListIR.add(tempList); 
	}
}