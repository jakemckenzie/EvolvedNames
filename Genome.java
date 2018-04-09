import java.util.*;
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
    @Override
    public int compare(Genome a, Genome b) {
        return (a.fitness() > b.fitness() ? 1 :(a.fitness() < b.fitness() ? -1 : 0));
    }
    /**
     * The target string is my instructor's name.
     */
    //private static final String target = "CHRISTOPHER PAUL MARRIOTT";
    private  final ArrayList<Character> target = new ArrayList<Character>(Arrays.asList('C','H','R','I','S','T','O','P','H','E','R',' ',
                                                                                        'P','A','U','L',' ',
                                                                                        'M','A','R','R','I','O','T','T'));
    /**
     * The urn used for most randomness used within this class.
     */
    private final Random urn = new Random(System.currentTimeMillis());
    /**
     * The set of characters that is allowed in this universe for evolution.
     */
    public static ArrayList<Character> set = new ArrayList<Character>(Arrays.asList('A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 
                                                                                    'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 
                                                                                    'W', 'X', 'Y', 'Z', ' ', '-', '\''));
    /**
     * This is the main genetic set used for evolution. We will evolve it to obtain the target.
     * Typicaly List is chosen for this but for random access and other libraries specific to
     * ArrayLists I chose that. Hopefully that pans out to a better time complexity.
     */
    private ArrayList<Character> geneticSet = new ArrayList<Character>();;
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

    public Genome(double mutationRate){
        mutRate = mutationRate;
        geneticSet.add(set.get(0));
    }
    /**
     * A copy constructor that initializes a Genome with the same values
     * as the input gene.
     * @param gene This genome allows the driver to pass the gene from inside the driver.
     */
    public Genome(Genome gene) {
        mutRate = gene.mutRate;
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
     * https://www.mkyong.com/java/java-generate-random-integers-in-a-range/
     */

    private boolean randomTrial() {
        return (urn.ints(0,101).findFirst().getAsInt() <= (mutRate * 100));
    }
    /**
     * This function will update the current Genome by crossing it over with other.
     * @param other this gene from the controller is crossed with the gene in the genome
     */

    public void crossover(Genome other) {

        int geneLength = Math.min(geneticSet.size(),other.geneticSet.size());
        ArrayList<Character> temp = new ArrayList<Character>(geneLength);
        for (int i = 0; i < geneLength; i++) temp.add(urn.nextBoolean() ? other.geneticSet.get(i) : geneticSet.get(i));
        geneticSet = temp;

    }
    /**
     * @return Returns the fitness of the Genome calculated using the following algorithm:
     * 
     * Let n be the length of the current string and m the length of the target string.
     * 
     * Let l = max(n,m) and f = |m - n|.
     * 
     * For each character position 1 <= i <= l add one to f if the char
     * in the current string is different from the character in the
     * target string(or if one of the two chars does not exist).
     * Otherwise add nothing to f.
     * 
     * For the interest of no branching and to save computations I did some bitwise operations.
     * Even if it isn't faster it was fun to read about.
     * http://graphics.stanford.edu/~seander/bithacks.html
     */
    public int fitness() {

        //int n = geneticSet.size();
        //int m = target.size();
        //int m_n = m - n;
        //int shift = m_n >> 31;
        //int mask = m_n >> 4 * 2 - 1;
        //int L = n ^((n ^ m) & -(n < m ? 1: 0));//max
        //int f = (m_n + mask) ^ mask;//absolute value
        //int f = (m_n ^ shift) - (shift);//absolute value
        //int[][] D = new int[n + 1][m + 1];
        //int i = 1;
        //Should initialize the array in O(L) instead of O(n) and O(m) respectively but
        //I was worried that I wouldn't be able to run on your version of Java so I took it out.
        //Integer[][] D = foo(n + 1, m + 1);
        //Integer[][] D = new Integer[n + 1][m + 1];
        /*for (int a = 0 ; a <= n ; a++) D[a][0] = a;
        for (int b = 0 ; b <= m ; b++) D[0][b] = b;
        
        for (int r = 1 ; r <= n ; r++) {
            for (int c = 1 ; c <= m ; c++) {
                D[r][c] = (geneticSet.get(r - 1) == target.get(c - 1)) ? D[r - 1][c - 1]:min(min(D[r - 1][c] + 1,D[r][c - 1] + 1),D[r - 1][c - 1] + 1);
            }
        }
        return D[n][m] + (abs_diff(n,m) + 1) / 2;*/
        //
        return levenshteinDistance(geneticSet,target);    


    }
    /*
    public int min(int a, int b) {
        return b ^ ((a ^ b) & -((a < b) ? 1 : 0));
    }
    */
    /**
     * Claims to run in O(min(m,n)) instead of O(m*n)
     * I implemented the algorithm you gave us and searched for methods which could improve on this.
     * https://en.wikibooks.org/wiki/Algorithm_Implementation/Strings/Levenshtein_distance#C
     * @param a The current character set.
     * @param b The targert character set.
     * @return returns the levenshtein distance.
     */

    public int levenshteinDistance(ArrayList<Character> a,ArrayList<Character> b) {
        //unsigned int s1len, s2len, x, y, lastdiag, olddiag;
        int n = a.size();
        int m = b.size();
        //int new_diagonal;
        int old_diagonal;

        int delta[] = new int[n + 1]; 
        for (int i = 1; i <= n; i++) delta[i] = i;
        for (int j = 1; j <= m; j++) {
           delta[0] = j;
            for (int k = 1, new_diagonal = j - 1 ; k <= n; k++) {
                old_diagonal =delta[k];
                delta[k] = min3(delta[k] + 1,delta[k - 1] + 1, new_diagonal + (a.get(j - 1) == b.get(k - 1) ? 0 : 1));
                new_diagonal = old_diagonal;
            }
        }
        return delta[n];
    }
    /**
     * Computes the minimum betwen three integers.
     */
    public int min3(int a, int b, int c) {
        return (a < b) ? ((a < c) ? a : c) : ((b < c) ? b : c);
    }
    /*
    public int abs_diff(int a, int b) {
        int a_b = a - b;
        int shift = a_b >> 31;
        return (a_b ^ shift) - (shift);
    }
    */
    /*public Integer[][] foo(int row, int column) {

        return IntStream.range(1, row)
                        .mapToObj(r -> IntStream.range(1, row)
                        .mapToObj(c -> new Integer(r,c))
                        .toArray(Integer[]::new))
                        .toArray(Integer[][]::new);

    }*/
    /**
     * This function will display the Genome’s character string and fitness in an easy to read format.
     * @param charForString converts current geneticSet to string for printing.
     * @return String used for printing the fitness to cmdline
     */
    public String toString(ArrayList<Character> charForString) {
        StringBuilder builder = new StringBuilder(charForString.size());
        for (Character c : charForString) builder.append(c);
        return "The fitness for " + builder.toString() + " is :" + fitness();
    }

}