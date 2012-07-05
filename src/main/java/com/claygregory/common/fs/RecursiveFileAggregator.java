package com.claygregory.common.fs;

import java.io.File;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Stack;

/**
 * Returns all files contained within the provided directory. This descendant through directories, for a
 * shallow aggregator, see {@link DirectoryFileAggregator}
 * 
 * @author Clay Gregory
 *
 */
public class RecursiveFileAggregator extends AbstractFileAggregator {
	
	private final File root;
	
	public RecursiveFileAggregator( String root ) {
		this( new File ( root ) );
	}
	
	public RecursiveFileAggregator( File root ) {
		this.root = root;
	}

	@Override
	public Iterable<File> all( ) {
		return new Iterable<File>( ) {
			@Override
			public Iterator<File> iterator( ) {
				
				final Stack<File> directories = new Stack<File>( );
				directories.add( root );
				
				final Stack<File> files = new Stack<File>( );
				
				return new Iterator<File>( ) {
					
					@Override
					public boolean hasNext( ) {
						return !files.isEmpty( ) || !directories.isEmpty( );
					}

					@Override
					public File next( ) {
						if ( !files.isEmpty( ) ) {
							return files.pop( );
						} else if ( !directories.isEmpty( ) ) {
							File dir = directories.pop( );
							for ( File f : dir.listFiles( ) )
								if ( f.isFile( ) )
									files.add( f );
								else
									directories.add( f );
									
							return dir;
						} else {
							 throw new NoSuchElementException( ); 
						}
					}

					@Override
					public void remove( ) {
						throw new UnsupportedOperationException( );
					}
				};
			}
		};
	}
}
