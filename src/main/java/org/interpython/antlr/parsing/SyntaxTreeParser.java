package org.interpython.antlr.parsing;

import org.antlr.v4.runtime.ANTLRFileStream;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.interpython.antlr.InterPythonLexer;
import org.interpython.antlr.InterPythonParser;
import org.interpython.antlr.parsing.statements.StatementVisitor;
import org.interpython.antlr.parsing.statements.Statements;

import java.io.IOException;
import java.util.ArrayDeque;
import java.util.Queue;

public class SyntaxTreeParser {
    public Queue<Statements> parse(String fileAbsolutePath) throws IOException {
        CharStream charStream = new ANTLRFileStream(fileAbsolutePath);
        InterPythonLexer lexer = new InterPythonLexer(charStream);
        CommonTokenStream tokenStream = new CommonTokenStream(lexer);
        InterPythonParser parser = new InterPythonParser(tokenStream);
        StatementVisitor statementVisitor = new StatementVisitor();
        for (InterPythonParser.StatementContext statementContext : parser.code().statement()) {
            statementVisitor.visit(statementContext);
        }

        Queue<Statements> ret = statementVisitor.instructionsQueue;

        return ret;

    }
}
