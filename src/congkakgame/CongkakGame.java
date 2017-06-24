
package congkakgame;

import java.util.ArrayList;
import java.util.Scanner;

public class CongkakGame {
    public static final Scanner input = new Scanner(System.in);
    
    
    public static void main(String[] args) {
        welcomeMessage();
        selectMode();
        Board gameBoard = new Board();
        gameBoard.setBoard();
        //gameBoard.displayBoard(player1_row, player2_row);
        
        
        
        
    }
    private static void welcomeMessage(){
     System.out.println("========================");
     System.out.println("Welcome to Congkak Game!");
     System.out.println("========================");
    }    

//    public static void setBoard(){
//        Board gameBoard = new Board(2);
//        ArrayList<BoardHole> player1_row = new ArrayList<BoardHole>();
//        ArrayList<BoardHole> player2_row = new ArrayList<BoardHole>();
//        
//        for(int i =0; i < 7; i++){
//            player1_row.add(new BoardHole(4));
//        }
//        
//        for(int i =0; i < 7; i++){
//            player2_row.add(new BoardHole(4));
//        }
//        
//        Board player1_hole = new PlayerHole(0);
//        Board player2_hole = new PlayerHole(0);
//    }
    
    public static void selectMode(){
        System.out.println("Select game mode.\n1-Player1 Vs Player2\n2-Player1 Vs Computer");
        int mode = input.nextInt();
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
    }
    
    
    
}


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
