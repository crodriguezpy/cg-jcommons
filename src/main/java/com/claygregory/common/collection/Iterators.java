package com.claygregory.common.collection;

import java.util.Enumeration;
import java.util.Iterator;

import com.claygregory.common.core.Predicate;

/**
 * 
 * General helpers when working with Iterators
 * 
 * @author Clay Gregory
 *
 */
public final class Iterators {
	
	/**
	 * Counts number of items of the Iterator
	 * 
	 * @param itr an Iterator
	 * @return count of items in itr
	 */
	public static<T> long count( Iterator<T> itr ) {
		long i;
		for ( i = 0; itr.hasNext( ); itr.next( ) )
			i++;
		
		return i;
	}
	
	/**
	 * Provides a unmodifiable filtered view of provided Iterator
	 * 
	 * @param itr an Iterator to be live-filtered
	 * @param predicate to apply as filter
	 * @return an filtered view of provided Iterator
	 */
	public static<T> UnmodifiableIterator<T> filter( Iterator<T> itr, Predicate<T> predicate ) {
		return IteratorFilter.wrap( itr, predicate );
	}
	
	/**
	 * Adapts an Enumeration into an Iterator. The Iterator does not support removal.
	 * @param e an enumeration
	 * @return A Iterator view of the Enumeration
	 */
	public static<T> UnmodifiableIterator<T> fromEnumeration( final Enumeration<T> e ) {
		return new UnmodifiableIterator<T>( ) {
			@Override
			public boolean hasNext( ) {
				return e.hasMoreElements( );
			}

			@Override
			public T next( ) {
				return e.nextElement( );
			}
			
		};
	}
	
	public static<T> UnmodifiableIterator<T> unmodifiableIterator( final Iterator<T> itr ) {
		return new UnmodifiableIterator<T>( ) {
			@Override
			public boolean hasNext( ) {
				return itr.hasNext( );
			}
			
			@Override
			public T next( ) {
				return itr.next( );
			}
		};
	}
	
}
