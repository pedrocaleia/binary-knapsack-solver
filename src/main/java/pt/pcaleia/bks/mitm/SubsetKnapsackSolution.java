package pt.pcaleia.bks.mitm;


/**
 * @author Pedro Caleia
 */
final class SubsetKnapsackSolution<W, V> {
	
	
	private final String items; //TODO better name for this
	private final W weight;
	private final V value;
	
	
	SubsetKnapsackSolution( String items, W weight, V value ) {
		this.items = items;
		this.weight = weight;
		this.value = value;
	}
	
	
	public String getItems() {
		return this.items;
	}


	public W getWeight() {
		return this.weight;
	}


	public V getValue() {
		return this.value;
	}

}
