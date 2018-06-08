import java.util.*;
import java.io.*;
//
// Hamza Shahid
// hshahid
// CS 342 - Term Project Part 4
//
public class MCMAQuestion extends MCQuestion
{
    protected ArrayList<Answer> answers;
    public double baseCredit;


    public MCMAQuestion(String text, double maxValue, double baseCredit)
    {
        super(text,maxValue);
        this.baseCredit = baseCredit;
        answers = new ArrayList<Answer>();
    }

    protected MCMAQuestion(Scanner s)
    {
        answers = new ArrayList<>();
        answersList = new ArrayList<>();

        maxValue = Double.parseDouble(s.nextLine());
        this.text = s.nextLine();
        this.baseCredit = Double.parseDouble(s.nextLine());

        int i = Integer.parseInt(s.nextLine());

        for(int j = 0; j < i; j++)
            super.addAnswer(new MCMAAnswer(s));
    }


    //==============================================================
    public Answer getNewAnswer()
    {
        MCMAAnswer ans = new MCMAAnswer(text,maxValue);
        return ans;
    }


    //==============================================================
    public Answer getNewAnswer(String text, double creditIfSelected)
    {
        MCMAAnswer ans = new MCMAAnswer(text,creditIfSelected);
        return ans;
    }


    //==============================================================
    // This method creates a MCSAAnswer object which gets the answer
    // from the student/ keyboard, and then stores the answer in the
    // student Answer field of the class. Calling this function will
    // ask the user to input a, b, c, d or e as inputs.
    public void getAnswerFromStudent()
    {
        Scanner input = ScannerFactory.getKeyboardScanner();
        String temp = input.nextLine();
        String[] ans = temp.split(" ");

        for(String s: ans){
            s = s.toUpperCase();
            answers.add((MCMAAnswer) answersList.get((char)(s.charAt(0)) - 65));
        }
    }


    //==============================================================
    public double getValue()
    {
        double accum = 0;

        for(Answer a: answers)
        {
            accum += super.getValue(a);
        }
        return (accum + baseCredit) * maxValue;
    }


    //==============================================================
    public void save(PrintWriter printWriter)
    {
        printWriter.println(maxValue);
        printWriter.println(text);
        printWriter.println(this.baseCredit);
        printWriter.println(answersList.size());

        for(Answer a : answersList)
            a.save(printWriter);
    }


    //==============================================================
    public void saveStudentAnswers(PrintWriter printWriter)
    {
        printWriter.println(answers.size());

        for(Answer a : answers)
            a.saveStudentAnswers(printWriter);
    }


    //==============================================================
    public void restoreStudentAnswers(Scanner sc)
    {
        int i = Integer.parseInt(sc.nextLine());

        for(int j = 0; j < i; j++) {
            answers.add(new MCMAAnswer(sc));
        }
    }
}