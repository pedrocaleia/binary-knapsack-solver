package pt.pcaleia.bks.dynamic.arraycreators;


/**
 * @author Pedro Caleia
 */
public final class IntegerArrayCreator implements ArrayCreator<Integer> {

	
	@Override
	public Integer[] createArray( int size ) {
		return new Integer[ size ];
	}


	@Override
	public Integer[][] createArray( int size1, int size2 ) {
		return new Integer[ size1 ][ size2 ];
	}
	
}
