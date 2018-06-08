import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;

public class ExamBuilderGUI extends JFrame implements ActionListener {


    //Constants for setting bounds
    private static final int DEFAULT_WIDTH = 800;
    private static final int DEFAULT_HEIGHT = 450;
    private static final int BUTTON_TOP = 30;
    private static final int BUTTON_LEFT = 30;
    private static final int BUTTON_HEIGHT_INC = 50;
    private static final int BUTTON_WIDTH_INC = 120;
    private static final int LABEL_TOP = 160;
    private static final int LABEL_LEFT = 80;
    private static final int LABEL_HEIGHT_INC = 35;

    //The Exam
    private Exam theExam;

    //GUI Elements for the Main window
    private JPanel panel;
    private JButton quitButton;
    private JButton loadButton;
    private JButton addButton;
    private JButton removeButton;
    private JButton reorderButton;
    private JButton printButton;
    private JButton saveButton;
    private JTextArea examTextArea;
    private JTextArea removeIndexTextArea;
    private JScrollPane scrollPane;


    //Just in case stuff
    private JLabel examName;
    private int counter = 0;


    public ExamBuilderGUI(Exam theExam) {
        super("Exam Builder 5.0");
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
        int reply = JOptionPane.showConfirmDialog(null, "Would you like to load an exam?", "ExamBuilder", JOptionPane.YES_NO_OPTION);
        Exam theExam;

        if (reply == JOptionPane.YES_OPTION) {
            FileDialog fd = new FileDialog((Frame) null, "Select File to Open", FileDialog.LOAD);
            fd.setFile("*.txt");
            fd.setVisible(true);


            String fileName = fd.getDirectory() + fd.getFile();
            System.out.println(fileName);

            try {
                File f = new File(fileName);
                Scanner s = new Scanner(f);
                theExam = new Exam(s);
                new ExamBuilderGUI(theExam);


            } catch (FileNotFoundException e) {
                //Shouldn't get to here
            }
        } else {
            String examTitle = JOptionPane.showInputDialog("Name your exam");
            theExam = new Exam(examTitle);
            new ExamBuilderGUI(theExam);
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

    //Initialize the main UI
    public void initDisplay() {
        panel = new JPanel() {
            public void paintComponent(Graphics g) {
                super.paintComponent(g);
            }
        };

        panel.setLayout(new BorderLayout());
        setTitle("Exam Builder");
        setSize(new Dimension(DEFAULT_WIDTH, DEFAULT_HEIGHT));
        panel.setLayout(null);
        panel.setOpaque(true);
        panel.setPreferredSize(new Dimension(DEFAULT_WIDTH - 20, DEFAULT_HEIGHT - 20));

        //Init Buttons
        quitButton = new JButton();
        quitButton.setText("Quit");
        panel.add(quitButton);
        quitButton.setBounds(BUTTON_LEFT, BUTTON_TOP, 100, 30);
        quitButton.addActionListener(this);

        loadButton = new JButton();
        loadButton.setText("Load");
        panel.add(loadButton);
        loadButton.setBounds(BUTTON_LEFT, BUTTON_TOP + BUTTON_HEIGHT_INC, 100, 30);
        loadButton.addActionListener(this);

        addButton = new JButton();
        addButton.setText("Add");
        panel.add(addButton);
        addButton.setBounds(BUTTON_LEFT, BUTTON_TOP + 2 * BUTTON_HEIGHT_INC, 100, 30);
        addButton.addActionListener(this);

        removeButton = new JButton();
        removeButton.setText("Remove");
        panel.add(removeButton);
        removeButton.setBounds(BUTTON_LEFT, BUTTON_TOP + 3 * BUTTON_HEIGHT_INC, 65, 30);
        removeButton.addActionListener(this);

        removeIndexTextArea = new JTextArea();
        panel.add(removeIndexTextArea);
        removeIndexTextArea.setBounds(BUTTON_LEFT + 70, BUTTON_TOP + 3 * BUTTON_HEIGHT_INC, 30, 30);

        reorderButton = new JButton();
        reorderButton.setText("Reorder");
        panel.add(reorderButton);
        reorderButton.setBounds(BUTTON_LEFT, BUTTON_TOP + 4 * BUTTON_HEIGHT_INC, 100, 30);
        reorderButton.addActionListener(this);

        printButton = new JButton();
        printButton.setText("Print");
        panel.add(printButton);
        printButton.setBounds(BUTTON_LEFT, BUTTON_TOP + 5 * BUTTON_HEIGHT_INC, 100, 30);
        printButton.addActionListener(this);

        saveButton = new JButton();
        saveButton.setText("Save");
        panel.add(saveButton);
        saveButton.setBounds(BUTTON_LEFT, BUTTON_TOP + 6 * BUTTON_HEIGHT_INC, 100, 30);
        saveButton.addActionListener(this);

        examTextArea = new JTextArea(50, 100);
        scrollPane = new JScrollPane(examTextArea);
        //scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        examTextArea.setEditable(false);
        examTextArea.setLineWrap(true);
        examTextArea.setWrapStyleWord(true);
        panel.add(scrollPane);
        scrollPane.setBounds(2 * LABEL_LEFT, BUTTON_TOP, 600, 350);


        //Final stuff
        setResizable(false);
        getContentPane().add(panel);
        getRootPane().setDefaultButton(quitButton);
        panel.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(quitButton)) {
            quit();
        } else if (e.getSource().equals(loadButton)) {
            theExam = load();
        } else if (e.getSource().equals(addButton)) {
            theExam = add();
        } else if (e.getSource().equals(removeButton)) {
            theExam = remove();
        } else if (e.getSource().equals(reorderButton)) {
            theExam = reorder();
        } else if (e.getSource().equals(printButton)) {
            print();
        } else if (e.getSource().equals(saveButton)) {
            theExam = save();
        }
    }

    private void quit() {
        System.exit(0);
    }

    private Exam load() {
        FileDialog fd = new FileDialog((Frame) null, "Select File to Open", FileDialog.LOAD);
        fd.setFile("*.txt");
        fd.setVisible(true);


        String fileName = fd.getDirectory() + fd.getFile();
        System.out.println(fileName);

        try {
            File f = new File(fileName);
            Scanner s = new Scanner(f);
            return new Exam(s);


        } catch (FileNotFoundException e) {
            //Shouldn't get to here
        }

        return theExam;
    }

    private Exam save() {
        FileDialog fd = new FileDialog((Frame) null, "Save", FileDialog.SAVE);
        fd.setFile("*.txt");
        fd.setVisible(true);

        String fileName = fd.getDirectory() + fd.getFile();
        System.out.println(fileName);

        try {
            File f = new File(fileName);
            PrintWriter p = new PrintWriter(f);

            theExam.save(p);
            p.close();

        } catch (FileNotFoundException e) {
            //Shouldn't get to here unless the call is cancelled. Even then, we do nothing.
        }
        return theExam;
    }

    private void addMCSAQuestion() {
        System.out.println("MCSAQuestion");

        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                JFrame frame = new JFrame("Add MCMAQuestion");
                frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                frame.setSize(new Dimension(400, 250));

                try {
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                } catch (Exception e) {
                    e.printStackTrace();
                }

                JPanel panel = new JPanel();
                panel.setLayout(new BorderLayout());
                panel.setOpaque(true);
                panel.setPreferredSize(new Dimension(380, 230));
                panel.setLayout(null);

                JLabel questionNameLabel = new JLabel("Title");
                questionNameLabel.setFont(new Font("Sanserif", Font.BOLD, 12));
                panel.add(questionNameLabel);
                questionNameLabel.setBounds(20, 30, 100, 30);
                JTextField questionName = new JTextField(50);
                panel.add(questionName);
                questionName.setBounds(60, 30, 250, 30);

                JLabel valueLabel = new JLabel("Value");
                JTextField value = new JTextField();
                valueLabel.setFont(new Font("Sanserif", Font.BOLD, 12));
                panel.add(valueLabel);
                valueLabel.setBounds(20, 80, 80, 30);
                value.setBounds(60, 80, 35, 30);
                panel.add(value);

                JLabel choicesLabel = new JLabel("# of Choices");
                JTextField choices = new JTextField();
                choicesLabel.setFont(new Font("Sanserif", Font.BOLD, 12));
                panel.add(choicesLabel);
                choicesLabel.setBounds(115, 80, 80, 30);
                panel.add(choices);
                choices.setBounds(190, 80, 35, 30);

                JButton addButton = new JButton("Add");
                panel.add(addButton);
                addButton.setBounds(LABEL_LEFT, 200 - BUTTON_TOP, 100, 30);

                counter = 0;

                addButton.addActionListener(new java.awt.event.ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        questionNameLabel.setVisible(false);
                        questionName.setVisible(false);
                        valueLabel.setVisible(false);
                        value.setVisible(false);
                        choicesLabel.setVisible(false);
                        choices.setVisible(false);
                        addButton.setVisible(false);

                        String title = questionName.getText();
                        double maxValue = Double.parseDouble(value.getText());
                        int numChoices = Integer.parseInt(choices.getText());

                        Question q = new MCSAQuestion(title, maxValue);


                        JLabel answerTitleLabel = new JLabel("Answer");
                        answerTitleLabel.setFont(new Font("Sanssrif", Font.BOLD, 12));
                        JTextField answerTitle = new JTextField("");
                        panel.add(answerTitleLabel);
                        answerTitleLabel.setBounds(BUTTON_LEFT, BUTTON_TOP, 50, 30);
                        panel.add(answerTitle);
                        answerTitle.setBounds(BUTTON_LEFT + 65, BUTTON_TOP, 250, 30);

                        JLabel creditLabel = new JLabel("Credit if Selected");
                        creditLabel.setFont(new Font("Sanssrif", Font.BOLD, 12));
                        JTextField creditIfSelected = new JTextField("");
                        panel.add(creditLabel);
                        creditLabel.setBounds(BUTTON_LEFT, BUTTON_TOP + BUTTON_HEIGHT_INC, 120, 30);
                        panel.add(creditIfSelected);
                        creditIfSelected.setBounds(BUTTON_LEFT + 115, BUTTON_TOP + BUTTON_HEIGHT_INC, 35, 30);

                        JButton addAnswer = new JButton("Add Answer");
                        panel.add(addAnswer);
                        addAnswer.setBounds(LABEL_LEFT, 200 - BUTTON_TOP, 100, 30);


                        addAnswer.addActionListener(new java.awt.event.ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                String ansTitle = answerTitle.getText();
                                double credit = Double.parseDouble(creditIfSelected.getText());
                                Answer a = new MCSAAnswer(ansTitle, credit);
                                ((MCSAQuestion) q).addAnswer(a);

                                answerTitle.setText("");
                                creditIfSelected.setText("");

                                counter++;
                                if (counter >= numChoices) {
                                    theExam.addQuestion(q);
                                    frame.dispose();
                                    print();
                                }
                            }

                        });


                    }
                });

                JButton cancelButton = new JButton("Cancel");
                panel.add(cancelButton);
                cancelButton.setBounds(LABEL_LEFT + BUTTON_WIDTH_INC, 200 - BUTTON_TOP, 100, 30);
                cancelButton.addActionListener(new java.awt.event.ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        frame.dispose();
                    }
                });


                frame.getContentPane().add(BorderLayout.CENTER, panel);
                frame.pack();
                frame.setLocationByPlatform(true);
                frame.setVisible(true);
                frame.setResizable(false);
            }
        });
    }

    private void addMCMAQuestion() {
        System.out.println("MCMAQuestion");

        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                JFrame frame = new JFrame("Add MCMAQuestion");
                frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                frame.setSize(new Dimension(400, 250));

                try {
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                } catch (Exception e) {
                    e.printStackTrace();
                }

                JPanel panel = new JPanel();
                panel.setLayout(new BorderLayout());
                panel.setOpaque(true);
                panel.setPreferredSize(new Dimension(380, 230));
                panel.setLayout(null);

                JLabel questionNameLabel = new JLabel("Title");
                questionNameLabel.setFont(new Font("Sanserif", Font.BOLD, 12));
                panel.add(questionNameLabel);
                questionNameLabel.setBounds(20, 30, 100, 30);
                JTextField questionName = new JTextField(50);
                panel.add(questionName);
                questionName.setBounds(60, 30, 250, 30);

                JLabel valueLabel = new JLabel("Value");
                JTextField value = new JTextField();
                valueLabel.setFont(new Font("Sanserif", Font.BOLD, 12));
                panel.add(valueLabel);
                valueLabel.setBounds(20, 80, 80, 30);
                value.setBounds(60, 80, 35, 30);
                panel.add(value);

                JLabel choicesLabel = new JLabel("# of Choices");
                JTextField choices = new JTextField();
                choicesLabel.setFont(new Font("Sanserif", Font.BOLD, 12));
                panel.add(choicesLabel);
                choicesLabel.setBounds(115, 80, 80, 30);
                panel.add(choices);
                choices.setBounds(190, 80, 35, 30);

                JLabel baseCreditLabel = new JLabel("Base");
                JTextField baseCredit = new JTextField();
                baseCreditLabel.setFont(new Font("Sanserif", Font.BOLD, 12));
                panel.add(baseCreditLabel);
                baseCreditLabel.setBounds(240, 80, 80, 30);
                panel.add(baseCredit);
                baseCredit.setBounds(275, 80, 35, 30);

                JButton addButton = new JButton("Add");
                panel.add(addButton);
                addButton.setBounds(LABEL_LEFT, 200 - BUTTON_TOP, 100, 30);

                counter = 0;

                addButton.addActionListener(new java.awt.event.ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        questionNameLabel.setVisible(false);
                        questionName.setVisible(false);
                        valueLabel.setVisible(false);
                        value.setVisible(false);
                        choicesLabel.setVisible(false);
                        choices.setVisible(false);
                        baseCreditLabel.setVisible(false);
                        baseCredit.setVisible(false);
                        addButton.setVisible(false);

                        String title = questionName.getText();
                        double maxValue = Double.parseDouble(value.getText());
                        double baseCred = Double.parseDouble(baseCredit.getText());
                        int numChoices = Integer.parseInt(choices.getText());

                        Question q = new MCMAQuestion(title, maxValue, baseCred);


                        JLabel answerTitleLabel = new JLabel("Answer");
                        answerTitleLabel.setFont(new Font("Sanssrif", Font.BOLD, 12));
                        JTextField answerTitle = new JTextField("");
                        panel.add(answerTitleLabel);
                        answerTitleLabel.setBounds(BUTTON_LEFT, BUTTON_TOP, 50, 30);
                        panel.add(answerTitle);
                        answerTitle.setBounds(BUTTON_LEFT + 65, BUTTON_TOP, 250, 30);

                        JLabel creditLabel = new JLabel("Credit if Selected");
                        creditLabel.setFont(new Font("Sanssrif", Font.BOLD, 12));
                        JTextField creditIfSelected = new JTextField("");
                        panel.add(creditLabel);
                        creditLabel.setBounds(BUTTON_LEFT, BUTTON_TOP + BUTTON_HEIGHT_INC, 120, 30);
                        panel.add(creditIfSelected);
                        creditIfSelected.setBounds(BUTTON_LEFT + 115, BUTTON_TOP + BUTTON_HEIGHT_INC, 35, 30);

                        JButton addAnswer = new JButton("Add Answer");
                        panel.add(addAnswer);
                        addAnswer.setBounds(LABEL_LEFT, 200 - BUTTON_TOP, 100, 30);


                        addAnswer.addActionListener(new java.awt.event.ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                String ansTitle = answerTitle.getText();
                                double credit = Double.parseDouble(creditIfSelected.getText());
                                Answer a = new MCMAAnswer(ansTitle, credit);
                                ((MCMAQuestion) q).addAnswer(a);

                                answerTitle.setText("");
                                creditIfSelected.setText("");

                                counter++;
                                if (counter >= numChoices) {
                                    theExam.addQuestion(q);
                                    frame.dispose();
                                    print();
                                }
                            }

                        });


                    }
                });

                JButton cancelButton = new JButton("Cancel");
                panel.add(cancelButton);
                cancelButton.setBounds(LABEL_LEFT + BUTTON_WIDTH_INC, 200 - BUTTON_TOP, 100, 30);
                cancelButton.addActionListener(new java.awt.event.ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        frame.dispose();
                    }
                });


                frame.getContentPane().add(BorderLayout.CENTER, panel);
                frame.pack();
                frame.setLocationByPlatform(true);
                frame.setVisible(true);
                frame.setResizable(false);
            }
        });
    }

    private void addSAQuestion() {
        System.out.println("SAQuestion");

        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                JFrame frame = new JFrame("Add SAQuestion");
                frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                frame.setSize(new Dimension(400, 250));

                try {
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                } catch (Exception e) {
                    e.printStackTrace();
                }

                JPanel panel = new JPanel();
                panel.setLayout(new BorderLayout());
                panel.setOpaque(true);
                panel.setPreferredSize(new Dimension(380, 230));
                panel.setLayout(null);

                JLabel questionNameLabel = new JLabel("Title");
                questionNameLabel.setFont(new Font("Sanserif", Font.BOLD, 12));
                panel.add(questionNameLabel);
                questionNameLabel.setBounds(20, 30, 100, 30);
                JTextField questionName = new JTextField(50);
                panel.add(questionName);
                questionName.setBounds(60, 30, 250, 30);

                JLabel valueLabel = new JLabel("Value");
                JTextField value = new JTextField();
                valueLabel.setFont(new Font("Sanserif", Font.BOLD, 12));
                panel.add(valueLabel);
                valueLabel.setBounds(20, 80, 80, 30);
                value.setBounds(60, 80, 35, 30);
                panel.add(value);

                JLabel correctAnsLabel = new JLabel("Answer");
                JTextField correctAns = new JTextField();
                correctAnsLabel.setFont(new Font("Sanserif", Font.BOLD, 12));
                panel.add(correctAnsLabel);
                correctAnsLabel.setBounds(105, 80, 80, 30);
                panel.add(correctAns);
                correctAns.setBounds(155, 80, 155, 30);


                JButton addButton = new JButton("Add");
                panel.add(addButton);
                addButton.setBounds(LABEL_LEFT, 200 - BUTTON_TOP, 100, 30);

                JButton cancelButton = new JButton("Cancel");
                panel.add(cancelButton);
                cancelButton.setBounds(LABEL_LEFT + BUTTON_WIDTH_INC, 200 - BUTTON_TOP, 100, 30);

                addButton.addActionListener(new java.awt.event.ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        String questionNameString = questionName.getText();
                        double valueDouble = Double.parseDouble(value.getText());
                        String correctAnswer = correctAns.getText();

                        System.out.println(questionNameString);

                        Question q = new SAQuestion(questionNameString, valueDouble);
                        Answer correct = new SAAnswer(correctAnswer);
                        q.setRightAnswer(correct);

                        theExam.addQuestion(q);
                        print();
                        ;
                        frame.dispose();
                    }
                });

                cancelButton.addActionListener(new java.awt.event.ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        frame.dispose();
                    }
                });

                frame.getContentPane().add(BorderLayout.CENTER, panel);
                frame.pack();
                frame.setLocationByPlatform(true);
                frame.setVisible(true);
                frame.setResizable(false);
            }
        });
    }

    private void addNumQuestion() {
        System.out.println("NumQuestion");

        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                JFrame frame = new JFrame("Add NumQuestion");
                frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                frame.setSize(new Dimension(400, 250));

                try {
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                } catch (Exception e) {
                    e.printStackTrace();
                }

                JPanel panel = new JPanel();
                panel.setLayout(new BorderLayout());
                panel.setOpaque(true);
                panel.setPreferredSize(new Dimension(380, 230));
                panel.setLayout(null);

                JLabel questionNameLabel = new JLabel("Title");
                questionNameLabel.setFont(new Font("Sanserif", Font.BOLD, 12));
                panel.add(questionNameLabel);
                questionNameLabel.setBounds(20, 30, 100, 30);
                JTextField questionName = new JTextField(50);
                panel.add(questionName);
                questionName.setBounds(60, 30, 250, 30);

                JLabel valueLabel = new JLabel("Value");
                JTextField value = new JTextField();
                valueLabel.setFont(new Font("Sanserif", Font.BOLD, 12));
                panel.add(valueLabel);
                valueLabel.setBounds(20, 80, 80, 30);
                value.setBounds(60, 80, 35, 30);
                panel.add(value);

                JLabel correctAnsLabel = new JLabel("Answer");
                JTextField correctAns = new JTextField();
                correctAnsLabel.setFont(new Font("Sanserif", Font.BOLD, 12));
                panel.add(correctAnsLabel);
                correctAnsLabel.setBounds(105, 80, 80, 30);
                panel.add(correctAns);
                correctAns.setBounds(155, 80, 35, 30);

                JLabel toleranceLabel = new JLabel("Tolerance");
                JTextField tolerance = new JTextField();
                toleranceLabel.setFont(new Font("Sanserif", Font.BOLD, 12));
                panel.add(toleranceLabel);
                toleranceLabel.setBounds(210, 80, 80, 30);
                panel.add(tolerance);
                tolerance.setBounds(275, 80, 35, 30);


                JButton addButton = new JButton("Add");
                panel.add(addButton);
                addButton.setBounds(LABEL_LEFT, 200 - BUTTON_TOP, 100, 30);

                JButton cancelButton = new JButton("Cancel");
                panel.add(cancelButton);
                cancelButton.setBounds(LABEL_LEFT + BUTTON_WIDTH_INC, 200 - BUTTON_TOP, 100, 30);

                addButton.addActionListener(new java.awt.event.ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        String questionNameString = questionName.getText();
                        double valueDouble = Double.parseDouble(value.getText());
                        double toleranceDouble = Double.parseDouble(tolerance.getText());
                        double correctAnswer = Double.parseDouble(correctAns.getText());

                        System.out.println(questionNameString);

                        Question q = new NumQuestion(questionNameString, valueDouble, toleranceDouble);
                        Answer correct = new NumAnswer(correctAnswer);
                        q.setRightAnswer(correct);

                        theExam.addQuestion(q);
                        print();
                        ;
                        frame.dispose();
                    }
                });

                cancelButton.addActionListener(new java.awt.event.ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        frame.dispose();
                    }
                });

                frame.getContentPane().add(BorderLayout.CENTER, panel);
                frame.pack();
                frame.setLocationByPlatform(true);
                frame.setVisible(true);
                frame.setResizable(false);
            }
        });

    }

    private Exam add() {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                JFrame frame = new JFrame("Add Question");
                frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                frame.setSize(new Dimension(400, 250));

                try {
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                } catch (Exception e) {
                    e.printStackTrace();
                }

                JPanel panel = new JPanel();
                panel.setLayout(new BorderLayout());
                panel.setOpaque(true);
                panel.setPreferredSize(new Dimension(380, 230));
                panel.setLayout(null);


                String questionOptions[] = {"MCSAQuestion", "MCMAQuestion", "SAQuestion", "NumQuestion"};

                JLabel messageLabel = new JLabel("What kind of question do you want to add?");
                panel.add(messageLabel);
                messageLabel.setBounds(37, 3 * BUTTON_TOP / 2, 500, 30);
                messageLabel.setFont(new Font("Sans Serif", Font.BOLD, 16));

                JComboBox questionList = new JComboBox(questionOptions);
                questionList.setSelectedIndex(3);
                questionList.setBounds(85, 3 * BUTTON_TOP, 200, 30);
                panel.add(questionList);


                JButton addButton = new JButton("Add");
                panel.add(addButton);
                addButton.setBounds(LABEL_LEFT, 200 - BUTTON_TOP, 100, 30);
                addButton.addActionListener(new java.awt.event.ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        String name = (String) questionList.getSelectedItem();

                        switch (name) {
                            case "MCSAQuestion":
                                addMCSAQuestion();
                                frame.dispose();
                                break;
                            case "MCMAQuestion":
                                addMCMAQuestion();
                                frame.dispose();
                                break;
                            case "SAQuestion":
                                addSAQuestion();
                                frame.dispose();
                                break;
                            case "NumQuestion":
                                addNumQuestion();
                                frame.dispose();
                                break;

                        }
                    }
                });

                JButton cancelButton = new JButton("Cancel");
                panel.add(cancelButton);
                cancelButton.setBounds(LABEL_LEFT + BUTTON_WIDTH_INC, 200 - BUTTON_TOP, 100, 30);
                cancelButton.addActionListener(new java.awt.event.ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        frame.dispose();
                    }
                });


                frame.getContentPane().add(BorderLayout.CENTER, panel);
                frame.pack();
                frame.setLocationByPlatform(true);
                frame.setVisible(true);
                frame.setResizable(false);
            }
        });


        return theExam;
    }

    //TODO: Make this work
    private Exam remove() {
        if (removeIndexTextArea.getText().equals("")) {
            return theExam;
        }
        int index = Integer.parseInt(removeIndexTextArea.getText());
        theExam.remove(index - 1);
        print();
        return theExam;
    }

    private Exam reorder() {
        theExam.reorderMCAnswers(-1);
        theExam.reorderQuestions();
        print();
        return theExam;
    }

    private void print() {
        examTextArea.setText(theExam.toString());
    }

    private class MyMouseListener implements MouseListener {

        /* Might need this */
        public void mouseClicked(MouseEvent e) {

        }

        //Ignore below
        public void mouseExited(MouseEvent e) {
        }

        public void mouseReleased(MouseEvent e) {
        }

        public void mouseEntered(MouseEvent e) {
        }

        public void mousePressed(MouseEvent e) {
        }
    }
}

