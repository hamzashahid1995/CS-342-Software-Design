JFLAGS = -g
JC = javac
OPT = -O3
WARN = -Wall

Exam: Exam.java Answer.java Question.java
	$(JC) $(JFLAGS)  ExamBuilderGUI.java
	$(JC) $(JFLAGS)  ExamTakerGUI.java
	$(JC) $(JFLAGS)  ExamGraderGUI.java

clean:
	rm -rf *.class
