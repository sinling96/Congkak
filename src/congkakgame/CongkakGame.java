
package congkakgame;

import java.util.ArrayList;
import java.util.Scanner;

public class CongkakGame {
    public static final Scanner input = new Scanner(System.in);
    public static ArrayList<BoardHole> player1_row = new ArrayList<BoardHole>();
    public static ArrayList<BoardHole> player2_row = new ArrayList<BoardHole>();
    public static HouseHole player1_hole = new HouseHole(0);
    public static HouseHole player2_hole = new HouseHole(0);
    public static int mode;
    public static int boardSize;
    public static int beanNum;
    
    public static void main(String[] args) {
        welcomeMessage();
        selectMode();
        setBoard();
        Board gameBoard = new Board(player1_row, player2_row, player1_hole, player2_hole);
    }
    
    private static void welcomeMessage(){
     System.out.println("========================");
     System.out.println("Welcome to Congkak Game!");
     System.out.println("========================");
    }    

    public static void setBoard(){
       System.out.println("Setting up board...");
       System.out.print("Please enter your board size (3 - 9): ");	
       boardSize = input.nextInt();
       while(boardSize < 3 || boardSize > 9) {	// Check for out of range inputs
			System.out.println("Error board size input");
			System.out.print("Please enter your board size (3 - 9): ");
			boardSize = input.nextInt();
		}	// end while
       
       System.out.print("How many beans in each hole (2 - 7): ");	// Prompt input
		beanNum = input.nextInt();	// Get user input and store in beans
		while(beanNum < 2 || beanNum > 7) {	// Check for out of range inputs
			System.out.println("Error beans number input");
			System.out.print("How many beans in each hole (2 - 7): ");	// Prompt input
			beanNum = input.nextInt();
		}	// end while
       
        //Creating Holes for Game Board
        for(int i =0; i < boardSize; i++){
            player1_row.add(new BoardHole(beanNum));
            }
        for(int i =0; i < boardSize; i++){
            player2_row.add(new BoardHole(beanNum));
            }
    }
         
    
    public static int selectMode(){
        System.out.println("Select game mode.\n1-Player1 Vs Player2\n2-Player1 Vs Computer");
        mode = input.nextInt();
        if(mode==1){
            System.out.println("Enter Player1's name:");
            String player1Name = input.next();
            Player P1 = new Player(player1Name);
            System.out.println("Enter Player2's name:");
            String player2Name = input.next();
            Player P2 = new Player(player2Name);
        }else if(mode==2){
            System.out.println("Enter Player1's name:");
            String player1Name = input.next();
            Player P1 = new Player(player1Name);
        }
        return mode;
    }
   
}
    
    
//}
//
// Scanner inputFile = new Scanner(new File("C:/Test/test.txt"));
//   Map<String, Integer> counts = new HashMap<>();
//    while (inputFile.hasNextLine()) {
//        String line = inputFile.nextLine();         
//        for (String word : line.split(" ")) {
//            Integer count = counts.get(word);
//            counts.put(word, count == null ? 1 : count + 1);
//        }
//
//    }
//    System.out.println(counts);
//
