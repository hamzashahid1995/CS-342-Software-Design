import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;

public class ExamTakerGUI extends JFrame implements ActionListener {


    private static final int DEFAULT_WIDTH = 800;
    private static final int DEFAULT_HEIGHT = 450;
    private static final int BUTTON_TOP = 30;
    private static final int BUTTON_LEFT = 30;
    private static final int BUTTON_HEIGHT_INC = 50;
    private static final int BUTTON_WIDTH_INC = 120;
    private static final int LABEL_TOP = 160;
    private static final int LABEL_LEFT = 100;
    private static final int LABEL_HEIGHT_INC = 35;
    private static String USER_NAME;
    //The Exam
    private Exam theExam;
    //GUI Elements for the Main window
    private JPanel panel;
    private JButton quitButton;
    private JButton loadButton;
    private JButton startButton;
    private JButton answerButton;
    private JButton skipButton;
    private JButton changeButton;
    private JButton saveButton;
    private JTextArea examTextArea;
    private JTextArea removeIndexTextArea;
    private JScrollPane scrollPane;


    //Just in case stuff
    private JLabel examName;
    private int counter = 0;

    public ExamTakerGUI(Exam theExam) {
        super("Exam Taker 5.0");
        this.theExam = theExam;
        initDisplay();
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        display();

    }

    public static void main(String[] args) throws FileNotFoundException {

        int reply = JOptionPane.showConfirmDialog(null, "Would you like to load an exam to continue?", "ExamTaker", JOptionPane.YES_NO_OPTION);
        Exam theExam;

        if (reply == JOptionPane.YES_OPTION) {
            FileDialog fd = new FileDialog((Frame) null, "Select File to Open", FileDialog.LOAD);
            fd.setFile("*.txt");
            fd.setVisible(true);


            String fileName = fd.getDirectory() + fd.getFile();
            System.out.println(fileName);

            String name = JOptionPane.showInputDialog("Enter your name");
            USER_NAME = name;

            try {
                File f = new File(fileName);
                Scanner s = new Scanner(f);
                theExam = new Exam(s);
                new ExamTakerGUI(theExam);


            } catch (FileNotFoundException e) {
                //Shouldn't get to here
            }
        } else {
            System.exit(0);
        }

    }


    public void display() {
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                setVisible(true);
            }
        });
    }

    public void initDisplay() {
        panel = new JPanel() {
            public void paintComponent(Graphics g) {
                super.paintComponent(g);
            }
        };

        panel.setLayout(new BorderLayout());
        setTitle("Exam Taker");
        setSize(new Dimension(DEFAULT_WIDTH, DEFAULT_HEIGHT));
        panel.setLayout(null);
        panel.setOpaque(true);
        panel.setPreferredSize(new Dimension(DEFAULT_WIDTH - 20, DEFAULT_HEIGHT - 20));

        //Init Buttons
        quitButton = new JButton();
        quitButton.setText("Quit Exam");
        panel.add(quitButton);
        quitButton.setBounds(BUTTON_LEFT, BUTTON_TOP, 150, 30);
        quitButton.addActionListener(this);

        loadButton = new JButton();
        loadButton.setText("Load Exam");
        panel.add(loadButton);
        loadButton.setBounds(BUTTON_LEFT, BUTTON_TOP + BUTTON_HEIGHT_INC, 150, 30);
        loadButton.addActionListener(this);

        startButton = new JButton();
        startButton.setText("Directions");
        panel.add(startButton);
        startButton.setBounds(BUTTON_LEFT, BUTTON_TOP + 2 * BUTTON_HEIGHT_INC, 150, 30);
        startButton.addActionListener(this);

        skipButton = new JButton();
        skipButton.setText("Skip Question");
        panel.add(skipButton);
        skipButton.setBounds(BUTTON_LEFT, BUTTON_TOP + 3 * BUTTON_HEIGHT_INC, 150, 30);
        skipButton.addActionListener(this);

        removeIndexTextArea = new JTextArea();
        panel.add(removeIndexTextArea);
        removeIndexTextArea.setBounds(BUTTON_LEFT + 70, BUTTON_TOP + 3 * BUTTON_HEIGHT_INC, 30, 30);

        answerButton = new JButton();
        answerButton.setText("Answer Question");
        panel.add(answerButton);
        answerButton.setBounds(BUTTON_LEFT, BUTTON_TOP + 4 * BUTTON_HEIGHT_INC, 150, 30);
        answerButton.addActionListener(this);

        changeButton = new JButton();
        changeButton.setText("Change Answer");
        panel.add(changeButton);
        changeButton.setBounds(BUTTON_LEFT, BUTTON_TOP + 5 * BUTTON_HEIGHT_INC, 150, 30);
        changeButton.addActionListener(this);

        saveButton = new JButton();
        saveButton.setText("Save Answers");
        panel.add(saveButton);
        saveButton.setBounds(BUTTON_LEFT, BUTTON_TOP + 6 * BUTTON_HEIGHT_INC, 150, 30);
        saveButton.addActionListener(this);

        examTextArea = new JTextArea(50, 100);
        scrollPane = new JScrollPane(examTextArea);
        //scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        examTextArea.setEditable(false);
        examTextArea.setLineWrap(true);
        examTextArea.setWrapStyleWord(true);
        panel.add(scrollPane);
        scrollPane.setBounds(2 * LABEL_LEFT, BUTTON_TOP, 550, 350);


        //Final stuff
        setResizable(false);
        getContentPane().add(panel);
        getRootPane().setDefaultButton(quitButton);
        panel.setVisible(true);
        print();

    }


    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(quitButton)) {
            quit();
        } else if (e.getSource().equals(loadButton)) {
            theExam = load();
        } else if (e.getSource().equals(startButton)) {
            start();
        } else if (e.getSource().equals(skipButton)) {
            theExam = skip();
        } else if (e.getSource().equals(changeButton)) {
            theExam = change();
        } else if (e.getSource().equals(answerButton)) {
            theExam = answer();
        } else if (e.getSource().equals(saveButton)) {
            theExam = save();
        }
    }

    private void quit() {
        System.exit(0);
    }

    private void print() {
        examTextArea.setText(theExam.toString());
    }

    private Exam load() {
        FileDialog fd = new FileDialog((Frame) null, "Select File to Open", FileDialog.LOAD);
        fd.setFile("*.txt");
        fd.setVisible(true);


        String fileName = fd.getDirectory() + fd.getFile();
        System.out.println(fileName);

        String name = JOptionPane.showInputDialog("Enter your name");
        USER_NAME = name;

        try {
            File f = new File(fileName);
            Scanner s = new Scanner(f);
            print();
            return new Exam(s);


        } catch (FileNotFoundException e) {
            //Shouldn't get to here
        }

        return theExam;
    }

    private Exam save() {
        theExam.setName(USER_NAME);
        FileDialog fd = new FileDialog((Frame) null, "Save Answer File", FileDialog.SAVE);
        fd.setFile("*.txt");
        fd.setVisible(true);

        FileDialog fd2 = new FileDialog((Frame) null, "Save Shuffled Exam", FileDialog.SAVE);
        fd2.setFile("*.txt");
        fd2.setVisible(true);

        String answersFileName = fd.getDirectory() + fd.getFile();
        System.out.println(answersFileName);

        String examFileName = fd2.getDirectory() + fd2.getFile();
        System.out.println(examFileName);

        try {
            File answers = new File(answersFileName);
            PrintWriter p = new PrintWriter(answers);
            File exam = new File(examFileName);
            PrintWriter p2 = new PrintWriter(exam);

            theExam.saveStudentAnswers(p, answers);
            theExam.save(p2);
            p.close();
            p2.close();

        } catch (FileNotFoundException e) {
            //Shouldn't get to here unless the call is cancelled. Even then, we do nothing.
        }
        return theExam;
    }

    private void start() {
        final JPanel panel = new JPanel();

        JOptionPane.showMessageDialog(panel, "1) To quit press Quit Exam.\n"
                + "2) To reload the original exam or load a new one click Load Exam.\n"
                + "3) To get the dirctions press Directions.\n"
                + "4) To skip a question until the end press Skip Question.\n"
                + "5) To  answer a question press Answer Question.\n "
                + "    a) For a Short Answer Question put your answer in the specified box.\n"
                + "     b) For a Multiple Choice Single Answer Question put the letter of you answer in the specified box.\n"
                + "     c) For a Multiple Choice Multiple Answer Question put the letters of you answer with spaces between them in the specified box.\n"
                + "     d) For a Numerical Answer Question put the numerical anwswer in the specified box rounded to the hundreth place.\n"
                + "6) To change an answer you have previously put press the Change Answers.\n"
                + "7) To save your answers press Save Answers.");
    }

    private Exam answer() {
        String s = JOptionPane.showInputDialog("What question would you like to answer?");
        int i = Integer.parseInt(s);

        if (i < theExam.upperBound() && i > 0) {
            String quest = theExam.getQuestion(i - 1);
            String ans = JOptionPane.showInputDialog(quest);
            theExam.answer(i - 1, ans);
            print();
        } else {
            final JPanel panel = new JPanel();

            JOptionPane.showMessageDialog(panel, "Enter a number within the bounds!", "Warning Invalid Input for Answer",
                    JOptionPane.WARNING_MESSAGE);
        }
        return theExam;
    }

    private Exam skip() {
        String s = JOptionPane.showInputDialog("What question would you like to skip?");
        int i = Integer.parseInt(s);

        if (i < theExam.upperBound() && i > 0) {
            theExam.skip(i - 1);
            print();
        } else {
            final JPanel panel = new JPanel();

            JOptionPane.showMessageDialog(panel, "Enter a number within the bounds!", "Warning Invalid Input for Skip",
                    JOptionPane.WARNING_MESSAGE);
        }
        return theExam;
    }


    private Exam change() {
        String s = JOptionPane.showInputDialog("What answer would you like to change?");
        int i = Integer.parseInt(s);

        if (i < theExam.upperBound() && i > 0) {
            String quest = theExam.getQuestion(i - 1);
            String ans = JOptionPane.showInputDialog(quest);
            theExam.changeAnswer(i - 1, ans);
            print();
        } else {
            final JPanel panel = new JPanel();

            JOptionPane.showMessageDialog(panel, "Enter a number within the bounds!", "Warning Invalid Input for Answer",
                    JOptionPane.WARNING_MESSAGE);
        }
        return theExam;
    }
}









