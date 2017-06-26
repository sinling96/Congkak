
package congkakgame;

public class BoardHole extends Hole{
    public BoardHole(int bean){
        super(bean);
    }
    public void removeAll(){
        bean = 0;
    }
    public void addBean(){
        this.bean = this.bean +1;
    }
}
