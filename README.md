# FiveHandPokerGame
Overview

This repository contains the implementation of the 5-card draw poker game, complete with game logic and data structures like Deck, Card, and Hand.
Table of Contents

    Description
    Components
        Card
        Deck
        GameLogic
        Hand
    CPU Player AI    
    Implementation Details
    Contributing

Description

    This project implements the classic 5-card draw poker game in Java. Players draw cards, make decisions based on their hand, and play against a CPU player with configurable intelligence.

Components
  Card

    Represents a single playing card with attributes like suit, value, and state.
    Provides methods to get and set the state, suit, and face-up status of the card.

  Deck

    Represents a deck of cards.
    Provides methods to shuffle the deck, return cards to the deck, and draw a card.

  GameLogic

    Manages the game's flow and mechanics, making use of the Deck and Hand classes.
    Supports the game's various stages, from initializing hands to deciding the winner.
    Configurable CPU intelligence - can act randomly or with a strategy.

  Hand

    Represents a player's hand in poker.
    Provides methods to evaluate the hand, discard cards, draw new cards, and compare hands.

  Cardable 
    
    Single card representation with a value and suit.
    
  Deckable 
  
    A 52-card deck representation.
  
  Handable
    
    Represents a poker hand with a comparison method.
    
  TestableHand 
     
     For JUnit testing; allows direct addition of Cardables.
    
  GameLogicable 
     
     Contains game logic, players, cards, and game stats.  


CPU Player AI

  Two types of AI:

    1. Random choices AI.
    2. An AI with smarter decision-making.


Implementation Details

    Card: A single card representation, with methods for getting its state, switching its selection, and checking its face orientation.
    Deck: Represents a collection of 52 cards. Provides methods to shuffle, draw cards, and return discarded cards.
    GameLogic: Contains game mechanics, evaluating hands, determining winners, and handling the game's progress.
    Hand: Represents a player's hand, evaluating its rank in poker, and offering methods for drawing and discarding.

JUnit Tests

    The "JUnitTests.java" file is used to validate the compareTo method. Evaluation criteria will consider the complexity and relevance of the provided tests.    

Contributing

    Feel free to raise issues or submit pull requests. Any feedback or contribution is appreciated.
