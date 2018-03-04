//
// Hamza Shahid
// hshahid
// CS 342 - Term Project Part 2
//
public class MCSAAnswer extends MCAnswer
{
    public MCSAAnswer(String text, double creditIfSelected)
    {
        super(text,creditIfSelected);
    }


    //==============================================================
    // This method compares the current answer to the correct answer
    // ( passed in ), and returns a 0.0 for wrong answers, 1.0 for
    // answers deserving full credit,
    public double getCredit(Answer rightAnswer)
    {
        if (text.equalsIgnoreCase(((MCSAAnswer)rightAnswer).text))
            return 1.0;
        else
            return 0.0;
    }
}
