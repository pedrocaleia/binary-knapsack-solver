package pt.pcaleia.bks.dynamic;


import java.math.BigDecimal;
import java.util.Set;

import pt.pcaleia.util.asserts.ArgumentAssertions;


/**
 * @author Pedro Caleia
 */
public final class DynamicKnapsackSolution {
	
	
	private final Set<DynamicKnapsackItem> items;
	private final int weight;
	private final BigDecimal value;
	
	
	public DynamicKnapsackSolution( Set<DynamicKnapsackItem> items ) {
		ArgumentAssertions.assertNonNullElements( items, "items" );
		
		this.items = Set.copyOf( items );
		this.weight = items.stream().mapToInt( DynamicKnapsackItem::getWeight ).sum();
		this.value = items.stream().map( DynamicKnapsackItem::getValue ).reduce( BigDecimal.ZERO, BigDecimal::add );
	}
	
	
	public Set<DynamicKnapsackItem> getItems() {
		return this.items;
	}


	public int getWeight() {
		return this.weight;
	}


	public BigDecimal getValue() {
		return this.value;
	}

}
