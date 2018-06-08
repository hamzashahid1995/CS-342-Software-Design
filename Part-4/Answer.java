import java.io.PrintWriter;
import java.util.Scanner;
//
// Hamza Shahid
// hshahid
// CS 342 - Term Project Part 4
//
abstract public class Answer
{
    protected Answer()
    {
    }

    public Answer(Scanner sc)
    {
    }

    public abstract void print();
    public abstract double getCredit(Answer rightAnswer);
    public abstract void save(PrintWriter printWriter);
    public abstract void saveStudentAnswers(PrintWriter printWriter);
}
