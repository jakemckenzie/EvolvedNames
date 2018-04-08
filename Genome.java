import java.util.*;

public class Genome {
    /**
     * The target string is my instructor's name.
     */
    
    private static final String target = "CHRISTOPHER PAUL MARRIOTT";

    /**
     * The set of characters that is allowed in this universe for evolution.
     */
    public static ArrayList<Character> set = new ArrayList<Character>(Arrays.asList('A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L',
            'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', ' ', '-', '\''));
    /**
     * This is the main genetic set used for evolution. We will evolve it to obtain the target.
     * Typicaly List is chosen for this but for random access and other libraries specific to
     * ArrayLists I chose that. Hopefully that pans out to a better time complexity.
     */

    private ArrayList<Character> geneticSet;
    /**
     * The mutation rate for the evolution in this universe. 
     */
    private double privateMutationRate;

    /**
      * A constructor that initializes a Genome with value ‘A’ and assigns 
      * the internal mutation rate. 
      * @param mutationRate The mutationRate must be between zero and one.
      */

    public Genome(double mutationRate){

        privateMutationRate = mutationRate;
        geneticSet = new ArrayList<Character>();
        geneticSet.add('A');
      }
}