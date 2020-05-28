package textExcel;

public class PercentCell extends RealCell{
	private String fullText;
	//methods
	public String abbreviatedCellText() {
		double num = Double.parseDouble(fullText);
		num = num * 100;
		int num1 = (int)num;
		String abvText = num1 + "%";
		if(abvText.length() >= 10) {
			abvText = abvText.substring(0, 9) + "%";
		}
		int spacesNeeded = 10 - abvText.length();
		for(int spacesAdded = 0; spacesAdded < spacesNeeded; spacesAdded++) {
			abvText += " ";
		}
		return abvText;
	}
	public String fullCellText() {
		return fullText;
	}
	public double getDoubleValue() {
		return Double.parseDouble(fullText);
	}
	//constructor
	
	public PercentCell(String input) {
		super(input);
		fullText = input.substring(0, input.length() - 1);
		fullText = Double.parseDouble(fullText) / 100 + "";
	}

}
