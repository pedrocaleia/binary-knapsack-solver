package pt.pcaleia.bks;


import java.math.BigDecimal;
import java.util.Collections;
import java.util.Set;

import pt.pcaleia.bks.processors.NumberProcessor;


/**
 * @author Pedro Caleia
 *
 * @param <W>
 * @param <V>
 */
public final class KnapsackSolution<W, V> {
	
	
	private final Set<KnapsackItem<W, V>> items;
	private final W weight;
	private final V value;
	
	
	KnapsackSolution( Set<KnapsackItem<W, V>> items, W weight, V value ) {
		this.items = Collections.unmodifiableSet( items );
		this.weight = weight;
		this.value = value;
	}
	
	
	public static <W, V> KnapsackSolution<W, V> getKnapsackSolution( Set<KnapsackItem<W, V>> items, NumberProcessor<W> weightProcessor, NumberProcessor<V> valueProcessor ) {
		W weight = weightProcessor.addAll( items.stream().map( KnapsackItem::getWeight ) );
		V value = valueProcessor.addAll( items.stream().map( KnapsackItem::getValue ) );
		
		return new KnapsackSolution<W, V>( items, weight, value );
	}
	
	
	public static KnapsackSolution<Long, Long> getLongKnapsackSolution( Set<KnapsackItem<Long, Long>> items ) {
		long weight = items.stream().mapToLong( KnapsackItem::getWeight ).sum();
		long value = items.stream().mapToLong( KnapsackItem::getValue ).sum();
		
		return new KnapsackSolution<>( items, weight, value );
	}
	
	
	public static KnapsackSolution<BigDecimal, BigDecimal> getBigDecimalKnapsackSolution( Set<KnapsackItem<BigDecimal, BigDecimal>> items ) {
		BigDecimal weight = items.stream().map( KnapsackItem::getWeight ).reduce( BigDecimal.ZERO, BigDecimal::add );
		BigDecimal value = items.stream().map( KnapsackItem::getValue ).reduce( BigDecimal.ZERO, BigDecimal::add );
		
		return new KnapsackSolution<>( items, weight, value );
	}
	
	
	public static <V> KnapsackSolution<Integer, V> getIntegerGenericKnapsackSolution( Set<KnapsackItem<Integer, V>> items, NumberProcessor<V> valueProcessor ) {
		int weight = items.parallelStream().map( KnapsackItem::getWeight ).mapToInt( Integer::intValue ).sum();
		V value = valueProcessor.addAll( items.stream().map( KnapsackItem::getValue ) );
		
		return new KnapsackSolution<>( items, weight, value );
	}
	
	
	public Set<KnapsackItem<W, V>> getItems() {
		return this.items;
	}


	public W getWeight() {
		return this.weight;
	}


	public V getValue() {
		return this.value;
	}

}
