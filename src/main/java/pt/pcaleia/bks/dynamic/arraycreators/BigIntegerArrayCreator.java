package pt.pcaleia.bks.dynamic.arraycreators;


import java.math.BigInteger;


/**
 * @author Pedro Caleia
 */
public final class BigIntegerArrayCreator implements ArrayCreator<BigInteger> {

	
	@Override
	public BigInteger[] createArray( int size ) {
		return new BigInteger[ size ];
	}
	

	@Override
	public BigInteger[][] createArray( int size1, int size2 ) {
		return new BigInteger[ size1 ][ size2 ];
	}
	
}
