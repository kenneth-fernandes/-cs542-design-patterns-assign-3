# CSX42: Assignment 2
## Name: Akshay Kumar Anvekar

-----------------------------------------------------------------------
-----------------------------------------------------------------------


Following are the commands and the instructions to run ANT on your project.
#### Note: build.xml is present in numberPlay/src folder.

-----------------------------------------------------------------------
## Instruction to clean:

```commandline
ant -buildfile numberPlay/src/build.xml clean
```

Description: It cleans up all the .class files that were generated when you
compiled your code.

-----------------------------------------------------------------------
## Instruction to compile:

```commandline
ant -buildfile numberPlay/src/build.xml all
```

Description: Compiles your code and generates .class files inside the BUILD folder.

-----------------------------------------------------------------------
## Instruction to run:

#### Use the below command to run the program.

```commandline
ant run -buildfile numberPlay/src/build.xml \
-DinputNumStream="numberPlay/src/File/input.txt" \
-DrunAvgWindowSize="3" \
-DrunAvgOutFile="numberPlay/src/File/AvgOutFile.txt" \
-Dk="3" \
-DtopKNumOutFile="numberPlay/src/File/topKNumOutFile" \
-DnumPeaksOutFile="numberPlay/src/File/numPeaksOutFile"
```

-----------------------------------------------------------------------
## Description:


1. Assumption

The input file will be well formatted with numbers in each new line.
The path for each file will be provided while passing the arguments from the command line.


2. Output

The output of the all the observers are performed and Running average , Number Peaks are writing into a file where as Top K number is no writing into a file but is priniting on the Console.

3. Data Structure
Used the List for performing the Top K numbers.
Used List for registering the observers for notifying the observers with filters.


4. References
Refered for singleton class to create getInstance()
Refered the creation of Data member varibales
Refered sorting and reversing in collection




-----------------------------------------------------------------------
### Academic Honesty statement:
-----------------------------------------------------------------------

"I have done this assignment completely on my own. I have not copied
it, nor have I given my solution to anyone else. I understand that if
I am involved in plagiarism or cheating an official form will be
submitted to the Academic Honesty Committee of the Watson School to
determine the action that needs to be taken. "

Date: [23/02/2020]


