package geneticAlgorithm;

import java.util.*;

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
	private final static double crossRate = 0.6;
	/**
	 * Construct a new <code>Population</code> object with default properties
	 */
	public Population() {
		this(1, Arrays.asList(new Individual()));
	}
	/**
	 * Construct a new <code>Population</code> object with the populationSize and the individuals
	 * @param populationSize
	 * @param individuals
	 */
	public Population(int populationSize, List<Individual> individuals) {
		this.populationSize = populationSize;
		this.individuals = individuals;
	}
	/**
	 * Get the populationSize of the <code>Population</code>
	 * @return the populationSize of the <code>Population</code>
	 */
	public int getPopulationSize() {
		return populationSize;
	}
	/**
	 * Get the individuals of the <code>Population</code>
	 * @return the individuals of the <code>Population</code>
	 */
	public List<Individual> getIndividuals() {
		return individuals;
	}
	/**
	 * Set the populationSize of the <code>Population</code>
	 * @param populationSize
	 */
	public void setPopulationSize(int populationSize) {
		this.populationSize = populationSize;
	}
	/**
	 * Set the individuals of the <code>Population</code>
	 * @param individuals
	 */
	public void setIndividuals(List<Individual> individuals) {
		this.individuals = individuals;
	}
	/**
	 * Add the individual to the <code>Population</code>
	 * @param individual
	 */
	public void addIndividual(Individual individual) {
		individuals.add(individual);
	}
	/**
	 * Remove the individual from the <code>Population</code>
	 * @param individual
	 */
	public void removeIndividual(Individual individual) {
		if (individuals.contains(individual))
			individuals.remove(individual);
		else
			throw new NullPointerException("this individual doesn't exist!");
	}
	/**
	 * <code>Individual</code> in the <code>Population</code> cross
	 */
	public void cross() {
		for (int i = 0; i < populationSize; i++) {
			if (Math.random() < crossRate) {
				String child1 = "", child2 = "";
				int other;
				// Two chromosomes should be different
				while ((other = new Random().nextInt(populationSize)) == i) continue;
				// The position where two chromosomes cross
				int position = new Random().nextInt(individuals.get(i).getChromosome().getGeneLength() - 1) + 1;
				
				// cross two random chromosomes at the position
				child1 = individuals.get(i).getChromosome().getSequence().substring(0, position) + 
						individuals.get(other).getChromosome().getSequence().substring(position);
				child2 = individuals.get(other).getChromosome().getSequence().substring(0, position) + 
						individuals.get(i).getChromosome().getSequence().substring(position);
				addIndividual(new Individual(new Chromosome(child1)));
				addIndividual(new Individual(new Chromosome(child2)));
			}
		}
	}
	/**
	 * Get the <code>Individual</code> who has the minimum fitness
	 * @return the <code>Individual</code> who has the minimum fitness
	 */
	public Individual getMinFitness() {
		Individual min = individuals.get(0);
		for (Individual individual: individuals)
			if (individual.getFitness() < min.getFitness())
				min = individual;
		
		return min;
	}
	/**
	 * Get the <code>Individual</code> who has the maximum fitness
	 * @return the <code>Individual</code> who has the maximum fitness
	 */
	public Individual getMaxFitness() {
		Individual max = individuals.get(0);
		for (Individual individual: individuals)
			if (individual.getFitness() > max.getFitness())
				max = individual;
		
		return max;
	}
	/**
	 * Get the <code>Individual</code> who has the fitness closest to the target
	 * @return the <code>Individual</code> who has the fitness closest to the target
	 */
	public Individual getBestFitness() {
		Individual best = individuals.get(0);
		for (Individual individual: individuals)
			if (Math.abs(individual.getFitness() - TestGeneticAlgorithm.TARGET) < 
					Math.abs(best.getFitness() - TestGeneticAlgorithm.TARGET))
				best = individual;
		
		return best;
	}
	/**
	 * Select individuals who have a lower fitness
	 */
	public void minSelect() {
		if (individuals.size() == populationSize) // There are no individuals in the <code>Population</code> crossing
			return;
		else {
			List<Individual> individuals = new ArrayList<Individual>();
			for (int i = 0; i < populationSize; i++) {
				Individual minFitness = this.getMinFitness();
				this.individuals.remove(minFitness);
				individuals.add(minFitness);
			}
			setIndividuals(individuals);
		}
	}
	/**
	 * Select individuals who have a higher fitness
	 */
	public void maxSelect() {
		if (individuals.size() == populationSize) // There are no individuals in the <code>Population</code> crossing
			return;
		else {
			List<Individual> individuals = new ArrayList<Individual>();
			for (int i = 0; i < populationSize; i++) {
				Individual maxFitness = this.getMaxFitness();
				this.individuals.remove(maxFitness);
				individuals.add(maxFitness);
			}
			setIndividuals(individuals);
		}
	}
	/**
	 * Select individuals who have a fitness closer to the target
	 */
	public void targetSelect() {
		if (individuals.size() == populationSize) // There are no individuals in the <code>Population</code> crossing
			return;
		else {
			List<Individual> individuals = new ArrayList<Individual>();
			for (int i = 0; i < populationSize; i++) {
				Individual bestFitness = this.getBestFitness();
				this.individuals.remove(bestFitness);
				individuals.add(bestFitness);
			}
			setIndividuals(individuals);
		}
	}

}
