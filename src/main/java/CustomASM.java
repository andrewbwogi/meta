import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

public class CustomASM {
    private static final String TRANSFORM_METHOD_NAME = "main";

    public void transform(String fileIn,String fileOut) throws Exception{
        FileInputStream is = new FileInputStream(fileIn);
        byte[] b;
        ClassReader cr = new ClassReader(is);
        ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_FRAMES);
        cr.accept(new ClassVisitor(Opcodes.ASM5, cw) {
            @Override
            public MethodVisitor visitMethod(int a, String name, String d, String s, String[] e) {
                MethodVisitor mv = super.visitMethod(a, name, d, s, e);
                if(name.equals(TRANSFORM_METHOD_NAME)) {
                    return new MethodVisitor(Opcodes.ASM5, mv) {
                        @Override
                        public void visitIntInsn(int opcode, int operand) {

                            // the transformation
                            super.visitIntInsn(opcode, 5);
                        }
                    };
                }
                return mv;
            }
        }, 0);
        b = cw.toByteArray();
        FileOutputStream fos = new FileOutputStream(fileOut);
        fos.write(b);
        fos.close();
    }
}
