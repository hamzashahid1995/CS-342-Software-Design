#
# A simple makefile for compiling three java classes
#

# define a makefile variable for the java compiler
#
JCC = javac

# define a makefile variable for compilation flags
# the -g flag compiles with debugging information
#
JFLAGS = -g

# typing 'make' will invoke the first target entry in the makefile
# (the default one in this case)
#
default: ExamTester.class Answer.class MCAnswer.class MCSAAnswer.class MCMAAnswer.class SAAnswer.class Question.class MCQuestion.class MCSAQuestion.class MCMAQuestion.class SAQuestion.class Exam.class ScannerFactory.class

# this target entry builds the Average class
# the Average.class file is dependent on the Average.java file
# and the rule associated with this entry gives the command to create it
#
ExamTester.class: ExamTester.java
	$(JCC) $(JFLAGS) ExamTester.java

Answer.class: Answer.java
	$(JCC) $(JFLAGS) Answer.java

MCAnswer.class: MCAnswer.java
	$(JCC) $(JFLAGS) MCAnswer.java

MCSAAnswer.class: MCSAAnswer.java
	$(JCC) $(JFLAGS) MCSAAnswer.java

MCMAAnswer.class: MCMAAnswer.java
	$(JCC) $(JFLAGS) MCMAAnswer.java

SAAnswer.class: SAAnswer.java
	$(JCC) $(JFLAGS) SAAnswer.java

Quesiton.class: Quesiton.java
	$(JCC) $(JFLAGS) Quesiton.java

MCQuestion.class: MCQuestion.java
	$(JCC) $(JFLAGS) MCQuestion.java

MCSAQuestion.class: MCSAQuestion.java
	$(JCC) $(JFLAGS) MCSAQuestion.java

MCMAQuestion.class: MCMAQuestion.java
	$(JCC) $(JFLAGS) MCMAQuestion.java

SAQuestion.class: SAQuestion.java
	$(JCC) $(JFLAGS) SAQuestion.java

Exam.class: Exam.java
	$(JCC) $(JFLAGS) Exam.java

ScannerFactory.class: ScannerFactory.java
	$(JCC) $(JFLAGS) ScannerFactory.java

# To start over from scratch, type 'make clean'.
# Removes all .class files, so that the next make rebuilds them
#
clean:
	$(RM) *.class
