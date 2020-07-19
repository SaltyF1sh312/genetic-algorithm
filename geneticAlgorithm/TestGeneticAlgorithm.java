package geneticAlgorithm;

import java.util.ArrayList;
import java.util.List;

public class TestGeneticAlgorithm {
	final static double PRECISION = 1e-6; // The target precision of the function
	final static double LEFTENDPOINT = 0; // The target left end point of the function
	final static double RIGHTENDPOINT = 6; // The target right end point of the function
	final static double INTERVAL = RIGHTENDPOINT - LEFTENDPOINT; // The target interval of the function
	final static int GENERATIONS = 1000; // How many generations the population would update
	final static int POPULATIONSIZE = 100; // The size of the population
	final static int GENELENGTHS = 2 * getGeneLength(INTERVAL, PRECISION); // The length of genes

	public static void main(String[] args) {
		getMin();
		System.out.println();
		getMax();

	}

	/**
	 * The target function
	 * 
	 * @param x
	 * @param y
	 * @return the value of the target function
	 */
	static double getFunctionValue(double x, double y) {
		return 3 - (x * x + y * y);
	}

	/**
	 * Calculate and get the length of genes
	 * 
	 * @param interval
	 * @param precision
	 * @return the length of genes
	 */
	static int getGeneLength(double interval, double precision) {
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
	 * Get the variables whose value closest to the minimum of the function
	 */
	static void getMin() {
		long startTime = System.currentTimeMillis();
		
		// Initial a population
		final List<Individual> individuals = new ArrayList<Individual>();
		for (int i = 0; i < POPULATIONSIZE; i++)
			individuals.add(new Individual(new Chromosome(GENELENGTHS)));
		Population population = new Population(individuals);

		int currentGeneration = 0; // Current generation
		int bestGeneration = 0; // The generation when the minimum fitness exists
		Individual minFitness = population.getAlpha(); // The individual whose fitness is the minimum

		while (currentGeneration < GENERATIONS) {
			population.crossOver(); // Individuals in the population cross
			for (Individual individual : population.getIndividuals())
				individual.mutate(); // Chromosomes of individuals in the population mutate
			population.minSelect(); // Population selects

			if (population.getAlpha().getFitness() < minFitness.getFitness()) {
				bestGeneration = currentGeneration; // Update bestGeneration
				minFitness = population.getAlpha(); // Update bestFitness
			}
			++currentGeneration; // Update currentGeneration
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
	static void getMax() {
		long startTime = System.currentTimeMillis();
		
		// Initial a population
		final List<Individual> individuals = new ArrayList<Individual>();
		for (int i = 0; i < POPULATIONSIZE; i++)
			individuals.add(new Individual(new Chromosome(GENELENGTHS)));
		Population population = new Population(individuals);

		int currentGeneration = 0; // Current generation
		int bestGeneration = 0; // The generation when the maximum fitness exists
		Individual maxFitness = population.getAlpha(); // The individual whose fitness is the maximum

		while (currentGeneration < GENERATIONS) {
			population.crossOver(); // Individuals in the population cross
			for (Individual individual : population.getIndividuals())
				individual.mutate(); // Chromosomes of individuals in the population mutate
			population.maxSelect(); // Population selects

			if (population.getAlpha().getFitness() > maxFitness.getFitness()) {
				bestGeneration = currentGeneration; // Update bestGeneration
				maxFitness = population.getAlpha(); // Update bestFitness
			}
			++currentGeneration; // Update currentGeneration
		}

		System.out.println("The maximum fitness is: " + maxFitness.getFitness());
		System.out.println("The " + bestGeneration + "th chromosome: " + maxFitness.getChromosome());
		System.out.println("x = " + maxFitness.getChromosome().getDecimalX());
		System.out.println("y = " + maxFitness.getChromosome().getDecimalY());

		long endTime = System.currentTimeMillis();
		System.out.println("The execution time is: " + (endTime - startTime) + " millseconds");
	}

}
