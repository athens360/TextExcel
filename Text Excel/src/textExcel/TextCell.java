package textExcel;

public class TextCell implements Cell{
	//fields
	private String fullText;
	
	//returns the thing wihtought quotes with a max of 10 chars
	public String abbreviatedCellText() {
		String noQuotes = fullText.substring(1, fullText.length() -1);
		if(noQuotes.length() >= 10) {
			return noQuotes.substring(0, 10);
		}
		int spacesNeeded = 10 - noQuotes.length();
		for(int spacesAdded = 0; spacesAdded < spacesNeeded; spacesAdded++) {
			noQuotes += " ";
		}
		return noQuotes;
	}

	//returns the full thing
	public String fullCellText() {
		return fullText;
	}
	
	//constructor
	public TextCell(String input) {
		fullText = input;
	}
}
