public class Card implements Cardable
{
    Suit suit;
    private int value;
    private boolean currentState;
    private boolean isFaceUp;

    public Card(int value,Suit suit)
    {
        this.value = value;
        this.suit = suit;
    }

    public boolean getSelected()
    {
        return currentState;
    }  //Returns the selected state: selected (true) or not selected (false).

    public boolean getFaceUp()
    {
        return isFaceUp;
    }  //Returns true if the face is up, false if it is facing down.

    public Suit getSuit()
    {
        return suit;
    }  //Returns the suit.

    public void switchSelectedState()
    {
        currentState = !currentState;
    }  //Switches the selected state: if it was true it becomes false and vice versa.

    public void resetSelected()
    {
        currentState = false;
    }  //Sets selected state to false (the default state).

    public void setFaceUp(boolean faceUp)
    {
        isFaceUp = faceUp;
    }  //Sets the faceUp to what is received as a parameter.


    public String toString()
    {
        String myStr;
        if(value<11)
        {
            myStr = ("" + value + getUnicode());
        }
        else
        {
            myStr = ("" + (char)value + getUnicode());
        }
        return myStr;
    }

    //HELPER METHOD
    private char getUnicode()
    {
        char unicode;
        if(suit==Suit.HEART)
        {
            unicode = '\u2665';
        }
        else if(suit==Suit.DIAMOND)
        {
            unicode = '\u2666';
        }
        else if(suit==Suit.SPADE)
        {
            unicode = '\u2660';
        }
        else
        {
            unicode = '\u2663';
        }
        return unicode;
    }



}
