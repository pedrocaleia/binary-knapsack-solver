package pt.pcaleia.bks.mitm;


import java.math.BigDecimal;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.NavigableSet;
import java.util.Set;
import java.util.TreeSet;

import pt.pcaleia.util.asserts.ArgumentAssertions;
import pt.pcaleia.util.asserts.NumberArgumentAssertions;


/**
 * @author Pedro Caleia
 */
public final class MeetInTheMiddleKnapsackSolver {
	
	
	private final SubsetKnapsackSolutionWeightComparator subsetSolutionWeightComparator;
	
	
	public MeetInTheMiddleKnapsackSolver() {
		this.subsetSolutionWeightComparator = new SubsetKnapsackSolutionWeightComparator();
	}
	
	
	public MeetInTheMiddleKnapsackSolution findSolution( Collection<MeetInTheMiddleKnapsackItem> items, BigDecimal maximumSolutionWeight ) {
		ArgumentAssertions.assertNotEmptyAndNonNullElements( items, "items" );
		NumberArgumentAssertions.assertPositive( maximumSolutionWeight, "maximumSolutionWeight" );
		
		// Divide the items into two sets
		Set<MeetInTheMiddleKnapsackItem> items1 = new HashSet<>();
		Set<MeetInTheMiddleKnapsackItem> items2 = new HashSet<>();
		this.breakItemsIntoTwoSets( items, items1, items2 );
		
		// Calculate subset solutions for items1
		NavigableSet<SubsetKnapsackSolution> solutions1 = computeSubsetSolutions( items1, maximumSolutionWeight );
		
		// Calculate subset solutions for items2
		NavigableSet<SubsetKnapsackSolution> solutions2 = computeSubsetSolutions( items2, maximumSolutionWeight );
		
		SubsetKnapsackSolution selectedSolution1 = null;
		SubsetKnapsackSolution selectedSolution2 = null;
		
		BigDecimal selectedSolutionsTotalValue = BigDecimal.ZERO;
		for( SubsetKnapsackSolution solution1 : solutions1 ) {
			SubsetKnapsackSolution solution2 = findBestFitSolution( solution1, solutions2, maximumSolutionWeight );
			
			BigDecimal currentSolutionsTotalValue = solution1.getValue().add( solution2.getValue() );
			
			// If the combined value of the current solutions under test is bigger than the one previously tested
			// then this is the new best combination that gives the highest value
			if( currentSolutionsTotalValue.compareTo( selectedSolutionsTotalValue ) >= 0 ) {
				selectedSolution1 = solution1;
				selectedSolution2 = solution2;
				selectedSolutionsTotalValue = currentSolutionsTotalValue;
			}
		}
		
		return this.joinSolutions( selectedSolution1, selectedSolution2 );
	}
	
	
	private void breakItemsIntoTwoSets( Collection<MeetInTheMiddleKnapsackItem> items, Set<MeetInTheMiddleKnapsackItem> items1, Set<MeetInTheMiddleKnapsackItem> items2 ) {
		int half = items.size() / 2;
		Iterator<MeetInTheMiddleKnapsackItem> iterator = items.iterator();
		for( int n = 0; n < half; n++ ) {
			MeetInTheMiddleKnapsackItem item = iterator.next();
			items1.add( item );
		}
		
		for( int n = half; n < items.size(); n++ ) {
			MeetInTheMiddleKnapsackItem item = iterator.next();
			items2.add( item );
		}
	}
	
	
	private NavigableSet<SubsetKnapsackSolution> computeSubsetSolutions( Set<MeetInTheMiddleKnapsackItem> items, BigDecimal maximumSolutionWeight ) {
		// Keep the solutions sorted by weight, from lowest to highest
		NavigableSet<SubsetKnapsackSolution> subsetSolutions = new TreeSet<>( this.subsetSolutionWeightComparator );
		
		// Add the empty solution to the subset of solutions
		SubsetKnapsackSolution emptySolution = new SubsetKnapsackSolution( null, null, BigDecimal.ZERO, BigDecimal.ZERO );
		subsetSolutions.add( emptySolution );
		
		// Compute all the possible subset solutions
		for( MeetInTheMiddleKnapsackItem item : items ) {
			Set<SubsetKnapsackSolution> previousSubsetSolutions = new HashSet<>( subsetSolutions );
			
			for( SubsetKnapsackSolution previousSubsetSolution : previousSubsetSolutions ) {
				BigDecimal newSolutionWeight = previousSubsetSolution.getWeight().add( item.getWeight() );
				
				// If the subset solution is below the maximum weight then
				if( newSolutionWeight.compareTo( maximumSolutionWeight ) <= 0 ) {
					// Create the subset solution
					BigDecimal newSolutionValue = previousSubsetSolution.getValue().add( item.getValue() );
					SubsetKnapsackSolution newSubsetSolution = new SubsetKnapsackSolution( previousSubsetSolution, item, newSolutionWeight, newSolutionValue );
					
					// Optimize the subset of solutions
					boolean shouldBeIn = false;
					
					// Get the solution that either has the same weight or has the greatest weight below the new subset solution
					// This always returns a solution because of the empty one
					SubsetKnapsackSolution floorSolution = subsetSolutions.floor( newSubsetSolution );

					// If the new subset solution value is bigger than the floor one (which has the same weight or the greatest weight
					// that is lower than the new one) then it has a place in the subset of solutions
					if( newSubsetSolution.getValue().compareTo( floorSolution.getValue() ) > 0 ) {
						shouldBeIn = true;
					}
					
					// In the case where the solution will be inserted into the subset of solutions then the subset needs to be optimized
					if( shouldBeIn ) {
						// Remove from the subset of solutions all those that have the same or higher weight but have a lower value
						while( true ) {
							// Get the solution with the same weight or that has the lowest of the higher weights
							SubsetKnapsackSolution ceilingSolution = subsetSolutions.ceiling( newSubsetSolution );
							
							if( ceilingSolution == null ) {
								// No ceiling solution found so break
								break;
							}
							else {
								if( ceilingSolution.getValue().compareTo( newSubsetSolution.getValue() ) < 0 ) {
									subsetSolutions.remove( ceilingSolution );
								}
								else {
									// The first ceiling solution was found with higher value so break
									break;
								}
							}
						}
						
						// Finally add the new subset solution to the subset of solutions
						subsetSolutions.add( newSubsetSolution );
					}
				}
			}
		}
		
		return subsetSolutions;
	}
	
	
	private MeetInTheMiddleKnapsackSolution joinSolutions( SubsetKnapsackSolution solution1, SubsetKnapsackSolution solution2) {
		Set<MeetInTheMiddleKnapsackItem> totalSelectedItems = new HashSet<>();
		totalSelectedItems.addAll( solution1.getItems() );
		totalSelectedItems.addAll( solution2.getItems() );
		
		MeetInTheMiddleKnapsackSolution finalSolution = new MeetInTheMiddleKnapsackSolution( totalSelectedItems );
		return finalSolution;
	}
	
	
	private SubsetKnapsackSolution findBestFitSolution( SubsetKnapsackSolution solution1, NavigableSet<SubsetKnapsackSolution> solutions2, BigDecimal maximumSolutionWeight ) {
		BigDecimal missingSolutionWeight = maximumSolutionWeight.subtract( solution1.getWeight() );
		
		SubsetKnapsackSolution dummySolution = new SubsetKnapsackSolution( null, null, missingSolutionWeight, BigDecimal.ZERO );
		
		SubsetKnapsackSolution bestFitSolution = solutions2.floor( dummySolution );
		
		return bestFitSolution;
	}

}
