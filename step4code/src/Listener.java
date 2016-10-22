import java.io.*;
import java.lang.*;
import java.util.*;

public class Listener extends MicroBaseListener {
	
	private SymbolTableList SymbolList = new SymbolTableList();
	private String Var_type;
	public static int tempRegNum = 1;
	private List <IRList> ListIR = new ArrayList<IRList>();
	@Override
	public void enterPgm_body(MicroParser.Pgm_bodyContext ctx) {
		SymbolList.pushNewSymbolTable("GLOBAL");	
	}
	@Override
	public void exitPgm_body(MicroParser.Pgm_bodyContext ctx) {
		//SymbolList.printSymbolList();	
		//ListIR.printList();
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

				if(opcode.contains("STORE")) {
					System.out.print("move");
					System.out.print(" " + operand1);
					System.out.println(" " + result);
				}
				else if(opcode.contains("ADD")) {
					System.out.print("move");
					System.out.print(" " + operand1);
					System.out.println(" " + result);

					System.out.println("addi " + operand2 + " " + result);					
				}
				else if(opcode.contains("SUB")) {
					System.out.print("move");
					System.out.print(" " + operand1);
					System.out.println(" " + result);

					System.out.println("subi " + operand2 + " " + result);					
				}
				else if(opcode.contains("MULT")) {
					System.out.print("move");
					System.out.print(" " + operand1);
					System.out.println(" " + result);

					System.out.println("muli " + operand2 + " " + result);					
				}
				else if(opcode.contains("DIV")) {
					System.out.print("move");
					System.out.print(" " + operand1);
					System.out.println(" " + result);

					System.out.println("divi " + operand2 + " " + result);					
				}
				else {
					System.out.println("sys writei " + operand1);
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
	}
	@Override
	public void enterElse_part(MicroParser.Else_partContext ctx) {
		if(!ctx.getText().equals(""))
			SymbolList.pushNewSymbolTable("BLOCK");
	}
	@Override
	public void enterDo_while_stmt(MicroParser.Do_while_stmtContext ctx) {
		SymbolList.pushNewSymbolTable("BLOCK");
	}
	@Override 
	public void enterAssign_expr(MicroParser.Assign_exprContext ctx) {

		String expression = ctx.getText().split(":=")[1];
		String result = ctx.getText().split(":=")[0];
			// IRList tempList = new IRList();
			// tempList.appendIRNode("STOREI", expression, "", "$T" + Integer.toString(Listener.tempRegNum));
			// tempList.appendIRNode("STOREI", "$T" + Integer.toString(Listener.tempRegNum), "", result);
			// Listener.tempRegNum += 1;
			// tempList.printList();
		ExpressionStack expstack = new ExpressionStack();
		String expr = expstack.createExprStack(expression);
		PemdasTree pdt = new PemdasTree();
		Node node = pdt.createBinaryTree(expr);
		IRList tempList = new IRList();
		if(node.getLeftNode() == null && node.getRightNode() == null) {
			tempList.appendIRNode("STOREI", expression, "", "$T" + Integer.toString(Listener.tempRegNum));
			tempList.appendIRNode("STOREI", "$T" + Integer.toString(Listener.tempRegNum), "", result);
			Listener.tempRegNum += 1;
			tempList.printList();
			ListIR.add(tempList);
		}
		else {
			tempList = pdt.inOrderTraverse(tempList, node);
			Listener.tempRegNum -= 1;
			tempList.appendIRNode("STOREI", "$T" + Integer.toString(Listener.tempRegNum), "", result);
			Listener.tempRegNum += 1;
			tempList.printList();
			//System.out.println("\n\n");
			ListIR.add(tempList);
		}
	}

		@Override 
		public void enterWrite_stmt(MicroParser.Write_stmtContext ctx) { 
			IRList tempList = new IRList();
			tempList.appendIRNode("WRITEI", ctx.getChild(2).getText(), "", "");
			tempList.printList();
			ListIR.add(tempList);
		}
	
}
