package textExcel;

public class FormulaCell extends RealCell{
	private String fullText;
	private Cell[][] spreadsheet;
	//methods
	public String fullCellText() {
		return fullText;
	}
	public String abbreviatedCellText() {
		String abvText = getDoubleValue() + "";
		if(abvText.length() >= 10) {
			return abvText.substring(0,10);
		}
		int spacesNeeded = 10 - abvText.length();
		for(int spacesAdded = 0; spacesAdded < spacesNeeded; spacesAdded++) {
			abvText += " ";
		}
		return abvText;
	}
	
	public double getDoubleValue() {
		String copyFullText = fullText;
		String[] equation = copyFullText.split(" ", 5);
		//formatted into (,sumoravg,cells,)
		//               0,1       ,2    ,3
		if(equation[1].toLowerCase().contentEquals("sum")) {
			double sum = 0;
			//this array is formatted topright,bottomleft
			//                        0       ,1
			String[] cellsString = equation[2].split("-");
			SpreadsheetLocation[] cells = new SpreadsheetLocation[]{new SpreadsheetLocation(cellsString[0]), new SpreadsheetLocation(cellsString[1]) };
			for(int r = cells[0].getRow(); r <= cells[1].getRow(); r++) {
				for(int c = cells[0].getCol(); c <= cells[1].getCol(); c++) {
					if(spreadsheet[r][c] instanceof RealCell) {
						sum += ((RealCell) spreadsheet[r][c]).getDoubleValue();
					}
				}
			}
			return sum;
		}
		
		if(equation[1].toLowerCase().contentEquals("avg")) {
			double avg = 0;
			int counter = 0;
			String[] cellsString = equation[2].split("-");
			SpreadsheetLocation[] cells = new SpreadsheetLocation[]{new SpreadsheetLocation(cellsString[0]), new SpreadsheetLocation(cellsString[1]) };
			for(int r = cells[0].getRow(); r <= cells[1].getRow(); r++) {
				for(int c = cells[0].getCol(); c <= cells[1].getCol(); c++) {
					if(spreadsheet[r][c] instanceof RealCell) {
						avg += ((RealCell) spreadsheet[r][c]).getDoubleValue();
						counter++;
					}
				}
			}
			avg = avg / counter;
			return avg;
		}
		// array equation will be formatted :   (,num,operation,num,everything else) - when reattaching the everything else part and the '('
		//                                      0,1  ,2        ,3  ,4              
		//remember to add spaces back becuz you will be very sad if you don't
		while(equation.length != 3) {
			if(isLoc(equation[1])) {
				SpreadsheetLocation cellLocation = new SpreadsheetLocation(equation[1]);
				Cell cell = spreadsheet[cellLocation.getRow()][cellLocation.getCol()];
				if(cell instanceof RealCell) {
					equation[1] = ((RealCell) cell).getDoubleValue() + "";
				}
			}
			if(isLoc(equation[3])) {
				SpreadsheetLocation cellLocation = new SpreadsheetLocation(equation[3]);
				Cell cell = spreadsheet[cellLocation.getRow()][cellLocation.getCol()];
				if(cell instanceof RealCell) {
					equation[3] = ((RealCell) cell).getDoubleValue() + "";
				}
			}
			if(equation[2].equals("+")) {
				equation[2] = Double.parseDouble(equation[1]) + Double.parseDouble(equation[3]) + "";
			}else if(equation[2].equals("-")) {
				equation[2] = Double.parseDouble(equation[1]) - Double.parseDouble(equation[3]) + "";
			}else if(equation[2].equals("*")) {
				equation[2] = Double.parseDouble(equation[1]) * Double.parseDouble(equation[3]) + "";
			}else {
				equation[2] = Double.parseDouble(equation[1]) / Double.parseDouble(equation[3]) + "";
			}
			copyFullText = equation[0] + " " + equation[2] + " " + equation[4];
			equation = copyFullText.split(" ", 5);
		}
		return Double.parseDouble(equation[1]);
	}
//support methods
	
	//determines if it is a sell location
	public boolean isLoc(String input) {
		int test = input.toUpperCase().charAt(0);
		if(test >= 65) {
			return true;
		}
		return false;
	}
	

//constructor
	public FormulaCell(String input, Cell[][] spreadsheet) {
		super(input);
		fullText = input;
		this.spreadsheet = spreadsheet;
	}
}
