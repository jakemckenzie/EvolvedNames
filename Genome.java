import java.util.*;
import java.util.stream.Collector;

public class Genome {
    /**
     * The target string is my instructor's name.
     */
    
    private static final String target = "CHRISTOPHER PAUL MARRIOTT";

    /**
     * The urn used for most randomness used within this class.
     */

    private final Random urn = new Random();

    /**
     * The set of characters that is allowed in this universe for evolution.
     */
    
    public static ArrayList<Character> set = new ArrayList<Character>(Arrays.asList('A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', ' ', '-', '\''));
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
        geneticSet.add(set.get(0));
      
    }
    /**
     * A copy constructor that initializes a Genome with the same values
     * as the input gene.
     * @param gene This genome allows the driver to pass the gene from inside the driver.
     */
    public Genome(Genome gene) {

        privateMutationRate = gene.privateMutationRate;
        geneticSet = new ArrayList<Character>();
        //for (Character c : gene.geneticSet) geneticSet.add(c);
        //Stream.of(gene.geneticSet).forEach(value -> geneticSet.add(value)); 
        geneticSet.addAll(gene.geneticSet);
        //geneticSet = gene.geneticSet.stream().collect(Collectors.toList());


    }
    /**
     *­ This function mutates the string in this Genome using the following rules:
     * 
     * With mutationRate chance add a randomly selected character to a randomly
     * selected position in the string
     * 
     * With mutationRate chance delete a single character from a randomly selected
     * position of the string but do this only if the string has length at least 2.
     * 
     * For each character in the string:
     * with mutationRate chance the character is replaced by a randomly 
     * selected character
     */

    public void mutate() {

        if (randomTrial()) geneticSet.add(urn.nextInt(geneticSet.size()),set.get(urn.nextInt(set.size())));
        
        if (geneticSet.size() > 1 && randomTrial()) geneticSet.remove(urn.nextInt(geneticSet.size()));

        for (int i = 0; i < geneticSet.size(); i++) if (randomTrial()) geneticSet.set(i,set.get(urn.nextInt(set.size())));
        
    }
    
    /**
     * @return This returns a boolean which is only true if the probability of a trial is less than the given mutation rate.
     */

    private boolean randomTrial() {

        return (urn.ints(0,101).findFirst().getAsInt() <= (privateMutationRate * 100));

    }

}