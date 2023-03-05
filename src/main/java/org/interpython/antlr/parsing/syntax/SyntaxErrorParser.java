package org.interpython.antlr.parsing.syntax;

import org.antlr.v4.runtime.BaseErrorListener;
import org.antlr.v4.runtime.CommonToken;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.Recognizer;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;

public class SyntaxErrorParser extends BaseErrorListener {
    protected String processingFileName;

    public SyntaxErrorParser(String processingFileName) {
        super();
        this.processingFileName = processingFileName;
    }

    @Override
    public void syntaxError(Recognizer<?, ?> recognizer, Object offendingSymbol, int line, int charPositionInLine, String msg, RecognitionException e) {
        String lineStr;
        try {
            lineStr = Files.lines(Path.of(recognizer.getInputStream().getSourceName())).toList().get(line-1);
        } catch (Exception ex) {
            lineStr = "";
        }

        if(offendingSymbol != null) {
            offendingSymbol = ((CommonToken) offendingSymbol).getText();
            System.out.println(
                    "  File \"" + processingFileName + "\", line " + line + "\n"
                            + "    " + lineStr + "\n"
                            + "    " + " ".repeat(charPositionInLine) + "^".repeat(((String) offendingSymbol).length()) + "\n"
                            + "SyntaxError: Unexpected " + ((String) offendingSymbol).replace("<EOF>", "end of line")
                            .replace(System.lineSeparator(), "end of line")
            );
        }else if((lineStr.charAt(charPositionInLine) == '\'') || (lineStr.charAt(charPositionInLine) == '\"'))
            System.out.println(
                    "  File \"" + processingFileName + "\", line " + line + "\n"
                            + "    " + lineStr + "\n"
                            + "    " + " ".repeat(charPositionInLine) + "^" + "\n"
                            + "SyntaxError: Unclosed string definition"
            );

        System.exit(1);
    }
}
