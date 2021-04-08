import java.awt.BasicStroke;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JPanel;

public class textPanel extends JPanel{

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		g2.setStroke(new BasicStroke(2));
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		
		g2.setFont(new Font("TimesRoman", Font.PLAIN, 30));
		g2.drawString("Instructions: ", 50,  50);
		
		g2.setFont(new Font("TimesRoman", Font.PLAIN, 20));
		g2.drawString("General Information ", 50,  100);
		g2.drawString("The Simulator", 50,  300);
		g2.drawString("Game 1", 50,  500);
		
		
		g2.setFont(new Font("TimesRoman", Font.PLAIN, 16));
		
		g2.drawString("- All elements of this tool are designed for deterministic pushdown automaton", 50,  120);
		g2.drawString("- On the right hand side of each screen the automata are displayed", 50,  140);
		g2.drawString("- You can always you left click to drag and drop the different states of the automata around", 50,  160);
		g2.drawString("- Lowercase e represents the empty string or pushing nothing onto the stack. To test the empty string simply input e ", 50,  180);
		
		g2.drawString("- The simulator is where you can create your own automaton and test different inputs on them", 50,  320);
		g2.drawString("- Make sure that the automaton that you create here are deterministic pushdown automaton", 50,  340);
		g2.drawString("- To update either of the alphabets, type in all of the characters you want to be in the alphabet, without spaces inbetween, then press enter", 50,  360);
		g2.drawString("- For the bulk input box, type each input on a separate line and click the test button next to it to execute ", 50,  380);
		g2.drawString("- To add or remove a state from the set of accepting states, right click on the state you wish to change on the right hand side of the screen ", 50,  400);
		g2.drawString("- To add a transition click the add transtion button and then click the starting transition, followed by the end transition, a popup will appear to enter the remainder of information", 50,  420);
		g2.drawString("- The step button is used for the input in the top input box, and it will step through the input on the right hand screen, showing the chosen transition and stack at each point after being clicked", 50,  440);
		
		g2.drawString("- Game 1 is a place to test your understanding of some basic deterministic pushdown automaton", 50,  520);
		g2.drawString("- You do this by selecting a level which will show an automaton on the right hand side of the screen. Then click start game ", 50,  540);
		g2.drawString("- You will then have 60 seconds to input and test as many different inputs as possible that are accepted by the automaton shown", 50,  560);
		g2.drawString("- The correct answers box will fill with all of the inputs that you guess correctly so you cannot use these again", 50,  580);
		g2.drawString("- At any time you can click on a different level or the start button again to try a different level or repeat one multiple times", 50,  600);
	}
}
