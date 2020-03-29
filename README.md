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

####Command: ant -buildfile socketProgramming/src/build.xml run -Darg0=<input_file.txt> -Darg1=<number_of_threads> -Darg2=<debug_level>

Note: Arguments accept the absolute path of the files.