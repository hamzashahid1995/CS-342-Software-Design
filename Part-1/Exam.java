import java.util.ArrayList;
import java.util.Collections;

//
// Hamza Shahid
// hshahid
// CS 342 - Term Project Part 1
//
public class Exam
{
    private String exam;
    private int numOfQuestions;
    private double points;
    private ArrayList <Question> questionList;


    //==============================================================
    public Exam(String exam)
    {
        this.exam = exam;
        this.numOfQuestions = 0;
        this.questionList = new ArrayList<Question>();
    }


    //==============================================================
    // Add a Question to an Exam. By default new questions are added
    // to the end of the existing list.
    public void addQuestion(Question question)
    {
        this.questionList.add(question);
    }


    //==============================================================
    // Prints the exam to the screen, along with all of its questions.
    public void print()
    {
        System.out.println("*** " + this.exam + " ***");
        this.numOfQuestions = 1;
        for (int i = 0; i < this.questionList.size(); i++)
        {
            this.questionList.get(i).print(this.numOfQuestions);
            this.numOfQuestions++;
        }
    }


    //==========================a====================================
    // Question – returns ( a reference to ) the question in the given
    // position within the exam.
    public Question getQuestion(int position)
    {

        return questionList.get(position-1);
    }


    //==============================================================
    // Randomly reorders the Questions within the Exam.
    public void reorderQuestions()
    {
        for (int i = 0; i < this.questionList.size(); i++)
        {
            this.questionList.get(i);
            int count = this.questionList.size();
            while (count > 0)
            {
                Collections.shuffle(this.questionList);
                count--;
            }
        }
    }


    //==============================================================
    // Get the overall value ( score ) of the exam. This method should
    // work regardless of how many ( if any ) of the Questions have
    // been answered.
    public double getValue()
    {
        for (int i = 0; i < this.questionList.size(); i++)
        {
            this.points += this.questionList.get(i).getValue();
        }
        return this.points;
    }
}
