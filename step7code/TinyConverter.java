import java.io.*;
import java.lang.*;
import java.util.*;

public class TinyConverter
{ 
    IRList list; 

    public TinyConverter(IRList irlist) 
    {
         this.list = irlist ; 
    }

    public void printTinyCode(IRList tempList) 
    {
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
                else if(opcode.contains("STRING")) { // add to IR List generator
                   
                    //System.out.println("jeq " + result);
                    System.out.println("str " + operand1 + " " + operand2);
                }
                else if( opcode.contains("LINK")) { 
                    System.out.println("push");
                    System.out.println("push r0"); 
                    System.out.println("push r1"); 
                    System.out.println("push r2"); 
                    System.out.println("push r"); 

                    System.out.println("link");
                } 
            }
        }
        System.out.println("sys halt");
    }
}