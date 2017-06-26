
package congkakgame;

import java.util.ArrayList;
import java.util.Scanner;

public class Board {
    ArrayList<BoardHole> player1_row = new ArrayList<BoardHole>();
    ArrayList<BoardHole> player2_row = new ArrayList<BoardHole>();
    HouseHole player1_hole = new HouseHole(0);
    HouseHole player2_hole = new HouseHole(0);
    Player p1Player;
    Player p2Player;
    public final Scanner input = new Scanner(System.in);
    
    public Board(ArrayList<BoardHole> p1BoardHole, ArrayList<BoardHole> p2BoardHole,HouseHole p1HouseHole, HouseHole p2HouseHole, Player p1Player, Player p2Player){
        this.player1_row = p1BoardHole;
        this.player2_row = p2BoardHole;
        this.player1_hole = p1HouseHole;
        this.player2_hole = p2HouseHole;
        this.p1Player = p1Player;
        this.p2Player = p2Player;
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

    public void displayBoard(ArrayList<BoardHole> player1_row, ArrayList<BoardHole> player2_row,HouseHole player1_hole, HouseHole player2_hole, int boardSize ){
       for(int i = 0; i < boardSize + 1; i++) {
			System.out.print("------");
        }
	System.out.println("-------");
        //printing player1_row
        System.out.print("|     ");
        for(BoardHole i: player1_row) {
                if(i.getBean() >= 10) {
                        System.out.printf("| "+ i.getBean()+"   ");
                } else {
                        System.out.printf("|  "+ i.getBean()+"  ");	
                }
        }
       System.out.print("|     |\n");
       //end of player1_row
       //printing row for houseHole
        if(player1_hole.getBean() >= 10) {
                System.out.printf("| %d  |", player1_hole.getBean());
        } else {
                System.out.printf("|  %d  |", player1_hole.getBean());
        }
		
        for(int i = 0; i < boardSize - 1; i++) {
                System.out.printf("------");
        }
        System.out.print("-----");
	if(player2_hole.getBean() >= 10) {
                System.out.printf("| %d  |", player2_hole.getBean());
        } else {
                System.out.printf("|  %d  |", player2_hole.getBean());
        }	
        //end of printing row for house hole
        //printing row for player2 holes
        System.out.print("\n|     ");
        for(BoardHole i: player2_row) {
                if(i.getBean() >= 10) {
                        System.out.printf("| "+ i.getBean()+"   ");
                } else {
                        System.out.printf("|  "+ i.getBean()+"  ");	
                }
        }
        System.out.print("|     |\n");
        for(int i = 0; i < boardSize + 1; i++) {
			System.out.print("------");
        }
	System.out.println("-------");
        // end of printing row for player2_row
    }
    
    public int selectHole(int mode, int playerTurn, int boardSize){
        int move = -1; // Initialize move variable to be returned
        if(mode ==1 || playerTurn ==1){ // for player (not computer)
            while(move < 0 || move > boardSize - 1) { // while loop check if the move is valid (only permit values between 1 to (max boardsize) inclusive)
			try { // try clause, catch error input (alphabet or symbol)
                                System.out.printf("Player "+ playerTurn +", select the hole you would like to scoop (1 - %d): ", boardSize);// Prompt user to input a move
                                move = Integer.parseInt(input.next()); // Take in user input
				move--;
				
				if(move < 0 || move > boardSize - 1) {
					System.out.println("Invalid move!!"); // Prompt error
					continue;
                                } else if (playerTurn == 1 && (player1_row.get(move).getBean()==0)) { //cannot choose the hole without seed
					System.out.println("Invalid move!!"); // Prompt error
					move = -1;
				} else if (playerTurn == 2  && (player2_row.get(move).getBean()==0)) { // cannot choose the hole without seed
					System.out.println("Invalid move!!"); // Prompt error
					move = -1;
				} else if(move >= 0 && move <= boardSize - 1) { // Check for valid move
					break; // Break the loop if the move is valid
				}
				
                            } catch (NumberFormatException e) { // in case of error input (not number or too big/small number)
				System.out.println("Invalid input!!"); // Prompt error
				System.out.println("Please enter a valid number!!"); // Prompt error
                                }
            } // end while
		
        }else if(mode ==2 && playerTurn ==2){ // for computer to choose seed
		while(move < 0 || move > boardSize - 1) { // while loop check if the move is valid (only permit values between 1 to 7 inclusive
                        move = (int) (Math.random() * boardSize);//need to modified		
			if(move < 0 || move > boardSize - 1) {
				continue;
			} else if (player2_row.get(move).equals(0)) {
				move = -1;
			} else if(move >= 0 && move <= boardSize - 1) { // Check for valid move
				break; // Break the loop if the move is valid
			}		
		} // end while
        }
       	return move; // Return valid user input
    }
    
   
}
    


    

