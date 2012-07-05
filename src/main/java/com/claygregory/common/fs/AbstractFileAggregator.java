package com.claygregory.common.fs;

import java.io.File;

import com.claygregory.common.collection.Iterables;
import com.claygregory.common.core.Predicate;

/**
 * 
 * Provides directories and files filtering, allowing implementations to focus on aggregating for {@link #all()}
 * 
 * @author Clay Gregory
 *
 */
public abstract class AbstractFileAggregator implements FileAggregator {
	
	@Override
	public abstract Iterable<File> all( );

	@Override
	public Iterable<File> directories( ) {
		return Iterables.filter( this.all( ), new Predicate<File>( ) {
			@Override
			public Boolean apply( File input ) {
				return input.isDirectory( );
			}
		} );
	}

	@Override
	public Iterable<File> files( ) {
		return Iterables.filter( this.all( ), new Predicate<File>( ) {
			@Override
			public Boolean apply( File input ) {
				return input.isFile( );
			}
		} );
	}
}
