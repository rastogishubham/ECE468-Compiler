import java.io.*;
import java.lang.*;
import java.util.*;

public class TinyConverter  { 
    
    private boolean enter = true;
    private int localCount = 1; // negative
    private int paramCount = 6; // pointer on stack starting at 6
    private int tempCount = 0; // r 
    private int funcCount = 0;
    int temp = 0; 
    private Hashtable <String, String> registerTypeTable = new Hashtable<String, String>();
    private Hashtable<String, String> typeTable = new Hashtable<String, String>();
    public void printTinyCode(IRList tempList) {
            for(int j = 0; j < tempList.getSize(); j++) 
            {
                IRNode tempNode = tempList.getIRNode(j);
                String opcode = tempNode.getOpcode();
                String operand1 = tempNode.getOperand1();
                String operand2 = tempNode.getOperand2();
                String result = tempNode.getResult();

                if(operand1.matches("\\$T\\d+$")) {
                    temp = Integer.parseInt(operand1.split("T")[1]); 
                    temp = temp - 1; 
                    operand1 = "r" + Integer.toString(temp);
                }

                if(operand1.matches("\\$L\\d+$")) {
                    operand1 = "$-" + operand1.split("L")[1];
                    localCount++; 
                }

                if(operand1.matches("\\$P\\d+$")) {
                    operand1 = "$" + Integer.toString(paramCount); // change number to include original number 
                    paramCount++; 
                }
                if(operand1.matches("\\$R\\d+$")) {
                    operand1 = "$" + Integer.toString(paramCount);
                    paramCount++; 
                }
                 if(operand2.matches("\\$T\\d+$")) {
                    temp = Integer.parseInt(operand2.split("T")[1]); 
                    temp = temp - 1; 
                    operand2 = "r" + Integer.toString(temp);
                }

                if(operand2.matches("\\$L\\d+$")) {
                    operand2 = "$-" + operand2.split("L")[1];
                    localCount++; 
                }

                if(operand2.matches("\\$P\\d+$")) {
                    operand2 = "$" + Integer.toString(paramCount);
                    paramCount++; 
                }
                if(operand2.matches("\\$R")) {
                    operand2 = "$" + Integer.toString(paramCount);
                    paramCount++; 
                }
                

                 if(result.matches("\\$T\\d+$")) {
                    temp = Integer.parseInt(result.split("T")[1]); 
                    temp = temp - 1; 
                    result = "r" + Integer.toString(temp);
                }

                if(result.matches("\\$L\\d+$")) {
                    result = "$-" + result.split("L")[1];
                    localCount++; 
                }

                if(result.matches("\\$P\\d+$")) {
                    result = "$" + Integer.toString(paramCount);
                    paramCount++; 
                }
                if(result.matches("\\$R")) {
                    result = "$" + Integer.toString(paramCount);
                    paramCount++; 
                }
                

               if(opcode.contains("LINK")) { 
                    localCount = 1; // negative 
                    paramCount = 6; //starts at pointer 6  
                    tempCount = 0; // r
               }

                if(opcode.contains("LABEL") && enter) { 
                    System.out.println("push");
                    System.out.println("push r0");
                    System.out.println("push r1");
                    System.out.println("push r2");
                    System.out.println("push r3");
                    System.out.println("jsr main");
                    System.out.println("sys halt");
                    enter = false; 
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
                 else if(opcode.contains("WRITES")) {

                    if(operand1.matches("\\r\\d+$")) {
                        registerTypeTable.put(operand1, "INT");
                    }

                    System.out.println("sys writes " + operand1);
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
                    System.out.println(" " + operand2);
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
                else if(opcode.contains("STRING")) { // add to IR List generator
                   
                    //System.out.println("jeq " + result);
                    System.out.println("str " + operand1 + " " + operand2);
                }
                else if(opcode.contains("LINK")) { 
                    String localCount = Integer.toString(getLocalVarCount(Listener.funcList.get(funcCount)));
                    System.out.println("link " + localCount);
                    funcCount++;
                } 

                else if (opcode.contains("RET")) { 
                    System.out.println("unlnk"); 
                    System.out.println("ret");
                } 

                else  if (opcode.contains("PUSH")) { 
                    System.out.println("push " + operand1);
                }
                else if (opcode.contains("POP")) { 
                    System.out.println("pop " + operand1);
                }

                else if (opcode.contains("JSR")) { 
                    System.out.println("push r0");
                    System.out.println("push r1");
                    System.out.println("push r2");
                    System.out.println("push r3");
                    System.out.println("jsr " + operand1);
                    System.out.println("pop r3");
                    System.out.println("pop r2");
                    System.out.println("pop r1");
                    System.out.println("pop r0");
                } 
            }
    }
    
    public int getLocalVarCount(String funcName) {
        SymbolTable tempTable = Listener.SymbolList.getSymbolTable();
        String scope = tempTable.getScope();
        Hashtable <String, Symbol> varTable = tempTable.getVariableTable();
        int pos = Listener.SymbolList.getListLen();
        int localCount = 0;
        while(pos >= 0) {
            if(scope.equals(funcName))
                break;
            pos--;
            tempTable = Listener.SymbolList.getSymbolTable(pos);
            scope = tempTable.getScope();
            varTable = tempTable.getVariableTable();
        }
        for(int i = pos; i <= Listener.SymbolList.getListLen(); i++) {
            tempTable = Listener.SymbolList.getSymbolTable(i);
            scope = tempTable.getScope();
            varTable = tempTable.getVariableTable();
            List <String> tempList = tempTable.getNameList();
            if(!scope.equals(funcName) && !scope.contains("BLOCK"))
                return localCount;
            for(String varName : tempList) {
                String tempRegName = varTable.get(varName).getTempName();
                if(tempRegName.contains("$L"))
                    localCount++;
            }
        }
        return localCount;
    }
    
}