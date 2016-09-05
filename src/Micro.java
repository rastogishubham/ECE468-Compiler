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
			final Grammar g = Grammar.load("/home/shay/a/rastogis/Compiler/Micro.g4");
			LexerInterpreter lexEngine = g.createLexerInterpreter(new ANTLRFileStream(filename));
			while(!lexEngine.nextToken().getText().equals("<EOF>")) {	
				System.out.print("Token Type: ");
				Token tokens = lexEngine.nextToken();
				//if(tokens.getText() == "IDENTIFIER")
				System.out.println(tokens);
			}
	}
}
