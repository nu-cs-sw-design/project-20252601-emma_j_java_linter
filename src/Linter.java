import org.objectweb.asm.ClassReader;

import asm.ClassInfo;
import asm.MyClassVisitor;

import checks.NameCheck;
import checks.CycleCheck;
import checks.RedundancyCheck;

import java.io.FileInputStream;
import java.io.IOException;

public class Linter {

    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("args length should be 1");
            return;
        }

        String classFilePath = args[0];
        //add .class if not there
        if (!classFilePath.endsWith(".class")) {
            classFilePath = classFilePath + ".class";
        }


        try {
            ClassInfo classInfo = new ClassInfo();
            ClassReader classReader = new ClassReader(new FileInputStream(classFilePath));
            MyClassVisitor classVisitor = new MyClassVisitor(classInfo);

            // traverse class to fill info
            classReader.accept(classVisitor, 0);

            System.out.println("Currently checking class: " + classInfo.className);

            NameCheck nameCheck = new NameCheck();
            nameCheck.run(classInfo);

            CycleCheck cycleCheck = new CycleCheck();
            cycleCheck.run(classInfo);

            RedundancyCheck redundancyCheck = new RedundancyCheck();
            redundancyCheck.run(classInfo);

        } catch (IOException e) {
            System.out.println("Error reading class file");
            e.printStackTrace();
        }
    }
}
