import java.io.*;
import org.antlr.v4.runtime.tree.*;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.ParseInfo;
import java.util.*;
import java.lang.*;
public class Micro {
	public static void main(String[] args) throws IOException{
			String filename = "";
			if(args.length > 0) 
				filename = args[0];
			MicroLexer lexEngine = new MicroLexer(new ANTLRFileStream(filename));
			CommonTokenStream tokenStream = new CommonTokenStream(lexEngine);
			tokenStream.fill();
			MicroParser parser = new MicroParser(tokenStream);
			try {
				MicroParser.ProgramContext context = parser.program();
				ParseTreeWalker walker = new ParseTreeWalker();
				Listener listener = new Listener();
				walker.walk(listener, context);
			}
			catch(Exception e) {
				
			}
	}
}