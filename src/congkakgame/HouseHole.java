
package congkakgame;

public class HouseHole extends Hole {

    public HouseHole(int seed) {
        super(seed);
    }
    // overriding method addSeed in Hole.java
    public void addSeed(int seed) {
        this.seed = this.seed + seed;
    }
}
