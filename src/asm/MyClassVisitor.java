package asm;

import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.FieldVisitor;
import org.objectweb.asm.Opcodes;

public class MyClassVisitor extends ClassVisitor{

    private ClassInfo classInfo;

    public MyClassVisitor(ClassInfo classInfo) {
        super(Opcodes.ASM9);
        this.classInfo = classInfo;
    }

    @Override
    //gather basic class info
    public void visit(int version, int access, String name, String signature, String superName, String[] interfaces) {
        classInfo.className = name;
        classInfo.superClassName = superName;
        if (interfaces != null) {
            for (String iface : interfaces) {
                classInfo.interfaceNames.add(iface);
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
        return super.visitMethod(access, name, descriptor, signature, exceptions);
    }

    @Override
    //gather field info (called once per field in class)
    public FieldVisitor visitField(int access, String name, String descriptor, String signature, Object value) {
        classInfo.fields.add(name);
        classInfo.fieldModifiers.put(name, access);
        return super.visitField(access, name, descriptor, signature, value);
    }


}
