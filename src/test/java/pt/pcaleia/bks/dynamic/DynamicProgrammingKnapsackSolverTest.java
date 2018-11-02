package pt.pcaleia.bks.dynamic;


import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import pt.pcaleia.bks.dynamic.DynamicKnapsackItem;
import pt.pcaleia.bks.dynamic.DynamicKnapsackSolution;
import pt.pcaleia.bks.dynamic.DynamicProgrammingKnapsackSolver;


/**
 * @author Pedro Caleia
 */
public final class DynamicProgrammingKnapsackSolverTest {
	
	
	private static DynamicProgrammingKnapsackSolver SOLVER;
	
	
	@BeforeAll
	public static void beforeAll() {
		SOLVER = new DynamicProgrammingKnapsackSolver();
	}

	
	@Test
	public void testThatFindSolutionReturnsTheCorrectSolution() {
		List<DynamicKnapsackItem> items = new ArrayList<>();
		items.add( new DynamicKnapsackItem( 14, BigDecimal.valueOf( 4 ) ) );
		items.add( new DynamicKnapsackItem( 10, BigDecimal.valueOf( 6 ) ) );
		items.add( new DynamicKnapsackItem( 8, BigDecimal.valueOf( 5 ) ) );
		items.add( new DynamicKnapsackItem( 11, BigDecimal.valueOf( 7 ) ) );
		items.add( new DynamicKnapsackItem( 14, BigDecimal.valueOf( 3 ) ) );
		items.add( new DynamicKnapsackItem( 7, BigDecimal.valueOf( 1 ) ) );
		items.add( new DynamicKnapsackItem( 9, BigDecimal.valueOf( 7 ) ) );

		// Solution
		DynamicKnapsackSolution solution = SOLVER.findSolution( items, 18 );
		
		// Assertions
		Assertions.assertEquals( 17, solution.getWeight() );
		Assertions.assertEquals( BigDecimal.valueOf( 12 ), solution.getValue() );
		
		Set<DynamicKnapsackItem> solutionItems = new HashSet<>();
		solutionItems.add( items.get( 2 ) );
		solutionItems.add( items.get( 6 ) );
		Assertions.assertEquals( solutionItems, solution.getItems() );
	}
	
	
	@Test
	public void testThatFindSolutionReturnsTheCorrectSolutionWhenAllItemsHaveTheSameWeight() {
		List<DynamicKnapsackItem> items = new ArrayList<>();
		items.add( new DynamicKnapsackItem( 10, BigDecimal.valueOf( 4 ) ) );
		items.add( new DynamicKnapsackItem( 10, BigDecimal.valueOf( 6 ) ) );
		items.add( new DynamicKnapsackItem( 10, BigDecimal.valueOf( 5 ) ) );
		items.add( new DynamicKnapsackItem( 10, BigDecimal.valueOf( 7 ) ) );
		items.add( new DynamicKnapsackItem( 10, BigDecimal.valueOf( 3 ) ) );
		items.add( new DynamicKnapsackItem( 10, BigDecimal.valueOf( 1 ) ) );
		items.add( new DynamicKnapsackItem( 10, BigDecimal.valueOf( 7 ) ) );

		// Solution
		DynamicKnapsackSolution solution = SOLVER.findSolution( items, 50 );
		
		// Assertions
		Assertions.assertEquals( 50, solution.getWeight() );
		Assertions.assertEquals( BigDecimal.valueOf( 29 ), solution.getValue() );
		
		Set<DynamicKnapsackItem> solutionItems = new HashSet<>();
		solutionItems.add( items.get( 0 ) );
		solutionItems.add( items.get( 1 ) );
		solutionItems.add( items.get( 2 ) );
		solutionItems.add( items.get( 3 ) );
		solutionItems.add( items.get( 6 ) );
		Assertions.assertEquals( solutionItems, solution.getItems() );
	}
	
	
	@Test
	public void testThatFindSolutionReturnsTheCorrectSolutionWhenAllItemsWeightAreBiggerThanTheMaximumSolutionWeight() {
		List<DynamicKnapsackItem> items = new ArrayList<>();
		items.add( new DynamicKnapsackItem( 10, BigDecimal.valueOf( 4 ) ) );
		items.add( new DynamicKnapsackItem( 10, BigDecimal.valueOf( 6 ) ) );
		items.add( new DynamicKnapsackItem( 10, BigDecimal.valueOf( 5 ) ) );
		items.add( new DynamicKnapsackItem( 10, BigDecimal.valueOf( 7 ) ) );
		items.add( new DynamicKnapsackItem( 10, BigDecimal.valueOf( 3 ) ) );
		items.add( new DynamicKnapsackItem( 10, BigDecimal.valueOf( 1 ) ) );
		items.add( new DynamicKnapsackItem( 10, BigDecimal.valueOf( 7 ) ) );

		// Solution
		DynamicKnapsackSolution solution = SOLVER.findSolution( items, 9 );
		
		// Assertions
		Assertions.assertEquals( 0, solution.getWeight() );
		Assertions.assertEquals( BigDecimal.ZERO, solution.getValue() );
		
		Set<DynamicKnapsackItem> solutionItems = new HashSet<>();
		Assertions.assertEquals( solutionItems, solution.getItems() );
	}
	
	
	@Test
	public void testThatFindSolutionThrowsWhenTheItemCollectionIsNull() {
		Executable executable = () -> SOLVER.findSolution( null, 1_000 );
		Assertions.assertThrows( IllegalArgumentException.class, executable );
	}
	
	
	@Test
	public void testThatFindSolutionThrowsWhenTheMaximumSolutionWeightIsNegative() {
		List<DynamicKnapsackItem> items = new ArrayList<>();
		items.add( new DynamicKnapsackItem( 14, BigDecimal.valueOf( 4 ) ) );
		
		Executable executable = () -> SOLVER.findSolution( items, -1 );
		Assertions.assertThrows( IllegalArgumentException.class, executable );
	}
	
	
	@Test
	public void testThatFindSolutionThrowsWhenTheMaximumSolutionWeightIsZero() {
		List<DynamicKnapsackItem> items = new ArrayList<>();
		items.add( new DynamicKnapsackItem( 14, BigDecimal.valueOf( 4 ) ) );
		
		Executable executable = () -> SOLVER.findSolution( items, 0 );
		Assertions.assertThrows( IllegalArgumentException.class, executable );
	}
	
	
	@Test
	public void test_MAX_WEIGHT_MAX() { //TODO name
		List<DynamicKnapsackItem> items = new ArrayList<>();
		items.add( new DynamicKnapsackItem( 14, BigDecimal.valueOf( 4 ) ) );
		
		Executable executable = () -> SOLVER.findSolution( items, Integer.MAX_VALUE );
		Assertions.assertThrows( IllegalArgumentException.class, executable );
	}
	
}
