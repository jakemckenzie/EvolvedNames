import java.util.*;

public class Genome {
    /**
     * The target string is my instructor's name.
     */
    
    private static final String target = "CHRISTOPHER PAUL MARRIOTT";

    /**
     * The set of characters that is allowed in this universe for evolution.
     */
    public static List<Character> set = new ArrayList<Character>(Arrays.asList('A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L',
            'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', ' ', '-', '\''));
    /**
     * The mutation rate for the evolution in this universe. 
     */

    private List<Character> genomeSet;

     private double privateMutationRate;

     /**
      * a constructor that initializes a Genome with value ‘A’ and assigns 
      * the internal mutation rate. 
      * @param mutationRate The mutationRate must be between zero and one.
      */

      public Genome(double mutationRate){
        privateMutationRate = mutationRate;
        genomeSet = new ArrayList<Character>();
        genomeSet.add('A');
      }
}