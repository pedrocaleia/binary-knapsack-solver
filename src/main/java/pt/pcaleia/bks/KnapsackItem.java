package pt.pcaleia.bks;


import java.math.BigDecimal;


/**
 * @author Pedro Caleia
 *
 * @param <W>
 * @param <V>
 */
public final class KnapsackItem<W, V> {
	
	
	private final W weight;
	private final V value;
	
	
	KnapsackItem( W weight, V value ) {
		this.weight = weight;
		this.value = value;
	}
	
	
	public static KnapsackItem<Long, Long> getLongKnapsackItem( int weight, int value ) {
		return new KnapsackItem<Long, Long>( (long)weight, (long)value );
	}
	
	
	public static KnapsackItem<BigDecimal, BigDecimal> getBigDecimalKnapsackItem( int weight, int value ) {
		return new KnapsackItem<BigDecimal, BigDecimal>( BigDecimal.valueOf( weight ), BigDecimal.valueOf( value ) );
	}
	
	
	public static KnapsackItem<Integer, Long> getIntegerLongKnapsackItem( int weight, int value ) { //TODO rename this
		return new KnapsackItem<Integer, Long>( weight, (long)value );
	}


	public W getWeight() {
		return this.weight;
	}
	
	
	public V getValue() {
		return this.value;
	}

}
