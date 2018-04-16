public class Main {

    public static void main(String[] theArgs) {
        int i;
        long count = 0;
        long runtime = 0;
        //long fastest = 100000;
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
        runtime += (stopTime - startTime);
        //if (fastest > count) fastest = count; 
        //}
        
        //count /= 1000;
        //runtime /= 1000;
        //System.out.println("The average generations is " + count + " days.");
        //System.out.println("The average run time is " + runtime + " miliseconds");
        //System.out.println("The best day count is: " + fastest);
    }
}