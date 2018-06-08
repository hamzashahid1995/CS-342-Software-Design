import java.io.*;
import java.util.*;

public class ExamBuilder
{

    private static Exam e = null;

    public static void quit(){
        System.exit(0);
    }

    public static void load(){
        Scanner input = ScannerFactory.getKeyboardScanner();
        System.out.println("Enter the Exam file followed by extension");
        String filename = input.nextLine();

        File f = new File(filename);

        try {
            Scanner ExamInput = new Scanner(f);
            e = new Exam(ExamInput);
        }catch (FileNotFoundException e){
            System.out.println("File not found. Enter the file name again again");
            load();
        }catch (NoSuchElementException e){
            System.out.println("File cannot be empty. Try again");
            load();
        }
    }

    private static void addSAQuestion(){
        Scanner input = ScannerFactory.getKeyboardScanner();
        System.out.println("What is the question? ");
        String title = input.nextLine();
        System.out.println("How many points is this worth? ");
        double maxValue = Double.parseDouble(input.nextLine());
        System.out.println("What is the correct answer? ");
        String answer = input.nextLine();

        Question toAdd = new SAQuestion(title, maxValue);
        Answer correct = new SAAnswer(answer);
        toAdd.setRightAnswer(correct);

        e.addQuestion(toAdd);
    }

    private static void addMCSAQuestion(){
        Scanner input = ScannerFactory.getKeyboardScanner();
        System.out.println("What is the question? ");
        String title = input.nextLine();
        System.out.println("How many points is this worth? ");
        double maxValue = Double.parseDouble(input.nextLine());
        System.out.println("How many Options are there? ");
        int options = Integer.parseInt(input.nextLine());

        Question q = new MCSAQuestion(title, maxValue);

        for(int i = 0; i < options; i++){
            System.out.println("Enter the choice");
            String ansTitle = input.nextLine();
            System.out.println("Enter the value of the question");
            double credit = Double.parseDouble(input.nextLine());

            Answer a = new MCSAAnswer(ansTitle, credit);
            ((MCSAQuestion) q).addAnswer(a);
        }

        e.addQuestion(q);
    }

    private static void addMCMAQuestion(){
        Scanner input = ScannerFactory.getKeyboardScanner();
        System.out.println("What is the question? ");
        String title = input.nextLine();
        System.out.println("How many points is this worth? ");
        double maxValue = Double.parseDouble(input.nextLine());
        System.out.println("What is the question's base credit");
        double baseCredit = Double.parseDouble(input.nextLine());
        System.out.println("How many Options are there? ");
        int options = Integer.parseInt(input.nextLine());

        Question q = new MCMAQuestion(title, maxValue, baseCredit);

        for(int i = 0; i < options; i++){
            System.out.println("Enter the choice");
            String ansTitle = input.nextLine();
            System.out.println("Enter the value of the question");
            double credit = Double.parseDouble(input.nextLine());

            Answer a = new MCMAAnswer(ansTitle, credit);
            ((MCMAQuestion) q).addAnswer(a);
        }

        e.addQuestion(q);
    }

    private static void addNumQuestion(){
        Scanner input = ScannerFactory.getKeyboardScanner();
        System.out.println("What is the question? ");
        String title = input.nextLine();
        System.out.println("How many points is this worth? ");
        double maxValue = Double.parseDouble(input.nextLine());
        System.out.println("What is the correct answer? ");
        double answer = Double.parseDouble(input.nextLine());
        System.out.println("What is the tolerance? ");
        double tolerance = Double.parseDouble(input.nextLine());

        Question toAdd = new NumQuestion(title, maxValue, tolerance);
        Answer correct = new NumAnswer(answer);
        toAdd.setRightAnswer(correct);
        e.addQuestion(toAdd);
    }

    public static void add(){
        Scanner input = ScannerFactory.getKeyboardScanner();

        if(e == null) {
            System.out.println("Please enter the name your exam");
            String name = input.nextLine();
            e = new Exam(name);
        }

        System.out.println("What kind of question do you want to add. Select from");
        System.out.println("SAQuestion, MCSAQuestion, MCMAQuestion, NumQuestion");
        String curr = input.nextLine();
        curr = curr.toLowerCase();

        switch (curr){
            case "saquestion": addSAQuestion();
                break;
            case "mcsaquestion": addMCSAQuestion();
                break;
            case "mcmaquestion": addMCMAQuestion();
                break;
            case "numquestion": addNumQuestion();
                break;

            default:
                System.out.println("Invalid response try again");
                add();
                break;
        }

    }

    public static void print(){
        e.print();
    }

    public static void save(){
        Scanner input = ScannerFactory.getKeyboardScanner();
        System.out.println("Enter the output file");
        String filename = input.nextLine();

        File ExamOutput = new File(filename);

        try{
            PrintWriter p = new PrintWriter(ExamOutput);
            e.save(p);
            p.close();
        }catch (FileNotFoundException e){

        }
    }

    public static void remove(){
        Scanner input = ScannerFactory.getKeyboardScanner();
        print();

        System.out.println("Which Question would you like to remove?");
        String temp = input.nextLine();
        int index = Integer.parseInt(temp) - 1;
        e.remove(index);

    }

    public static void reorder(){
        Scanner input = ScannerFactory.getKeyboardScanner();

        System.out.println("Select what you would like to reorder");
        System.out.println("1. MC Answers");
        System.out.println("2. Question Order");
        System.out.println("3. Both");

        String temp = input.nextLine();
        int tempNum = Integer.parseInt(temp);

        switch (tempNum){
            case 1:
                e.reorderMCAnswers(-1);
            case 2:
                e.reorderQuestions();
            case 3:
                e.reorderQuestions();
                e.reorderMCAnswers(-1);

        }

    }

    public static void main(String[] args){
        System.out.println("Taj Atassi\nTatass2");
        Scanner input = ScannerFactory.getKeyboardScanner();

        StringBuilder menu = new StringBuilder();
        menu.append("Select a menu item below\n");
        menu.append("quit \t\t Quit Program\n");
        menu.append("load \t\t Load a saved exam from a file\n");
        menu.append("add \t\t add a question\n");
        menu.append("remove \t\t Remove a question\n");
        menu.append("reorder \t Reorder questions\n");
        menu.append("print \t\t Print the exam to the screen\n");
        menu.append("save \t\t save the exam to a file\n");


        //4.0 for HW4!
        System.out.println("Welcome to ExamBuilder 4.0");


        while(true) {
            System.out.println(menu.toString());
            String curr = input.nextLine();
            curr = curr.toLowerCase();


            switch(curr){
                case "quit": quit();
                    break;
                case "load": load();
                    break;
                case "add": add();
                    break;
                case "remove": remove();
                    break;
                case "reorder": reorder();
                    break;
                case "print": print();
                    break;
                case "save": save();
                    break;
                default: System.out.println("Invalid response. Try again");
            }
        }


    }
}
