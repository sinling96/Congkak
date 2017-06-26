
package congkakgame;

public class HouseHole extends Hole {

    public HouseHole(int seed) {
        super(seed);
    }
    // overriding method addSeed in Hole.java
    public void addBean(int bean) {
        this.bean = this.bean + bean;
    }
}
