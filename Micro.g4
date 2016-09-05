grammar Micro;

dummy : 'WATER';


COMMENT:
	'--'.*? '\n'-> skip
	;

KEYWORDS: 
	'PROGRAM'
	| 'BEGIN'
	| 'END'
	| 'FUNCTION'
	| 'READ'
	| 'WRITE'
	| 'IF'
	| 'ELSIF'
	| 'ENDIF'
	| 'DO'
	| 'WHILE'
	| 'CONTINUE'
	| 'BREAK'
	| 'RETURN'
	| 'INT'
	| 'VOID'
	| 'STRING'
	| 'FLOAT'
	| 'TRUE'
	| 'FALSE'
	;

OPERATORS:
	':='
	| '+'
	| '-'
	| '*'
	| '/'
	| '='
	| '!='
	| '<'
	| '>'
	| '('
	| ')'
	| ';'
	| ','
	| '<='
	| '>='
	;

EMPTY:
	[ \t\r\n\f]
	;

IDENTIFIERS:
	[a-zA-Z][a-zA-Z0-9]*;

FLOATLITERAL:
	[0-9]*'.'[0-9]+;

INTLITERAL:
	[0-9]+;

STRINGLITERAL:
	'"'.*?'"';		

