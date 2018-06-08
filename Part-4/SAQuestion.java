import java.io.PrintWriter;
import java.util.Scanner;
//
// Hamza Shahid
// hshahid
// CS 342 - Term Project Part 4
//
public class SAQuestion extends Question
{
    protected SAQuestion(String text, double maxValue)
    {

        super(text,maxValue);
    }

    public SAQuestion(Scanner sc)
    {
        maxValue = Double.parseDouble(sc.nextLine());
        text = sc.nextLine();
        rightAnswer = new SAAnswer(sc);
    }


    //==============================================================
    // This method creates and returns a new Answer object, suitably
    // matched to the Question type of the object through which this
    // method is called.
    public Answer getNewAnswer()
    {
        SAAnswer ans = new SAAnswer(super.text);
        return ans;
    }


    //==============================================================
    // This method creates and returns a new Answer object, suitably
    // matched to the Question type of the object through which this
    // method is called.
    public Answer getNewAnswer(String text)
    {
        SAAnswer ans = new SAAnswer(text);
        return ans;
    }


    //==============================================================
    // This method creates a SAAnswer object object which gets the
    // answer from the student/ keyboard, and then stores the answer
    // in the student Answer field of the class.
    public void getAnswerFromStudent()
    {
        Scanner input = ScannerFactory.getKeyboardScanner();
        studentAnswer = new SAAnswer(input.nextLine());
    }


    //==============================================================
    // Save the question and its value
    public void save(PrintWriter printWriter)
    {
        printWriter.println(maxValue);
        printWriter.println(text);
        rightAnswer.save(printWriter);
    }


    //==============================================================
    // Get the number of points that this question contributes to the
    // exam score, based on which answers are currently selected.
    public double getValue()
    {
        if (studentAnswer == null)
            return 0.0;
        return maxValue * studentAnswer.getCredit(rightAnswer);
    }


    //==============================================================
    public void saveStudentAnswers(PrintWriter printWriter)
    {
        studentAnswer.save(printWriter);
        printWriter.println();
    }


    //==============================================================
    public void restoreStudentAnswers(Scanner sc)
    {

        studentAnswer = new SAAnswer(sc);
    }
}
