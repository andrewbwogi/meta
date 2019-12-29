import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

class ClassAdapter extends ClassVisitor implements Opcodes {
    private static final String TRANSFORM_METHOD_NAME = "main";
    private static final String TRANSFORM_METHOD_DESC = "([Ljava/lang/String;)V";

    public ClassAdapter(final ClassVisitor cv) {
        super(ASM5, cv);
    }

    @Override
    public MethodVisitor visitMethod(final int access, final String name,
                                     final String desc, final String signature, final String[] exceptions) {
        MethodVisitor mv = cv.visitMethod(access, name, desc, signature, exceptions);
        if(name.equals(TRANSFORM_METHOD_NAME)
                && desc.equals(TRANSFORM_METHOD_DESC)) {
            return new MethodAdapter(mv);
        }
        return mv;
    }
}
