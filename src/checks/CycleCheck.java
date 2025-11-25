package checks;

import asm.ClassInfo;
import java.util.Set;

public class CycleCheck {

    public void run(ClassInfo info) {
        System.out.println("running cycle check...");

        String className = info.className;
        Set<String> dependencies = info.referencedClasses;

        if ((className == null) || (dependencies == null))
            return; //nothing to check

        //check for tight coupling/too many dependencies
        if (dependencies.size() > 10) { //arbitrary threshold
            System.out.println("CycleCheck Warning: Class '" + className +
            "' has a high number of dependencies (" + dependencies.size() +
            "), which may indicate tight coupling.");
        }

        //check for A<-->B
        for (String dep: dependencies) {
            
            if (dep.equals(className))
                continue; //skip self

            if ((dep.endsWith(className) || className.endsWith(dep))) {
                System.out.println("CycleCheck Warning: Class '" + className +
                "' and class '" + dep + "' may have cyclic dependency.");
            }
        }

    }
    
}
