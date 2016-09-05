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
		T__0=1, KEYWORDS=2, OPERATORS=3, EMPTY=4, IDENTIFIERS=5, FLOATLITERAL=6, 
		INTLITERAL=7, STRINGLITERAL=8, COMMENT=9;
	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] ruleNames = {
		"T__0", "KEYWORDS", "OPERATORS", "EMPTY", "IDENTIFIERS", "FLOATLITERAL", 
		"INTLITERAL", "STRINGLITERAL", "COMMENT"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "'WATER'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, null, "KEYWORDS", "OPERATORS", "EMPTY", "IDENTIFIERS", "FLOATLITERAL", 
		"INTLITERAL", "STRINGLITERAL", "COMMENT"
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
		"\2\3\2\3\2\3\2\3\2\3\2\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3"+
		"\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3"+
		"\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3"+
		"\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3"+
		"\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3"+
		"\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\5\3~"+
		"\n\3\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\5\4\u008a\n\4\3\5\3\5\3\6"+
		"\3\6\7\6\u0090\n\6\f\6\16\6\u0093\13\6\3\7\7\7\u0096\n\7\f\7\16\7\u0099"+
		"\13\7\3\7\3\7\6\7\u009d\n\7\r\7\16\7\u009e\3\b\6\b\u00a2\n\b\r\b\16\b"+
		"\u00a3\3\t\3\t\7\t\u00a8\n\t\f\t\16\t\u00ab\13\t\3\t\3\t\3\n\3\n\3\n\3"+
		"\n\7\n\u00b3\n\n\f\n\16\n\u00b6\13\n\4\u00a9\u00b4\2\13\3\3\5\4\7\5\t"+
		"\6\13\7\r\b\17\t\21\n\23\13\3\2\b\6\2,-//\61\61??\6\2*+..=>@@\5\2\13\f"+
		"\16\17\"\"\4\2C\\c|\5\2\62;C\\c|\3\2\62;\u00d4\2\3\3\2\2\2\2\5\3\2\2\2"+
		"\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3"+
		"\2\2\2\2\23\3\2\2\2\3\25\3\2\2\2\5}\3\2\2\2\7\u0089\3\2\2\2\t\u008b\3"+
		"\2\2\2\13\u008d\3\2\2\2\r\u0097\3\2\2\2\17\u00a1\3\2\2\2\21\u00a5\3\2"+
		"\2\2\23\u00ae\3\2\2\2\25\26\7Y\2\2\26\27\7C\2\2\27\30\7V\2\2\30\31\7G"+
		"\2\2\31\32\7T\2\2\32\4\3\2\2\2\33\34\7R\2\2\34\35\7T\2\2\35\36\7Q\2\2"+
		"\36\37\7I\2\2\37 \7T\2\2 !\7C\2\2!~\7O\2\2\"#\7D\2\2#$\7G\2\2$%\7I\2\2"+
		"%&\7K\2\2&~\7P\2\2\'(\7G\2\2()\7P\2\2)~\7F\2\2*+\7H\2\2+,\7W\2\2,-\7E"+
		"\2\2-.\7P\2\2./\7V\2\2/\60\7K\2\2\60\61\7Q\2\2\61~\7P\2\2\62\63\7T\2\2"+
		"\63\64\7G\2\2\64\65\7C\2\2\65~\7F\2\2\66\67\7Y\2\2\678\7T\2\289\7K\2\2"+
		"9:\7V\2\2:~\7G\2\2;<\7K\2\2<~\7H\2\2=>\7G\2\2>?\7N\2\2?@\7U\2\2@A\7G\2"+
		"\2AB\7K\2\2B~\7H\2\2CD\7G\2\2DE\7P\2\2EF\7F\2\2FG\7K\2\2G~\7H\2\2HI\7"+
		"F\2\2I~\7Q\2\2JK\7Y\2\2KL\7J\2\2LM\7K\2\2MN\7N\2\2N~\7G\2\2OP\7E\2\2P"+
		"Q\7Q\2\2QR\7P\2\2RS\7V\2\2ST\7K\2\2TU\7P\2\2UV\7W\2\2V~\7G\2\2WX\7D\2"+
		"\2XY\7T\2\2YZ\7G\2\2Z[\7C\2\2[~\7M\2\2\\]\7T\2\2]^\7G\2\2^_\7V\2\2_`\7"+
		"W\2\2`a\7T\2\2a~\7P\2\2bc\7K\2\2cd\7P\2\2d~\7V\2\2ef\7X\2\2fg\7Q\2\2g"+
		"h\7K\2\2h~\7F\2\2ij\7U\2\2jk\7V\2\2kl\7T\2\2lm\7K\2\2mn\7P\2\2n~\7I\2"+
		"\2op\7H\2\2pq\7N\2\2qr\7Q\2\2rs\7C\2\2s~\7V\2\2tu\7V\2\2uv\7T\2\2vw\7"+
		"W\2\2w~\7G\2\2xy\7H\2\2yz\7C\2\2z{\7N\2\2{|\7U\2\2|~\7G\2\2}\33\3\2\2"+
		"\2}\"\3\2\2\2}\'\3\2\2\2}*\3\2\2\2}\62\3\2\2\2}\66\3\2\2\2};\3\2\2\2}"+
		"=\3\2\2\2}C\3\2\2\2}H\3\2\2\2}J\3\2\2\2}O\3\2\2\2}W\3\2\2\2}\\\3\2\2\2"+
		"}b\3\2\2\2}e\3\2\2\2}i\3\2\2\2}o\3\2\2\2}t\3\2\2\2}x\3\2\2\2~\6\3\2\2"+
		"\2\177\u0080\7<\2\2\u0080\u008a\7?\2\2\u0081\u008a\t\2\2\2\u0082\u0083"+
		"\7#\2\2\u0083\u008a\7?\2\2\u0084\u008a\t\3\2\2\u0085\u0086\7>\2\2\u0086"+
		"\u008a\7?\2\2\u0087\u0088\7@\2\2\u0088\u008a\7?\2\2\u0089\177\3\2\2\2"+
		"\u0089\u0081\3\2\2\2\u0089\u0082\3\2\2\2\u0089\u0084\3\2\2\2\u0089\u0085"+
		"\3\2\2\2\u0089\u0087\3\2\2\2\u008a\b\3\2\2\2\u008b\u008c\t\4\2\2\u008c"+
		"\n\3\2\2\2\u008d\u0091\t\5\2\2\u008e\u0090\t\6\2\2\u008f\u008e\3\2\2\2"+
		"\u0090\u0093\3\2\2\2\u0091\u008f\3\2\2\2\u0091\u0092\3\2\2\2\u0092\f\3"+
		"\2\2\2\u0093\u0091\3\2\2\2\u0094\u0096\t\7\2\2\u0095\u0094\3\2\2\2\u0096"+
		"\u0099\3\2\2\2\u0097\u0095\3\2\2\2\u0097\u0098\3\2\2\2\u0098\u009a\3\2"+
		"\2\2\u0099\u0097\3\2\2\2\u009a\u009c\7\60\2\2\u009b\u009d\t\7\2\2\u009c"+
		"\u009b\3\2\2\2\u009d\u009e\3\2\2\2\u009e\u009c\3\2\2\2\u009e\u009f\3\2"+
		"\2\2\u009f\16\3\2\2\2\u00a0\u00a2\t\7\2\2\u00a1\u00a0\3\2\2\2\u00a2\u00a3"+
		"\3\2\2\2\u00a3\u00a1\3\2\2\2\u00a3\u00a4\3\2\2\2\u00a4\20\3\2\2\2\u00a5"+
		"\u00a9\7$\2\2\u00a6\u00a8\13\2\2\2\u00a7\u00a6\3\2\2\2\u00a8\u00ab\3\2"+
		"\2\2\u00a9\u00aa\3\2\2\2\u00a9\u00a7\3\2\2\2\u00aa\u00ac\3\2\2\2\u00ab"+
		"\u00a9\3\2\2\2\u00ac\u00ad\7$\2\2\u00ad\22\3\2\2\2\u00ae\u00af\7/\2\2"+
		"\u00af\u00b0\7/\2\2\u00b0\u00b4\3\2\2\2\u00b1\u00b3\13\2\2\2\u00b2\u00b1"+
		"\3\2\2\2\u00b3\u00b6\3\2\2\2\u00b4\u00b5\3\2\2\2\u00b4\u00b2\3\2\2\2\u00b5"+
		"\24\3\2\2\2\u00b6\u00b4\3\2\2\2\13\2}\u0089\u0091\u0097\u009e\u00a3\u00a9"+
		"\u00b4\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}