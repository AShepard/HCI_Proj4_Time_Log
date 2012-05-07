package edu.ucsb.cs.cs185.hw4;

public class Duration {
	private String m_from_year, m_from_month, m_from_day;
	private String m_from_hour, m_from_minute;
	private String m_to_year, m_to_month, m_to_day;
	private String m_to_hour, m_to_minute;
	
	public Duration() {
		m_to_year = "";
		m_to_month = "";
		m_to_day = "";
		m_to_hour = "";
		m_to_minute = "";
		
		m_from_year = "";
		m_from_month = "";
		m_from_day = "";
		m_from_hour = "";
		m_from_minute = "";
		
	}
	
	public int setToDate(String year, String month, String day, String hour, String minute) {
		m_to_year = year;
		m_to_month = month;
		m_to_day = day;
		m_to_hour = hour;
		m_to_minute = minute;
		
		return 0;
	}
	
	public int setFromDate(String year, String month, String day, String hour, String minute) {
		m_from_year = year;
		m_from_month = month;
		m_from_day = day;
		m_from_hour = hour;
		m_from_minute = minute;
		
		return 0;
	}
}
