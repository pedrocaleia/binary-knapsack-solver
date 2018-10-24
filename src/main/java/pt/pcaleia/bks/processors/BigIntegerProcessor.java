package pt.pcaleia.bks.processors;


import java.math.BigInteger;
import java.util.stream.Stream;


/**
 * @author Pedro Caleia
 */
public final class BigIntegerProcessor implements NumberProcessor<BigInteger> {

	
	@Override
	public BigInteger zero() {
		return BigInteger.ZERO;
	}
	
	
	@Override
	public BigInteger add( BigInteger number1, BigInteger number2 ) {
		return number1.add( number2 );
	}
	
	
	@Override
	public BigInteger addAll( Stream<BigInteger> numbers ) {
		return numbers.reduce( BigInteger.ZERO, BigInteger::add );
	}
	
	
	@Override
	public BigInteger subtract( BigInteger number1, BigInteger number2 ) {
		return number1.subtract( number2 );
	}


	@Override
	public BigInteger maxOf( BigInteger value1, BigInteger value2 ) {
		return value1.max( value2 );
	}
	
	
	@Override
	public int compare( BigInteger value1, BigInteger value2 ) {
		return value1.compareTo( value2 );
	}
	
}
