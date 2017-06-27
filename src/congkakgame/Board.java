
package congkakgame;

import java.util.ArrayList;
import java.util.Collections;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Board {
    ArrayList<BoardHole> player1_row ;
    ArrayList<BoardHole> player2_row ;
    HouseHole player1_hole;
    HouseHole player2_hole;
    public final Scanner input = new Scanner(System.in);
    int boardSize = -1;
    int beanNum = -1;
    int status = 0;
    public static boolean leftSow = true;
    public static boolean checkNextHole = false; 
    int selectBoard=-1;
    int mode = -1;
    public static String player1Name;
    public static String player2Name;

    public int getStatus() {
        return status;
    }
    
    public Board(ArrayList<BoardHole> p1BoardHole, ArrayList<BoardHole> p2BoardHole,HouseHole p1HouseHole, HouseHole p2HouseHole){
        this.player1_row = p1BoardHole;
        this.player2_row = p2BoardHole;
        this.player1_hole = p1HouseHole;
        this.player2_hole = p2HouseHole;
    }

    public ArrayList<BoardHole> getPlayer1_row() {
        return player1_row;
    }

    public ArrayList<BoardHole> getPlayer2_row() {
        return player2_row;
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
    
    public int getBoardSize() {
        return boardSize;
    }

    public void setBoardSize(int boardSize) {
        this.boardSize = boardSize;
    }

    public int getBeanNum() {
        return beanNum;
    }
    
    public void setBeanNum(int beanNum) {
        this.beanNum = beanNum;
    }

    public String getPlayer1Name() {
        return player1Name;
    }

    public String getPlayer2Name() {
        return player2Name;
    }
    
    public void setBoard(){
       
        player1_row.clear();
        player2_row.clear();
        player1_hole.setBean(0);
        player2_hole.setBean(0);

        while(selectBoard ==-1){  
            try{
                System.out.println("Select:\n1-Basic Board\n2-Intermediate Board");
                selectBoard = input.nextInt();
                if(selectBoard == 1 || selectBoard ==2){
                    break;
                }else{
                    selectBoard=-1;
                    System.out.println("Invalid option.\nPlease enter a valid option.");
                }
            }catch(InputMismatchException e){
                input.nextLine();
                System.out.println("Invalid option");
            }
        }
        if(selectBoard == 1){
            setBoardSize(7); 
            setBeanNum(4);
        }else if(selectBoard ==2){
            while(boardSize ==-1){  
                System.out.print("Please enter your board size (3 - 9): ");	
                try{
                    boardSize = input.nextInt();
                    if(boardSize >= 3 && boardSize <= 9){
                        break;
                    }else{
                        boardSize =-1;
                        System.out.println("Invalid board size input");
                    }
                }catch(InputMismatchException e){
                    input.nextLine();
                    System.out.println("Invalid board size input");
                }   
            } 
            setBoardSize(boardSize);
            while(beanNum ==-1){  
                System.out.print("How many beans in each hole (2 - 7): ");	// Prompt input	
                try{
                    beanNum = input.nextInt();// Get user input and store in beans
                    if(beanNum >= 2 && beanNum <= 7){
                        break;
                    }else{
                        beanNum = -1;
                        System.out.println("Invalid bean number input");
                    }
                }catch(InputMismatchException e){
                    input.nextLine();
                    System.out.println("Invalid bean number input");
                }
            }
            setBeanNum(beanNum);
        }
        
        System.out.println("Setting up board...");
        //Creating requested holes with the user's requested bean number for Game Board
        for(int i =0; i < getBoardSize(); i++){
            player1_row.add(new BoardHole(getBeanNum()));
        }
        for(int i =0; i < getBoardSize(); i++){
            player2_row.add(new BoardHole(getBeanNum()));
        }

    }
        public  void selectMode(){

        System.out.println("Select game mode.\n1-Player1 Vs Player2\n2-Player1 Vs Computer");
        while(mode == -1){
            try{
                mode = input.nextInt();
                if(mode==1){
                    System.out.println("Enter Player1's name:");
                    player1Name = input.next();
                    System.out.println("Enter Player2's name:");
                    player2Name = input.next();
                    break;
                }else if(mode==2){
                    System.out.println("Enter Player1's name:");
                    player1Name = input.next();
                    player2Name = "Computer";
                    break;
                }else{
                    mode = -1;
                    System.out.println("Invalid option\nPlease enter a valid option.");
                }  
            }catch(InputMismatchException e){
                input.nextLine();
                System.out.println("Invalid option\nPlease enter a valid option.");
            }
        } 
    }
    public void displayBoard(ArrayList<BoardHole> player1_row, ArrayList<BoardHole> player2_row,HouseHole player1_hole, HouseHole player2_hole, int boardSize ){
       for(int i = 0; i < boardSize + 1; i++) {
            System.out.print("--------");
        }
        for(int i = 0; i<boardSize-1 ; i++){
            System.out.printf("-");
        }
        System.out.printf("\n");
        //printing player1_row
        System.out.print("|     ");
        for(BoardHole i: player1_row) {
            if(i.getBean() >= 10) {
                System.out.printf("|  "+ i.getBean()+"  ");
            } else {
                System.out.printf("|  "+ i.getBean()+"   ");	
            }
        }
        System.out.print("|     |\n");
        //end of player1_row
        //printing row for houseHole
        if(player1_hole.getBean() >= 10) {
            System.out.printf("|  %d |", player1_hole.getBean());
        } else {
            System.out.printf("|  %d  |", player1_hole.getBean());
        }
		
        for(int i = 0; i < boardSize; i++) {
            System.out.printf("------");
        }
        for(int i = 0; i<boardSize-1 ; i++){
            System.out.printf("-");
        }
	if(player2_hole.getBean() >= 10) {
            System.out.printf("|  %d |", player2_hole.getBean());
        } else {
            System.out.printf("|  %d  |", player2_hole.getBean());
        }	
        //end of printing row for house hole
        //printing row for player2 holes
        System.out.print("\n|     ");
        for(BoardHole i: player2_row) {
            if(i.getBean() >= 10) {
                System.out.printf("|  "+ i.getBean()+"  ");
            } else {
                System.out.printf("|  "+ i.getBean()+"   ");	
            }
        }
        System.out.print("|     |\n");
        for(int i = 0; i < boardSize + 1; i++) {
            System.out.print("--------");
        }
	for(int i = 0; i<boardSize-1 ; i++){
            System.out.print("-");
        }
        System.out.print("\n");
    }
    
    public int selectHole(int playerTurn, int boardSize){
        int move = -1; // Initialize move variable to be returned
        String playerName;
        if(playerTurn ==1){
            playerName = player1Name;
        }else playerName = player2Name;
        if(mode ==1 || playerTurn ==1){ // for player (not computer)
            while(move < 0 || move > boardSize - 1) { // while loop check if the move is valid (only permit values between 1 to (max boardsize) inclusive)
                try { // try clause, catch error input (alphabet or symbol)
                    System.out.printf( playerName +", select the hole you would like to scoop (1 - %d): ", boardSize);// Prompt user to input a move
                    move = Integer.parseInt(input.next()); // Take in user input
                    move--;
                        if(move < 0 || move > boardSize - 1) {
                            System.out.println("Invalid option"); // Prompt error
                            continue;
                        } else if (playerTurn == 1 && (player1_row.get(move).getBean()==0)) { //cannot choose the hole without seed
                            System.out.println("Invalid option"); // Prompt error
                            move = -1;
                        } else if (playerTurn == 2  && (player2_row.get(move).getBean()==0)) { // cannot choose the hole without seed
                            System.out.println("Invalid option"); // Prompt error
                            move = -1;
                        } else if(move >= 0 && move <= boardSize - 1) { // Check for valid move
                            break; // Break the loop if the move is valid
                        }
                    } catch (NumberFormatException e) { // in case of error input (not number or too big/small number)
                        System.out.println("Invalid option"); // Prompt error
                        System.out.println("Please enter a valid number"); // Prompt error
                    }
            } // end while	
        }else if(mode ==2 && playerTurn ==2){ // for computer to choose seed
            move=-1;
            System.out.println("\nComputer's Turn\n");
            move = smarterMove();
            System.out.println("Hole selected: "+ move);
            move--;
        }
       	return move; // Return valid user input
    }
    public void checkGameStatus(int playerTurn){
        int count1 = 0; 
        int count2 = 0;
        int remainingSeed = 0;
        for(BoardHole i: player1_row) {
            if(i.getBean() == 0) {
                count1++;
            } else{
                remainingSeed+= i.getBean();
            }
        }
        for(BoardHole i: player2_row){
            if(i.getBean() == 0){
                count2++;
            }else{
                remainingSeed+= i.getBean();
            }
        }
        if(count1+count2 >= (boardSize*2) -1 && remainingSeed <=1){
            System.out.println("endgame");
            status = 0;//game stop
        }else if(count1 == boardSize && playerTurn ==1 && remainingSeed > 1){ // if player1 row is empty , and the next turn is player is player 1
            status = 1; // board holes player 1 become all empty, game does not stop
            System.out.println(Board.player1Name+"'s holes are all empty.");
        }else if(count2 == boardSize && playerTurn ==2 && remainingSeed >1){
            status = 2;// player 2's boardHole are all empty , game does not stop
            System.out.println(Board.player2Name+"'s holes are all empty.");
        }else{
            status = 3;
        }
    }

    public void getResult(){
        System.out.println("=====================");
        System.out.println("|     GAME END      |");
        System.out.println("=====================");
        if(player1_hole.getBean() == player2_hole.getBean()){
            System.out.println("DRAW");
        }else if(player1_hole.getBean()>player2_hole.getBean()){
            System.out.println("Congratulations,"+player1Name+"\nYou win the game!");
        }else {if(mode==1){
                System.out.println("Congratulations," +player2Name+"\n You win the game!");
            }else System.out.println("You lose the game.");
        }
    }
    private int smarterMove(){
        int tempMove =0;
        int minBean = player2_row.get(0).getBean(); // initialise minMove
        do{
            for(BoardHole i: player2_row) {
                if(i.getBean() == boardSize - (player2_row.indexOf(i) )+1) { 
                    tempMove = player2_row.indexOf(i);
                    break;
                }else if(i.getBean() != boardSize - (player2_row.indexOf(i)+1) ){
                    if(i.getBean() == 2*(boardSize - (player2_row.indexOf(i)+1))){
                        tempMove = player2_row.indexOf(i);
                        break;
                    }else{
                        if(i.getBean()<minBean){
                            minBean = i.getBean();
                            tempMove = player2_row.indexOf(i);
                            break;
                        }else{
                            tempMove = (int) (Math.random() * boardSize); 
                            break;
                        }
                    }
                }
            }
            
        }while(player2_row.get(tempMove).getBean()==0);
        return tempMove +1;
    }
}
    


    

