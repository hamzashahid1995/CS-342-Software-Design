import java.util.Scanner;
//
// Hamza Shahid
// hshahid
// CS 342 - Term Project Part 3
//
public class MCMAAnswer extends MCAnswer
{
    protected MCMAAnswer()
    {
    }

    protected MCMAAnswer(String text, double creditIfSelected)
    {

        super(text,creditIfSelected);
    }

    protected MCMAAnswer(Scanner s)
    {

        super(s);
    }
}
