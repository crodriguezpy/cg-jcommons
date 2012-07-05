package com.claygregory.common.fs;

import java.io.File;

/**
 * Provides an Iterable of Files as selected by the implementation
 * 
 * @author Clay Gregory
 *
 */
public interface FileAggregator {
	
	public Iterable<File> all( );

	public Iterable<File> files( );
	
	public Iterable<File> directories( );
	
}
