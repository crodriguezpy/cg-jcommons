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

	public static<T> Iterable<T> filter( Iterable<T> itr, Predicate<T> predicate ) {
		return IteratorFilter.wrap( itr, predicate );
	}
	
}
