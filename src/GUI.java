import javax.swing.*;

import javax.swing.GroupLayout.Alignment;
import javax.swing.event.PopupMenuEvent;
import javax.swing.event.PopupMenuListener;

import java.awt.BasicStroke;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.Math;
import java.util.ArrayList;

public class GUI extends JFrame{
	automatonPanel mainAutomatonPanel;
	automatonPanel game1AutomatonPanel;
	
	Timer timer;
	Timer stepTimer;
	int count = 60;
	ArrayList<String> used;
	
	ArrayList<String> transitionsStepped;
	ArrayList<String> stackStep;
	String stepInput;
	//int stepCounter = 0;
	
	public GUI() {
		
		initUI();
	}
	
	public void initUI() {		
		
		var tabbedPane = new JTabbedPane();
		mainAutomatonPanel = getAutomatonPanel();
		game1AutomatonPanel = getGame1AutomatonPanel();
		
		tabbedPane.add("Simulator", createSplitPanel(getControlsPanel(), mainAutomatonPanel));
        tabbedPane.add("Game 1", createSplitPanel(getGame1ControlPanel(), game1AutomatonPanel));
        //tabbedPane.add("Game 2", createSplitPanel(new JPanel(), new JPanel()));
        //tabbedPane.add("Game 3", createSplitPanel(new JPanel(), new JPanel()));
        tabbedPane.addTab("Instructions", createPanel("Instructions"));
        
        createLayout(tabbedPane);
		
		setTitle("Automaton Simulator");
		setSize(1440, 810);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	private JPanel createPanel(String text) {
		var panel = new JPanel();
        var lbl = new JLabel(text);
        panel.add(lbl);

        return panel;
	}
	
	//make arguments left and right panes
	private JSplitPane createSplitPanel(JPanel leftPanel, JPanel rightPanel) {

		/*
		var leftPanel = new JPanel();
		var lbl1 = new JLabel("left");
		leftPanel.add(lbl1);
		
		var rightPanel = new JPanel();
		var lbl2 = new JLabel("right");
		rightPanel.add(lbl2);
		*/
		
		var pane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, leftPanel, rightPanel );
		//var pane = new JSplitPane();
		
		pane.setResizeWeight(0.1);
		pane.setEnabled(false);

        return pane;
	}
	
	private void createLayout(JComponent... arg) {
		var pane = getContentPane();
        var gl = new GroupLayout(pane);
        pane.setLayout(gl);

        gl.setAutoCreateContainerGaps(true);
        gl.setAutoCreateGaps(true);

        gl.setHorizontalGroup(gl.createSequentialGroup().addComponent(arg[0]));

        gl.setVerticalGroup(gl.createParallelGroup().addComponent(arg[0]));

        pack();
	}
	
	private JPanel getControlsPanel() {
		var input = new JLabel("Input");
		var inputField1 = new JTextField(15);
		var test1 = new JButton("Test");
		var step = new JButton("Step");
		
		var button2 = new JButton("Add state");
		var statesLabel = new JLabel("States");
		var stateComboBox = new JComboBox(mainAutomatonPanel.getAutomaton().getStateStrings());
		var removeState = new JButton("Delete state");
		
		var button3 = new JButton("Add transition");
		var transitionLabel = new JLabel("Transitions");
		var transitionComboBox = new JComboBox(mainAutomatonPanel.getAutomaton().getTransitionStrings());
		var removeTransition = new JButton("Delete transition");
		
		var stackAlphLabel = new JLabel("Stack alphabet");
		var inputAlphLabel = new JLabel("Input alphabet");
		var stackAlphabet = new JTextField();
		var inputAlphabet = new JTextField();
		
		var startStateButton = new JButton("Select start state");
		
		var label2 = new JLabel("Bulk input: ");
		var area1 = new JTextArea(5,5);
		var button6 = new JButton("Test");
		
		var button7 = new JButton("New automaton");
		
		var stackAlphLabel2 = new JLabel(mainAutomatonPanel.getAutomaton().stackAlphabet.asString());
		var inputAlphLabel2 = new JLabel(mainAutomatonPanel.getAutomaton().inputAlphabet.asString());
		
		var startingStack = new JComboBox(mainAutomatonPanel.getAutomaton().stackAlphabet.getLetters().toArray());
		var startingStackLabel = new JLabel("Stack start symbol");
		var startingStackLabel2 = new JLabel(mainAutomatonPanel.getAutomaton().startStackSymbol.getValue());
		
		var panel = new JPanel();
		var layout = new GroupLayout(panel);
		panel.setLayout(layout);
		
		layout.setAutoCreateGaps(true);
		layout.setAutoCreateContainerGaps(true);
		
		//Creating horizontal groups
		GroupLayout.SequentialGroup hGroup = layout.createSequentialGroup();
		
		hGroup.addGroup(layout.createParallelGroup().
	            addComponent(input).addComponent(label2).addComponent(inputAlphLabel).addComponent(stackAlphLabel)
	            .addComponent(transitionLabel).addComponent(statesLabel).addComponent(startingStackLabel));
		
	   hGroup.addGroup(layout.createParallelGroup().
	            addComponent(inputField1).addComponent(button2).addComponent(button3)
	            .addComponent(stackAlphabet).addComponent(startStateButton).addComponent(area1)
	            .addComponent(button7).addComponent(transitionComboBox).addComponent(inputAlphabet)
	            .addComponent(stateComboBox).addComponent(startingStack));
	   
	   hGroup.addGroup(layout.createParallelGroup().
	            addComponent(test1).addComponent(button6).addComponent(removeTransition).addComponent(step).addComponent(removeState)
	            .addComponent(inputAlphLabel2).addComponent(stackAlphLabel2).addComponent(startingStackLabel2));
	   
	   
	   
	   
	   
	   layout.setHorizontalGroup(hGroup);
	   
	   //Creating Vertical groups
	   GroupLayout.SequentialGroup vGroup = layout.createSequentialGroup();
	   
	   vGroup.addGroup(layout.createParallelGroup(Alignment.BASELINE).
	            addComponent(input).addComponent(inputField1).addComponent(test1));
	   
	   vGroup.addGroup(layout.createParallelGroup(Alignment.BASELINE).
	            addComponent(button2).addComponent(step));
	   
	   vGroup.addGroup(layout.createParallelGroup(Alignment.BASELINE).
	            addComponent(removeState).addComponent(stateComboBox).addComponent(statesLabel));
	   
	   vGroup.addGroup(layout.createParallelGroup(Alignment.BASELINE).
	            addComponent(button3));
	   
	   vGroup.addGroup(layout.createParallelGroup(Alignment.BASELINE).
	            addComponent(removeTransition).addComponent(transitionComboBox).addComponent(transitionLabel));
	   
	   vGroup.addGroup(layout.createParallelGroup(Alignment.BASELINE).
	            addComponent(inputAlphabet).addComponent(inputAlphLabel).addComponent(inputAlphLabel2));
	   
	   vGroup.addGroup(layout.createParallelGroup(Alignment.BASELINE).
	            addComponent(stackAlphabet).addComponent(stackAlphLabel).addComponent(stackAlphLabel2));
	   
	   vGroup.addGroup(layout.createParallelGroup(Alignment.BASELINE).
	            addComponent(startStateButton));
	   
	   vGroup.addGroup(layout.createParallelGroup(Alignment.BASELINE).
	            addComponent(startingStackLabel).addComponent(startingStack).addComponent(startingStackLabel2));
	   
	   vGroup.addGroup(layout.createParallelGroup(Alignment.BASELINE).
	            addComponent(label2).addComponent(area1).addComponent(button6));
	   
	   vGroup.addGroup(layout.createParallelGroup(Alignment.BASELINE).
	            addComponent(button7));
	   
	   layout.setVerticalGroup(vGroup);
	
		
		/*
		GroupLayout.SequentialGroup sg = gl.createSequentialGroup();
		
		sg.addComponent(lbl).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED ).addComponent(field).addComponent(button,
                GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE,
                GroupLayout.PREFERRED_SIZE);
		
		gl.setHorizontalGroup(sg);
		
		GroupLayout.ParallelGroup pg = gl.createParallelGroup(GroupLayout.Alignment.LEADING, false);
		
		pg.addComponent(lbl).addComponent(field).addComponent(button);
		gl.setVerticalGroup(pg);
		
		gl.setAutoCreateContainerGaps(true);

	    pack();
	    */
		
	   //Add state button
	   button2.addActionListener(new ActionListener() {
		   public void actionPerformed(ActionEvent e) {
			   //name to be +1 from the array converted to a string
			   int n = mainAutomatonPanel.getAutomaton().getStateCount();
			   String name = "s" + (n+1);
			   state temp = new state(name, false, false); 
			   mainAutomatonPanel.getAutomaton().addState(temp);;
			   mainAutomatonPanel.addState(temp);
			   stateComboBox.removeAllItems();
				for(int i = 0; i < mainAutomatonPanel.getAutomaton().states.length; i++) {
					stateComboBox.addItem(mainAutomatonPanel.getAutomaton().states[i].getName());
				}
		   }
	   });
	   
	   
	   //Step button
	   
	   ActionListener stepper = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(!transitionsStepped.isEmpty()) {
					mainAutomatonPanel.highlightTransition(transitionsStepped.get(0));
					mainAutomatonPanel.showStack(stackStep.get(0));
					transitionsStepped.remove(0);
				}
				else {
					mainAutomatonPanel.removeHighlight();
					mainAutomatonPanel.hideStack();
					mainAutomatonPanel.getAutomaton().input(stepInput);
					stepTimer.stop();
				}
			}
		};
		
		stepTimer = new Timer(2500, stepper);
		
	    step.addActionListener(new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			transitionsStepped = mainAutomatonPanel.getAutomaton().getSteps(inputField1.getText());
			stackStep = mainAutomatonPanel.getAutomaton().stackStepped;
			stepInput = inputField1.getText();
			//stepCounter = 0;
			stepTimer.start();
			//boolean stepping = true;
			//mainAutomatonPanel.getAutomaton().input(inputField1.getText());
			
		}
		   
	   });
	   
	   
	   //Add transition button
	   button3.addActionListener(new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			for(int i = 0; i < mainAutomatonPanel.stateUIList.length; i++) {
				//mainAutomatonPanel.stateUIList[i].setMouseMode(1);
				mainAutomatonPanel.getAutomaton().setMouseMode(1);
			}
		}   
	   });
	   
	   //Delete state button
	   removeState.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String s = (String) stateComboBox.getSelectedItem();
				
				mainAutomatonPanel.removeSingleStateUI(s);
				//mainAutomatonPanel.remove(mainAutomatonPanel.getUIList()[0]);
				
				mainAutomatonPanel.getAutomaton().removeState(s);
				mainAutomatonPanel.removeState(s);
				
	
				stateComboBox.removeAllItems();
				for(int i = 0; i < mainAutomatonPanel.getAutomaton().states.length; i++) {
					stateComboBox.addItem(mainAutomatonPanel.getAutomaton().states[i].getName());
				}
				
				transitionComboBox.removeAllItems();
				for(int i = 0; i < mainAutomatonPanel.getAutomaton().transitions.length; i++) {
					transitionComboBox.addItem(mainAutomatonPanel.getAutomaton().getTransitionStrings()[i]);
				}

			}   
		   });
	   
	   //Alphabet listeners (user has to press enter to trigger update)
	   inputAlphabet.addActionListener(new ActionListener() {
		   public void actionPerformed(ActionEvent e) {
				String text = inputAlphabet.getText();
				//System.out.println(text);
				mainAutomatonPanel.getAutomaton().setInputAlphabet(text);
				inputAlphLabel2.setText(mainAutomatonPanel.getAutomaton().inputAlphabet.asString());
				//mainAutomatonPanel.getAutomaton().printInputAlphabet();
			}   
	   });
	   
	   stackAlphabet.addActionListener(new ActionListener() {
		   public void actionPerformed(ActionEvent e) {
				String text = stackAlphabet.getText();
				//System.out.println(text);
				mainAutomatonPanel.getAutomaton().setStackAlphabet(text);
				stackAlphLabel2.setText(mainAutomatonPanel.getAutomaton().stackAlphabet.asString());
				//mainAutomatonPanel.getAutomaton().printStackAlphabet();
			}   
	   });
	   
	   //Start state button
	   startStateButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				mainAutomatonPanel.getAutomaton().setMouseMode(2);
			}   
		   });
	   
	   //Delete transition
	   removeTransition.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String t = (String) transitionComboBox.getSelectedItem();
				//System.out.println(t);
				mainAutomatonPanel.getAutomaton().removeTransitionWithString(t);
				
				//update jcombobox with existing transitions
				transitionComboBox.removeAllItems();

				for(int i = 0; i < mainAutomatonPanel.getAutomaton().transitions.length; i++) {
					transitionComboBox.addItem(mainAutomatonPanel.getAutomaton().getTransitionStrings()[i]);
				}
			}   
		   });
	   
	   //New automaton Button
	   button7.addActionListener(new ActionListener() {
		   @Override
		   public void actionPerformed(ActionEvent e) {
			   mainAutomatonPanel.setAutomaton(new pushdown());
			   
			   //Updating all the UI
			   stateComboBox.removeAllItems();
				for(int i = 0; i < mainAutomatonPanel.getAutomaton().states.length; i++) {
					stateComboBox.addItem(mainAutomatonPanel.getAutomaton().states[i].getName());
				}
				
				transitionComboBox.removeAllItems();
				for(int i = 0; i < mainAutomatonPanel.getAutomaton().transitions.length; i++) {
					transitionComboBox.addItem(mainAutomatonPanel.getAutomaton().getTransitionStrings()[i]);
				}
				
				stackAlphLabel2.setText(mainAutomatonPanel.getAutomaton().stackAlphabet.asString());
				inputAlphLabel2.setText(mainAutomatonPanel.getAutomaton().inputAlphabet.asString());
			
		   }
	   });
	   
	   transitionComboBox.addPopupMenuListener(new PopupMenuListener() {
		@Override
		public void popupMenuWillBecomeVisible(PopupMenuEvent e) {
			transitionComboBox.removeAllItems();
			for(int i = 0; i < mainAutomatonPanel.getAutomaton().transitions.length; i++) {
				transitionComboBox.addItem(mainAutomatonPanel.getAutomaton().getTransitionStrings()[i]);
			}
			// TODO Auto-generated method stub
			
		}

		@Override
		public void popupMenuWillBecomeInvisible(PopupMenuEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void popupMenuCanceled(PopupMenuEvent e) {
			// TODO Auto-generated method stub
			
		}   
	   });
	   
	   startingStack.addPopupMenuListener(new PopupMenuListener() {

		@Override
		public void popupMenuWillBecomeVisible(PopupMenuEvent e) {
			startingStack.removeAllItems();
			//String[] temp = (String[]) mainAutomatonPanel.getAutomaton().stackAlphabet.getLetters().toArray().length;
			for(int i = 0; i < mainAutomatonPanel.getAutomaton().stackAlphabet.getLetters().toArray().length; i++) {
				startingStack.addItem(mainAutomatonPanel.getAutomaton().stackAlphabet.getLetters().toArray()[i]);
			}
			
		}

		@Override
		public void popupMenuWillBecomeInvisible(PopupMenuEvent e) {
			// TODO Auto-generated method stub
			//System.out.println("Trig");
			mainAutomatonPanel.getAutomaton().setStartStackSymbol(startingStack.getSelectedItem().toString());
			startingStackLabel2.setText(mainAutomatonPanel.getAutomaton().getStartStackSymbol().getValue());
			
		}

		@Override
		public void popupMenuCanceled(PopupMenuEvent e) {
			// TODO Auto-generated method stub
			
		}
		   
	   });

	   
	   //Test button 1
	   test1.addActionListener(new ActionListener() {
		   public void actionPerformed(ActionEvent e) {
			   //System.out.println("Running input on: " + inputField1.getText());
			   mainAutomatonPanel.getAutomaton().input(inputField1.getText());
			   //System.out.println(mainAutomatonPanel.getAutomaton().input(inputField1.getText()));
			   /*
				boolean test = mainAutomatonPanel.getAutomaton().input(inputField1.getText());
				if(test) {
					System.out.println("The input " + inputField1.getText() + " was accepted");
				}
				else {
					System.out.println("The input " + inputField1.getText() + " was rejected");
				}
				*/
			}   
	   });
	   
	   return panel;
	}
	
	private automatonPanel getAutomatonPanel() {
		
		//stateUI mc = new stateUI();
		//stateUI mc1 = new stateUI();
		
		var panel = new automatonPanel(new pushdown(0,0), false);

		panel.setLayout(null);

		//panel.add(mc);
		//panel.add(mc1);

		
		return panel;
		
	}
	
	private automatonPanel getGame1AutomatonPanel() {
		
		//pushdown constructor needs to make a random pda
		var panel = new automatonPanel(new pushdown(1,1), true);
		panel.setLayout(null);
		
		return panel;
	}

	private JPanel getGame1ControlPanel() {
		var input = new JLabel("Input");
		var inputField1 = new JTextField(15);
		var test1 = new JButton("Test");
		
		
		var startButton = new JButton("Start Game");
		
		
		var timeLabel = new JLabel(String.valueOf(count));
		var timeRemain = new JLabel("Time remaining: ");
		
		var panel = new JPanel();
		var layout = new GroupLayout(panel);
		
		var usedLabel = new JLabel("Correct Answers");
		var usedAns = new JComboBox();
		
		var level1 = new JButton("Level 1");
		var level2 = new JButton("Level 2");
		var level3 = new JButton("Level 3");
		var level4 = new JButton("Level 4");
		
		panel.setLayout(layout);
		
		layout.setAutoCreateGaps(true);
		layout.setAutoCreateContainerGaps(true);
		
		//Creating horizontal groups
		GroupLayout.SequentialGroup hGroup = layout.createSequentialGroup();
		hGroup.addGroup(layout.createParallelGroup().
	            addComponent(input).addComponent(timeRemain).addComponent(usedLabel));

		hGroup.addGroup(layout.createParallelGroup().
	            addComponent(inputField1).addComponent(timeLabel).addComponent(startButton)
	            .addComponent(usedAns).addComponent(level1).addComponent(level2).addComponent(level3).addComponent(level4));
		
		hGroup.addGroup(layout.createParallelGroup().
	            addComponent(test1));
		
		layout.setHorizontalGroup(hGroup);
		   
		
		
		//Creating Vertical groups
		GroupLayout.SequentialGroup vGroup = layout.createSequentialGroup();
		vGroup.addGroup(layout.createParallelGroup(Alignment.BASELINE).
	            addComponent(input).addComponent(inputField1).addComponent(test1));
		
		vGroup.addGroup(layout.createParallelGroup(Alignment.BASELINE).
	            addComponent(startButton));
		
		vGroup.addGroup(layout.createParallelGroup(Alignment.BASELINE).
	            addComponent(timeRemain).addComponent(timeLabel));
		
		vGroup.addGroup(layout.createParallelGroup(Alignment.BASELINE).
	            addComponent(usedAns).addComponent(usedLabel));
		
		vGroup.addGroup(layout.createParallelGroup(Alignment.BASELINE).
	            addComponent(level1));
		
		vGroup.addGroup(layout.createParallelGroup(Alignment.BASELINE).
	            addComponent(level2));
		
		vGroup.addGroup(layout.createParallelGroup(Alignment.BASELINE).
	            addComponent(level3));
		
		vGroup.addGroup(layout.createParallelGroup(Alignment.BASELINE).
	            addComponent(level4));
		
		layout.setVerticalGroup(vGroup);
		
		used = new  ArrayList<String>();
		 
		int delay = 1000;
		ActionListener countDown = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				count--;
				if(count == 0) {
					timer.stop();
					JOptionPane.showMessageDialog(null, "Your final score was: " + game1AutomatonPanel.gameScore);
				}
				else {
					timeLabel.setText(String.valueOf(count));
				}

				//repaint();
				//System.out.println("one second has passed");
			}
			
		};
		
		timer = new Timer(delay, countDown);
		
		//test button
		test1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				boolean b = game1AutomatonPanel.getAutomaton().input(inputField1.getText());
				if(b && !used.contains(inputField1.getText())) {
					game1AutomatonPanel.increaseGameScore();
					used.add(inputField1.getText());
					usedAns.addItem(inputField1.getText());
					}

			}
			
		});
		
		//Start button
		startButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//game1AutomatonPanel.getAutomaton().generateRandom();
				//start the timer
				count = 60;
				game1AutomatonPanel.resetGameScore();
				used.clear();
				usedAns.removeAllItems();
				timer.start();
			}
			
		});
		
		
		//Level1 Button
		level1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
				pushdown p = new pushdown(1);
				game1AutomatonPanel.setAutomaton(p);
				
				if(timer.isRunning()) {
					count = 60;
					timer.stop();
				}
				
			}
			
		});
		
		//Level 2 Button
		level2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				pushdown p = new pushdown(1,1);
				game1AutomatonPanel.setAutomaton(p);
				
				if(timer.isRunning()) {
					count = 60;
					timer.stop();
				}
			}
		});
		
			   
		return panel;
		
		
	}
	
	public static void main(String args[]) {
		
		EventQueue.invokeLater(() -> {
			var test = new GUI();
			test.setVisible(true);
		});
		
		
	}
	
	
	
}
