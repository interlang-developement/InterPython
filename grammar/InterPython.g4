grammar InterPython;

@header {
package org.interpython.antlr;
}

code : statement ( ('\r' | '\n' | '\r\n' | ';') statement )* ('\r' | '\n' | '\r\n' | ';')* EOF;

statement :
      expression |
      assignment;

assignment :
    NAME '=' expression;

/* order of operations START */

expression :
    lambda_expression
    ;

lambda_expression :
    'lambda' NAME (',' NAME)* (',')? ':' lambda_expression |
    if_expression
    ;

if_expression :
    or_expression (IF or_expression ELSE if_expression)*
    ;
or_expression :
    and_expression (OR or_expression)*
    ;

and_expression :
    not_expression (AND and_expression)*
    ;

not_expression :
    NOT not_expression |
    comparison
    ;

comparison :
    equality (comp_op comparison)*
    ;

equality :
    identify (eq_op equality)*
    ;

identify :
    bitwise_or (ide_op atom)*
    ;

bitwise_or :
    bitwise_xor (BITWISE_OR bitwise_or)*
    ;

bitwise_xor :
    bitwise_and (BITWISE_XOR bitwise_xor)*
    ;

bitwise_and :
    shift (BITWISE_AND bitwise_and)*
    ;

shift :
    arith (shifts shift)*
    ;

arith :
    term (arith_op arith)*
    ;

term :
    unary_symbol (term_op term)*
    ;

unary_symbol :
    unary_symbol_op unary_symbol | exponetial
    ;

exponetial :
    await_expr (POWER exponetial)*
    ;

await_expr :
    ('await')* call_slice_attribute_expr
    ;

call_slice_attribute_expr :
    atom ('.' NAME)+ |          // attribute
    atom '[' expression ']'|        //subscription
    atom '[' expression? ':' expression? ':'? expression? ']' |         //slice
    atom '(' (star_expr? (',' star_expr)* )?  (',' NAME '=' expression)* (',' must_star_expr)? (',')? ')' |         //call
    atom
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

atom :
    '(' expression ')' |
    '[' (expression (',' expression)*)? ']' |
    '{' (expression (',' expression)*)? '}' |
    '{' (expression ':' expression (',' expression ':' expression)*)? '}' |
    NAME |
    NUMBER |
    STRING
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