# Prime Number Processing

-----------------------------------------------------------------------
## Description
This project processes a large input file and determines if the number provided on each line of the input file is prime or not. The reading of the input file can be done using one or multiple threads, passed as a command line parameter. The input file is read only once using multiple threads. Thread pool pattern is used for implementing the worker threads which read the input file line by line and check if the number is prime or not. If the number is prime, then it is inserted into an array. It is assumed that all non-even numbers are prime. Logger has also been implemented.
#### Note: Logger levels are as follows
DEBUG_VALUE=4 [Prints to stdout every time a constructoris called]<br/>
DEBUG_VALUE=3 [Prints to stdout every time a thread's run()method is called]<br/>
DEBUG_VALUE=2 [Prints to stdout every time an entry is addedto the Results data structure]<br/>
DEBUG_VALUE=1 [Prints to stdout the contents of the datastructure in the store Results instance]<br/>
DEBUG_VALUE=0 [Prints the line "The sume of all theprime numbers is: XYZ"]<br/>

#### Note: In this implementation the threads are not returned to the thread pool.

-----------------------------------------------------------------------
## Inputs
input_file.txt with the following format: <NUM_TO_BE_CHECKED><br/>
number_of_threads between: 1-5<br/>
debug_level between: 0-4

-----------------------------------------------------------------------
Following are the commands and the instructions to run ANT on your project.
#### Note: build.xml is present in primeNumberValidation/src folder.

-----------------------------------------------------------------------
## Instruction to clean:

####Command: ant -buildfile primeNumberValidation/src/build.xml clean

Description: It cleans up all the .class files that were generated when you
compiled your code.

-----------------------------------------------------------------------
## Instruction to compile:

####Command: ant -buildfile primeNumberValidation/src/build.xml all

Description: Compiles your code and generates .class files inside the BUILD folder.

-----------------------------------------------------------------------
## Instruction to run:

####Command: ant -buildfile primeNumberValidation/src/build.xml run -Darg0=<input_file.txt> -Darg1=<number_of_threads> -Darg2=<debug_level>

Note: Arguments accept the absolute path of the files.