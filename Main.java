public class Main {

    public static void main(String[] theArgs) {
		int i;
        long count = 0;
        long runtime = 0;
		for (int j = 1; j < 101; j++) {
            long startTime = System.currentTimeMillis();
		    Population p = new Population(100, .05);
            i = 1;
            while (p.mostFit.getFitness() != 0) {
                p.day();
                System.out.println("Day " + i + ": " + p.mostFit);
                i++;
            }
            long stopTime = System.currentTimeMillis();
		    System.out.println("Generations: " + (i - 1) + "\nRunning Time: " 
                            + (stopTime - startTime) + " milliseconds");
            count += i;
            runtime += (stopTime -startTime);
        }
		
        count /= 100;
        runtime /= 100;
        System.out.println("The average generations is " + count);
        System.out.println("The average run time is " + runtime);
    }
}