Joel Saindon
CS4300 Proj4
Decision Trees

An Important Note! please be sure to edit the Main.java file for the location of the test file you want to use; if the entering file directory doesn't work.

This project was written in Eclipse so the submission should include:
	A zip of the proj4, containing (amongst Eclipse package items):
		Main.java
		Node.java
		test.txt
		test2.txt
		This README file
		
Main structure of the program
	Splits on features are 0-based indexes (i.e. 1st column is col 0, 2nd is column 1, etc.)
	The Tree class uses Nodes and lists to represent the actual tree
	The Node class uses ArrayLists of rows to represent the rows that are: within the node pre-split and then which nodes have nodes that have decisions
		vars that are true when associated with the column.
	


Items not implemented:
	Entropy calculations (started, not working; see functions at bottom of main)
	Recursively building tree (also started, not working; see commented sections in function runProcedure).
	Entering vector for prediction using completed tree
	

Items implemented:
	test file by argument (static path only (at least in my environment) as in C:\\Users\\User\\eclipse-workspace\\CS4300-Proj4\\src\\test2.txt)
	Table calculations (fitness of feature columns, pseudo-entropy)
	File I/O parsing into Lists of rows/cols/features
	Finding a pure feature
	Removing columns after split
	
	
Other notes:
	Project went pretty smoothly over all, just couldn't troubleshoot my recursion into a workable state. The bulk of the actions I have figured out.
	As it sits, the program only does one iteration of splitting.
	Grader can undo comments on lines at the bottom of function runProcedure to see what recursion does 