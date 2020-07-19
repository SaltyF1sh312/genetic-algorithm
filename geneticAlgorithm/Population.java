package geneticAlgorithm;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class Population {
	/**
	 * The size of the population
	 */
	private int populationSize;
	/**
	 * Individuals in the population
	 */
	private List<Individual> individuals = new ArrayList<Individual>();
	/**
	 * The rate of individuals crossing
	 */
	private final static double crossOverRate = 0.6;

	/**
	 * Construct a new <code>Population</code> object with default properties
	 */
	Population() {

	}

	/**
	 * Construct a new <code>Population</code> object with the populationSize and
	 * the individuals
	 * 
	 * @param populationSize
	 * @param individuals
	 */
	Population(List<Individual> individuals) {
		this.individuals = individuals;
		this.populationSize = individuals.size();
	}

	/**
	 * Get the populationSize of the <code>Population</code>
	 * 
	 * @return the populationSize of the <code>Population</code>
	 */
	int getPopulationSize() {
		return populationSize;
	}

	/**
	 * Get the individuals of the <code>Population</code>
	 * 
	 * @return the individuals of the <code>Population</code>
	 */
	List<Individual> getIndividuals() {
		return individuals;
	}

	/**
	 * Set the populationSize of the <code>Population</code>
	 * 
	 * @param populationSize
	 */
	void setPopulationSize(int populationSize) {
		this.populationSize = populationSize;
	}

	/**
	 * Add the individual to the <code>Population</code>
	 * 
	 * @param individual
	 */
	void addIndividual(Individual individual) {
		individuals.add(individual);
	}

	/**
	 * <code>Individual</code> in the <code>Population</code> cross
	 */
	void crossOver() {
		for (int i = 0; i < populationSize; i++) {
			if (Math.random() < crossOverRate) {
				String firstChild = "", secondChild = "";
				int other;
				// Two chromosomes should be different
				while ((other = new Random().nextInt(populationSize)) == i)
					continue;
				// The position where two chromosomes cross
				int position = new Random().nextInt(individuals.get(i).getChromosome().getGeneLength() - 1) + 1;

				// cross two random chromosomes at the position
				firstChild = individuals.get(i).getChromosome().getSequence().substring(0, position)
						+ individuals.get(other).getChromosome().getSequence().substring(position);
				secondChild = individuals.get(other).getChromosome().getSequence().substring(0, position)
						+ individuals.get(i).getChromosome().getSequence().substring(position);
				addIndividual(new Individual(new Chromosome(firstChild)));
				addIndividual(new Individual(new Chromosome(secondChild)));
			}
		}
	}

	/**
	 * Get the first <code>Individual</code>
	 * 
	 * @return the first <code>Individual</code>
	 */
	Individual getAlpha() {
		return individuals.get(0);
	}

	/**
	 * Select individuals whose fitness are lower
	 */
	void minSelect() {
		individuals = individuals.stream().sorted(Comparator.comparingDouble(Individual::getFitness))
				.limit(populationSize).collect(Collectors.toList());
	}

	/**
	 * Select individuals whose fitness are higher
	 */
	void maxSelect() {
		Collections.sort(individuals, Comparator.reverseOrder());
		individuals = individuals.stream().limit(populationSize).collect(Collectors.toList());
	}

}
