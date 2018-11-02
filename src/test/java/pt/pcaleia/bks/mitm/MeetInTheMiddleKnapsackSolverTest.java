package pt.pcaleia.bks.mitm;


import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import pt.pcaleia.bks.mitm.MeetInTheMiddleKnapsackItem;
import pt.pcaleia.bks.mitm.MeetInTheMiddleKnapsackSolution;
import pt.pcaleia.bks.mitm.MeetInTheMiddleKnapsackSolver;


/**
 * @author Pedro Caleia
 */
public final class MeetInTheMiddleKnapsackSolverTest {
	
	
	private static MeetInTheMiddleKnapsackSolver SOLVER;
	
	
	@BeforeAll
	public static void beforeAll() {
		SOLVER = new MeetInTheMiddleKnapsackSolver();
	}

	
	@Test
	public void testThatFindSolutionReturnsTheCorrectSolution() {
		List<MeetInTheMiddleKnapsackItem> items = new ArrayList<>();
		items.add( new MeetInTheMiddleKnapsackItem( BigDecimal.valueOf( 14 ), BigDecimal.valueOf( 4 ) ) );
		items.add( new MeetInTheMiddleKnapsackItem( BigDecimal.valueOf( 10 ), BigDecimal.valueOf( 6 ) ) );
		items.add( new MeetInTheMiddleKnapsackItem( BigDecimal.valueOf( 8 ), BigDecimal.valueOf( 5 ) ) );
		items.add( new MeetInTheMiddleKnapsackItem( BigDecimal.valueOf( 11 ), BigDecimal.valueOf( 7 ) ) );
		items.add( new MeetInTheMiddleKnapsackItem( BigDecimal.valueOf( 14 ), BigDecimal.valueOf( 3 ) ) );
		items.add( new MeetInTheMiddleKnapsackItem( BigDecimal.valueOf( 7 ), BigDecimal.valueOf( 1 ) ) );
		items.add( new MeetInTheMiddleKnapsackItem( BigDecimal.valueOf( 9 ), BigDecimal.valueOf( 7 ) ) );

		// Solution
		MeetInTheMiddleKnapsackSolution solution = SOLVER.findSolution( items, BigDecimal.valueOf( 18 ) );
		
		// Assertions
		Assertions.assertEquals( BigDecimal.valueOf( 17 ), solution.getWeight() );
		Assertions.assertEquals( BigDecimal.valueOf( 12 ), solution.getValue() );
		
		Set<MeetInTheMiddleKnapsackItem> solutionItems = new HashSet<>();
		solutionItems.add( items.get( 2 ) );
		solutionItems.add( items.get( 6 ) );
		Assertions.assertEquals( solutionItems, solution.getItems() );
	}
	
	
	@Test
	public void testThatFindSolutionReturnsTheCorrectSolutionWhenAllItemsHaveTheSameWeight() {
		List<MeetInTheMiddleKnapsackItem> items = new ArrayList<>();
		items.add( new MeetInTheMiddleKnapsackItem( BigDecimal.valueOf( 10 ), BigDecimal.valueOf( 4 ) ) );
		items.add( new MeetInTheMiddleKnapsackItem( BigDecimal.valueOf( 10 ), BigDecimal.valueOf( 6 ) ) );
		items.add( new MeetInTheMiddleKnapsackItem( BigDecimal.valueOf( 10 ), BigDecimal.valueOf( 5 ) ) );
		items.add( new MeetInTheMiddleKnapsackItem( BigDecimal.valueOf( 10 ), BigDecimal.valueOf( 7 ) ) );
		items.add( new MeetInTheMiddleKnapsackItem( BigDecimal.valueOf( 10 ), BigDecimal.valueOf( 3 ) ) );
		items.add( new MeetInTheMiddleKnapsackItem( BigDecimal.valueOf( 10 ), BigDecimal.valueOf( 1 ) ) );
		items.add( new MeetInTheMiddleKnapsackItem( BigDecimal.valueOf( 10 ), BigDecimal.valueOf( 7 ) ) );

		// Solution
		MeetInTheMiddleKnapsackSolution solution = SOLVER.findSolution( items, BigDecimal.valueOf( 50 ) );
		
		// Assertions
		Assertions.assertEquals( BigDecimal.valueOf( 50 ), solution.getWeight() );
		Assertions.assertEquals( BigDecimal.valueOf( 29 ), solution.getValue() );
		
		Set<MeetInTheMiddleKnapsackItem> solutionItems = new HashSet<>();
		solutionItems.add( items.get( 0 ) );
		solutionItems.add( items.get( 1 ) );
		solutionItems.add( items.get( 2 ) );
		solutionItems.add( items.get( 3 ) );
		solutionItems.add( items.get( 6 ) );
		Assertions.assertEquals( solutionItems, solution.getItems() );
	}
	
	
	@Test
	public void testThatFindSolutionReturnsTheCorrectSolutionWhenAllItemsWeightAreBiggerThanTheMaximumSolutionWeight() {
		List<MeetInTheMiddleKnapsackItem> items = new ArrayList<>();
		items.add( new MeetInTheMiddleKnapsackItem( BigDecimal.valueOf( 10 ), BigDecimal.valueOf( 4 ) ) );
		items.add( new MeetInTheMiddleKnapsackItem( BigDecimal.valueOf( 10 ), BigDecimal.valueOf( 6 ) ) );
		items.add( new MeetInTheMiddleKnapsackItem( BigDecimal.valueOf( 10 ), BigDecimal.valueOf( 5 ) ) );
		items.add( new MeetInTheMiddleKnapsackItem( BigDecimal.valueOf( 10 ), BigDecimal.valueOf( 7 ) ) );
		items.add( new MeetInTheMiddleKnapsackItem( BigDecimal.valueOf( 10 ), BigDecimal.valueOf( 3 ) ) );
		items.add( new MeetInTheMiddleKnapsackItem( BigDecimal.valueOf( 10 ), BigDecimal.valueOf( 1 ) ) );
		items.add( new MeetInTheMiddleKnapsackItem( BigDecimal.valueOf( 10 ), BigDecimal.valueOf( 7 ) ) );

		// Solution
		MeetInTheMiddleKnapsackSolution solution = SOLVER.findSolution( items, BigDecimal.valueOf( 9 ) );
		
		// Assertions
		Assertions.assertEquals( BigDecimal.ZERO, solution.getWeight() );
		Assertions.assertEquals( BigDecimal.ZERO, solution.getValue() );
		
		Set<MeetInTheMiddleKnapsackItem> solutionItems = new HashSet<>();
		Assertions.assertEquals( solutionItems, solution.getItems() );
	}
	
	
	@Test
	public void testThatFindSolutionThrowsWhenTheItemCollectionIsNull() {
		Executable executable = () -> SOLVER.findSolution( null, BigDecimal.valueOf( 1_000 ) );
		Assertions.assertThrows( IllegalArgumentException.class, executable );
	}
	
	
	@Test
	public void testThatFindSolutionThrowsWhenTheMaximumSolutionWeightIsNegative() {
		List<MeetInTheMiddleKnapsackItem> items = new ArrayList<>();
		items.add( new MeetInTheMiddleKnapsackItem( BigDecimal.valueOf( 14 ), BigDecimal.valueOf( 4 ) ) );
		
		Executable executable = () -> SOLVER.findSolution( items, BigDecimal.valueOf( -1 ) );
		Assertions.assertThrows( IllegalArgumentException.class, executable );
	}
	
	
	@Test
	public void testThatFindSolutionThrowsWhenTheMaximumSolutionWeightIsZero() {
		List<MeetInTheMiddleKnapsackItem> items = new ArrayList<>();
		items.add( new MeetInTheMiddleKnapsackItem( BigDecimal.valueOf( 14 ), BigDecimal.valueOf( 4 ) ) );
		
		Executable executable = () -> SOLVER.findSolution( items, BigDecimal.ZERO );
		Assertions.assertThrows( IllegalArgumentException.class, executable );
	}
	
	
	@Test
	public void testThatFindSolutionThrowsWhenTheMaximumSolutionWeightIsNull() {
		List<MeetInTheMiddleKnapsackItem> items = new ArrayList<>();
		items.add( new MeetInTheMiddleKnapsackItem( BigDecimal.valueOf( 14 ), BigDecimal.valueOf( 4 ) ) );
		
		Executable executable = () -> SOLVER.findSolution( items, null );
		Assertions.assertThrows( IllegalArgumentException.class, executable );
	}

}
