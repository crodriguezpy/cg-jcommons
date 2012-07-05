package com.claygregory.common.util;

import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public final class DateUtil {

	public final static Calendar asCalendar( Date date ) {	
		Calendar cal = Calendar.getInstance( );
		cal.setTime( date );
		
		return cal;
	}
	
	public final static List<Date> dateList( Date start, Date end ) {
		return dateList( start, end, Calendar.DATE );
	}
	
	public final static List<Date> dateList( Date start, Date end, int stepField ) {
		return dateList( start, end, stepField, 1 );
	}
	
	public final static List<Date> dateList( Date start, Date end, int stepField, int stepAmount ) {
		return dateList( start, end, stepField, stepAmount, true );
	}
	
	public final static List<Date> dateList( Date start, Date end, int stepField, int stepAmount, boolean roundToField ) {
		
		Calendar baseCal = asCalendar( roundToField ? floor( start, stepField ) : start );
		Calendar endCal = asCalendar( roundToField ? ceil( end, stepField ) : end );
		
		List<Date> list = new LinkedList<Date>( );
		while ( baseCal.before( endCal ) ) {
			list.add( baseCal.getTime( ) );
			baseCal.add( stepField, stepAmount );
		}
		
		return list;
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
