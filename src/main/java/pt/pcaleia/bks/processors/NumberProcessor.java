package pt.pcaleia.bks.processors;


import java.util.Collection;
import java.util.Comparator;
import java.util.stream.Stream;


/**
 * @author Pedro Caleia
 *
 * @param <T>
 */
public interface NumberProcessor<T> extends Comparator<T> {
	
	
	public T zero();
	
	public T add( T value1, T value2 );
	
	public T addAll( Collection<T> values );
	
	public T addAll( Stream<T> values );
	
	public T subtract( T value1, T value2 );
	
	public T max( T value1, T value2 );
	
	public T[] getArray( int size );
	
	public T[][] getArray( int size1, int size2 );

}
