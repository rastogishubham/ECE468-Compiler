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
        this.createOperatorTableFloat();
    }

    public void createOperatorTable() {
        this.OperatorTable.put("*", "MULTI");
        this.OperatorTable.put("/", "DIVI");
        this.OperatorTable.put("-", "SUBI");
        this.OperatorTable.put("+", "ADDI");
    }

    public void createOperatorTableFloat() {
        this.OperatorTableFloat.put("*", "MULTF");
        this.OperatorTableFloat.put("/", "DIVF");
        this.OperatorTableFloat.put("-", "SUBF");
        this.OperatorTableFloat.put("+", "ADDF");
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

    public IRList inOrderTraverse(IRList listIR, Node exprTree) {
        if(exprTree.getLeftNode() != null) {
            inOrderTraverse(listIR, exprTree.getLeftNode());
        }
        if(exprTree.getRightNode() != null) {
            inOrderTraverse(listIR, exprTree.getRightNode());
        }

        if(this.OperatorTable.containsKey(exprTree.getValue())) { 
            String opcode = this.OperatorTable.get(exprTree.getValue()); 
            String operand1 = exprTree.getLeftNode().getValue(); 
            String operand2 = exprTree.getRightNode().getValue(); 
            String result = "$T" + Integer.toString(Listener.tempRegNum); 

            if(operand1.matches("\\d+(?:\\.\\d+)?$")) {
                listIR.appendIRNode("STOREI", exprTree.getLeftNode().getValue(), "", result);
                exprTree.getLeftNode().setValue(result);
                Listener.tempRegNum += 1;
            }
            if(operand2.matches("\\d+(?:\\.\\d+)?$")) {
                result = "$T" + Integer.toString(Listener.tempRegNum);
                listIR.appendIRNode("STOREI", exprTree.getRightNode().getValue(), "", result);
                exprTree.getRightNode().setValue(result);
                Listener.tempRegNum += 1;
            }
            result = "$T" + Integer.toString(Listener.tempRegNum);
            SymbolTable tempTable = Listener.SymbolList.getSymbolTable();
            String scope = tempTable.getScope();

            if(scope.equals("GLOBAL") || (operand1.matches("\\$T\\d+$") && operand2.matches("\\$T\\d+$"))) {
                operand1 = exprTree.getLeftNode().getValue(); 
                operand2 = exprTree.getRightNode().getValue();
            }
            else {
                if(operand1.matches("\\$T\\d+$"))
                    operand1 = exprTree.getLeftNode().getValue();
                else {
                    String op1 = exprTree.getLeftNode().getValue();
                    operand1 = getTempRegName(op1);
                    if(operand1.contains("null"))
                        operand1 = op1;
                }
                if(operand2.matches("\\$T\\d+$")) {
                    operand2 = exprTree.getRightNode().getValue();
                }
                else {
                    String op2 = exprTree.getRightNode().getValue();
                    operand2 = getTempRegName(op2);
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

    public IRList inOrderTraverseFloat(IRList listIR, Node exprTree) {
        if(exprTree.getLeftNode() != null) {
            inOrderTraverseFloat(listIR, exprTree.getLeftNode());
        }
        if(exprTree.getRightNode() != null) {
            inOrderTraverseFloat(listIR, exprTree.getRightNode());
        }

        if(this.OperatorTableFloat.containsKey(exprTree.getValue())) { 
            String opcode = this.OperatorTableFloat.get(exprTree.getValue()); 
            String operand1 = exprTree.getLeftNode().getValue(); 
            String operand2 = exprTree.getRightNode().getValue(); 
            String result = "$T" + Integer.toString(Listener.tempRegNum); 

            if(operand1.matches("\\d+(?:\\.\\d+)?$")) {
                listIR.appendIRNode("STOREF", exprTree.getLeftNode().getValue(), "", result);
                exprTree.getLeftNode().setValue(result);
                Listener.tempRegNum += 1;
            }
            if(operand2.matches("\\d+(?:\\.\\d+)?$")) {
                listIR.appendIRNode("STOREF", exprTree.getRightNode().getValue(), "", result);
                exprTree.getRightNode().setValue(result);
                Listener.tempRegNum += 1;
            }
            result = "$T" + Integer.toString(Listener.tempRegNum);
            SymbolTable tempTable = Listener.SymbolList.getSymbolTable();
            String scope = tempTable.getScope();

            if(scope.equals("GLOBAL") || (operand1.matches("\\$T\\d+$") && operand2.matches("\\$T\\d+$"))) {
                operand1 = exprTree.getLeftNode().getValue(); 
                operand2 = exprTree.getRightNode().getValue();
            }
            else {
                if(operand1.matches("\\$T\\d+$"))
                    operand1 = exprTree.getLeftNode().getValue();
                else {
                    String op1 = exprTree.getLeftNode().getValue();
                    operand1 = getTempRegName(op1);
                    if(operand1.contains("null"))
                        operand1 = op1;
                }
                if(operand2.matches("\\$T\\d+$")) {
                    operand2 = exprTree.getRightNode().getValue();
                }
                else {
                    String op2 = exprTree.getRightNode().getValue();
                    operand2 = getTempRegName(op2);
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