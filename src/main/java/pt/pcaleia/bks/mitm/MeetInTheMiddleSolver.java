package pt.pcaleia.bks.mitm;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.NavigableSet;
import java.util.Set;
import java.util.TreeSet;

import pt.pcaleia.bks.KnapsackItem;
import pt.pcaleia.bks.KnapsackSolution;
import pt.pcaleia.bks.processors.NumberProcessor;


/**
 * @author Pedro Caleia
 */
public final class MeetInTheMiddleSolver<W, V> {
	
	
	private final SubsetKnapsackSolutionWeightComparator<W> subsetSolutionWeightComparator;
	private final NumberProcessor<W> weightProcessor;
	private final NumberProcessor<V> valueProcessor;
	
	
	public MeetInTheMiddleSolver( NumberProcessor<W> weightProcessor, NumberProcessor<V> valueProcessor ) {
		this.subsetSolutionWeightComparator = new SubsetKnapsackSolutionWeightComparator<>( weightProcessor );
		this.weightProcessor = weightProcessor;
		this.valueProcessor = valueProcessor;
	}
	
	
	public KnapsackSolution<W, V> findSolution( Set<KnapsackItem<W, V>> items, W maximumSolutionWeight ) {
		// Divide the items into two sets
		List<Set<KnapsackItem<W, V>>> sets = this.breakItemsIntoTwoSets( items );
		Set<KnapsackItem<W, V>> items1 = sets.get( 0 );
		Set<KnapsackItem<W, V>> items2 = sets.get( 1 );
		
		// Calculate subset solutions for items1
		NavigableSet<SubsetKnapsackSolution<W, V>> solutions1 = computeSubsetSolutions( items1, maximumSolutionWeight );
		
		// Calculate subset solutions for items2
		NavigableSet<SubsetKnapsackSolution<W, V>> solutions2 = computeSubsetSolutions( items2, maximumSolutionWeight );
		
		SubsetKnapsackSolution<W, V> selectedSolution1 = null;
		SubsetKnapsackSolution<W, V> selectedSolution2 = null;
		
		V selectedSolutionsTotalValue = this.valueProcessor.zero();
		for( SubsetKnapsackSolution<W, V> solution1 : solutions1 ) {
			SubsetKnapsackSolution<W, V> solution2 = findBestFitSolution( solution1, solutions2, maximumSolutionWeight );
			
			V currentSolutionsTotalValue = this.valueProcessor.add( solution1.getValue(), solution2.getValue() );
			
			// If the combined value of the current solutions under test is bigger than the one previously tested
			// then this is the new best combination that gives the highest value
			if( this.valueProcessor.compare( currentSolutionsTotalValue, selectedSolutionsTotalValue ) >= 0 ) {
				selectedSolution1 = solution1;
				selectedSolution2 = solution2;
				selectedSolutionsTotalValue = currentSolutionsTotalValue;
			}
		}
		
		return this.joinSolutions( selectedSolution1, selectedSolution2, items1, items2 );
	}
	
	
	private List<Set<KnapsackItem<W, V>>> breakItemsIntoTwoSets( Set<KnapsackItem<W, V>> items ) {
		Set<KnapsackItem<W, V>> items1 = new HashSet<>();
		Set<KnapsackItem<W, V>> items2 = new HashSet<>();
		
		int half = items.size() / 2;
		Iterator<KnapsackItem<W, V>> iterator = items.iterator();
		for( int n = 0; n < half; n++ ) {
			KnapsackItem<W, V> item = iterator.next();
			items1.add( item );
		}
		
		for( int n = half; n < items.size(); n++ ) {
			KnapsackItem<W, V> item = iterator.next();
			items2.add( item );
		}
		
		List<Set<KnapsackItem<W, V>>> sets = new ArrayList<>();
		sets.add( items1 );
		sets.add( items2 );
		
		return sets;
	}
	
	
	private NavigableSet<SubsetKnapsackSolution<W, V>> computeSubsetSolutions( Set<KnapsackItem<W, V>> items, W maximumSolutionWeight ) {
		// Keep the solutions sorted by weight, from lowest to highest
		NavigableSet<SubsetKnapsackSolution<W, V>> subsetSolutions = new TreeSet<>( this.subsetSolutionWeightComparator );
		
		// Add the empty solution to the subset of solutions
		SubsetKnapsackSolution<W, V> emptySolution = new SubsetKnapsackSolution<>( "", this.weightProcessor.zero(), this.valueProcessor.zero() );
		subsetSolutions.add( emptySolution );
		
		// Compute all the possible subset solutions
		int itemIndex = 0;
		for( KnapsackItem<W, V> item : items ) {
			Set<SubsetKnapsackSolution<W, V>> previousSubsetSolutions = new HashSet<>( subsetSolutions );
			
			for( SubsetKnapsackSolution<W, V> previousSubsetSolution : previousSubsetSolutions ) {
				W newSolutionWeight = this.weightProcessor.add( previousSubsetSolution.getWeight(), item.getWeight() );
				
				// If the subset solution is below the maximum weight then
				if( this.weightProcessor.compare( newSolutionWeight, maximumSolutionWeight ) <= 0 ) {
					// Code the new selected items
					StringBuilder newSelectedItems = new StringBuilder( previousSubsetSolution.getItems() );
					for( int i = newSelectedItems.length(); i < itemIndex; i++ ) {
						newSelectedItems.append( '0' );
					}
					newSelectedItems.append( '1' );
					
					// Create the subset solution
					V newSolutionValue = this.valueProcessor.add( previousSubsetSolution.getValue(), item.getValue() );
					SubsetKnapsackSolution<W, V> newSubsetSolution = new SubsetKnapsackSolution<W, V>( newSelectedItems.toString(), newSolutionWeight, newSolutionValue );
					
					// Optimize the subset of solutions
					boolean shouldBeIn = false;
					
					// Get the solution that either has the same weight or has the greatest weight below the new subset solution
					SubsetKnapsackSolution<W, V> floorSolution = subsetSolutions.floor( newSubsetSolution );
					if( floorSolution == null ) {
						// If the floor solution doesn't exist then the new subset solution has a place in the subset of solutions
						shouldBeIn = true;
					}
					else {
						// If the new subset solution value is bigger than the floor one (which has the same weight or the greatest weight
						// that is lower than the new one) then it has a place in the subset of solutions
						if( this.valueProcessor.compare( newSubsetSolution.getValue(), floorSolution.getValue() ) > 0 ) {
							shouldBeIn = true;
						}
					}
					
					// In the case where the solution will be inserted into the subset of solutions then the subset needs to be optimized
					if(shouldBeIn) {
						// Remove from the subset of solutions all those that have the same or higher weight but have a lower value
						while( true ) {
							// Get the solution with the same weight or that has the lowest of the higher weights
							SubsetKnapsackSolution<W, V> ceilingSolution = subsetSolutions.ceiling( newSubsetSolution );
							
							if( ceilingSolution == null) {
								// No ceiling solution found so break
								break;
							}
							else {
								if( this.valueProcessor.compare( ceilingSolution.getValue(), newSubsetSolution.getValue() ) < 0 ) {
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
		
			itemIndex++;
		}
		
		return subsetSolutions;
	}
	
	
	private KnapsackSolution<W, V> joinSolutions( SubsetKnapsackSolution<W, V> solution1, SubsetKnapsackSolution<W, V> solution2, Set<KnapsackItem<W, V>> items1, Set<KnapsackItem<W, V>> items2 ) {
		Set<KnapsackItem<W, V>> selectedItems1 = new HashSet<>();
		Set<KnapsackItem<W, V>> selectedItems2 = new HashSet<>();
		
		Iterator<KnapsackItem<W, V>> iterator1 = items1.iterator();
		String solution1Items = solution1.getItems();
		for( int n = 0; n < solution1Items.length(); n++ ) {
			KnapsackItem<W, V> item = iterator1.next();
			
			if( solution1Items.charAt( n ) == '1' ) {
				selectedItems1.add( item );
			}
		}
		
		Iterator<KnapsackItem<W, V>> iterator2 = items2.iterator();
		String solution2Items = solution2.getItems();
		for( int n = 0; n < solution2Items.length(); n++ ) {
			KnapsackItem<W, V> item = iterator2.next();
			
			if( solution2Items.charAt( n ) == '1' ) {
				selectedItems2.add( item );
			}
		}
		
		Set<KnapsackItem<W, V>> totalSelectedItems = new HashSet<>();
		totalSelectedItems.addAll( selectedItems1 );
		totalSelectedItems.addAll( selectedItems2 );
		
		KnapsackSolution<W, V> finalSolution = KnapsackSolution.getKnapsackSolution( totalSelectedItems, this.weightProcessor, this.valueProcessor );
		
		return finalSolution;
	}
	
	
	private SubsetKnapsackSolution<W, V> findBestFitSolution( SubsetKnapsackSolution<W, V> solution1, NavigableSet<SubsetKnapsackSolution<W, V>> solutions2, W maximumSolutionWeight ) {
		W missingSolutionWeight = this.weightProcessor.subtract( maximumSolutionWeight, solution1.getWeight() );
		
		SubsetKnapsackSolution<W, V> dummySolution = new SubsetKnapsackSolution<>( "", missingSolutionWeight, this.valueProcessor.zero() );
		
		SubsetKnapsackSolution<W, V> bestFitSolution = solutions2.floor( dummySolution );
		
		return bestFitSolution;
	}

}
