//
// Hamza Shahid
// hshahid
// CS 342 - Term Project Part 2
//
public class ExamTester
{
    public static void main(String[] args)
    {
        System.out.println("Author: Hamza Shahid");
        System.out.println("Net-ID: hshahi2");
        System.out.println("CS 342 Term Project Part 2\n");

        Exam exam1 = new Exam("TEST 1");
        Question question;
        Answer answer1, answer2, answer3, answer4, answer5;

        //
        // Create a new MCSAQuestion and MCSAAnswers pertaining to
        // that question. Give each answer a value, set a right answer
        // for that question and then add it to the exam.
        //
        question = new MCSAQuestion("What is 2+2?",5.0);

        answer1 = new MCSAAnswer("Four",5.0);
        answer2 = new MCSAAnswer("One",0.0);
        answer3 = new MCSAAnswer("Five",0.0);
        answer4 = new MCSAAnswer("Ten",0.0);
        answer5 = new MCSAAnswer("Eight",0.0);

        ((MCQuestion)question).addAnswer((MCAnswer)answer1);
        ((MCQuestion)question).addAnswer((MCAnswer)answer2);
        ((MCQuestion)question).addAnswer((MCAnswer)answer3);
        ((MCQuestion)question).addAnswer((MCAnswer)answer4);
        ((MCQuestion)question).addAnswer((MCAnswer)answer5);

        question.setRightAnswers(answer1);
        exam1.addQuestion(question);


        //
        // Create a new MCSAQuestion and MCSAAnswers pertaining to
        // that question. Give each answer a value, set a right answer
        // for that question and then add it to the exam.
        //
        question = new MCSAQuestion("What is 1+2?",5.0);

        answer1 = new MCSAAnswer("Four",0.0);
        answer2 = new MCSAAnswer("Three",5.0);
        answer3 = new MCSAAnswer("Five",0.0);
        answer4 = new MCSAAnswer("Ten",0.0);
        answer5 = new MCSAAnswer("Eight",0.0);

        ((MCQuestion)question).addAnswer((MCAnswer)answer1);
        ((MCQuestion)question).addAnswer((MCAnswer)answer2);
        ((MCQuestion)question).addAnswer((MCAnswer)answer3);
        ((MCQuestion)question).addAnswer((MCAnswer)answer4);
        ((MCQuestion)question).addAnswer((MCAnswer)answer5);

        question.setRightAnswers(answer2);
        exam1.addQuestion(question);


        //
        // Create a new SAQuestion and SAAnswer pertaining to
        // that question. Set the right answer for that question
        // and then add it to the exam.
        //
        question = new SAQuestion("What company makes iPhones?",5.0);

        answer1 = new SAAnswer("Apple");
        question.setRightAnswers(answer1);

        exam1.addQuestion(question);


        //
        // Create a new SAQuestion and SAAnswer pertaining to
        // that question. Set the right answer for that question
        // and then add it to the exam.
        //
        question = new SAQuestion("What company makes PlayStation?",5.0);

        answer1 = new SAAnswer("Sony");
        question.setRightAnswers(answer1);

        exam1.addQuestion(question);




        exam1.reorderMCAnswers(2);
        exam1.reorderQuestions();

        exam1.print();
        exam1.getAnswerFromStudent(-1);
        exam1.reportQuestionValues();

    }
}
