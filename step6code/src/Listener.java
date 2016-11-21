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
	TinyConverter tiny = new TinyConverter(); 
	private List <IRList> ListIR = new ArrayList<IRList>();
	public static Hashtable <String, String> typeTable = new Hashtable<String, String>();
	private Hashtable <String, String> logOperatorTable = new Hashtable<String, String>();
	private Stack <String> labelStack = new Stack<String>();
	private Stack <String> exitLabelStack = new Stack<String>();
	private Hashtable <String, String> registerTypeTable = new Hashtable<String, String>();
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
		typeTable.put(name, "STRING");
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
		 	typeTable.put(param_list[i], ctx.getChild(0).getText());
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

		if(!expression.equals("TRUE")) {
			operand = expression.split("<=|>=|!=|<|>|=")[0];
			val = expression.split("<=|>=|!=|<|>|=")[1];
			operator = expression.split(operand)[1].split(val)[0];
			operator = operator.substring(0, 1);
		}
		
		if(typeTable.containsKey(operand) && typeTable.get(operand).equals("INT")) {
			
			if(val.matches("(\\d+(?:\\.\\d+)?$)|([A-Za-z]+$)")) {
				tempList.appendIRNode("STOREI", val, "", ("$T" + Integer.toString(Listener.tempRegNum)));
			}
			else {
				Helper Help = new Helper();
				tempList = Help.parseExp(val, operand);
			}
		}
		else if(typeTable.containsKey(operand) && typeTable.get(operand).equals("FLOAT")) {
			if(val.matches("(\\d+(?:\\.\\d+)?$)|([A-Za-z]+$)")) {
				tempList.appendIRNode("STOREF", val, "", ("$T" + Integer.toString(Listener.tempRegNum)));
			}
			else {
				Helper Help = new Helper();
				tempList = Help.parseExp(val, operand);
			}
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

			if(!expression.equals("TRUE")) {
				operand = expression.split("<=|>=|!=|<|>|=")[0];
				val = expression.split("<=|>=|!=|<|>|=")[1];
				operator = expression.split(operand)[1].split(val)[0];
			}
			if(typeTable.containsKey(operand) && typeTable.get(operand).equals("INT")) {

				if(val.matches("(\\d+(?:\\.\\d+)?$)|([A-Za-z]+$)")) {
					retList.appendIRNode("STOREI", val, "", ("$T" + Integer.toString(Listener.tempRegNum)));
				}
				else {
					Helper Help = new Helper();
					retList = Help.parseExp(expression, operand);
				}
				String operand_old = operand;
				Helper Help = new Helper();
				operand = Help.getTempRegName(operand);
				if(operand.contains("null"))
					operand = operand_old;
				retList.appendIRNode(logOperatorTable.get(operator), operand, ("$T" + Integer.toString(Listener.tempRegNum)), labelName);
			}
			else if(typeTable.containsKey(operand) && typeTable.get(operand).equals("FLOAT")) {

				if(val.matches("(\\d+(?:\\.\\d+)?$)|([A-Za-z]+$)")) {
					retList.appendIRNode("STOREF", val, "", ("$T" + Integer.toString(Listener.tempRegNum)));
				}
				else {
					Helper Help = new Helper();
					retList = Help.parseExp(expression, operand);
				}
				String operand_old = operand;
				Helper Help = new Helper();
				operand = Help.getTempRegName(operand);
				if(operand.contains("null"))
					operand = operand_old;
				retList.appendIRNode(logOperatorTable.get(operator), operand, ("$T" + Integer.toString(Listener.tempRegNum)), labelName);
			}
			else {
				retList.appendIRNode("STOREI", "1", "", ("$T" + Integer.toString(Listener.tempRegNum)));
				Listener.tempRegNum += 1;
				retList.appendIRNode("STOREI", "1", "", ("$T" + Integer.toString(Listener.tempRegNum)));
				retList.appendIRNode("NE", ("$T" + Integer.toString((Listener.tempRegNum - 1))), ("$T" + Integer.toString(Listener.tempRegNum)), labelName);
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
		if(!expression.equals("TRUE")) {
			operand = expression.split("<=|>=|!=|<|>|=")[0];
			val = expression.split("<=|>=|!=|<|>|=")[1];
			operator = expression.split(operand)[1].split(val)[0];
		}
		if(typeTable.containsKey(operand) && typeTable.get(operand).equals("INT")) {
			String oldVal = val;
			Helper Help = new Helper();
			val = Help.getTempRegName(val);
			if(val.contains("null"))
				val = oldVal;
			tempList.appendIRNode("STOREI", val, "", ("$T" + Integer.toString(Listener.tempRegNum)));
		}
		else if(typeTable.containsKey(operand) && typeTable.get(operand).equals("FLOAT")) {
			String oldVal = val;
			Helper Help = new Helper();
			val = Help.getTempRegName(val);
			if(val.contains("null"))
				val = oldVal;
			tempList.appendIRNode("STOREF", val, "", ("$T" + Integer.toString(Listener.tempRegNum)));
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
		ExpressionStack expstack = new ExpressionStack();
		String expr = expstack.createExprStack(expression);
		PemdasTree pdt = new PemdasTree();
		Node node = pdt.createBinaryTree(expr);
		IRList tempList = new IRList();
		if(typeTable.get(result).equals("INT") && !expression.matches("\\w+\\(.*\\)$")) {
			if(node.getLeftNode() == null && node.getRightNode() == null) {
				if(expression.matches("\\d+(?:\\.\\d+)?$"))
					tempList.appendIRNode("STOREI", expression, "", "$T" + Integer.toString(Listener.tempRegNum));
				else {
					Helper Help = new Helper();
					String tempReg = Help.getTempRegName(expression);
					if(tempReg.contains("null"))
						tempReg = expression;
					tempList.appendIRNode("STOREI", tempReg, "", "$T" + Integer.toString(Listener.tempRegNum));
				}
				Helper Help = new Helper();
				String resultReg = Help.getTempRegName(result);
				if(resultReg.contains("null"))
					resultReg = result;
				tempList.appendIRNode("STOREI", "$T" + Integer.toString(Listener.tempRegNum), "", resultReg);
				Listener.tempRegNum += 1;
			}
			else {
				tempList = pdt.inOrderTraverse(tempList, node);
				Listener.tempRegNum -= 1;
				Helper Help = new Helper();
				String resultReg = Help.getTempRegName(result);
				if(resultReg.contains("null"))
					resultReg = result;
				tempList.appendIRNode("STOREI", "$T" + Integer.toString(Listener.tempRegNum), "", resultReg);
				Listener.tempRegNum += 1;
			}
		}
		else if(typeTable.get(result).equals("FLOAT") && !expression.matches("\\w+\\(.*\\)$")) {
			if(node.getLeftNode() == null && node.getRightNode() == null) {
				if(expression.matches("\\d+(?:\\.\\d+)?$"))
					tempList.appendIRNode("STOREF", expression, "", "$T" + Integer.toString(Listener.tempRegNum));
				else {
					Helper Help = new Helper();
					String tempReg = Help.getTempRegName(expression);
					if(tempReg.contains("null"))
						tempReg = expression;
					tempList.appendIRNode("STOREF", tempReg, "", "$T" + Integer.toString(Listener.tempRegNum));
				}
				Helper Help = new Helper();
				String resultReg = Help.getTempRegName(result);
				if(resultReg.contains("null"))
					resultReg = result;
				tempList.appendIRNode("STOREF", "$T" + Integer.toString(Listener.tempRegNum), "", resultReg);
				Listener.tempRegNum += 1;
			}
			else {
				tempList = pdt.inOrderTraverseFloat(tempList, node);
				Listener.tempRegNum -= 1;
				Helper Help = new Helper();
				String resultReg = Help.getTempRegName(result);
				if(resultReg.contains("null"))
					resultReg = result;
				tempList.appendIRNode("STOREF", "$T" + Integer.toString(Listener.tempRegNum), "", resultReg);
				Listener.tempRegNum += 1;
			}
		}
		else {
			Helper Help = new Helper();
			tempList = Help.generateFuncCall(ctx.getChild(2).getText(), result);
		}
		tempList.printList(); 
		ListIR.add(tempList);
	}
	@Override 
	public void enterWrite_stmt(MicroParser.Write_stmtContext ctx) { 
		IRList tempList = new IRList();
		String [] varList = ctx.getChild(2).getText().split(",");
		for(int i = 0; i < varList.length; i++) {
			if(typeTable.get(varList[i]).equals("INT")) {
				String old_val = varList[i];
				Helper Help = new Helper();
				varList[i] = Help.getTempRegName(varList[i]);
				if(varList[i].contains("null"))
					varList[i] = old_val;
				tempList.appendIRNode("WRITEI", varList[i], "", "");
			}
			else if(typeTable.get(varList[i]).equals("FLOAT")) {
				String old_val = varList[i];
				Helper Help = new Helper();
				varList[i] = Help.getTempRegName(varList[i]);
				if(varList[i].contains("null"))
					varList[i] = old_val;
				tempList.appendIRNode("WRITEF", varList[i], "", "");
			}
			else {
				String old_val = varList[i];
				Helper Help = new Helper();
				varList[i] = Help.getTempRegName(varList[i]);
				if(varList[i].contains("null"))
					varList[i] = old_val;
				tempList.appendIRNode("WRITES", varList[i], "", "");
			}
		}
		tempList.printList();
		ListIR.add(tempList);


	}
	@Override 
	public void enterVar_decl(MicroParser.Var_declContext ctx) {
		String varlist = ctx.getChild(1).getText();
		String [] var_list = varlist.split(",");
		 for(int i = 0; i < var_list.length; i ++) {
		 	typeTable.put(var_list[i], ctx.getChild(0).getText());
		 }
	}
	@Override
	public void enterRead_stmt(MicroParser.Read_stmtContext ctx) {
		IRList tempList = new IRList();
		String [] varList = ctx.getChild(2).getText().split(",");
		Helper Help = new Helper();
		for(int i = 0; i < varList.length; i++) {
			if(typeTable.get(varList[i]).equals("INT")) {
				String old_val = varList[i];
				varList[i] = Help.getTempRegName(varList[i]);
				if(varList[i].contains("null"))
					varList[i] = old_val;
				tempList.appendIRNode("READI", varList[i], "", "");
			}
			else if(typeTable.get(varList[i]).equals("FLOAT")) {
				String old_val = varList[i];
				varList[i] = Help.getTempRegName(varList[i]);
				if(varList[i].contains("null"))
					varList[i] = old_val;
				tempList.appendIRNode("READF", varList[i], "", "");
			}
			else {
				String old_val = varList[i];
				varList[i] = Help.getTempRegName(varList[i]);
				if(varList[i].contains("null"))
					varList[i] = old_val;
				tempList.appendIRNode("READS", varList[i], "", "");
			}
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
		if(retType == 1) {
			if(node.getLeftNode() == null && node.getRightNode() == null) {
				if(expression.matches("\\d+(?:\\.\\d+)?$")) {
					tempList.appendIRNode("STOREI", expression, "", "$T" + Integer.toString(Listener.tempRegNum));
					tempList.appendIRNode("STOREI", "$T" + Integer.toString(Listener.tempRegNum), "", "$R");
					Listener.tempRegNum += 1;
				}
				else {
					String tempReg = Help.getTempRegName(expression);
					if(tempReg.contains("null"))
						tempReg = expression;
					tempList.appendIRNode("STOREI", tempReg, "", "$T" + Integer.toString(Listener.tempRegNum));
					tempList.appendIRNode("STOREI", "$T" + Integer.toString(Listener.tempRegNum), "", "$R");
					Listener.tempRegNum += 1;
				}
			}
			else {
				tempList = pdt.inOrderTraverse(tempList, node);
				Listener.tempRegNum -= 1;
				tempList.appendIRNode("STOREI", "$T" + Integer.toString(Listener.tempRegNum), "", "$R");
				Listener.tempRegNum += 1;
			}
		}
		else if(retType == 2) {
			if(node.getLeftNode() == null && node.getRightNode() == null) {
				if(expression.matches("\\d+(?:\\.\\d+)?$")) {
					tempList.appendIRNode("STOREF", expression, "", "$T" + Integer.toString(Listener.tempRegNum));
					tempList.appendIRNode("STOREF", "$T" + Integer.toString(Listener.tempRegNum), "", "$R");
					Listener.tempRegNum += 1;
				}
				else {
					String tempReg = Help.getTempRegName(expression);
					if(tempReg.contains("null"))
						tempReg = expression;
					tempList.appendIRNode("STOREF", tempReg, "", "$T" + Integer.toString(Listener.tempRegNum));
					tempList.appendIRNode("STOREF", "$T" + Integer.toString(Listener.tempRegNum), "", "$R");
					Listener.tempRegNum += 1;
				}
			}
			else {
				tempList = pdt.inOrderTraverseFloat(tempList, node);
				Listener.tempRegNum -= 1;
				tempList.appendIRNode("STOREF", "$T" + Integer.toString(Listener.tempRegNum), "", "$R");
				Listener.tempRegNum += 1;
			}
		}
		tempList.appendIRNode("RET", "", "", "");
		tempList.printList();
		ListIR.add(tempList); 
	}

}