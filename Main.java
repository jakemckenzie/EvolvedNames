
/**
 * The main driver for the program. It incudes three test functions, two required in the assignment 
 * I left the three functions commented out in the main for ease of use to the grader. All you need
 * do is uncomment them and they should show you their usuability.
 */
import java.util.stream.Collector;
import java.util.stream.Stream;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Vector;
import java.util.TreeSet;
import java.util.Arrays;
import java.util.Random;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.IntSummaryStatistics;
import java.util.LongSummaryStatistics;

public class Main {

    public static void main(String[] Args) {
        int i;
        //long count = 0;
        //long runtime = 0;
        //for (int j = 1; j < 1001; j++) {
        long startTime = System.currentTimeMillis();
        Population p = new Population(100, 0.05d);
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
        //testFinal(1000);
    }

    private static void testGenome() {
        Genome genome1 = new Genome(0.05);
        System.out.println("Running testGenome():");
        genome1.mutate();
        for (int i = 0; i < 100; i++)
            genome1.mutate();
        System.out.println(genome1.toString());
        Genome genome2 = new Genome(0.05d);
        for (int i = 0; i < 100; i++)
            genome2.mutate();
        genome1.crossover(genome2);
        System.out.println(genome2.toString());
    }

    private static void testPopulation() {
        System.out.println("Running testPopulation():");
        Population pop = new Population(10, 0.05d);
        pop.day();
    }

    private static void testFinal(int z) {
        System.out.println("Running testFinal():");
        int[] dayVector = new int[z];
        long[] timeVector = new long[z];
        int i;
        //long count = 0;
        //long runtime = 0;
        for (int j = 1; j < z + 1; j++) {
            long startTime = System.currentTimeMillis();
            Population p = new Population(100, 0.05d);
            i = 1;

            while (p.mostFit.fitness() != 0) {
                p.day();
                System.out.println("Day " + i + " | " + p.mostFit);
                i++;
            }
            long stopTime = System.currentTimeMillis();
            dayVector[j - 1] = i;
            timeVector[j - 1] = (stopTime - startTime);
            System.out
                    .println("Generations: " + (i - 1) + "\nRunning Time: " + (stopTime - startTime) + " milliseconds");
            //count += i;
            //runtime += (stopTime - startTime);
            //System.out.println(j);
        }
        //http://www.java2s.com/Tutorials/Java_Streams/Example/IntStream/Convert_int_array_to_IntStream.htm
        //https://www.concretepage.com/java/jdk-8/java-8-summary-statistics-example
        IntStream intStream = Arrays.stream(dayVector);
        IntSummaryStatistics istats = intStream.collect(IntSummaryStatistics::new, IntSummaryStatistics::accept,
                IntSummaryStatistics::combine);
        LongStream longStream = Arrays.stream(timeVector);
        LongSummaryStatistics longStats = longStream.collect(LongSummaryStatistics::new, LongSummaryStatistics::accept,
                LongSummaryStatistics::combine);
        double daySd = 0.0;
        double timeSd = 0.0;
        for (int n : dayVector)
            daySd += Math.pow((double) n - istats.getAverage(), 2);
        for (long t : timeVector)
            timeSd += Math.pow((double) t - longStats.getAverage(), 2);
        daySd = Math.sqrt(daySd / z);
        timeSd = Math.sqrt(timeSd / z);
        System.out.println("******************* NUMBER OF DAYS *******************");
        System.out.println("Max: " + istats.getMax() + ", Min: " + istats.getMin());
        System.out.println("Average: " + istats.getAverage() + ", Standard Deviation: " + daySd);
        System.out.println("******************* RUNTIME ANALYSIS *******************");
        System.out.println("Max: " + longStats.getMax() + ", Min: " + longStats.getMin());
        System.out.println("Average: " + longStats.getAverage() + ", Standard Deviation: " + timeSd);
    }

}