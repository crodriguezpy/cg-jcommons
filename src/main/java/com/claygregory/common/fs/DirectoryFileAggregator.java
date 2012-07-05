package com.claygregory.common.fs;

import java.io.File;
import java.util.Arrays;

/**
 * 
 * Aggregates all files at the root of the given directory. For deep file aggregation, see {@link RecursiveFileAggregator}
 * 
 * @author Clay Gregory
 *
 */
public class DirectoryFileAggregator extends AbstractFileAggregator {
	
	private File root;
	
	public DirectoryFileAggregator( String root ) {
		this( new File ( root ) );
	}
	
	public DirectoryFileAggregator( File root ) {
		this.root = root;
	}

	@Override
	public Iterable<File> all( ) {
		return Arrays.asList( this.root.listFiles( ) );
	}
}
