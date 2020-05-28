package textExcel;
/*
 * Andrew Yang
 * APCS
 * per 3
 * Text Excel
 * 
 * supposed to do a bunch of textexcel functions.
 * it passes cp5. This code is really redundant but its done. Like rly rly redundant. 
 */
import java.io.FileNotFoundException;
import java.util.Scanner;

// Update this file with your own code.

public class TextExcel
{

	public static void main(String[] args)
	{
		Scanner input = new Scanner(System.in);
		Spreadsheet sheet = new Spreadsheet();
		System.out.println("enter your first command");
		String command = input.nextLine();
		
		//while command == quit is false, go on.
		while(command.toLowerCase().equals("quit") != true){
			System.out.print(sheet.processCommand(command));
			command = input.nextLine();
		}
	}
}
