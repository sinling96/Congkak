
package congkakgame;

import java.util.ArrayList;
import java.util.Scanner;

public class CongkakGame {
    public static final Scanner input = new Scanner(System.in);
    public static ArrayList<BoardHole> player1_row = new ArrayList<BoardHole>();
    public static ArrayList<BoardHole> player2_row = new ArrayList<BoardHole>();
    public static HouseHole player1_hole = new HouseHole(0);
    public static HouseHole player2_hole = new HouseHole(0);
    public static int boardSize;
    public static int beanNum;
    public static int playerTurn = 1;
    public static int index;
    public static int beanInHand;      
    public static boolean leftSow = true;
    public static int status = 0;
    public static boolean checkNextHole = false; 
    public static Player P1;
    public static Player P2;
    
    public static void main(String[] args) {
        String playNext = "N";
        welcomeMessage();
        do{
            System.out.println("Select game mode.\n1-Player1 Vs Player2\n2-Player1 Vs Computer");
           int mode = input.nextInt();
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
           setBoard();
           Board gameBoard = new Board(player1_row, player2_row, player1_hole, player2_hole, P1, P2);
           gameBoard.displayBoard(player1_row, player2_row, player1_hole, player2_hole, boardSize);
        
            do{    
                index = gameBoard.selectHole(mode,playerTurn, boardSize);
                do{
                    if(playerTurn ==1){
                        leftSow = true;
                        beanInHand = player1_row.get(index).getBean();//get the number of bean taken from the hole selected
                        player1_row.get(index).removeAll();// take all the seed from the hole selected by the user
                    }else{
                        leftSow = false;
                        beanInHand = player2_row.get(index).getBean();
                        player2_row.get(index).removeAll();
                    }
                    do{
                        sowing();
                        gameBoard.displayBoard(player1_row, player2_row, player1_hole, player2_hole, boardSize);
                        checkNextHole = checkNextHole();
                    }while(beanInHand>0);
                }while(checkNext2Hole()==false);//discontinue if the next hole is empty 
                if(playerTurn ==1){
                    playerTurn =2;
                }else playerTurn =1;
                status = checkGameStatus();
                gameBoard.displayBoard(player1_row, player2_row, player1_hole, player2_hole, boardSize);
            }while(status ==0);//none of the players' boardholes are all empty
        if(status!=0){
            result();
            System.out.println("Play again? (Y/N)");
            playNext = input.nextLine(); 
        }
        }while( playNext != "N" || playNext != "n" );
    }    
    private static void welcomeMessage(){
     System.out.println("========================");
     System.out.println("Welcome to Congkak Game!");
     System.out.println("========================");
    }    

    public static void setBoard(){
       System.out.println("Setting up board...");
        player1_row.clear();
        player2_row.clear();
        player1_hole.setBean(0);
        player2_hole.setBean(0);
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

    public static void sowing(){

        while(beanInHand >0) {      // while there is still beans in hand
            if(leftSow == true) {	// player 1 turn
                if(index>0){
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
                if(index<boardSize){
                    player2_row.get(index).addBean();
                    beanInHand--;
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
    public static boolean checkNextHole(){
        if(beanInHand ==0){
            if(leftSow == true){
                if(index>0 && player1_row.get(index-1).getBean()!=0){ //check whether the next hole has bean or not
                    beanInHand += player1_row.get(index-1).getBean(); 
                    System.out.println("1.You get extra "+player1_row.get(index-1).getBean()+" beans");
                    player1_row.get(index-1).removeAll(); 
                    index -=1;
                    checkNextHole = true;
                }else if(index ==0 && player2_row.get(index).getBean()!=0){//if next hole is 0(player2row) and does not contain anything
                    beanInHand += player2_row.get(index).getBean();
                    System.out.println("2.You get extra "+player2_row.get(index).getBean()+" beans");
                    player2_row.get(index).removeAll();
                    leftSow = false;
                    checkNextHole = true;
                }
            }else if (leftSow == false){
                if(index < (boardSize-1) && player2_row.get(index+1).getBean()!=0){
                    beanInHand += player2_row.get(index+1).getBean();
                    System.out.println("3.You get extra "+player2_row.get(index+1).getBean()+" beans");
                    player2_row.get(index+1).removeAll();
                    index+=1;
                    checkNextHole = true;
                }else if(index == (boardSize-1) && player1_row.get(index).getBean()!=0){ 
                    beanInHand += player1_row.get(index).getBean();
                    System.out.println("4.You get extra "+player1_row.get(index).getBean()+" beans");
                    player1_row.get(index).removeAll();
                    leftSow = true;
                    checkNextHole = true;
                }
            }
        }
        return checkNextHole;
    }
    public static boolean checkNext2Hole(){
        //if next hole is empty ,take all of the bean in the next hole, put into househole
        boolean check2Hole= false;// false means next Hole contains bean
        int totalBean = 0;
        if(beanInHand==0){
            if(leftSow == true){
                if(index > 1 && player1_row.get(index-1).getBean()==0){
                    totalBean = player1_row.get(index-2).getBean();
                    player1_row.get(index-2).removeAll();
                    check2Hole = true;
                }else if(index == 1 && player1_row.get(index-1).getBean()==0){
                    totalBean = player2_row.get(index-1).getBean();
                    player2_row.get(index-1).removeAll();
                    check2Hole = true;
                }else if(index == 0 && player2_row.get(index).getBean()==0){
                    totalBean = player2_row.get(index+1).getBean();
                    player2_row.get(index+1).removeAll();
                    check2Hole = true;
                }
            }else if(leftSow == false){
                if(index < (boardSize-2) && player2_row.get(index+1).getBean()==0){
                    totalBean = player2_row.get(index+2).getBean();
                    player2_row.get(index+2).removeAll();
                    check2Hole = true;
                }else if(index ==(boardSize-2) && player2_row.get(index+1).getBean()==0){
                    totalBean = player1_row.get(index+1).getBean();
                    player1_row.get(index+1).removeAll();
                    check2Hole = true;
                }else if(index == (boardSize-1) && player1_row.get(index).getBean()==0){
                    totalBean = player1_row.get(index-1).getBean();
                    player1_row.get(index-1).removeAll();
                    check2Hole = true;
                }
            }
           if(playerTurn ==1){
               if(totalBean!=0){
                   player1_hole.addBean(totalBean);
                   System.out.println("Congratulations!\n"+"Player 1 earned " + totalBean + " beans.");
               }else System.out.println("Sorry, Player 1.\n No bean is awarded.");
               
           }else{
               if(totalBean != 0){
                   player2_hole.addBean(totalBean);     
                    System.out.println("Congratulations!\n"+"Player 2 earned " + totalBean + " beans.");
               }else System.out.println("Sorry, Player 2.\nNo bean is awarded.");
                
           } 
        }
        return check2Hole;
    }
    public static int checkGameStatus(){
        int count1 = 0; 
        int count2 = 0;
        for(BoardHole i: player1_row) {
                if(i.getBean() == 0) {
                        count1++;
                } 
        }
        for(BoardHole i: player2_row){
            if(i.getBean() == 0){
                count2++;
            }
        }
    
        if(count1 == boardSize && playerTurn ==1){ // if player1 row is empty , and the next turn is player is player 1
            status = 1; // board holes player 1 become all empty
        }else if(count2 == boardSize && playerTurn ==2){
            status = 2;// player 2's boardHole are all empty
        }else status = 0;
        
        return status;
    }
    
    public static void result(){
        if(player1_hole.getBean() == player2_hole.getBean()){
            System.out.println("DRAW");
        }else if(player1_hole.getBean()>player2_hole.getBean()){
            System.out.println("Player 1 win!");
        }else System.out.println("Playern 2 win!");
    }

}
