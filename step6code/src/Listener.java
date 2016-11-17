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
	private List <IRList> ListIR = new ArrayList<IRList>();
	private Hashtable<String, String> typeTable = new Hashtable<String, String>();
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

	private void parseExp(String expression, String result) {
		ExpressionStack expstack = new ExpressionStack();
		String expr = expstack.createExprStack(expression);
		PemdasTree pdt = new PemdasTree();
		Node node = pdt.createBinaryTree(expr);
		IRList tempList = new IRList();
		if(typeTable.get(result).equals("INT")) {
			tempList = pdt.inOrderTraverse(tempList, node);
		}
		else {
			tempList = pdt.inOrderTraverseFloat(tempList, node);
		}
		tempList.printList();
		ListIR.add(tempList);
	}

	@Override
	public void enterPgm_body(MicroParser.Pgm_bodyContext ctx) {
		Listener.SymbolList.pushNewSymbolTable("GLOBAL");	
	}
	@Override
	public void exitPgm_body(MicroParser.Pgm_bodyContext ctx) {
		SymbolTable tempTable = Listener.SymbolList.getSymbolTable(0);
		List nameList = tempTable.getNameList();


		//SymbolList.printSymbolList();

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

					if(operand1.matches("\\r\\d+$")) {
						registerTypeTable.put(operand1, "INT");
					}
					if(result.matches("\\r\\d+$")) {
						registerTypeTable.put(result, "INT");
					}

					System.out.print("move");
					System.out.print(" " + operand1);
					System.out.println(" " + result);
				}
				else if(opcode.contains("ADDI")) {

					if(operand1.matches("\\r\\d+$")) {
						registerTypeTable.put(operand1, "INT");
					}
					if(operand2.matches("\\r\\d+$")) {
						registerTypeTable.put(operand1, "INT");
					}
					if(result.matches("\\r\\d+$")) {
						registerTypeTable.put(result, "INT");
					}

					System.out.print("move");
					System.out.print(" " + operand1);
					System.out.println(" " + result);

					System.out.println("addi " + operand2 + " " + result);					
				}
				else if(opcode.contains("SUBI")) {

					if(operand1.matches("\\r\\d+$")) {
						registerTypeTable.put(operand1, "INT");
					}
					if(operand2.matches("\\r\\d+$")) {
						registerTypeTable.put(operand1, "INT");
					}
					if(result.matches("\\r\\d+$")) {
						registerTypeTable.put(result, "INT");
					}

					System.out.print("move");
					System.out.print(" " + operand1);
					System.out.println(" " + result);

					System.out.println("subi " + operand2 + " " + result);					
				}
				else if(opcode.contains("MULTI")) {

					if(operand1.matches("\\r\\d+$")) {
						registerTypeTable.put(operand1, "INT");
					}
					if(operand2.matches("\\r\\d+$")) {
						registerTypeTable.put(operand1, "INT");
					}
					if(result.matches("\\r\\d+$")) {
						registerTypeTable.put(result, "INT");
					}

					System.out.print("move");
					System.out.print(" " + operand1);
					System.out.println(" " + result);

					System.out.println("muli " + operand2 + " " + result);					
				}
				else if(opcode.contains("DIVI")) {

					if(operand1.matches("\\r\\d+$")) {
						registerTypeTable.put(operand1, "INT");
					}
					if(operand2.matches("\\r\\d+$")) {
						registerTypeTable.put(operand1, "INT");
					}
					if(result.matches("\\r\\d+$")) {
						registerTypeTable.put(result, "INT");
					}

					System.out.print("move");
					System.out.print(" " + operand1);
					System.out.println(" " + result);

					System.out.println("divi " + operand2 + " " + result);					
				}
				else if(opcode.contains("WRITEI")) {

					if(operand1.matches("\\r\\d+$")) {
						registerTypeTable.put(operand1, "INT");
					}

					System.out.println("sys writei " + operand1);
				}
				else if(opcode.contains("STOREF")) {

					if(operand1.matches("\\r\\d+$")) {
						registerTypeTable.put(operand1, "FLOAT");
					}
					if(result.matches("\\r\\d+$")) {
						registerTypeTable.put(result, "FLOAT");
					}

					System.out.print("move");
					System.out.print(" " + operand1);
					System.out.println(" " + result);
				}
				else if(opcode.contains("ADDF")) {

					if(operand1.matches("\\r\\d+$")) {
						registerTypeTable.put(operand1, "FLOAT");
					}
					if(operand2.matches("\\r\\d+$")) {
						registerTypeTable.put(operand1, "FLOAT");
					}
					if(result.matches("\\r\\d+$")) {
						registerTypeTable.put(result, "FLOAT");
					}

					System.out.print("move");
					System.out.print(" " + operand1);
					System.out.println(" " + result);

					System.out.println("addr " + operand2 + " " + result);					
				}
				else if(opcode.contains("SUBF")) {

					if(operand1.matches("\\r\\d+$")) {
						registerTypeTable.put(operand1, "FLOAT");
					}
					if(operand2.matches("\\r\\d+$")) {
						registerTypeTable.put(operand1, "FLOAT");
					}
					if(result.matches("\\r\\d+$")) {
						registerTypeTable.put(result, "FLOAT");
					}

					System.out.print("move");
					System.out.print(" " + operand1);
					System.out.println(" " + result);

					System.out.println("subr " + operand2 + " " + result);					
				}
				else if(opcode.contains("MULTF")) {

					if(operand1.matches("\\r\\d+$")) {
						registerTypeTable.put(operand1, "FLOAT");
					}
					if(operand2.matches("\\r\\d+$")) {
						registerTypeTable.put(operand1, "FLOAT");
					}
					if(result.matches("\\r\\d+$")) {
						registerTypeTable.put(result, "FLOAT");
					}

					System.out.print("move");
					System.out.print(" " + operand1);
					System.out.println(" " + result);

					System.out.println("mulr " + operand2 + " " + result);					
				}
				else if(opcode.contains("DIVF")) {

					if(operand1.matches("\\r\\d+$")) {
						registerTypeTable.put(operand1, "FLOAT");
					}
					if(operand2.matches("\\r\\d+$")) {
						registerTypeTable.put(operand1, "FLOAT");
					}
					if(result.matches("\\r\\d+$")) {
						registerTypeTable.put(result, "FLOAT");
					}

					System.out.print("move");
					System.out.print(" " + operand1);
					System.out.println(" " + result);

					System.out.println("divr " + operand2 + " " + result);					
				}
				else if(opcode.contains("WRITEF")) {
					System.out.println("sys writer " + operand1);
				}
				else if(opcode.contains("READI")) {
					System.out.println("sys readi " + operand1);
				}
				else if(opcode.contains("READF")) {
					System.out.println("sys readr " + operand1);
				}
				else if(opcode.contains("JUMP")) {
					System.out.println("jmp " + operand1);
				}
				else if(opcode.contains("LABEL")) {
					System.out.println("label " + operand1);
				}
				else if(opcode.contains("GT")) {
					if(registerTypeTable.containsKey(operand1)) {
						if(registerTypeTable.get(operand1).equals("INT")) {
							System.out.println("cmpi " + operand1 + " " + operand2);
						}
						else {
							System.out.println("cmpr " + operand1 + " " + operand2);
						}
					}
					else if(typeTable.containsKey(operand1)) {
						if(typeTable.get(operand1).equals("INT")) {
							System.out.println("cmpi " + operand1 + " " + operand2);
						}
						else {
							System.out.println("cmpr " + operand1 + " " + operand2);
						}
					}
					else {
						System.out.println("cmpi " + operand1 + " " + operand2);
					}
					System.out.println("jgt " + result);
				}
				else if(opcode.contains("GE")) {
					if(registerTypeTable.containsKey(operand1)) {
						if(registerTypeTable.get(operand1).equals("INT")) {
							System.out.println("cmpi " + operand1 + " " + operand2);
						}
						else {
							System.out.println("cmpr " + operand1 + " " + operand2);
						}
					}
					else if(typeTable.containsKey(operand1)) {
						if(typeTable.get(operand1).equals("INT")) {
							System.out.println("cmpi " + operand1 + " " + operand2);
						}
						else {
							System.out.println("cmpr " + operand1 + " " + operand2);
						}
					}
					else {
						System.out.println("cmpi " + operand1 + " " + operand2);
					}
					System.out.println("jge " + result);
				}
				else if(opcode.contains("LT")) {
					if(registerTypeTable.containsKey(operand1)) {
						if(registerTypeTable.get(operand1).equals("INT")) {
							System.out.println("cmpi " + operand1 + " " + operand2);
						}
						else {
							System.out.println("cmpr " + operand1 + " " + operand2);
						}
					}
					else if(typeTable.containsKey(operand1)) {
						if(typeTable.get(operand1).equals("INT")) {
							System.out.println("cmpi " + operand1 + " " + operand2);
						}
						else {
							System.out.println("cmpr"+ operand1 + " " + operand2);
						}
					}
					else {
						System.out.println("cmpi " + operand1 + " " + operand2);
					}
					System.out.println("jlt " + result);
				}
				else if(opcode.contains("LE")) {
					if(registerTypeTable.containsKey(operand1)) {
						if(registerTypeTable.get(operand1).equals("INT")) {
							System.out.println("cmpi " + operand1 + " " + operand2);
						}
						else {
							System.out.println("cmpr " + operand1 + " " + operand2);
						}
					}
					else if(typeTable.containsKey(operand1)) {
						if(typeTable.get(operand1).equals("INT")) {
							System.out.println("cmpi " + operand1 + " " + operand2);
						}
						else {
							System.out.println("cmpr " + operand1 + " " + operand2);
						}
					}
					else {
						System.out.println("cmpi " + operand1 + " " + operand2);
					}
					System.out.println("jle " + result);
				}
				else if(opcode.contains("NE")) {
					if(registerTypeTable.containsKey(operand1)) {
						if(registerTypeTable.get(operand1).equals("INT")) {
							System.out.println("cmpi " + operand1 + " " + operand2);
						}
						else {
							System.out.println("cmpr " + operand1 + " " + operand2);
						}
					}
					else if(typeTable.containsKey(operand1)) {
						if(typeTable.get(operand1).equals("INT")) {
							System.out.println("cmpi " + operand1 + " " + operand2);
						}
						else {
							System.out.println("cmpr " + operand1 + " " + operand2);
						}
					}
					else {
						System.out.println("cmpi " + operand1 + " " + operand2);
					}
					System.out.println("jne " + result);
				}
				else if(opcode.contains("EQ")) {
					if(registerTypeTable.containsKey(operand1)) {
						if(registerTypeTable.get(operand1).equals("INT")) {
							System.out.println("cmpi " + operand1 + " " + operand2);
						}
						else {
							System.out.println("cmpr " + operand1 + " " + operand2);
						}
					}
					else if(typeTable.containsKey(operand1)) {
						if(typeTable.get(operand1).equals("INT")) {
							System.out.println("cmpi " + operand1 + " " + operand2);
						}
						else {
							System.out.println("cmpr "+ operand1 + " " + operand2);
						}
					}
					else {
						System.out.println("cmpi " + operand1 + " "  + operand2);
					}
					System.out.println("jeq " + result);
				}
			}
		}
		System.out.println("sys halt");
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
		//Listener.localNum++;
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
		//Listener.paramNum++;
	}
	@Override
	public void enterFunc_decl(MicroParser.Func_declContext ctx) {
		Listener.paramNum = 1;
		localNum = 1;

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
				parseExp(val, operand);
			}
		}
		else if(typeTable.containsKey(operand) && typeTable.get(operand).equals("FLOAT")) {
			if(val.matches("(\\d+(?:\\.\\d+)?$)|([A-Za-z]+$)")) {
				tempList.appendIRNode("STOREF", val, "", ("$T" + Integer.toString(Listener.tempRegNum)));
			}
			else {
				parseExp(val, operand);
			}
		}
		else {
			tempList.appendIRNode("STOREI", "1", "", ("$T" + Integer.toString(Listener.tempRegNum)));
			Listener.tempRegNum += 1;
			tempList.appendIRNode("STOREI", "1", "", ("$T" + Integer.toString(Listener.tempRegNum)));
		}
		String operand_old = operand;
		operand = getTempRegName(operand);
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
					tempList.appendIRNode("STOREI", val, "", ("$T" + Integer.toString(Listener.tempRegNum)));
				}
				else {
					parseExp(expression, operand);
				}
				String operand_old = operand;
				operand = getTempRegName(operand);
				if(operand.contains("null"))
					operand = operand_old;
				tempList.appendIRNode(logOperatorTable.get(operator), operand, ("$T" + Integer.toString(Listener.tempRegNum)), labelName);
			}
			else if(typeTable.containsKey(operand) && typeTable.get(operand).equals("FLOAT")) {

				if(val.matches("(\\d+(?:\\.\\d+)?$)|([A-Za-z]+$)")) {
					tempList.appendIRNode("STOREF", val, "", ("$T" + Integer.toString(Listener.tempRegNum)));
				}
				else {
					parseExp(expression, operand);
				}
				String operand_old = operand;
				operand = getTempRegName(operand);
				if(operand.contains("null"))
					operand = operand_old;
				tempList.appendIRNode(logOperatorTable.get(operator), operand, ("$T" + Integer.toString(Listener.tempRegNum)), labelName);
			}
			else {
				tempList.appendIRNode("STOREI", "1", "", ("$T" + Integer.toString(Listener.tempRegNum)));
				Listener.tempRegNum += 1;
				tempList.appendIRNode("STOREI", "1", "", ("$T" + Integer.toString(Listener.tempRegNum)));
				tempList.appendIRNode("NE", ("$T" + Integer.toString((Listener.tempRegNum - 1))), ("$T" + Integer.toString(Listener.tempRegNum)), labelName);
			}
			Listener.tempRegNum += 1;
			Listener.labelNum += 1;
			labelName = "label" + Integer.toString(Listener.labelNum);
		}

		tempList.printList();
		ListIR.add(tempList);
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
		String operand_old = operand;
		operand = getTempRegName(operand);
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
					String tempReg = getTempRegName(expression);
					if(tempReg.contains("null"))
						tempReg = expression;
					tempList.appendIRNode("STOREI", tempReg, "", "$T" + Integer.toString(Listener.tempRegNum));
				}
				String resultReg = getTempRegName(result);
				if(resultReg.contains("null"))
					resultReg = result;
				tempList.appendIRNode("STOREI", "$T" + Integer.toString(Listener.tempRegNum), "", resultReg);
				Listener.tempRegNum += 1;
			}
			else {
				tempList = pdt.inOrderTraverse(tempList, node);
				Listener.tempRegNum -= 1;
				String resultReg = getTempRegName(result);
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
					String tempReg = getTempRegName(expression);
					if(tempReg.contains("null"))
						tempReg = expression;
					tempList.appendIRNode("STOREF", tempReg, "", "$T" + Integer.toString(Listener.tempRegNum));
				}
				String resultReg = getTempRegName(result);
				if(resultReg.contains("null"))
					resultReg = result;
				tempList.appendIRNode("STOREF", "$T" + Integer.toString(Listener.tempRegNum), "", resultReg);
				Listener.tempRegNum += 1;
			}
			else {
				tempList = pdt.inOrderTraverseFloat(tempList, node);
				Listener.tempRegNum -= 1;
				String resultReg = getTempRegName(result);
				if(resultReg.contains("null"))
					resultReg = result;
				tempList.appendIRNode("STOREF", "$T" + Integer.toString(Listener.tempRegNum), "", resultReg);
				Listener.tempRegNum += 1;
			}
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
				varList[i] = getTempRegName(varList[i]);
				if(varList[i].contains("null"))
					varList[i] = old_val;
				tempList.appendIRNode("WRITEI", varList[i], "", "");
			}
			else if(typeTable.get(varList[i]).equals("FLOAT")) {
				String old_val = varList[i];
				varList[i] = getTempRegName(varList[i]);
				if(varList[i].contains("null"))
					varList[i] = old_val;
				tempList.appendIRNode("WRITEF", varList[i], "", "");
			}
			else {
				String old_val = varList[i];
				varList[i] = getTempRegName(varList[i]);
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
		for(int i = 0; i < varList.length; i++) {
			if(typeTable.get(varList[i]).equals("INT")) {
				String old_val = varList[i];
				varList[i] = getTempRegName(varList[i]);
				if(varList[i].contains("null"))
					varList[i] = old_val;
				tempList.appendIRNode("READI", varList[i], "", "");
			}
			else if(typeTable.get(varList[i]).equals("FLOAT")) {
				String old_val = varList[i];
				varList[i] = getTempRegName(varList[i]);
				if(varList[i].contains("null"))
					varList[i] = old_val;
				tempList.appendIRNode("READF", varList[i], "", "");
			}
			else {
				String old_val = varList[i];
				varList[i] = getTempRegName(varList[i]);
				if(varList[i].contains("null"))
					varList[i] = old_val;
				tempList.appendIRNode("READS", varList[i], "", "");
			}
		}
		tempList.printList();
		ListIR.add(tempList);
	}

	@Override public void exitFunc_decl(MicroParser.Func_declContext ctx) {
		//Listener.SymbolList.printSymbolList();
		System.out.println();
	}

	@Override public void enterReturn_stmt(MicroParser.Return_stmtContext ctx) {
		String ret_val = ctx.getChild(1).getText();
		IRList tempList = new IRList();
		if(ret_val.matches("\\d+(?:\\.\\d+)?$") && retType == 2) {
			tempList.appendIRNode("STOREF", ret_val, "", "$T" + Integer.toString(Listener.tempRegNum));
			tempList.appendIRNode("STOREF", "$T" + Integer.toString(Listener.tempRegNum), "", "$R");
			Listener.tempRegNum ++;
		}
		else if(ret_val.matches("\\d+(?:\\.\\d+)?$") && retType == 1) {
			tempList.appendIRNode("STOREI", ret_val, "", "$T" + Integer.toString(Listener.tempRegNum));
			tempList.appendIRNode("STOREI", "$T" + Integer.toString(Listener.tempRegNum), "", "$R");
			Listener.tempRegNum ++;
		}
		else if(!ret_val.matches("\\d+(?:\\.\\d+)?$") && retType == 2) {
			String old_val = ret_val;
			ret_val = getTempRegName(ret_val);
			if(ret_val.contains("null"))
				ret_val = old_val;
			tempList.appendIRNode("STOREF", ret_val, "", "$R");
		}
		else if(!ret_val.matches("\\d+(?:\\.\\d+)?$") && retType == 1) {
			String old_val = ret_val;
			ret_val = getTempRegName(ret_val);
			if(ret_val.contains("null"))
				ret_val = old_val;
			tempList.appendIRNode("STOREF", ret_val, "", "$R");
		}
		else {}
		tempList.appendIRNode("RET", "", "", "");
		tempList.printList();
		ListIR.add(tempList);
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