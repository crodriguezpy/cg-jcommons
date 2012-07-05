package com.claygregory.common.data;

/**
 * 
 * 
 * @author Clay Gregory
 *
 */
public class Duration implements Comparable<Duration> {

	public static final int MS_IN_SECONDS = 1000;

	public static final int MS_IN_MINUTES = 60 * MS_IN_SECONDS;

	public static final int MS_IN_HOURS = 60 * MS_IN_MINUTES;
	
	public static final int MS_IN_DAYS = 24 * MS_IN_HOURS;

	private long duration;

	public Duration( int seconds ) {
		this.duration = seconds * MS_IN_SECONDS;
	}

	public Duration( long ms ) {
		this.duration = ms;
	}

	public Duration( int minutes, int seconds ) {
		this.duration += minutes * MS_IN_MINUTES;
		this.duration += seconds * MS_IN_SECONDS;
	}

	public Duration( int hours, int minutes, int seconds ) {
		this.duration += hours * MS_IN_HOURS;
		this.duration += minutes * MS_IN_MINUTES;
		this.duration += seconds * MS_IN_SECONDS;
	}

	public double asDays( ) {
		return this.duration / ( double ) MS_IN_DAYS;
	}

	public double asHours( ) {
		return this.duration / ( double ) MS_IN_HOURS;
	}

	public long asMilliseconds( ) {
		return this.duration;
	}

	public double asMinutes( ) {
		return this.duration / ( double ) MS_IN_MINUTES;
	}

	public double asSeconds( ) {
		return this.duration / ( double ) MS_IN_SECONDS;
	}
	
	public Duration add( Duration duration ) {
		return new Duration( this.asMilliseconds( ) + duration.asMilliseconds( ) );
	}
	
	public Duration subtract( Duration duration ) {
		return new Duration( this.asMilliseconds( ) - duration.asMilliseconds( ) );
	}

	@Override
	public String toString( ) {
		return String.format( "%d:%02d:%02d",
				( int ) Math.floor( this.asHours( ) ),
				( int ) Math.floor( this.asMinutes( ) ) % 60,
				( int ) Math.floor( this.asSeconds( ) ) % 60 );
	}

	@Override
	public int compareTo( Duration o ) {
		return ( int ) Math.signum( this.duration - o.duration );
	}
}