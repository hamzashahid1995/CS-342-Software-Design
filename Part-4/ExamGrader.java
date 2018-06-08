import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;
//
// Hamza Shahid
// hshahid
// CS 342 - Term Project Part 4
//
public class ExamGrader
{
    public static void main(String[] args) throws FileNotFoundException
    {
        System.out.println();

        Scanner input = ScannerFactory.getKeyboardScanner();

        System.out.println("Input exam name: (shuffled.txt)");
        String in = input.nextLine();

        File Exam1 = new File(in);
        PrintWriter pw = new PrintWriter(new File("results.csv"));

        StringBuilder sb = new StringBuilder();
        Scanner inputExam = new Scanner(Exam1);

        Exam e = new Exam(inputExam);

        System.out.println("Enter answer file: (answers.txt)");
        in = input.nextLine();
        File studentAnswers = new File(in);
        Scanner theAnswers = new Scanner(studentAnswers);

        e.restoreStudentAnswers(theAnswers);
        e.reportQuestionValues();


        e.saveToCSV(pw);

        theAnswers.close();

        pw.write(sb.toString());
        pw.close();

    }
}
