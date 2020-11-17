import javax.swing.*;
import javax.swing.border.TitledBorder;

import java.awt.*;
import java.util.Arrays;

public class Foothill 
{
    // static for the 57 icons and their corresponding labels
    // normally we would not have a separate label for each card, but
    // if we want to display all at once using labels, we need to.

    static final int NUM_CARD_IMAGES = 57; // 52 + 4 jokers + 1 back-of-card image
    static final int NUM_CARDS_PER_HAND = 7;
    static final int NUM_PLAYERS = 2;

    static JLabel[] computerLabels = new JLabel[NUM_CARDS_PER_HAND];
    static JLabel[] humanLabels = new JLabel[NUM_CARDS_PER_HAND];
    static JLabel[] playedCardLabels = new JLabel[NUM_PLAYERS];
    static JLabel[] playerLabelText = new JLabel[NUM_PLAYERS];

    public static void main(String[] args)
    {
        int k;
        Icon tempIcon;

        // establish main frame in which program will run
        CardTable myCardTable 
        = new CardTable("CS 1B CardTable", NUM_CARDS_PER_HAND, NUM_PLAYERS);
        myCardTable.setSize(800, 600);
        myCardTable.setLocationRelativeTo(null);
        myCardTable.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // show everything to the user
        myCardTable.setVisible(true);

        // CREATE LABELS AND SETTING ICONS FOR COMPUTER AND HAND-----------------------
        Card randomCard;
        for (k=0; k< NUM_CARDS_PER_HAND; k++)
        {
            tempIcon = GUICard.getIcon(Card.generateRandomCard()); 
            humanLabels[k]=new JLabel(tempIcon);
            tempIcon = GUICard.getIcon(Card.generateRandomCard());
            computerLabels[k]=new JLabel(tempIcon);
        }

        // GENERATE TWO RANDOM CARDS ON PLAYING FIELD
        for (k=0; k< NUM_PLAYERS; k++)
        {
            randomCard = Card.generateRandomCard();
            playedCardLabels[k] = new JLabel(GUICard.getIcon(randomCard));
        }

        //CREATE TWO LABELS FOR PLAYING FIELD
        playerLabelText[0] = new JLabel("Computer", JLabel.CENTER);
        playerLabelText[1] = new JLabel("You", JLabel.CENTER);

        //adding the random Icons to the playing field panel
        for (k = 0; k < NUM_PLAYERS; k++)
        {
            myCardTable.pnlplaying_card.add(playedCardLabels[k]);
        }

        // adding the text fields to playing panel 
        for (k = 0; k < NUM_PLAYERS; k++)
        {
            myCardTable.pnlplaying_card.add(playerLabelText[k]);
        }
        
        //ADD LABELS TO COMPUTER AND HAND PANEL
        for (k=0; k < NUM_CARDS_PER_HAND; k++)
        {
            myCardTable.pnlplayer_bottom.add(humanLabels[k]);
            myCardTable.pnlcomputer_top.add(new JLabel(GUICard.getBackCardIcon()));
        }

        // show everything to the user
        myCardTable.setVisible(true);
    }
}

class CardTable extends JFrame
{
    static final int MAX_CARDS_PER_HAND = 57;
    static final int MAX_PLAYERS = 2;  // for now, we only allow 2 person games

    private int numCardsPerHand;
    private int numPlayers;

    public JPanel  pnlcomputer_top, pnlplayer_bottom, pnlplaying_card; 


    //public methods
    public CardTable(String title, int nCPerHand, int nPlayers)
    {
        super(title);

        //filters input
        numCardsPerHand = nCPerHand;
        numPlayers = nPlayers;
        pnlcomputer_top = new JPanel(new GridLayout(1,numCardsPerHand,10,10));
        pnlplayer_bottom = new JPanel(new GridLayout(1,numCardsPerHand,10,10));
        pnlplaying_card = new JPanel(new GridLayout(2,numPlayers,10,10));

        setLayout(new BorderLayout(10,10));
        add(pnlcomputer_top, BorderLayout.NORTH);
        add(pnlplaying_card, BorderLayout.CENTER);
        add(pnlplayer_bottom, BorderLayout.SOUTH);

        // place borders around three sub-panels
        pnlcomputer_top.setBorder(new TitledBorder("Computer's Hand"));
        pnlplaying_card.setBorder(new TitledBorder("Playing Field"));
        pnlplayer_bottom.setBorder(new TitledBorder("Your Hand"));

        GUICard.loadCardIcons();

    }

    public int getNumCardsPerHand()
    {
        return numCardsPerHand;
    }

    public int getNumPlayers()
    {
        return numPlayers;
    }
}

class GUICard
{
    //static members
    private static Icon[][] iconCards = new ImageIcon[14][4]; // 14 = A thru K (+ joker optional)
    private static Icon iconBack;
    private static boolean iconsLoaded = false;

    //helper static arrays
    private static String cardlValsConvertAssist = "A23456789TJQKX";
    private static String suitValsConvertAssist  = "CDHS";
    private static Card.Suit suitConvertAssist[] =
        {
                Card.Suit.clubs,
                Card.Suit.diamonds,
                Card.Suit.hearts,
                Card.Suit.spades
        };

    //static methods

    static void loadCardIcons()
    {
        String imageFileName;
        int intSuit, intVal;

        if (!iconsLoaded)
        {
            for (intSuit = 0; intSuit < 4; intSuit++)
                for (intVal = 0; intVal < 14; intVal++ )
                {
                    // card image files stored in Foothill/images folder with names like
                    // "AC.gif", "3H.gif","XD.gif", etc.
                    imageFileName = "images/"
                            + turnIntIntoCardValueChar(intVal) 
                            + turnIntIntoCardSuitChar(intSuit)
                            + ".gif";
                    iconCards[intVal][intSuit] = new ImageIcon(imageFileName);
                }
            iconBack = new ImageIcon("images/BK.gif");
            iconsLoaded = true;
        }
    }    

    // turns 0 - 13 into 'A', '2', '3', ... 'Q', 'K', 'X'
    static public char turnIntIntoCardValueChar(int k)
    {

        if ( k < 0 || k > 13)
            return '?'; 
        return cardlValsConvertAssist.charAt(k);
    }

    // turns 0 - 3 into 'C', 'D', 'H', 'S'
    static public char turnIntIntoCardSuitChar(int k)
    {
        if ( k < 0 || k > 3)
            return '?'; 
        return suitValsConvertAssist.charAt(k);
    }

    static public Icon getIcon(Card card)
    {
        loadCardIcons();
        return iconCards[valueAsInt(card)][suitAsInt(card)];
    }

 
    static public Icon getBackCardIcon()
    {
        return iconBack;
    }
    
    static public int valueAsInt(Card card)
    {
        return cardlValsConvertAssist.indexOf(card.getVal());
    }

    static public int suitAsInt(Card card)
    {
        return Arrays.asList(suitConvertAssist).indexOf(card.getSuit());
    }

}

//Card class --------------------------------------------------------------------
class Card
{   
    // type and constants
    public enum Suit { clubs, diamonds, hearts, spades };

    static final char DEFAULT_VAL = 'A';
    static final Suit DEFAULT_SUIT = Suit.spades;

    // private data
    private char value;
    private Suit suit;
    private boolean errorFlag;

    // 4 overloaded constructors
    public Card(char value, Suit suit)
    {   // because mutator sets errorFlag, we don't have to test
        set(value, suit);
    }

    public Card(char value)
    {
        this(value, DEFAULT_SUIT);
    }

    public Card()
    {
        this(DEFAULT_VAL, DEFAULT_SUIT);
    }

    // copy constructor
    public Card(Card card)
    {
        this(card.value, card.suit);
    }

    // mutators
    public boolean set(char value, Suit suit)
    {
        char upVal;  // for upcasing char

        // convert to uppercase to simplify
        upVal = Character.toUpperCase(value);

        if ( !isValid(upVal, suit))
        {
            errorFlag = true;
            return false;
        }

        // else implied
        errorFlag = false;
        this.value = upVal;
        this.suit = suit;
        return true;
    }

    // accessors
    public char getVal()
    {
        return value;
    }

    public Suit getSuit()
    {
        return suit;
    }

    public boolean getErrorFlag()
    {
        return errorFlag;
    }

    // stringizer
    public String toString()
    {
        String retVal;

        if (errorFlag)
            return "** illegal **";

        // else implied
        retVal =  String.valueOf(value);
        retVal += " of ";
        retVal += String.valueOf(suit);

        return retVal;
    }

    // helper
    private static boolean isValid(char value, Suit suit)
    {
        // don't need to test suit (enum), but leave in for clarity
        char upVal;  // string to hold the 1-char value
        String legalVals = "23456789TJQKA";

        // convert to uppercase to simplify (need #include <cctype>)
        upVal = Character.toUpperCase(value);

        // check for validity
        if ( legalVals.indexOf(upVal) >= 0 )
            return true;
        else
            return false;
    }

    public boolean equals(Card card)
    {
        if (this.value != card.value)
            return false;
        if (this.suit != card.suit)
            return false;
        if (this.errorFlag != card.errorFlag)
            return false;
        return true;
    }

    static public Card generateRandomCard()
    {
        int value_index = (int)(Math.random() * 52) /4 ;
        int suit_index = (int) (Math.random() * 4);
        return(new Card(GUICard.turnIntIntoCardValueChar(value_index),
                Card.Suit.values()[suit_index]));
    }
}








