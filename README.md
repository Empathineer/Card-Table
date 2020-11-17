# Card-Table GUI
            


<h4 style="color: #ff0000;"> A JFrame of Card Icons</h4>
<h3><u>Objective</u></h3>
<p>The purpose of this assignment was to transition <strong>Card</strong> classes from the realm of console apps to that of GUI apps.&nbsp; This was accomplished per the following stages:</p>
<ol>
<li>
<strong>Read and display Card pictures</strong> - Read <strong>.gif</strong> image files as <strong>Icons</strong>, and attach them to <strong>JLabels</strong> that we can display on a <strong>JFrame</strong>.</li>
<li>
<strong>Encapsulate the Card Icons in a class GUICard</strong> - Once we debug imagery for cards, above, we can move it into its own class, <strong>GUICard</strong>.</li>
<li>
<strong>Create a CardTable class </strong>- This <strong>JFrame</strong> class will embody the <strong>JPanels</strong> and <strong>Layout</strong>(s) needed for our application. This is where all the cards and controls will be placed. (It plays the same role as class <strong>Calculator</strong> of your RPN lecture.)</li>
</ol>
<p>The first phase (item 1) will allow you to debug the problem of reading the <strong>.gif </strong>files and displaying them on a <strong>JFrame</strong> without any excess logic or class complexity.&nbsp; The second phase (items 2 and 3) will let you turn what you did in the first phase and what you read in the modules into a multi-class project.</p>
<p>The three&nbsp; bullets will be done in<i><strong> two phases</strong></i>.&nbsp; <span style="color: #ff0000;"><strong>You should hand in two complete programs, one for each phase.&nbsp; You may attach two files or place them all in one file, well delimited.</strong></span>&nbsp; The main, public class of each program must be named <strong>Foothill</strong> so I can easily run it, and other classes must <i>not</i> be public.&nbsp; <i>You do not submit any runs - I will run your programs</i>.</p>
<h3><u>Download the Card .gifs</u></h3>
<p>The GNU Free Software Foundation provides code and images for playing-cards that are public domain.&nbsp; I prepared a <strong>.zip</strong> file which contains a <strong> .gif f</strong>or every card with some minor name changes.&nbsp; Download it here:&nbsp;</p>
<p><a href="http://www.fgamedia.org/faculty/loceff/cs_courses/cs_1b/resources/images_for_card_assignment.zip" target="_blank" class="external" rel="noreferrer noopener"><span>Card Images Download</span><span aria-hidden="true" class="ui-icon ui-icon-extlink ui-icon-inline" title="Links to an external site."></span><span class="screenreader-only">&nbsp;(Links to an external site.)</span></a>.</p>
<p>After you unzip them, you will have a single folder called <strong>images</strong> that contains all the <strong>.gif </strong>files.&nbsp; Move that folder to your <strong>[java workspace]/[project name]</strong> directory.&nbsp; If your project is named <strong> Assignment_5</strong>, and your Eclipse workspace is named <strong>workspace</strong>, then move images folder to <strong>workspace/Assignment_5</strong>.&nbsp; Since your program considers <strong>Assignment_5</strong> to be the root directory, all <strong>.gif</strong> files can be referenced from your program using names like <strong> "images/3S.gif"</strong> for, say, <i><strong>3 of spades</strong></i>.</p>
<p>In addition to the <strong>52 standard cards</strong>, there are <strong>four jokers</strong> and a <strong>card-back</strong> image which can be used to display dealer or other player cards that you don't want the user to see yet.</p>
<p><img src="http://www.fgamedia.org/faculty/loceff/cs_courses/common/assignment_pics/JH.gif" alt="" width="73" height="97" style="max-width: 73px;">&nbsp; <img src="http://www.fgamedia.org/faculty/loceff/cs_courses/common/assignment_pics/4C.gif" alt="" width="73" height="97" style="max-width: 73px;">&nbsp; <img src="http://www.fgamedia.org/faculty/loceff/cs_courses/common/assignment_pics/BK.gif" alt="" width="73" height="97" style="max-width: 73px;">&nbsp; <img src="http://www.fgamedia.org/faculty/loceff/cs_courses/common/assignment_pics/AS.gif" alt="" width="73" height="97" style="max-width: 73px;">&nbsp; <img src="http://www.fgamedia.org/faculty/loceff/cs_courses/common/assignment_pics/XD.gif" alt="" width="73" height="97" style="max-width: 73px;"></p>
<p>(The card on the far right is <i><strong>joker 2</strong></i>, not a jack.)</p>
<h3><u>Phase 1: Reading and Displaying the .gif Files</u></h3>
<p>At the end of <span style="color: #800080;"><i><strong>Module 5A.7</strong></i></span>, you learned how to instantiate an <strong>Icon</strong> object to represent any<strong> .gif</strong>, <strong>.jpg</strong> or other image file on your disk and then place that <strong>Icon</strong> on a <strong>JLabel</strong>.&nbsp; In <strong><i>P</i></strong><i><strong>hase 1</strong></i>, we simply create an array of 57 <strong>JLabels</strong>, attach the<strong> 57 .gif files</strong> to them, and display the labels, unstructured, in a single <strong>JFrame</strong>.&nbsp; Here is a possible <strong>main()</strong> that you can use as a starting point. You don't have to use my<strong> main()</strong> but yours should be no longer or more complicated.&nbsp; Look at your images folder to see the names of each <strong>.gif</strong> file:&nbsp; you have to be able to construct their names in a loop (-22 points if you list all 52 cards named literally because this is array logic).&nbsp; Where is the <i><strong>card-back</strong></i> image stored?&nbsp; Find it in the <strong>images</strong> folder.</p>
<blockquote>
<pre>import javax.swing.*;
import java.awt.*;
   
public class Foothill 
{
   // static for the 57 icons and their corresponding labels
   // normally we would not have a separate label for each card, but
   // if we want to display all at once using labels, we need to.
   
   static int NUM_CARD_IMAGES = 57; // 52 + 4 jokers + 1 back-of-card image
   static Icon[] icon = new ImageIcon[NUM_CARD_IMAGES];
   static JLabel[] labels = new JLabel[NUM_CARD_IMAGES];

   // for assisting with conversions:
   static String cardlValsConvertAssist = "23456789TJQKAX";
   static String suitValsConvertAssist = "CDHS";
   
   static void loadCardIcons()
   {
      String imageFileName;
      int intSuit, intVal;

      for (intSuit = 0; intSuit &lt; 4; intSuit++)
         for (intVal = 0; intVal &lt; 14; intVal++ )
         {
            // card image files stored in Foothill/images folder with names like
            // "AC.gif", "3H.gif","XD.gif", etc.
            imageFileName = "images/"
                  + turnIntIntoCardValueChar(intVal) 
                  + turnIntIntoCardSuitChar(intSuit)
                  + ".gif";
            icon[intSuit*14 + intVal] = new ImageIcon(imageFileName);
         }
      imageFileName = "images/BK.gif";
      icon[icon.length - 1] = new ImageIcon(imageFileName);
   }
   
   // turns 0 - 13 into 'A', '2', '3', ... 'Q', 'K', 'X'
   static char turnIntIntoCardValueChar(int k)
   {
   
      if ( k &lt; 0 || k &gt; 13)
         return '?'; 
      return cardlValsConvertAssist.charAt(k);
   }
   
   // turns 0 - 3 into 'C', 'D', 'H', 'S'
   static char turnIntIntoCardSuitChar(int k)
   {
      if ( k &lt; 0 || k &gt; 3)
         return '?'; 
      return suitValsConvertAssist.charAt(k);
   }
   
   public static void main(String[] args)
   {
      int k;
      
      // prepare the image icon array
      loadCardIcons();
      
      // establish main frame in which program will run
      JFrame frmMyWindow = new JFrame("Card Room");
      frmMyWindow.setSize(1150, 650);
      frmMyWindow.setLocationRelativeTo(null);
      frmMyWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      
      // set up layout which will control placement of buttons, etc.
      FlowLayout layout = new FlowLayout(FlowLayout.CENTER, 5, 20);   
      frmMyWindow.setLayout(layout);
      
      // prepare the image label array
      for (k = 0; k &lt; NUM_CARD_IMAGES; k++)
         labels[k] = new JLabel(icon[k]);
      
      // place your 3 controls into frame
      for (k = 0; k &lt; NUM_CARD_IMAGES; k++)
         frmMyWindow.add(labels[k]);

      // show everything to the user
      frmMyWindow.setVisible(true);
   }
}
</pre>
</blockquote>
<p><img style="display: block; margin-left: auto; margin-right: auto; max-width: 394px;" src="http://www.fgamedia.org/faculty/loceff/cs_courses/common/assignment_pics/ass_5_shot_1.JPG" alt="" width="394" height="437"></p>
<p>Hand in a main class that accomplishes this as the first of two&nbsp; programs.</p>
<h3><u>Phase 2: Encapsulating Layout and Icons into CardTable and GUICard Classes</u></h3>
<p>The second part creates a separate <strong>CardTable</strong> class that extends <strong> JFrame</strong>. This class will control the positioning of the panels and cards of the GUI. We also create a new <strong>GUICard</strong> class that manages the reading and building of the card image <strong>Icons</strong>. As a result, some of the machinery and statics that we debugged in the first phase of the main, <strong>Foothill</strong> class, will be moved into one or the other of these two new classes.</p>
<h4>CardTable Class (subclassed from JFrame)</h4>
<p>Include four members:</p>
<pre>   static int MAX_CARDS_PER_HAND = 57;
   static int MAX_PLAYERS = 2;  // for now, we only allow 2 person games
   
   private int numCardsPerHand;
   private int numPlayers;</pre>
<p>These members are needed is to establish the <strong>grid layout</strong> for the <strong>JPanels</strong>, the organization of which depends on how many cards and players will be displayed. We'll provide an accessor, but no mutator (other than the constructor) for these members.&nbsp; Here are the public instance methods:</p>
<ul>
<li>
<strong>CardTable(String title, int numCardsPerHand, int numPlayers) </strong>- The constructor filters input, adds any panels to the <strong>JFrame</strong>, and establishes layouts according to the general description below.</li>
<li>
<strong>Accessors</strong> for the two instance members.</li>
</ul>
<p><span style="color: #008000;"><strong>Suggestion</strong>: Use three <strong>JPanel</strong>s, one for each hand (player-bottom and computer-top) and a middle "playing" <strong>JPanel</strong>.&nbsp; The client (below) will generate the human's cards at random and those will be <i>visible</i> in the bottom <strong>JPanel</strong>, while the computer's cards will be chosen (again, by the client) to be all <i><strong>back-of-card</strong></i> images in the top <strong>JPanel</strong>.&nbsp; The middle <strong>JPanel</strong> will display cards that are "played" by the computer and human during the conflict.&nbsp; Let's assume that each player plays one card per round, so for a 2-person game (computer + human) there will be exactly two cards played in the central region<i> per round of battle</i>.&nbsp; My client chose a <i><strong>joker</strong></i> for the two central cards, just so we would have something to see in the playing region.&nbsp; No game is being played in this assignment, so the cards to be displayed in the center are immaterial.</span></p>
<h4>GUICard Class</h4>
<p>This class is the benefactor of most of the GUI machinery we tested in <i><strong>Phase 1</strong></i>. It will read the image files and store them in a static <strong>Icon</strong> array. Rather than a 1-D array of <i><strong>Phase 1</strong></i> (if you followed my earlier outline), this will be a 2-D array to facilitate addressing the value and suit of a <strong>Card</strong> in order get its <strong>Icon</strong>. While simple in principle (just read the <strong>Icons</strong> and store them in an array for client use), the details are subtle. We have to be able to convert from <strong>chars</strong> and <strong>suits</strong> to <strong>ints</strong>, and back again, in order to find the <strong>Icon</strong> for any given <strong>Card</strong> object. The overview of the class data and methods, shown below, will suggest the right approach and should take the mystery out of this class.</p>
<h5><span style="background-color: #ffff00;">Static Members</span></h5>
<blockquote>
<pre> private static Icon[][] iconCards = new ImageIcon[14][4]; // 14 = A thru K (+ joker optional)
 private static Icon iconBack;
 private static boolean iconsLoaded = false;
</pre>
</blockquote>
<p>The 52 (+ 4 jokers opptional) <strong>Icon</strong>s will be read and stored into the<strong> iconCards[][]</strong> array.&nbsp; The <i><strong>card-back</strong></i> image in the <strong>iconBack</strong> member.&nbsp; None of these data need to be stored more than once, so this is a class without instance data.&nbsp; This class is used is to produce an image icon when the client needs one.&nbsp;</p>
<h5><span style="background-color: #ffff00;">Helper Static Arrays</span></h5>
<pre>   private static String cardlValsConvertAssist = "23456789TJQKAX";
   private static String suitValsConvertAssist  = "CDHS";
   private static Card.Suit suitConvertAssist[] =
   {
      Card.Suit.clubs,
      Card.Suit.diamonds,
      Card.Suit.hearts,
      Card.Suit.spades
   };
</pre>
<p>The first two arrays are the ones we used in phase 1, but moved into this class.&nbsp; The third is a new array used to convert ints into actual enum Suits, as you'll see.</p>
<h5><span style="background-color: #ffff00;">Static Methods</span></h5>
<p>To begin, we need a method generates the image Icon array from files:</p>
<ul>
<li>
<strong>static void loadCardIcons()</strong> - the code for this was fundamentally done in <i><strong>Phase 1</strong></i>.&nbsp; The difference here is that we are storing the<strong> Icon</strong>s in a 2-D array.&nbsp; So you have to use a nested <i><strong>for-loop</strong></i>, suits and values, to generate the 2-D index for each <strong>Icon</strong> in the array. Another suggestion:&nbsp; I'd like you to not require the client to call this method.&nbsp; Think about where you would need to call it and how can you avoid having the method reload the icons after it has already loaded them once.&nbsp; The hint is in the <strong>static boolean iconsLoaded = false;</strong>, above.&nbsp; <i><span style="color: #008000;"><strong>Hint:&nbsp; </strong>Call this method any time you might need an <strong>Icon</strong>, but make sure that it loads the entire array the first time it is called, and does nothing any later time</span>.</i>
</li>
</ul>
<p>The use of <strong>loadCardIcons()</strong> should be clearer after you take a look at the primary public method offered by this class:</p>
<ul>
<li>
<strong>static public Icon getIcon(Card card) </strong>- This method takes a <strong>Card</strong> object from the client, and returns the <strong>Icon</strong> for that card.&nbsp; It would be used when the client needs to instantiate or change a <strong>JLabel</strong>.&nbsp; Here is what the <strong>getIcon()</strong> method definition might look like:
<pre>&nbsp;static public Icon getIcon(Card card)
 {
    loadCardIcons(); // will not load twice, so no worries.
    return iconCards[valueAsInt(card)][suitAsInt(card)];
 }</pre>
</li>
</ul>
<p>I just realized I wrote the entire method for you.&nbsp; Well, there are three method calls packed into this definition, and those, I did not write.&nbsp; Now you see where <strong>loadCardIcons()</strong> is called, and you see why we don't want it to do anything except <strong>return</strong> if the cards are already loaded.&nbsp; There is another method that returns the <i><strong>card-back</strong></i> image:</p>
<ul>
<li>
<strong>static public Icon getBackCardIcon()</strong> - this one is even simpler than <strong>getIcon()</strong>.</li>
</ul>
<p>The above three methods comprise the essential part of the <strong>GUICard</strong> class.&nbsp; Everything else is support for these three, so you can work off my implied suggestions, or you can build the class from scratch as you wish.&nbsp; Just make sure you are efficient.</p>
<h4>Main Foothill Client</h4>
<h5><span style="background-color: #ffff00;">Static Methods</span></h5>
<p>This class now only has the <strong>main()</strong> and one other static helper method:</p>
<ul>
<li>
<strong>generateRandomCard()</strong> - This method uses Java's Math.random() utility as a helper, and from it, generates a random card.&nbsp; We don't care if it generates the same card more than once - that's fine.&nbsp; There are some public helpers, now in the GUICard class, that will also be useful -- invoke them as needed to make this a short method:&nbsp; <strong> turnIntIntoSuit()</strong> and <strong>turnIntIntoCardValueChar()</strong>.</li>
</ul>
<h5><span style="background-color: #ffff00;">main() for Phase 2</span></h5>
<p>The main, <strong>Foothill</strong>, class needs to define the specific <strong>JLabel</strong> arrays that will go into each of <strong>CardTable</strong>'s <strong>JPanels</strong>. You will need <strong>NUM_CARDS_PER_HAND</strong> <strong>JLabels</strong> for the player and the computer (each), even though the computer only uses one <strong>Icon</strong> (<i><strong>back-of-card</strong></i>). We also want two <strong>Icon</strong> <strong>JLabels</strong> for the central <strong>JPanel</strong> (these are the two cards played by computer and human, each turn). But we also need to some <strong>text</strong> below each of the two center icons to we know who played which card (<strong>"Computer"</strong> or <strong>"You")</strong>, so, we'll really need <i>four</i> labels in this central play <strong>JPanel</strong> : two for card images and two for text<strong> "Computer"</strong> and <strong>"You"</strong>. Since we want the text directly below the icon, one way to do this is to make your central playing panel a <strong>2x2 Grid Layout</strong>, where the top two positions will be images and the bottom two will be text that describe the images. <span style="color: #008000;"><strong>Hint</strong>: to center text in a label, use </span></p>
<pre><span style="color: #008000;">   myLabel = new JLabel( "My Text", JLabel.CENTER ); </span></pre>
<p>The net result should be cards we can see (our hand) in the lower <strong>JPanel</strong> , cards that we can't see -- except for the card backs -- in the upper <strong>JPanel</strong> (the computer's hand) and a central playing region which would represent two cards, one each played by the user and the computer. These two cards depend on what game we are playing, the rules, and the goal.&nbsp; Based on these two cards played, either we or the computer win that round and then we go on to the next round. For this assignment, we don't worry about strategy or rules or winning -- we just want to see two cards in the central <strong>JPanel</strong> so we know they are correctly positioned for later program development.&nbsp; Here's a partial picture of a basic solution:</p>
<h3><img style="display: block; margin-left: auto; margin-right: auto; max-width: 492px;" src="http://www.fgamedia.org/faculty/loceff/cs_courses/common/assignment_pics/ass_5_shot_2.JPG" alt="" width="492" height="497"></h3>
<p>You job is to simply produce this output using the classes and methods suggested.&nbsp; Here is an idea for a <strong>main()</strong> that you can use to get started:</p>
<div>
<blockquote>
<pre>import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;


public class Foothill 
{
   static int NUM_CARDS_PER_HAND = 7;
   static int  NUM_PLAYERS = 2;
   static JLabel[] computerLabels = new JLabel[NUM_CARDS_PER_HAND];
   static JLabel[] humanLabels = new JLabel[NUM_CARDS_PER_HAND];  
   static JLabel[] playedCardLabels  = new JLabel[NUM_PLAYERS]; 
   static JLabel[] playLabelText  = new JLabel[NUM_PLAYERS]; 
   
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

      // CREATE LABELS ----------------------------------------------------
      code goes here ...
		
      // ADD LABELS TO PANELS -----------------------------------------
      code goes here ...
      
      // and two random cards in the play region (simulating a computer/hum ply)
      code goes here ...

      // show everything to the user
      myCardTable.setVisible(true);
   }</pre>
</blockquote>
</div>
<h4><span style="color: #0000ff;">Option B:&nbsp; More Players</span></h4>
<p>Hand in <span style="color: #ff0000;"><strong>Option A</strong></span>, <i>exactly as specified, above</i>.&nbsp; Next, you can add a second version that allows more than two players.&nbsp; This is going to be a challenge as it requires thinking about</p>
<ol>
<li>where to put all the hands,</li>
<li>which hands to reveal and which to hide from the user (your decision, based on the intent of the program, and</li>
<li>how to display the played-cards in the center of the table.</li>
</ol>
<p>Once your assignment is graded and returned, you can view the instructor solution here:</p>
<ul>
<li title="Click to insert a link to this quiz"><a href="/courses/3209/quizzes" data-api-endpoint="https://foothillcollege.instructure.com/api/v1/courses/3209/quizzes" data-api-returntype="[Quiz]"> Quiz List</a></li>
</ul>
<p>Your access code will be provided in your graded assignment comments.&nbsp; Find the assignment in the list, click "Take Survey" and you will see the solution.&nbsp; Even though it is called a "Quiz," it is actually just a solution; there is no need to submit anything, just open the quiz and see the solution.</p></div>


  <div style="display: none;">
    <span class="timestamp">1495141200</span>
    <span class="due_date_string">05/18/2017</span>
    <span class="due_time_string">02:00pm</span>
  </div>
</div>






  <div id="rubrics" style="margin-bottom: 10px;">
    <div style="display: none;" id="rubric_parameters">
      <input type="hidden" name="rubric_association[association_type]" value="Assignment">
      <input type="hidden" name="rubric_association[association_id]" value="54419">
      <input type="hidden" name="rubric_association[purpose]" value="grading">
    </div>
    
<div id="rubric_long_description_dialog" style="display: none;">
  <div class="editing">
    <form id="edit_criterion_form" class="no-margin-bottom">
      <div>
        <label class="rating_form_label">Description
          <textarea class="description" rows="1" name="description"></textarea>
        </label>
      </div>
      <div>
        <label class="rating_form_label">Long Description
          <textarea class="long_description" rows="4" name="long_description"></textarea>
        </label>
      </div>
      <div class="button-container">
        <button type="button" class="btn btn button-secondary cancel_button">Cancel</button>
        <button type="button" class="btn save_button btn-primary">Update Criterion</button>
      </div>
    </form>
  </div>
  <div class="displaying">
    <div class="long_description">
    </div>
  </div>
</div>
<div id="rubric_criterion_comments_dialog" style="display: none;">
  <div class="criterion_description" style="border-bottom: 1px solid #ccc; padding: 5px 0; font-size: 1.2em; font-weight: bold; margin-bottom: 5px;" tabindex="-1"></div>
  <div class="editing">
    <label for="criterion_comments_textarea">
      Additional Comments:
    </label>
    <textarea id="criterion_comments_textarea" class="criterion_comments" name="criterion_comments" style="width: 370px;"></textarea>
    <div class="button-container">
      <button type="button" class="btn btn button-secondary cancel_button">Cancel</button>
      <button type="button" class="btn save_button">Update Comments</button>
    </div>
  </div>
  <div class="displaying">
    Additional Comments:
    <div class="criterion_comments" style="margin-top: 10px;">
    </div>
  </div>
</div>
<div id="rubric_rating_dialog" style="display: none;">
  <div class="description" style="border-bottom: 1px solid #ccc; padding: 5px 0; font-size: 1.2em; font-weight: bold; margin-bottom: 5px;">
    <span id="edit_rating_form_criterion_description"></span>
  </div>
  <div class="editing">
    <form id="edit_rating_form" class="no-margin-bottom">
      <div class="toggle_for_hide_points">
        <div><label id="rating_form_score_label" class="rating_form_label">Rating Score</label></div>
        <span id="rating_form_max_score_label" hidden="">Rating max score</span>
        <input id="points" aria-labelledby="rating_form_score_label" type="text" size="2" name="points" class="no-margin-bottom span1">
        <span class="range_rating">to &gt;
          <input aria-label="Rating min score" type="text" size="2" name="min_points" class="no-margin-bottom span1 min_points" placeholder="min">
        </span>pts
      </div>
      <div>
        <label for="rating_form_title" class="rating_form_label">Rating Title</label>
        <input id="rating_form_title" type="text" class="rating_description" style="width: 90%;" name="description">
      </div>
      <div>
        <label for="rating_form_description" class="rating_form_label">Rating Description</label>
        <textarea id="rating_form_description" rows="4" style="width: 90%;" class="rating_long_description" name="rating_long_description" form="edit_rating_form"></textarea>
      </div>
      <div class="button-container">
        <button type="button" class="btn button-secondary cancel_button">Cancel</button>
        <button type="button" class="btn btn-primary save_button ok_button">Update Rating</button>
      </div>
    </form>
  </div>
</div>

  </div>
  
<div class="rubric_container rubric  " id="default_rubric" style="display: none;">
  <div class="screenreader-only"><h2>Rubric</h2></div>
  <div class="rubric_title">
    <div style="display: none;" class="links displaying pull-right">
      <a href="/courses/3209/rubrics/%7B%7B%20id%20%7D%7D" class=" edit_rubric_link no-print no-hover" style="" title="Edit Rubric" aria-label="Edit Rubric" role="button"><i class="icon-edit standalone-icon"></i></a>
      <a href="https://foothillcollege.instructure.com/search/rubrics?q=" class="find_rubric_link no-print no-hover" style="" title="Find Another Rubric" aria-label="Find Another Rubric" role="button"><i class="icon-search standalone-icon"></i></a>
        <a href="/courses/3209/rubric_associations/%7B%7B%20rubric_association_id%20%7D%7D" class="delete_rubric_link no-print no-hover" style="" title="Delete Rubric" aria-label="Delete Rubric" role="button"><i class="icon-trash standalone-icon"></i></a>
      <div style="display: none;">
        <div class="use_for_grading">&nbsp;</div>
        <div class="free_form_criterion_comments">&nbsp;</div>
        <div class="hide_score_total">&nbsp;</div>
        <div class="hide_outcome_results">&nbsp;</div>
        <div class="hide_points">&nbsp;</div>
        <div class="rubric_association_id">&nbsp;</div>
          <div class="user_id">&nbsp;</div>
        <div class="assessment_type"></div>
        <a href="/courses/3209/rubric_associations/%7B%7B%20rubric_association_id%20%7D%7D/assessments/%7B%7B%20assessment_id%20%7D%7D" rel="nofollow" class="edit_assessment_link">&nbsp;</a>
        <a href="/courses/3209/rubrics/%7B%7B%20rubric_id%20%7D%7D" class="edit_rubric_url">&nbsp;</a>
          <a href="/courses/3209/rubric_associations/%7B%7B%20association_id%20%7D%7D" class="delete_rubric_url">&nbsp;</a>
      </div>
    </div>
    <div style="float: right; font-size: 0.8em; display: none;" class="links displaying locked">
      <span style="">Can't change a rubric once you've started using it.</span>
        <a href="/courses/3209/rubric_associations/%7B%7B%20association_id%20%7D%7D" class="delete_rubric_url" style="display: none;">&nbsp;</a>
    </div>

    <div class="editing" style="float: right;">
      <a href="https://foothillcollege.instructure.com/search/rubrics?q=" class="find_rubric_link icon-search" style="" title="Find Existing Rubric">Find a Rubric</a>
    </div>
    <div class="editing" style="text-align: left">
      <label for="rubric-title">Title:</label>
      <input id="rubric-title" type="text" class="no-margin-bottom" name="title" value="Some Rubric" style="width: 200px;" maxlength="255" aria-label="Title:">
      <a href="https://foothillcollege.instructure.com/search/rubrics?q=" style="display: none;"><img alt="" src="https://du11hjcvx0uqb.cloudfront.net/br/dist/images/find-6164443e2a.png"> Find Rubric</a>
    </div>
    <div class="displaying">
      <span class="title" tabindex="-1">Title</span>
    </div>
    <div class="has-assessments-warning" style="display: none;">
      You've already rated students with this rubric.  Any major changes could affect their assessment results.
    </div>
  </div>
<table class="rubric_table">
<caption>
  <div class="screenreader-only">
    <span class="title">Title</span>
  </div>
</caption>
<thead>
  <tr>
    <th scope="col">Criteria</th>
    <th scope="col">Ratings</th>
    <th scope="col" class="toggle_for_hide_points ">
      Pts
    </th>
  </tr>
</thead>
<tbody>
  
<tr id="criterion_blank" class="criterion blank" style="display: none;">
  <td class="criterion_description hover-container pad-box-micro">
    <div class="container">
      <div class="links editing">
          <a href="#" class="edit_criterion_link"><i class="icon-edit standalone-icon"></i><span class="screenreader-only">Edit criterion description</span></a>
        <a href="#" class="delete_criterion_link"><i class="icon-trash standalone-icon"></i><span class="screenreader-only">Delete criterion row</span></a>
      </div>
      <div class="description_content">
        <span class="outcome_sr_content" aria-hidden="true">
          <i class="learning_outcome_flag icon-outcomes" aria-hidden="true"></i>
          <span class="screenreader-only">This criterion is linked to a Learning Outcome</span>
        </span>
        <span class="description description_title">Description of criterion</span>
        <span class="learning_outcome_id" style="display: none;"></span>
        <span class="criterion_id" style="display: none;"></span>
          <div class="long_description small_description"></div>
        <div class="hide_when_learning_outcome ">
          <div class="criterion_use_range_div editing toggle_for_hide_points ">
            <label>Range
              <input type="checkbox" class="criterion_use_range"></label>
          </div>
        </div>
        <div class="threshold toggle_for_hide_points ">
          threshold:
          <span class="mastery_points">5</span> pts
        </div>
      </div>

    </div>
  </td>
  <td style="padding: 0;">
      <table class="ratings" style=""><tbody><tr>
          <td id="rating_blank" class="rating edge_rating
                
                ">
            <div class="container">
              <div class="rating-main">
                  <div class="editing links">
                    <a href="#" class="edit_rating_link"><i class="icon-edit standalone-icon"></i><span class="screenreader-only">Edit rating</span></a>
                    <a href="#" class="delete_rating_link"><i class="icon-trash standalone-icon"></i><span class="screenreader-only">Delete rating</span></a>
                  </div>
                  <div class="clear"></div>
                <span class="nobr toggle_for_hide_points ">
                  <span class="points">5</span>
                  <span class="range_rating" style="display: none;">to &gt;<span class="min_points">0</span></span> pts
                </span>
                <div class="description rating_description_value">Full Marks</div>
                <div class="rating_long_description small_description"></div>
                <span class="rating_id" style="display: none;">blank</span>
              </div>
                <div class="editing links add_rating_link">
                  <a href="#" class="add_rating_link_after" aria-label="Add rating"><i class="icon-add icon-Solid"></i></a>
                </div>
            </div>
          </td>
          <td id="rating_blank_2" class="rating edge_rating
                infinitesimal
                ">
            <div class="container">
              <div class="rating-main">
                  <div class="editing links">
                    <a href="#" class="edit_rating_link"><i class="icon-edit standalone-icon"></i><span class="screenreader-only">Edit rating</span></a>
                    <a href="#" class="delete_rating_link"><i class="icon-trash standalone-icon"></i><span class="screenreader-only">Delete rating</span></a>
                  </div>
                  <div class="clear"></div>
                <span class="nobr toggle_for_hide_points ">
                  <span class="points">0</span>
                  <span class="range_rating" style="display: none;">to &gt;<span class="min_points">0</span></span> pts
                </span>
                <div class="description rating_description_value">No Marks</div>
                <div class="rating_long_description small_description"></div>
                <span class="rating_id" style="display: none;">blank_2</span>
              </div>
            </div>
          </td>
      </tr></tbody></table>
      <div style="display: none; font-size: 0.8em; margin: 5px;" class="custom_ratings">
        This area will be used by the assessor to leave comments related to this criterion.
      </div>
  </td>
  <td class="nobr points_form toggle_for_hide_points ">
    <div class="editing" style="white-space: normal">
      <span style="white-space: nowrap; font-size: 0.8em">
          
            <input type="text" aria-label="Points" value="5" class="criterion_points span1 no-margin-bottom">
           pts
      </span><br>
    </div>
    <div class="displaying">
      <span style="white-space: nowrap;">
        <span class="criterion_rating_points_holder" style="display: none;">
          <span class="criterion_rating_points">&nbsp;</span> /
        </span>
        <span class="display_criterion_points">5</span> pts<br>
      </span>
    </div>
    <div class="ignoring">
      <span> -- </span>
    </div>
    <div class="criterion_comments">
        <a href="#" class="no-hover criterion_comments_link" title="Additional Comments">
          <img alt="Additional Comments" src="https://du11hjcvx0uqb.cloudfront.net/br/dist/images/rubric_comment-ddae8546ab.png">
        </a>
        <div class="custom_rating" style="display: none;"></div>
    </div>
  </td>
</tr>

  <tr id="criterion_1" class="criterion" style="display: table-row;">
  <td class="criterion_description hover-container pad-box-micro">
    <div class="container">
      <div class="links editing">
          <a href="#" class="edit_criterion_link"><i class="icon-edit standalone-icon"></i><span class="screenreader-only">Edit criterion description</span></a>
        <a href="#" class="delete_criterion_link"><i class="icon-trash standalone-icon"></i><span class="screenreader-only">Delete criterion row</span></a>
      </div>
      <div class="description_content">
        <span class="outcome_sr_content" aria-hidden="true">
          <i class="learning_outcome_flag icon-outcomes" aria-hidden="true"></i>
          <span class="screenreader-only">This criterion is linked to a Learning Outcome</span>
        </span>
        <span class="description description_title">Description of criterion</span>
        <span class="learning_outcome_id" style="display: none;"></span>
        <span class="criterion_id" style="display: none;"></span>
          <div class="long_description small_description"></div>
        <div class="hide_when_learning_outcome ">
          <div class="criterion_use_range_div editing toggle_for_hide_points ">
            <label>Range
              <input type="checkbox" class="criterion_use_range"></label>
          </div>
        </div>
        <div class="threshold toggle_for_hide_points ">
          threshold:
          <span class="mastery_points">5</span> pts
        </div>
      </div>

    </div>
  </td>
  <td style="padding: 0;">
      <table class="ratings" style=""><tbody><tr>
          <td id="rating_blank" class="rating edge_rating
                
                ">
            <div class="container">
              <div class="rating-main">
                  <div class="editing links">
                    <a href="#" class="edit_rating_link"><i class="icon-edit standalone-icon"></i><span class="screenreader-only">Edit rating</span></a>
                    <a href="#" class="delete_rating_link"><i class="icon-trash standalone-icon"></i><span class="screenreader-only">Delete rating</span></a>
                  </div>
                  <div class="clear"></div>
                <span class="nobr toggle_for_hide_points ">
                  <span class="points">5</span>
                  <span class="range_rating" style="display: none;">to &gt;<span class="min_points">0</span></span> pts
                </span>
                <div class="description rating_description_value">Full Marks</div>
                <div class="rating_long_description small_description"></div>
                <span class="rating_id" style="display: none;">blank</span>
              </div>
                <div class="editing links add_rating_link">
                  <a href="#" class="add_rating_link_after" aria-label="Add rating"><i class="icon-add icon-Solid"></i></a>
                </div>
            </div>
          </td>
          <td id="rating_blank_2" class="rating edge_rating
                infinitesimal
                ">
            <div class="container">
              <div class="rating-main">
                  <div class="editing links">
                    <a href="#" class="edit_rating_link"><i class="icon-edit standalone-icon"></i><span class="screenreader-only">Edit rating</span></a>
                    <a href="#" class="delete_rating_link"><i class="icon-trash standalone-icon"></i><span class="screenreader-only">Delete rating</span></a>
                  </div>
                  <div class="clear"></div>
                <span class="nobr toggle_for_hide_points ">
                  <span class="points">0</span>
                  <span class="range_rating" style="display: none;">to &gt;<span class="min_points">0</span></span> pts
                </span>
                <div class="description rating_description_value">No Marks</div>
                <div class="rating_long_description small_description"></div>
                <span class="rating_id" style="display: none;">blank_2</span>
              </div>
            </div>
          </td>
      </tr></tbody></table>
      <div style="display: none; font-size: 0.8em; margin: 5px;" class="custom_ratings">
        This area will be used by the assessor to leave comments related to this criterion.
      </div>
  </td>
  <td class="nobr points_form toggle_for_hide_points ">
    <div class="editing" style="white-space: normal">
      <span style="white-space: nowrap; font-size: 0.8em">
          
            <input type="text" aria-label="Points" value="5" class="criterion_points span1 no-margin-bottom">
           pts
      </span><br>
    </div>
    <div class="displaying">
      <span style="white-space: nowrap;">
        <span class="criterion_rating_points_holder" style="display: none;">
          <span class="criterion_rating_points">&nbsp;</span> /
        </span>
        <span class="display_criterion_points">5</span> pts<br>
      </span>
    </div>
    <div class="ignoring">
      <span> -- </span>
    </div>
    <div class="criterion_comments">
        <a href="#" class="no-hover criterion_comments_link" title="Additional Comments">
          <img alt="Additional Comments" src="https://du11hjcvx0uqb.cloudfront.net/br/dist/images/rubric_comment-ddae8546ab.png">
        </a>
        <div class="custom_rating" style="display: none;"></div>
    </div>
  </td>
</tr><tr class="summary">
    <td colspan="4">
      <div class="total_points_holder toggle_for_hide_points " style="float: right; ">
        <span>Total Points:
            <span class="rubric_total">
              5
            </span>
 <span class="assessing">out of 5</span>        </span>
      </div>
      <div class="editing pull-left">
        <span id="add_criterion_holder" class="criterion_link"></span>
      </div>
      <div class="clear"></div>
    </td>
  </tr>
</tbody>
</table>
</div>
<table style="display: none;">
  <tbody><tr id="edit_rubric">
    <td colspan="4">
      <form id="edit_rubric_form" class="edit-rubric-form no-margin-bottom">
        <div class="rubric_custom_ratings" style="">
          <input type="checkbox" id="rubric_custom_rating" class="rubric_custom_rating">
          <label for="rubric_custom_rating">I'll write free-form comments when assessing students</label>
        </div>
          <div class="hide_points" style="">
            <input type="checkbox" id="hide_points" class="hide_points_checkbox">
            <label for="hide_points">Remove points from rubric</label>
          </div>
          <div class="hide_outcome_results" style="">
            <input type="checkbox" id="hide_outcome_results" class="hide_outcome_results_checkbox">
            <label for="hide_outcome_results">Don't post Outcomes results to Learning Mastery Gradebook</label>
          </div>
          <div class="rubric_grading" style="">
            <input type="checkbox" id="grading_rubric" class="grading_rubric_checkbox">
            <label for="grading_rubric">Use this rubric for assignment grading</label>
          </div>
        <div class="totalling_rubric" style="">
          <input type="checkbox" id="totalling_rubric" class="totalling_rubric_checkbox">
          <label for="totalling_rubric">Hide score total for assessment results</label>
        </div>
        <div class="ic-Action-header ic-Action-header--half-margin">
          <div class="ic-Action-header__Primary">
            <button type="button" class="Button cancel_button">Cancel</button>
            <button type="submit" class="Button Button--primary save_button">Create Rubric</button>
          </div>
        </div>
      </form>
    </td>
  </tr>
</tbody></table>


  <div id="assignment_external_tools"><div></div></div>

  
  <div id="sequence_footer" data-course-id="3209" data-asset-id="54419" data-asset-type="Assignment"><div class="module-sequence-padding"></div>
<div class="module-sequence-footer" role="navigation" aria-label="Module Navigation">
  <div class="module-sequence-footer-content">
    
      <span class="module-sequence-footer-button--previous" data-tooltip="right" data-html-tooltip-title="<i class='icon-link'></i> The Listing">
          <a href="https://foothillcollege.instructure.com/courses/3209/modules/items/147134" role="button" class="Button" aria-describedby="msf0-previous-desc" aria-label="Previous Module Item">
            <i class="icon-mini-arrow-left"></i>Previous
            <span id="msf0-previous-desc" class="hidden" hidden="">Previous: The Listing</span>
          </a>
      </span>
    

    
      <span class="module-sequence-footer-button--next" data-tooltip="left" data-html-tooltip-title="<strong style='float:left'>Next Module:</strong> <br> Week 6A - Applets and Further into GUI">
        <a href="https://foothillcollege.instructure.com/courses/3209/modules/items/147137" role="button" class="Button" aria-describedby="msf0-next-desc" aria-label="Next Module Item">
          Next<i class="icon-mini-arrow-right"></i>
          <span id="msf0-next-desc" class="hidden" hidden="">Next Module: Week 6A - Applets and Further into GUI</span>
        </a>
      </span>
    
  </div>
</div>



          </div>
