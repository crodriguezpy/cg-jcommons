package com.claygregory.common.collection;

import com.claygregory.common.core.Predicate;

/**
 * 
 * General helpers when working with Iterables
 * 
 * @author Clay Gregory
 *
 */
public final class Iterables {

	/**
	 * Counts number of items in an Iterable
	 * 
	 * @param itr an Iterable
	 * @return count of items in itr
	 */
	public static<T> long count( Iterable<T> itr ) {
		return Iterators.count( itr.iterator( ) );
	}
	
	/**
	 * Provides a filter view of an Iterable based on provided Predicate. Iterators provided
	 * by view are not modifiable.
	 * 
	 * @param itr an Iterable to be filtered
	 * @param predicate to apply as filter
	 * @return an Iterable filtered by provided predicate
	 */
	public static<T> Iterable<T> filter( Iterable<T> itr, Predicate<T> predicate ) {
		return IteratorFilter.wrap( itr, predicate );
	}
	
}
