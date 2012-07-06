package com.claygregory.common.collection;

import java.util.Iterator;

/**
 * An iterator that does not allow removal of items
 * 
 * @author Clay Gregory
 *
 * @param <T> Element type of Iterator
 */
public abstract class UnmodifiableIterator<T> implements Iterator<T> {

	/**
	 * Will throw an UnsupportedOperationException
	 */
	@Override
	public void remove( ) {
		throw new UnsupportedOperationException( );
	}
	
}
