//
// Hamza Shahid
// hshahid
// CS 342 - Term Project Part 2
//
abstract public class Question
{
    protected String text;
    protected Answer rightAnswer;
    protected Answer studentAnswer;
    protected double maxValue;


    public Question(String text, double maxValue)
    {
        this.text = text;
        this.maxValue = maxValue;
    }

    public void print()
    {
        System.out.println(text + "\n");
    }

    public void setRightAnswers(Answer ans)
    {
        this.rightAnswer = ans;
    }

    public abstract Answer getNewAnswer();
    public abstract void getAnswerFromStudent();
    public abstract double getValue();
}
