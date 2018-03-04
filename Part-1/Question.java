import java.util.ArrayList;
import java.util.Collections;

//
// Hamza Shahid
// hshahid
// CS 342 - Term Project Part 1
//
public class Question
{
    private String question;
    private double value;
    private ArrayList <Answer> answerList;


    //==============================================================
    public Question(String question)
    {
        this.question = question;
        this.value = 0.0;
        this.answerList = new ArrayList<Answer>();
    }


    //==============================================================
    // Add an Answer to a Question. By default new answers are
    // added to the end of the existing list.
    public void addAnswer(Answer answer)
    {
        this.answerList.add(answer);
    }


    //==============================================================
    // Prints the question to the screen, along with all of its answers.
    // The input parameter indicates the position of this question
    // in the list of questions, 1 for the first question, 2 for
    // the second, and so on.
    public void print(int position)
    {
        System.out.println(position + ". " + this.question);
        for (int i = 0; i < this.answerList.size(); i++)
        {
            if (i == 0)
                this.answerList.get(i).print(1);
            else if (i == 1)
                this.answerList.get(i).print(2);
            else if (i == 2)
                this.answerList.get(i).print(3);
            else if (i == 3)
                this.answerList.get(i).print(4);
            else if (i == 4)
                this.answerList.get(i).print(5);
        }
    }


    //==============================================================
    // selects the Answer in the given position. This may or may not
    // require unselecting other previously selected Answers.
    public void selectAnswer(int position)
    {

        this.answerList.get(position-1).setSelected(true);
    }


    //==============================================================
    // unselects the Answer in the given position.
    public void unselectAnswer(int position)
    {

        this.answerList.get(position-1).setSelected(false);
    }


    //==============================================================
    // Randomly reorders the Answers within the Question.
    public void reorderAnswers()
    {
        for (int i = 0; i < this.answerList.size(); i++)
        {
            this.answerList.get(i);
            int count = 5;
            while (count > 0)
            {
                Collections.shuffle(this.answerList);
                count--;
            }
        }
    }


    //==============================================================
    // Get the number of points that this question contributes to the
    // exam score, based on which answers are currently selected.
    public double getValue()
    {
        this.value = 0;
        for (int i = 0; i < this.answerList.size(); i++)
        {
            this.value += this.answerList.get(i).getValue();
        }
        return this.value;
    }
}
