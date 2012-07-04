package com.claygregory.common.data;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

/**
 * Simple counter object to track discrete number of observations of unique values (based on {@link #equals(Object)})
 * 
 * @author Clay Gregory
 *
 * @param <T> Type of object being counted
 */
public class Counter<T> {
	
	public static class Count<T> implements Comparable<Count<T>> {
		
		private T value;
		
		private int count;
		
		public Count( T value, int count ) {
			this.value = value;
			this.count = count;
		}

		public T value( ) {
			return this.value;
		}

		public int count( ) {
			return this.count;
		}

		@Override
		public int compareTo( Count<T> o ) {
			return o.count( ) - this.count( );
		}
	}

	private final Map<T,Integer> countMap = new HashMap<T,Integer>( );
	
	private final int defaultIncrement;
	
	public Counter( ) {
		this( 1 );
	}
	
	public Counter( int defaultIncrement ) {
		this.defaultIncrement = defaultIncrement;
	}
	
	public int increment( T value ) {
		return this.increment( value, this.defaultIncrement );
	}
	
	public int increment( T value, int incr ) {
		this.countMap.put( value, count( value ) + incr );
		return count( value );
	}
	
	public void append( Counter<T> counter ) {
		for ( T o : counter.observedValues( ) )
			this.increment( o, counter.count( o ) );
	}
	
	public int count( T value ) {
		Integer c = this.countMap.get( value );
		return c == null ? 0 : c;
	}
	
	public Set<T> observedValues( ) {
		return this.countMap.keySet( );
	}
	
	public List<Count<T>> counts( ) {
		return this.counts( null, false );
	}
	
	public List<Count<T>> counts( boolean asc ) {
		return this.counts( null, asc );
	}
	
	public List<Count<T>> counts( Integer limit, boolean asc ) {
		List<Count<T>> counts = new ArrayList<Count<T>>( this.countMap.size( ) );
		
		for ( T value : this.countMap.keySet( ) )
			counts.add( new Count<T>( value, count( value ) ) );
		
		Collections.sort( counts );
		if ( !asc )
			Collections.reverse( counts );
				
		if ( limit != null && counts.size( ) > limit )
			counts.subList( limit, counts.size( ) ).clear( );
		
		return counts;
	}
	
	public void prune( int limit ) {
		
		Iterator<Entry<T,Integer>> itr = this.countMap.entrySet( ).iterator( );
		while ( itr.hasNext( ) ) {
			Entry<T,Integer> e = itr.next( );
			
			if ( e.getValue( ) <= limit )
				itr.remove( );
		}
	}
}
