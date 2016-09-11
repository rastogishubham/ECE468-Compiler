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
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		T__9=10, T__10=11, T__11=12, T__12=13, T__13=14, T__14=15, T__15=16, T__16=17, 
		T__17=18, T__18=19, T__19=20, T__20=21, T__21=22, T__22=23, T__23=24, 
		T__24=25, T__25=26, T__26=27, T__27=28, T__28=29, T__29=30, T__30=31, 
		T__31=32, T__32=33, COMMENT=34, KEYWORDS=35, OPERATORS=36, EMPTY=37, IDENTIFIERS=38, 
		FLOATLITERAL=39, INTLITERAL=40, STRINGLITERAL=41;
	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] ruleNames = {
		"T__0", "T__1", "T__2", "T__3", "T__4", "T__5", "T__6", "T__7", "T__8", 
		"T__9", "T__10", "T__11", "T__12", "T__13", "T__14", "T__15", "T__16", 
		"T__17", "T__18", "T__19", "T__20", "T__21", "T__22", "T__23", "T__24", 
		"T__25", "T__26", "T__27", "T__28", "T__29", "T__30", "T__31", "T__32", 
		"COMMENT", "KEYWORDS", "OPERATORS", "EMPTY", "IDENTIFIERS", "FLOATLITERAL", 
		"INTLITERAL", "STRINGLITERAL"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "'PROGRAM'", "'BEGIN'", "'END'", "'STRING'", "':='", "';'", "'FLOAT'", 
		"'INT'", "'VOID'", "','", "'FUNCTION'", "'READ'", "'('", "')'", "'WRITE'", 
		"'RETURN'", "'+'", "'-'", "'*'", "'/'", "'IF'", "'ENDIF'", "'ELSIF'", 
		"'TRUE'", "'FALSE'", "'<'", "'>'", "'='", "'!='", "'<='", "'>='", "'DO'", 
		"'WHILE'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, null, null, null, null, null, null, null, null, null, null, null, 
		null, null, null, null, null, null, null, null, null, null, null, null, 
		null, null, null, null, null, null, null, null, null, null, "COMMENT", 
		"KEYWORDS", "OPERATORS", "EMPTY", "IDENTIFIERS", "FLOATLITERAL", "INTLITERAL", 
		"STRINGLITERAL"
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

	@Override
	public void action(RuleContext _localctx, int ruleIndex, int actionIndex) {
		switch (ruleIndex) {
		case 37:
			IDENTIFIERS_action((RuleContext)_localctx, actionIndex);
			break;
		case 40:
			STRINGLITERAL_action((RuleContext)_localctx, actionIndex);
			break;
		}
	}
	private void IDENTIFIERS_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 0:

					if(getText().length() > 31)
						throw new RuntimeException("lxcjkgvksjd");
				
			break;
		}
	}
	private void STRINGLITERAL_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 1:

					if(getText().length() > 81)
						throw new RuntimeException("lxcjkgvksjd");
				
			break;
		}
	}

	public static final String _serializedATN =
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\2+\u0181\b\1\4\2\t"+
		"\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!"+
		"\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\4(\t(\4)\t)\4*\t*\3\2\3\2"+
		"\3\2\3\2\3\2\3\2\3\2\3\2\3\3\3\3\3\3\3\3\3\3\3\3\3\4\3\4\3\4\3\4\3\5\3"+
		"\5\3\5\3\5\3\5\3\5\3\5\3\6\3\6\3\6\3\7\3\7\3\b\3\b\3\b\3\b\3\b\3\b\3\t"+
		"\3\t\3\t\3\t\3\n\3\n\3\n\3\n\3\n\3\13\3\13\3\f\3\f\3\f\3\f\3\f\3\f\3\f"+
		"\3\f\3\f\3\r\3\r\3\r\3\r\3\r\3\16\3\16\3\17\3\17\3\20\3\20\3\20\3\20\3"+
		"\20\3\20\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\22\3\22\3\23\3\23\3\24\3"+
		"\24\3\25\3\25\3\26\3\26\3\26\3\27\3\27\3\27\3\27\3\27\3\27\3\30\3\30\3"+
		"\30\3\30\3\30\3\30\3\31\3\31\3\31\3\31\3\31\3\32\3\32\3\32\3\32\3\32\3"+
		"\32\3\33\3\33\3\34\3\34\3\35\3\35\3\36\3\36\3\36\3\37\3\37\3\37\3 \3 "+
		"\3 \3!\3!\3!\3\"\3\"\3\"\3\"\3\"\3\"\3#\3#\3#\3#\7#\u00e2\n#\f#\16#\u00e5"+
		"\13#\3#\3#\3#\3#\3$\3$\3$\3$\3$\3$\3$\3$\3$\3$\3$\3$\3$\3$\3$\3$\3$\3"+
		"$\3$\3$\3$\3$\3$\3$\3$\3$\3$\3$\3$\3$\3$\3$\3$\3$\3$\3$\3$\3$\3$\3$\3"+
		"$\3$\3$\3$\3$\3$\3$\3$\3$\3$\3$\3$\3$\3$\3$\3$\3$\3$\3$\3$\3$\3$\3$\3"+
		"$\3$\3$\3$\3$\3$\3$\3$\3$\3$\3$\3$\3$\3$\3$\3$\3$\3$\3$\3$\3$\3$\3$\3"+
		"$\3$\3$\3$\3$\3$\3$\3$\3$\3$\3$\5$\u014c\n$\3%\3%\3%\3%\3%\3%\3%\3%\3"+
		"%\3%\5%\u0158\n%\3&\3&\3&\3&\3\'\3\'\7\'\u0160\n\'\f\'\16\'\u0163\13\'"+
		"\3\'\3\'\3(\7(\u0168\n(\f(\16(\u016b\13(\3(\3(\6(\u016f\n(\r(\16(\u0170"+
		"\3)\6)\u0174\n)\r)\16)\u0175\3*\3*\7*\u017a\n*\f*\16*\u017d\13*\3*\3*"+
		"\3*\4\u00e3\u017b\2+\3\3\5\4\7\5\t\6\13\7\r\b\17\t\21\n\23\13\25\f\27"+
		"\r\31\16\33\17\35\20\37\21!\22#\23%\24\'\25)\26+\27-\30/\31\61\32\63\33"+
		"\65\34\67\359\36;\37= ?!A\"C#E$G%I&K\'M(O)Q*S+\3\2\b\6\2,-//\61\61??\6"+
		"\2*+..=>@@\5\2\13\f\16\17\"\"\4\2C\\c|\5\2\62;C\\c|\3\2\62;\u019e\2\3"+
		"\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2"+
		"\2\17\3\2\2\2\2\21\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2\2\31"+
		"\3\2\2\2\2\33\3\2\2\2\2\35\3\2\2\2\2\37\3\2\2\2\2!\3\2\2\2\2#\3\2\2\2"+
		"\2%\3\2\2\2\2\'\3\2\2\2\2)\3\2\2\2\2+\3\2\2\2\2-\3\2\2\2\2/\3\2\2\2\2"+
		"\61\3\2\2\2\2\63\3\2\2\2\2\65\3\2\2\2\2\67\3\2\2\2\29\3\2\2\2\2;\3\2\2"+
		"\2\2=\3\2\2\2\2?\3\2\2\2\2A\3\2\2\2\2C\3\2\2\2\2E\3\2\2\2\2G\3\2\2\2\2"+
		"I\3\2\2\2\2K\3\2\2\2\2M\3\2\2\2\2O\3\2\2\2\2Q\3\2\2\2\2S\3\2\2\2\3U\3"+
		"\2\2\2\5]\3\2\2\2\7c\3\2\2\2\tg\3\2\2\2\13n\3\2\2\2\rq\3\2\2\2\17s\3\2"+
		"\2\2\21y\3\2\2\2\23}\3\2\2\2\25\u0082\3\2\2\2\27\u0084\3\2\2\2\31\u008d"+
		"\3\2\2\2\33\u0092\3\2\2\2\35\u0094\3\2\2\2\37\u0096\3\2\2\2!\u009c\3\2"+
		"\2\2#\u00a3\3\2\2\2%\u00a5\3\2\2\2\'\u00a7\3\2\2\2)\u00a9\3\2\2\2+\u00ab"+
		"\3\2\2\2-\u00ae\3\2\2\2/\u00b4\3\2\2\2\61\u00ba\3\2\2\2\63\u00bf\3\2\2"+
		"\2\65\u00c5\3\2\2\2\67\u00c7\3\2\2\29\u00c9\3\2\2\2;\u00cb\3\2\2\2=\u00ce"+
		"\3\2\2\2?\u00d1\3\2\2\2A\u00d4\3\2\2\2C\u00d7\3\2\2\2E\u00dd\3\2\2\2G"+
		"\u014b\3\2\2\2I\u0157\3\2\2\2K\u0159\3\2\2\2M\u015d\3\2\2\2O\u0169\3\2"+
		"\2\2Q\u0173\3\2\2\2S\u0177\3\2\2\2UV\7R\2\2VW\7T\2\2WX\7Q\2\2XY\7I\2\2"+
		"YZ\7T\2\2Z[\7C\2\2[\\\7O\2\2\\\4\3\2\2\2]^\7D\2\2^_\7G\2\2_`\7I\2\2`a"+
		"\7K\2\2ab\7P\2\2b\6\3\2\2\2cd\7G\2\2de\7P\2\2ef\7F\2\2f\b\3\2\2\2gh\7"+
		"U\2\2hi\7V\2\2ij\7T\2\2jk\7K\2\2kl\7P\2\2lm\7I\2\2m\n\3\2\2\2no\7<\2\2"+
		"op\7?\2\2p\f\3\2\2\2qr\7=\2\2r\16\3\2\2\2st\7H\2\2tu\7N\2\2uv\7Q\2\2v"+
		"w\7C\2\2wx\7V\2\2x\20\3\2\2\2yz\7K\2\2z{\7P\2\2{|\7V\2\2|\22\3\2\2\2}"+
		"~\7X\2\2~\177\7Q\2\2\177\u0080\7K\2\2\u0080\u0081\7F\2\2\u0081\24\3\2"+
		"\2\2\u0082\u0083\7.\2\2\u0083\26\3\2\2\2\u0084\u0085\7H\2\2\u0085\u0086"+
		"\7W\2\2\u0086\u0087\7P\2\2\u0087\u0088\7E\2\2\u0088\u0089\7V\2\2\u0089"+
		"\u008a\7K\2\2\u008a\u008b\7Q\2\2\u008b\u008c\7P\2\2\u008c\30\3\2\2\2\u008d"+
		"\u008e\7T\2\2\u008e\u008f\7G\2\2\u008f\u0090\7C\2\2\u0090\u0091\7F\2\2"+
		"\u0091\32\3\2\2\2\u0092\u0093\7*\2\2\u0093\34\3\2\2\2\u0094\u0095\7+\2"+
		"\2\u0095\36\3\2\2\2\u0096\u0097\7Y\2\2\u0097\u0098\7T\2\2\u0098\u0099"+
		"\7K\2\2\u0099\u009a\7V\2\2\u009a\u009b\7G\2\2\u009b \3\2\2\2\u009c\u009d"+
		"\7T\2\2\u009d\u009e\7G\2\2\u009e\u009f\7V\2\2\u009f\u00a0\7W\2\2\u00a0"+
		"\u00a1\7T\2\2\u00a1\u00a2\7P\2\2\u00a2\"\3\2\2\2\u00a3\u00a4\7-\2\2\u00a4"+
		"$\3\2\2\2\u00a5\u00a6\7/\2\2\u00a6&\3\2\2\2\u00a7\u00a8\7,\2\2\u00a8("+
		"\3\2\2\2\u00a9\u00aa\7\61\2\2\u00aa*\3\2\2\2\u00ab\u00ac\7K\2\2\u00ac"+
		"\u00ad\7H\2\2\u00ad,\3\2\2\2\u00ae\u00af\7G\2\2\u00af\u00b0\7P\2\2\u00b0"+
		"\u00b1\7F\2\2\u00b1\u00b2\7K\2\2\u00b2\u00b3\7H\2\2\u00b3.\3\2\2\2\u00b4"+
		"\u00b5\7G\2\2\u00b5\u00b6\7N\2\2\u00b6\u00b7\7U\2\2\u00b7\u00b8\7K\2\2"+
		"\u00b8\u00b9\7H\2\2\u00b9\60\3\2\2\2\u00ba\u00bb\7V\2\2\u00bb\u00bc\7"+
		"T\2\2\u00bc\u00bd\7W\2\2\u00bd\u00be\7G\2\2\u00be\62\3\2\2\2\u00bf\u00c0"+
		"\7H\2\2\u00c0\u00c1\7C\2\2\u00c1\u00c2\7N\2\2\u00c2\u00c3\7U\2\2\u00c3"+
		"\u00c4\7G\2\2\u00c4\64\3\2\2\2\u00c5\u00c6\7>\2\2\u00c6\66\3\2\2\2\u00c7"+
		"\u00c8\7@\2\2\u00c88\3\2\2\2\u00c9\u00ca\7?\2\2\u00ca:\3\2\2\2\u00cb\u00cc"+
		"\7#\2\2\u00cc\u00cd\7?\2\2\u00cd<\3\2\2\2\u00ce\u00cf\7>\2\2\u00cf\u00d0"+
		"\7?\2\2\u00d0>\3\2\2\2\u00d1\u00d2\7@\2\2\u00d2\u00d3\7?\2\2\u00d3@\3"+
		"\2\2\2\u00d4\u00d5\7F\2\2\u00d5\u00d6\7Q\2\2\u00d6B\3\2\2\2\u00d7\u00d8"+
		"\7Y\2\2\u00d8\u00d9\7J\2\2\u00d9\u00da\7K\2\2\u00da\u00db\7N\2\2\u00db"+
		"\u00dc\7G\2\2\u00dcD\3\2\2\2\u00dd\u00de\7/\2\2\u00de\u00df\7/\2\2\u00df"+
		"\u00e3\3\2\2\2\u00e0\u00e2\13\2\2\2\u00e1\u00e0\3\2\2\2\u00e2\u00e5\3"+
		"\2\2\2\u00e3\u00e4\3\2\2\2\u00e3\u00e1\3\2\2\2\u00e4\u00e6\3\2\2\2\u00e5"+
		"\u00e3\3\2\2\2\u00e6\u00e7\7\f\2\2\u00e7\u00e8\3\2\2\2\u00e8\u00e9\b#"+
		"\2\2\u00e9F\3\2\2\2\u00ea\u00eb\7R\2\2\u00eb\u00ec\7T\2\2\u00ec\u00ed"+
		"\7Q\2\2\u00ed\u00ee\7I\2\2\u00ee\u00ef\7T\2\2\u00ef\u00f0\7C\2\2\u00f0"+
		"\u014c\7O\2\2\u00f1\u00f2\7D\2\2\u00f2\u00f3\7G\2\2\u00f3\u00f4\7I\2\2"+
		"\u00f4\u00f5\7K\2\2\u00f5\u014c\7P\2\2\u00f6\u00f7\7G\2\2\u00f7\u00f8"+
		"\7P\2\2\u00f8\u014c\7F\2\2\u00f9\u00fa\7H\2\2\u00fa\u00fb\7W\2\2\u00fb"+
		"\u00fc\7P\2\2\u00fc\u00fd\7E\2\2\u00fd\u00fe\7V\2\2\u00fe\u00ff\7K\2\2"+
		"\u00ff\u0100\7Q\2\2\u0100\u014c\7P\2\2\u0101\u0102\7T\2\2\u0102\u0103"+
		"\7G\2\2\u0103\u0104\7C\2\2\u0104\u014c\7F\2\2\u0105\u0106\7Y\2\2\u0106"+
		"\u0107\7T\2\2\u0107\u0108\7K\2\2\u0108\u0109\7V\2\2\u0109\u014c\7G\2\2"+
		"\u010a\u010b\7K\2\2\u010b\u014c\7H\2\2\u010c\u010d\7G\2\2\u010d\u010e"+
		"\7N\2\2\u010e\u010f\7U\2\2\u010f\u0110\7K\2\2\u0110\u014c\7H\2\2\u0111"+
		"\u0112\7G\2\2\u0112\u0113\7P\2\2\u0113\u0114\7F\2\2\u0114\u0115\7K\2\2"+
		"\u0115\u014c\7H\2\2\u0116\u0117\7F\2\2\u0117\u014c\7Q\2\2\u0118\u0119"+
		"\7Y\2\2\u0119\u011a\7J\2\2\u011a\u011b\7K\2\2\u011b\u011c\7N\2\2\u011c"+
		"\u014c\7G\2\2\u011d\u011e\7E\2\2\u011e\u011f\7Q\2\2\u011f\u0120\7P\2\2"+
		"\u0120\u0121\7V\2\2\u0121\u0122\7K\2\2\u0122\u0123\7P\2\2\u0123\u0124"+
		"\7W\2\2\u0124\u014c\7G\2\2\u0125\u0126\7D\2\2\u0126\u0127\7T\2\2\u0127"+
		"\u0128\7G\2\2\u0128\u0129\7C\2\2\u0129\u014c\7M\2\2\u012a\u012b\7T\2\2"+
		"\u012b\u012c\7G\2\2\u012c\u012d\7V\2\2\u012d\u012e\7W\2\2\u012e\u012f"+
		"\7T\2\2\u012f\u014c\7P\2\2\u0130\u0131\7K\2\2\u0131\u0132\7P\2\2\u0132"+
		"\u014c\7V\2\2\u0133\u0134\7X\2\2\u0134\u0135\7Q\2\2\u0135\u0136\7K\2\2"+
		"\u0136\u014c\7F\2\2\u0137\u0138\7U\2\2\u0138\u0139\7V\2\2\u0139\u013a"+
		"\7T\2\2\u013a\u013b\7K\2\2\u013b\u013c\7P\2\2\u013c\u014c\7I\2\2\u013d"+
		"\u013e\7H\2\2\u013e\u013f\7N\2\2\u013f\u0140\7Q\2\2\u0140\u0141\7C\2\2"+
		"\u0141\u014c\7V\2\2\u0142\u0143\7V\2\2\u0143\u0144\7T\2\2\u0144\u0145"+
		"\7W\2\2\u0145\u014c\7G\2\2\u0146\u0147\7H\2\2\u0147\u0148\7C\2\2\u0148"+
		"\u0149\7N\2\2\u0149\u014a\7U\2\2\u014a\u014c\7G\2\2\u014b\u00ea\3\2\2"+
		"\2\u014b\u00f1\3\2\2\2\u014b\u00f6\3\2\2\2\u014b\u00f9\3\2\2\2\u014b\u0101"+
		"\3\2\2\2\u014b\u0105\3\2\2\2\u014b\u010a\3\2\2\2\u014b\u010c\3\2\2\2\u014b"+
		"\u0111\3\2\2\2\u014b\u0116\3\2\2\2\u014b\u0118\3\2\2\2\u014b\u011d\3\2"+
		"\2\2\u014b\u0125\3\2\2\2\u014b\u012a\3\2\2\2\u014b\u0130\3\2\2\2\u014b"+
		"\u0133\3\2\2\2\u014b\u0137\3\2\2\2\u014b\u013d\3\2\2\2\u014b\u0142\3\2"+
		"\2\2\u014b\u0146\3\2\2\2\u014cH\3\2\2\2\u014d\u014e\7<\2\2\u014e\u0158"+
		"\7?\2\2\u014f\u0158\t\2\2\2\u0150\u0151\7#\2\2\u0151\u0158\7?\2\2\u0152"+
		"\u0158\t\3\2\2\u0153\u0154\7>\2\2\u0154\u0158\7?\2\2\u0155\u0156\7@\2"+
		"\2\u0156\u0158\7?\2\2\u0157\u014d\3\2\2\2\u0157\u014f\3\2\2\2\u0157\u0150"+
		"\3\2\2\2\u0157\u0152\3\2\2\2\u0157\u0153\3\2\2\2\u0157\u0155\3\2\2\2\u0158"+
		"J\3\2\2\2\u0159\u015a\t\4\2\2\u015a\u015b\3\2\2\2\u015b\u015c\b&\2\2\u015c"+
		"L\3\2\2\2\u015d\u0161\t\5\2\2\u015e\u0160\t\6\2\2\u015f\u015e\3\2\2\2"+
		"\u0160\u0163\3\2\2\2\u0161\u015f\3\2\2\2\u0161\u0162\3\2\2\2\u0162\u0164"+
		"\3\2\2\2\u0163\u0161\3\2\2\2\u0164\u0165\b\'\3\2\u0165N\3\2\2\2\u0166"+
		"\u0168\t\7\2\2\u0167\u0166\3\2\2\2\u0168\u016b\3\2\2\2\u0169\u0167\3\2"+
		"\2\2\u0169\u016a\3\2\2\2\u016a\u016c\3\2\2\2\u016b\u0169\3\2\2\2\u016c"+
		"\u016e\7\60\2\2\u016d\u016f\t\7\2\2\u016e\u016d\3\2\2\2\u016f\u0170\3"+
		"\2\2\2\u0170\u016e\3\2\2\2\u0170\u0171\3\2\2\2\u0171P\3\2\2\2\u0172\u0174"+
		"\t\7\2\2\u0173\u0172\3\2\2\2\u0174\u0175\3\2\2\2\u0175\u0173\3\2\2\2\u0175"+
		"\u0176\3\2\2\2\u0176R\3\2\2\2\u0177\u017b\7$\2\2\u0178\u017a\13\2\2\2"+
		"\u0179\u0178\3\2\2\2\u017a\u017d\3\2\2\2\u017b\u017c\3\2\2\2\u017b\u0179"+
		"\3\2\2\2\u017c\u017e\3\2\2\2\u017d\u017b\3\2\2\2\u017e\u017f\7$\2\2\u017f"+
		"\u0180\b*\4\2\u0180T\3\2\2\2\13\2\u00e3\u014b\u0157\u0161\u0169\u0170"+
		"\u0175\u017b\5\b\2\2\3\'\2\3*\3";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}