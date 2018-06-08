import java.io.*;
import java.util.*;
//
// Hamza Shahid
// hshahid
// CS 342 - Term Project Part 4
//
abstract public class MCAnswer extends Answer
{
    protected String  text;
    protected double  creditIfSelected;
    protected boolean selected;


    protected MCAnswer()
    {
    }

    protected MCAnswer(String text, double creditIfSelected)
    {
        this.text = text;
        this.selected = false;
        this.creditIfSelected = creditIfSelected;
    }

    // Scan the answer and value for that
    // answer from the .txt file.
    public MCAnswer(Scanner sc)
    {
        this.creditIfSelected = sc.nextDouble();
        this.text = sc.nextLine();

        if(this.text.startsWith(" "))
            this.text = this.text.substring(1);

    }


    //==============================================================
    // Print the answer.
    public void print()
    {

        System.out.println(this.text);
    }


    //==============================================================
    // This method compares the current answer to the correct answer
    // ( passed in ), and returns a 0.0 for wrong answers, 1.0 for
    // answers deserving full credit.
    public double getCredit(Answer rightAnswer)
    {
        if(this.text.equalsIgnoreCase(((MCAnswer) rightAnswer).text))
            return creditIfSelected;
        else
            return 0;
    }


    //==============================================================
    // Save the answer and its value.
    public void save(PrintWriter printWriter)
    {
        StringBuilder sb = new StringBuilder();

        sb.append(this.creditIfSelected);
        sb.append(" ");
        sb.append(this.text);

        printWriter.println(sb.toString());
    }


    public void saveStudentAnswers(PrintWriter printWriter)
    {

        save(printWriter);
    }
}
