package org.interpython.antlr.parsing;

import org.antlr.v4.runtime.ANTLRFileStream;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.interpython.antlr.InterPythonLexer;
import org.interpython.antlr.InterPythonParser;
import org.interpython.antlr.parsing.statements.StatementVisitor;
import org.interpython.antlr.parsing.statements.Statements;
import org.interpython.antlr.parsing.syntax.SyntaxErrorParser;
import org.interpython.core.utils.Scope;

import java.io.IOException;
import java.util.Queue;

public class SyntaxTreeParser {
    public Queue<Statements> parse(String fileAbsolutePath) throws IOException {
        CharStream charStream = new ANTLRFileStream(fileAbsolutePath);
        InterPythonLexer lexer = new InterPythonLexer(charStream);
        lexer.removeErrorListeners();
        lexer.addErrorListener(new SyntaxErrorParser(fileAbsolutePath.substring(fileAbsolutePath.lastIndexOf('/') + 1)));

        CommonTokenStream tokenStream = new CommonTokenStream(lexer);

        InterPythonParser parser = new InterPythonParser(tokenStream);
        parser.removeErrorListeners();
        parser.addErrorListener(new SyntaxErrorParser(fileAbsolutePath.substring(fileAbsolutePath.lastIndexOf('/') + 1)));

        StatementVisitor statementVisitor = new StatementVisitor(new Scope(null));

        statementVisitor.visit(parser.code());

        Queue<Statements> ret = statementVisitor.instructionsQueue;

        return ret;

    }
}
