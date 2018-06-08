import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.Scanner;
//
// Hamza Shahid
// hshahi2
// Term Project Part 5
//
public class ExamGraderGUI extends JFrame implements ActionListener
{
    private static final int WINDOW_WIDTH = 800;
    private static final int WINDOW_HEIGHT = 450;
    private static final int BUTTON_TOP = 30;
    private static final int BUTTON_LEFT = 30;
    private static final int BUTTON_HEIGHT_INC = 50;
    private static final int LABEL_LEFT = 80;

    // creating shuffled,student answer and results files
    private static File shuffledFile;
    private static File answerFile;
    private static File resultFile;

    private JTextArea examTextArea;
    private JScrollPane scrollPane;

    // load the shuffled exam file
    private Exam shuffledExam;

    private JPanel panel;

    // button options
    private JButton QUIT_BUTTON;
    private JButton PRINT_BUTTON;
    private JButton SAVE_BUTTON;
    private JButton SAVE_BUTTON_EXCEL;
    private JButton SHUFFLED_BUTTON;
    private JButton ANSWERS_BUTTON;

    public ExamGraderGUI(Exam shuffledExam)
    {
        super("EXAM GRADER!");
        this.shuffledExam = shuffledExam;
        initializeDisplay();
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        try
        {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        display();
    }


    //===============================================================
    public static void main(String[] args) throws FileNotFoundException
    {

        int reply = JOptionPane.showConfirmDialog(null, "Would you like to load an exam?", "ExamGrader", JOptionPane.YES_NO_OPTION);

        if (reply == JOptionPane.YES_OPTION)
        {
            FileDialog fd = new FileDialog((Frame) null, "Select File to Open", FileDialog.LOAD);
            fd.setFile("*.txt");
            fd.setVisible(true);


            String fileName = fd.getDirectory() + fd.getFile();
            System.out.println(fileName);

            try
            {
                shuffledFile = new File(fileName);
                Scanner s = new Scanner(shuffledFile);
                new ExamGraderGUI(new Exam(s));
            }
            catch (FileNotFoundException e)
            {
                //Shouldn't get to here
            }
        }
        else
        {
            System.exit(0);
        }
    }


    //===============================================================
    public void display()
    {
        java.awt.EventQueue.invokeLater(new Runnable()
        {
            @Override
            public void run()
            {
                setVisible(true);
            }
        });
    }


    //===============================================================
    @Override
    public void actionPerformed(ActionEvent e)
    {
        if (e.getSource().equals(QUIT_BUTTON))
            quit();
        else if (e.getSource().equals(PRINT_BUTTON))
            print();
        else if (e.getSource().equals(SHUFFLED_BUTTON))
            shuffledExam = loadShuffled();
        else if (e.getSource().equals(ANSWERS_BUTTON))
            shuffledExam = loadAnswers();
        else if (e.getSource().equals(SAVE_BUTTON))
            shuffledExam = saveToCSV();
        else if(e.getSource().equals(SAVE_BUTTON_EXCEL))
            shuffledExam = saveToExcel();
    }


    //===============================================================
    private Exam loadShuffled()
    {
        FileDialog fd = new FileDialog((Frame) null, "Select File to Open", FileDialog.LOAD);
        fd.setFile("*.txt");
        fd.setVisible(true);


        String fileName = fd.getDirectory() + fd.getFile();
        System.out.println(fileName);
        shuffledFile = new File(fileName);

        try
        {
            Scanner s = new Scanner(shuffledFile);
            return new Exam(s);
        }
        catch (FileNotFoundException e)
        {
            //Shouldn't get to here
        }

        return shuffledExam;
    }


    //===============================================================
    private Exam loadAnswers()
    {
        FileDialog fd = new FileDialog((Frame) null, "Select File to Open", FileDialog.LOAD);
        fd.setFile("*.txt");
        fd.setVisible(true);

        String fileName = fd.getDirectory() + fd.getFile();
        System.out.println(fileName);
        answerFile = new File(fileName);
        try
        {
            Scanner s = new Scanner(answerFile);
            shuffledExam.restoreStudentAnswers(s);
        }
        catch (FileNotFoundException e1)
        {
            e1.printStackTrace();
        }
        System.out.println("File name " + answerFile.toString());
        return shuffledExam;
    }


    //===============================================================
    private void quit()
    {

        System.exit(0);
    }


    //===============================================================
    private void print()
    {
        examTextArea.setText("");
        examTextArea.append(shuffledExam.reportQuestionValuesGUI());
        examTextArea.append("\n\n");
        examTextArea.append(shuffledExam.toString());
    }


    //===============================================================
    private Exam saveToCSV()
    {
        FileDialog fd = new FileDialog((Frame) null, "Save", FileDialog.SAVE);
        fd.setFile("*.csv");
        fd.setVisible(true);

        String fileName = fd.getDirectory() + fd.getFile();
        System.out.println(fileName);

        try
        {
            resultFile = new File(fileName);
            PrintWriter p = new PrintWriter(resultFile);

            shuffledExam.saveToCSV(p);
            p.close();
        }
        catch (FileNotFoundException e)
        {
            //Shouldn't get to here unless the call is cancelled. Even then, we do nothing.
        }
        catch( NullPointerException e){
            //nothing here
        }
        return shuffledExam;
    }


    //===============================================================
    private Exam saveToExcel()
    {
        FileDialog fd = new FileDialog((Frame) null, "Save", FileDialog.SAVE);
        fd.setFile("*.xls");
        fd.setVisible(true);

        String fileName = fd.getDirectory() + fd.getFile();
        System.out.println(fileName);

        try
        {
            resultFile = new File(fileName);
            PrintWriter p = new PrintWriter(resultFile);

            shuffledExam.saveToXLS(p);
            p.close();
        }
        catch (FileNotFoundException e)
        {
            //Shouldn't get to here unless the call is cancelled. Even then, we do nothing.
        }
        catch( NullPointerException e){
            //nothing here
        }
        return shuffledExam;
    }


    //===============================================================
    public void initializeDisplay()
    {
        panel = new JPanel()
        {
            public void paintComponent(Graphics g)
            {
                super.paintComponent(g);
            }
        };

        panel.setLayout(new BorderLayout());
        setTitle("Exam Grader");
        setSize(new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT));
        panel.setLayout(null);
        panel.setOpaque(true);
        panel.setPreferredSize(new Dimension(WINDOW_WIDTH - 20, WINDOW_HEIGHT - 20));
        panel.setBackground(Color.orange);

        //Init Buttons
        QUIT_BUTTON = new JButton();
        QUIT_BUTTON.setText("Quit");
        panel.add(QUIT_BUTTON);
        QUIT_BUTTON.setBounds(BUTTON_LEFT, BUTTON_TOP, 100, 30);
        QUIT_BUTTON.addActionListener(this);

        SHUFFLED_BUTTON = new JButton();
        SHUFFLED_BUTTON.setText("Load Shuffled Exam File");
        panel.add(SHUFFLED_BUTTON);
        SHUFFLED_BUTTON.setBounds(BUTTON_LEFT, BUTTON_TOP + BUTTON_HEIGHT_INC, 100, 30);
        SHUFFLED_BUTTON.addActionListener(this);

        ANSWERS_BUTTON = new JButton();
        ANSWERS_BUTTON.setText("Load Answers File");
        panel.add(ANSWERS_BUTTON);
        ANSWERS_BUTTON.setBounds(BUTTON_LEFT, BUTTON_TOP + 2 * BUTTON_HEIGHT_INC, 100, 30);
        ANSWERS_BUTTON.addActionListener(this);

        PRINT_BUTTON = new JButton();
        PRINT_BUTTON.setText("Print");
        panel.add(PRINT_BUTTON);
        PRINT_BUTTON.setBounds(BUTTON_LEFT, BUTTON_TOP + 3 * BUTTON_HEIGHT_INC, 100, 30);
        PRINT_BUTTON.addActionListener(this);

        SAVE_BUTTON = new JButton();
        SAVE_BUTTON.setText("Save CSV");
        panel.add(SAVE_BUTTON);
        SAVE_BUTTON.setBounds(BUTTON_LEFT, BUTTON_TOP + 4 * BUTTON_HEIGHT_INC, 100, 30);
        SAVE_BUTTON.addActionListener(this);

        SAVE_BUTTON_EXCEL = new JButton();
        SAVE_BUTTON_EXCEL.setText("Save XLS");
        panel.add(SAVE_BUTTON_EXCEL);
        SAVE_BUTTON_EXCEL.setBounds(BUTTON_LEFT, BUTTON_TOP + 5 * BUTTON_HEIGHT_INC, 100, 30);
        SAVE_BUTTON_EXCEL.addActionListener(this);

        examTextArea = new JTextArea(50, 100);
        scrollPane = new JScrollPane(examTextArea);
        examTextArea.setEditable(false);
        examTextArea.setLineWrap(true);
        examTextArea.setWrapStyleWord(true);
        examTextArea.setBackground(Color.lightGray);
        panel.add(scrollPane);
        scrollPane.setBounds(2 * LABEL_LEFT, BUTTON_TOP,600, 350);

        //Final stuff
        setResizable(false);
        getContentPane().add(panel);
        getRootPane().setDefaultButton(QUIT_BUTTON);
        panel.setVisible(true);
    }
}