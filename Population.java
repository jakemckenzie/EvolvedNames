import java.util.stream.Collector;
import java.util.stream.Stream;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Vector;
import java.util.TreeSet;
import java.util.Random;
import java.util.Collections;

public class Population {
    /**
     * A data element that is equal to the most­fit Genome in the population.
     */
    public Genome mostFit;

    public int numGenomes;
    /**
     * The urn used for most randomness used within this class.
     */
    private static final Random urn = new Random(XORShift128plus());
    //private static final Random urn = new Random(XORShift());
    //private static final Random urn = new Random(System.currentTimeMillis());
    //private static final Random urn = new Random();
    /**
     * The population set for evolution. Populations (or gene pools) evolve as gene frequencies change; individual organism cannot evolve.
     * "The population is the so-called unit of evolution. Genes, individuals, and species also play a role, but it is the change in populations 
     * that characterizes organic evolution." ~ Ernst Mayr, What evolution Is. pg 25
     * 
     * "Darwin made a radical break with the typological tradition of essentialism by initiating an entirely new way of thinking. What we find 
     * among living organisms, he said, are not constant classes (types), but variable populations. Every species is composed of numerous local 
     * populations. Within a population, in contrast to a class, every individual is uniquely different from every other individual" 
     *  ~ Ernst Mayr, What evolution Is. pg 95
     */
    public ArrayList<Genome> populationSet;
    //public LinkedList<Genome> populationSet;
    //public Vector<Genome> populationSet;
    //public TreeSet<Genome> populationSet;

    /**
     * A constructor that initializes a Population with a number of default genomes
     */
    public Population(Integer numGenomes, Double mutationRate) {
        this.numGenomes = numGenomes;
        populationSet = new ArrayList<Genome>(numGenomes);
        //populationSet = new LinkedList<Genome>();
        //populationSet = new Vector<Genome>(numGenomes);
        //populationSet = new TreeSet<Genome>();
        for (int i = 0; i < numGenomes; i++)
            populationSet.add(new Genome(mutationRate));
        Collections.sort(populationSet);
        mostFit = populationSet.get(0);
    }

    /**
     * ­This function is called every breeding cycle and carries out the following steps:
     * 
     * (1) Update mostFit variable to the most­fit Genome in the population.
     * (2) Delete the least­fit half of the population.
     * (3) create new genomes from the remaining population until the number of genomes is restored by doing either of the following with *equal chance*:
     *      (I) pick a remaining genome at random and clone it (with the copy constructor) and mutate the clone.
     *     (II) pick a remaining genome at random and clone it and then crossover the clone with another remaining genome selected at random and then
     *          mutate the result.
     */
    public void day() {
        Collections.sort(populationSet);
        for (int i = populationSet.size() >> 1; i < populationSet.size(); i++)
            populationSet.remove(i);
        Genome gene;
        for (int j = numGenomes >> 1; j < numGenomes; j++) {
            gene = new Genome(populationSet.get(urn.nextInt(populationSet.size())));
            if (urn.nextBoolean())
                gene.crossover(populationSet.get(urn.nextInt(populationSet.size())));
            gene.mutate();
            populationSet.add(gene);
        }
        mostFit = null;
        for (Genome g : populationSet)
            mostFit = (mostFit == null) ? g : (mostFit.compareTo(g) <= 0 ? mostFit : g);
    }
    /**
     * I implemented these after reading about how Java random works. Essentially they exist to make
     * better psuedo-random seeds. My average day count and standard deviation decreased after I 
     * implemented these.
     * 
     * https://en.wikipedia.org/wiki/Xorshift
     * https://www.javamex.com/tutorials/random_numbers/xorshift.shtml#.Wtmgwi7wZEY
     */

    private static long XORShift() {
        long x = System.currentTimeMillis();
        x ^= (x << 21);
        x ^= (x >>> 35);
        x ^= (x << 4);
        return x;
    }
    private static long XORShift128plus() {
        long x = System.currentTimeMillis();
        long y = XORShift();    
        x ^= (x << 23);
        long z = x ^ y ^ (x >> 26);
        return z + x;
    }
}