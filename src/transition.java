
public class transition {
	state start;
	symbol sym; 
	symbol startStack;
	state end;
	symbol[] endStack;
	
	public transition(state start, symbol sym, symbol startStack, state end, symbol[] endStack) {
		this.start = start;
		this.sym = sym;
		this.startStack = startStack;
		this.end = end;
		this.endStack = endStack;
	}
	
	//For consoleIO (step function)
	public void printT() {
		System.out.print("Transition Used: ");
		if(getEndStack().length == 0) {
			System.out.println("(" + getStart().getName() + ", " + getSym().getValue() + ", " + getStartStack().getValue() + ") -> (" + getEnd().getName() + ", [ ])" );
		}
		if(getEndStack().length == 1) {
			System.out.println("(" + getStart().getName() + ", " + getSym().getValue() + ", " + getStartStack().getValue() + ") -> (" + getEnd().getName() + ", [" + getEndStack()[0].getValue() + "])");
		}
		if(getEndStack().length == 2) {
			System.out.println("(" + getStart().getName() + ", " + getSym().getValue() + ", " + getStartStack().getValue() + ") -> (" + getEnd().getName() + ", [" + getEndStack()[0].getValue() + ", " + getEndStack()[1].getValue() + "])");
		}
	}
	
	public String getString() {
		if(getEndStack().length == 0) {
			return "(" + getStart().getName() + ", " + getSym().getValue() + ", " + getStartStack().getValue() + ") -> (" + getEnd().getName() + ", [ ])" ;
		}
		if(getEndStack().length == 1) {
			return "(" + getStart().getName() + ", " + getSym().getValue() + ", " + getStartStack().getValue() + ") -> (" + getEnd().getName() + ", [" + getEndStack()[0].getValue() + "])";
		}
		if(getEndStack().length == 2) {
			return "(" + getStart().getName() + ", " + getSym().getValue() + ", " + getStartStack().getValue() + ") -> (" + getEnd().getName() + ", [" + getEndStack()[0].getValue() + ", " + getEndStack()[1].getValue() + "])";
		}
		else {
			return "transition error";
		}
	}

	public state getStart() {
		return start;
	}

	public void setStart(state start) {
		this.start = start;
	}

	public symbol getSym() {
		return sym;
	}

	public void setSym(symbol sym) {
		this.sym = sym;
	}

	public symbol getStartStack() {
		return startStack;
	}

	public void setStartStack(symbol startStack) {
		this.startStack = startStack;
	}

	public state getEnd() {
		return end;
	}

	public void setEnd(state end) {
		this.end = end;
	}

	public symbol[] getEndStack() {
		return endStack;
	}
	
	public String[] getEndStackAsString() {
		String[] temp = new String[endStack.length];
		for(int i = 0; i < endStack.length; i++) {
			temp[i] = endStack[i].getValue();
		}
		return temp;
	}

	public void setEndStack(symbol[] endStack) {
		this.endStack = endStack;
	}
	
}
