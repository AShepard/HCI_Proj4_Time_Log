package edu.ucsb.cs.cs185.hw4;

import java.util.Calendar;
import java.util.Vector;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

/*
 * This lists durations
 * stored as vector of string durations
 */
public class Home extends ListActivity  {
	//TODO: This is a bad way to do this
	private static final int NEW_ENTRY_KEY = 999999;
	
	private static final String FROM_YEAR = "FROM_YEAR";
	private static final String FROM_MONTH = "FROM_MONTH";
	private static final String FROM_DAY = "FROM_DAY";
	private static final String FROM_HOUR= "FROM_HOUR";
	private static final String FROM_MINUTE = "FROM_MINUTE";
	
	private static final String DURATION_MINUTE = "DURATION_MINUTE";
	private static final String DURATION_HOUR = "DURATION_HOUR";
	
	private Vector<String> m_duration_strings;
	private Vector<DurationEntry> m_duration_entries;
	@Override
    public void onCreate(Bundle savedInstanceState)
    {
		super.onCreate(savedInstanceState);
		
		//http://www.devx.com/wireless/Article/40792/1954
		m_duration_entries = (Vector<DurationEntry>)getLastNonConfigurationInstance();
		if (m_duration_entries == null) {
			m_duration_entries = new Vector<DurationEntry>();
			m_duration_strings = new Vector<String>();
		} else {
			int size = m_duration_entries.size();
			m_duration_strings = new Vector<String>(size);
			for(int i=0; i< size; i++) {
				
				m_duration_strings.addElement(m_duration_entries.get(i).getDurationString());
			}
		}
        
		//http://developer.android.com/resources/tutorials/views/hello-listview.html
		ListView lv = getListView();
		lv.setTextFilterEnabled(true);
		lv.setOnItemClickListener(new OnItemClickListener() {
			

			public void onItemClick(AdapterView<?> parent, View view, int position,
					long id) {
				// Edit the duration for the current item
				editTimeEntry(view, position, id);
			}
		  });
		updateList();
    }
	
	//http://www.devx.com/wireless/Article/40792/1954
	@Override
	public Object onRetainNonConfigurationInstance()
	{
		return m_duration_entries;
	}
	
	
	@Override
	public void onConfigurationChanged(Configuration newConfig) {
	  super.onConfigurationChanged(newConfig);
	  updateList();
	}
	
	String StringToDateTime(int year, int month, int day, int hour, int minute)
    {
    	return (month + "/" + day + "/" + year + ", " + hour + ":" + String.format("%02d", minute));
    }
	
	private void editTimeEntry(View view, int position, long id) {
		DurationEntry duration = m_duration_entries.get(position);
		
		/*
		 * place current values into bundle so NewEntry will display current entered fields
		 */
		Intent intent = new Intent(this, NewEntry.class);
		intent.putExtra(FROM_YEAR, duration.getFromYear());
 		intent.putExtra(FROM_MONTH, duration.getFromMonth());
		intent.putExtra(FROM_DAY, duration.getFromDay());
		intent.putExtra(FROM_HOUR, duration.getFromHour());
		intent.putExtra(FROM_MINUTE, duration.getFromMinute());
		
		intent.putExtra(DURATION_HOUR, duration.getDurationHour());
		intent.putExtra(DURATION_MINUTE, duration.getDurationMinute());
		
        startActivityForResult(intent, position);
	}
	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.actionbarhome, menu);
        return true;
    }
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.new_entry:
            	/*
            	 * User can enter new duration
            	 */
                Intent intent = new Intent(this, NewEntry.class);
                startActivityForResult(intent, NEW_ENTRY_KEY);
                break;
            case R.id.settings:
            	/*
            	 * http://www.helloandroid.com/tutorials/how-display-alertdialog-your-android-application
            	 */
            	AlertDialog settings_dialog = new AlertDialog.Builder(Home.this).create();
            	settings_dialog.setMessage("There are no settings yet!");
            	
            	settings_dialog.setButton("OK", new DialogInterface.OnClickListener() {
          	      public void onClick(DialogInterface dialog, int which) {
          	 
          	       //nothing to do
          	 
          	    } });
            	
            	settings_dialog.show();
            	break;
            case R.id.help:
            	/*
            	 * http://www.helloandroid.com/tutorials/how-display-alertdialog-your-android-application
            	 */
            	AlertDialog help_dialog = new AlertDialog.Builder(Home.this).create();
            	help_dialog.setMessage("Name: Aaron Shepard. \nSoftware Version: Unknown. \nExtra Info: This project deserves an A+!");
            	
            	help_dialog.setButton("OK", new DialogInterface.OnClickListener() {
          	      public void onClick(DialogInterface dialog, int which) {
          	 
          	       //nothing to do
          	 
          	    } });
            	
            	help_dialog.show();
            	break;
            default:
                return false;
        }
        
        return true;
    }
    
    @Override
    protected void onActivityResult(int requestCode, int resultCode,
            Intent data) {
    	
    	Bundle extras = data.getExtras();
    	int from_year = -1, from_month= -1, from_day=-1, from_hour=-1, from_minute=-1;
    	int duration_hour=-1, duration_minute=-1;
    	if (resultCode != RESULT_OK) {
    		//SOMETHING BAD HAPPENED!
    		return;
    	} else {
    		from_year = extras.getInt(FROM_YEAR);
        	from_month= extras.getInt(FROM_MONTH);
        	from_day = extras.getInt(FROM_DAY);
        	from_hour = extras.getInt(FROM_HOUR);
        	from_minute = extras.getInt(FROM_MINUTE);
        	
        	duration_hour = extras.getInt(DURATION_HOUR);
        	duration_minute = extras.getInt(DURATION_MINUTE);
        	
        	
    	}
        if (requestCode == NEW_ENTRY_KEY) {
            
        	/*
        	 * Create a duration object
        	 */
        	DurationEntry new_duration = new DurationEntry();
        	new_duration.setFromYear(from_year);
        	new_duration.setFromMonth(from_month);
        	new_duration.setFromDay(from_day);
        	new_duration.setFromHour(from_hour);
        	new_duration.setFromMinute(from_minute);
        	new_duration.setDurationHour(duration_hour);
        	new_duration.setDurationMinute(duration_minute);

        	//pass new duration and then update list
        	updateList(new_duration);
        } else {
        	//edit based on request code
        	DurationEntry duration = m_duration_entries.elementAt(requestCode);
        	duration.setFromYear(from_year);
        	duration.setFromMonth(from_month);
        	duration.setFromDay(from_day);
        	duration.setFromHour(from_hour);
        	duration.setFromMinute(from_minute);
        	duration.setDurationHour(duration_hour);
        	duration.setDurationMinute(duration_minute);
        	
        	//replace old duration entry
        	m_duration_entries.setElementAt(duration, requestCode);
        	//edit duration string
        	m_duration_strings.setElementAt(duration.getDurationString(), requestCode);
        	
        	//update list
        	updateList();
        }
    }
    
    private void updateList(DurationEntry new_duration) {
    	String duration_string = new_duration.getDurationString();
    	
    	m_duration_entries.add(new_duration);
    	m_duration_strings.add(duration_string);
    	
    	updateList();
    }
    
    /*
     * http://developer.android.com/resources/tutorials/views/hello-listview.html
     */
    private void updateList() {
    	setListAdapter(new ArrayAdapter<String>(this, R.layout.home, m_duration_strings));
    }

}
