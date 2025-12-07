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


//more for other checks: TODO