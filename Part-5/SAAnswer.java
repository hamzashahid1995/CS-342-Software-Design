import java.io.PrintWriter;
import java.lang.String;
import java.util.Scanner;
//
// Hamza Shahid
// hshahid
// CS 342 - Term Project Part 5
//
public class SAAnswer extends Answer
{
    protected String text;


    public SAAnswer()
    {
    }

    public SAAnswer(String text)
    {


        this.text = text;
    }

    public SAAnswer(Scanner sc)
    {

        this.text = sc.nextLine();
    }


    //==============================================================
    public void print()
    {

        System.out.println("\t" + this.text);
    }


    //==============================================================
    public String toString()
    {

        return "\t" + text;
    }


    //==============================================================
    // This method compares the current answer to the correct answer
    // ( passed in ), and returns a 0.0 for wrong answers, 1.0 for
    // answers deserving full credit,
    public double getCredit(Answer rightAnswer)
    {
        if (rightAnswer instanceof SAAnswer)
        {
            if(this.text.equalsIgnoreCase(((SAAnswer)rightAnswer).text))
                return 1.0;
            else
                return 0.0;
        }
        return 0.0;
    }


    //==============================================================
    public void save(PrintWriter printWriter)
    {
        StringBuilder stringBuilder = new StringBuilder(text);
        printWriter.println(stringBuilder.toString());
    }


    public void saveStudentAnswers(PrintWriter printWriter)
    {

        save(printWriter);
    }
}