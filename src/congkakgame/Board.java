
package congkakgame;

import java.util.ArrayList;

public class Board {
    ArrayList<BoardHole> player1_row = new ArrayList<BoardHole>();
    ArrayList<BoardHole> player2_row = new ArrayList<BoardHole>();
    HouseHole player1_hole = new HouseHole(0);
    HouseHole player2_hole = new HouseHole(0);
    
    public Board(ArrayList<BoardHole> p1BoardHole, ArrayList<BoardHole> p2BoardHole,HouseHole p1HouseHole, HouseHole p2HouseHole){
        this.player1_row = p1BoardHole;
        this.player2_row = p2BoardHole;
        this.player1_hole = p1HouseHole;
        this.player2_hole = p2HouseHole;
    }

    public ArrayList<BoardHole> getPlayer1_row() {
        return player1_row;
    }

    public void setPlayer1_row(ArrayList<BoardHole> player1_row) {
        this.player1_row = player1_row;
    }

    public ArrayList<BoardHole> getPlayer2_row() {
        return player2_row;
    }

    public void setPlayer2_row(ArrayList<BoardHole> player2_row) {
        this.player2_row = player2_row;
    }

    public HouseHole getPlayer1_hole() {
        return player1_hole;
    }

    public void setPlayer1_hole(HouseHole player1_hole) {
        this.player1_hole = player1_hole;
    }

    public HouseHole getPlayer2_hole() {
        return player2_hole;
    }

    public void setPlayer2_hole(HouseHole player2_hole) {
        this.player2_hole = player2_hole;
    }
    
    public void displayBoard(ArrayList<BoardHole> player1_row, ArrayList<BoardHole> player2_row,int boardSize ){
       // System.out.printf("-------------------------------------------------------\n| 	");
    }
//   		 
           // System.out.println("| "+ player1_row[]);    
        
   	 
//   	 System.out.print("| 	|\n");
//   	 System.out.printf("|  %d  |-----------------------------------------|  %d  |\n| 	", player2, player1);
//   	 for(Board values i : player2_hole) {
//   		 System.out.printf("|  %d  ", values);    
//   	 }
//   	 System.out.print("| 	|\n");
//   	 System.out.println("-------------------------------------------------------\n");
//         System.out.println(playerName);
//    }
}
   
    

