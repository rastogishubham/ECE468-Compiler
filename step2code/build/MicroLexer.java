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
		null, "'PROGRAM'", "'BEGIN'", "'END'", "'IDENTIFIER'", "'STRING'", "':='", 
		"'FLOAT'", "'INT'", "'VOID'", "','", "'FUNCTION'", "'READ'", "'WRITE'", 
		"'RETURN'", "'INTLITERAL'", "'FLOATLITERAL'", "'+'", "'-'", "'*'", "'/'", 
		"'IF'", "'ENDIF'", "'ELSIF'", "'TRUE'", "'FALSE'", "'<'", "'>'", "'='", 
		"'!='", "'<='", "'>='", "'DO'", "'WHILE'"
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
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\2+\u019c\b\1\4\2\t"+
		"\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!"+
		"\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\4(\t(\4)\t)\4*\t*\3\2\3\2"+
		"\3\2\3\2\3\2\3\2\3\2\3\2\3\3\3\3\3\3\3\3\3\3\3\3\3\4\3\4\3\4\3\4\3\5\3"+
		"\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\7"+
		"\3\7\3\7\3\b\3\b\3\b\3\b\3\b\3\b\3\t\3\t\3\t\3\t\3\n\3\n\3\n\3\n\3\n\3"+
		"\13\3\13\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\r\3\r\3\r\3\r\3\r\3\16"+
		"\3\16\3\16\3\16\3\16\3\16\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\20\3\20"+
		"\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\21\3\21\3\21\3\21\3\21"+
		"\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\22\3\22\3\23\3\23\3\24\3\24"+
		"\3\25\3\25\3\26\3\26\3\26\3\27\3\27\3\27\3\27\3\27\3\27\3\30\3\30\3\30"+
		"\3\30\3\30\3\30\3\31\3\31\3\31\3\31\3\31\3\32\3\32\3\32\3\32\3\32\3\32"+
		"\3\33\3\33\3\34\3\34\3\35\3\35\3\36\3\36\3\36\3\37\3\37\3\37\3 \3 \3 "+
		"\3!\3!\3!\3\"\3\"\3\"\3\"\3\"\3\"\3#\3#\3#\3#\7#\u00ff\n#\f#\16#\u0102"+
		"\13#\3#\3#\3#\3#\3$\3$\3$\3$\3$\3$\3$\3$\3$\3$\3$\3$\3$\3$\3$\3$\3$\3"+
		"$\3$\3$\3$\3$\3$\3$\3$\3$\3$\3$\3$\3$\3$\3$\3$\3$\3$\3$\3$\3$\3$\3$\3"+
		"$\3$\3$\3$\3$\3$\3$\3$\3$\3$\3$\3$\3$\3$\3$\3$\3$\3$\3$\3$\3$\3$\3$\3"+
		"$\3$\3$\3$\3$\3$\3$\3$\3$\3$\3$\3$\3$\3$\3$\3$\3$\3$\3$\3$\3$\3$\3$\3"+
		"$\3$\3$\3$\3$\3$\3$\3$\3$\3$\3$\5$\u0169\n$\3%\3%\3%\3%\3%\3%\3%\3%\3"+
		"%\3%\5%\u0175\n%\3&\3&\3\'\3\'\7\'\u017b\n\'\f\'\16\'\u017e\13\'\3\'\3"+
		"\'\3(\7(\u0183\n(\f(\16(\u0186\13(\3(\3(\6(\u018a\n(\r(\16(\u018b\3)\6"+
		")\u018f\n)\r)\16)\u0190\3*\3*\7*\u0195\n*\f*\16*\u0198\13*\3*\3*\3*\4"+
		"\u0100\u0196\2+\3\3\5\4\7\5\t\6\13\7\r\b\17\t\21\n\23\13\25\f\27\r\31"+
		"\16\33\17\35\20\37\21!\22#\23%\24\'\25)\26+\27-\30/\31\61\32\63\33\65"+
		"\34\67\359\36;\37= ?!A\"C#E$G%I&K\'M(O)Q*S+\3\2\b\6\2,-//\61\61??\6\2"+
		"*+..=>@@\5\2\13\f\16\17\"\"\4\2C\\c|\5\2\62;C\\c|\3\2\62;\u01b9\2\3\3"+
		"\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2"+
		"\17\3\2\2\2\2\21\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2\2\31\3"+
		"\2\2\2\2\33\3\2\2\2\2\35\3\2\2\2\2\37\3\2\2\2\2!\3\2\2\2\2#\3\2\2\2\2"+
		"%\3\2\2\2\2\'\3\2\2\2\2)\3\2\2\2\2+\3\2\2\2\2-\3\2\2\2\2/\3\2\2\2\2\61"+
		"\3\2\2\2\2\63\3\2\2\2\2\65\3\2\2\2\2\67\3\2\2\2\29\3\2\2\2\2;\3\2\2\2"+
		"\2=\3\2\2\2\2?\3\2\2\2\2A\3\2\2\2\2C\3\2\2\2\2E\3\2\2\2\2G\3\2\2\2\2I"+
		"\3\2\2\2\2K\3\2\2\2\2M\3\2\2\2\2O\3\2\2\2\2Q\3\2\2\2\2S\3\2\2\2\3U\3\2"+
		"\2\2\5]\3\2\2\2\7c\3\2\2\2\tg\3\2\2\2\13r\3\2\2\2\ry\3\2\2\2\17|\3\2\2"+
		"\2\21\u0082\3\2\2\2\23\u0086\3\2\2\2\25\u008b\3\2\2\2\27\u008d\3\2\2\2"+
		"\31\u0096\3\2\2\2\33\u009b\3\2\2\2\35\u00a1\3\2\2\2\37\u00a8\3\2\2\2!"+
		"\u00b3\3\2\2\2#\u00c0\3\2\2\2%\u00c2\3\2\2\2\'\u00c4\3\2\2\2)\u00c6\3"+
		"\2\2\2+\u00c8\3\2\2\2-\u00cb\3\2\2\2/\u00d1\3\2\2\2\61\u00d7\3\2\2\2\63"+
		"\u00dc\3\2\2\2\65\u00e2\3\2\2\2\67\u00e4\3\2\2\29\u00e6\3\2\2\2;\u00e8"+
		"\3\2\2\2=\u00eb\3\2\2\2?\u00ee\3\2\2\2A\u00f1\3\2\2\2C\u00f4\3\2\2\2E"+
		"\u00fa\3\2\2\2G\u0168\3\2\2\2I\u0174\3\2\2\2K\u0176\3\2\2\2M\u0178\3\2"+
		"\2\2O\u0184\3\2\2\2Q\u018e\3\2\2\2S\u0192\3\2\2\2UV\7R\2\2VW\7T\2\2WX"+
		"\7Q\2\2XY\7I\2\2YZ\7T\2\2Z[\7C\2\2[\\\7O\2\2\\\4\3\2\2\2]^\7D\2\2^_\7"+
		"G\2\2_`\7I\2\2`a\7K\2\2ab\7P\2\2b\6\3\2\2\2cd\7G\2\2de\7P\2\2ef\7F\2\2"+
		"f\b\3\2\2\2gh\7K\2\2hi\7F\2\2ij\7G\2\2jk\7P\2\2kl\7V\2\2lm\7K\2\2mn\7"+
		"H\2\2no\7K\2\2op\7G\2\2pq\7T\2\2q\n\3\2\2\2rs\7U\2\2st\7V\2\2tu\7T\2\2"+
		"uv\7K\2\2vw\7P\2\2wx\7I\2\2x\f\3\2\2\2yz\7<\2\2z{\7?\2\2{\16\3\2\2\2|"+
		"}\7H\2\2}~\7N\2\2~\177\7Q\2\2\177\u0080\7C\2\2\u0080\u0081\7V\2\2\u0081"+
		"\20\3\2\2\2\u0082\u0083\7K\2\2\u0083\u0084\7P\2\2\u0084\u0085\7V\2\2\u0085"+
		"\22\3\2\2\2\u0086\u0087\7X\2\2\u0087\u0088\7Q\2\2\u0088\u0089\7K\2\2\u0089"+
		"\u008a\7F\2\2\u008a\24\3\2\2\2\u008b\u008c\7.\2\2\u008c\26\3\2\2\2\u008d"+
		"\u008e\7H\2\2\u008e\u008f\7W\2\2\u008f\u0090\7P\2\2\u0090\u0091\7E\2\2"+
		"\u0091\u0092\7V\2\2\u0092\u0093\7K\2\2\u0093\u0094\7Q\2\2\u0094\u0095"+
		"\7P\2\2\u0095\30\3\2\2\2\u0096\u0097\7T\2\2\u0097\u0098\7G\2\2\u0098\u0099"+
		"\7C\2\2\u0099\u009a\7F\2\2\u009a\32\3\2\2\2\u009b\u009c\7Y\2\2\u009c\u009d"+
		"\7T\2\2\u009d\u009e\7K\2\2\u009e\u009f\7V\2\2\u009f\u00a0\7G\2\2\u00a0"+
		"\34\3\2\2\2\u00a1\u00a2\7T\2\2\u00a2\u00a3\7G\2\2\u00a3\u00a4\7V\2\2\u00a4"+
		"\u00a5\7W\2\2\u00a5\u00a6\7T\2\2\u00a6\u00a7\7P\2\2\u00a7\36\3\2\2\2\u00a8"+
		"\u00a9\7K\2\2\u00a9\u00aa\7P\2\2\u00aa\u00ab\7V\2\2\u00ab\u00ac\7N\2\2"+
		"\u00ac\u00ad\7K\2\2\u00ad\u00ae\7V\2\2\u00ae\u00af\7G\2\2\u00af\u00b0"+
		"\7T\2\2\u00b0\u00b1\7C\2\2\u00b1\u00b2\7N\2\2\u00b2 \3\2\2\2\u00b3\u00b4"+
		"\7H\2\2\u00b4\u00b5\7N\2\2\u00b5\u00b6\7Q\2\2\u00b6\u00b7\7C\2\2\u00b7"+
		"\u00b8\7V\2\2\u00b8\u00b9\7N\2\2\u00b9\u00ba\7K\2\2\u00ba\u00bb\7V\2\2"+
		"\u00bb\u00bc\7G\2\2\u00bc\u00bd\7T\2\2\u00bd\u00be\7C\2\2\u00be\u00bf"+
		"\7N\2\2\u00bf\"\3\2\2\2\u00c0\u00c1\7-\2\2\u00c1$\3\2\2\2\u00c2\u00c3"+
		"\7/\2\2\u00c3&\3\2\2\2\u00c4\u00c5\7,\2\2\u00c5(\3\2\2\2\u00c6\u00c7\7"+
		"\61\2\2\u00c7*\3\2\2\2\u00c8\u00c9\7K\2\2\u00c9\u00ca\7H\2\2\u00ca,\3"+
		"\2\2\2\u00cb\u00cc\7G\2\2\u00cc\u00cd\7P\2\2\u00cd\u00ce\7F\2\2\u00ce"+
		"\u00cf\7K\2\2\u00cf\u00d0\7H\2\2\u00d0.\3\2\2\2\u00d1\u00d2\7G\2\2\u00d2"+
		"\u00d3\7N\2\2\u00d3\u00d4\7U\2\2\u00d4\u00d5\7K\2\2\u00d5\u00d6\7H\2\2"+
		"\u00d6\60\3\2\2\2\u00d7\u00d8\7V\2\2\u00d8\u00d9\7T\2\2\u00d9\u00da\7"+
		"W\2\2\u00da\u00db\7G\2\2\u00db\62\3\2\2\2\u00dc\u00dd\7H\2\2\u00dd\u00de"+
		"\7C\2\2\u00de\u00df\7N\2\2\u00df\u00e0\7U\2\2\u00e0\u00e1\7G\2\2\u00e1"+
		"\64\3\2\2\2\u00e2\u00e3\7>\2\2\u00e3\66\3\2\2\2\u00e4\u00e5\7@\2\2\u00e5"+
		"8\3\2\2\2\u00e6\u00e7\7?\2\2\u00e7:\3\2\2\2\u00e8\u00e9\7#\2\2\u00e9\u00ea"+
		"\7?\2\2\u00ea<\3\2\2\2\u00eb\u00ec\7>\2\2\u00ec\u00ed\7?\2\2\u00ed>\3"+
		"\2\2\2\u00ee\u00ef\7@\2\2\u00ef\u00f0\7?\2\2\u00f0@\3\2\2\2\u00f1\u00f2"+
		"\7F\2\2\u00f2\u00f3\7Q\2\2\u00f3B\3\2\2\2\u00f4\u00f5\7Y\2\2\u00f5\u00f6"+
		"\7J\2\2\u00f6\u00f7\7K\2\2\u00f7\u00f8\7N\2\2\u00f8\u00f9\7G\2\2\u00f9"+
		"D\3\2\2\2\u00fa\u00fb\7/\2\2\u00fb\u00fc\7/\2\2\u00fc\u0100\3\2\2\2\u00fd"+
		"\u00ff\13\2\2\2\u00fe\u00fd\3\2\2\2\u00ff\u0102\3\2\2\2\u0100\u0101\3"+
		"\2\2\2\u0100\u00fe\3\2\2\2\u0101\u0103\3\2\2\2\u0102\u0100\3\2\2\2\u0103"+
		"\u0104\7\f\2\2\u0104\u0105\3\2\2\2\u0105\u0106\b#\2\2\u0106F\3\2\2\2\u0107"+
		"\u0108\7R\2\2\u0108\u0109\7T\2\2\u0109\u010a\7Q\2\2\u010a\u010b\7I\2\2"+
		"\u010b\u010c\7T\2\2\u010c\u010d\7C\2\2\u010d\u0169\7O\2\2\u010e\u010f"+
		"\7D\2\2\u010f\u0110\7G\2\2\u0110\u0111\7I\2\2\u0111\u0112\7K\2\2\u0112"+
		"\u0169\7P\2\2\u0113\u0114\7G\2\2\u0114\u0115\7P\2\2\u0115\u0169\7F\2\2"+
		"\u0116\u0117\7H\2\2\u0117\u0118\7W\2\2\u0118\u0119\7P\2\2\u0119\u011a"+
		"\7E\2\2\u011a\u011b\7V\2\2\u011b\u011c\7K\2\2\u011c\u011d\7Q\2\2\u011d"+
		"\u0169\7P\2\2\u011e\u011f\7T\2\2\u011f\u0120\7G\2\2\u0120\u0121\7C\2\2"+
		"\u0121\u0169\7F\2\2\u0122\u0123\7Y\2\2\u0123\u0124\7T\2\2\u0124\u0125"+
		"\7K\2\2\u0125\u0126\7V\2\2\u0126\u0169\7G\2\2\u0127\u0128\7K\2\2\u0128"+
		"\u0169\7H\2\2\u0129\u012a\7G\2\2\u012a\u012b\7N\2\2\u012b\u012c\7U\2\2"+
		"\u012c\u012d\7K\2\2\u012d\u0169\7H\2\2\u012e\u012f\7G\2\2\u012f\u0130"+
		"\7P\2\2\u0130\u0131\7F\2\2\u0131\u0132\7K\2\2\u0132\u0169\7H\2\2\u0133"+
		"\u0134\7F\2\2\u0134\u0169\7Q\2\2\u0135\u0136\7Y\2\2\u0136\u0137\7J\2\2"+
		"\u0137\u0138\7K\2\2\u0138\u0139\7N\2\2\u0139\u0169\7G\2\2\u013a\u013b"+
		"\7E\2\2\u013b\u013c\7Q\2\2\u013c\u013d\7P\2\2\u013d\u013e\7V\2\2\u013e"+
		"\u013f\7K\2\2\u013f\u0140\7P\2\2\u0140\u0141\7W\2\2\u0141\u0169\7G\2\2"+
		"\u0142\u0143\7D\2\2\u0143\u0144\7T\2\2\u0144\u0145\7G\2\2\u0145\u0146"+
		"\7C\2\2\u0146\u0169\7M\2\2\u0147\u0148\7T\2\2\u0148\u0149\7G\2\2\u0149"+
		"\u014a\7V\2\2\u014a\u014b\7W\2\2\u014b\u014c\7T\2\2\u014c\u0169\7P\2\2"+
		"\u014d\u014e\7K\2\2\u014e\u014f\7P\2\2\u014f\u0169\7V\2\2\u0150\u0151"+
		"\7X\2\2\u0151\u0152\7Q\2\2\u0152\u0153\7K\2\2\u0153\u0169\7F\2\2\u0154"+
		"\u0155\7U\2\2\u0155\u0156\7V\2\2\u0156\u0157\7T\2\2\u0157\u0158\7K\2\2"+
		"\u0158\u0159\7P\2\2\u0159\u0169\7I\2\2\u015a\u015b\7H\2\2\u015b\u015c"+
		"\7N\2\2\u015c\u015d\7Q\2\2\u015d\u015e\7C\2\2\u015e\u0169\7V\2\2\u015f"+
		"\u0160\7V\2\2\u0160\u0161\7T\2\2\u0161\u0162\7W\2\2\u0162\u0169\7G\2\2"+
		"\u0163\u0164\7H\2\2\u0164\u0165\7C\2\2\u0165\u0166\7N\2\2\u0166\u0167"+
		"\7U\2\2\u0167\u0169\7G\2\2\u0168\u0107\3\2\2\2\u0168\u010e\3\2\2\2\u0168"+
		"\u0113\3\2\2\2\u0168\u0116\3\2\2\2\u0168\u011e\3\2\2\2\u0168\u0122\3\2"+
		"\2\2\u0168\u0127\3\2\2\2\u0168\u0129\3\2\2\2\u0168\u012e\3\2\2\2\u0168"+
		"\u0133\3\2\2\2\u0168\u0135\3\2\2\2\u0168\u013a\3\2\2\2\u0168\u0142\3\2"+
		"\2\2\u0168\u0147\3\2\2\2\u0168\u014d\3\2\2\2\u0168\u0150\3\2\2\2\u0168"+
		"\u0154\3\2\2\2\u0168\u015a\3\2\2\2\u0168\u015f\3\2\2\2\u0168\u0163\3\2"+
		"\2\2\u0169H\3\2\2\2\u016a\u016b\7<\2\2\u016b\u0175\7?\2\2\u016c\u0175"+
		"\t\2\2\2\u016d\u016e\7#\2\2\u016e\u0175\7?\2\2\u016f\u0175\t\3\2\2\u0170"+
		"\u0171\7>\2\2\u0171\u0175\7?\2\2\u0172\u0173\7@\2\2\u0173\u0175\7?\2\2"+
		"\u0174\u016a\3\2\2\2\u0174\u016c\3\2\2\2\u0174\u016d\3\2\2\2\u0174\u016f"+
		"\3\2\2\2\u0174\u0170\3\2\2\2\u0174\u0172\3\2\2\2\u0175J\3\2\2\2\u0176"+
		"\u0177\t\4\2\2\u0177L\3\2\2\2\u0178\u017c\t\5\2\2\u0179\u017b\t\6\2\2"+
		"\u017a\u0179\3\2\2\2\u017b\u017e\3\2\2\2\u017c\u017a\3\2\2\2\u017c\u017d"+
		"\3\2\2\2\u017d\u017f\3\2\2\2\u017e\u017c\3\2\2\2\u017f\u0180\b\'\3\2\u0180"+
		"N\3\2\2\2\u0181\u0183\t\7\2\2\u0182\u0181\3\2\2\2\u0183\u0186\3\2\2\2"+
		"\u0184\u0182\3\2\2\2\u0184\u0185\3\2\2\2\u0185\u0187\3\2\2\2\u0186\u0184"+
		"\3\2\2\2\u0187\u0189\7\60\2\2\u0188\u018a\t\7\2\2\u0189\u0188\3\2\2\2"+
		"\u018a\u018b\3\2\2\2\u018b\u0189\3\2\2\2\u018b\u018c\3\2\2\2\u018cP\3"+
		"\2\2\2\u018d\u018f\t\7\2\2\u018e\u018d\3\2\2\2\u018f\u0190\3\2\2\2\u0190"+
		"\u018e\3\2\2\2\u0190\u0191\3\2\2\2\u0191R\3\2\2\2\u0192\u0196\7$\2\2\u0193"+
		"\u0195\13\2\2\2\u0194\u0193\3\2\2\2\u0195\u0198\3\2\2\2\u0196\u0197\3"+
		"\2\2\2\u0196\u0194\3\2\2\2\u0197\u0199\3\2\2\2\u0198\u0196\3\2\2\2\u0199"+
		"\u019a\7$\2\2\u019a\u019b\b*\4\2\u019bT\3\2\2\2\13\2\u0100\u0168\u0174"+
		"\u017c\u0184\u018b\u0190\u0196\5\b\2\2\3\'\2\3*\3";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}