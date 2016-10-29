import java.io.*;
import java.lang.*;
import java.util.*;

public class Listener extends MicroBaseListener {
	
	private SymbolTableList SymbolList = new SymbolTableList();
	private String Var_type;
	public static int tempRegNum = 1;
	public static int labelNum = 1;
	private List <IRList> ListIR = new ArrayList<IRList>();
	private Hashtable<String, String> typeTable = new Hashtable<String, String>();
	private Hashtable <String, String> logOperatorTable = new Hashtable<String, String>();
	private Stack <String> labelStack = new Stack<String>();
	private Stack <String> exitLabelStack = new Stack<String>();

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
		SymbolList.pushNewSymbolTable("GLOBAL");	
	}
	@Override
	public void exitPgm_body(MicroParser.Pgm_bodyContext ctx) {
		SymbolTable tempTable = SymbolList.getSymbolTable(0);
		List nameList = tempTable.getNameList();

		for(int i = 0; i < nameList.size(); i ++) {
			System.out.println("var " + nameList.get(i));
		}
		for(int i = 0; i < ListIR.size(); i++) {
			IRList tempList = ListIR.get(i);
			for(int j = 0; j < tempList.getSize(); j++) {
				IRNode tempNode = tempList.getIRNode(j);
				String opcode = tempNode.getOpcode();
				String operand1 = tempNode.getOperand1();
				String operand2 = tempNode.getOperand2();
				String result = tempNode.getResult();

				if(operand1.matches("\\$T\\d+$")) {
					operand1 = "r" + operand1.split("T")[1];
				}

				if(operand2.matches("\\$T\\d+$")) {
					operand2 = "r" + operand2.split("T")[1];
				}

				if(result.matches("\\$T\\d+$")) {
					result = "r" + result.split("T")[1];
				}

				if(opcode.contains("STOREI")) {
					System.out.print("move");
					System.out.print(" " + operand1);
					System.out.println(" " + result);
				}
				else if(opcode.contains("ADDI")) {
					System.out.print("move");
					System.out.print(" " + operand1);
					System.out.println(" " + result);

					System.out.println("addi " + operand2 + " " + result);					
				}
				else if(opcode.contains("SUBI")) {
					System.out.print("move");
					System.out.print(" " + operand1);
					System.out.println(" " + result);

					System.out.println("subi " + operand2 + " " + result);					
				}
				else if(opcode.contains("MULTI")) {
					System.out.print("move");
					System.out.print(" " + operand1);
					System.out.println(" " + result);

					System.out.println("muli " + operand2 + " " + result);					
				}
				else if(opcode.contains("DIVI")) {
					System.out.print("move");
					System.out.print(" " + operand1);
					System.out.println(" " + result);

					System.out.println("divi " + operand2 + " " + result);					
				}
				else if(opcode.contains("WRITEI")) {
					System.out.println("sys writei " + operand1);
				}
				else if(opcode.contains("STOREF")) {
					System.out.print("move");
					System.out.print(" " + operand1);
					System.out.println(" " + result);
				}
				else if(opcode.contains("ADDF")) {
					System.out.print("move");
					System.out.print(" " + operand1);
					System.out.println(" " + result);

					System.out.println("addr " + operand2 + " " + result);					
				}
				else if(opcode.contains("SUBF")) {
					System.out.print("move");
					System.out.print(" " + operand1);
					System.out.println(" " + result);

					System.out.println("subr " + operand2 + " " + result);					
				}
				else if(opcode.contains("MULTF")) {
					System.out.print("move");
					System.out.print(" " + operand1);
					System.out.println(" " + result);

					System.out.println("mulr " + operand2 + " " + result);					
				}
				else if(opcode.contains("DIVF")) {
					System.out.print("move");
					System.out.print(" " + operand1);
					System.out.println(" " + result);

					System.out.println("divr " + operand2 + " " + result);					
				}
				else if(opcode.contains("WRITEF")) {
					System.out.println("sys writer " + operand1);
				}
			}
		}
		System.out.println("sys halt");
	}
	@Override
	public void enterString_decl(MicroParser.String_declContext ctx) {
		String name = ctx.getText().split(":=")[0].split("STRING")[1];
		String Value = ctx.getText().split(":=")[1].split(";")[0];
		SymbolList.addSymbol("STRING", name, Value);	
	}
	@Override
	public void exitVar_decl(MicroParser.Var_declContext ctx) {
		String name_list = ctx.getText().split("(FLOAT)|(INT)")[1].split(";")[0];
		SymbolList.addSymbol(Var_type, name_list, null);
	}
	@Override
	public void enterVar_type(MicroParser.Var_typeContext ctx) {
		Var_type = ctx.getText();
	}
	@Override
	public void exitParam_decl(MicroParser.Param_declContext ctx) {
		String name = ctx.getText().split("(FLOAT)|(INT)")[1];
		SymbolList.addSymbol(Var_type, name, null);
	}
	@Override
	public void enterFunc_decl(MicroParser.Func_declContext ctx) {
		String name = ctx.getText().split("FUNCTION")[1].split("(FLOAT)|(VOID)|(INT)")[1].split("\\(")[0];
		SymbolList.pushNewSymbolTable(name);
	}
	@Override 
	public void enterIf_stmt(MicroParser.If_stmtContext ctx) {
		SymbolList.pushNewSymbolTable("BLOCK");

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
		}
		
		if(typeTable.containsKey(operand) && typeTable.get(operand).equals("INT")) {
			tempList.appendIRNode("STOREI", val, "", ("$T" + Integer.toString(Listener.tempRegNum)));
		}
		else if(typeTable.containsKey(operand) && typeTable.get(operand).equals("FLOAT")) {
			tempList.appendIRNode("STOREF", val, "", ("$T" + Integer.toString(Listener.tempRegNum)));
		}
		else {
			tempList.appendIRNode("STOREI", "1", "", ("$T" + Integer.toString(Listener.tempRegNum)));
			Listener.tempRegNum += 1;
			tempList.appendIRNode("STOREI", "1", "", ("$T" + Integer.toString(Listener.tempRegNum)));
		}

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
		if(!ctx.getText().equals("")) {

			SymbolList.pushNewSymbolTable("BLOCK");
			
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
				tempList.appendIRNode("STOREI", val, "", ("$T" + Integer.toString(Listener.tempRegNum)));
				tempList.appendIRNode(logOperatorTable.get(operator), operand, ("$T" + Integer.toString(Listener.tempRegNum)), labelName);
			}
			else if(typeTable.containsKey(operand) && typeTable.get(operand).equals("FLOAT")) {
				tempList.appendIRNode("STOREF", val, "", ("$T" + Integer.toString(Listener.tempRegNum)));
				tempList.appendIRNode(logOperatorTable.get(operator), operand, ("$T" + Integer.toString(Listener.tempRegNum)), labelName);
			}
			else {
				tempList.appendIRNode("STOREI", "1", "", ("$T" + Integer.toString(Listener.tempRegNum)));
				Listener.tempRegNum += 1;
				tempList.appendIRNode("STOREI", "1", "", ("$T" + Integer.toString(Listener.tempRegNum)));
				tempList.appendIRNode("NE", ("$T" + Integer.toString((Listener.tempRegNum - 1))), ("$T" + Integer.toString(Listener.tempRegNum)), labelName);
			}
			Listener.tempRegNum += 1;
			labelName = "label" + Integer.toString(Listener.labelNum);
		}

		tempList.printList();
		ListIR.add(tempList);
	}
	@Override
	public void enterDo_while_stmt(MicroParser.Do_while_stmtContext ctx) {
		SymbolList.pushNewSymbolTable("BLOCK");
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
		String labelName = "label" + Integer.toString(Listener.labelNum);
		if(!expression.equals("TRUE")) {
			operand = expression.split("<=|>=|!=|<|>|=")[0];
			val = expression.split("<=|>=|!=|<|>|=")[1];
			operator = expression.split(operand)[1].split(val)[0];
		}
		if(typeTable.containsKey(operand) && typeTable.get(operand).equals("INT")) {
			tempList.appendIRNode("STOREI", val, "", ("$T" + Integer.toString(Listener.tempRegNum)));
		}
		else if(typeTable.containsKey(operand) && typeTable.get(operand).equals("FLOAT")) {
			tempList.appendIRNode("STOREF", val, "", ("$T" + Integer.toString(Listener.tempRegNum)));
		}
		else {
			tempList.appendIRNode("STOREI", "1", "", ("$T" + Integer.toString(Listener.tempRegNum)));
			Listener.tempRegNum += 1;
			tempList.appendIRNode("STOREI", "1", "", ("$T" + Integer.toString(Listener.tempRegNum)));
		}
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
		if(typeTable.get(result).equals("INT")) {
			if(node.getLeftNode() == null && node.getRightNode() == null) {
				tempList.appendIRNode("STOREI", expression, "", "$T" + Integer.toString(Listener.tempRegNum));
				tempList.appendIRNode("STOREI", "$T" + Integer.toString(Listener.tempRegNum), "", result);
			}
			else {
				tempList = pdt.inOrderTraverse(tempList, node);
				Listener.tempRegNum -= 1;
				tempList.appendIRNode("STOREI", "$T" + Integer.toString(Listener.tempRegNum), "", result);
			}
		}
		else {
			if(node.getLeftNode() == null && node.getRightNode() == null) {
				tempList.appendIRNode("STOREF", expression, "", "$T" + Integer.toString(Listener.tempRegNum));
				tempList.appendIRNode("STOREF", "$T" + Integer.toString(Listener.tempRegNum), "", result);
			}
			else {
				tempList = pdt.inOrderTraverseFloat(tempList, node);
				Listener.tempRegNum -= 1;
				tempList.appendIRNode("STOREF", "$T" + Integer.toString(Listener.tempRegNum), "", result);
			}
		}
		Listener.tempRegNum += 1;
		tempList.printList();
		ListIR.add(tempList);
	}
	@Override 
	public void enterWrite_stmt(MicroParser.Write_stmtContext ctx) { 
		IRList tempList = new IRList();
		String var = ctx.getText();
		String var2 = var.split("\\(")[1].split("\\)")[0];
		if(typeTable.get(var2).equals("INT")) {
			tempList.appendIRNode("WRITEI", ctx.getChild(2).getText(), "", "");
		}
		else {
			tempList.appendIRNode("WRITEF", ctx.getChild(2).getText(), "", "");
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
		String id = ctx.getChild(2).getText();
		if(typeTable.get(id).equals("INT")) {
			tempList.appendIRNode("READI", id, "", "");
		}
		else {
			tempList.appendIRNode("READF", id, "", "");
		}
		tempList.printList();
		ListIR.add(tempList);
	}
}
