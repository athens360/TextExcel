package textExcel;

// Update this file with your own code.

public class Spreadsheet implements Grid
{
	//field
	private Cell[][] spreadsheet = new Cell[20][12];
	
	//methods

	@Override
	public String processCommand(String command)
	{
		String[] commandArray = command.split(" ", 3);
		int commandLength = commandArray.length;
		
		//only works with text cells for now
		if(commandLength == 3) {
			//textCell 
			if(commandArray[2].substring(0,1).equals("\"")) {
				SpreadsheetLocation cell = new SpreadsheetLocation(commandArray[0].toUpperCase());
				spreadsheet[cell.getRow()][cell.getCol()] = new TextCell(commandArray[2]);
				return getGridText();
			//formula cell
			}else if(commandArray[2].substring(0,1).equals("(")) {
				SpreadsheetLocation cell = new SpreadsheetLocation(commandArray[0].toUpperCase());
				spreadsheet[cell.getRow()][cell.getCol()] = new FormulaCell(commandArray[2], spreadsheet);
				return getGridText();
			//percent cell
			}else if(commandArray[2].substring(commandArray[2].length() - 1).equals("%")) {
				SpreadsheetLocation cell = new SpreadsheetLocation(commandArray[0].toUpperCase());
				spreadsheet[cell.getRow()][cell.getCol()] = new PercentCell(commandArray[2]);
				return getGridText();
			}
			//value cell
			SpreadsheetLocation cell = new SpreadsheetLocation(commandArray[0].toUpperCase());
			spreadsheet[cell.getRow()][cell.getCol()] = new ValueCell(commandArray[2]);
			return getGridText();
		}
		if(commandLength == 2) {
			SpreadsheetLocation cell = new SpreadsheetLocation(commandArray[1].toUpperCase());
			spreadsheet[cell.getRow()][cell.getCol()] = new EmptyCell();
			return getGridText();
		}
		if(command.toUpperCase().equals("CLEAR")) {
			Spreadsheet clearedSheet = new Spreadsheet();
			spreadsheet = clearedSheet.clearSupportMethod();
			return getGridText();
		}
		SpreadsheetLocation cell = new SpreadsheetLocation(command.toUpperCase());
		return spreadsheet[cell.getRow()][cell.getCol()].fullCellText();
	}

	@Override
	public int getRows()
	{
		// TODO Auto-generated method stub
		return 20;
	}

	@Override
	public int getCols()
	{
		// TODO Auto-generated method stub
		return 12;
	}

	@Override
	public Cell getCell(Location loc)
	{
		int row = loc.getRow();
		int col = loc.getCol();
		return spreadsheet[row][col];
	}

	public Cell[][] clearSupportMethod(){
		return spreadsheet;
	}
	@Override
	public String getGridText()
	{
		String finalString = "";
		
		String header = "   |";
		for(int i = 0; i < 12; i++) {
			header += (char)('A' + i);
			header += "         |";
		}
		//I know its redundant but I can't think of a digit way to make it one loop without making it super complicated and adding in more levels of
		//loops to see if its single digit row or double digit and then add spaces accordingly.
		String singleDigitRows = "";
		for(int row = 1; row <= 9; row++) {
			singleDigitRows += row + "  |";
			for(int col = 1; col <= 12; col++) {
				singleDigitRows += spreadsheet[row - 1][col - 1].abbreviatedCellText() + "|";
			}
			singleDigitRows += "\n";
		}

		String doubleDigitRows = "";
		for(int row = 10; row <= 20; row++) {
			doubleDigitRows += row + " |";
			for(int col = 1; col <= 12; col++) {
				doubleDigitRows += spreadsheet[row - 1][col - 1].abbreviatedCellText() + "|";
			}
			doubleDigitRows += "\n";
		}
		
		finalString += header + "\n" + singleDigitRows + doubleDigitRows;
		
		return finalString;
	}
	
	//constructor
	public Spreadsheet() {
		for(int r = 0; r < 20; r++) {
			for(int c = 0; c < 12; c++) {
				spreadsheet[r][c] = new EmptyCell();
			}
		}
	}
}
