import java.lang.String;
//
// Hamza Shahid
// hshahid
// CS 342 - Term Project Part 2
//
public class SAAnswer extends Answer
{
    protected String text;

    public SAAnswer(String text)
    {
        this.text = text;
    }


    //==============================================================
    public void print()
    {
        System.out.println(this.text);
    }


    //==============================================================
    // This method compares the current answer to the correct answer
    // ( passed in ), and returns a 0.0 for wrong answers, 1.0 for
    // answers deserving full credit,
    public double getCredit(Answer rightAnswer)
    {
        // checking for case sensitivity
        if (text.equalsIgnoreCase(((SAAnswer)rightAnswer).text))
            return 1.0;
        else
            return 0.0;
    }
}
