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
	
	private TextView tv_text_from;
	private TextView tv_text_to;
	
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
        
        tv_text_from = (TextView) findViewById(R.id.textFrom);
        tv_text_to = (TextView) findViewById(R.id.textTo);
        
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
        
        UpdateDisplay();
    }
    
    String DateTimem_to_String(int year, int month, int day, int hour, int minute)
    {
    	return (month + "-" + day + "-" + year + ", " + hour + ":" + String.format("%02d", minute));
    }
    
    void UpdateDisplay()
    {
    	tv_text_from.setText(getString(R.string.from) + " " +
        	DateTimem_to_String(m_from_Year, m_from_Month, m_from_Day, m_from_Hour, m_from_Minute));
    	tv_text_to.setText(getString(R.string.to) + " " +
        	DateTimem_to_String(m_to_Year, m_to_Month, m_to_Day, m_to_Hour, m_to_Minute));
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.actionbar, menu);
        return true;
    }
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.new_entry:
            	/*
                // app icon in action bar clicked; go home
                Intent intent = new Intent(this, HomeActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_m_to_P);
                startActivity(intent);
                */
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
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
    	
    	UpdateDisplay();
    }
}