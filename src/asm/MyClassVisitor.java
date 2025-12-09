package asm;

import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.FieldVisitor;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Type;

public class MyClassVisitor extends ClassVisitor{

    private final ClassInfo classInfo;

    public MyClassVisitor(ClassInfo classInfo) {
        super(Opcodes.ASM9);
        this.classInfo = classInfo;
    }

    @Override
    //gather basic class info
    public void visit(int version, int access, String name, String signature, String superName, String[] interfaces) {

        //normalize class name
        classInfo.className = name.replace('/', '.');

        //normalize superclass name + add to referenced
        classInfo.superClassName = (superName != null ? superName.replace('/', '.') : null);
        if (superName != null) {
            classInfo.referencedClasses.add(classInfo.superClassName);
        }

        if (interfaces != null) {
            for (String iface : interfaces) {
                //normalize and record interface
                String fixed = iface.replace('/', '.');
                classInfo.interfaceNames.add(fixed);
                classInfo.referencedClasses.add(fixed);
            }
        }

        //asm continue
        super.visit(version, access, name, signature, superName, interfaces);
    }

    @Override
    //gather method info (called once per method in class)
    public MethodVisitor visitMethod(int access, String name, String descriptor, String signature, String[] exceptions) {
        classInfo.methods.add(name);
        classInfo.methodModifiers.put(name, access);

        //track return + argument types
        Type methodType = Type.getMethodType(descriptor);
        classInfo.referencedClasses.add(methodType.getReturnType().getClassName());
        for (Type arg : methodType.getArgumentTypes()) {
            classInfo.referencedClasses.add(arg.getClassName());
        }

        //track bytecode-level dependencies (method calls, field access, new A(), etc.)
        return new MethodVisitor(Opcodes.ASM9, super.visitMethod(access, name, descriptor, signature, exceptions)) {

            @Override
            public void visitFieldInsn(int opcode, String owner, String fieldName, String fieldDesc) {
                classInfo.referencedClasses.add(owner.replace('/', '.'));
                super.visitFieldInsn(opcode, owner, fieldName, fieldDesc);
            }

            @Override
            public void visitMethodInsn(int opcode, String owner, String methodName, String methodDesc, boolean itf) {
                classInfo.referencedClasses.add(owner.replace('/', '.'));
                super.visitMethodInsn(opcode, owner, methodName, methodDesc, itf);
            }

            @Override
            public void visitTypeInsn(int opcode, String type) {
                classInfo.referencedClasses.add(type.replace('/', '.'));
                super.visitTypeInsn(opcode, type);
            }
        };
    }

    @Override
    //gather field info (called once per field in class)
    public FieldVisitor visitField(int access, String name, String descriptor, String signature, Object value) {
        classInfo.fields.add(name);
        classInfo.fieldModifiers.put(name, access);

        //extract and add field type
        String typeName = Type.getType(descriptor).getClassName();
        classInfo.referencedClasses.add(typeName);

        return super.visitField(access, name, descriptor, signature, value);
    }

}
