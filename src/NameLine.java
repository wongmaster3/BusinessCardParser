/* This class stores two attributes
 *      1. The current line stored as a String 
 *      2. The current probability that the line is a name
 * This class will be used in the 'BusinessCardParser' class.
 */
public class NameLine implements Comparable<NameLine> {
	private String namePossibility; 
	private double probability;
	
	public NameLine(String name, double prob) {
		namePossibility = name;
		probability = prob;
	}
	
	@Override
	public int compareTo(NameLine other) {
		return (other.probability >= this.probability) ? 1 : -1;
	}
	
	public String getName() {
		return namePossibility;
	}
	
	public double getProbability() {
		return probability;
	}
}
