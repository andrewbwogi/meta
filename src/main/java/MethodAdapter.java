import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

class MethodAdapter extends MethodVisitor implements Opcodes {

    public MethodAdapter(final MethodVisitor mv) {
        super(ASM5, mv);
    }

    @Override
    public void visitIntInsn(int opcode, int operand) {
        if(opcode == Opcodes.BIPUSH && operand == 10)
            operand = 5;
        super.visitIntInsn(opcode, operand);
    }
}
