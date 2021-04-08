import java.util.*;
import javax.swing.JOptionPane;
import java.util.Random;

public class pushdown {
	state[] states;
	alphabet inputAlphabet;
	alphabet stackAlphabet;
	state startState;
	symbol startStackSymbol;
	state[] accepting;
	
	transition[] transitions;
	
	Stack<String> stack;
	
	String current;
	int stringPos;
	
	int stateCount = 0;
	
	private boolean firstClick;
	private String firstState;
	private int mouseMode = 0;
	
	public ArrayList<String> stackStepped;
	
	public pushdown(state[] states, alphabet inputAlphabet, alphabet stackAlphabet, state startState, symbol startStackSymbol, state[] accepting) {
		this.states = states;
		transitions = new transition[0];
		this.inputAlphabet = inputAlphabet;
		this.stackAlphabet = stackAlphabet;
		this.startState = startState;
		this.startStackSymbol = startStackSymbol;
		this.accepting = accepting;
		
		stack = new Stack<String>();
		stack.push(startStackSymbol.getValue());
		
		current = startState.getName();
	}
	
	public pushdown() {
		states = new state[0];
		transitions = new transition[0];
		inputAlphabet = new alphabet();
		stackAlphabet = new alphabet();
		inputAlphabet.setAlphabet("01");
		stackAlphabet.setAlphabet("XYZ");
		state temp = new state("s1", true, false);
		addState(temp);
		startState = temp;
		startStackSymbol = new symbol("X");;
		accepting = new state[0];
		
		stack = new Stack<String>();
		current = startState.getName();
	}
	
	
	public pushdown(int level) {
		if(level == 1) {
			inputAlphabet = new alphabet();
			stackAlphabet = new alphabet();
			
			inputAlphabet.setAlphabet("01");
			stackAlphabet.setAlphabet("X");
			
			states = new state[0];
			transitions = new transition[0];
			startStackSymbol = new symbol("X");
			accepting = new state[0];
			
			stack = new Stack<String>();
			stack.push(startStackSymbol.getValue());
			
			state s1 = new state("s1", true, false);
			startState = s1;
			current = startState.getName();
			
			addState(s1);
			
			String[] add = {"X","X"};
			String[] neutral =  {"X"};
			String[] remove = {"e"};
			
			addTransition("s1", "0", "e", "s1", add);
			addTransition("s1", "1", "e", "s1", add);
			addTransition("s1", "0", "X", "s1", remove);
			addTransition("s1", "1", "X", "s1", add);
			addTransition("s1", "e", "e", "s1", neutral);
			addTransition("s1", "e", "X", "s1", neutral);
		}
		else if(level == 2) {
			inputAlphabet = new alphabet();
			stackAlphabet = new alphabet();
			
			inputAlphabet.setAlphabet("01");
			stackAlphabet.setAlphabet("XY");
			
			states = new state[0];
			transitions = new transition[0];
			startStackSymbol = new symbol("X");
			accepting = new state[0];
			
			stack = new Stack<String>();
			stack.push(startStackSymbol.getValue());
			
			state s1 = new state("s1", true, false);
			state s2 = new state("s2", false, true);
			startState = s1;
			current = startState.getName();
			
			addState(s1);
			addState(s2);
			
			String[] addX = {"X","X"};
			String[] neutralX =  {"X"};
			String[] addY = {"Y","Y"};
			String[] neutralY =  {"Y"};
			String[] remove = {"e"};
			
			addTransition("s1", "1", "X", "s1", addX);
			addTransition("s1", "1", "Y", "s1", remove);
			addTransition("s1", "1", "e", "s2", addY);
			addTransition("s1", "0", "X", "s1", neutralY);
			addTransition("s1", "0", "Y", "s1", addX);
			addTransition("s1", "0", "e", "s2", addY);
			addTransition("s1", "e", "X", "s1", neutralX);
			
			addTransition("s2", "1", "X", "s2", remove);
			addTransition("s2", "1", "Y", "s2", remove);
			addTransition("s2", "1", "e", "s1", addY);
			addTransition("s2", "0", "X", "s2", addX);
			addTransition("s2", "0", "Y", "s2", neutralY);
			addTransition("s2", "0", "e", "s1", addY);					
		}
		else if(level == 3) {
			inputAlphabet = new alphabet();
			stackAlphabet = new alphabet();
			
			inputAlphabet.setAlphabet("01");
			stackAlphabet.setAlphabet("X");
			
			states = new state[0];
			transitions = new transition[0];
			startStackSymbol = new symbol("X");
			accepting = new state[0];
			
			stack = new Stack<String>();
			stack.push(startStackSymbol.getValue());
			
			state s1 = new state("s1", true, false);
			state s2 = new state("s2", false, true);
			state s3 = new state("s3", false, false);
			startState = s1;
			current = startState.getName();
			
			addState(s1);
			addState(s2);
			addState(s3);
			
			String[] add = {"X","X"};
			String[] neutral =  {"X"};
			String[] remove = {"e"};
			
			addTransition("s1", "0", "X", "s1", add);
			addTransition("s1", "0", "e", "s1", add);
			addTransition("s1", "1", "X", "s2", add);
			addTransition("s1", "1", "e", "s2", add);
			addTransition("s1", "e", "X", "s2", neutral);
			
			
			addTransition("s2", "0", "X", "s2", remove);
			addTransition("s2", "0", "e", "s3", neutral);
			addTransition("s2", "1", "X", "s3", remove);
			addTransition("s2", "1", "e", "s3", neutral);
			
			addTransition("s3", "0", "X", "s3", neutral);
			addTransition("s3", "0", "e", "s3", neutral);
			addTransition("s3", "1", "X", "s3", neutral);
			addTransition("s3", "1", "e", "s3", neutral);
			

		}	
		else if(level == 4) {
			
		}	
		else{
			System.out.println("Level not found");
		}
		
	}
	
	
	public pushdown(int n, String Q) {
		String[] a = {"a", "b"};
		String[] b = {"A", "S"};
				
		states = new state[0];
		transitions = new transition[0];
		inputAlphabet = new alphabet(a);
		stackAlphabet = new alphabet(b);
		//startState = new state("A", true, false);
		startStackSymbol = new symbol("A");;
		accepting = new state[0];
		
		stack = new Stack<String>();
		stack.push(startStackSymbol.getValue());
		
		
		state q = new state("p", true, false);
		state r = new state("r", false, false);
		startState = q;
		current = startState.getName();
		
		addState(q);
		addState(new state("q", false, true));
		addState(r);
		
		addAccepting("r");
		
		String[] x = {"A"};
		String[] y = {"A", "A"};
		String[] z = {};
		addTransition("p", "a", "S", "p", x);
		addTransition("p", "b", "A", "q", z);
		addTransition("p", "a", "A", "p", y);
		addTransition("q", "b", "A", "q", z);
		
	}
	
	public pushdown(int n, int w) {
		String[] a = {"0", "1"};
		String[] b = {"Z"};
				
		states = new state[0];
		transitions = new transition[0];
		inputAlphabet = new alphabet(a);
		stackAlphabet = new alphabet(b);
		//startState = new state("A", true, false);
		startStackSymbol = new symbol("Z");;
		accepting = new state[0];
		
		stack = new Stack<String>();
		stack.push(startStackSymbol.getValue());
		
		
		state p = new state("p", true, false);
		state q = new state("q", false, true);
		state r = new state("r", false, false);
		
		startState = p;
		current = startState.getName();
		
		addState(p);
		addState(q);
		addState(r);
		
		updateAccepting();
		
		String[] x = {"Z"};
		String[] y = {"Z", "Z"};
		String[] z = {};
		addTransition("p", "0", "Z", "p", y);
		addTransition("p", "1", "Z", "q", x);
		addTransition("q", "0", "Z", "r", x);
		addTransition("q", "1", "Z", "q", z);
		addTransition("r", "0", "Z", "r", x);
		addTransition("r", "1", "Z", "r", x);
		
		//edit for testing here
		//addTransition("p", "1", "Z", "q", x);
		//addTransition("p", "1", "Z", "q", x);
		addTransition("r", "1", "Z", "q", x);
		addTransition("r", "1", "Z", "q", x);
	}
	
	public void addState(state a) {
		//states = (state[]) addToArray(states, a);
		state[] temp = new state[states.length + 1];
		for(int i = 0; i < states.length; i++) {
			temp[i] = states[i];
		}
		
		temp[states.length] = a;
		states = temp;
		
		stateCount++;
		updateAccepting();
		
	}
	
	//Need to add removing all transitions that connect to this state too
	public void removeState(String name) {
		for(int i = 0; i < states.length; i++) {
			if(states[i].getName().equals(name)) {
				if(states[i].getEnd() == true) {
					updateAccepting();
				}
				
				//System.out.println(transitions.length);
				ArrayList<String> remove = new ArrayList<String>();
				
				for(int j = 0; j < transitions.length; j++) {
					//System.out.println("Comparing " + states[i].getName() + " to " + transitions[j].start.getName() + " and " + transitions[j].end.getName());
					if(transitions[j].start.getName().equals(states[i].getName()) || transitions[j].end.getName().equals(states[i].getName()) ) {
						//removeFromTransitions(j);
						remove.add(transitions[j].getString());
						//System.out.println("match\n");
					}
					else {
						//System.out.println("no match\n");
					}
				}
				/*
				System.out.println(remove.toString());
				for(int x = 0; x < transitions.length; x++) {
					System.out.println(transitions[x].getString());
				}
				*/
				
				for(int j = 0; j < remove.size(); j++) {
					//removeFromTransitions(remove.get(j));
					removeTransitionWithString(remove.get(j));
				}
				
				/*
				System.out.println("After");
				for(int x = 0; x < transitions.length; x++) {
					System.out.println(transitions[x].getString());
				}
				*/
				
				
				
				/*
				states[i].setName("dead");
				
				for(int j = 0; j < transitions.length; j++) {
					if(transitions[j].start.getName().equals("dead") || transitions[j].end.getName().equals("") ) {
						System.out.println("Removing " + transitions[j].getString());
						removeFromTransitions(j);
					}
				}
				*/
				
				
				removeFromStates(i);
				//stateCount --;
				return;
			}
			
		}
		//System.out.println("The state to be removed could not be found");
	}
	
	/*
	public void addTransition(transition t) {

		transition[] temp = new transition[transitions.length+1];
		
		for(int i = 0; i < transitions.length; i++) {
			temp[i] = transitions[i];
		}
		
		temp[transitions.length] = t;
		transitions = temp;
	}*/

	public void addTransition(String startState, String sym, String startStack, String endState, String[] endStack) {
		
		int startIndex = -1;
		int endIndex = -1;
		
		for(int i = 0; i < states.length; i++) {
			if(states[i].getName().equals(startState)) {
				startIndex = i;
			}
			if(states[i].getName().equals(endState)) {
				endIndex = i;
			}	
		}
		
		if(startIndex == -1 || endIndex == -1) {
			System.out.println("Start or end state not found");
			return;
		}
		
		symbol[] outStack = new symbol[endStack.length];
		
		for(int i = 0; i < endStack.length; i++) {
			outStack[i] = new symbol(endStack[i]);
		}
		
		transition t = new transition(states[startIndex], new symbol(sym), new symbol(startStack), states[endIndex], outStack);
		
		transition[] temp = new transition[transitions.length+1];
		for(int i = 0; i < transitions.length; i++) {
			temp[i] = transitions[i];
		}
		
		temp[transitions.length] = t;
		transitions = temp;
	}
	
	/*
	public void removeTransition(state start, symbol sym, symbol[] startStack) {
		String n = start.getName();
		String s = sym.getValue();
		String currentStack = startStack[0].getValue();
		for(int i = 0; i < transitions.length; i++) {
			transition t = transitions[i];
			if(n.equals(t.getStart().getName()) && s.equals(t.getSym().getValue()) && currentStack.equals(t.getStartStack().getValue()) ) {
				removeFromTransitions(i);
			}
		}
	}
	*/
	
	public void removeTransition(String start, String sym, String startStack) {
		String n = start;
		String s = sym;
		String currentStack = startStack;
		for(int i = 0; i < transitions.length; i++) {
			transition t = transitions[i];
			if(n.equals(t.getStart().getName()) && s.equals(t.getSym().getValue()) && currentStack.equals(t.getStartStack().getValue()) ) {
				removeFromTransitions(i);
			}
		}
	}
	
	public void removeTransitionWithString(String s) {
		for(int i = 0; i < transitions.length; i++) {
			transition t = transitions[i];
			if(t.getString().equals(s)) {
				removeFromTransitions(i);
			}
		}
	}
	
	public void addAccepting(String name) {
		
		for(int i = 0; i < states.length; i++) {
			if(states[i].getName().equals(name)) {
				states[i].setEnd(true);
				updateAccepting();
				return;
			}
		}
	}
	
	public void removeAccepting(String name) {
		for(int i = 0; i < accepting.length; i++) {
			if(accepting[i].getName().equals(name)) {
				//removeByIndex(accepting, i);
				
				state[] temp = new state[accepting.length-1];
				
				for(int x = 0, j = 0; x < states.length; x++) {
					if(x != i) {
						temp[j++] = accepting[x];
					}
				}
				accepting = temp;
				
			}
		}
	}
	
	public void updateAccepting() {
		accepting = new state[0];
		for(int i = 0; i < states.length; i++) {
			if(states[i].getEnd() == true) {
				//addToArray(accepting, states[i]);
				
				state[] temp = new state[accepting.length + 1];
				for(int j = 0; j < accepting.length; j++) {
					temp[j] = accepting[j];
				}
				temp[accepting.length] = states[i];
				accepting = temp;
			}
		}
	}
	
	//return boolean if input is accepted or no
	public boolean inputOld(String s) {
		
		
		stack.clear();
		stack.push(startStackSymbol.getValue());
		current = startState.getName();
		char[] t = s.toCharArray();
			
		for(int i = 0; i < t.length; i++) {
			step(current, new symbol(Character.toString(t[i])), i);
		}
		
		if(stack.isEmpty()) {
			return true;
		}
		
		String endPos = current;
		for(int i = 0; i < accepting.length; i++) {
			if(accepting[i].getName().equals(endPos)) {
				return true;
			}
		}
		
		return false;
	}
	
	public boolean input(String s) {
		System.out.println("NEW INPUT: " + s);
		stack.clear();
		stack.push(startStackSymbol.getValue());
		current = startState.getName();
		char[] t = s.toCharArray();
		
		//System.out.println("testing e:" + s);
		
		//Testing input alphabet
		for(int i = 0; i < t.length; i++) {
			if(!inputAlphabet.contains(String.valueOf(t[i])) && !Character.toString(t[i]).equals("e")) {
				//System.out.println("Input contains symbols that are not in the input alphabet");
				JOptionPane.showMessageDialog(null, "The input \"" + s + "\" contains symbols that are not in the input alphabet");
				return false;
				//return "The input \"" + s + "\" contains symbols that are not in the input alphabet";
			}
		}
		
		//Testing stack alphabet
//		for(int i = 0; i < transitions.length; i++) {
//			if(!stackAlphabet.contains(transitions[i].startStack.getValue()) && !stackAlphabet.containsAll(transitions[i].getEndStackAsString()) ) {
//				JOptionPane.showMessageDialog(null, "The automaton contains transitions with symbols that aren't in the stack alphabet");
//				return false;
//			}
//		}
		
		for(int i = 0; i < t.length; i++) {
			step(current, new symbol(Character.toString(t[i])), i);
		}
		
		if(stack.isEmpty()) {
			JOptionPane.showMessageDialog(null, "The input \"" + s + "\" is accepted by the automaton");
			return true;
			//return "The input " + s + " is accepted by the automaton";
		}
		
		String endPos = current;
		for(int i = 0; i < accepting.length; i++) {
			if(accepting[i].getName().equals(endPos)) {
				JOptionPane.showMessageDialog(null, "The input \"" + s + "\" is accepted by the automaton");
				return true;
				//return "The input " + s + " is accepted by the automaton";
			}
		}
		
		//return "The input " + s + " is rejected by the automaton";
		JOptionPane.showMessageDialog(null, "The input \"" + s + "\" is rejected by the automaton");
		return false;
		
			
	}
	
	public ArrayList<String> getSteps(String s) {
		stack.clear();
		stack.push(startStackSymbol.getValue());
		current = startState.getName();
		char[] t = s.toCharArray();
		
		ArrayList<String> temp = new ArrayList<String>();
		stackStepped = new ArrayList<String>();
		
		//Testing input alphabet
		for(int i = 0; i < t.length; i++) {
			if(!inputAlphabet.contains(String.valueOf(t[i])) && !Character.toString(t[i]).equals("e")) {
				//System.out.println("Input contains symbols that are not in the input alphabet");
				//JOptionPane.showMessageDialog(null, "The input \"" + s + "\" contains symbols that are not in the input alphabet");
				return temp;
				//return "The input \"" + s + "\" contains symbols that are not in the input alphabet";
			}
		}
		
		
		
		for(int i = 0; i < t.length; i++) {
			stepThrough(current, new symbol(Character.toString(t[i])), i, temp);
		}
		
		return temp;
		
	}
	
	public void stepThrough(String cur, symbol s, int x, ArrayList<String> store) {
		for(int i = 0; i < transitions.length; i++) {
			transition t = transitions[i];
			
			//if stack is empty search for e
			String a;
			if(!stack.isEmpty()) {
				a = stack.peek();
			}
			else {
				a = "e";
			}
			
			if(t.start.getName().equals(cur) && s.getValue().equals(t.sym.getValue()) && a.equals(t.getStartStack().getValue())) {
				//Printing used Transition and stack (current state)
				System.out.println("Current state: " + cur);
				printStack();
				System.out.println("Stack stepped" + stackStepped.toString());
				t.printT();
				store.add(t.getString());
				System.out.println();
				//
				current = t.getEnd().getName();
				
				//if stack is already empty, don't pop
				if(!stack.isEmpty()) {
					stack.pop();
				}
				
				//if end stack is e don't push anything
				if(t.getEndStack()[0].getValue().equals("e")) {
					System.out.println("Empty found");
					System.out.println("New stack: " + stack.toString());
					System.out.println();
					stackStepped.add(stack.toString());
					return;
				}
				
				//if stack coming in is empty and 2 in stackEnd for transition
				if((a.equals("e") && t.getEndStack().length == 2) ) {
					System.out.println("Only adding last");
					stack.push(t.getEndStack()[1].getValue());
					System.out.println("New stack: " + stack.toString());
					System.out.println();
					stackStepped.add(stack.toString());
					return;
				}
				
				
				for(symbol m : t.getEndStack()) {
					stack.push(m.getValue());
				}
				//Output stack arraylist
				stackStepped.add(stack.toString());
				return;
			}
		}
	}
	
	//take current state, stack and current input and change based on transitions
	public void step(String cur, symbol s, int x) {
		//Find the corresponding transition (if none non-determinant)
		for(int i = 0; i < transitions.length; i++) {
			transition t = transitions[i];
			
			//if stack is empty search for e
			String a;
			if(!stack.isEmpty()) {
				a = stack.peek();
			}
			else {
				a = "e";
			}
			
			if(t.start.getName().equals(cur) && s.getValue().equals(t.sym.getValue()) && a.equals(t.getStartStack().getValue())) {
				//Printing used Transition and stack (current state)
				System.out.println("Current state: " + cur);
				System.out.println("Input symbol used: " + s.getValue());
				printStack();
				t.printT();
				//
				current = t.getEnd().getName();
				
				//if stack is already empty, don't pop
				if(!stack.isEmpty()) {
					stack.pop();
				}
				
				//if end stack is e don't push anything
				if(t.getEndStack()[0].getValue().equals("e")) {
					System.out.println("Empty found");
					System.out.println("New stack: " + stack.toString());
					System.out.println();
					return;
				}
				
				//if stack coming in is empty and 2 in stackEnd for transition
				if(a.equals("e") && t.getEndStack().length == 2 ) {
					System.out.println("Only adding last");
					stack.push(t.getEndStack()[1].getValue());
					System.out.println("New stack: " + stack.toString());
					System.out.println();
					return;
				}
				
				for(symbol m : t.getEndStack()) {
					stack.push(m.getValue());
				}
				System.out.println("New stack: " + stack.toString());
				System.out.println();
				return;
			}
		}
	}
	
	public void clearAutomaton() {
		states = new state[0];
		transitions = new transition[0];
		inputAlphabet = new alphabet();
		stackAlphabet = new alphabet();
		state temp = new state("s1", true, false);
		addState(temp);
		startState = temp;
		startStackSymbol = new symbol("A");;
		accepting = new state[0];
		
		stack = new Stack<String>();
		current = startState.getName();
	}
	
	public void generateRandom() {
		clearAutomaton();
		Random r = new Random();
		int statesNum = r.nextInt(3) + 1;
		int randStartState = r.nextInt(3);
		int endStateNum = r.nextInt(statesNum) + 1;
		int[] endStates = new int[endStateNum];
		//Doesn't stop duplicate end states 
		for(int i = 0; i < endStateNum; i++) {
			endStates[i] = r.nextInt(statesNum);
		}
		
		System.out.println("endStateNum: " + endStateNum);
		System.out.println("statesNum: " + statesNum);
		
		setInputAlphabet("012");
		setStackAlphabet("AZ");
		
		//Creating 1-4 states
		for(int i = 0; i < statesNum; i++) {
			state temp;
			int n = stateCount;
			String name = "s" + (n+1);
			if(i == randStartState && i == endStates[i]) {
				temp = new state(name, true, true); 
			}
			else if(i == randStartState && !(i == endStates[i])) {
				temp = new state(name, true, false); 
			}
			else if(!(i == randStartState) && i == endStates[i] ) {
				temp = new state(name, false, true); 
			}
			else {
				temp = new state(name, false, false); 
			}
			
			addState(temp);
		}
		
		startState = states[randStartState];
		current = startState.getName();
		
		
		
		String[] inAlph = {"0","1","2"};
		String[] stkAlph = {"A","Z","."};
		String[][] endStackOptions = {{"."},{"A"},{"Z"},{"A", "A"},{"Z","Z"},{"A","Z"},{"Z","A"}};
		//Creating transitions
		//for each state, for each input symbol, for each stack symbol (inc empty):
		//pick another random state to go to and a random thing to do to the stack
		for(int i = 0; i < states.length; i++) {
			for(int j = 0; j < inAlph.length; j++) {
				for(int k = 0; k < stkAlph.length; k++) {
					int randState = r.nextInt(states.length);
					int randEndStack = r.nextInt(7);
					addTransition(states[i].getName(), inAlph[j], stkAlph[k], states[randState].getName(), endStackOptions[randEndStack]);
				}
			}
		}
		
		int randStartSym = r.nextInt(3);
		startStackSymbol = new symbol(stkAlph[randStartSym]);
		
		stack = new Stack<String>();
		stack.push(startStackSymbol.getValue());
		
	}
	
	public void addToInputAlphabet(String a) {
		inputAlphabet.addChar(a);
	}
	
	public void removeFromInputAlphabet(String a) {
		inputAlphabet.removeChar(a);
	}
	
	public void addToStackAlphabet(String a) {
		stackAlphabet.addChar(a);
	}
	
	public void removeFromStackAlphabet(String a) {
		stackAlphabet.removeChar(a);
	}
	
	public void setInputAlphabet(String s) {
		inputAlphabet.setAlphabet(s);
	}
	
	public void setStackAlphabet(String s) {
		stackAlphabet.setAlphabet(s);
	}
	
	
	
	private void removeFromStates(int n) {
		state[] temp = new state[states.length-1];
		
		for(int i = 0, j = 0; i < states.length; i++) {
			if(i != n) {
				temp[j++] = states[i];
			}
		}
		states = temp;
	}
	
	private void removeFromTransitions(int n) {
		transition[] temp = new transition[transitions.length-1];
		
		for(int i = 0, j = 0; i < transitions.length; i++) {
			if(i != n) {
				temp[j++] = transitions[i];
			}
		}
		transitions = temp;
	}
	
	/*
	private Object[] addToArray(Object[] a, Object x) {
		Object[] temp = new Object[a.length+1];
		
		for(int i = 0; i < a.length; i++) {
			temp[i] = a[i];
		}
		
		temp[a.length] = x;
		return temp;
	}
	
	private Object[] removeByIndex(Object[] a, int index) {
		Object[] temp = new Object[a.length-1];
		
		for(int i=0,j=0; i < a.length; i++) {
			if(i != index) {
				temp[j++] = a[i];
			}
		}
		return temp;
	}
	*/
	
	public void printStates() {
		if(states.length == 0) {
			System.out.println("Q = { }");
			return;
		}
		System.out.print("Q = {");
		for(int i = 0; i < states.length-1; i++) {
			System.out.print(states[i].getName() + ", ");
		}
		System.out.print(states[states.length-1].getName());
		System.out.print("}\n");
		
	}
	
	public void printInputAlphabet() {
		if(inputAlphabet.isEmpty()) {
			System.out.println("Input Alph = { }");
			return;
		}
		
		System.out.print("Input Alph = {");
		ArrayList<String> temp = inputAlphabet.getLetters();
		
		for(int i = 0; i < temp.size()-1; i++) {
			System.out.print(temp.get(i) + ", ");
		}
		
		System.out.print(temp.get(temp.size()-1));
		System.out.print("}\n");
		
	}
	
	public void printStackAlphabet() {
		if(stackAlphabet.isEmpty()) {
			System.out.println("Stack Alph = { }");
			return;
		}
		
		System.out.print("Stack Alph = {");
		ArrayList<String> temp = stackAlphabet.getLetters();
		
		for(int i = 0; i < temp.size()-1; i++) {
			System.out.print(temp.get(i) + ", ");
		}
		
		System.out.print(temp.get(temp.size()-1));
		System.out.print("}\n");
	}
	
	public void printAcceptingStates() {
		if(accepting.length == 0) {
			System.out.println("F = { }");
			return;
		}
		System.out.print("F = {");
		for(int i = 0; i < accepting.length-1; i++) {
			System.out.print(accepting[i].getName() + ", ");
		}
		System.out.print(accepting[accepting.length-1].getName());
		System.out.print("}\n");
	}
	
	public void printTranstions() {
		System.out.println("Delta: ");
		for(int i = 0; i < transitions.length; i++) {
			transition t = transitions[i];
			if(t.getEndStack().length == 0) {
				System.out.println("(" + t.getStart().getName() + ", " + t.getSym().getValue() + ", " + t.getStartStack().getValue() + ") -> (" + t.getEnd().getName() + ", [ ])" );
			}
			if(t.getEndStack().length == 1) {
				System.out.println("(" + t.getStart().getName() + ", " + t.getSym().getValue() + ", " + t.getStartStack().getValue() + ") -> (" + t.getEnd().getName() + ", [" + t.getEndStack()[0].getValue() + "])");
			}
			if(t.getEndStack().length == 2) {
				System.out.println("(" + t.getStart().getName() + ", " + t.getSym().getValue() + ", " + t.getStartStack().getValue() + ") -> (" + t.getEnd().getName() + ", [" + t.getEndStack()[0].getValue() + ", " + t.getEndStack()[1].getValue() + "])");
			}
			
			
		}
	}
	
	public void printStack() {
		System.out.print("Stack: ");
		System.out.println(stack.toString());
	}
	
	public state[] getStates() {
		return states;
	}
	
	public state getStartState() {
		return startState;
	}
	
	public void setStartState(String name) {
		for(int i = 0; i < states.length; i++) {
			if(states[i].getName().equals(name)) {
				states[i].setStart(true);
				startState = states[i];
			}
			else {
				states[i].setStart(false);
			}
		}
	}
	
	public symbol getStartStackSymbol() {
		return startStackSymbol;
	}
	
	public void setStartStackSymbol(String s) {
		startStackSymbol = new symbol(s);
	}
	
	public boolean getFirstClick() {
		return firstClick;
	}
	
	public void setFirstClick(boolean b) {
		firstClick = b;
	}
	
	public String[] getTransitionStrings() {
		String temp[] = new String[transitions.length];
		for(int i = 0; i < transitions.length; i++) {
			temp[i] = transitions[i].getString();
		}
		
		return temp;
	}
	
	public String[] getStateStrings() {
		String temp[] = new String[states.length];
		for(int i = 0; i < states.length; i++) {
			temp[i] = states[i].getName();
		}
		
		return temp;
	}
	
	public String getFirstState() {
		return firstState;
	}
	
	public void setFirstState(String s) {
		firstState = s;
	}
	
	public int getStateCount() {
		return stateCount;
	}
	
	public int getMouseMode() {
		return mouseMode;
	}
	
	public void setMouseMode(int i) {
		mouseMode = i;
	}
}