package pt.pcaleia.bks.dynamic;


import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import pt.pcaleia.util.asserts.ArgumentAssertions;
import pt.pcaleia.util.asserts.NumberArgumentAssertions;


/**
 * @author Pedro Caleia
 */
public final class DynamicProgrammingKnapsackSolver {
	
	
	public DynamicProgrammingKnapsackSolver() {
		// Empty on purpose
	}
	
	
	public DynamicKnapsackSolution findSolution( Collection<DynamicKnapsackItem> items, int maximumSolutionWeight ) {
		ArgumentAssertions.assertNotEmptyAndNonNullElements( items, "items" );
		NumberArgumentAssertions.assertPositive( maximumSolutionWeight, "maximumSolutionWeight" );
		if( maximumSolutionWeight > Integer.MAX_VALUE - 1 ) {
			throw new IllegalArgumentException( "The argument 'maximumSolutionWeight' cannot be higher than Integer.MAX_VALUE - 1." );
		}
		
		// Solution table initialized from 0 to the number of items and from 0 to the maximumSolutionWeight
		BigDecimal[][] solutionTable = new BigDecimal[ items.size() + 1 ][ maximumSolutionWeight + 1 ];
		
		// Creation of arrays to hold the weights and values
		List<DynamicKnapsackItem> itemsList = new ArrayList<>( items );
		int[] weights = itemsList.stream().mapToInt( DynamicKnapsackItem::getWeight ).toArray();
		BigDecimal[] values = itemsList.stream().map( DynamicKnapsackItem::getValue ).toArray( s -> new BigDecimal[ s ] );
		
		// Populate the first row with zeros
		for( int n = 0; n < solutionTable[ 0 ].length; n++ ) {
			solutionTable[ 0 ][ n ] = BigDecimal.ZERO;
		}
		
		// Main execution algorithm to fill the solution table
		for( int itemIndex = 1; itemIndex <= items.size(); itemIndex++ ) {
			for( int weightIndex = 0; weightIndex <= maximumSolutionWeight; weightIndex++ ) {
				int currentItemWeight = weights[ itemIndex - 1 ];
				
				// If the weight of the current item is bigger than the current weightIndex then it doesn't fit
				// so the value is equal to the previous value in the same column
				if( currentItemWeight > weightIndex ) {
					solutionTable[ itemIndex ][ weightIndex ] = solutionTable[ itemIndex - 1 ][ weightIndex ];
				}
				else {
					// Previous value without the item
					BigDecimal previousItemsValueAtCurrentWeightIndex = solutionTable[ itemIndex - 1 ][ weightIndex ];
					
					// Value of the item plus the value of the other items that fit with current item
					BigDecimal currentItemValue = values[ itemIndex - 1 ];
					BigDecimal valueOfItemsAllowingForCurrentItemWeight = solutionTable[ itemIndex - 1 ][ weightIndex - currentItemWeight ];
					BigDecimal currentValueIncludingItem = currentItemValue.add( valueOfItemsAllowingForCurrentItemWeight );
					
					// Max of the previous 2 values meaning that the item is included or not
					solutionTable[ itemIndex ][ weightIndex ] = previousItemsValueAtCurrentWeightIndex.max( currentValueIncludingItem );
				}
			}
		}
		
		// Determine which items belong to the solution
		BigDecimal currentSolutionValue = solutionTable[ items.size() ][ maximumSolutionWeight ];
		int weightIndex = maximumSolutionWeight;
		Set<DynamicKnapsackItem> selectedItems = new HashSet<>();
		
		for( int itemIndex = itemsList.size(); itemIndex > 0 && currentSolutionValue.compareTo( BigDecimal.ZERO ) > 0; itemIndex-- ) {
			// If the value comes from (is equal to) the upper row (same column) then the current item was not included in the solution table
			BigDecimal sameColumnUpperRowValue = solutionTable[ itemIndex - 1 ][ weightIndex ];
			if( currentSolutionValue.compareTo( sameColumnUpperRowValue ) == 0 ) {
				continue;
			}
			else {
				// This item is included
				selectedItems.add( itemsList.get( itemIndex - 1 ) );
				
				// The currentSolutionValue and weightIndex are updated to reflect the selecte item weight and value
				int selectedItemWeight = weights[ itemIndex - 1 ];
				BigDecimal selectedItemValue = values[ itemIndex - 1 ];
				weightIndex -= selectedItemWeight;
				currentSolutionValue = currentSolutionValue.subtract( selectedItemValue );
			}
		}
		
		return new DynamicKnapsackSolution( selectedItems );
	}
	
}
