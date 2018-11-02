package pt.pcaleia.bks.mitm;


import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;


/**
 * @author Pedro Caleia
 */
public final class MeetInTheMiddleKnapsackSolutionTest {
	
	
	@Test
	public void testThatTheConstructorReturnsWhenTheArgumentIsASetWithItems() {
		Set<MeetInTheMiddleKnapsackItem> items = new HashSet<>();
		items.add( new MeetInTheMiddleKnapsackItem( BigDecimal.valueOf( 10 ), BigDecimal.valueOf( 20 ) ) );
		items.add( new MeetInTheMiddleKnapsackItem( BigDecimal.valueOf( 20 ), BigDecimal.valueOf( 35 ) ) );
		
		MeetInTheMiddleKnapsackSolution solution = new MeetInTheMiddleKnapsackSolution( items );
		
		Assertions.assertEquals( items, solution.getItems() );
		Assertions.assertEquals( BigDecimal.valueOf( 30 ), solution.getWeight() );
		Assertions.assertEquals( BigDecimal.valueOf( 55 ), solution.getValue() );
	}
	
	
	@Test
	public void testThatTheConstructorReturnsWhenTheArgumentIsAnEmptySet() {
		Set<MeetInTheMiddleKnapsackItem> items = new HashSet<>();
		
		MeetInTheMiddleKnapsackSolution solution = new MeetInTheMiddleKnapsackSolution( items );
		
		Assertions.assertEquals( items, solution.getItems() );
		Assertions.assertEquals( BigDecimal.ZERO, solution.getWeight() );
		Assertions.assertEquals( BigDecimal.ZERO, solution.getValue() );
	}
	
	
	@Test
	public void testThatTheConstructorThrowsWhenTheArgumentIsNull() {
		Executable executable = () -> new MeetInTheMiddleKnapsackSolution( null );
		Assertions.assertThrows( IllegalArgumentException.class, executable );
	}
	
	
	@Test
	public void testThatTheConstructorThrowsWhenTheArgumentContainsNullElements() {
		Set<MeetInTheMiddleKnapsackItem> items = new HashSet<>();
		items.add( new MeetInTheMiddleKnapsackItem( BigDecimal.valueOf( 10 ), BigDecimal.valueOf( 20 ) ) );
		items.add( null );
		items.add( new MeetInTheMiddleKnapsackItem( BigDecimal.valueOf( 20 ), BigDecimal.valueOf( 35 ) ) );
		
		Executable executable = () -> new MeetInTheMiddleKnapsackSolution( items );
		Assertions.assertThrows( IllegalArgumentException.class, executable );
	}
	
	
	@Test
	public void testThatTheSetReturnedByGetItemsThrowsWhenTryingToModify() {
		Set<MeetInTheMiddleKnapsackItem> items = new HashSet<>();
		items.add( new MeetInTheMiddleKnapsackItem( BigDecimal.valueOf( 10 ), BigDecimal.valueOf( 20 ) ) );
		items.add( new MeetInTheMiddleKnapsackItem( BigDecimal.valueOf( 20 ), BigDecimal.valueOf( 35 ) ) );
		
		MeetInTheMiddleKnapsackSolution solution = new MeetInTheMiddleKnapsackSolution( items );
		
		Executable executable = () -> solution.getItems().add( new MeetInTheMiddleKnapsackItem( BigDecimal.valueOf( 30 ), BigDecimal.valueOf( 15 ) ) );
		Assertions.assertThrows( UnsupportedOperationException.class, executable );
	}
	
	
	@Test
	public void testThatTheGetItemsIsntAffectedWhenModifyingTheItemsSetAfterCallingTheConstructor() {
		Set<MeetInTheMiddleKnapsackItem> items = new HashSet<>();
		items.add( new MeetInTheMiddleKnapsackItem( BigDecimal.valueOf( 10 ), BigDecimal.valueOf( 20 ) ) );
		items.add( new MeetInTheMiddleKnapsackItem( BigDecimal.valueOf( 20 ), BigDecimal.valueOf( 35 ) ) );
		
		MeetInTheMiddleKnapsackSolution solution = new MeetInTheMiddleKnapsackSolution( items );
		
		items.add( new MeetInTheMiddleKnapsackItem( BigDecimal.valueOf( 30 ), BigDecimal.valueOf( 15 ) ) );
		
		Assertions.assertEquals( 2, solution.getItems().size() );
	}

}
