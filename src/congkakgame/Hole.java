
package congkakgame;

public class Hole {
    int bean;
    public Hole(int bean){
        this.bean = bean ;
    }

    public int getBean() {
        return bean;
    }

    public void setBean(int bean) {
        this.bean = bean;
    }
    
    public void addBean(){
        this.bean = this.bean +1;
    }

    
}
