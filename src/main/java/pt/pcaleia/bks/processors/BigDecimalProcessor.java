package pt.pcaleia.bks.processors;


import java.math.BigDecimal;
import java.util.stream.Stream;


/**
 * @author Pedro Caleia
 */
public final class BigDecimalProcessor implements NumberProcessor<BigDecimal> {

	
	@Override
	public BigDecimal zero() {
		return BigDecimal.ZERO;
	}
	
	
	@Override
	public BigDecimal add( BigDecimal number1, BigDecimal number2 ) {
		return number1.add( number2 );
	}
	
	
	@Override
	public BigDecimal addAll( Stream<BigDecimal> numbers ) {
		return numbers.reduce( BigDecimal.ZERO, BigDecimal::add );
	}
	
	
	@Override
	public BigDecimal subtract( BigDecimal number1, BigDecimal number2 ) {
		return number1.subtract( number2 );
	}


	@Override
	public BigDecimal max( BigDecimal value1, BigDecimal value2 ) {
		return value1.max( value2 );
	}
	

	@Override
	public BigDecimal[] createArray( int size ) {
		return new BigDecimal[ size ];
	}
	

	@Override
	public BigDecimal[][] createArray( int size1, int size2 ) {
		return new BigDecimal[ size1 ][ size2 ];
	}
	
	
	@Override
	public int compare( BigDecimal value1, BigDecimal value2 ) {
		return value1.compareTo( value2 );
	}
	
}
