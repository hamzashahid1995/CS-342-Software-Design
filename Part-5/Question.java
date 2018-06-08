import java.io.PrintWriter;
import java.util.Scanner;
//
// Hamza Shahid
// hshahid
// CS 342 - Term Project Part 5
//
abstract public class Question
{
    protected String text;
    protected Answer rightAnswer;
    protected Answer studentAnswer;
    protected double maxValue;


    protected Question()
    {
        this.text = null;
        maxValue = 0;
        rightAnswer = null;
        studentAnswer = null;
    }

    protected Question(String text, double maxValue)
    {
        this.text = text;
        this.maxValue = maxValue;
        this.studentAnswer = null;
        this.rightAnswer = null;
    }

    protected Question(Scanner sc)
    {
    }


    //==============================================================
    public void saveStudentAnswers(PrintWriter printWriter)
    {

        studentAnswer.saveStudentAnswers(printWriter);
    }


    //==============================================================
    public void print()
    {

        System.out.println(this.text + "\n");
    }


    //==============================================================
    public void setRightAnswer(Answer rightAnswer)
    {

        this.rightAnswer = rightAnswer;
    }



    //==============================================================
    public void save(PrintWriter printWriter)
    {

        printWriter.println(this.text);
    }


    //==============================================================
    public String toString()
    {

        return text;
    }


    //==============================================================
    public void restoreStudentAnswers(Scanner sc)
    {
    }


    public abstract Answer getNewAnswer();
    public abstract void getAnswerFromStudent();
    public abstract double getValue();
}