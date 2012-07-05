package com.claygregory.common.collection;

import java.util.Iterator;
import java.util.NoSuchElementException;

import com.claygregory.common.core.Predicate;

/**
 * A wrapper for iterators which filters out elements not matching provided predicate
 * 
 * @author Clay Gregory
 *
 * @param <T> Element type of iterator
 */
public class IteratorFilter<T> extends UnmodifiableIterator<T> {

	private Iterator<T> itr;
	
	private Predicate<? super T> predicate;
	
	private T peek;
	
	public static<T> Iterable<T> wrap( final Iterable<T> itr, final Predicate<? super T> predicate ) {
		return new Iterable<T>( ) {
			@Override
			public UnmodifiableIterator<T> iterator( ) {
				return new IteratorFilter<T>( itr.iterator( ), predicate );
			}
		};
	}
	
	public static<T> UnmodifiableIterator<T> wrap( Iterator<T> itr, Predicate<? super T> predicate ) {
		return new IteratorFilter<T>( itr, predicate );
	}
	
	private IteratorFilter( Iterator<T> itr, Predicate<? super T> predicate ) {
		this.itr = itr;
		this.predicate = predicate;
	}

	@Override
	public boolean hasNext( ) {
		return this.peek( ) != null;
	}

	@Override
	public T next( ) {
		T next = this.consumeNext( );
		if ( next == null )
			throw new NoSuchElementException( );
		
		return next;
	}
	
	private T consumeNext( ) {
		T next = this.peek( );
		this.peek = null;
		return next;
	}
	
	private T peek( ) {
		if ( this.peek == null )
			while ( this.itr.hasNext( ) ) {
				T next = this.itr.next( );
				if( this.predicate.apply( next ) ) {
					this.peek = next;
					break;
				}
			}
		return this.peek;
	}
}
