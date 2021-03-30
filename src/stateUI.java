import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.SwingUtilities;


public class stateUI extends JComponent {

  private volatile int screenX = 0;
  private volatile int screenY = 0;
  private volatile int myX = 0;
  private volatile int myY = 0;
  private state s;
  private pushdown p;
  
  //private int mouseMode;
  //private String firstState;

  public stateUI(state s, pushdown p) {
	  this.s = s;
	  this.p = p;
	  p.setFirstClick(true);
	  setToolTipText(s.getName());
	  
	  
    //setBorder(new LineBorder(Color.BLACK, 3));
    setBackground(Color.WHITE);
    setBounds(0, 0, 80, 80);
    setOpaque(false);
    
    addMouseListener(new MouseListener() {

      @Override
      public void mouseClicked(MouseEvent e) {
    	  //System.out.println("Button clicked");
    	  
    	  if(SwingUtilities.isRightMouseButton(e)) {
    		  s.setEnd(!s.getEnd());
    		  p.updateAccepting();
    		  return;
    	  }
    	  
    	  //Mouse modes:
    	  //1 is add transition, 2 is start state select
    	  if(p.getMouseMode() == 1 && p.getFirstClick()) {
    		  //firstState = s.getName();
    		  p.setFirstState(s.getName());
    		  p.setFirstClick(false);
    	  }
    	  else if(p.getMouseMode() == 1 && !p.getFirstClick()) {
    		  //String symbol = JOptionPane.showInputDialog("Enter the transition symbol, start stack symbol and end stack symbol(s) separeted by a space: ");
    		  //p.addTransition(firstState, TOOL_TIP_TEXT_KEY, TOOL_TIP_TEXT_KEY, firstState, null);
    		 
    		  
    		  p.setMouseMode(0);
    		  p.setFirstClick(true);
    		  
    		  JTextField xField = new JTextField(5);
    	      JTextField yField = new JTextField(5);
    	      JTextField zField = new JTextField(5);

    	      JPanel myPanel = new JPanel();
    	      myPanel.add(new JLabel("Transtion Symbol:"));
    	      myPanel.add(xField);
    	      myPanel.add(Box.createHorizontalStrut(15)); // a spacer
    	      myPanel.add(new JLabel("Start stack state:"));
    	      myPanel.add(yField);
    	      myPanel.add(Box.createHorizontalStrut(15)); 
    	      myPanel.add(new JLabel("End stack state:"));
    	      myPanel.add(zField);
    	     

    	      int result = JOptionPane.showConfirmDialog(null, myPanel, "New Transition", JOptionPane.OK_CANCEL_OPTION);
    	      if (result == JOptionPane.OK_OPTION) {
    	         //System.out.println("x value: " + xField.getText());
    	         //System.out.println("y value: " + yField.getText());
    	         //System.out.println("z value: " + zField.getText());
    	    	  String z = zField.getText();
    	    	  
    	    	 //Alphabet validation
    	    	 if(!p.inputAlphabet.contains(xField.getText())) {
    	    		 JOptionPane.showMessageDialog(null, "The input \"" + xField.getText() + "\" does not belong to the input alphabet");
    	    		 return;
    	    	 }
    	    	 else if(!p.stackAlphabet.contains(yField.getText())) {
    	    		 JOptionPane.showMessageDialog(null, "The input \"" + yField.getText() + "\" does not belong to the stack alphabet");
    	    		 return;
    	    	 }
    	    	 else if(z.length() == 1) {
    	    		 if(!p.stackAlphabet.contains(String.valueOf(z.toCharArray()[0]))) {
    	    			 JOptionPane.showMessageDialog(null, "The input \"" + zField.getText() + "\" does not belong to the stack alphabet");
    	    			 return;
    	    		 }
    	    	 }
    	    	 else if(z.length() == 2) {
    	    		 if(!p.stackAlphabet.contains(String.valueOf(z.toCharArray()[0])) && !p.stackAlphabet.contains(String.valueOf(z.toCharArray()[1]))) {
    	    			 JOptionPane.showMessageDialog(null, "The input \"" + zField.getText() + "\" does not belong to the stack alphabet");
    	    			 return;
    	    		 }
    	    	 }
    	    	 else if(z.length() > 2) {
    	    		 JOptionPane.showMessageDialog(null, "The end stack state cannot be more than 2 characters");
    	    		 return;
    	    	 }
    	    	 
    	    	  
    	    	  
    	         //last one needs conditions for 1 2 or 0 elements in list
    	         //firstState needs to be moved into pushdown and given getters and setters. **done**
    	         //String z = zField.getText();
    	         if(z.length() == 0) {
    	        	 String temp[] = {};
    	        	 p.addTransition(p.getFirstState(), xField.getText(), yField.getText(), s.getName(), temp);
    	         }
    	         else if(z.length() == 1){
    	        	 String temp[] = {z};
    	        	 p.addTransition(p.getFirstState(), xField.getText(), yField.getText(), s.getName(), temp);
    	         }
    	         else if(z.length() == 2){
    	        	 String temp[] = {Character.toString(z.toCharArray()[0]),Character.toString(z.toCharArray()[1])};
    	        	 p.addTransition(p.getFirstState(), xField.getText(), yField.getText(), s.getName(), temp);
    	         }
    	         else {
    	        	 //More than 2 chars for the end stack 
    	        	 System.out.println("Error need more validation here");
    	         }
    	         
    	         //System.out.println("firstState" + p.getFirstState());
    	         //System.out.println("endState" + s.getName());
    	         //p.addTransition(firstState, xField.getText(), yField.getText(), s.getName(), null);
    	      }
    	  }
    	  else if(p.getMouseMode() == 2) {
    		  //System.out.println("New start state");
    		  //p.removeState(s.getName());
    		  p.setStartState(s.getName());
    		  p.setMouseMode(0);
    	  }
      }

      @Override
      public void mousePressed(MouseEvent e) {
        screenX = e.getXOnScreen();
        screenY = e.getYOnScreen();

        myX = getX();
        myY = getY();
      }

      @Override
      public void mouseReleased(MouseEvent e) { }

      @Override
      public void mouseEntered(MouseEvent e) { }

      @Override
      public void mouseExited(MouseEvent e) { }

    });
    addMouseMotionListener(new MouseMotionListener() {

      @Override
      public void mouseDragged(MouseEvent e) {
        int deltaX = e.getXOnScreen() - screenX;
        int deltaY = e.getYOnScreen() - screenY;

        setLocation(myX + deltaX, myY + deltaY);
      }

      @Override
      public void mouseMoved(MouseEvent e) { }

    });
    
  }
  
  public state getState() {
	  return s;
  }
  
  @Override
  public void paintComponent(Graphics g) {
      super.paintComponent(g);
      //g.fillOval(0,0,70,70);
      //g.setPaintMode();
      //g.drawOval(0, 0, 70, 70);
      //paint code goes here of whatever
      Graphics2D g2 =(Graphics2D)g;
      g2.setStroke(new BasicStroke(3));
      g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
      
      if(!s.getName().equals("dead")) {
	      //Drawing circle
	      g2.drawOval(0, 0, 70, 70);
	      
	      
	      //Drawing final states
	      if(s.getEnd()) {
	    	  //System.out.println("drawing circles");
	    	  g2.drawOval(5, 5, 60, 60);
	      }
	      
	      //Drawing initial state
	      if(s.getStart()) {   	  
	    	  int[] xCoords = {10, 0, 10};
	    	  int[] yCoords = {10, 10, 0};
	    	  //g2.drawPolygon(xCoords, yCoords, 3);
	    	  g2.fillPolygon(xCoords, yCoords, 3);
	      }
      }
      
  }
  


}