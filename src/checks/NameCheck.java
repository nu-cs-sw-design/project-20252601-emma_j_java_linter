package checks;

import asm.ClassInfo;
import org.objectweb.asm.Opcodes;

public class NameCheck {

    private static final int MAX_NAME_LENGTH = 30;
    
    public void run(ClassInfo info) {


        System.out.println("running name Check...");
        //class names should start w uppercase
        if ((info.className != null) && (!info.className.isEmpty())) {
            char firstChar = info.className.charAt(0);
            if (!Character.isUpperCase(firstChar)) {
                System.out.println("NameCheck Warning: Class name '" + 
                info.className + "' should start with an uppercase letter.");
            }

            checkNameLength("Class", info.className);
        }

        //field names should start w lowercase, except constants
        for (String fieldName : info.fields) {
            Integer mods = info.fieldModifiers.get(fieldName);
            boolean isConstant = ((mods & Opcodes.ACC_FINAL) != 0) && 
                ((mods & Opcodes.ACC_STATIC) != 0);
            if (isConstant) {
                if (!fieldName.matches("[A-Z0-9_]+")) {
                    System.out.println("NameCheck Warning: Constant field name '" +
                    fieldName + "' should be all uppercase letters, numbers, or underscores.");
                }
            } else if ((fieldName != null) && (!fieldName.isEmpty())) {
                char firstChar = fieldName.charAt(0);
                if (!Character.isLowerCase(firstChar)) {
                    System.out.println("NameCheck Warning: Field name '" + 
                    fieldName + "' should start with a lowercase letter.");
                }
            }
            checkNameLength("Field", fieldName);
        }

        //method names should start w lowercase
        for (String methodName : info.methods) {

            //stop picking up <init>
            if (methodName.equals("<init>")) continue;

            if ((methodName != null) && (!methodName.isEmpty())) {
                char firstChar = methodName.charAt(0);
                if (!Character.isLowerCase(firstChar)) {
                    System.out.println("NameCheck Warning: Method name '" +
                    methodName + "' should start with a lowercase letter.");
                }
            }
            checkNameLength("Method", methodName);
        }


    }  
    
    private void checkNameLength(String type, String name) {
    if ((name != null) && (name.length() > MAX_NAME_LENGTH)) {
        System.out.println("NameCheck Warning: " + type + " name '" +
        name + "' exceeds maximum length of " + MAX_NAME_LENGTH + " characters.");
        }
    }
}
