import java.util.*;
public class Population{
    /**
     * A data element that is equal to the most­fit Genome in the population.
     */
    public Genome mostFit;
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
    private ArrayList<Genome> populationSet = new ArrayList<Genome>();
    /**
     * A constructor that initializes a Population with a number of default genomes
     */
    public void Population(Integer numGenomes, Double mutationRate) {
        //populationSet = new ArrayList<Genome>();
        for (int i = 0; i < numGenomes; i++) populationSet.add(new Genome(mutationRate));
        mostFit = populationSet.get(0); 
    }
    /**
     * ­This function is called every breeding cycle and carries out the following steps:
     * 
     * (1) Update mostFit variable to the most­fit Genome in the population.
     * (2) Delete the least­fit half of the population.
     * (3) create new genomes from the remaining population until the number of genomes is restored by doing either of the following with equal chance:
     *      (I) pick a remaining genome at random and clone it (with the copy constructor) and mutate the clone.
     *     (II) pick a remaining genome at random and clone it and then crossover the clone with another remaining genome selected at random and then
     *          mutate the result.

     */
    public void day() {
        
    }

}   