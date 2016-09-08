import java.io.*;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.tool.Grammar;
import java.util.*;
public class Micro {
	public static void main(String[] args) throws IOException{
			String filename = "";
			if(args.length > 0) 
				filename = args[0];
			//final Grammar g = Grammar.load("Micro.g4");
			MicroLexer lexEngine = new MicroLexer(new ANTLRFileStream(filename));
			Token token = lexEngine.nextToken();
			while(!token.getText().equals("<EOF>")) {
				if(token.getType() == 3)
					System.out.println("Token Type: KEYWORD\nValue: " + token.getText());
				else if(token.getType() == 4)
					System.out.println("Token Type: OPERATOR\nValue: " + token.getText());				
				else if(token.getType() == 6)
					System.out.println("Token Type: IDENTIFIER\nValue: " + token.getText());
				else if(token.getType() == 7)
					System.out.println("Token Type: FLOATLITERAL\nValue: " + token.getText());
				else if(token.getType() == 8)
					System.out.println("Token Type: INTLITERAL\nValue: " + token.getText());
				else if(token.getType() == 9)
					System.out.println("Token Type: STRINGLITERAL\nValue: " + token.getText());
				else {}
					
				token = lexEngine.nextToken();				
			}
	}
}
