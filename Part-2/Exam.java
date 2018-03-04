import java.util.ArrayList;
import java.util.Collections;
//
// Hamza Shahid
// hshahid
// CS 342 - Term Project Part 2
//
public class Exam
{
    private String exam;
    private int numOfQuestions;
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
        System.out.println("*** " + this.exam + " ***\n");
        this.numOfQuestions = 1;
        for (int i = 0; i < this.questionList.size(); i++)
        {
            System.out.print(this.numOfQuestions + ". ");
            this.questionList.get(i).print();
            this.numOfQuestions++;
        }
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
    // For multiple choice questions only, this method reorders the
    // answers of the question. If the position parameter is negative,
    // then all MC questions get their answers reordered. Otherwise
    // the position indicates which Question should have its answers
    // reordered, provided that that Question is a MC type question.
    public void reorderMCAnswers(int position)
    {
        int count = 0;
        for (Question q : questionList)
        {
            count++;
            if (q instanceof MCQuestion)
            {
                if (position < 0)
                    Collections.shuffle(((MCQuestion) q).answersList);
                else if (position == count)
                    Collections.shuffle(((MCQuestion) q).answersList);
            }
        }
    }

    //==============================================================
    // If the position parameter is negative, then all questions
    // get their answers. Otherwise the position indicates which
    // Question should have its answer from student
    public void getAnswerFromStudent(int position)
    {
        int count = 0;
        for (Question q : questionList)
        {
            count++;
            if (position < 0)
                q.getAnswerFromStudent();
            else if (position == count)
            {
                q.getAnswerFromStudent();
                break;
            }
        }
    }

    //==============================================================
    // This method produces a table of the values of each Question
    // on the Exam, and the total.
    public void reportQuestionValues()
    {
        double questionPoints = 0;
        for (int i = 0; i < this.questionList.size(); i++)
        {
            if(questionList.get(i).studentAnswer != null)
            {
                questionPoints += questionList.get(i).getValue();
                System.out.println("Points received for Question " + (i+1) + ": " + this.questionList.get(i).getValue());
            }
        }
        System.out.println("Points Received: " + questionPoints + "/" + questionList.size()*5.0);
    }

    //==============================================================
    // Get the overall value ( score ) of the exam. This method should
    // work regardless of how many ( if any ) of the Questions have
    // been answered.
    public double getValue()
    {
        double sum = 0;
        for (int i = 0; i < this.questionList.size(); i++)
        {
            sum += this.questionList.get(i).getValue();
        }
        return sum;
    }
}
