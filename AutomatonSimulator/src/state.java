
public class state {
	private String name;
	private boolean start;
	private boolean end;

	public state(String name, boolean start, boolean end) {
		this.name = name;
		this.end = end;
		this.start = start;
	}
	
	public void printState() {
		System.out.println("\nState Name: " + name);
		System.out.println("Start state: " + start);
		System.out.println("Accepting state: " + end);
	}
	
	public String getName() {
		return name;
	}
	
	public boolean getStart() {
		return start;
	}

	public boolean getEnd() {
		return end;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	public void setStart(boolean start) {
		this.start = start;
	}

	public void setEnd(boolean end) {
		this.end = end;
	}

}