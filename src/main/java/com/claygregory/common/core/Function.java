package com.claygregory.common.core;

/**
 * 
 * For a given input, return an output, preferably without side effects.
 * 
 * @author Clay Gregory
 *
 * @param <I> Input type
 * @param <O> Output type
 */
public interface Function<I,O> {

	public O apply( I input );
}
