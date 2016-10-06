import java.io.*;
import org.antlr.runtime.tree.*;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.ParseInfo;
import java.util.*;
import java.lang.*;
public class Micro {
	public static void main(String[] args) throws IOException{
			String filename = "";
			if(args.length > 0) 
				filename = args[0];
			//final Grammar g = Grammar.load("Micro.g4");
			MicroLexer lexEngine = new MicroLexer(new ANTLRFileStream(filename));
		//	Token token = lexEngine.nextToken();
			CommonTokenStream tokenStream = new CommonTokenStream(lexEngine);
			tokenStream.fill();
			MicroParser parser = new MicroParser(tokenStream);
			try {
				//parser.setErrorHandler(new BailErrorStrategy());
				//parser.removeErrorListeners();				
				parser.program();
				System.out.println("Accepted");
			}
			catch(Exception e) {
				System.out.println("Not Accepted");
			}
	}
}
