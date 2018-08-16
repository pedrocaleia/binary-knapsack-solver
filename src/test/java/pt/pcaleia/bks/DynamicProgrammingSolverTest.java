package pt.pcaleia.bks;


import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import pt.pcaleia.bks.processors.LongProcessor;


/**
 * @author Pedro Caleia
 */
public final class DynamicProgrammingSolverTest {
	
	
	private static LongProcessor LONG_PROCESSOR;
	
	
	@BeforeAll
	public static void beforeAll() {
		LONG_PROCESSOR = new LongProcessor();
	}

	
	@Test
	public void test1() {
		Set<KnapsackItem<Integer, Long>> items = new HashSet<>();
		
		KnapsackItem<Integer, Long> item1 = KnapsackItem.getIntegerLongKnapsackItem( 14, 4 );
		items.add( item1 );
		KnapsackItem<Integer, Long> item2 = KnapsackItem.getIntegerLongKnapsackItem( 10, 6 );
		items.add( item2 );
		KnapsackItem<Integer, Long> item3 = KnapsackItem.getIntegerLongKnapsackItem( 8, 5 );
		items.add( item3 );
		KnapsackItem<Integer, Long> item4 = KnapsackItem.getIntegerLongKnapsackItem( 11, 7 );
		items.add( item4 );
		KnapsackItem<Integer, Long> item5 = KnapsackItem.getIntegerLongKnapsackItem( 14, 3 );
		items.add( item5 );
		KnapsackItem<Integer, Long> item6 = KnapsackItem.getIntegerLongKnapsackItem( 7, 1 );
		items.add( item6 );
		KnapsackItem<Integer, Long> item7 = KnapsackItem.getIntegerLongKnapsackItem( 9, 6 );
		items.add( item7 );

		// Solver
		DynamicProgrammingSolver<Long> solver = new DynamicProgrammingSolver<>( LONG_PROCESSOR );
		
		// Solution
		KnapsackSolution<Integer, Long> solution = solver.findSolution( items, 18 );
		
		// Assertions
		Assertions.assertTrue( solution.getWeight() <= 18 );
		Assertions.assertEquals( 11L, (long)solution.getValue() );
	}
	
	
	@Test
	public void test2() {
		Random random = new Random( 283 );
		
		Set<KnapsackItem<Integer, Long>> items = new HashSet<>();
		for( int n = 0; n < 1000; n++ ) {
			KnapsackItem<Integer, Long> item = KnapsackItem.getIntegerLongKnapsackItem( random.nextInt( 100 ) + 1, random.nextInt( 50 ) + 1 );
			items.add( item );
		}
		
		// Solver
		DynamicProgrammingSolver<Long> solver = new DynamicProgrammingSolver<>( LONG_PROCESSOR );
		
		// Solution
		KnapsackSolution<Integer, Long> solution = solver.findSolution( items, 20_000 );
		
		// Assertions
		Assertions.assertTrue( solution.getWeight() <= 20_000 );
		Assertions.assertEquals( 17992L, (long)solution.getValue() );
	}
	
}
