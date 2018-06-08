import java.io.PrintWriter;
import java.util.Scanner;

public class NumAnswer extends Answer
{

    private double ans;

    public NumAnswer()
    {
    }

    public NumAnswer(Scanner s)
    {

        ans = Double.parseDouble(s.nextLine());
    }


    public NumAnswer(double d)
    {

        ans = d;
    }


    //==============================================================
    @Override
    public void print()
    {

        System.out.println(ans);
    }


    //==============================================================
    public String toString()
    {

        StringBuilder sb = new StringBuilder();
        sb.append(ans + "\n");
        return sb.toString();
    }


    //==============================================================
    @SuppressWarnings("Unused")
    public double getCredit(Answer a)
    {

        return 0;
    }


    //==============================================================
    public double getCredit(Answer correct, double tolerance)
    {
        NumAnswer temp = (NumAnswer) correct;
        double posDelta = temp.ans + tolerance;
        double negDelta = temp.ans - tolerance;

        if(this.ans >= negDelta && this.ans <= posDelta)
            return 1.0;
        else
            return 0.0;
    }


    //==============================================================
    @Override
    public void save(PrintWriter p)
    {

        p.println(ans);
    }


    //==============================================================
    @Override
    public void saveStudentAnswers(PrintWriter p)
    {

        save(p);
    }
}
