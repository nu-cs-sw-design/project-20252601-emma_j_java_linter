# Project: Java Linter with ASM

## Contributors
Emma Johnston

## Dependencies
- Java 11 (11.0.23)
- `asm-9.9.jar`
- `asm-commons-9.9.jar`

## Build Instructions
- clone this repository to your machine
- Make sure the dependencies are fulfilled (run in Java v11 and download asm libraries)
- run the following commands, in order, in the src directory
- javac -cp ".:../libs/asm-9.9.jar:../libs/asm-commons-9.9.jar" Linter.java asm/*.java checks/*.java (Compile the entire linter)
- javac CombinedTests.java (Optional, for running tests only)


## To run the tests- copy and paste any of these (or one after another)

- java -cp ".:../libs/asm-9.9.jar:../libs/asm-commons-9.9.jar" Linter ./badClassNameTest.class
- java -cp ".:../libs/asm-9.9.jar:../libs/asm-commons-9.9.jar" Linter ./BadFieldNameTest.class
- java -cp ".:../libs/asm-9.9.jar:../libs/asm-commons-9.9.jar" Linter ./BadMethodNameTest.class
- java -cp ".:../libs/asm-9.9.jar:../libs/asm-commons-9.9.jar" Linter ./BadConstantNameTest.class
- java -cp ".:../libs/asm-9.9.jar:../libs/asm-commons-9.9.jar" Linter ./LongFieldNameTest.class
- java -cp ".:../libs/asm-9.9.jar:../libs/asm-commons-9.9.jar" Linter ./LongMethodNameTest.class
- java -cp ".:../libs/asm-9.9.jar:../libs/asm-commons-9.9.jar" Linter ./HighDependencyClass.class
- java -cp ".:../libs/asm-9.9.jar:../libs/asm-commons-9.9.jar" Linter ./SelfRef.class
- java -cp ".:../libs/asm-9.9.jar:../libs/asm-commons-9.9.jar" Linter ./AdependsOnBt.class
- java -cp ".:../libs/asm-9.9.jar:../libs/asm-commons-9.9.jar" Linter ./BdependsOnA.class
- java -cp ".:../libs/asm-9.9.jar:../libs/asm-commons-9.9.jar" Linter ./ClassTooLonggggggggggggggggggggggggggggggggggggggggggg.class

## Another testing option- test your own file/code instead of my test file

- create a file in the src folder, fill it with whatever you wish to use the linter on
- from inside src, run javaC <yourNewFile'sName>
- javac -cp ".:../libs/asm-9.9.jar:../libs/asm-commons-9.9.jar" Linter.java asm/*.java checks/*.java (Compile the entire linter)
- java -cp ".:../libs/asm-9.9.jar:../libs/asm-commons-9.9.jar" Linter ./<NewClassFile'sName> 
(that was created by running javaC <yourNewFile'sName>)
- repeat as necessary for any classes you wish to test!


