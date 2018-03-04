//
// Hamza Shahid
// hshahid
// CS 342 - Term Project Part 2
//
abstract public class MCAnswer extends Answer
{
    protected String  text;
    protected Boolean selected;
    protected double  creditIfSelected;


    protected MCAnswer(String text, double creditIfSelected)
    {
        this.text = text;
        this.creditIfSelected = 0.0;
    }


    public void setSelected(Boolean selected)
    {
        this.selected = selected;
    }


    public void print()
    {
        System.out.println(this.text);
    }
}
