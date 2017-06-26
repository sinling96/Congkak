
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
    public static boolean leftSow = true;
    public static boolean status = false;
    public static boolean checkNextHole = false; 
    
    public static void main(String[] args) {
        welcomeMessage();
        mode = selectMode();   
        setBoard();
        Board gameBoard = new Board(player1_row, player2_row, player1_hole, player2_hole);
        gameBoard.displayBoard(player1_row, player2_row, player1_hole, player2_hole, boardSize);
        do{    
            do{

                index = gameBoard.selectHole(mode,playerTurn, boardSize);
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
                   
               // }while(checkNextHole == true);//continue if the next hole is not empty
            }while(checkNext2Hole()==false);//discontinue if the next hole is empty 
            if(playerTurn ==1){
                playerTurn =2;
            }else playerTurn =1;
            status = checkGameStatus();
            gameBoard.displayBoard(player1_row, player2_row, player1_hole, player2_hole, boardSize);
        }while(status == true);
       
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
                    check2Hole = true;
                }else if(index == 1 && player1_row.get(index-1).getBean()==0){
                    totalBean = player2_row.get(index-1).getBean();
                    check2Hole = true;
                }else if(index == 0 && player2_row.get(index).getBean()==0){
                    totalBean = player2_row.get(index+1).getBean();
                    check2Hole = true;
                }
            }else if(leftSow == false){
                if(index < (boardSize-1) && player2_row.get(index+1).getBean()==0){
                    totalBean = player2_row.get(index+2).getBean();
                    check2Hole = true;
                }else if(index ==(boardSize-2) && player2_row.get(index+1).getBean()==0){
                    totalBean = player1_row.get(index+1).getBean();
                    check2Hole = true;
                }else if(index == (boardSize-1) && player1_row.get(index).getBean()==0){
                    totalBean = player1_row.get(index-1).getBean();
                    check2Hole = true;
                }
            }
           if(playerTurn ==1){
               player1_hole.addBean(totalBean);
           }else player2_hole.addBean(totalBean);     
        }
        return check2Hole;
    }
    public static boolean checkGameStatus(){
        int count = 0; 
        for(BoardHole i: player1_row) {
                if(i.getBean() == 0) {
                        count++;
                } 
        }
        for(BoardHole i: player2_row){
            if(i.getBean() == 0){
                count++;
            }
        }
        
        if(count == (boardSize * 2)){
            status = true;
        }else status = false;
        
        return status;
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
