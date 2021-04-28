import java.util.ArrayList;

public class alphabet {
	ArrayList<String> letters;
	
	public alphabet(String[] letters) {
		this.letters = new ArrayList<String>();
		
		for(int i = 0; i < letters.length; i++) {
			this.letters.add(letters[i]);
		}
	}
	
	public alphabet() {
		this.letters = new ArrayList<String>();
	}
	
	public boolean contains(String a) {
		return letters.contains(a);
	}
	
	public boolean containsAll(String[] a) {
		boolean b = true;
		
		for(int i = 0; i < a.length; i++) {
			if(!letters.contains(a[i])) {
				b = false;
			}
		}
		
		return b;
	}
	
	public void setAlphabet(String s) {
		ArrayList<String> temp = new ArrayList<String>();
		var charA = s.toCharArray();
		for(int i = 0; i < charA.length; i++) {
			temp.add(String.valueOf(charA[i]));
		}
		letters = temp;
	}
	
	public void printAlphabet() {
		System.out.println("The characters in the alphabet are: ");
		letters.forEach((l) -> System.out.println(l));
	}
	
	public String asString() {
		return letters.toString();
	}
	
	public void addChar(String a) {
		letters.add(a);
	}
	
	public void removeCharByIndex(int index) {
		letters.remove(index);
	}
	
	public void removeChar(String a) {
		letters.remove(a);
	}
	
	public boolean isEmpty() {
		return letters.isEmpty();
	}
	
	public ArrayList<String> getLetters() {
		return letters;
	}
}