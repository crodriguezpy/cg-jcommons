package com.claygregory.common.util;

import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;

import com.claygregory.common.collection.UnmodifiableIterator;

/**
 * 
 * Misc utility function for working with {@link java.util.Date} objects
 * 
 * @author Clay Gregory
 *
 */
public final class DateUtil {

	/**
	 * Get a {@link java.util.Calendar} representation of provided Date
	 * @param date the date to Calendar-ize
	 * @return a new Calendar instance set to Date values
	 */
	public final static Calendar asCalendar( Date date ) {	
		Calendar cal = Calendar.getInstance( );
		cal.setTime( date );
		
		return cal;
	}
	
	/**
	 * Allows for iterating through a range of dates, stepping 1 day at a time. Note:
	 * this auto selects date-rounding
	 * 
	 * @param start the start date, inclusive
	 * @param end the end date, inclusive
	 * @return a provider for date iteration
	 */
	public final static Iterable<Date> dateList( Date start, Date end ) {
		return dateList( start, end, Calendar.DATE );
	}
	
	/**
	 * Allows for iterating through a range of dates, stepping one provided unit at a time. Note:
	 * this auto selects date-rounding
	 * 
	 * @param start the start date, inclusive
	 * @param end the end date, inclusive
	 * @param stepField the {@link java.util.Calendar} field constant to step by
	 * @return a provider for date iteration
	 */
	public final static Iterable<Date> dateList( Date start, Date end, int stepField ) {
		return dateList( start, end, stepField, 1 );
	}
	
	/**
	 * Allows for iterating through a range of dates, stepping a custom number of provided unit at a time. Note:
	 * this auto selects date-rounding
	 * 
	 * 
	 * @param start the start date, inclusive
	 * @param end the end date, inclusive
	 * @param stepField the {@link java.util.Calendar} field constant to step by
	 * @param stepAmount the number of stepField units to increment at each step
	 * @return a provider for date iteration
	 */
	public final static Iterable<Date> dateList( Date start, Date end, int stepField, int stepAmount ) {
		return dateList( start, end, stepField, stepAmount, true );
	}
	
	/**
	 * Allows for iterating through a range of dates, stepping a custom number of provided unit at a time. If
	 * roundToField is true, the lower and upper bounds are rounded via {@link #floor(Date, int)} and {@link #ceil(Date, int)}
	 * to the selected stepField, respectively.
	 * 
	 * @param start the start date, inclusive
	 * @param end the end date, inclusive
	 * @param stepField the {@link java.util.Calendar} field constant to step by
	 * @param stepAmount the number of stepField units to increment at each step
	 * @param roundToField should the start and end dates be interpreted to nearest full stepField unit?
	 * @return a provider for date iteration
	 */
	public final static Iterable<Date> dateList( final Date start, final Date end, final int stepField, final int stepAmount, final boolean roundToField ) {
		
		return new Iterable<Date>( ) {

			@Override
			public Iterator<Date> iterator( ) {
				
				final Calendar baseCal = asCalendar( roundToField ? floor( start, stepField ) : start );
				final Calendar endCal = asCalendar( roundToField ? ceil( end, stepField ) : end );
				
				return new UnmodifiableIterator<Date>( ) {

					@Override
					public boolean hasNext( ) {
						return baseCal.before( endCal );
					}

					@Override
					public Date next( ) {
						Date d = baseCal.getTime( );
						baseCal.add( stepField, stepAmount );
						return d;
					}
				};
			}
			
		};
	}
	
	public final static Date addYear( Date date, int amount ) {
		return addField( date, Calendar.YEAR, amount );
	}
	
	public final static int getYear( Date date ) {
		return getField( date, Calendar.YEAR );
	}
	
	public final static Date setYear( Date date, int year ) {
		return setField( date, Calendar.YEAR, year );
	}
	
	public final static Date addMonth( Date date, int amount ) {
		return addField( date, Calendar.MONTH, amount );
	}
	
	public final static int getMonth( Date date ) {
		return getField( date, Calendar.MONTH );
	}
	
	public final static Date setMonth( Date date, int month ) {
		return setField( date, Calendar.MONTH, month );
	}
	
	public final static Date addDay( Date date, int amount ) {
		return addField( date, Calendar.DATE, amount );
	}
	
	public final static int getDate( Date date ) {
		return getField( date, Calendar.DATE );
	}
	
	public final static Date setDate( Date date, int dateValue ) {
		return setField( date, Calendar.DATE, dateValue );
	}
	
	public final static Date addHour( Date date, int amount ) {
		return addField( date, Calendar.HOUR, amount );
	}
	
	public final static int getHour( Date date ) {
		return getField( date, Calendar.HOUR );
	}
	
	public final static Date setHour( Date date, int hour ) {
		return setField( date, Calendar.HOUR, hour );
	}
	
	public final static Date addMinute( Date date, int amount ) {
		return addField( date, Calendar.MINUTE, amount );
	}
	
	public final static int getMinute( Date date ) {
		return getField( date, Calendar.MINUTE );
	}
	
	public final static Date setMinute( Date date, int minute ) {
		return setField( date, Calendar.MINUTE, minute );
	}
	
	public final static Date addSecond( Date date, int amount ) {
		return addField( date, Calendar.SECOND, amount );
	}
	
	public final static int getSecond( Date date ) {
		return getField( date, Calendar.SECOND );
	}
	
	public final static Date setSecond( Date date, int second ) {
		return setField( date, Calendar.SECOND, second );
	}
	
	public final static Date addMillisecond( Date date, int amount ) {
		return addField( date, Calendar.MILLISECOND, amount );
	}
	
	public final static int getMillisecond( Date date ) {
		return getField( date, Calendar.MILLISECOND );
	}
	
	public final static Date setMillisecond( Date date, int millisecond ) {
		return setField( date, Calendar.MILLISECOND, millisecond );
	}
	
	/**
	 * Creates new Date at next full field step to given Date. All fields
	 * of higher resolution than selected field are cleared.
	 * 
	 * @param date the Date to ceiling
	 * @param field the Calendar field resolution at which we want to round
	 * @return a new Date 
	 */
	public final static Date ceil( Date date, int field ) {	
		Calendar cal = asCalendar( date );
		boolean increment = false;
		
		for ( int i = Calendar.MILLISECOND; i > field; i-- )
			if ( i < Calendar.DAY_OF_MONTH || i > Calendar.AM_PM )
				increment |= cal.get( i ) > 0;
		
		for ( int i = Calendar.MILLISECOND; i > field; i-- )
			cal.clear( i );
		
		if ( increment )
			cal.add( field, 1 );
		
		return cal.getTime( );
	}
	
	/**
	 * Creates new Date at lowest full field step to given Date. All fields
	 * of higher resolution than selected field are cleared.
	 * 
	 * @param date the Date to floor
	 * @param field the Calendar field resolution at which we want to round
	 * @return a new Date 
	 */
	public final static Date floor( Date date, int field ) {	
		Calendar cal = asCalendar( date );
		for ( int i = Calendar.MILLISECOND; i > field; i-- )
			cal.clear( i );
		
		return cal.getTime( );
	}
	
	private final static Date addField( Date date, int field, int amount ) {
		Calendar cal = asCalendar( date );
		cal.add( field, amount );
		return cal.getTime( );
	}
	
	private final static int getField( Date date, int field ) {
		Calendar cal = asCalendar( date );
		return cal.get( field );
	}
	
	private final static Date setField( Date date, int field, int value ) {
		Calendar cal = asCalendar( date );
		cal.set( field, value );
		return cal.getTime( );
	}
}
