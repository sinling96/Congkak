
package congkakgame;

public class HouseHole extends Hole {

    public HouseHole(int seed) {
        super(seed);
    }

    @Override
    public int getBean() {
        return bean;
    }

    @Override
    public void setBean(int bean) {
        this.bean = bean;
    }
    
    // overriding method addSeed in Hole.java
    public void addBean(int bean) {
        this.bean = this.bean + bean;
    }
}
