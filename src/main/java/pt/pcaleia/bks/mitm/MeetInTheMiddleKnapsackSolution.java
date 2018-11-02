package pt.pcaleia.bks.mitm;


import java.math.BigDecimal;
import java.util.Set;

import pt.pcaleia.util.asserts.ArgumentAssertions;


/**
 * @author Pedro Caleia
 */
public final class MeetInTheMiddleKnapsackSolution {
	
	
	private final Set<MeetInTheMiddleKnapsackItem> items;
	private final BigDecimal weight;
	private final BigDecimal value;
	
	
	public MeetInTheMiddleKnapsackSolution( Set<MeetInTheMiddleKnapsackItem> items ) {
		ArgumentAssertions.assertNonNullElements( items, "items" );
		
		this.items = Set.copyOf( items );
		this.weight = items.stream().map( MeetInTheMiddleKnapsackItem::getWeight ).reduce( BigDecimal.ZERO, BigDecimal::add );
		this.value = items.stream().map( MeetInTheMiddleKnapsackItem::getValue ).reduce( BigDecimal.ZERO, BigDecimal::add );
	}
	
	
	public Set<MeetInTheMiddleKnapsackItem> getItems() {
		return this.items;
	}


	public BigDecimal getWeight() {
		return this.weight;
	}


	public BigDecimal getValue() {
		return this.value;
	}

}
