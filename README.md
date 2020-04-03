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

-----------------------------------------------------------------------
## Description:

1. Assumptions:
- The first assumption would be the input is well formated with every number in new line.
- The input number are integer value only
- We are assuming that there are no duplicate  
2. Data structures:
- Vector: Used for storing the prime number results as it is thread safe.
- ArrayList: Used for storing the threads.

3. External Materials:
- Socket Programming - geeksforgeeks
- Multithreading wait(), notify() - tutorials point

4. Compiling:
Follow the instruction as mentioned above.

5. Run:
Follow the instructions as mentioned above.

6. Challenges Faced:
- One of our computer system crashed that lead to lesser co-ordination with each other in committing the code to the repository. Luckily, we got another system but it was too late for us to submit the assignment on time.

- Due to this and multiple submissions during those days, we were bound to use "4 slack days" in order to complete our assignment.

- Also, there are certain commits that we made by Akshay Anvekar but was registered on Kenneth Fernandes. Following are the commit Ids: 45f3a72, 5de3fbe, 93824fa, d504b1a.

--------------------------------------------------------------------------------
## Academic Honesty statement:
"We have done this assignment completely on our own. We have not copied it, nor have we given our solution to anyone else. We understand that if we are involved in plagiarism or cheating an official form will be submitted to the Academic Honesty Committee of the Watson School to determine the action that needs to be taken."

Date: 04/02/2020