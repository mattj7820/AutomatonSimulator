
public class symbol {
	private String value;
	public String emptySymbol = ".";
	
	public symbol(String a) {
		value = a;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getEmptySymbol() {
		return emptySymbol;
	}

	public void setEmptySymbol(String emptySymbol) {
		this.emptySymbol = emptySymbol;
	}
	
}
