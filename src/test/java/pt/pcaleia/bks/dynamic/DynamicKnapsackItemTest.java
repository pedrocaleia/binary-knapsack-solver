package pt.pcaleia.bks.dynamic;


import java.math.BigDecimal;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;


/**
 * @author Pedro Caleia
 */
public final class DynamicKnapsackItemTest {
	
	
	@Test
	public void testThatTheConstructorReturnsWhenTheArgumentsAreValid() {
		DynamicKnapsackItem item = new DynamicKnapsackItem( 10, BigDecimal.valueOf( 20 ) );
		
		Assertions.assertEquals( 10, item.getWeight() );
		Assertions.assertEquals( BigDecimal.valueOf( 20 ), item.getValue() );
	}
	
	
	@Test
	public void testThatTheConstructorThrowsWhenTheWeightIsNegative() {
		Executable executable = () -> new DynamicKnapsackItem( -10, BigDecimal.valueOf( 20 ) );
		Assertions.assertThrows( IllegalArgumentException.class, executable );
	}
	
	
	@Test
	public void testThatTheConstructorThrowsWhenTheWeightIsZero() {
		Executable executable = () -> new DynamicKnapsackItem( 0, BigDecimal.valueOf( 20 ) );
		Assertions.assertThrows( IllegalArgumentException.class, executable );
	}
	
	
	@Test
	public void testThatTheConstructorThrowsWhenTheWeightIsIntegerMaxValue() {
		Executable executable = () -> new DynamicKnapsackItem( Integer.MAX_VALUE, BigDecimal.valueOf( 20 ) );
		Assertions.assertThrows( IllegalArgumentException.class, executable );
	}
	
	
	@Test
	public void testThatTheConstructorThrowsWhenTheValueIsNegative() {
		Executable executable = () -> new DynamicKnapsackItem( 10, BigDecimal.valueOf( -10 ) );
		Assertions.assertThrows( IllegalArgumentException.class, executable );
	}
	
	
	@Test
	public void testThatTheConstructorThrowsWhenTheValueIsZero() {
		Executable executable = () -> new DynamicKnapsackItem( 10, BigDecimal.ZERO );
		Assertions.assertThrows( IllegalArgumentException.class, executable );
	}
	
	
	@Test
	public void testThatTheConstructorThrowsWhenTheValueIsNull() {
		Executable executable = () -> new DynamicKnapsackItem( 10, null );
		Assertions.assertThrows( IllegalArgumentException.class, executable );
	}

}
