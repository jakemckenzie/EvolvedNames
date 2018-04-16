import java.util.*;

//cd Documents\Data Structures\Assignment2
//javac Genome.java Population.java Main.java
//import java.util.stream.Collector;
//import java.util.stream.Stream;
/**
 * 
 * 
* "Evolution is often considered as something unexpected. Wouldn't it be more natural, some
* antievolutionists ask, if everything would always stay the same? Perhaps this was a valid 
* question before we understood genetics, but it is no longer. In fact, the way organisms are
* structured, evolution is inevitable. Each organism, even the simplest bacterium, has a genome,
* consisting of thousands to many millions of base pairs. Observation has established that each base 
* pair is subject to occasional mutation. Different populations have different mutations, and 
* if they are isolated from each other, these populations inevitably become more different 
* from each other from generation to generation. Even this simplest of all possible scenarios
* represents evolution. If one adds further biological processes, such as recombination and 
* selection, the rate of evolution accelerates exponentially. Therefore, the mere fact of the 
* existence of genetic programs makes the assumption of a stationary world impossible. 
* Evolution is thus a plain fact, not a conjecture or assumption." ~ Ernst Mayr, What Evolution Is pg 305
 */
public class Genome implements Comparable<Genome> {
    /**
     * This compares two integers and returns the larger of the two. 
     * https://stackoverflow.com/questions/20035111/java-6-equivalent-of-integer-compare
     * 
     * This is how Java implements compare.
     */
    public Integer fitness;

    @Override
    public int compareTo(Genome that) {
        return (this.fitness() < that.fitness()) ? -1 : ((this.fitness() == that.fitness()) ? 0 : 1);
    }

    /**
     * The target string is my instructor's name.
     */
    //private static final String target = "CHRISTOPHER PAUL MARRIOTT";
    private final char[] target = { 'C', 'H', 'R', 'I', 'S', 'T', 'O', 'P', 'H', 'E', 'R', ' ', 'P', 'A', 'U', 'L', ' ',
            'M', 'A', 'R', 'R', 'I', 'O', 'T', 'T' };
    /**
     * The urn used for most randomness used within this class.
     */
    //private static final Random urn = new Random(System.currentTimeMillis());
    private static final Random urn = new Random();
    /**
     * The set of characters that is allowed in this universe for evolution.
     */
    public static char[] set = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q',
            'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', ' ', '-', '\'' };
    /**
     * This is the main genetic set used for evolution. We will evolve it to obtain the target.
     * Typicaly List is chosen for this but for random access and other libraries specific to
     * ArrayLists I chose that. Hopefully that pans out to a better time complexity.
     */
    public ArrayList<Character> geneticSet;
    //public LinkedList<Character> geneticSet;
    //public Vector<Character> geneticSet;
    //public TreeSet<Character> geneticSet;
    /**
     * The mutation rate for the evolution in this universe. 
     */
    private double mutRate;

    /**
      * A constructor that initializes a Genome with value ‘A’ and assigns 
      * the internal mutation rate. 
      * @param mutationRate The mutationRate must be between zero and one.
      * NOTE: CONSTRUCTORS DON'T NEED A RETURN TYPE
      */

    public Genome(double mutationRate) {
        mutRate = mutationRate;
        geneticSet = new ArrayList<Character>();
        //geneticSet = new LinkedList<Character>();
        //geneticSet = new Vector<Character>();
        //geneticSet = new TreeSet<Character>();
        geneticSet.add(set[0]);
        setFitness();
    }

    /**
     * A copy constructor that initializes a Genome with the same values
     * as the input gene.
     * @param gene This genome allows the driver to pass the gene from inside the driver.
     */
    public Genome(Genome gene) {
        this.mutRate = gene.mutRate;
        this.geneticSet = new ArrayList<Character>();
        //this.geneticSet = new LinkedList<Character>();
        //this.geneticSet = new Vector<Character>();
        //this.geneticSet = new TreeSet<Character>();
        for (char c : gene.geneticSet)
            this.geneticSet.add(c);
        this.fitness = gene.fitness;
    }

    public Integer fitness() {
        return fitness;
    }

    /**
     *­ This function mutates the string in this Genome using the following rules:
     * 
     * With mutationRate chance add a randomly selected character to a randomly
     * selected position in the string
     * 
     * With mutationRate chancce delete a single character from a randomly selected
     * position of the string but do this only if the string has length at least 2.
     * 
     * For each character in the string:
     * with mutationRate chance the character is replaced by a randomly 
     * selected character
     */
    public void mutate() {
        if (randomTrial())
            geneticSet.add(shakeUrn(geneticSet.size()), set[shakeUrn(set.length)]);
        if (geneticSet.size() > 1 && randomTrial())
            geneticSet.remove(shakeUrn(geneticSet.size()));
        for (int i = 0; i < geneticSet.size(); i++)
            if (randomTrial())
                geneticSet.set(i, set[shakeUrn(set.length)]);
        setFitness();
    }

    public int shakeUrn(int z) {
        return urn.nextInt(z);
    }

    /**
     * @return This returns a boolean which is only true if the probability of a trial is less than the given mutation rate.
     * https://www.mkyong.com/java/java-generate-random-integers-in-a-range/
     */

    private boolean randomTrial() {
        return (urn.nextDouble() <= mutRate);
    }

    /**
     * This function will update the current Genome by crossing it over with other.
     * @param other this gene from the controller is crossed with the gene in the genome
     */

    public void crossover(Genome other) {
        int geneLength = (geneticSet.size() > other.geneticSet.size()) ? geneticSet.size() : other.geneticSet.size();
        ArrayList<Character> temp = new ArrayList<Character>();
        //LinkedList<Character> temp = new LinkedList<Character>();
        //Vector<Character> temp = new Vector<Character>(geneLength);
        //TreeSet<Character> temp = new TreeSet<Character>();
        for (int i = 0; i < geneLength; i++) {
            if (urn.nextBoolean()) {
                if (geneticSet.size() > i) temp.add(geneticSet.get(i)); 
            } else {
                if (other.geneticSet.size() > i) temp.add(other.geneticSet.get(i));
            }
        }
            geneticSet = temp;
        //fitness();
    }

    /**
     * @return Returns the fitness of the Genome calculated using the following algorithm:
     * 
     */

    //inmate number: 1700088761 icaregifts.com
    //}

    
    /**
     * setFitness(): sets the fitness using 
     * Let n be the length of the current string and m the length of the target string.
     * 
     * Let l = max(n,m) and f = |m - n|.
     * 
     * For each character position 1 <= i <= l add one to f if the char
     * in the current string is different from the character in the
     * target string(or if one of the two chars does not exist).
     * Otherwise add nothing to f.
     */
    public void setFitness() {
        int n = geneticSet.size();
        int m = target.length;
        fitness = abs_diff(m,n)<<1;//|m - n| * 2
        int l = min(n,m); //min(n,m)
        for (int i = 0; i < l; i++) {
    		if (i < geneticSet.size() && i < m && geneticSet.get(i) != target[i]) fitness++;
    		if (n + i < l) fitness++;
        }   
    }
    /**
     * This is the extra credit portion. I don't know what the best way to submit this code for grading. If you comment out the function above and run
     * this line of code instead it should just go.
     */

    /*
    public void setFitness() {
        int n = geneticSet.size();
        int m = target.length;
        int[][] D = new int[n + 1][m + 1];
        for (int i = 0; i <= m; i++)
            D[0][i] = i;
        for (int j = 0; j <= n; j++)
            D[j][0] = j;

        for (int j = 1; j <= m; j++) {
            for (int i = 1; i <= n; i++) {
                D[i][j] = (geneticSet.get(i - 1) == target[j - 1]) ? D[i - 1][j - 1]
                        : min3(D[i - 1][j] + 1, D[i][j - 1] + 1, D[i - 1][j - 1] + 1);
            }
        }
        fitness = D[n][m] + (abs_diff(m, n) + 1) >> 1;
        //fitness = D[n][m];
    }
    */

    /**
     * I implemented the algorithm you gave us and searched for methods which could improve on this.
     * https://en.wikibooks.org/wiki/Algorithm_Implementation/Strings/Levenshtein_distance#C
     * @param a The current character set.
     * @param b The targert character set.
     * @return returns the levenshtein distance.
     */
    /*
    /*public void setFitness() {
        int x, y, lastdiag, olddiag;
        int n = geneticSet.size();
        int m = target.length;
        int[] vector = new int[n + 1];
        for (y = 1; y <= n; y++)
            vector[y] = y;
        for (x = 1; x <= m; x++) {
            vector[0] = x;
            for (y = 1, lastdiag = x-1; y <= n; y++) {
                olddiag = vector[y];
                vector[y] = min3(vector[y] + 1, vector[y-1] + 1, lastdiag + (geneticSet.get(y-1) == target[x - 1] ? 0 : 1));
                lastdiag = olddiag;
            }
        }
        fitness = (vector[n]) + (abs_diff(m, n) + 1) >> 1;
    }*/

    /**
     * @param n left sided variable
     * @param m right sided variable
     * computes the minimum between n and m
     * Hacker's Delight pg 100 Warren et al
     */

    public int min(int n, int m) {
        return n ^ ((n ^ m) & -(n < m ? 1 : 0));
    }

    /**
     * Computes the minimum betwen three integers.
     */
    public int min3(int m, int n, int o) {
        return min(min(m, n), o);
    }

    /**
     * Computers the absolute difference
     * pg 33 Hacker's Delight Warren et al
     */
    public int abs_diff(int a, int b) {
        int a_b = a - b;
        int shift = a_b >> 31;
        return (a_b ^ shift) - (shift);
    }

    /**
     * This is a functional programming way of initializing a 2d array.
     * I commented it out in fear that it would not compile if you were
     * using Java 7 or lower.
     */
    /*
    public Integer[][] intializeArray(int row, int column) {
    
        return IntStream.range(1, row)
                        .mapToObj(r -> IntStream.range(1, row)
                        .mapToObj(c -> new Integer(r,c))
                        .toArray(Integer[]::new))
                        .toArray(Integer[][]::new);
    
    }
    */
    /**
     * This function will display the Genome’s character string and fitness in an easy to read format.
     * @return String used for printing the fitness to cmdline
     */
    public String toString() {
        String result = "\"";
        for (Character c : geneticSet)
            result += c;
        result += "\", ";
        result += fitness();
        return result;
    }

}