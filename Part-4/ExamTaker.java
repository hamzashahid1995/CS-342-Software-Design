/**
 * ExamTaker main written by Joshua Herman
 */
import java.io.*;
import java.util.*;

public class ExamTaker
{
    public static void main(String[] args) throws FileNotFoundException
    {
        System.out.println("Joshua Herman");
        System.out.println("Jherma20\n");

        Scanner input = ScannerFactory.getKeyboardScanner();
        System.out.println("Input exam file.");
        String in = input.nextLine();

        File examFile = new File(in); //input exam file
        File studentAnswers = new File("answers.txt");  //input student answers file
        File shuffledExam = new File("shuffled.txt");

        PrintWriter shuffle = new PrintWriter(shuffledExam);

        PrintWriter answers = new PrintWriter(studentAnswers); //printwriter for answers
        Scanner inputExam = new Scanner(examFile); //scanner for exam file

        Exam exam1 = new Exam(inputExam); //make exam object

        System.out.println("Go through the exam once type skip if you want to skip else press enter\n");
        exam1.getName(); //prompt for students name

        exam1.reorderMCAnswers(-1); //reorder answers
        exam1.reorderQuestions(); //reorder questions
        exam1.print(); //print the exam

        System.out.println("Go through the exam once type skip if you want to skip else press enter\n");
        exam1.getAnswerFromStudent(-1); //get answer from student one at a time

        int a = 0;
        while (a != 1)
        {
            System.out.println("Would you like to change answers? Y/N"); //prompt user if they want to change answers
            String temp = input.nextLine(); //get input

            if (temp.equalsIgnoreCase("Y")) //if Y ask for answers again
            {
                System.out.println("Go through the exam once type skip if you want to skip else press enter\n"); //see if they want to skip another question
                exam1.getAnswerFromStudent(-1);	//get answers from student 1 by 1
            }
            else
            {
                a = 1; //else break
            }
        }

        exam1.saveStudentAnswers(answers, shuffledExam); //save student answers to shuffledExam

        //close printwriters
        answers.close();
        inputExam.close();

        exam1.save(shuffle); //save shuffled exam
        shuffle.close();
        System.out.println("Answers saved to answers.txt"); //inform user answers saved

    }
}