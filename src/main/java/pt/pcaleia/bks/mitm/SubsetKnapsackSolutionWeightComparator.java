package pt.pcaleia.bks.mitm;


import java.util.Comparator;

import pt.pcaleia.bks.processors.NumberProcessor;


/**
 * @author Pedro Caleia
 */
class SubsetKnapsackSolutionWeightComparator<W> implements Comparator<SubsetKnapsackSolution<W, ?>> {
	
	
	private final NumberProcessor<W> weightProcessor;
	
	
	public SubsetKnapsackSolutionWeightComparator( NumberProcessor<W> weightProcessor ) {
		this.weightProcessor = weightProcessor;
	}
	
	
	@Override
	public int compare( SubsetKnapsackSolution<W, ?> solution1, SubsetKnapsackSolution<W, ?> solution2 ) {
		return this.weightProcessor.compare( solution1.getWeight(), solution2.getWeight() );
	}

}
