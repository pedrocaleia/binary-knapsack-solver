package pt.pcaleia.bks.mitm;


import java.math.BigDecimal;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;


/**
 * @author Pedro Caleia
 */
public final class MeetInTheMiddleKnapsackItemTest {
	
	
	@Test
	public void testThatTheConstructorReturnsWhenTheArgumentsAreValid() {
		MeetInTheMiddleKnapsackItem item = new MeetInTheMiddleKnapsackItem( BigDecimal.valueOf( 10 ), BigDecimal.valueOf( 20 ) );
		
		Assertions.assertEquals( BigDecimal.valueOf( 10 ), item.getWeight() );
		Assertions.assertEquals( BigDecimal.valueOf( 20 ), item.getValue() );
	}
	
	
	@Test
	public void testThatTheConstructorThrowsWhenTheWeightIsNegative() {
		Executable executable = () -> new MeetInTheMiddleKnapsackItem( BigDecimal.valueOf( -10 ), BigDecimal.valueOf( 20 ) );
		Assertions.assertThrows( IllegalArgumentException.class, executable );
	}
	
	
	@Test
	public void testThatTheConstructorThrowsWhenTheWeightIsZero() {
		Executable executable = () -> new MeetInTheMiddleKnapsackItem( BigDecimal.ZERO, BigDecimal.valueOf( 20 ) );
		Assertions.assertThrows( IllegalArgumentException.class, executable );
	}
	
	
	@Test
	public void testThatTheConstructorThrowsWhenTheWeightIsNull() {
		Executable executable = () -> new MeetInTheMiddleKnapsackItem( null, BigDecimal.valueOf( 20 ) );
		Assertions.assertThrows( IllegalArgumentException.class, executable );
	}
	
	
	@Test
	public void testThatTheConstructorThrowsWhenTheValueIsNegative() {
		Executable executable = () -> new MeetInTheMiddleKnapsackItem( BigDecimal.valueOf( 10 ), BigDecimal.valueOf( -10 ) );
		Assertions.assertThrows( IllegalArgumentException.class, executable );
	}
	
	
	@Test
	public void testThatTheConstructorThrowsWhenTheValueIsZero() {
		Executable executable = () -> new MeetInTheMiddleKnapsackItem( BigDecimal.valueOf( 10 ), BigDecimal.ZERO );
		Assertions.assertThrows( IllegalArgumentException.class, executable );
	}
	
	
	@Test
	public void testThatTheConstructorThrowsWhenTheValueIsNull() {
		Executable executable = () -> new MeetInTheMiddleKnapsackItem( BigDecimal.valueOf( 10 ), null );
		Assertions.assertThrows( IllegalArgumentException.class, executable );
	}

}
