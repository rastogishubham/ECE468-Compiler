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
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\2\13\u00b7\b\1\4\2"+
		"\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\3"+
		"\2\3\2\3\2\3\2\3\2\3\2\3\3\3\3\3\3\3\3\7\3 \n\3\f\3\16\3#\13\3\3\4\3\4"+
		"\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3"+
		"\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4"+
		"\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3"+
		"\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4"+
		"\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3"+
		"\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\5\4\u0087\n\4\3\5\3\5\3\5\3\5\3\5\3"+
		"\5\3\5\3\5\3\5\3\5\5\5\u0093\n\5\3\6\3\6\3\7\3\7\7\7\u0099\n\7\f\7\16"+
		"\7\u009c\13\7\3\b\7\b\u009f\n\b\f\b\16\b\u00a2\13\b\3\b\3\b\6\b\u00a6"+
		"\n\b\r\b\16\b\u00a7\3\t\6\t\u00ab\n\t\r\t\16\t\u00ac\3\n\3\n\7\n\u00b1"+
		"\n\n\f\n\16\n\u00b4\13\n\3\n\3\n\4!\u00b2\2\13\3\3\5\4\7\5\t\6\13\7\r"+
		"\b\17\t\21\n\23\13\3\2\b\6\2,-//\61\61??\6\2*+..=>@@\5\2\13\f\16\17\""+
		"\"\4\2C\\c|\5\2\62;C\\c|\3\2\62;\u00d4\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2"+
		"\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2\2\2\2"+
		"\23\3\2\2\2\3\25\3\2\2\2\5\33\3\2\2\2\7\u0086\3\2\2\2\t\u0092\3\2\2\2"+
		"\13\u0094\3\2\2\2\r\u0096\3\2\2\2\17\u00a0\3\2\2\2\21\u00aa\3\2\2\2\23"+
		"\u00ae\3\2\2\2\25\26\7Y\2\2\26\27\7C\2\2\27\30\7V\2\2\30\31\7G\2\2\31"+
		"\32\7T\2\2\32\4\3\2\2\2\33\34\7/\2\2\34\35\7/\2\2\35!\3\2\2\2\36 \13\2"+
		"\2\2\37\36\3\2\2\2 #\3\2\2\2!\"\3\2\2\2!\37\3\2\2\2\"\6\3\2\2\2#!\3\2"+
		"\2\2$%\7R\2\2%&\7T\2\2&\'\7Q\2\2\'(\7I\2\2()\7T\2\2)*\7C\2\2*\u0087\7"+
		"O\2\2+,\7D\2\2,-\7G\2\2-.\7I\2\2./\7K\2\2/\u0087\7P\2\2\60\61\7G\2\2\61"+
		"\62\7P\2\2\62\u0087\7F\2\2\63\64\7H\2\2\64\65\7W\2\2\65\66\7E\2\2\66\67"+
		"\7P\2\2\678\7V\2\289\7K\2\29:\7Q\2\2:\u0087\7P\2\2;<\7T\2\2<=\7G\2\2="+
		">\7C\2\2>\u0087\7F\2\2?@\7Y\2\2@A\7T\2\2AB\7K\2\2BC\7V\2\2C\u0087\7G\2"+
		"\2DE\7K\2\2E\u0087\7H\2\2FG\7G\2\2GH\7N\2\2HI\7U\2\2IJ\7G\2\2JK\7K\2\2"+
		"K\u0087\7H\2\2LM\7G\2\2MN\7P\2\2NO\7F\2\2OP\7K\2\2P\u0087\7H\2\2QR\7F"+
		"\2\2R\u0087\7Q\2\2ST\7Y\2\2TU\7J\2\2UV\7K\2\2VW\7N\2\2W\u0087\7G\2\2X"+
		"Y\7E\2\2YZ\7Q\2\2Z[\7P\2\2[\\\7V\2\2\\]\7K\2\2]^\7P\2\2^_\7W\2\2_\u0087"+
		"\7G\2\2`a\7D\2\2ab\7T\2\2bc\7G\2\2cd\7C\2\2d\u0087\7M\2\2ef\7T\2\2fg\7"+
		"G\2\2gh\7V\2\2hi\7W\2\2ij\7T\2\2j\u0087\7P\2\2kl\7K\2\2lm\7P\2\2m\u0087"+
		"\7V\2\2no\7X\2\2op\7Q\2\2pq\7K\2\2q\u0087\7F\2\2rs\7U\2\2st\7V\2\2tu\7"+
		"T\2\2uv\7K\2\2vw\7P\2\2w\u0087\7I\2\2xy\7H\2\2yz\7N\2\2z{\7Q\2\2{|\7C"+
		"\2\2|\u0087\7V\2\2}~\7V\2\2~\177\7T\2\2\177\u0080\7W\2\2\u0080\u0087\7"+
		"G\2\2\u0081\u0082\7H\2\2\u0082\u0083\7C\2\2\u0083\u0084\7N\2\2\u0084\u0085"+
		"\7U\2\2\u0085\u0087\7G\2\2\u0086$\3\2\2\2\u0086+\3\2\2\2\u0086\60\3\2"+
		"\2\2\u0086\63\3\2\2\2\u0086;\3\2\2\2\u0086?\3\2\2\2\u0086D\3\2\2\2\u0086"+
		"F\3\2\2\2\u0086L\3\2\2\2\u0086Q\3\2\2\2\u0086S\3\2\2\2\u0086X\3\2\2\2"+
		"\u0086`\3\2\2\2\u0086e\3\2\2\2\u0086k\3\2\2\2\u0086n\3\2\2\2\u0086r\3"+
		"\2\2\2\u0086x\3\2\2\2\u0086}\3\2\2\2\u0086\u0081\3\2\2\2\u0087\b\3\2\2"+
		"\2\u0088\u0089\7<\2\2\u0089\u0093\7?\2\2\u008a\u0093\t\2\2\2\u008b\u008c"+
		"\7#\2\2\u008c\u0093\7?\2\2\u008d\u0093\t\3\2\2\u008e\u008f\7>\2\2\u008f"+
		"\u0093\7?\2\2\u0090\u0091\7@\2\2\u0091\u0093\7?\2\2\u0092\u0088\3\2\2"+
		"\2\u0092\u008a\3\2\2\2\u0092\u008b\3\2\2\2\u0092\u008d\3\2\2\2\u0092\u008e"+
		"\3\2\2\2\u0092\u0090\3\2\2\2\u0093\n\3\2\2\2\u0094\u0095\t\4\2\2\u0095"+
		"\f\3\2\2\2\u0096\u009a\t\5\2\2\u0097\u0099\t\6\2\2\u0098\u0097\3\2\2\2"+
		"\u0099\u009c\3\2\2\2\u009a\u0098\3\2\2\2\u009a\u009b\3\2\2\2\u009b\16"+
		"\3\2\2\2\u009c\u009a\3\2\2\2\u009d\u009f\t\7\2\2\u009e\u009d\3\2\2\2\u009f"+
		"\u00a2\3\2\2\2\u00a0\u009e\3\2\2\2\u00a0\u00a1\3\2\2\2\u00a1\u00a3\3\2"+
		"\2\2\u00a2\u00a0\3\2\2\2\u00a3\u00a5\7\60\2\2\u00a4\u00a6\t\7\2\2\u00a5"+
		"\u00a4\3\2\2\2\u00a6\u00a7\3\2\2\2\u00a7\u00a5\3\2\2\2\u00a7\u00a8\3\2"+
		"\2\2\u00a8\20\3\2\2\2\u00a9\u00ab\t\7\2\2\u00aa\u00a9\3\2\2\2\u00ab\u00ac"+
		"\3\2\2\2\u00ac\u00aa\3\2\2\2\u00ac\u00ad\3\2\2\2\u00ad\22\3\2\2\2\u00ae"+
		"\u00b2\7$\2\2\u00af\u00b1\13\2\2\2\u00b0\u00af\3\2\2\2\u00b1\u00b4\3\2"+
		"\2\2\u00b2\u00b3\3\2\2\2\u00b2\u00b0\3\2\2\2\u00b3\u00b5\3\2\2\2\u00b4"+
		"\u00b2\3\2\2\2\u00b5\u00b6\7$\2\2\u00b6\24\3\2\2\2\13\2!\u0086\u0092\u009a"+
		"\u00a0\u00a7\u00ac\u00b2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}