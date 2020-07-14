package geneticAlgorithm;

import java.util.*;

public class TestGeneticAlgorithm {
	public final static double PRECISION = 1e-6; // The target precision of the function
	public final static double LEFTENDPOINT = 0; // The target left end point of the function
	public final static double RIGHTENDPOINT = 6; // The target right end point of the function
	public final static double INTERVAL = RIGHTENDPOINT - LEFTENDPOINT; // The target interval of the function
	public final static double TARGET = 2.5; // The target value of the function
	public final static int GENERATIONS = 1000; // How many generations the population may cross
	public final static int POPULATIONSIZE = 100; // The size of the population
	public final static int GENELENGTHS = 2 * getGeneLength(INTERVAL, PRECISION); // The length of genes

	public static void main(String[] args) {
		if (LEFTENDPOINT - RIGHTENDPOINT >= 0)
			throw new IllegalArgumentException("The left end point should be less than the right end point!");
			
		getTarget();
		System.out.println();
		getMin();
		System.out.println();
		getMax();

	}
	/**
	 * The target function
	 * @param x
	 * @param y
	 * @return the value of the target function
	 */
	public static double getFunctionValue(double x, double y) {
		return 3 - (x * x + y * y);
	}
	/**
	 * Calculate and get the length of genes
	 * @param interval
	 * @param precision
	 * @return the length of genes
	 */
	public static int getGeneLength(double interval, double precision) {
		int geneLength = 1;
		long powerOfTwo = 2;
		long intervals = (long) Math.ceil(interval / precision);
		while (powerOfTwo < intervals) {
			powerOfTwo *= 2;
			geneLength++;
		}
		return geneLength;
	}
	/**
	 * Get the variables whose value closest to the target
	 */
	public static void getTarget() {
		long startTime = System.currentTimeMillis();
		// Initial a population
		final List<Individual> individuals = new ArrayList<Individual>();
		for (int i = 0; i < POPULATIONSIZE; i++)
			individuals.add(new Individual(new Chromosome(GENELENGTHS)));
		Population population = new Population(POPULATIONSIZE, individuals);
		
		int currentGeneration = 1;
		int bestGeneration = 1; // The generation when the best fitness exists
		Individual bestFitness = population.getBestFitness();
		
		while (Math.abs(bestFitness.getFitness() - TARGET) > PRECISION && currentGeneration < GENERATIONS) {
			population.cross(); // Individuals in the population cross
			for (Individual individual: population.getIndividuals())
				individual.mutate(); // Chromosomes of individuals in the population mutate
			population.targetSelect(); // Population selects
			
			if (Math.abs(population.getBestFitness().getFitness() - TARGET) < 
					Math.abs(bestFitness.getFitness() - TARGET)) {
				bestGeneration = currentGeneration; // Update bestGeneration
				bestFitness = population.getBestFitness(); // Update bestFitness
			}
			++currentGeneration;
		}
		
		System.out.println("The best fitness is: " + bestFitness.getFitness());
		System.out.println("The " + bestGeneration + "th chromosome: " + bestFitness.getChromosome());
		System.out.println("x = " + bestFitness.getChromosome().getDecimalX());
		System.out.println("y = " + bestFitness.getChromosome().getDecimalY());

		long endTime = System.currentTimeMillis();
		System.out.println("The execution time is: " + (endTime - startTime) + " millseconds");
	}
	/**
	 * Get the variables whose value closest to the minimum of the function
	 */
	public static void getMin() {
		long startTime = System.currentTimeMillis();
		// Initial a population
		final List<Individual> individuals = new ArrayList<Individual>();
		for (int i = 0; i < POPULATIONSIZE; i++)
			individuals.add(new Individual(new Chromosome(GENELENGTHS)));
		Population population = new Population(POPULATIONSIZE, individuals);
		
		int currentGeneration = 1;
		int bestGeneration = 1; // The generation when the minimum fitness exists
		Individual minFitness = population.getMinFitness();
		
		while (currentGeneration < GENERATIONS) {
			population.cross(); //Individuals in the population cross
			for (Individual individual: population.getIndividuals())
				individual.mutate(); // Chromosomes of individuals in the population mutate
			population.minSelect(); // Population selects
			
			if (population.getMinFitness().getFitness() < minFitness.getFitness()) {
				bestGeneration = currentGeneration; // Update bestGeneration
				minFitness = population.getMinFitness(); // Update bestFitness
			}
			++currentGeneration;
		}
		
		System.out.println("The minimum fitness is: " + minFitness.getFitness());
		System.out.println("The " + bestGeneration + "th chromosome: " + minFitness.getChromosome());
		System.out.println("x = " + minFitness.getChromosome().getDecimalX());
		System.out.println("y = " + minFitness.getChromosome().getDecimalY());

		long endTime = System.currentTimeMillis();
		System.out.println("The execution time is: " + (endTime - startTime) + " millseconds");
	}
	/**
	 * Get the variables whose value closest to the maximum of the function
	 */
	public static void getMax() {
		long startTime = System.currentTimeMillis();
		// Initial a population
		final List<Individual> individuals = new ArrayList<Individual>();
		for (int i = 0; i < POPULATIONSIZE; i++)
			individuals.add(new Individual(new Chromosome(GENELENGTHS)));
		Population population = new Population(POPULATIONSIZE, individuals);
		
		int currentGeneration = 1;
		int bestGeneration = 1; // The generation when the maximum fitness exists
		Individual maxFitness = population.getMaxFitness();
		
		while (currentGeneration < GENERATIONS) {
			population.cross(); // Individuals in the population cross
			for (Individual individual: population.getIndividuals())
				individual.mutate(); // Chromosomes of individuals in the population mutate
			population.maxSelect(); // Population selects
			
			if (population.getMaxFitness().getFitness() > maxFitness.getFitness()) {
				bestGeneration = currentGeneration; // Update bestGeneration
				maxFitness = population.getMaxFitness(); // Update bestFitness
			}
			++currentGeneration;
		}
		
		System.out.println("The maximum fitness is: " + maxFitness.getFitness());
		System.out.println("The " + bestGeneration + "th chromosome: " + maxFitness.getChromosome());
		System.out.println("x = " + maxFitness.getChromosome().getDecimalX());
		System.out.println("y = " + maxFitness.getChromosome().getDecimalY());

		long endTime = System.currentTimeMillis();
		System.out.println("The execution time is: " + (endTime - startTime) + " millseconds");
	}

}
