package pt.pcaleia.bks.processors;


import java.util.stream.Stream;


/**
 * @author Pedro Caleia
 */
public final class IntegerProcessor implements NumberProcessor<Integer> {

	
	@Override
	public Integer zero() {
		return 0;
	}
	
	
	@Override
	public Integer add( Integer value1, Integer value2 ) {
		return value1 + value2;
	}
	
	
	@Override
	public Integer addAll( Stream<Integer> values ) {
		return values.mapToInt( Integer::intValue ).sum();
	}
	
	
	@Override
	public Integer subtract( Integer value1, Integer value2 ) {
		return value1 - value2;
	}
	
	
	@Override
	public Integer max( Integer value1, Integer value2 ) {
		return Math.max( value1, value2 );
	}
	
	
	@Override
	public Integer[] getArray( int size ) {
		return new Integer[ size ];
	}


	@Override
	public Integer[][] getArray( int size1, int size2 ) {
		return new Integer[ size1 ][ size2 ];
	}


	@Override
	public int compare( Integer value1, Integer value2 ) {
		return value1.compareTo( value2 );
	}
	
}
