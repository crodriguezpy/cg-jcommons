package com.claygregory.common.data;

import java.io.Serializable;

import com.claygregory.common.util.ObjectUtil;

/**
 * 
 * @author Clay Gregory
 *
 * @param <T> ranged type
 */
public class Range<T extends Comparable<T>> implements Serializable {

	public static final String SEPARATOR = "-";

	private static final long serialVersionUID = 5860425093613849606L;

	private T lowerBound;

	private T upperBound;

	public Range( T lowerBound, T upperBound ) {
		if ( lowerBound == null && upperBound == null )
			throw new IllegalArgumentException( );

		if ( lowerBound != null && upperBound != null && lowerBound.compareTo( upperBound ) > 0 )
			throw new IllegalArgumentException( );

		this.lowerBound = lowerBound;
		this.upperBound = upperBound;
	}

	public T getLowerBound( ) {
		return this.lowerBound;
	}

	public T getUpperBound( ) {
		return this.upperBound;
	}

	public boolean inRangeInclusive( T t ) {

		if ( this.getUpperBound( ) != null && t.compareTo( this.getUpperBound( ) ) > 0 )
			return false;

		if ( this.getLowerBound( ) != null && t.compareTo( this.getLowerBound( ) ) < 0 )
			return false;

		return true;
	}

	public boolean isUnbounded( ) {
		return ( this.lowerBound == null || this.upperBound == null );
	}

	public boolean overlaps( Range<T> r ) {
		return ( r.getUpperBound( ) != null && this.inRangeInclusive( r.getUpperBound( ) ) || r.getLowerBound( ) != null
				&& this.inRangeInclusive( r.getLowerBound( ) ) );
	}

	@Override
	public int hashCode( ) {
		int s = 0;
		if ( this.getLowerBound( ) != null )
			s += Math.abs( this.getLowerBound( ).hashCode( ) );
		if ( this.getUpperBound( ) != null )
			s -= Math.abs( this.getUpperBound( ).hashCode( ) );

		return s;
	}

	@Override
	public boolean equals( Object o ) {

		if ( o == null )
			return false;

		if ( o.getClass( ) != this.getClass( ) )
			return false;

		@SuppressWarnings( "unchecked" )
		Range<T> oRange = (com.claygregory.common.data.Range<T> ) o;
		return
			( this.getLowerBound( ) == null && oRange.getLowerBound( ) == null ||
			  ObjectUtil.nullsafeEquals( oRange.getLowerBound( ), this.getLowerBound( ) ) ) &&
			( this.getUpperBound( ) == null && oRange.getUpperBound( ) == null ||
			  ObjectUtil.nullsafeEquals( this.getUpperBound( ), oRange.getUpperBound( ) ) );
	}
	
	@Override
	public String toString( ) {
		if ( this.lowerBound != null && this.lowerBound.equals( this.upperBound ) )
			return this.lowerBound.toString( );
		if ( this.lowerBound != null && this.upperBound != null ) {
			return this.lowerBound + " " + SEPARATOR + " " + this.upperBound;
		} else if ( this.lowerBound != null ) {
			return this.lowerBound + " " + SEPARATOR;
		} else if ( this.upperBound != null ) {
			return SEPARATOR + " " + this.upperBound;
		}

		return "";
	}
}