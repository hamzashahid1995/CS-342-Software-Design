import java.io.*;
import java.text.*;
import java.util.*;
//
// Hamza Shahid
// hshahid
// CS 342 - Term Project Part 5
//
public class Exam
{
    private static final DateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
    private ArrayList<Question> questionList;
    private String title;
    private String studentName;


    public Exam(String examName)
    {
        title = examName;
        questionList = new ArrayList<>();
    }

    //Creates a full Exam from file (From Scanner)
    public Exam(Scanner s)
    {
        questionList = new ArrayList<>();
        title = s.nextLine();
        String curr;
        while (s.hasNextLine())
        {
            curr = s.nextLine();
            if (curr.equalsIgnoreCase("SAQuestion"))
                addQuestion(new SAQuestion(s));
            if (curr.equalsIgnoreCase("MCSAQuestion"))
                addQuestion(new MCSAQuestion(s));
            if (curr.equalsIgnoreCase("MCMAQuestion"))
                addQuestion(new MCMAQuestion(s));
            if (curr.equalsIgnoreCase("NumQuestion"))
                addQuestion(new NumQuestion(s));
        }
    }


    //==============================================================
    public void addQuestion(Question toAdd)
    {

        questionList.add(toAdd);
    }


    //==============================================================
    public Question remove(int index)
    {

        return questionList.remove(index);
    }


    //==============================================================
    public void answer(int position, String answer)
    {
        for (int i = 0; i < questionList.size(); i++)
        {
            if (position == i)
            {
                if (questionList.get(i) instanceof SAQuestion)
                    ((SAQuestion) questionList.get(i)).setAnswer(answer);
                if (questionList.get(i) instanceof MCSAQuestion)
                    ((MCSAQuestion) questionList.get(i)).setAnswer(answer);
                if (questionList.get(i) instanceof MCMAQuestion)
                    ((MCMAQuestion) questionList.get(i)).setAnswer(answer);
                if (questionList.get(i) instanceof NumQuestion)
                    ((NumQuestion) questionList.get(i)).setAnswer(answer);
            }
        }
    }


    //==============================================================
    public void changeAnswer(int position, String answer)
    {
        for (int i = 0; i < questionList.size(); i++)
        {
            if (position == i)
            {
                if (questionList.get(i) instanceof SAQuestion)
                    ((SAQuestion) questionList.get(i)).setAnswer(answer);
                if (questionList.get(i) instanceof MCSAQuestion)
                    ((MCSAQuestion) questionList.get(i)).setAnswer(answer);
                if (questionList.get(i) instanceof MCMAQuestion)
                    ((MCMAQuestion) questionList.get(i)).changeAnswer(answer);
                if (questionList.get(i) instanceof NumQuestion)
                    ((NumQuestion) questionList.get(i)).setAnswer(answer);
            }
        }
    }


    //==============================================================
    public String getQuestion(int position)
    {
        String q = "";
        for (int i = 0; i < questionList.size(); i++)
        {
            if (position == i)
                q = questionList.get(i).text;
        }
        return q;
    }


    //==============================================================
    public void setName(String name)
    {

        studentName = name;
    }


    //==============================================================
    public int upperBound()
    {
        int count = 1;
        for (Question q : questionList)
            count = count + 1;
        return count;
    }


    //==============================================================
    public void skip(int position)
    {
        ArrayList<Question> norm = new ArrayList<>();
        ArrayList<Question> skip = new ArrayList<>();

        for (Question q : questionList)
            norm.add(q);

        for (int i = 0; i < questionList.size(); i++)
        {
            if (position == i)
                skip.add(questionList.get(i));
        }

        norm.removeAll(skip);
        questionList.clear();

        for (Question q : norm)
            questionList.add(q);

        for (Question q : skip)
            questionList.add(q);
    }


    //==============================================================
    public void print()
    {
        int i = 0;
        System.out.println(title);
        for (Question q : questionList)
        {
            System.out.print(++i + ". ");
            q.print();
            System.out.println();
        }
    }


    //==============================================================
    public String toString()
    {
        int i = 0;
        StringBuilder sb = new StringBuilder();
        sb.append(title + "\n\n");

        for (Question q : questionList)
        {
            sb.append(++i + ".");
            sb.append(q.toString());
            sb.append("\n");
        }

        return sb.toString();
    }


    //==============================================================
    public void getName()
    {
        Scanner s = ScannerFactory.getKeyboardScanner();
        System.out.println("Enter Your name");
        studentName = s.nextLine();
    }


    //==============================================================
    public String getExamName()
    {

        return title;
    }


    //==============================================================
    public void reorderQuestions()
    {

        Collections.shuffle(questionList);
    }


    //==============================================================
    public void reorderMCAnswers(int position)
    {
        if (position < 0)
        {
            for (Question q : questionList)
            {
                if (q instanceof MCQuestion)
                    ((MCQuestion) q).reorderAnswers();
                else
                    continue;
            }
        }
        else if (position < questionList.size())
        {
            if (questionList.get(position) instanceof MCQuestion)
                ((MCQuestion) questionList.get(position)).reorderAnswers();
        }
        else
        {
            //Some Exception should be thrown
        }
    }


    //==============================================================
    public void getAnswerFromStudent(int position)
    {
        if (position < 0)
        {
            int i = 0;
            ArrayList<Question> tempL = new ArrayList<>();
            ArrayList<Question> skips = new ArrayList<>();

            for (Question q : questionList)
            {
                tempL.add(q);
                System.out.print(++i + ". ");
                q.print();
                Scanner input = ScannerFactory.getKeyboardScanner();
                String temp = input.nextLine();
                if (temp.equalsIgnoreCase("skip"))
                    skips.add(q);
            }
            tempL.removeAll(skips);
            i = 0;
            questionList.clear();
            for (Question q : tempL)
            {
                System.out.print(++i + ". ");
                q.print();
                q.getAnswerFromStudent();
                questionList.add(q);
            }

            for (Question q : skips)
            {
                System.out.print(++i + ". ");
                q.print();
                q.getAnswerFromStudent();
                questionList.add(q);
            }


        }
        else
        {
            if (position < questionList.size())
                questionList.get(position).getAnswerFromStudent();
        }
    }


    //==============================================================
    public double getValue()
    {
        double sum = 0;
        for (Question q : questionList)
            sum += q.getValue();
        return sum;
    }


    //==============================================================
    public void reportQuestionValues()
    {
        int i = 0;
        System.out.println("Question\t|\tScore");
        System.out.println("------------+----------");
        for (Question q : questionList)
            System.out.println("\t\t" + ++i + "\t|\t" + q.getValue());
        System.out.println("------------+----------");
        System.out.println("\tTotal\t|\t" + getValue());
    }


    //==============================================================
    public String reportQuestionValuesGUI()
    {
        StringBuilder sb = new StringBuilder();
        int i = 0;
        sb.append("--------------------\n");
        sb.append(" Question   |     Score\n");
        sb.append("---------+----------\n");

        for (Question q : questionList)
            sb.append("        " + ++i + "        |        " + q.getValue() + "\n");
        sb.append("---------+----------\n");
        sb.append("     Total     |     " + getValue() + "\n");
        sb.append("--------------------\n");

        return sb.toString();
    }


    //==============================================================
    public void save(PrintWriter p)
    {
        p.println(title);
        Date d = new Date();
        p.println(sdf.format(d));
        p.println();
        for (Question q : questionList)
        {
            if (q instanceof SAQuestion)
            {
                p.println("SAQuestion");
                q.save(p);
                p.println();
                p.println();
            }
            if (q instanceof MCSAQuestion)
            {
                p.println("MCSAQuestion");
                q.save(p);
                p.println();
            }
            if (q instanceof MCMAQuestion)
            {
                p.println("MCMAQuestion");
                q.save(p);
                p.println();
            }
            if (q instanceof NumQuestion)
            {
                p.println("NumQuestion");
                q.save(p);
                p.println();
            }
        }
    }


    //==============================================================
    public void saveStudentAnswers(PrintWriter p, File examName) throws FileNotFoundException
    {
        p.println(studentName);
        p.println(examName.getName());
        Date d = new Date();
        p.println(sdf.format(d));
        p.println();

        for (Question q : questionList)
        {
            if (q instanceof SAQuestion)
            {
                p.println("SAAnswer");
                q.saveStudentAnswers(p);
                p.println();
                p.println();
            }
            if (q instanceof MCSAQuestion)
            {
                p.println("MCSAAnswer");
                q.saveStudentAnswers(p);
                p.println();

            }
            if (q instanceof MCMAQuestion)
            {
                p.println("MCMAAnswer");
                q.saveStudentAnswers(p);
                p.println();
            }
            if (q instanceof NumQuestion)
            {
                p.println("NumAnswer");
                q.saveStudentAnswers(p);
                p.println();
            }
        }
    }


    //==============================================================
    public void restoreStudentAnswers(Scanner s)
    {
        studentName = s.nextLine();
        String line;
        int i = 0;
        while (s.hasNextLine())
        {
            line = s.nextLine();
            if (line.equals("SAAnswer"))
                ((SAQuestion) questionList.get(i++)).restoreStudentAnswers(s);
            if (line.equals("MCSAAnswer"))
                ((MCSAQuestion) questionList.get(i++)).restoreStudentAnswers(s);
            if (line.equals("MCMAAnswer"))
                ((MCMAQuestion) questionList.get(i++)).restoreStudentAnswers(s);
            if (line.equals("NumAnswer"))
                ((NumQuestion) questionList.get(i++)).restoreStudentAnswers(s);
        }
    }


    //==============================================================
    public void saveToCSV(PrintWriter pw)
    {
        double sum;
        StringBuilder sb = new StringBuilder();
        sb.append("Name,");

        for (int i = 0; i < questionList.size(); i++)
        {
            sb.append("Question " + (i + 1));
            sb.append(", ");
        }
        sb.append("Total Score");
        sb.append("\n");
        sb.append(studentName);
        sb.append(",");
        for (int i = 0; i < questionList.size(); i++)
        {
            sum = questionList.get(i).getValue();
            sb.append(sum);
            sb.append(",");
        }
        sb.append(getValue());
        pw.write(sb.toString());
    }


    //==============================================================
    public void saveToXLS(PrintWriter pw)
    {
        double sum;
        StringBuilder sb = new StringBuilder();
        sb.append("Name\t");

        for (int i = 0; i < questionList.size(); i++)
        {
            sb.append("Question " + (i + 1));
            sb.append("\t");
        }
        sb.append("Total Score");
        sb.append("\n");
        sb.append(studentName);
        sb.append("\t");
        for (int i = 0; i < questionList.size(); i++)
        {
            sum = questionList.get(i).getValue();
            sb.append(sum);
            sb.append("\t");
        }
        sb.append(getValue());
        pw.write(sb.toString());
    }
}
