
package congkakgame;

import java.util.ArrayList;

public class Board {
    public void setBoard(){
        ArrayList<BoardHole> player1_row = new ArrayList<BoardHole>();
        ArrayList<BoardHole> player2_row = new ArrayList<BoardHole>();
        
        for(int i =0; i < 7; i++){
            player1_row.add(new BoardHole(4));
        }
        
        for(int i =0; i < 7; i++){
            player2_row.add(new BoardHole(4));
        }
        
        Hole player1_hole = new HouseHole(0);
        Hole player2_hole = new HouseHole(0);
        System.out.println("| "+ player1_row);  
    }
    public void displayBoard(ArrayList<BoardHole> player1_row, ArrayList<BoardHole> player2_row){
   	 System.out.printf("-------------------------------------------------------\n| 	");
   	 
   		 System.out.println("| "+ player1_row);    
        
   	 
//   	 System.out.print("| 	|\n");
//   	 System.out.printf("|  %d  |-----------------------------------------|  %d  |\n| 	", player2, player1);
//   	 for(Board values i : player2_hole) {
//   		 System.out.printf("|  %d  ", values);    
//   	 }
//   	 System.out.print("| 	|\n");
//   	 System.out.println("-------------------------------------------------------\n");
//         System.out.println(playerName);
    }
}
   
    

