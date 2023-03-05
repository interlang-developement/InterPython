package org.interpython.antlr.compiler;

import org.interpython.antlr.parsing.SyntaxTreeParser;
import org.interpython.antlr.parsing.statements.Statements;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.Queue;

public class Compiler {
    public static void main(String[] args) throws Exception{
        new Compiler().compile(args);
    }

    public void compile(String[] args) throws Exception {
        //arguments validation skipped (check out full code on GitHub)
        final File pythonFile = new File(args[0]);
        String fileName = pythonFile.getName();
        String fileAbsolutePath = pythonFile.getAbsolutePath();
        Queue<Statements> statements = new SyntaxTreeParser().parse(fileAbsolutePath);
        final byte[] bytecode = new ByteCodeConverter().generateBytecode(statements, fileName.substring(0, fileName.lastIndexOf('.')));

        final String classFile = fileName.substring(0, fileName.lastIndexOf('.')) + ".class";

        OutputStream os = new FileOutputStream(classFile);
        os.write(bytecode);
        os.close();
    }
}
