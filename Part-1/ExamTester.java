//
// Hamza Shahid 
// hshahid
// CS 342 - Term Project Part 1 
// 
public class ExamTester
{
    public static void main(String[] args)
    {
        System.out.println("Author: Hamza Shahid");
        System.out.println("Net-ID: hshahi2");
        System.out.println("CS 342 Term Project Part 1\n");


        Exam exam1 = new Exam("Test 1");
        Question question;
        Answer answer1,answer2,answer3,answer4,answer5;

        //
        // Create a new question and set values for each
        // answer pertaining to that and then adding those
        // answers to the question. And then add it to the exam
        //
        question = new Question("What color in moon?");
        answer1 = new Answer("Black");
        answer1.setValue(0.0, 0.0);
        answer2 = new Answer("Yellow");
        answer2.setValue(0.0, 0.0);
        answer3 = new Answer("White");
        answer3.setValue(5.0, 0.0);
        answer4 = new Answer("Blue");
        answer4.setValue(0.0, 0.0);
        answer5 = new Answer("Red");
        answer5.setValue(0.0, 0.0);

        question.addAnswer(answer1);
        question.addAnswer(answer2);
        question.addAnswer(answer3);
        question.addAnswer(answer4);
        question.addAnswer(answer5);

        //
        // Choosing an answer.
        // selectedAnswer(#) - between 1-4
        // getQuestion(1) - stays 1
        //
        exam1.addQuestion(question);
        exam1.getQuestion(1).selectAnswer(1);

        //
        // Create a new question and set values for each
        // answer pertaining to that and then adding those
        // answers to the question. And then add it to the exam
        //
        question = new Question("What color is grass?");
        answer1 = new Answer("Orange");
        answer1.setValue(0.0, 0.0);
        answer2 = new Answer("Green");
        answer2.setValue(5.0, 0.0);
        answer3 = new Answer("Brown");
        answer3.setValue(0.0, 0.0);
        answer4 = new Answer("Pink");
        answer4.setValue(0.0, 0.0);
        answer5 = new Answer("White");
        answer5.setValue(0.0, 0.0);

        question.addAnswer(answer1);
        question.addAnswer(answer2);
        question.addAnswer(answer3);
        question.addAnswer(answer4);
        question.addAnswer(answer5);

        //
        // Choosing an answer.
        // selectedAnswer(#) - between 1-4
        // getQuestion(2) - stays 2
        //
        exam1.addQuestion(question);
        exam1.getQuestion(2).selectAnswer(2);

        //
        // Create a new question and set values for each
        // answer pertaining to that and then adding those
        // answers to the question. And then add it to the exam
        //
        question = new Question("What is 2 + 2?");
        answer1 = new Answer("40");
        answer1.setValue(0.0, 0.0);
        answer2 = new Answer("80");
        answer2.setValue(0.0, 0.0);
        answer3 = new Answer("2");
        answer3.setValue(0.0, 0.0);
        answer4 = new Answer("4");
        answer4.setValue(5.0, 0.0);
        answer5 = new Answer("5");
        answer5.setValue(0.0, 0.0);

        question.addAnswer(answer1);
        question.addAnswer(answer2);
        question.addAnswer(answer3);
        question.addAnswer(answer4);
        question.addAnswer(answer5);

        //
        // Choosing an answer.
        // selectedAnswer(#) - between 1-4
        // getQuestion(3) - stays 3
        //
        exam1.addQuestion(question);
        exam1.getQuestion(3).selectAnswer(4);

        //
        // Create a new question and set values for each
        // answer pertaining to that and then adding those
        // answers to the question. And then add it to the exam
        //
        question = new Question("Who won the UEFA Champions League in 2011/12 season?");
        answer1 = new Answer("Real Madrid");
        answer1.setValue(0.0, 0.0);
        answer2 = new Answer("Chelsea");
        answer2.setValue(5.0, 0.0);
        answer3 = new Answer("Barcelona");
        answer3.setValue(0.0, 0.0);
        answer4 = new Answer("Manchester United");
        answer4.setValue(0.0, 0.0);
        answer5 = new Answer("Liverpool");
        answer5.setValue(0.0, 0.0);

        question.addAnswer(answer1);
        question.addAnswer(answer2);
        question.addAnswer(answer3);
        question.addAnswer(answer4);
        question.addAnswer(answer5);

        //
        // Choosing an answer.
        // selectedAnswer(#) - between 1-4
        // getQuestion(4) - stays 4
        //
        exam1.addQuestion(question);
        exam1.getQuestion(4).selectAnswer(2);

        //
        // Print the exam along with total score and score on each question
        //
        exam1.reorderQuestions();

        exam1.print();
        System.out.println("Question 1 points: " + exam1.getQuestion(1).getValue());
        System.out.println("Question 2 points: " + exam1.getQuestion(2).getValue());
        System.out.println("Question 3 points: " + exam1.getQuestion(3).getValue());
        System.out.println("Question 4 points: " + exam1.getQuestion(4).getValue());
        System.out.println("You got " + exam1.getValue() + " points on the exam");

    }
}