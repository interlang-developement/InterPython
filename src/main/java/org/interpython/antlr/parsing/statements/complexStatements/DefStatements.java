package org.interpython.antlr.parsing.statements.complexStatements;

import org.interpython.antlr.parsing.statements.Statements;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.MethodVisitor;

public class DefStatements implements Statements {
    public String name;
    public CodeBlock codeBlock;

    public DefStatements(String name, CodeBlock codeBlock) {
        this.name = name;
        this.codeBlock = codeBlock;
    }

    @Override
    public void apply(MethodVisitor methodVisitor) {}

    public void apply(ClassWriter classWriter){
//        classWriter.visit(52, ACC_PUBLIC);
    }

    @Override
    public int localVariableCount() {
        return 0;
    }
}
