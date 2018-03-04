import java.util.Scanner;
//
// Hamza Shahid
// hshahid
// CS 342 - Term Project Part 2
//
public class MCSAQuestion extends MCQuestion
{
    public MCSAQuestion(String text, double maxValue)
    {
        super(text,maxValue);
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
    public Answer getNewAnswer(String text)
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
        String temp;
        MCSAAnswer ans = new MCSAAnswer(text,maxValue);
        Scanner sc = new Scanner(System.in);
        temp = sc.nextLine();



        if (temp.equalsIgnoreCase("a"))
            ans.text = ((MCSAAnswer) answersList.get(0)).text;
        else if (temp.equalsIgnoreCase("b"))
            ans.text = ((MCSAAnswer) answersList.get(1)).text;
        else if (temp.equalsIgnoreCase("c"))
            ans.text = ((MCSAAnswer) answersList.get(2)).text;
        else if (temp.equalsIgnoreCase("d"))
            ans.text = ((MCSAAnswer) answersList.get(3)).text;
        else if (temp.equalsIgnoreCase("e"))
            ans.text = ((MCSAAnswer) answersList.get(4)).text;
        studentAnswer = new MCSAAnswer(ans.text, maxValue);
    }


    //==============================================================
    // Get the number of points that this question contributes to the
    // exam score, based on which answers are currently selected.
    public double getValue()
    {
        return maxValue * studentAnswer.getCredit(rightAnswer);
    }
}
