//
// Hamza Shahid
// hshahid
// CS 342 - Term Project Part 1
//
public class Answer
{
    private String answer;
    private Boolean selected;
    private double isSelected;
    private double unSelected;


    //==============================================================
    public Answer(String answer)
    {
        this.answer     = answer;
        this.selected   = false;
        this.isSelected = 0.0;
        this.unSelected = 0.0;
    }


    //==============================================================
    // Prints the answer to the screen. The input parameter indicates
    // the position of this answer in the list of answers, 1 for the
    // first answer, 2 for the second, and so on.
    public void print(int position)
    {
        if (position == 1)
            System.out.println("   a. " + this.answer);
        else if (position == 2)
            System.out.println("   b. " + this.answer);
        else if (position == 3)
            System.out.println("   c. " + this.answer);
        else if (position == 4)
            System.out.println("   d. " + this.answer);
        else if (position == 5)
            System.out.println("   e. " + this.answer);
    }


    //==============================================================
    // An answer is selected ( true ) when a student has selected this
    // answer. It becomes unselected ( false ) if the student changes
    // their mind or selects another answer when only one answer is allowed.
    public void setSelected(Boolean answer)
    {

        this.selected = answer;
    }


    //==============================================================
    // sets the value(s) of this Answer if it is selected or unselected
    // respectively. The default for each is zero if unset. Set
    // values can be positive or negative.
    public void setValue(double isSelected, double unSelected)
    {
        this.isSelected = isSelected;
        this.unSelected = unSelected;
    }


    //==============================================================
    // Get the number of points that this answer contributes to the
    // exam score, based on whether it is selected and its value
    // when it is selected.
    public double getValue()
    {
        if(selected)
            return this.isSelected;
        else
            return this.unSelected;
    }
}
