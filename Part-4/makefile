JFLAGS = -g
JC = javac
OPT = -O3
WARN = -Wall

Exam: Exam.java Answer.java Question.java
	$(JC) $(JFLAGS)  ExamBuilder.java
	$(JC) $(JFLAGS)  ExamTaker.java
	$(JC) $(JFLAGS)  ExamGrader.java

clean:
	rm -rf *.class
