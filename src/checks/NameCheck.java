package checks;

import asm.ClassInfo;
import org.objectweb.asm.Opcodes;

public class NameCheck {
    
    public void run(ClassInfo info) {

        System.out.println("running name Check...");
        //class names should start w uppercase
        if ((info.className != null) && (!info.className.isEmpty())) {
            char firstChar = info.className.charAt(0);
            if (!Character.isUpperCase(firstChar)) {
                System.out.println("NameCheck Warning: Class name '" + 
                info.className + "' should start with an uppercase letter.");
            }
        }

        //field names should start w lowercase
        for (String fieldName : info.fields) {
            if ((fieldName != null) && (!fieldName.isEmpty())) {
                char firstChar = fieldName.charAt(0);
                if (!Character.isLowerCase(firstChar)) {
                    System.out.println("NameCheck Warning: Field name '" + 
                    fieldName + "' should start with a lowercase letter.");
                }
            }
        }

        //method names should start w lowercase
        for (String methodName : info.methods) {
            if ((methodName != null) && (!methodName.isEmpty())) {
                char firstChar = methodName.charAt(0);
                if (!Character.isLowerCase(firstChar)) {
                    System.out.println("NameCheck Warning: Method name '" +
                    methodName + "' should start with a lowercase letter.");
                }
            }
        }

        //constants should be all uppercase
        for (String fieldName : info.fields) {
            Integer mods = info.fieldModifiers.get(fieldName);
            boolean isConstant = ((mods & Opcodes.ACC_FINAL) != 0) && 
                ((mods & Opcodes.ACC_STATIC) != 0);
            if (isConstant) {
                if (!fieldName.matches("[A-Z0-9_]+")) {
                    System.out.println("NameCheck Warning: Constant field name '" +
                    fieldName + "' should be all uppercase letters, numbers, or underscores.");
                }
            }
        }


    }   
}
