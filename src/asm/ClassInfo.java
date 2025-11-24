package asm;
import java.util.*;

//collects information about a class during asm visit
public class ClassInfo {

    public String className;
    public String superClassName;

    public List<String> interfaceNames = new ArrayList<>();

    public List<String> methods = new ArrayList<>();
    //check if constant, among other things
    public Map<String, Integer> methodModifiers = new HashMap<>();

    public List<String> fields = new ArrayList<>();
    //check if constant, among other things
    public Map<String, Integer> fieldModifiers = new HashMap<>();

    //need this to track dependencies
    public Set<String> referencedClasses = new HashSet<>();
}
