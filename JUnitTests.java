import static org.junit.jupiter.api.Assertions.*;

import org.junit.Test;
//import org.junit.jupiter.api.Test;

//import static org.junit.jupiter.api.Assertions.assertTrue;

public class JUnitTests {

    @Test
    public void test1() 
	{
		Cardable[] cards1 = {new Card(2, Cardable.Suit.CLUB), new Card(2, Cardable.Suit.HEART), new Card(3, Cardable.Suit.CLUB), new Card(4, Cardable.Suit.CLUB), new Card(2, Cardable.Suit.DIAMOND)};
		TestableHand th1 = new Hand();
		th1.addCards(cards1);
		
		Cardable[] cards2 = {new Card(3, Cardable.Suit.HEART), new Card(4, Cardable.Suit.HEART), new Card(5, Cardable.Suit.HEART), new Card(6, Cardable.Suit.HEART), new Card(7, Cardable.Suit.DIAMOND)};
		TestableHand th2 = new Hand();
		th2.addCards(cards2);
		
		assertTrue(th1.compareTo(th2) < 0, "Straight beats Three of a kind.");
    }

	@Test
	public void test2()
	{
		Cardable[] cards1 = {new Card(2, Cardable.Suit.CLUB), new Card(2, Cardable.Suit.HEART), new Card(2, Cardable.Suit.CLUB), new Card(4, Cardable.Suit.CLUB), new Card(2, Cardable.Suit.DIAMOND)};
		TestableHand th1 = new Hand();
		th1.addCards(cards1);

		Cardable[] cards2 = {new Card(3, Cardable.Suit.HEART), new Card(4, Cardable.Suit.HEART), new Card(5, Cardable.Suit.HEART), new Card(6, Cardable.Suit.HEART), new Card(7, Cardable.Suit.DIAMOND)};
		TestableHand th2 = new Hand();
		th2.addCards(cards2);

		assertTrue(th1.compareTo(th2) < 0, "Four of kind beats Three of a kind.");
	}


	@Test
	public void test3()
	{
		Cardable[] cards1 = {new Card(2, Cardable.Suit.CLUB), new Card(2, Cardable.Suit.HEART), new Card(2, Cardable.Suit.CLUB), new Card(4, Cardable.Suit.CLUB), new Card(2, Cardable.Suit.DIAMOND)};
		TestableHand th1 = new Hand();
		th1.addCards(cards1);

		Cardable[] cards2 = {new Card(3, Cardable.Suit.HEART), new Card(4, Cardable.Suit.HEART), new Card(5, Cardable.Suit.HEART), new Card(6, Cardable.Suit.HEART), new Card(7, Cardable.Suit.DIAMOND)};
		TestableHand th2 = new Hand();
		th2.addCards(cards2);

		assertTrue(th2.compareTo(th1) < 0, "Three  of kind cannot beat Four of kind");
	}


	@Test
	public void test4()
	{
		Cardable[] cards1 = {new Card(2, Cardable.Suit.CLUB), new Card(2, Cardable.Suit.HEART), new Card(2, Cardable.Suit.CLUB), new Card(3, Cardable.Suit.CLUB), new Card(3, Cardable.Suit.DIAMOND)};
		TestableHand th1 = new Hand();
		th1.addCards(cards1);
		assert(th1.evaluateHand().contains("Full house"));
	}

	@Test
	public void test5()
	{
		Cardable[] cards1 = {new Card(2, Cardable.Suit.CLUB), new Card(3, Cardable.Suit.HEART), new Card(4, Cardable.Suit.CLUB), new Card(5, Cardable.Suit.CLUB), new Card(6, Cardable.Suit.DIAMOND)};
		TestableHand th1 = new Hand();
		th1.addCards(cards1);
		assert(th1.evaluateHand().contains("Straight"));
	}

	@Test
	public void test6()
	{
		Cardable[] cards1 = {new Card(2, Cardable.Suit.CLUB), new Card(3, Cardable.Suit.CLUB), new Card(4, Cardable.Suit.CLUB), new Card(5, Cardable.Suit.CLUB), new Card(6, Cardable.Suit.CLUB)};
		TestableHand th1 = new Hand();
		th1.addCards(cards1);


		Cardable[] cards2 = {new Card(2, Cardable.Suit.HEART), new Card(3, Cardable.Suit.CLUB), new Card(4, Cardable.Suit.CLUB), new Card(5, Cardable.Suit.CLUB), new Card(6, Cardable.Suit.CLUB)};
		TestableHand th2 = new Hand();
		th2.addCards(cards2);

		assertTrue(th2.compareTo(th1)<0,"Straight Flush beats straight");

	}


	@Test
	public void test7()
	{
		Cardable[] cards1 = {new Card(4, Cardable.Suit.CLUB), new Card(4, Cardable.Suit.CLUB), new Card(2, Cardable.Suit.CLUB), new Card(2, Cardable.Suit.CLUB), new Card(6, Cardable.Suit.CLUB)};
		TestableHand th1 = new Hand();
		th1.addCards(cards1);


		Cardable[] cards2 = {new Card(4, Cardable.Suit.CLUB), new Card(4, Cardable.Suit.CLUB), new Card(6, Cardable.Suit.CLUB), new Card(5, Cardable.Suit.CLUB), new Card(7, Cardable.Suit.CLUB)};
		TestableHand th2 = new Hand();
		th2.addCards(cards2);

		assertTrue(th2.compareTo(th1)<0,"Two pairs beat pairs");

	}


}
