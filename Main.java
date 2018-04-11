public class Main {

    public static void main(String[] theArgs) {
		long startTime = System.currentTimeMillis();
		Population p = new Population(100, .05);
		int i = 1;
		while (p.mostFit.getFitness() != 0) {
			p.day();
			System.out.println("Day " + i + ": " + p.mostFit);
			i++;
		}
		long stopTime = System.currentTimeMillis();
		System.out.println("Generations: " + (i - 1) + "\nRunning Time: " 
							+ (stopTime - startTime) + " milliseconds");
//		testGenome();
//		testPopulation();
	}
}