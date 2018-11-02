package pt.pcaleia.bks.dynamic;


import java.math.BigDecimal;

import pt.pcaleia.util.asserts.NumberArgumentAssertions;


/**
 * @author Pedro Caleia
 */
public final class DynamicKnapsackItem {
	
	
	private final int weight;
	private final BigDecimal value;
	
	
	public DynamicKnapsackItem( int weight, BigDecimal value ) {
		NumberArgumentAssertions.assertPositive( weight, "weight" );
		if( weight > Integer.MAX_VALUE - 1 ) {
			throw new IllegalArgumentException( "The argument 'maximumSolutionWeight' cannot be higher than Integer.MAX_VALUE - 1." );
		}
		NumberArgumentAssertions.assertPositive( value, "value" );
		
		this.weight = weight;
		this.value = value;
	}


	public int getWeight() {
		return this.weight;
	}
	
	
	public BigDecimal getValue() {
		return this.value;
	}

}
