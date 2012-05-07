package edu.ucsb.cs.cs185.hw4;

public class Duration {
	private String m_to_duration;
	private String m_from_duration;
	
	public Duration() {
		m_to_duration = "";
		m_from_duration = "";
	}
	
	public int setToDate(String year, String month, String day, String hour, String minute) {
		
		m_to_duration = month + "/" + day + "/" + year + ", " + 
						hour + ":" + minute;
		return 0;
	}
	
	public int setFromDate(String year, String month, String day, String hour, String minute) {
		
		m_from_duration = month + "/" + day + "/" + year + ", " + 
				hour + ":" + minute;
		
		return 0;
	}
	
	public String getString() {
		String complete_duration = "";
		
		complete_duration = m_from_duration + " - " + m_to_duration;
		
		return complete_duration;
	}
}
