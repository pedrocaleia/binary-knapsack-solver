package pt.pcaleia.bks.dynamic;


import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;


/**
 * @author Pedro Caleia
 */
public final class DynamicKnapsackSolutionTest {
	
	
	@Test
	public void testThatTheConstructorReturnsWhenTheArgumentIsASetWithItems() {
		Set<DynamicKnapsackItem> items = new HashSet<>();
		items.add( new DynamicKnapsackItem( 10, BigDecimal.valueOf( 20 ) ) );
		items.add( new DynamicKnapsackItem( 20, BigDecimal.valueOf( 35 ) ) );
		
		DynamicKnapsackSolution solution = new DynamicKnapsackSolution( items );
		
		Assertions.assertEquals( items, solution.getItems() );
		Assertions.assertEquals( 30, solution.getWeight() );
		Assertions.assertEquals( BigDecimal.valueOf( 55 ), solution.getValue() );
	}
	
	
	@Test
	public void testThatTheConstructorReturnsWhenTheArgumentIsAnEmptySet() {
		Set<DynamicKnapsackItem> items = new HashSet<>();
		
		DynamicKnapsackSolution solution = new DynamicKnapsackSolution( items );
		
		Assertions.assertEquals( items, solution.getItems() );
		Assertions.assertEquals( 0, solution.getWeight() );
		Assertions.assertEquals( BigDecimal.ZERO, solution.getValue() );
	}
	
	
	@Test
	public void testThatTheConstructorThrowsWhenTheArgumentIsNull() {
		Executable executable = () -> new DynamicKnapsackSolution( null );
		Assertions.assertThrows( IllegalArgumentException.class, executable );
	}
	
	
	@Test
	public void testThatTheConstructorThrowsWhenTheArgumentContainsNullElements() {
		Set<DynamicKnapsackItem> items = new HashSet<>();
		items.add( new DynamicKnapsackItem( 10, BigDecimal.valueOf( 20 ) ) );
		items.add( null );
		items.add( new DynamicKnapsackItem( 20, BigDecimal.valueOf( 35 ) ) );
		
		Executable executable = () -> new DynamicKnapsackSolution( items );
		Assertions.assertThrows( IllegalArgumentException.class, executable );
	}
	
	
	@Test
	public void testThatTheSetReturnedByGetItemsThrowsWhenTryingToModify() {
		Set<DynamicKnapsackItem> items = new HashSet<>();
		items.add( new DynamicKnapsackItem( 10, BigDecimal.valueOf( 20 ) ) );
		items.add( new DynamicKnapsackItem( 20, BigDecimal.valueOf( 35 ) ) );
		
		DynamicKnapsackSolution solution = new DynamicKnapsackSolution( items );
		
		Executable executable = () -> solution.getItems().add( new DynamicKnapsackItem( 30, BigDecimal.valueOf( 15 ) ) );
		Assertions.assertThrows( UnsupportedOperationException.class, executable );
	}
	
	
	@Test
	public void testThatTheGetItemsIsntAffectedWhenModifyingTheItemsSetAfterCallingTheConstructor() {
		Set<DynamicKnapsackItem> items = new HashSet<>();
		items.add( new DynamicKnapsackItem( 10, BigDecimal.valueOf( 20 ) ) );
		items.add( new DynamicKnapsackItem( 20, BigDecimal.valueOf( 35 ) ) );
		
		DynamicKnapsackSolution solution = new DynamicKnapsackSolution( items );
		
		items.add( new DynamicKnapsackItem( 30, BigDecimal.valueOf( 15 ) ) );
		
		Assertions.assertEquals( 2, solution.getItems().size() );
	}

}
