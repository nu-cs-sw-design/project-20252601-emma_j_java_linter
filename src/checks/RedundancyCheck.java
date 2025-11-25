package checks;
import asm.ClassInfo;

//import java.util.List;
import java.util.Set;

public class RedundancyCheck {

    public void run(ClassInfo info) {
        System.out.println("running redundancy check...");
        String className = info.className;
        Set<String> dependencies = info.referencedClasses;

        if ((className == null) || (dependencies == null))
            return; //nothing to check

        //check for self-dependency and self-inheritance
        if (dependencies.contains(className)) {
            System.out.println("RedundancyCheck Warning: Class '" + className
            + "' references itself, may indicate redundant code.");
        }
    }
    
}
