package pt.pcaleia.bks.dynamic.arraycreators;


/**
 * @author Pedro Caleia
 */
public final class LongArrayCreator implements ArrayCreator<Long> {

	
	@Override
	public Long[] createArray( int size ) {
		return new Long[ size ];
	}


	@Override
	public Long[][] createArray( int size1, int size2 ) {
		return new Long[ size1 ][ size2 ];
	}
	
}
