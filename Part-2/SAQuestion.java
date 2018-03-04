import java.util.Scanner;
//
// Hamza Shahid
// hshahid
// CS 342 - Term Project Part 2
//
public class SAQuestion extends Question
{
    public SAQuestion(String text, double maxValue)
    {
        super(text,maxValue);
    }


    //==============================================================
    // This method creates and returns a new Answer object, suitably
    // matched to the Question type of the object through which this
    // method is called.
    public Answer getNewAnswer()
    {
        SAAnswer ans = new SAAnswer(text);
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
        SAAnswer ans = new SAAnswer(text);
        Scanner sc = new Scanner(System.in);
        ans.text = sc.nextLine();
        studentAnswer = new SAAnswer(ans.text);
    }


    //==============================================================
    // Get the number of points that this question contributes to the
    // exam score, based on which answers are currently selected.
    public double getValue()
    {
        return maxValue * studentAnswer.getCredit(rightAnswer);
    }
}
