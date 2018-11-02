package pt.pcaleia.bks.mitm;


import java.math.BigDecimal;

import pt.pcaleia.util.asserts.NumberArgumentAssertions;


/**
 * @author Pedro Caleia
 */
public final class MeetInTheMiddleKnapsackItem {
	
	
	private final BigDecimal weight;
	private final BigDecimal value;
	
	
	public MeetInTheMiddleKnapsackItem( BigDecimal weight, BigDecimal value ) {
		NumberArgumentAssertions.assertPositive( weight, "weight" );
		NumberArgumentAssertions.assertPositive( value, "value" );
		
		this.weight = weight;
		this.value = value;
	}


	public BigDecimal getWeight() {
		return this.weight;
	}
	
	
	public BigDecimal getValue() {
		return this.value;
	}

}
