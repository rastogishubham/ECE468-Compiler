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
        this.OperatorTable.put("*", "MULTF");
        this.OperatorTable.put("/", "DIVF");
        this.OperatorTable.put("-", "SUBF");
        this.OperatorTable.put("+", "ADDF");
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
            else if(operand2.matches("\\d+(?:\\.\\d+)?$")) {
                listIR.appendIRNode("STOREI", exprTree.getRightNode().getValue(), "", result);
                exprTree.getRightNode().setValue(result);
                Listener.tempRegNum += 1;
            }
            result = "$T" + Integer.toString(Listener.tempRegNum);
            operand1 = exprTree.getLeftNode().getValue(); 
            operand2 = exprTree.getRightNode().getValue();
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
            else if(operand2.matches("\\d+(?:\\.\\d+)?$")) {
                listIR.appendIRNode("STOREF", exprTree.getRightNode().getValue(), "", result);
                exprTree.getRightNode().setValue(result);
                Listener.tempRegNum += 1;
            }
            result = "$T" + Integer.toString(Listener.tempRegNum);
            operand1 = exprTree.getLeftNode().getValue(); 
            operand2 = exprTree.getRightNode().getValue();
            listIR.appendIRNode(new IRNode(opcode, operand1, operand2, result));
            exprTree.setValue(result);
            Listener.tempRegNum += 1;
        }
        return listIR;
    }
}