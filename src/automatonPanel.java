import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.*;

public class automatonPanel extends JPanel{
	pushdown a;
	stateUI[] stateUIList;
	
	int gameScore;
	boolean game;
	String highlight = "";
	
	String stackShown = null;
	
	public automatonPanel(pushdown a, boolean g) {
		this.a = a;
		init();
		game = g;
	}
	
	public void init() {
		stateUIList = new stateUI[a.states.length];
		
		for(int i = 0; i < a.states.length; i++) {
			stateUIList[i] = new stateUI(a.states[i], a);
			add(stateUIList[i]);
			
		}
	}
	
	public void addState(state x) {
		stateUI[] temp = new stateUI[stateUIList.length + 1];
		for(int i = 0; i < stateUIList.length; i++) {
			temp[i] = stateUIList[i];
		}
		
		stateUI tempUI = new stateUI(x, a);
		temp[stateUIList.length] = tempUI;
		stateUIList = temp;
		add(tempUI);
		
	}
	
	public void removeState(String s) {
		//stateUI[] temp = new stateUI[stateUIList.length - 1];
		for(int i = 0; i < stateUIList.length; i++) {
			if(stateUIList[i].getState().getName().equals(s)) {
				removeFromStatesUI(i);
			}
		}
		
		//stateUIList = temp;
		//updateUI();
	}
	
	private void removeFromStatesUI(int n) {
		stateUI[] temp = new stateUI[stateUIList.length-1];
		
		for(int i = 0, j = 0; i < stateUIList.length; i++) {
			if(i != n) {
				temp[j++] = stateUIList[i];
			}
			else {
				remove(stateUIList[i]);
			}
		}
		stateUIList = temp;	
		//updateUI();
	}
	
	public void removeAllStateUI() {
//		for(int i = 0; i < stateUIList.length; i++) {
//			//stateUIList[i]
//		}
		removeAll();
	}
	
	public void removeSingleStateUI(String name) {
		for(int i = 0; i < stateUIList.length; i++) {
			if(stateUIList[i].getState().getName().equals(name)) {
				remove(stateUIList[i]);
			}
		}
	}
	
	public void increaseGameScore() {
		gameScore++;
	}
	
	public int getGameScore() {
		return gameScore;
	}
	
	public void resetGameScore() {
		gameScore = 0;
	}
	
	
	private int[] drawTransition(transition t) {
		//x1 y1 x2 y2
		int[] temp = new int[4];
		//transition t = a.transitions[i1];
		
		//get start UI and end UI send coords back
		for(int i = 0; i < stateUIList.length; i++) {
			if(stateUIList[i].getState().getName().equals(t.getStart().getName())) {
				//start state found
				temp[0] = stateUIList[i].getX();
				temp[1] = stateUIList[i].getY();
			}
			if(stateUIList[i].getState().getName().equals(t.getEnd().getName())) {
				//end state found
				temp[2] = stateUIList[i].getX();
				temp[3] = stateUIList[i].getY();
						
			}
		}
		
		return temp;
	}
	
	private void drawArrowLine(Graphics g, int x1, int y1, int x2, int y2, int d, int h) {
	    int dx = x2 - x1, dy = y2 - y1;
	    double D = Math.sqrt(dx*dx + dy*dy);
	    double xm = D - d, xn = xm, ym = h, yn = -h, x;
	    double sin = dy / D, cos = dx / D;

	    x = xm*cos - ym*sin + x1;
	    ym = xm*sin + ym*cos + y1;
	    xm = x;

	    x = xn*cos - yn*sin + x1;
	    yn = xn*sin + yn*cos + y1;
	    xn = x;

	    int[] xpoints = {x2, (int) xm, (int) xn};
	    int[] ypoints = {y2, (int) ym, (int) yn};

	    g.drawLine(x1, y1, x2, y2);
	    g.fillPolygon(xpoints, ypoints, 3);
	}
	
	private void labelTransition(Graphics g, int x1, int y1, int x2, int y2, transition t) {
		int midX = (x1 + x2)/2;
		int midY = (y1 + y2)/2;
		
		g.setFont(new Font("TimesRoman", Font.PLAIN, 18));
		g.drawString(t.getString(), midX, midY);
	}
	
	private void labelTransitionAtPoint(Graphics g, int x, int y, transition t) {
		g.setFont(new Font("TimesRoman", Font.PLAIN, 18));
		g.drawString(t.getString(), x, y);
	}
	
	private void labelSelfTransition(Graphics g, int x, int y, transition t) {
		g.setFont(new Font("TimesRoman", Font.PLAIN, 18));
		g.drawString(t.getString(), x-200, y + 35);
	}
	
	private int getTransitionMultiplier(int i) {
		String startName = a.transitions[i].getStart().getName();
		String endName = a.transitions[i].getEnd().getName();
		int num = 0;
		
		for(int x = 0; x < a.transitions.length; x++) {
			if(i == x) {
				break;
			}
			else if(a.transitions[x].getStart().getName().equals(startName) && a.transitions[x].getEnd().getName().equals(endName)) {
				num++;
			}
		}
		
		return num;
	}
	
	private boolean isOppositeTransition(int i) {
		String startName = a.transitions[i].getStart().getName();
		String endName = a.transitions[i].getEnd().getName();
		boolean b = false;
		
		for(int x = 0; x < a.transitions.length; x++) {
			if(i == x) {
				break;
			}
			else if(a.transitions[x].getStart().getName().equals(endName) && a.transitions[x].getEnd().getName().equals(startName)) {
				b = true;
			}
		}
		
		
		return b;
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		g2.setStroke(new BasicStroke(2));
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		
		if(game) {
			g2.setFont(new Font("TimesRoman", Font.PLAIN, 30));
			g2.drawString("Score: " + String.valueOf(gameScore), 50,  50);
			
			//g2.drawString("Timer:" + 10, 200, 50);
		}
		
		if(stackShown != null) {
			g2.setFont(new Font("TimesRoman", Font.PLAIN, 30));
			g2.drawString("Stack: " + a.stack, 50,  50);
		}
		//g2.drawLine(mc.getX() + 35, mc.getY() + 35, mc1.getX() + 35, mc1.getY() + 35);
		
		//for each transition
		for(int i = 0; i < a.transitions.length; i++) {
			//Highlight color setting
			if(highlight.equals(a.transitions[i].getString())) {
				g2.setColor(Color.RED);
			}
			else {
				g2.setColor(Color.BLACK);
			}
			
			int[] temp = new int[4];
			temp = drawTransition(a.transitions[i]);
			if(temp[0] == temp[2] && temp[1] == temp[3]) {
				//transition that returns to the same state
				g2.drawArc(temp[0] - 40, temp[1] + 10, 50, 50, 45, 270);
				labelSelfTransition(g, temp[0], temp[1] + (20 * getTransitionMultiplier(i)), a.transitions[i]);
				
				int x = 4;
				int y = 50;
				
				int xCoords[] = {temp[0] + x, temp[0] + x, temp[0] -10 + x};
				int yCoords[] = {temp[1] + y, temp[1] + 10 + y, temp[1] + y};
				
				
				g2.fillPolygon(xCoords, yCoords, 3);
			}
			else {
				if(isOppositeTransition(i)) {
					int midX = (temp[0] + temp[2])/2;
					int midY = (temp[1] + temp[3])/2;
					int xOffset = 200;
					g.drawLine(temp[0] + 35, temp[1] + 35, midX + xOffset, midY);
					drawArrowLine(g, midX + xOffset, midY, temp[2] + 35, temp[3] + 35, 10, 5);
					labelTransitionAtPoint(g, midX + xOffset, midY + (20 * getTransitionMultiplier(i)), a.transitions[i]);
				}
				else {
					drawArrowLine(g, temp[0] + 35, temp[1] + 35, temp[2] + 35, temp[3] + 35, 10, 5);
					labelTransition(g, temp[0] + 35, temp[1] + 35 + (40 * getTransitionMultiplier(i)), temp[2] + 35, temp[3] + 35, a.transitions[i]);
				}
				//g2.drawLine(temp[0] + 35, temp[1] + 35, temp[2] + 35, temp[3] + 35);
			}
			
		}
		
		
		updateUI();
		
	}
	
	private int[] getLine(int x1, int x2, int y1, int y2) {
		int[] temp = new int[4];
		double m;
		if(x1 - x2 == 0) {
			m = 0;
		}
		else {
			m = (double) (y1 - y2)/(x1 - x2);
		}
		int c = (int) Math.round(y1 - (m*x1));
		
		temp = getLines(m, c, x1, y1, 35, x2, y2);
		
		
		return temp;
	}
	
	private int[] getLines(double m, int c, int p1, int q1, int r, int p2, int q2) {
		
		return getCoords(getIntersect(m, c, p1, q1, r), getIntersect(m, c, p2, q2, r));
	}
	
	private double[] getIntersect(double m, int c, int p, int q, int r) {
		double[] temp = new double[4];
		double A = (m*m)+1;
		double B = 2*(m*c-m*q-p);
		double C = (q*q-r*r+p*p-2*c*q+c*c);
		temp[0] = (-B+Math.sqrt(B*B-4*A*C))/2*A;
		temp[1] = (-B-Math.sqrt(B*B-4*A*C))/2*A;
		
		temp[2] = m*temp[0]+c;
		temp[3] = m*temp[1]+c;
		
		return temp;
	}
	
	private int[] getCoords(double[] a, double[] b) {
		int[] temp = new int[4];
		
		if((a[0] - b[0]) + (a[2] - b[2]) < (a[1] - b[1]) + (a[3] - b[3])) {
			temp[0] = (int) Math.round(a[0]);
			temp[1] = (int) Math.round(a[2]);
			temp[2] = (int) Math.round(b[0]);
			temp[3] = (int) Math.round(b[2]);
		}
		else {
			temp[0] = (int) Math.round(a[1]);
			temp[1] = (int) Math.round(a[3]);
			temp[2] = (int) Math.round(b[1]);
			temp[3] = (int) Math.round(b[3]);
		}
		
		return temp;
	}
	
	public pushdown getAutomaton() {
		return a;
	}
	
	public void setAutomaton(pushdown push) {
		a = push;
		removeAllStateUI();
		init();
	}
	
	public stateUI[] getUIList() {
		return stateUIList;
	}
	
	public void highlightTransition(String s) {
		highlight = s;
	}
	
	public void removeHighlight() {
		highlight = "";
	}
	
	public void hideStack() {
		stackShown = null;
	}
	
	public void showStack(String s) {
		stackShown = s;
	}
	
}
