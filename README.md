# CSX42: Assignment 3
## Name: Akshay Kumar Anvekar and Kenneth Fernandes


-----------------------------------------------------------------------
## Inputs
input_file.txt with the following format: <NUM_TO_BE_CHECKED><br/>
number_of_threads between: 1-5<br/>
debug_level between: 0-4

-----------------------------------------------------------------------
Following are the commands and the instructions to run ANT on your project.
#### Note: build.xml is present in socketProgramming/src folder.

-----------------------------------------------------------------------
## Instruction to clean:

####Command: ant -buildfile socketProgramming/src/build.xml clean

Description: It cleans up all the .class files that were generated when you
compiled your code.

-----------------------------------------------------------------------
## Instruction to compile:

####Command: ant -buildfile socketProgramming/src/build.xml all

Description: Compiles your code and generates .class files inside the BUILD folder.

-----------------------------------------------------------------------
## Instruction to run:

####Command: 
1. Persister Service:
ant -buildfile socketProgramming/src/build.xml run-persister-service -Darg0="src/BUILD/outputFile/output.txt" -Darg1=5000
2. Prime Detector: 
ant -buildfile socketProgramming/src/build.xml run-prime-detector -Darg0="src/input.txt" -Darg1=5 -Darg2=5 -Darg3="127.0.0.1" -Darg4=5000 -Darg5=1 

Note: Arguments accept the absolute path of the files.