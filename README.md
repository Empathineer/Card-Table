# Card-Table GUI
            


<h3><u>Objective</u></h3>
<p>The purpose of this assignment was to transition <strong>Card</strong> classes from the realm of console apps to that of GUI apps by implementing JFrame's of Card icons.&nbsp; This was accomplished per the following stages:</p>
<ol>
<li>
<strong>Read and display Card pictures</strong> - Read <strong>.gif</strong> image files as <strong>Icons</strong>, and attach them to <strong>JLabels</strong> that we can display on a <strong>JFrame</strong>.</li>
<li>
<strong>Encapsulate the Card Icons in a class GUICard</strong> - Once we debug imagery for cards, above, we can move it into its own class, <strong>GUICard</strong>.</li>
<li>
<strong>Create a CardTable class </strong>- This <strong>JFrame</strong> class will embody the <strong>JPanels</strong> and <strong>Layout</strong>(s) needed for our application. This is where all the cards and controls will be placed. (It plays the same role as class <strong>Calculator</strong> of your RPN lecture.)</li>
</ol>
<p>The first phase (item 1) set the groundwork  for debugging as it achieved reading <strong>.gif </strong>files and displaying them on a <strong>JFrame</strong> without any excess logic or class complexity.&nbsp; The second phase (items 2 and 3 from above) converts the first phase into a multi-class project.</p>

<h3><u>Project Materials & Steup</u></h3>

<ol>
<li><u>Download the Card .gifs</u>. The GNU Free Software Foundation provides code and images for playing-cards that are public domain.&nbsp; A prepared<strong>.zip</strong> file containing the<strong> .gif </strong>for every card with some minor name changes were downloaded from here:&nbsp;</li>
<p><a href="http://www.fgamedia.org/faculty/loceff/cs_courses/cs_1b/resources/images_for_card_assignment.zip" target="_blank" class="external" rel="noreferrer noopener"><span>Card Images Download</span><span aria-hidden="true" class="ui-icon ui-icon-extlink ui-icon-inline" title="Links to an external site."></span><span class="screenreader-only">&nbsp;(Links to an external site.)</span></a>.</p>
<p><li>The unzipped folder called <strong>images</strong> contains all the <strong>.gif </strong>files.&nbsp; Move that folder to your <strong>[java workspace]/[project name]</strong> directory.&nbsp; If your project is named <strong> Assignment_5</strong>, and your Eclipse workspace is named <strong>workspace</strong>, then move images folder to <strong>workspace/Assignment_5</strong>.&nbsp; Since your program considers <strong>Assignment_5</strong> to be the root directory, all <strong>.gif</strong> files can be referenced from your program using names like <strong> "images/3S.gif"</strong> for, say, <i><strong>3 of spades</strong></i>.</li></p>

<p><li>In addition to the <strong>52 standard cards</strong>, there are <strong>four jokers</strong> and a <strong>card-back</strong> image which can be used to display dealer or other player cards that you don't want the user to see yet.</li></p>
</ol>

<p><img src="http://www.fgamedia.org/faculty/loceff/cs_courses/common/assignment_pics/JH.gif" alt="" width="73" height="97" style="max-width: 73px;">&nbsp; <img src="http://www.fgamedia.org/faculty/loceff/cs_courses/common/assignment_pics/4C.gif" alt="" width="73" height="97" style="max-width: 73px;">&nbsp; <img src="http://www.fgamedia.org/faculty/loceff/cs_courses/common/assignment_pics/BK.gif" alt="" width="73" height="97" style="max-width: 73px;">&nbsp; <img src="http://www.fgamedia.org/faculty/loceff/cs_courses/common/assignment_pics/AS.gif" alt="" width="73" height="97" style="max-width: 73px;">&nbsp; <img src="http://www.fgamedia.org/faculty/loceff/cs_courses/common/assignment_pics/XD.gif" alt="" width="73" height="97" style="max-width: 73px;"></p>
<p>(The card on the far right is <i><strong>joker 2</strong></i>, not a jack.)</p>
<h3><u>Phase 1: Reading and Displaying the .gif Files</u></h3>
<p>Previously, we instantiated an <strong>Icon</strong> object to represent any<strong> .gif</strong>, <strong>.jpg</strong> or other image file on your disk and then place that <strong>Icon</strong> on a <strong>JLabel</strong>.&nbsp; Now in <strong><i>P</i></strong><i><strong>hase 1</strong></i>, an array of 57 <strong>JLabels</strong> has been created followed by attaching the<strong> 57 .gif files</strong> to them. The labels are subsequently displayed, unstructured, in a single <strong>JFrame</strong>.&nbsp; </p>

<h4>Client Side Example</h4>

<p><img style="display: block; margin-left: auto; margin-right: auto; max-width: 394px;" src="http://www.fgamedia.org/faculty/loceff/cs_courses/common/assignment_pics/ass_5_shot_1.JPG" alt="" width="394" height="437"></p>
<h3><u>Phase 2: Encapsulating Layout and Icons into CardTable and GUICard Classes</u></h3>
<p>The second part creates a separate <strong>CardTable</strong> class that extends <strong> JFrame</strong>. This class will control the positioning of the panels and cards of the GUI. We also create a new <strong>GUICard</strong> class that manages the reading and building of the card image <strong>Icons</strong>. As a result, some of the machinery and statics that we debugged in the first phase of the main, <strong>Foothill</strong> class, will be moved into one or the other of these two new classes.</p>
<h4>CardTable Class (subclassed from JFrame)</h4>
<p>This contains four members:</p>
<pre>static int MAX_CARDS_PER_HAND = 57;
   static int MAX_PLAYERS = 2;  // limit to 2 players for beta version
   
   private int numCardsPerHand;
   private int numPlayers;</pre>
<p>These members are needed is to establish the <strong>grid layout</strong> for the <strong>JPanels</strong>, the organization of which depends on how many cards and players will be displayed. We'll provide an accessor, but no mutator (other than the constructor) for these members.&nbsp; Here are the public instance methods:</p>
<ul>
<li>
<strong>CardTable(String title, int numCardsPerHand, int numPlayers) </strong>- The constructor filters input, adds any panels to the <strong>JFrame</strong>, and establishes layouts according to the general description below.</li>
<li>
<strong>Accessors</strong> for the two instance members.</li>
</ul>

<h4>GUICard Class</h4>
<p>This class is the benefactor of most of the GUI machinery we tested in <i><strong>Phase 1</strong></i>. It will read the image files and store them in a static <strong>Icon</strong> array. In place of the 1-D array from <i><strong>Phase 1</strong></i>, this is a 2-D array to facilitate addressing the value and suit of a <strong>Card</strong> in order get its <strong>Icon</strong>. While simple in principle (just read the <strong>Icons</strong> and store them in an array for client use), this task required being able to convert from <strong>chars</strong> and <strong>suits</strong> to <strong>ints</strong>, and back again in order to match the <strong>Icon</strong> for any given <strong>Card</strong> object. </p>
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

<h5><span style="background-color: #ffff00;">Static Methods</span></h5>
<p>We needed a method to generate the image Icon array from files:</p>
<ul>
<li>
<strong>static void loadCardIcons()</strong> -The brunt of this code was achieved in <i><strong>Phase 1</strong></i>.&nbsp; The subtle difference here is that the<strong> Icon</strong>s was stored in a 2-D array.&nbsp; 
</li>
</ul>
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

<ul>
<li>
<strong>static public Icon getBackCardIcon()</strong> - this is a reduced implementationthan of<strong>getIcon()</strong>.</li>
</ul>

<h4>Main Foothill Client</h4>
<h5><span style="background-color: #ffff00;">Static Methods</span></h5>
<p>This class now only has the <strong>main()</strong> and one other static helper method:</p>
<ul>
<li>
<strong>generateRandomCard()</strong> - This method uses Java's Math.random() utility as a helper, and from it, generates a random card.&nbsp; We don't care if it generates the same card more than once - that's fine.&nbsp; There are some public helpers, now in the GUICard class, that will also be useful -- invoke them as needed to make this a short method:&nbsp; <strong> turnIntIntoSuit()</strong> and <strong>turnIntIntoCardValueChar()</strong>.</li>
</ul>
<h5><span style="background-color: #ffff00;">main() for Phase 2</span></h5>
<p>The main class defines the specific <strong>JLabel</strong> arrays that will go into each of <strong>CardTable</strong>'s <strong>JPanels</strong>. <strong>NUM_CARDS_PER_HAND</strong> <strong>JLabels</strong> is required for the player and the computer (each), even though the computer only uses one <strong>Icon</strong> (<i><strong>back-of-card</strong></i>). Two <strong>Icon</strong> <strong>JLabels</strong> are displayed on the central <strong>JPanel</strong> (these are the two cards played by computer and human, each turn). additionally, <strong>text</strong> below each of the two center icons was provided to know who played which card (<strong>"Computer"</strong> or <strong>"You")</strong>. In order to format the text directly below the icon, the central playing panel was rendered as a <strong>2x2 Grid Layout</strong>, where the top two positions will be images and the bottom two will be text that describe the images. <span style="color: #008000;"><strong>Hint</strong>: to center text in a label, use </span></p>
<pre><span style="color: #008000;">   myLabel = new JLabel( "My Text", JLabel.CENTER ); </span></pre>
<p>The final result produced cards from the player's hand in the lower <strong>JPanel</strong> and cards "turned over" in the upper <strong>JPanel</strong> representing the computer's hand. Finally, there is a central playing region displaying one card played by the user and the computer. These two cards depend on what game we are playing, the rules, and the goal.&nbsp; Based on these two cards played, either we or the computer win that round and then we go on to the next round. For this assignment's purpose, no elaborate game mechanics were implemented. It was mainly focused on constructing the GUI via <strong>JPanel</strong> </p>

<h5><span style="background-color: #ffff00;">Sample Client Side</span></h5>
<h3><img style="display: block; margin-left: auto; margin-right: auto; max-width: 492px;" src="http://www.fgamedia.org/faculty/loceff/cs_courses/common/assignment_pics/ass_5_shot_2.JPG" alt="" width="492" height="497"></h3>



