package com.claygregory.common.collection;

import java.util.List;
import java.util.Map;

/**
 * 
 * Map allowing many values per given key.
 * 
 * @author Clay Gregory
 *
 */
public interface MultiMap<K,V> extends Map<K,List<V>> {

	public List<V> put( K key, V... value );
	
}
