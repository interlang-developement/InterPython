grammar InterPython;

@header {
package org.interpython.antlr;
}

tokens { INDENT, DEDENT }

@lexer::members {
  	private boolean pendingDent = true;
  	private int indentCount = 0;
  	private java.util.LinkedList<Token> tokenQueue = new java.util.LinkedList<>();
  	private java.util.Stack<Integer> indentStack = new java.util.Stack<>();
  	private Token initialIndentToken = null;
  	private int getSavedIndent() { return indentStack.isEmpty() ? 0 : indentStack.peek(); }

  	private CommonToken createToken(int type, String text, Token next) {
  		CommonToken token = new CommonToken(type, text);
  		if (null != initialIndentToken) {
  			token.setStartIndex(initialIndentToken.getStartIndex());
  			token.setLine(initialIndentToken.getLine());
  			token.setCharPositionInLine(initialIndentToken.getCharPositionInLine());
  			token.setStopIndex(next.getStartIndex()-1);
  		}
  		return token;
  	}

  	@Override
  	public Token nextToken() {
  		if (!tokenQueue.isEmpty()) { return tokenQueue.poll(); }

  		Token next = super.nextToken();
  		if (pendingDent && null == initialIndentToken && NEWLINE != next.getType()) { initialIndentToken = next; }
  		if (null == next || HIDDEN == next.getChannel() || NEWLINE == next.getType()) { return next; }

  		if (next.getType() == EOF) {
  			indentCount = 0;
  			if (!pendingDent) {
  				initialIndentToken = next;
  				tokenQueue.offer(createToken(NEWLINE, "NEWLINE", next));
  			}
  		}

  		while (indentCount != getSavedIndent()) {
  			if (indentCount > getSavedIndent()) {
  				indentStack.push(indentCount);
  				tokenQueue.offer(createToken(InterPythonParser.INDENT, "INDENT" + indentCount, next));
  			} else {
  				indentStack.pop();
  				tokenQueue.offer(createToken(InterPythonParser.DEDENT, "DEDENT" + getSavedIndent(), next));
  			}
  		}
  		pendingDent = false;
  		tokenQueue.offer(next);
  		return tokenQueue.poll();
  	}
}

code : lines EOF;

lines : NEWLINE* (statement ( NEWLINE statement )* NEWLINE*)?;

statement :
        complex_statement
      | expression
      | assignment
      ;

complex_statement :
        if_expr;

if_expr :
    IF ifexpr=expression ifblock=code_block (ELIF expression code_block)* (ELSE elseblock=code_block)?
    ;

code_block : ':' NEWLINE* INDENT lines DEDENT;

assignment :
    NAME '=' expression;



/* order of operations START */

expression : atom                                                                                                   #ATOM_EXPR
           | atom ('.' NAME)+                                                                                       #ATTRIBUTE_EXPR
           | atom '[' expression ']'                                                                                #SUBSCRIPT_EXPR
           | atom '[' expression? ':' expression? ':'? expression? ']'                                              #SLICE_EXPR
           | atom '(' (star_expr? (',' star_expr)* )?  (',' NAME '=' expression)* (',' must_star_expr)? (',')? ')'  #CALL_EXPR
           |'await' expression                                                                                      #AWAIT_EXPR
           | expression POWER expression                                                                            #EXPONENTS_EXPR
           | unary_symbol_op expression                                                                             #UNARY_SYMBOL_EXPR
           | expression term_op expression                                                                          #MULTIPLICATIVE_EXPR
           | expression arith_op expression                                                                         #ADDITIVE_EXPR
           | expression shifts expression                                                                           #SHIFT_EXPR
           | expression BITWISE_AND expression                                                                      #BITWISE_AND_EXPR
           | expression BITWISE_XOR expression                                                                      #BITWISE_XOR_EXPR
           | expression BITWISE_OR expression                                                                       #BITWISE_OR_EXPR
           | expression ide_op expression                                                                           #IDENTITY_EXPR
           | expression eq_op expression                                                                            #EQUALITY_EXPR
           | expression comp_op expression                                                                          #COMPARISON_EXPR
           | NOT expression                                                                                         #LOGICAL_NOT_EXPR
           | expression AND expression                                                                              #LOGICAL_AND_EXPR
           | expression OR expression                                                                               #LOGICAL_OR_EXPR
           | expression IF expression ELSE expression                                                               #TERNARY_EXPR
           | 'lambda' NAME (',' NAME)* (',')? ':' expression                                                        #LAMBDA_EXPR
           ;

star_expr :
    ('*')? expression
    ;

must_star_expr :
    '*' expression
    ;


unary_symbol_op :
    '~' |
    '+' |
    '-'
    ;

arith_op :
    '+' |
    '-'
    ;

term_op :
    '*' |
    '/' |
    '//' |
    '%'
    ;

shifts :
    '<<' |
    '>>'
    ;

ide_op :
    'in' |
    'not in' |
    'is' |
    'is not'
    ;

eq_op :
    '==' |
    '!='
    ;
comp_op :
    '<' |
    '>' |
    '==' |
    '>=' |
    '<=' |
    '!='
    ;

/* order of operations END */

atom : '(' expression ')'                                                       #PARENTHESIS
     | '[' (expression (',' expression)*)? ']'                                  #LIST
     | '{' (expression (',' expression)*)? '}'                                  #SET
     | '{' (expression ':' expression (',' expression ':' expression)*)? '}'    #DICT
     | NAME                                                                     #VAR
     | NUMBER                                                                   #INT
     | FLOAT                                                                    #FLOAT
     | STRING                                                                   #STRING
    ;

IF : 'if';
ELIF : 'elif';
ELSE : 'else';

OR : 'or';
AND : 'and';
NOT : 'not';

BITWISE_OR : '|';
BITWISE_XOR : '^';
BITWISE_AND : '&';

POWER : '**';

NAME : [a-zA-Z_][a-zA-Z0-9_]*;
NUMBER : [0-9]+;
FLOAT: NUMBER '.' NUMBER;
STRING : '\'' (~('\r' | '\n' | '\'') ('\\\'')? )* '\'' | '"' (~('\r' | '\n' | '"') ('\\"')? )* '"';

NEWLINE : ( '\r'? '\n' | '\r' ) {
    if (pendingDent) { setChannel(HIDDEN); }
    pendingDent = true;
    indentCount = 0;
    initialIndentToken = null;
} ;

WS : [ \t]+ {
    setChannel(HIDDEN);
    if (pendingDent) { indentCount += getText().length(); }
} -> skip;
