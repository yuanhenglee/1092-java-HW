package assign3;

/**
 * 
 * The ALife class is used to do the third homework assignment. 
 * This class will be used to enter the main function and set up the world 
 * for the given specification of simulation passed from the command line arguments. 
 * The parameters include the number of passes for the whole simulation, the number
 * passes for each display of the world, and the seed to initialize the random number 
 * generator.
 * @author li
 *
 */
public class ALife {
	
    /**
     * The main function for ALife
     * @param args the first argument is the number of passes for the whole simulation
     * 				the second argument is the number of passes between each display of the world
     * 				the third argument is the seed to initialize the random number generator.
     */
    public static void main(String[] args) {
	
	int numPasses=1; // first argument, can be optional if the second and third are not specified
	int displayInterval=1; // second argument, can be optional if the third is not specified.
	int seed=0;  // third argument, optional
	if (args.length>3) {
	    System.out.println("Usage: ALife [passes [display [seed]]]");
	} else if (args.length==3){
	    seed = Integer.valueOf(args[2]);
	}
	if (args.length>2) 
	    displayInterval = Integer.valueOf(args[1]);
	if (args.length>1) 
	    numPasses = Integer.valueOf(args[0]);
	
	// creating a world object for simulation
	World fate = new World(numPasses, seed);

	// run the simulation
	fate.mainLoop(displayInterval);
	System.out.println("107703004");
    }
    
}

