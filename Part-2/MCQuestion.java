import java.util.ArrayList;
import java.util.Collections;
//
// Hamza Shahid
// hshahid
// CS 342 - Term Project Part 2
//
abstract public class MCQuestion extends Question
{
    protected ArrayList<Answer> answersList;

    public MCQuestion(String text, double maxValue)
    {
        super(text,maxValue);
        this.answersList = new ArrayList<Answer>();
    }


    //==============================================================
    // Prints the multiple choice questions along with the answer
    // options (a,b,c,d,e)
    public void print()
    {
        System.out.println(text);
        for (int i = 0; i < this.answersList.size(); i++)
        {
            if (i == 0)
            {
                System.out.print("   a. ");
                this.answersList.get(i).print();
            }
            else if (i == 1)
            {
                System.out.print("   b. ");
                this.answersList.get(i).print();
            }
            else if (i == 2)
            {
                System.out.print("   c. ");
                this.answersList.get(i).print();
            }
            else if (i == 3)
            {
                System.out.print("   d. ");
                this.answersList.get(i).print();
            }
            else if (i == 4)
            {
                System.out.print("   e. ");
                this.answersList.get(i).print();
            }
        }
        System.out.println();
    }

    //==============================================================
    // Add an Answer to a Question. By default new answers are
    // added to the end of the existing list.
    public void addAnswer(MCAnswer mcAnswer)
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
}
