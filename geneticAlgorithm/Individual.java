package geneticAlgorithm;

import java.util.Random;

public class Individual implements Comparable<Individual> {
	/**
	 * The chromosome of the individual
	 */
	private Chromosome chromosome;
	/**
	 * The value after putting chromosome into the target function
	 */
	private Double fitness;
	/**
	 * The rate of gene mutating
	 */
	private final static double mutateRate = 0.01;

	/**
	 * Construct a new <code>Individual</code> object with default properties
	 */
	Individual() {

	}

	/**
	 * Construct a new <code>Individual</code> object with the chromosome
	 * 
	 * @param chromosome
	 */
	Individual(Chromosome chromosome) {
		this.chromosome = chromosome;
		this.fitness = TestGeneticAlgorithm.getFunctionValue(chromosome.getDecimalX(), chromosome.getDecimalY());
	}

	/**
	 * Get the chromosome of the <code>Individual</code>
	 * 
	 * @return the chromosome of the <code>Individual</code>
	 */
	Chromosome getChromosome() {
		return chromosome;
	}

	/**
	 * Get the fitness of the <code>Individual</code>
	 * 
	 * @return the fitness of the <code>Individual</code>
	 */
	Double getFitness() {
		return fitness;
	}

	/**
	 * Chromosome of this mutates
	 */
	void mutate() {
		if (Math.random() < mutateRate) {
			String mutation = ""; // Part of the sequence after mutation
			String temp = "";
			int position = new Random().nextInt(chromosome.getGeneLength()); // The position where this mutates
			// How many sequences this can mutate
			int mutateInterval = new Random().nextInt(chromosome.getGeneLength() / 10 + 1) + 1;
			int count = 0;

			if (position >= chromosome.getGeneLength() - mutateInterval) {
				mutateInterval = chromosome.getGeneLength() - position; // Mutate at the last part of the chromosome
				while (mutateInterval > count) { // Mutate
					mutation = mutation
							+ (chromosome.getSequence().charAt(position + mutateInterval - 1) == '0' ? "1" : "0");
					++count;
				}
				temp = chromosome.getSequence().substring(0, position) + mutation;
			} else {
				while (mutateInterval > count) { // Mutate
					mutation = mutation
							+ (chromosome.getSequence().charAt(position + mutateInterval - 1) == '0' ? "1" : "0");
					++count;
				}
				if (position == 0) // Mutate at the head part of the chromosome
					temp = mutation + chromosome.getSequence().substring(position + mutateInterval);
				else if (position + mutateInterval + 1 == chromosome.getGeneLength()) // The last part of the chromosome
																						// is a character
					temp = chromosome.getSequence().substring(0, position) + mutation
							+ chromosome.getSequence().charAt(chromosome.getGeneLength() - 1);
				else // Mutate at the middle part of the chromosome
					temp = chromosome.getSequence().substring(0, position) + mutation
							+ chromosome.getSequence().substring(position + mutateInterval);
			}

			// Update the chromosome and the fitness of the individual
			chromosome.setSequence(temp);
			this.fitness = TestGeneticAlgorithm.getFunctionValue(chromosome.getDecimalX(), chromosome.getDecimalY());
		}
	}

	@Override
	public int compareTo(Individual o) {
		return fitness.compareTo(o.getFitness());
	}

}
