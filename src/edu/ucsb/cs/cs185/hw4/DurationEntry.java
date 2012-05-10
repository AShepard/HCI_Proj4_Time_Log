package edu.ucsb.cs.cs185.hw4;

import java.util.Calendar;

public class DurationEntry {
	private int m_from_year = 2012, m_from_month = 4, m_from_day = 27;
	private int m_from_hour = 23, m_from_minute = 59;
	private int m_to_year = 2012, m_to_month = 4, m_to_day = 27;
	private int m_to_hour = 23, m_to_minute = 59;
	
	private int m_duration_hour = 0, m_duration_minute = 0;
	
	public DurationEntry() {
		
	}
	
	/*
	 * Mutator methods
	 */
	public void setFromYear(int year) {
		m_from_year = year;
	}
	
	public void setFromMonth(int month) {
		m_from_month = month;
	}
	
	public void setFromDay(int day) {
		m_from_day = day;
	}
	
	public void setFromHour(int hour) {
		m_from_hour = hour;
	}
	
	public void setFromMinute(int minute) {
		m_from_minute = minute;
	}
	
	public void setDurationHour(int hour) {
		m_duration_hour = hour;
	}
	public void setDurationMinute(int minute){
		m_duration_minute = minute;
	}
	/*
	 * Accessor methods
	 */
	public int getFromYear() {
		return m_from_year;
	}
	
	public int getFromMonth() {
		return m_from_month;
	}
	
	public int getFromDay() {
		return m_from_day;
	}
	
	public int getFromHour() {
		return m_from_hour;
	}
	
	public int getFromMinute() {
		return m_from_minute;
	}
	/* TODO: REMOVE
	public int getToYear() {
		return m_to_year;
	}
	
	public int getToMonth() {
		return m_to_month;
	}
	
	public int getToDay() {
		return m_to_day;
	}
	
	public int getToHour() {
		return m_to_hour;
	}
	
	public int getToMinute() {
		return m_to_minute;
	}
	*/
	
	public String getDurationString() {
		String duration = "";
    	
		String from_date = dateTimeToString(m_from_year, m_from_month, m_from_day, m_from_hour, m_from_minute);
		convertToDate();
		String to_date = dateTimeToString(m_to_year, m_to_month, m_to_day, m_to_hour, m_to_minute);
    	duration = from_date + " - " + to_date;
    	
    	return duration;
	}
	
	private void convertToDate() {
		int duration = m_duration_hour * 60 + m_duration_minute;
		Calendar calendar = Calendar.getInstance();
    	calendar.set(m_from_year, m_from_month - 1, m_from_day, m_from_hour, m_from_minute);
    	calendar.add(Calendar.MINUTE, duration);
    	
    	m_to_day = calendar.get(Calendar.DATE);
    	m_to_month = calendar.get(Calendar.MONTH) + 1;
    	m_to_year = calendar.get(Calendar.YEAR);
    	m_to_hour = calendar.get(Calendar.HOUR_OF_DAY);
    	m_to_minute = calendar.get(Calendar.MINUTE);
	}
	
	private String dateTimeToString(int year, int month, int day, int hour, int minute)
    {
    	return (month + "/" + day + "/" + year + ", " + hour + ":" + String.format("%02d", minute));
    }
}
