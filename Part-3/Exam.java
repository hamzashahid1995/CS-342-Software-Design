import java.io.*;
import java.util.*;
//
// Hamza Shahid
// hshahid
// CS 342 - Term Project Part 3
//
public class Exam
{
    private String exam;
    private String studentName;
    private ArrayList <Question> questionList;


    public Exam(String exam)
    {
        this.exam = exam;
        this.questionList = new ArrayList<Question>();
    }

    public Exam(Scanner s)
    {
        questionList = new ArrayList<>();
        exam = s.nextLine();
        String curr;
        while(s.hasNextLine())
        {
            curr = s.nextLine();
            if(curr.equalsIgnoreCase("SAQuestion"))
                addQuestion(new SAQuestion(s));
            if(curr.equalsIgnoreCase("MCSAQuestion"))
                addQuestion(new MCSAQuestion(s));
            if(curr.equalsIgnoreCase("MCMAQuestion"))
                addQuestion(new MCMAQuestion(s));
        }
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
        int i = 0;
        System.out.println(exam);

        for(Question q: questionList)
        {
            System.out.print(++i + ". ");
            q.print();
            System.out.println();
        }
    }


    //==============================================================
    // Randomly reorders the Questions within the Exam.
    public void reorderQuestions()
    {

        Collections.shuffle(questionList);
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
    public void getName()
    {
        Scanner s = ScannerFactory.getKeyboardScanner();
        System.out.println("Enter Your name");
        studentName = s.nextLine();
    }


    //==============================================================
    // If the position parameter is negative, then all questions
    // get their answers. Otherwise the position indicates which
    // Question should have its answer from student
    public void getAnswerFromStudent(int position)
    {
        if(position < 0){
            int i = 0;
            for (Question q: questionList) {
                System.out.print(++i + ". ");
                q.print();
                q.getAnswerFromStudent();
            }
        }else{
            if(position < questionList.size()){
                questionList.get(position).getAnswerFromStudent();
            }
        }
    }

    //==============================================================
    // This method produces a table of the values of each Question
    // on the Exam, and the total.
    public void reportQuestionValues()
    {
        int i = 0;
        System.out.println("Question\t|\tScore");
        System.out.println("----------------+--------------");
        for (Question q : questionList) {
            System.out.println("\t" + ++i + "\t|\t" + q.getValue());
        }
        System.out.println("----------------+--------------");
        System.out.println("\tTotal\t|\t" + getValue());
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


    //==============================================================
    public void save(PrintWriter printWriter)
    {
        printWriter.println(exam);
        printWriter.println();

        for(Question q: questionList)
        {
            if(q instanceof SAQuestion)
            {
                printWriter.println("SAQuestion");
                q.save(printWriter);
                printWriter.println();
                printWriter.println();
            }
            if(q instanceof  MCSAQuestion)
            {
                printWriter.println("MCSAQuestion");
                q.save(printWriter);
                printWriter.println();
            }
            if(q instanceof MCMAQuestion)
            {
                printWriter.println("MCMAQuestion");
                q.save(printWriter);
                printWriter.println();
            }
        }
    }


    //==============================================================
    public void saveStudentAnswers(PrintWriter printWriter)
    {
        printWriter.println(studentName);
        printWriter.println();

        for(Question q : questionList)
        {
            if(q instanceof SAQuestion)
            {
                printWriter.println("SAQuestion");
                q.saveStudentAnswers(printWriter);
                printWriter.println();
                printWriter.println();
            }
            if(q instanceof MCSAQuestion)
            {
                printWriter.println("MCSAQuestion");
                q.saveStudentAnswers(printWriter);
                printWriter.println();

            }
            if(q instanceof MCMAQuestion)
            {
                printWriter.println("MCMAQuestion");
                q.saveStudentAnswers(printWriter);
                printWriter.println();
            }
        }
    }


    //==============================================================
    public void restoreStudentAnswers(Scanner sc)
    {
        studentName = sc.nextLine();
        String line;
        int i = 0;
        while(sc.hasNextLine()) {
            line = sc.nextLine();
            if(line.equalsIgnoreCase("SAQuestion")) {
                ((SAQuestion) questionList.get(i++)).restoreStudentAnswers(sc);
            }
            if(line.equalsIgnoreCase("MCSAQuestion")) {
                ((MCSAQuestion) questionList.get(i++)).restoreStudentAnswers(sc);
            }
            if(line.equalsIgnoreCase("MCMAQuestion")) {
                ((MCMAQuestion) questionList.get(i++)).restoreStudentAnswers(sc);
            }
        }
    }
}
