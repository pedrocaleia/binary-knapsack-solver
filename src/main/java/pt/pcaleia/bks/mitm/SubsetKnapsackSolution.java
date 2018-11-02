package pt.pcaleia.bks.mitm;


import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;


/**
 * @author Pedro Caleia
 */
final class SubsetKnapsackSolution {
	
	
	private final SubsetKnapsackSolution baseSolution;
	private final MeetInTheMiddleKnapsackItem item;
	private final BigDecimal weight;
	private final BigDecimal value;
	
	
	SubsetKnapsackSolution( SubsetKnapsackSolution baseSolution, MeetInTheMiddleKnapsackItem item, BigDecimal weight, BigDecimal value ) {
		this.baseSolution = baseSolution;
		this.item = item;
		this.weight = weight;
		this.value = value;
	}
	
	
	public Set<MeetInTheMiddleKnapsackItem> getItems() {
		Set<MeetInTheMiddleKnapsackItem> items = new HashSet<>();
		this.getItems( items );
		
		return items;
	}
	
	
	private void getItems( Set<MeetInTheMiddleKnapsackItem> items ) {
		if( this.baseSolution != null ) {
			items.add( this.item );
			this.baseSolution.getItems( items );
		}
	}


	public BigDecimal getWeight() {
		return this.weight;
	}


	public BigDecimal getValue() {
		return this.value;
	}

}
