package pt.pcaleia.bks.dynamic.arraycreators;


/**
 * @author Pedro Caleia
 */
public interface ArrayCreator<T> {
	
	
	public T[] createArray( int size );
	
	public T[][] createArray( int size1, int size2 );

}
