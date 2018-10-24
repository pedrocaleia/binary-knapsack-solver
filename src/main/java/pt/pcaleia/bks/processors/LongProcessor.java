package pt.pcaleia.bks.processors;


import java.util.stream.Stream;


/**
 * @author Pedro Caleia
 */
public final class LongProcessor implements NumberProcessor<Long> {

	
	@Override
	public Long zero() {
		return 0L;
	}
	
	
	@Override
	public Long add( Long value1, Long value2 ) {
		return value1 + value2;
	}
	
	
	@Override
	public Long addAll( Stream<Long> values ) {
		return values.mapToLong( Long::longValue ).sum();
	}
	
	
	@Override
	public Long subtract( Long value1, Long value2 ) {
		return value1 - value2;
	}
	
	
	@Override
	public Long maxOf( Long value1, Long value2 ) {
		return Math.max( value1, value2 );
	}


	@Override
	public int compare( Long value1, Long value2 ) {
		return value1.compareTo( value2 );
	}
	
}
