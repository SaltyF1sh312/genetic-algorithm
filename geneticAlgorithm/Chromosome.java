package geneticAlgorithm;

public class Chromosome implements Comparable<Chromosome> {
	/**
	 * Decimal variables in binary sequence
	 */
	private String sequence = "";
	/**
	 * The length of gene
	 */
	private int geneLength;
	
	/**
	 * Construct a new <code>Chromosome</code> object with default properties
	 */
	public Chromosome() {
		this(1);
	}
	/**
	 * Construct a new <code>Chromosome</code> object with the sequence
	 * @param sequence
	 */
	public Chromosome(String sequence) {
		this.sequence = sequence;
		this.geneLength = sequence.length();
	}
	/**
	 * Construct a new <code>Chromosome</code> object with the geneLength
	 * @param geneLength
	 */
	public Chromosome(int geneLength) {
		this.geneLength = geneLength;
		for (int i = 0; i < geneLength; i++)
			sequence = Math.random() < 0.5 ? sequence + "0" : sequence + "1";
	}
	/**
	 * Get the sequence of the <code>Chromosome</code>
	 * @return the sequence of the <code>Chromosome</code>
	 */
	public String getSequence() {
		return sequence;
	}
	/**
	 * Get the geneLength of the <code>Chromosome</code>
	 * @return the geneLength of the <code>Chromosome</code>
	 */
	public int getGeneLength() {
		return geneLength;
	}
	/**
	 * Set the sequence of the <code>Chromosome</code>
	 * @param sequence
	 */
	public void setSequence(String sequence) {
		this.sequence = sequence;
	}
	/**
	 * Get x in decimal
	 * @return x in decimal
	 */
	public double getDecimalX() {
		return getDecimalVariable(binaryToDecimal(sequence.substring(0, geneLength / 2)));
	}
	/**
	 * Get y in decimal
	 * @return y in decimal
	 */
	public double getDecimalY() {
		return getDecimalVariable(binaryToDecimal(sequence.substring(geneLength / 2, geneLength)));
	}
	/**
	 * Get the decimal of the part of the binary chromosome
	 * @param chromosome
	 * @return the decimal of the part of the binary chromosome
	 */
	public double binaryToDecimal(String chromosome) {
		double x = 0;
        for(char c: chromosome.toCharArray())
            x = x * 2 + (c == '1' ? 1 : 0);
        return x;
	}
	/**
	 * Get the variable of the part of the decimal chromosome
	 * @param decimalChromosome
	 * @return the variable of the part of the decimal chromosome
	 */
	public double getDecimalVariable(double decimalChromosome) {
		if (TestGeneticAlgorithm.LEFTENDPOINT * TestGeneticAlgorithm.RIGHTENDPOINT >= 0
				&& TestGeneticAlgorithm.RIGHTENDPOINT <= 0)
			return -1 * decimalChromosome / (Math.pow(2, geneLength / 2) - 1) * TestGeneticAlgorithm.INTERVAL + 
					TestGeneticAlgorithm.RIGHTENDPOINT;
		else
			return decimalChromosome / (Math.pow(2, geneLength / 2) - 1) * TestGeneticAlgorithm.INTERVAL + 
					TestGeneticAlgorithm.LEFTENDPOINT;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((sequence == null) ? 0 : sequence.hashCode());
		return result;
	}
	
	@Override
	public boolean equals(Object object) {
		if (this == object)
			return true;
		if (object == null)
			return false;
		if (this.getClass() != object.getClass())
			return false;
		
		Chromosome other = (Chromosome) object;
		if (sequence == null) {
			if (other.getSequence() != null)
				return false;
		}
		else if (!sequence.equals(other.getSequence()))
			return false;
		
		return true;
	}
	
	@Override
	public String toString() {
		return "<" + sequence + ">";
	}
	
	@Override
	public int compareTo(Chromosome o) {
		return sequence.toString().compareTo(o.getSequence().toString());
	}

}
