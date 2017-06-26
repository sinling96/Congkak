package congkakgame;

import java.util.Scanner;

public class InputHandlerForPrimitiveTypes {
	
	private static Scanner scan = new Scanner(System.in);
	public static int readIntegerInput()
	{
		int userInput = 0;
		boolean validInput = false;
		while(!validInput)
		{
			try{
				userInput = Integer.parseInt(scan.nextLine());
				validInput = true;
			}
			catch(NumberFormatException e)
			{
				System.out.println("Error, you did not enter in a proper number!");
				System.out.println("Please enter a valid number: ");
			}
			catch(Exception e)
			{
				System.out.println("Error occured!");
			}
		}
		return userInput;
	}
	

}
