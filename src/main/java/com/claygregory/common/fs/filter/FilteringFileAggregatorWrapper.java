package com.claygregory.common.fs.filter;

import java.io.File;
import java.io.FilenameFilter;

import com.claygregory.common.collection.Iterables;
import com.claygregory.common.core.Predicate;
import com.claygregory.common.fs.AbstractFileAggregator;
import com.claygregory.common.fs.FileAggregator;

/**
 * Wrap a FileAggregator for filtering files based on a Predicate or FilenameFilter
 * 
 * @author Clay Gregory
 *
 */
public class FilteringFileAggregatorWrapper {
	
	protected static class FilteringFileAggregator extends AbstractFileAggregator {
	
		private final FileAggregator baseAggregator;
		
		private final Predicate<File> filterPredicate;
		
		public FilteringFileAggregator( FileAggregator baseAggregator, Predicate<File> filterPredicate ) {
			this.baseAggregator = baseAggregator;
			this.filterPredicate = filterPredicate;
		}
	
		@Override
		public Iterable<File> all( ) {
			return Iterables.filter( this.baseAggregator.all( ), filterPredicate );
		}
	}
	
	private Predicate<File> filterPredicate;
	
	public FilteringFileAggregatorWrapper( Predicate<File> filterPredicate ) {
		this.filterPredicate = filterPredicate;
	}
	
	public FilteringFileAggregatorWrapper( final FilenameFilter filter ) {
		this.filterPredicate = new Predicate<File>( ) {
			@Override
			public Boolean apply( File input ) {
				return filter.accept( input.getParentFile( ), input.getName( ) );
			}
		};
	}
	
	public FileAggregator wrap( FileAggregator baseAggregator ) {
		return new FilteringFileAggregator( baseAggregator, this.filterPredicate );
	}
}
