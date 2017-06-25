
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
    public static int playerTurn = 1;
    public static int index;
    public static int beanInHand;
    
    public static void main(String[] args) {
        welcomeMessage();
        mode = selectMode();   
        setBoard();
        Board gameBoard = new Board(player1_row, player2_row, player1_hole, player2_hole);
        gameBoard.displayBoard(player1_row, player2_row,player1_hole,player2_hole, boardSize);
        index = gameBoard.selectHole(mode,playerTurn, boardSize);
        beanInHand = player1_row.get(index).getBean();//get the number of bean taken from the hole selected
        player1_row.get(index).removeAll();// take all the seed from the hole selected by the user
        gameBoard.displayBoard(player1_row, player2_row, player1_hole, player2_hole, boardSize);
        sowing();
         gameBoard.displayBoard(player1_row, player2_row, player1_hole, player2_hole, boardSize);
        //switch player playerTurn = 2; 
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
       
        //Creating requested holes with the user's requested bean number for Game Board
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
            Player P1 = new Player(player1Name,1);
            System.out.println("Enter Player2's name:");
            String player2Name = input.next();
            Player P2 = new Player(player2Name,2);
        }else if(mode==2){
            System.out.println("Enter Player1's name:");
            String player1Name = input.next();
            Player P1 = new Player(player1Name,1);
            Player P2 = new Player("Computer",2);
        }
        return mode;
    }
    public static void sowing(){
        
        boolean leftSow = true;
        boolean end = false;
        if(playerTurn ==1){
            leftSow = true;
        }else leftSow = false;
        
        while(beanInHand >0) {	// while there is still beans in hand
            if(leftSow == true) {	// player 1 turn
                if((index)>0){
                    index--;
                    player1_row.get(index).addBean();      
                    beanInHand--;
                    if(beanInHand>0 && (index)==0){
                        leftSow = false;
                        index=-1;
                    }
                }else{
                    leftSow = false;
                    index = -1;
                }
            }
            if(leftSow == false){
                index++;
                if(index>=0 || index<boardSize){
                    player2_row.get(index).addBean();
                    beanInHand--;}
                    if(beanInHand>0 && index==boardSize-1){
                       leftSow = true;
                       index = boardSize;
                    }
                }else{
                    leftSow = true;
                    index = boardSize;
                }
        }
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
