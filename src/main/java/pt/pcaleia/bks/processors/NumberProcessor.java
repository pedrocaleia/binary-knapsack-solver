package pt.pcaleia.bks.processors;


import java.util.Comparator;
import java.util.stream.Stream;


/**
 * @author Pedro Caleia
 */
public interface NumberProcessor<T> extends Comparator<T> {
	
	
	public T zero();
	
	public T add( T value1, T value2 );
	
	public T addAll( Stream<T> values );
	
	public T subtract( T value1, T value2 );
	
	public T maxOf( T value1, T value2 );
	
	public T[] createArray( int size );
	
	public T[][] createArray( int size1, int size2 );

}
