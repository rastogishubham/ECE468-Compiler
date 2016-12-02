import java.io.*;
import java.lang.*;
import java.util.*;

public class PemdasTree 
{
    private Stack <Node> operatorStack = new Stack<Node>();
    private Hashtable<String, String> OperatorTable = new Hashtable<String, String>();
    private Hashtable<String, String> OperatorTableFloat = new Hashtable<String, String>();
    public PemdasTree() {
        this.createOperatorTable();
    }

    public void createOperatorTable() {
        this.OperatorTable.put("*", "MULT");
        this.OperatorTable.put("/", "DIV");
        this.OperatorTable.put("-", "SUB");
        this.OperatorTable.put("+", "ADD");
    }
    
    public Node createBinaryTree(String expression) {
        String expr = expression.trim();
        String [] tokenArray = expr.split(" ");
        for(int i = 0; i < tokenArray.length; i++) {
            String token = tokenArray[i];
            if(this.OperatorTable.containsKey(token)) {

                Node Operand1 = operatorStack.pop();
                Node Operand2 = operatorStack.pop();
                Node tempNode = new Node(Operand1, Operand2, token);
                operatorStack.push(tempNode);
            }
            else {
                Node tempNode = new Node(null, null, token);
                operatorStack.push(tempNode);
            }
        }
        return operatorStack.pop();
    }

    public IRList inOrderTraverse(IRList listIR, Node exprTree , String iType) {
        String storeOpcode = "STORE" + iType;
        String expType = "";
        Helper help = new Helper();
        if(iType.equals("F"))
            expType = "FLOAT";
        else
            expType = "INT";
        if(exprTree.getLeftNode() != null) {
            inOrderTraverse(listIR, exprTree.getLeftNode(), iType);
        }
        if(exprTree.getRightNode() != null) {
            inOrderTraverse(listIR, exprTree.getRightNode(), iType);
        }

        if(this.OperatorTable.containsKey(exprTree.getValue())) { 
            String opcode = this.OperatorTable.get(exprTree.getValue()) + iType; 
            String operand1 = exprTree.getLeftNode().getValue(); 
            String operand2 = exprTree.getRightNode().getValue(); 
            String result = "$T" + Integer.toString(Listener.tempRegNum); 

            if(operand1.matches("\\d+(?:\\.\\d+)?$")) {
                listIR.appendIRNode(storeOpcode, exprTree.getLeftNode().getValue(), "", result);
                exprTree.getLeftNode().setValue(result);
                Listener.tempRegNum += 1;
            }
            else if(operand1.matches("\\w+\\(.*\\)$")) {       
                IRList tempList = help.generateFuncCall(operand1, expType);
                listIR.addAll(tempList.getList());
                exprTree.getLeftNode().setValue("$T" + Integer.toString(Listener.tempRegNum - 1));
            }
            if(operand2.matches("\\d+(?:\\.\\d+)?$")) {
                listIR.appendIRNode(storeOpcode, exprTree.getRightNode().getValue(), "", result);
                exprTree.getRightNode().setValue(result);
                Listener.tempRegNum += 1;
            }
            else if(operand2.matches("\\w+\\(.*\\)$")) {
                IRList tempList = help.generateFuncCall(operand2, expType);  
                listIR.addAll(tempList.getList());
                exprTree.getRightNode().setValue("$T" + Integer.toString(Listener.tempRegNum - 1));       
            }
            result = "$T" + Integer.toString(Listener.tempRegNum);
            SymbolTable tempTable = Listener.SymbolList.getSymbolTable();
            String scope = tempTable.getScope();

            if(scope.equals("GLOBAL") || (operand1.matches("\\$T\\d+$") && operand2.matches("\\$T\\d+$"))) {
                operand1 = exprTree.getLeftNode().getValue(); 
                operand2 = exprTree.getRightNode().getValue();
            }
            else {
                Helper Help = new Helper();
                if(operand1.matches("\\$T\\d+$") || operand1.matches("\\d+(?:\\.\\d+)?$"))
                    operand1 = exprTree.getLeftNode().getValue();
                else {
                    String op1 = exprTree.getLeftNode().getValue();
                    operand1 = Help.getTempRegName(op1);
                    if(operand1.contains("null"))
                        operand1 = op1;
                }
                if(operand2.matches("\\$T\\d+$") || operand2.matches("\\d+(?:\\.\\d+)?$")) {
                    operand2 = exprTree.getRightNode().getValue();
                }
                else {
                    String op2 = exprTree.getRightNode().getValue();
                    operand2 = Help.getTempRegName(op2);
                    if(operand2.contains("null"))
                        operand2 = op2;
                }
            }
            listIR.appendIRNode(new IRNode(opcode, operand1, operand2, result));
            exprTree.setValue(result);
            Listener.tempRegNum += 1;
        }
        return listIR;
    }
}