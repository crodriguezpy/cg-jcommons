package com.claygregory.common.collection;

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

	public static<T> UnmodifiableIterator<T> filter( Iterator<T> itr, Predicate<T> predicate ) {
		return IteratorFilter.wrap( itr, predicate );
	}
	
}
