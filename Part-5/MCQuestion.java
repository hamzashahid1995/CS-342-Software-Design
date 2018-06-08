import java.util.*;
//
// Hamza Shahid
// hshahid
// CS 342 - Term Project Part 5
//
abstract public class MCQuestion extends Question
{
    protected ArrayList<Answer> answersList;


    protected MCQuestion()
    {
    }

    protected MCQuestion(String text, double maxValue)
    {
        super(text,maxValue);
        this.answersList = new ArrayList<Answer>();
    }

    protected MCQuestion(Scanner sc)
    {
    }


    //==============================================================
    protected String toAlphabet(int pos)
    {
        if (pos < 0)
            return toAlphabet(-pos - 1);

        int quotient = pos / 26;
        int remainder = pos % 26;
        char letter = (char) ((int) 'A' + remainder);
        if (quotient == 0)
            return "" + letter;
        else
            return toAlphabet(quotient - 1) + letter;
    }


    //==============================================================
    @Override
    public void print()
    {
        super.print();
        int i = 0;
        for (Answer a : answersList)
        {
            System.out.print("\t" + toAlphabet(i++) + ". ");
            a.print();
        }
    }

    //==============================================================
    // Add an Answer to a Question. By default new answers are
    // added to the end of the existing list.
    public void addAnswer(Answer mcAnswer)
    {

        this.answersList.add(mcAnswer);
    }


    //==============================================================
    public void reorderAnswers()
    {
        for (int i = 0; i < this.answersList.size(); i++)
        {
            this.answersList.get(i);
            int count = this.answersList.size();
            while (count > 0)
            {
                Collections.shuffle(this.answersList);
                count--;

            }
        }
    }


    //==============================================================
    public String toString()
    {
        StringBuilder sb = new StringBuilder(super.toString());
        int i = 0;

        sb.append("\n");

        for (Answer a : answersList) {
            sb.append("\t" + toAlphabet(i++) + ". ");
            sb.append(a.toString());
            sb.append("\n");
        }

        return sb.toString();
    }


    //==============================================================
    public double getValue(Answer studentAnswer)
    {
        double accum = 0;

        for(Answer a: answersList){
            accum += studentAnswer.getCredit(a);
        }

        return accum;
    }
}