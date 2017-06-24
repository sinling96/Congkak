
package congkakgame;

public class Hole {
    int seed;
    public Hole(int seed){
        this.seed= seed ;
    }

    public int getSeed() {
        return seed;
    }

    public void setSeed(int seed) {
        this.seed = seed;
    }
    
    public void addSeed(){
        this.seed = this.seed +1;
    }

    
}
