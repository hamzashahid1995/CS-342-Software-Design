import java.util.*;
import java.io.*;
//
// Hamza Shahid
// hshahid
// CS 342 - Term Project Part 4
//
public class MCSAQuestion extends MCQuestion
{
    public MCSAQuestion(String text, double maxValue)
    {

        super(text,maxValue);
    }

    public MCSAQuestion(Scanner sc)
    {
        answersList = new ArrayList<>();

        maxValue = Double.parseDouble(sc.nextLine());
        this.text = sc.nextLine();
        int i = Integer.parseInt(sc.nextLine());

        for(int j = 0; j < i; j++)
            super.addAnswer(new MCSAAnswer(sc));

    }


    //==============================================================
    // This method creates and returns a new Answer object, suitably
    // matched to the Question type of the object through which this
    // method is called.
    public Answer getNewAnswer()
    {
        MCSAAnswer ans = new MCSAAnswer(text,maxValue);
        return ans;
    }


    //==============================================================
    // This method creates and returns a new Answer object, suitably
    // matched to the Question type of the object through which this
    // method is called.
    public Answer getNewAnswer(String text, double creditIfSelected)
    {
        MCSAAnswer ans = new MCSAAnswer(text,creditIfSelected);
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
        String ans = input.nextLine();
        ans = ans.toUpperCase();
        char c = ans.charAt(0);

        MCSAAnswer temp = (MCSAAnswer) answersList.get((int)(c - 65));
        studentAnswer = temp;
    }


    //==============================================================
    public void save(PrintWriter printWriter)
    {
        printWriter.println(maxValue);
        printWriter.println(text);
        printWriter.println(answersList.size());

        for (Answer ans : answersList)
            ans.save(printWriter);
    }



    public void saveStudentAnswers(PrintWriter printWriter)
    {

        studentAnswer.saveStudentAnswers(printWriter);
    }


    public void restoreStudentAnswers(Scanner sc)
    {

        studentAnswer = new MCSAAnswer(sc);
    }

    //==============================================================
    // Get the number of points that this question contributes to the
    // exam score, based on which answers are currently selected.
    public double getValue()
    {

        return super.getValue(studentAnswer) * maxValue;
    }
}
