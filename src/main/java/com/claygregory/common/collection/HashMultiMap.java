package com.claygregory.common.collection;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Map allowing many values per given key. This implementation wraps a basic {@link java.util.HashMap}
 * 
 * @author Clay Gregory
 *
 * @param <K> Key type
 * @param <V> Value type
 */
public class HashMultiMap<K,V> implements MultiMap<K,V> {

	private Map<K,List<V>> map = new HashMap<K,List<V>>( );
	
	@Override
	public void clear( ) {
		this.map.clear( );
	}

	@Override
	public boolean containsKey( Object key ) {
		return this.map.containsKey( key );
	}

	@Override
	public boolean containsValue( Object value ) {
		for ( K key : this.map.keySet( ) )
			if ( this.map.get( key ).contains( value ) )
				return true;
				
		return false;
	}

	@Override
	public Set<java.util.Map.Entry<K, List<V>>> entrySet( ) {
		return this.map.entrySet( );
	}

	@Override
	public List<V> get( Object key ) {
		return this.map.get( key );
	}

	@Override
	public boolean isEmpty( ) {
		return this.map.isEmpty( );
	}

	@Override
	public Set<K> keySet( ) {
		return this.map.keySet( );
	}
	
	public List<V> put( K key, V value ) {
		return this.put( key, Collections.singletonList( value ) );
	}

	@Override
	public List<V> put( K key, List<V> value ) {
		List<V> values = this.map.get( key );
		List<V> previous = values == null ? null : new ArrayList<V>( values );
		
		if ( values == null ) {
			values = new ArrayList<V>( );
			this.map.put( key, values );
		}
		
		values.addAll( value );
	
		return previous;
	}

	@Override
	public void putAll( Map<? extends K, ? extends List<V>> m ) {
		for ( K key : m.keySet( ) )
			this.map.put( key, m.get( key ) );
	}

	@Override
	public List<V> remove( Object key ) {
		return this.map.remove( key );
	}

	@Override
	public int size( ) {
		int size = 0;
		for ( K key : this.map.keySet( ) )
			size += this.map.get( key ).size( );
				
		return size;
	}

	@Override
	public Collection<List<V>> values( ) {
		return this.map.values( );
	}

	@Override
	public String toString( ) {
		return this.map.toString( );
	}
	
}
