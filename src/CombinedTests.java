// bad class name
class badClassNameTest {
    int fieldOne;
    public void testMethod() {}
}

//bad field name
class BadFieldNameTest {
    int GoodField;  //bad
    int okfield;

    public void testMethod() {}
}

//bad method name
class BadMethodNameTest {
    public void GoodMethod() {}  //bad
    public void okMethod() {}
}

//bad constant naming
class BadConstantNameTest {
    public static final int badConstant = 10;      //bad
    public static final int GOOD_CONSTANT = 20;
}

//too long
class ClassTooLonggggggggggggggggggggggggggggggggggggggggggg {
    int okField;
    public void okMethod() {}
}
class LongFieldNameTest {
    int fieldNameTooLonggggggggggggggggggggggggggggggggggggggggggg;
    public void okMethod() {}
}
class LongMethodNameTest {
    public void methodNameTooLonggggggggggggggggggggggggggggggggggggggggggg() {}
}


//cyclic dependency
class AdependsOnB {
    BdependsOnA b = new BdependsOnA();
}

class BdependsOnA {
    AdependsOnB a = new AdependsOnB();
}


//tons of dependencies
class HighDependencyClass {
    A a;
    B b;
    C c;
    D d;
    E e;
    F f;
    G g;
    H h;
    I i;
    J j;
    K k; //11 in total

    void useAll() {
        System.out.println(a);
        System.out.println(b);
        System.out.println(c);
        System.out.println(d);
        System.out.println(e);
        System.out.println(f);
        System.out.println(g);
        System.out.println(h);
        System.out.println(i);
        System.out.println(j);
        System.out.println(k);
    }
}

class A {}
class B {}
class C {}
class D {}
class E {}
class F {}
class G {}
class H {}
class I {}
class J {}
class K {}

//self dependency/inheritance
class SelfRef {
    SelfRef self = new SelfRef();

    void useSelf() {
        System.out.println(self);
    }
}