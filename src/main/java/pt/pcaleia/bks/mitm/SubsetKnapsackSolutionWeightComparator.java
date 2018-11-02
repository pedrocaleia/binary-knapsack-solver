package pt.pcaleia.bks.mitm;


import java.util.Comparator;


/**
 * @author Pedro Caleia
 */
class SubsetKnapsackSolutionWeightComparator implements Comparator<SubsetKnapsackSolution> {
	
	
	public SubsetKnapsackSolutionWeightComparator() {
		// Empty on purpose
	}
	
	
	@Override
	public int compare( SubsetKnapsackSolution solution1, SubsetKnapsackSolution solution2 ) {
		return solution1.getWeight().compareTo( solution2.getWeight() );
	}

}
