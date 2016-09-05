// Generated from Micro.g4 by ANTLR 4.5.3
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class MicroLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.5.3", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, COMMENT=2, KEYWORDS=3, OPERATORS=4, EMPTY=5, IDENTIFIERS=6, FLOATLITERAL=7, 
		INTLITERAL=8, STRINGLITERAL=9;
	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] ruleNames = {
		"T__0", "COMMENT", "KEYWORDS", "OPERATORS", "EMPTY", "IDENTIFIERS", "FLOATLITERAL", 
		"INTLITERAL", "STRINGLITERAL"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "'WATER'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, null, "COMMENT", "KEYWORDS", "OPERATORS", "EMPTY", "IDENTIFIERS", 
		"FLOATLITERAL", "INTLITERAL", "STRINGLITERAL"
	};
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}


	public MicroLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "Micro.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public static final String _serializedATN =
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\2\13\u00ba\b\1\4\2"+
		"\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\3"+
		"\2\3\2\3\2\3\2\3\2\3\2\3\3\3\3\3\3\3\3\7\3 \n\3\f\3\16\3#\13\3\3\3\3\3"+
		"\3\3\3\3\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3"+
		"\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4"+
		"\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3"+
		"\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4"+
		"\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3"+
		"\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\5\4\u008a\n\4\3\5\3\5\3"+
		"\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\5\5\u0096\n\5\3\6\3\6\3\7\3\7\7\7\u009c"+
		"\n\7\f\7\16\7\u009f\13\7\3\b\7\b\u00a2\n\b\f\b\16\b\u00a5\13\b\3\b\3\b"+
		"\6\b\u00a9\n\b\r\b\16\b\u00aa\3\t\6\t\u00ae\n\t\r\t\16\t\u00af\3\n\3\n"+
		"\7\n\u00b4\n\n\f\n\16\n\u00b7\13\n\3\n\3\n\4!\u00b5\2\13\3\3\5\4\7\5\t"+
		"\6\13\7\r\b\17\t\21\n\23\13\3\2\b\6\2,-//\61\61??\6\2*+..=>@@\5\2\13\f"+
		"\16\17\"\"\4\2C\\c|\5\2\62;C\\c|\3\2\62;\u00d7\2\3\3\2\2\2\2\5\3\2\2\2"+
		"\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3"+
		"\2\2\2\2\23\3\2\2\2\3\25\3\2\2\2\5\33\3\2\2\2\7\u0089\3\2\2\2\t\u0095"+
		"\3\2\2\2\13\u0097\3\2\2\2\r\u0099\3\2\2\2\17\u00a3\3\2\2\2\21\u00ad\3"+
		"\2\2\2\23\u00b1\3\2\2\2\25\26\7Y\2\2\26\27\7C\2\2\27\30\7V\2\2\30\31\7"+
		"G\2\2\31\32\7T\2\2\32\4\3\2\2\2\33\34\7/\2\2\34\35\7/\2\2\35!\3\2\2\2"+
		"\36 \13\2\2\2\37\36\3\2\2\2 #\3\2\2\2!\"\3\2\2\2!\37\3\2\2\2\"$\3\2\2"+
		"\2#!\3\2\2\2$%\7\f\2\2%&\3\2\2\2&\'\b\3\2\2\'\6\3\2\2\2()\7R\2\2)*\7T"+
		"\2\2*+\7Q\2\2+,\7I\2\2,-\7T\2\2-.\7C\2\2.\u008a\7O\2\2/\60\7D\2\2\60\61"+
		"\7G\2\2\61\62\7I\2\2\62\63\7K\2\2\63\u008a\7P\2\2\64\65\7G\2\2\65\66\7"+
		"P\2\2\66\u008a\7F\2\2\678\7H\2\289\7W\2\29:\7P\2\2:;\7E\2\2;<\7V\2\2<"+
		"=\7K\2\2=>\7Q\2\2>\u008a\7P\2\2?@\7T\2\2@A\7G\2\2AB\7C\2\2B\u008a\7F\2"+
		"\2CD\7Y\2\2DE\7T\2\2EF\7K\2\2FG\7V\2\2G\u008a\7G\2\2HI\7K\2\2I\u008a\7"+
		"H\2\2JK\7G\2\2KL\7N\2\2LM\7U\2\2MN\7K\2\2N\u008a\7H\2\2OP\7G\2\2PQ\7P"+
		"\2\2QR\7F\2\2RS\7K\2\2S\u008a\7H\2\2TU\7F\2\2U\u008a\7Q\2\2VW\7Y\2\2W"+
		"X\7J\2\2XY\7K\2\2YZ\7N\2\2Z\u008a\7G\2\2[\\\7E\2\2\\]\7Q\2\2]^\7P\2\2"+
		"^_\7V\2\2_`\7K\2\2`a\7P\2\2ab\7W\2\2b\u008a\7G\2\2cd\7D\2\2de\7T\2\2e"+
		"f\7G\2\2fg\7C\2\2g\u008a\7M\2\2hi\7T\2\2ij\7G\2\2jk\7V\2\2kl\7W\2\2lm"+
		"\7T\2\2m\u008a\7P\2\2no\7K\2\2op\7P\2\2p\u008a\7V\2\2qr\7X\2\2rs\7Q\2"+
		"\2st\7K\2\2t\u008a\7F\2\2uv\7U\2\2vw\7V\2\2wx\7T\2\2xy\7K\2\2yz\7P\2\2"+
		"z\u008a\7I\2\2{|\7H\2\2|}\7N\2\2}~\7Q\2\2~\177\7C\2\2\177\u008a\7V\2\2"+
		"\u0080\u0081\7V\2\2\u0081\u0082\7T\2\2\u0082\u0083\7W\2\2\u0083\u008a"+
		"\7G\2\2\u0084\u0085\7H\2\2\u0085\u0086\7C\2\2\u0086\u0087\7N\2\2\u0087"+
		"\u0088\7U\2\2\u0088\u008a\7G\2\2\u0089(\3\2\2\2\u0089/\3\2\2\2\u0089\64"+
		"\3\2\2\2\u0089\67\3\2\2\2\u0089?\3\2\2\2\u0089C\3\2\2\2\u0089H\3\2\2\2"+
		"\u0089J\3\2\2\2\u0089O\3\2\2\2\u0089T\3\2\2\2\u0089V\3\2\2\2\u0089[\3"+
		"\2\2\2\u0089c\3\2\2\2\u0089h\3\2\2\2\u0089n\3\2\2\2\u0089q\3\2\2\2\u0089"+
		"u\3\2\2\2\u0089{\3\2\2\2\u0089\u0080\3\2\2\2\u0089\u0084\3\2\2\2\u008a"+
		"\b\3\2\2\2\u008b\u008c\7<\2\2\u008c\u0096\7?\2\2\u008d\u0096\t\2\2\2\u008e"+
		"\u008f\7#\2\2\u008f\u0096\7?\2\2\u0090\u0096\t\3\2\2\u0091\u0092\7>\2"+
		"\2\u0092\u0096\7?\2\2\u0093\u0094\7@\2\2\u0094\u0096\7?\2\2\u0095\u008b"+
		"\3\2\2\2\u0095\u008d\3\2\2\2\u0095\u008e\3\2\2\2\u0095\u0090\3\2\2\2\u0095"+
		"\u0091\3\2\2\2\u0095\u0093\3\2\2\2\u0096\n\3\2\2\2\u0097\u0098\t\4\2\2"+
		"\u0098\f\3\2\2\2\u0099\u009d\t\5\2\2\u009a\u009c\t\6\2\2\u009b\u009a\3"+
		"\2\2\2\u009c\u009f\3\2\2\2\u009d\u009b\3\2\2\2\u009d\u009e\3\2\2\2\u009e"+
		"\16\3\2\2\2\u009f\u009d\3\2\2\2\u00a0\u00a2\t\7\2\2\u00a1\u00a0\3\2\2"+
		"\2\u00a2\u00a5\3\2\2\2\u00a3\u00a1\3\2\2\2\u00a3\u00a4\3\2\2\2\u00a4\u00a6"+
		"\3\2\2\2\u00a5\u00a3\3\2\2\2\u00a6\u00a8\7\60\2\2\u00a7\u00a9\t\7\2\2"+
		"\u00a8\u00a7\3\2\2\2\u00a9\u00aa\3\2\2\2\u00aa\u00a8\3\2\2\2\u00aa\u00ab"+
		"\3\2\2\2\u00ab\20\3\2\2\2\u00ac\u00ae\t\7\2\2\u00ad\u00ac\3\2\2\2\u00ae"+
		"\u00af\3\2\2\2\u00af\u00ad\3\2\2\2\u00af\u00b0\3\2\2\2\u00b0\22\3\2\2"+
		"\2\u00b1\u00b5\7$\2\2\u00b2\u00b4\13\2\2\2\u00b3\u00b2\3\2\2\2\u00b4\u00b7"+
		"\3\2\2\2\u00b5\u00b6\3\2\2\2\u00b5\u00b3\3\2\2\2\u00b6\u00b8\3\2\2\2\u00b7"+
		"\u00b5\3\2\2\2\u00b8\u00b9\7$\2\2\u00b9\24\3\2\2\2\13\2!\u0089\u0095\u009d"+
		"\u00a3\u00aa\u00af\u00b5\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}