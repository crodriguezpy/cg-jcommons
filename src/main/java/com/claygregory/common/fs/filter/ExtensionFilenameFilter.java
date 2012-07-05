package com.claygregory.common.fs.filter;

import java.io.File;
import java.io.FilenameFilter;

/**
 * FilenameFilter to match based on file extension.
 * 
 * @author Clay Gregory
 * @see java.io.FilenameFilter
 */
public class ExtensionFilenameFilter implements FilenameFilter {

	private String[ ] allowedExtensions;

	public ExtensionFilenameFilter( String... extensions ) {
		this.allowedExtensions = extensions;

		for ( int i = 0; i < this.allowedExtensions.length; i++ ) {
			this.allowedExtensions[ i ] = "." + this.allowedExtensions[ i ];
		}
	}

	public boolean accept( File dir, String name ) {
		for ( String ext : this.allowedExtensions ) {
			if ( name.endsWith( ext ) )
				return true;
		}

		return false;
	}

}
