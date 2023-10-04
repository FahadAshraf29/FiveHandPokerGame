import java.util.LinkedList;
import java.util.Random;

public class GameLogic implements GameLogicable {

    //Constants:
    private Hand cpuHand;
    private Hand humanHand;
    private int currentState = 0;
    private int GameState = 1;
    private int userWins=0;
    private int cpuWins = 0;
    //private String type = "Dumb";
    private String type = "Intelligent";
    private static LinkedList<Cardable> tempList = new LinkedList<>();
    Deck deck = new Deck();


    //Methods:

    public GameLogic()
    {
        deck.shuffle();
        cpuHand = new Hand();
        humanHand = new Hand();
        Cardable[] humanCards = new Cardable[Handable.HAND_SIZE];
        Cardable[] cpuCards = new Cardable[Handable.HAND_SIZE];
        for (int i = 0; i < Handable.HAND_SIZE; i++)
        {
            humanCards[i] = deck.drawACard(true);
            cpuCards[i] = deck.drawACard(false);
        }
        cpuHand.addCards(cpuCards);
        humanHand.addCards(humanCards);
    }


    //Returns the hand (Handable) of the CPU player.
    public Handable getCPUHand()
    {
        return cpuHand;
    }

    //Returns the hand (Handable) of the human player.
    public Handable getHumanHand()
    {
        return humanHand;
    }


    //The GUI will call this method to proceed to the next stage/state of the game. The String[] parameter is an empty array, which the method can fill up with messages that will be displayed in the GUI to describe the current state of the game. The size of this array is determined by the number of lines that can be displayed in the GUI, and this is stored in PokerTableDisplay.NUM_MESSAGE_ROWS. It is set to 4 for this assignment (4 lines max). Leaves empty rows (where nothing should be displayed) to null.
    //The method returns a boolean that indicates if the proceed button in the GUI should be enabled (return true) or not (return false). This was done to get more f
    public boolean nextState(String[] messages)
    {
        if(GameState<MAX_GAME_STATES)
        {
            if (currentState <= 0)
            {
                messages[0] = "Beginning Game " + GameState;
                messages[1] = "Player1, Select cards to Discard";
            }
            if (currentState == 1)
            {
                messages[1] = "Player1 has discarded";
                messages[2] = "Cpu is making decision........";
                stage1();
            }
            if (currentState == 2) {
                messages[1] = "Cpu discards";
                stage2();
            }
            if (currentState == 3) {
                messages[1] = "Giving cards from deck";
                stage3();
            }
            if (currentState == 4) {
                messages[1] = "Showing cards";
                stage4();
            }
            if (currentState == 5) {
                messages[0] = stage5();
                messages[1] = "Cpu, " + cpuHand.evaluateHand();
                messages[2] = "Player1, " + humanHand.evaluateHand();
            }
            if(currentState == 6)
            {
                messages[0] = "Number of Wins";
                messages[1] = "Cpu won: " + cpuWins;
                messages[2] = "Player1 won: " + userWins;
            }
            if(currentState==7)
            {
                stage6();
                messages[0] = "Beginning Game " + GameState;
                messages[1] = "Player choose cards to Discard";
            }
        }
        else
        {
            messages[1] = "Game Ends, Click 'X' to close window ";
        }

        currentState++;
        return true;
    }

    //Discards player card
    private void stage1()
    {
       tempList = humanHand.discard();
    }

    //Discards computerCard
    private void stage2()
    {
        if(getCpuType().equals("Dumb"))
        {
            Random random = new Random();
            int l = random.nextInt(Handable.HAND_SIZE);
            int c = 0;
            while(c <= l)
            {
                if(c == l)
                {
                    cpuHand.getCard(c).switchSelectedState();
                    tempList.addAll(cpuHand.discard());
                }
                c++;
            }
        }
        else // Intelligent CPU decision-making
        {
            String evaluation = cpuHand.evaluateHand();

            // If the CPU has a high ranking hand, don't discard any cards
            if(evaluation.contains("Straight Flush") || evaluation.contains("Four of a Kind") ||
                    evaluation.contains("Full house") || evaluation.contains("Flush") ||
                    evaluation.contains("Straight"))
            {
                // Don't discard any cards
            }
            else if(evaluation.contains("Three of a kind"))
            {
                // Discard two unpaired cards
                // Here we could add more logic to determine which exact cards to discard.
                cpuHand.getCard(3).switchSelectedState();
                cpuHand.getCard(4).switchSelectedState();
                tempList.addAll(cpuHand.discard());
            }
            else if(evaluation.contains("Two pairs") || evaluation.contains("Pair"))
            {
                // Discard cards that aren't part of the pair(s)
                // For simplicity, we're discarding the highest unpaired card. More complex logic could be added.
                for(int i = 0; i < Handable.HAND_SIZE; i++)
                {
                    // Using a placeholder logic to determine unpaired cards
                    if(!cpuHand.getCard(i).toString().contains("the paired value"))
                    {
                        cpuHand.getCard(i).switchSelectedState();
                        tempList.addAll(cpuHand.discard());
                        break;
                    }
                }
            }
            else // If the CPU has a weak hand
            {
                // Discard the card with the least potential
                // For simplicity, let's discard the first card. More complex logic could be added.
                cpuHand.getCard(0).switchSelectedState();
                tempList.addAll(cpuHand.discard());
            }
        }
    }

    //Will draw cards in stage3 with cpu card facing down
    private void stage3()
    {
        humanHand.draw(deck,true);
        cpuHand.draw(deck,false);
    }

    //Shows all card of user
    private void stage4()
    {
        cpuHand.showAllCards();
    }


   //Decide winner
    private String stage5()
    {
        String temp;
        if(cpuHand.compareTo(humanHand)==1)
        {
            temp = "Player1 wins!";
            userWins++;
        }
        else if(cpuHand.compareTo(humanHand)==-1)
        {
            temp = "Cpu wins!";
            cpuWins++;
        }
        else
        {
            temp = "Tied";
        }

        return temp;
    }

    private void stage6() {
        tempList.addAll(humanHand.returnCards());
        tempList.addAll(cpuHand.returnCards());

        deck.shuffle();
        Cardable[] humanCards = new Cardable[Handable.HAND_SIZE];
        Cardable[] cpuCards = new Cardable[Handable.HAND_SIZE];
        for (int i = 0; i < Handable.HAND_SIZE; i++)
        {
            humanCards[i] = deck.drawACard(true);
            cpuCards[i] = deck.drawACard(false);
        }
        cpuHand.addCards(cpuCards);
        humanHand.addCards(humanCards);


        for (int i = 0; i < Handable.HAND_SIZE; i++) {
            cpuHand.getCard(i).setFaceUp(false);
        }
        deck.returnToDeck(tempList);
        currentState = 0;
        GameState++;


    }

    private String getCpuType()
    {
        return this.type;
    }

}