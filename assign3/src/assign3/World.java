package assign3;

import java.util.Random;

/**
 * The World class is used to represent the world for simlation of artificial
 * life with goats and grasses. The world of fixed size (20x35) is expected to
 * be initialized with the number of passes and the seed for the random number
 * generator used for the simulation. The class will provide a mainLoop method
 * with an argument about the interval for displaying the world to the standard
 * output. The world consists of a 2D grid, in each of which, a creature (a
 * grass or a goat) may reside. If no creature resides, the slot will be null.
 * 
 * @author li
 *
 */

public class World {

    static int mapW = 35, mapH = 20, initGoat = 5, initGrass = 10;
    int _numPasses;
    Creature[][] map = new Creature[mapH][mapW];
    Random r;

    /**
     * The constructor of the World containing two parameters:
     * 
     * @param numPasses number of passes used for the whole simulation
     * @param seed      the seed to initialize the random number generator for the
     *                  simulation
     */
    public World(int numPasses, int seed) {
        if (numPasses < 1)
            _numPasses = 1;
        else
            _numPasses = numPasses;
        r = new Random(seed);

        // set goats
        for (int i = 0; i < initGoat;) {
            int x = r.nextInt(mapH), y = r.nextInt(mapW);
            if (map[x][y] == null) {
                map[x][y] = new Goat(-1);
                ++i;
            }
        }
        // set grasses
        for (int i = 0; i < initGrass;) {
            int x = r.nextInt(mapH), y = r.nextInt(mapW);
            if (map[x][y] == null) {
                map[x][y] = new Grass(-1);
                ++i;
            }
        }

    }

    /**
     * the main loop use for the simulation and showing the result to the screen
     * periodically.
     * 
     * @param displayInterval the number of passes for each display of the world
     */

    public void mainLoop(int displayInterval) {
        if (displayInterval < 0)
            displayInterval = 1;
        for (int _curNPass = 0; _curNPass < _numPasses; ++_curNPass) {

            for (int pI = 0; pI < mapH; ++pI) {
                for (int pJ = 0; pJ < mapW; ++pJ) {
                    int i = pI, j = pJ;
                    if (map[i][j] == null || map[i][j].lastMovedPass == _curNPass)
                        continue;

                    int target_i = i, target_j = j;
                    switch (r.nextInt(4)) {
                    case 0:
                        ++target_i;
                        break;
                    case 1:
                        --target_i;
                        break;
                    case 2:
                        ++target_j;
                        break;
                    case 3:
                        --target_j;
                        break;
                    }
                    if (target_i >= 0 && target_i < mapH && target_j >= 0 && target_j < mapW
                            && map[i][j].cellValid(map[target_i][target_j])) {
                        
                        map[target_i][target_j] = null;

                        if (map[i][j] instanceof Goat) {
                            if (map[i][j].isPregnant()) {
                                map[target_i][target_j] = new Goat(_curNPass);
                            } else {
                                map[target_i][target_j] = map[i][j];
                                map[i][j] = null;
                                map[target_i][target_j].updateStatus(_curNPass);
                                if (map[target_i][target_j].isDead()) {
                                    map[target_i][target_j] = null;
                                }
                                continue;

                            }
                        } else if (map[i][j] instanceof Grass)
                            if (map[i][j].isPregnant()){
                                map[target_i][target_j] = new Grass(_curNPass);
                            }

                    }
                    map[i][j].updateStatus(_curNPass);

                    if (map[i][j].isDead()) {
                        map[i][j] = null;
                    }
                }
            }
            if (_curNPass % displayInterval == 0)
                printWorld();

        }
    }

    public void printWorld() {
        System.out.print(" ");
        for (int i = 0; i < mapW; ++i)
            System.out.print(" " + i % 10);
        System.out.println("");
        for (int i = 0; i < mapH; ++i) {
            System.out.print(i % 10 + " ");
            for (int j = 0; j < mapW; ++j) {
                if (map[i][j] == null)
                    System.out.print("  ");
                else
                    System.out.print(map[i][j] + " ");
            }
            System.out.println("");
        }
        System.out.println("------------------------------------------------------------------------");
    }

}

abstract class Creature {
    int age;
    int lastMovedPass;

    Creature() {
        age = 0;
    };

    abstract boolean isPregnant();

    abstract boolean isDead();

    abstract boolean cellValid(Creature cell);

    public void updateStatus(int nPass) {
        ++this.age;
        this.lastMovedPass = nPass;
    };
};

class Grass extends Creature {

    Grass(int nPass) {
        this.lastMovedPass = nPass;
    }

    public String toString() {
        return "I";
    }

    boolean isPregnant() {
        return this.age >= 3 && this.age <= 5;
    }

    boolean isDead() {
        return this.age > 6;
    }

    boolean cellValid(Creature cell) {
        return cell == null;
    }

}

class Goat extends Creature {

    int satiety;

    Goat(int nPass) {
        this.satiety = 20;
        this.lastMovedPass = nPass;
    }

    public String toString() {
        return "X";
    }

    public boolean isPregnant() {
        return this.age >= 50 && this.age <= 55;
    }

    public boolean isDead() {
        return this.age > 70 || this.satiety <= 0;
    }

    public void updateStatus(int nPass) {
        super.updateStatus(nPass);

        --this.satiety;
    }

    boolean cellValid(Creature cell) {

        if (cell == null)
            return true;
        if ( cell instanceof Grass ){
            this.satiety += 5;
            return true; 
        }
        return false;
    }

}