package sineSection.util;

/**
 * a Util class to help in creation of hashCades for objects, especially those used in hashMaps as keys
 * @author geekman9097
 *
 */
public class HashCoder {
	private int result;
	final private int increment;

	public HashCoder(int start, int increment) {
		result = start;
		this.increment = increment;
	}

	public HashCoder append(int element) {
		multiply();
		add(element);
		return this;
	}

	public HashCoder append(double element) {
		multiply();
		add((int) (element + 0.5));
		return this;
	}

	public HashCoder append(Object element) {
		multiply();
		add(element.hashCode());
		return this;
	}

	public int getHash() {
		return result;
	}

	private void multiply() {
		result *= increment;
	}

	private void add(int i) {
		result += i;
	}
}