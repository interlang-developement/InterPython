package org.interpython.antlr.compiler;

import org.interpython.antlr.parsing.statements.Statements;
import org.interpython.antlr.parsing.statements.complexStatements.DefStatements;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

import java.util.Queue;

public class ByteCodeConverter implements Opcodes {

    public byte[] generateBytecode(Queue<Statements> instructionStatements, String name) {
        ClassWriter writer = new ClassWriter(0);
        MethodVisitor visitor;

        writer.visit(52, ACC_PUBLIC + ACC_SUPER, name, null, "java/lang/Object", null);
        {
            DefaultConstants.apply(writer);


            visitor = writer.visitMethod(ACC_PUBLIC + ACC_STATIC, "main", "([Ljava/lang/String;)V", null, null);

            //apply instructions generated from traversing parse tree!
            for (Statements instruction : instructionStatements) {
                if (!(instruction instanceof DefStatements)){
                    instruction.apply(visitor);
                }
            }
            visitor.visitInsn(RETURN); //add return instruction

//            visitor.visitMaxs(maxStack, (int) localVariablesCount); //set max stack and max local variables
            visitor.visitEnd();
//            visitor.visitLineNumber();
        }
        writer.visitEnd();

        return writer.toByteArray();
    }
}
