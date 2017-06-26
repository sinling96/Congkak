
package congkakgame;
public class Player {
    String name;
    private final int playerTurn;
    
    public Player(String name, int playerTurn){
        this.name = name;
        this.playerTurn = playerTurn;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        
        this.name = name;
    }
}
