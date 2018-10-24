package pt.pcaleia.bks.dynamic;


import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import pt.pcaleia.bks.KnapsackItem;
import pt.pcaleia.bks.KnapsackSolution;
import pt.pcaleia.bks.dynamic.arraycreators.ArrayCreator;
import pt.pcaleia.bks.processors.NumberProcessor;


/**
 * @author Pedro Caleia
 */
public final class DynamicProgrammingSolver<V> {
	
	
	private final NumberProcessor<V> valueProcessor;
	private final ArrayCreator<V> valueArrayCreator;
	
	
	public DynamicProgrammingSolver( NumberProcessor<V> valueProcessor, ArrayCreator<V> valueArrayCreator ) {
		this.valueProcessor = valueProcessor;
		this.valueArrayCreator = valueArrayCreator;
	}
	
	
	public KnapsackSolution<Integer, V> findSolution( Collection<KnapsackItem<Integer, V>> items, Integer maximumSolutionWeight ) {
		// Solution table initialized from 0 to the number of items and from 0 to the maximumSolutionWeight
		V[][] solutionTable = this.valueArrayCreator.createArray( items.size() + 1, maximumSolutionWeight + 1 );
		
		// Creation of arrays to old the weights and values
		List<KnapsackItem<Integer, V>> itemsList = new ArrayList<>( items );
		int[] weights = itemsList.stream().mapToInt( KnapsackItem::getWeight ).toArray();
		V[] values = itemsList.stream().map( KnapsackItem::getValue ).toArray( this.valueArrayCreator::createArray );
		
		// Populate the first row with zeros
		for( int n = 0; n < solutionTable[ 0 ].length; n++ ) {
			solutionTable[ 0 ][ n ] = this.valueProcessor.zero();
		}
		
		// MAIN //TODO comment this better
		for( int itemIndex = 1; itemIndex <= items.size(); itemIndex++ ) {
			for( int weightIndex = 0; weightIndex <= maximumSolutionWeight; weightIndex++ ) {
				if( weights[ itemIndex - 1 ] > weightIndex ) {
					solutionTable[ itemIndex ][ weightIndex ] = solutionTable[ itemIndex - 1 ][ weightIndex ];
				}
				else {
					V a = solutionTable[ itemIndex - 1 ][ weightIndex ];
					V b = this.valueProcessor.add( values[ itemIndex - 1 ], solutionTable[ itemIndex - 1 ][ weightIndex - weights[ itemIndex - 1 ] ] );
					
					solutionTable[ itemIndex ][ weightIndex ] = this.valueProcessor.maxOf( a, b );
				}
			}
		}
		
		// Calculate which items belong to the solution
		V solutionValue = solutionTable[ items.size() ][ maximumSolutionWeight ];
		int missingSolutionWeight = maximumSolutionWeight;
		Set<KnapsackItem<Integer, V>> selectedItems = new HashSet<>();
		
		for( int itemIndex = itemsList.size(); itemIndex > 0 && this.valueProcessor.compare( solutionValue, this.valueProcessor.zero() ) > 0; itemIndex-- ) {
			 
            // either the result comes from the top
            // (K[i-1][w]) or from (val[i-1] + K[i-1]
            // [w-wt[i-1]]) as in Knapsack table. If
            // it comes from the latter one/ it means
            // the item is included.
            if( this.valueProcessor.compare( solutionValue, solutionTable[ itemIndex - 1 ][ missingSolutionWeight ] ) == 0 ) {
                continue;
            }
            else {
 
                // This item is included.
                //System.out.println( weights[ itemIndex - 1 ] + ", " + values[ itemIndex - 1 ] );
                selectedItems.add( itemsList.get( itemIndex - 1 ) );
 
                // Since this weight is included its
                // value is deducted
                solutionValue = this.valueProcessor.subtract( solutionValue, values[ itemIndex - 1 ] );
                missingSolutionWeight -= weights[ itemIndex - 1 ];
            }
        }
		
		
		
		return KnapsackSolution.getIntegerGenericKnapsackSolution( selectedItems, this.valueProcessor );
	}
	
}
