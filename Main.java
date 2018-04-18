
/**
 * The main driver for the program.
 */
import java.util.*;
import java.util.stream.IntStream;

public class Main {

    public static void main(String[] Args) {
        int i;
        //long count = 0;
        //long runtime = 0;
        //for (int j = 1; j < 1001; j++) {
        long startTime = System.currentTimeMillis();
        Population p = new Population(100, .05);
        i = 1;

        while (p.mostFit.fitness() != 0) {
            p.day();
            System.out.println("Day " + i + " | " + p.mostFit);
            i++;
        }
        long stopTime = System.currentTimeMillis();
        System.out.println("Generations: " + (i - 1) + "\nRunning Time: " + (stopTime - startTime) + " milliseconds");
        //count += i;
        //runtime += (stopTime - startTime);
        //}
        //count /= 1000;
        //runtime /= 1000;
        //System.out.println("The average generations is " + count + " days.");
        //System.out.println("The average run time is " + runtime + " miliseconds");
        //testGenome();
        //testPopulation();
        testFinal(1000000);
    }

    private static void testGenome() {
        Genome genome1 = new Genome(0.05);
        System.out.println("Running testGenome():");
        genome1.mutate();
        for (int i = 0; i < 100; i++)
            genome1.mutate();
        System.out.println(genome1.toString());
        Genome genome2 = new Genome(0.05);
        for (int i = 0; i < 100; i++)
            genome2.mutate();
        genome1.crossover(genome2);
        System.out.println(genome2.toString());
    }

    private static void testPopulation() {
        System.out.println("Running testPopulation():");
        Population pop = new Population(10, 0.05);
        pop.day();
    }

    private static void testFinal(int z) {
        System.out.println("Running testFinal():");
        int[] vector = new int[z];
        int i;
        //long count = 0;
        //long runtime = 0;
        for (int j = 1; j < z + 1; j++) {
            //long startTime = System.currentTimeMillis();
            Population p = new Population(100, .05);
            i = 1;

            while (p.mostFit.fitness() != 0) {
                p.day();
                //System.out.println("Day " + i + " | " + p.mostFit);
                i++;
            }
            vector[j - 1] = i;
            //long stopTime = System.currentTimeMillis();
            //System.out.println("Generations: " + (i - 1) + "\nRunning Time: " + (stopTime - startTime) + " milliseconds");
            //count += i;
            //runtime += (stopTime - startTime);
            System.out.println(j);
        }
        IntStream intStream = Arrays.stream(vector);//http://www.java2s.com/Tutorials/Java_Streams/Example/IntStream/Convert_int_array_to_IntStream.htm
        IntSummaryStatistics istats = intStream.collect(IntSummaryStatistics::new, 
                                                        IntSummaryStatistics::accept,
                                                        IntSummaryStatistics::combine);
        System.out.println("Max:" + istats.getMax() + ", Min:" + istats.getMin());
        System.out.println("Average:" + istats.getAverage());
        //https://www.concretepage.com/java/jdk-8/java-8-summary-statistics-example
        //System.out.println("The average generations is " + count + " days.");
        //System.out.println("The average run time is " + runtime + " miliseconds");
    }

}