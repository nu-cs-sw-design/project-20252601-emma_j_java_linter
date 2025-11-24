import org.objectweb.asm.ClassReader;

import asm.ClassInfo;
import asm.MyClassVisitor;

import java.io.IOException;
import java.util.List;
import java.util.jar.Attributes.Name;

import checks.NameCheck;
import checks.CycleCheck;
import checks.RedundancyCheck;

public class Linter {

    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("args length should be 1");
            return;
        }

        String classFilePath = args[0];

        try {
            ClassInfo classInfo = new ClassInfo();
            ClassReader classReader = new ClassReader(classFilePath);
            MyClassVisitor classVisitor = new MyClassVisitor(classInfo);
            System.out.println("currently checking class: " + classInfo.className);

            NameCheck nameCheck = new NameCheck();
            nameCheck.run(classInfo);

            CycleCheck cycleCheck = new CycleCheck();
            cycleCheck.run(classInfo);

            RedundancyCheck redundancyCheck = new RedundancyCheck();
            redundancyCheck.run(classInfo);


        } catch (IOException e) {
            System.out.println("error reading class file");
            e.printStackTrace();
        }     
    }
    
}
