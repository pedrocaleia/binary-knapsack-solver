package pt.pcaleia.bks.dynamic.arraycreators;


import java.math.BigDecimal;


/**
 * @author Pedro Caleia
 */
public final class BigDecimalArrayCreator implements ArrayCreator<BigDecimal> {

	
	@Override
	public BigDecimal[] createArray( int size ) {
		return new BigDecimal[ size ];
	}
	

	@Override
	public BigDecimal[][] createArray( int size1, int size2 ) {
		return new BigDecimal[ size1 ][ size2 ];
	}
	
}
