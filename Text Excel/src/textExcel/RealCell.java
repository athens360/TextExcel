package textExcel;

public abstract class RealCell implements Cell{
	private String fullText;
	//real cell cant have abbreviated cell text cuz it depends on what reallcell it is.
	public String abbreviatedCellText(){
		return null;
	}
	public String fullCellText() {
		return fullText;
	}
	//this one only works none of the cells but I have it anyway
	public double getDoubleValue() {
		return 0;
	}
	//constructor
	public RealCell(String input) {
		fullText = input;
	}
}
