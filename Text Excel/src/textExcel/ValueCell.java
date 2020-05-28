package textExcel;

public class ValueCell extends RealCell{
	private String fullText;
	
	public String abbreviatedCellText() {
		if(fullText.length() >= 10) {
			return fullText.substring(0,10);
		}
		int spacesNeeded = 10 - fullText.length();
		for(int spacesAdded = 0; spacesAdded < spacesNeeded; spacesAdded++) {
			fullText += " ";
		}
		return fullText;
	}
	public double getDoubleValue() {
		return Double.parseDouble(fullText);
	}
	//constructor
	public ValueCell(String input) {
		super(input);
		
		fullText = Double.parseDouble(input) + "";
	}
}
