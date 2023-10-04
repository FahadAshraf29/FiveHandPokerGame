
import java.util.LinkedList;

public class Hand implements Handable, TestableHand {

    //public static final int HAND_SIZE = 5;

    private LinkedList<Cardable> handList = new LinkedList<>();
    private int ranking = 0;

    //Methods:
    public Cardable getCard(int i)
    {
        return handList.get(i);
    }  //Returns the ith Cardable element of the hand.



    //This should be called after the discard method (see below), and replace all discarded cards with cards drawn from the Deckable
    // sent as a parameter (faceUp determines if the drawn cards should have the face up or down).
    public void draw(Deckable d, boolean faceUp)
    {
        for(int i=0;i<HAND_SIZE;i++)
        {
            if(handList.get(i)==null)
            {
                handList.set(i,d.drawACard(faceUp));
            }
        }
    }


    //Flips all cards (Cardables) of the hand so that they face up. Useful for the showdown.
    public void showAllCards()
    {
        for(int i=0;i<HAND_SIZE;i++)
        {
            handList.get(i).setFaceUp(true);
        }
    }


    //This method discards from the hand all the Cardables that have been selected  (that have selected state = true).
    // The method returns a LinkedList<Cardable> containing all the cards that have been discarded from this hand.
    public LinkedList<Cardable> discard()
    {
        LinkedList<Cardable> list2 = new LinkedList<>();
        for(int i=0;i<HAND_SIZE;i++)
        {
            if (handList.get(i).getSelected())
            {
                list2.add(handList.get(i));
                handList.set(i, null);
            }
        }
        return list2;
    }


    //This method will be called at the end of a round to empty the hand (discard the full hand, not considering the selected state).
    // It returns a LinkedList<Cardable> containing all the cards that were in the hand.
    public LinkedList<Cardable> returnCards()
    {
        LinkedList<Cardable> temp = new LinkedList<>();
        for(int i=0;i<HAND_SIZE;i++)
        {
            temp.add(handList.get(i));
        }
        return temp;
    }


    public void addCards(Cardable[] cards)
    {
        for(int i=0;i<HAND_SIZE;i++)
        {
            handList.addFirst(cards[i]);
        }
    }



    //This method evaluates what is in the hand, and returns a String description of the best poker hand that can be made with these cards.
    public String evaluateHand()
    {
        String s = "";
        String val = getHigh();


        if(isStraightFlush())
        {
            s = "Straight Flush";
            ranking = 1;
        }
        else if(isFourKind())
        {
            s = "Four of a Kind";
            ranking = 2;
        }
        else if(isFullHouse())
        {
            s = "Full house";
            ranking = 3;
        }
        else if(isFlush())
        {
            s = "Flush";
            ranking = 4;
        }
        else if(isStraight())
        {
            s = "Straight";
            ranking = 5;
        }
        else if(isThreeOfKind())
        {
            s = "Three of a kind";
            ranking = 6;
        }
        else if(isTwoPairs())
        {
            s = "Two pairs";
            ranking = 7;
        }
        else if(isPair())
        {
            s = "Pair";
            ranking = 8;
        }
        else
        {
            s = "Nothing";
            ranking = Integer.MAX_VALUE;
        }
        return s+", "+val+" high";
    }

    public int compareTo(Handable h)
    {
        int returnVal;
        Hand player = (Hand) h;
        player.evaluateHand();
        LinkedList<Cardable> temp = sortCard();
        int maxVal = getValue(temp.get(4));
        int maxValPar = player.getValue(temp.get(4));

        if (ranking > player.getRanking())
        {
            returnVal = 1;
        }
        else if (ranking < player.getRanking())
        {
            returnVal = -1;
        }
        else
        {
            if(maxVal>maxValPar)
            {
                returnVal = -1;
            }
            else if(maxVal<maxValPar)
            {
                returnVal = 1;
            }
            else
            {
                returnVal = 0;
            }
        }
        return returnVal;
    }


    ///HELPER METHODS
    private int getRanking()
    {
        return ranking;
    }

    private int getValue(Cardable card)
    {
        int tempValue;
        String cardDetail = card.toString();
        if(cardDetail.charAt(0)=='A')
        {
            tempValue = 14;
        }
        else if(cardDetail.charAt(0)=='K')
        {
            tempValue = 13;
        }
        else if(cardDetail.charAt(0)=='Q')
        {
            tempValue = 12;
        }
        else if(cardDetail.charAt(0)=='J')
        {
            tempValue = 11;
        }
        else if(cardDetail.charAt(0)=='1' &&cardDetail.charAt(1)=='0')
        {
            tempValue = 10;
        }
        else
        {
            tempValue = Character.getNumericValue(cardDetail.charAt(0));
        }
        return tempValue;
    }


    //Returns Highest Card
    private String getHigh()
    {
        int value;
        LinkedList<Cardable> tempList = sortCard();
        value = getValue(tempList.get(4));
        String val;
        if(value==14)
        {
            val = 'A' + "";
        }
        else if(value==13)
        {
            val = 'K' + "";
        }
        else if(value==12)
        {
            val = 'Q'+ "";
        }
        else if(value==11)
        {
            val = 'J'+"";
        }
        else if(value==10)
        {
            val = "10";
        }
        else
        {
            val = value + "";
        }
        return val;
    }



    //sorts the card in ascending order
    private LinkedList<Cardable> sortCard()
    {
        LinkedList<Cardable> curr = handList;
        Cardable tempCard;
        for(int i=0;i<HAND_SIZE;i++)
        {
            for(int j=i+1;j<HAND_SIZE;j++)
            {
                if(getValue(handList.get(i))>getValue(handList.get(j)))
                {
                    tempCard = handList.get(i);
                    curr.set(i,handList.get(j));
                    curr.set(j,tempCard);
                }
            }
        }
        return curr;
    }




    //Checks for ranking



    //Used to check straight also in this method the difference is just that it will return to false if all suits are not equal
    private boolean isStraightFlush()
    {
        boolean isBool = false;

        LinkedList<Cardable> temp = sortCard();
        int first = getValue(temp.get(0));
        int second = getValue(temp.get(1));
        int third = getValue(temp.get(2));
        int fourth = getValue(temp.get(3));
        int last = getValue(temp.get(4));

        Cardable.Suit suit = temp.get(0).getSuit();
        if(last-first +1 == temp.size())
        {
            if(first!=second && second!=third && third!=fourth && fourth!=last && last!=first)
            {
                isBool = true;
                for (int i = 0; i < HAND_SIZE; i++)
                {
                    if (suit.compareTo(temp.get(i).getSuit()) != 0)
                    {
                        isBool = false;
                        break;
                    }
                }
            }
        }
        return isBool;
    }

    private boolean isFourKind()
    {
        boolean isBool = false;
        LinkedList<Cardable> temp = sortCard();
        int first = getValue(temp.get(0));
        int second = getValue(temp.get(1));
        int secondLast = getValue(temp.get(3));
        int last = getValue(temp.get(4));

        if((secondLast  == first && last!=secondLast ) || (last==second && first!=second))
        {
            isBool = true;
        }

        return isBool;
    }
    private boolean isFullHouse()
    {
        boolean isBool = false;
        LinkedList<Cardable> temp = sortCard();
        int first = getValue(temp.get(0));
        int second = getValue(temp.get(1));
        int third = getValue(temp.get(2));
        int secondLast = getValue(temp.get(3));
        int last = getValue(temp.get(4));

        if((first==third && last==secondLast)||(first==second && third==last))
        {
            isBool = true;
        }


        return isBool;
    }
    private boolean isStraight()
    {
        boolean isBool = false;

        LinkedList<Cardable> temp = sortCard();
        int first = getValue(temp.get(0));
        int second = getValue(temp.get(1));
        int third = getValue(temp.get(2));
        int fourth = getValue(temp.get(3));
        int last = getValue(temp.get(4));

        if(last-first +1 == temp.size())
        {
            if(first!=second && second!=third && third!=fourth && fourth!=last && last!=first)
            {
                isBool = true;
            }
        }
        return isBool;
    }
    private boolean isFlush()
    {
        boolean isBool = false;
        LinkedList<Cardable> temp = sortCard();
        Cardable.Suit suit = temp.get(0).getSuit();

        for(int i=0;i< HAND_SIZE;i++)
        {
            if(suit.compareTo(temp.get(i).getSuit())==0)
            {
                isBool = true;
            }
            else
            {
                isBool = !isBool;
                break;
            }
        }
        return isBool;
    }



    private boolean isThreeOfKind()
    {
        boolean isBool = false;
        LinkedList<Cardable> temp = sortCard();
        int first = getValue(temp.get(0));
        int second = getValue(temp.get(1));
        int third = getValue(temp.get(2));
        int secondLast = getValue(temp.get(3));
        int last = getValue(temp.get(4));

       if((first==third && last!=secondLast && first!=last ) || (second==secondLast && first!=last && first!=second) || (third==last && first!=second && second!=last))
       {
           isBool = true;
       }
        return isBool;
    }

    private boolean isTwoPairs()
    {
        boolean isBool = false;
        LinkedList<Cardable> temp = sortCard();
        int first = getValue(temp.get(0));
        int second = getValue(temp.get(1));
        int third = getValue(temp.get(2));
        int secondLast = getValue(temp.get(3));
        int last = getValue(temp.get(4));

        if((first==second && second!=third && third==secondLast && last!=secondLast)||(first!=second && second==third && third!=secondLast && secondLast==last))
        {
            isBool = true;
        }

        return isBool;
    }

    private boolean isPair()
    {
        boolean isBool = false;
        LinkedList<Cardable> temp = sortCard();
        int first = getValue(temp.get(0));
        int second = getValue(temp.get(1));
        int third = getValue(temp.get(2));
        int secondLast = getValue(temp.get(3));
        int last = getValue(temp.get(4));

      if  ((first==second && second!=third && third!=secondLast && secondLast!=last)|| (first!=second && second==third && third!=secondLast && secondLast!=last)||
                (first!=second && second!=third && third==secondLast && secondLast!=last)|| (first!=second && second!=third && third!=secondLast && secondLast==last))
      {
          isBool = true;
      }

        return isBool;
    }

}
