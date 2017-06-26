
package congkakgame;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class CongkakGame {
    public static final Scanner input = new Scanner(System.in);
    public static ArrayList<BoardHole> player1_row = new ArrayList<BoardHole>();
    public static ArrayList<BoardHole> player2_row = new ArrayList<BoardHole>();
    public static HouseHole player1_hole = new HouseHole(0);
    public static HouseHole player2_hole = new HouseHole(0);
    public static Player P1;
    public static Player P2;
    public static int playerTurn = 1;
    public static int index;
    public static int beanInHand;      
    public static boolean leftSow = true;
    public static int mode = -1;
    public static int boardSize;
    
    public static void main(String[] args) {
 
        welcomeMessage();
        do{
           selectMode();
           Board gameBoard = new Board(player1_row, player2_row, player1_hole, player2_hole, P1, P2);
           gameBoard.setBoard();
           boardSize = gameBoard.getBoardSize();
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
                        checkNextHole();
                    }while(beanInHand>0);
                }while(checkNext2Hole()==false);//discontinue if the next hole is empty 
                if(playerTurn ==1){ // switch the player
                    playerTurn =2;
                }else playerTurn =1;
                gameBoard.checkGameStatus(playerTurn);
                gameBoard.displayBoard(player1_row, player2_row, player1_hole, player2_hole, boardSize);
            }while(gameBoard.getStatus() ==0);//none of the players' boardholes are all empty
            if(gameBoard.getStatus()!=0){
                gameBoard.getResult();
                input.nextLine();
                askForNextPlay();
            }
        }while(askForNextPlay().equalsIgnoreCase("Y")  );
    }    
    
    private static void welcomeMessage(){
     System.out.println("========================");
     System.out.println("Welcome to Congkak Game!");
     System.out.println("========================");
    }    
    
    public static void selectMode(){
        System.out.println("Select game mode.\n1-Player1 Vs Player2\n2-Player1 Vs Computer");
        while(mode == -1){
            try{
                mode = input.nextInt();
                if(mode==1){
                    System.out.println("Enter Player1's name:");
                    String player1Name = input.next();
                    P1.setName(player1Name);
                    System.out.println("Enter Player2's name:");
                    String player2Name = input.next();
                    P2.setName(player2Name);
                    break;
                }else if(mode==2){
                    System.out.println("Enter Player1's name:");
                    String player1Name = input.next();
                    P1.setName(player1Name);
                    P2.setName("Computer");
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
    
    public static void checkNextHole(){
        if(beanInHand ==0){
            if(leftSow == true){
                if(index>0 && player1_row.get(index-1).getBean()!=0){ //check whether the next hole has bean or not
                    beanInHand += player1_row.get(index-1).getBean(); 
                    System.out.println("Great!You get extra "+player1_row.get(index-1).getBean()+" beans");
                    player1_row.get(index-1).removeAll(); 
                    index -=1;
                }else if(index ==0 && player2_row.get(index).getBean()!=0){//if next hole is 0(player2row) and does not contain anything
                    beanInHand += player2_row.get(index).getBean();
                    System.out.println("Bravo!You get extra "+player2_row.get(index).getBean()+" beans");
                    player2_row.get(index).removeAll();
                    leftSow = false;
                }
            }else if (leftSow == false){
                if(index < (boardSize-1) && player2_row.get(index+1).getBean()!=0){
                    beanInHand += player2_row.get(index+1).getBean();
                    System.out.println("Good job!You get extra "+player2_row.get(index+1).getBean()+" beans");
                    player2_row.get(index+1).removeAll();
                    index+=1;
                }else if(index == (boardSize-1) && player1_row.get(index).getBean()!=0){ 
                    beanInHand += player1_row.get(index).getBean();
                    System.out.println("Yay!You get extra "+player1_row.get(index).getBean()+" beans");
                    player1_row.get(index).removeAll();
                    leftSow = true;
                }
            }
        }
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
    
    public static String askForNextPlay(){
        String playNext = "A";
        while(playNext.equalsIgnoreCase("A")  ){
            System.out.println("Play again? (Y/N)");
            try{
                playNext = input.nextLine();
                if(playNext.equalsIgnoreCase("Y")){
                    break;
                }else if(playNext.equalsIgnoreCase("N")){
                    break;
                }else{
                    throw new IllegalArgumentException("Invalid key.\nPlease enter a valid option.");
                }
            }catch (IllegalArgumentException e){
                playNext="A";
                System.out.println(e.getMessage());
            }
        }
        return playNext;
    }
    
}
