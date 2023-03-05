grammar InterPython;

@header {
package org.interpython.antlr;
}

code : (statement ( ('\r' | '\n' | '\r\n' | ';') statement )* ('\r' | '\n' | '\r\n' | ';')*)? EOF;

statement :
      expression |
      assignment;

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
    '+'
    '-'
    ;

arith_op :
    '+' |
    '-'
    ;

term_op :
    '*' |
    '@' |
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
     | STRING                                                                   #STRING
    ;

IF : 'if';
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
STRING : '\'' NAME '\'' | '"' NAME '"';

WS: [ \t\n\r]+ -> skip ;