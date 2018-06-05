import java.io.*;
import java.util.*;
//
// Hamza Shahid
// hshahid
// CS 342 - Term Project Part 3
//
public class ExamTester
{
    public static void main(String[] args) throws FileNotFoundException{
        System.out.println();


        File Exam1 = new File("exam.txt");
        File shuffledExam = new File("shuffled.txt");
        File studentAnswers = new File("answers.txt");

        PrintWriter shuffle = new PrintWriter(shuffledExam);
        PrintWriter answers = new PrintWriter(studentAnswers);
        Scanner inputExam = new Scanner(Exam1);

        Exam e = new Exam(inputExam);

        e.getName();
        e.reorderMCAnswers(-1);
        e.reorderQuestions();
        e.print();
        e.getAnswerFromStudent(-1);
        System.out.println("Value: " + e.getValue());
        e.reportQuestionValues();

        e.save(shuffle);
        e.saveStudentAnswers(answers);
        shuffle.close();
        answers.close();
        inputExam.close();

        System.out.println("Setting Exam to null and reloading answers...\n\n");

        e = null;

        Scanner shuffleInput = new Scanner(shuffledExam);
        Scanner theAnswers = new Scanner(studentAnswers);

        e = new Exam(shuffleInput);
        e.restoreStudentAnswers(theAnswers);
        System.out.println("Value: " + e.getValue());
        e.reportQuestionValues();

        shuffleInput.close();
        theAnswers.close();
    }
}
