
import java.util.LinkedList;
import java.util.Random;

public class Deck implements Deckable
{

    private LinkedList<Cardable> list = new LinkedList<>();

    //Constant:
    //public static final int NUM_CARDS = 52;

    public Deck()
    {
        int i = 0;
        int num = 2;
        int temp;
        Cardable card1;
        Cardable card2;
        Cardable card3;
        Cardable card4;
        while (i < NUM_CARDS) {
            temp = num;
            if (num == 11) {
                temp = (int) 'J';
            } else if (num == 12) {
                temp = (int) 'Q';
            } else if (num == 13) {
                temp = (int) 'K';
            } else if (num == 14) {
                temp = (int) 'A';
            }

            card1 = new Card(temp, Cardable.Suit.SPADE);
            card2 = new Card(temp, Cardable.Suit.HEART);
            card3 = new Card(temp, Cardable.Suit.DIAMOND);
            card4 = new Card(temp, Cardable.Suit.CLUB);

            list.add(card1);
            list.add(card2);
            list.add(card3);
            list.add(card4);

            num++;
            i = i + 4;
        }
    }


    //Methods:
    public void shuffle()
    {
        int i = 0;
        int k;

        Random random = new Random();
        Cardable temp;
        Cardable temp2;
        while (i<list.size())
        {
            k = random.nextInt(list.size());
            temp = list.get(i);
            temp2 = list.get(k);
            list.set(k,temp);
            list.set(i,temp2);
            i++;
        }
    }  //This must shuffle the deck randomly.


    //This must return the cards (Cardables) that were drawn previously (passed as a LinkedList<Cardable> parameter) back to the deck (do not recreate new cards, the same cards that were drawn must go back).
    public void returnToDeck(LinkedList<Cardable> discarded)
    {
        while (!discarded.isEmpty())
        {
            list.add(discarded.remove());
        }

    }

    //This deals the card (Cardable) that is at the top of the deck, either with the face up (true) or down (false).
    public Cardable drawACard(boolean faceUp)
    {
        Cardable card = list.removeFirst();
        card.setFaceUp(faceUp);
        return card;
    }
}
