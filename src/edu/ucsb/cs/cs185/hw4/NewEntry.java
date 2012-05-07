package edu.ucsb.cs.cs185.hw4;

import java.util.Calendar;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.NumberPicker;

public class NewEntry extends Activity
{
	
	private int m_from_Year = 2012, m_from_Month = 4, m_from_Day = 27;
	private int m_from_Hour = 23, m_from_Minute = 59;
	private int m_to_Year = 2012, m_to_Month = 4, m_to_Day = 27;
	private int m_to_Hour = 23, m_to_Minute = 59;
	
	private static final String NEW_ENTRY = "NEW ENTRY";
	
	private DatePicker datePicker;
	private TimePicker timePicker;
	private NumberPicker hourPicker;
	private NumberPicker minutePicker;
	
	private Button b_set;
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.newentry);
        
        
        datePicker = (DatePicker) findViewById(R.id.datePicker);
        datePicker.init(m_from_Year, m_from_Month, m_from_Day, null);
		datePicker.setMinDate(1072944000000L); // 1/1/2004 00:00 in epochs
		datePicker.setMaxDate(1335553140000L); // 4/27/2012 11:59 in epochs
		
        timePicker = (TimePicker) findViewById(R.id.timePicker);
        timePicker.setCurrentHour(23);
        timePicker.setCurrentMinute(59);
        
        hourPicker = (NumberPicker) findViewById(R.id.hourPicker);
        hourPicker.setMinValue(0);
        hourPicker.setMaxValue(47);
        hourPicker.setValue(0); 

        minutePicker = (NumberPicker) findViewById(R.id.minutePicker);
        minutePicker.setMinValue(0);
        minutePicker.setMaxValue(59);
        minutePicker.setValue(0);
        
        b_set = (Button) findViewById(R.id.buttonSet);
        b_set.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				buttonSetOnClick(v);
			}
		});
        
    }
    
    String DateTimeToString(int year, int month, int day, int hour, int minute)
    {
    	return (month + "/" + day + "/" + year + ", " + hour + ":" + String.format("%02d", minute));
    }
    
    String DurationToString(String from_date, String to_date) {
    	String duration = "";
    	
    	duration = from_date + " - " + to_date;
    	
    	return duration;
    }
    
    void addDuration()
    {
    	String to_date, from_date, duration;
    	to_date= DateTimeToString(m_from_Year, m_from_Month, m_from_Day, m_from_Hour, m_from_Minute);
    	from_date =	DateTimeToString(m_to_Year, m_to_Month, m_to_Day, m_to_Hour, m_to_Minute);
    	
    	duration = DurationToString(from_date, to_date);
    	
    	finishEntry(duration);
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.actionbarnewentry, menu);
        return true;
    }
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.home:
            	finishEntry("");
                break;
            default:
                return false;
        }
        
        return true;
    }
    
    public void buttonSetOnClick(View view)
    {
    	m_from_Day = datePicker.getDayOfMonth();
    	m_from_Month = datePicker.getMonth() + 1;
    	m_from_Year = datePicker.getYear();
    	m_from_Hour = timePicker.getCurrentHour();
    	m_from_Minute = timePicker.getCurrentMinute();

    	int duration = hourPicker.getValue() * 60 + minutePicker.getValue();
    	
    	Calendar calendar = Calendar.getInstance();
    	calendar.set(m_from_Year, m_from_Month - 1, m_from_Day, m_from_Hour, m_from_Minute);
    	calendar.add(Calendar.MINUTE, duration);
    	
    	m_to_Day = calendar.get(Calendar.DATE);
    	m_to_Month = calendar.get(Calendar.MONTH) + 1;
    	m_to_Year = calendar.get(Calendar.YEAR);
    	m_to_Hour = calendar.get(Calendar.HOUR_OF_DAY);
    	m_to_Minute = calendar.get(Calendar.MINUTE);
    	
    	addDuration();
    }
    
    private void finishEntry(String new_entry) {
    	Intent intent = this.getIntent();
    	
    	if(new_entry != "") {
    		intent.putExtra(NEW_ENTRY, new_entry);
    		this.setResult(RESULT_OK, intent);
    	} else {
    		this.setResult(RESULT_CANCELED, intent);
    	}
    	finish();
    }
    
}