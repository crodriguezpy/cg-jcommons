package com.claygregory.common.core;

/**
 * A function providing the distance between two elements.
 * 
 * @author Clay Gregory
 *
 * @param <T> the element type supported
 */
public interface DistanceFunction<T> {

	public double distance( T o1, T o2 );
	
}
