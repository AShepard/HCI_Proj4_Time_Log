package edu.ucsb.cs.cs185.hw4;

import java.util.Vector;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.DialogInterface;
import android.content.Intent;
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
	private static final int NEW_ENTRY_KEY = 0;
	private static final String NEW_ENTRY = "NEW ENTRY";
	private Vector<String> m_durations;
	
	@Override
    public void onCreate(Bundle savedInstanceState)
    {
		super.onCreate(savedInstanceState);
        
		if (m_durations == null) {
			m_durations = new Vector<String>();
		}
		
		updateList();
        
		ListView lv = getListView();
		lv.setTextFilterEnabled(true);
		lv.setOnItemClickListener(new OnItemClickListener() {
			

			public void onItemClick(AdapterView<?> parent, View view, int position,
					long id) {
				// TODO edit times on click
				Toast.makeText(getApplicationContext(), "TODO: Make editable",
				          Toast.LENGTH_SHORT).show();
			}
		  });
        
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
    
    protected void onActivityResult(int requestCode, int resultCode,
            Intent data) {
        if (requestCode == NEW_ENTRY_KEY) {
            if (resultCode == RESULT_OK) {
            	Bundle extras = data.getExtras();
            	
            	String new_entry = extras.getString(NEW_ENTRY);
            	
            	updateList(new_entry);
            }
        }
    }
    
    private void updateList(String new_entry) {
    	m_durations.add(new_entry);
    	updateList();
    }
    
    /*
     * http://developer.android.com/resources/tutorials/views/hello-listview.html
     */
    private void updateList() {
    	setListAdapter(new ArrayAdapter<String>(this, R.layout.home, m_durations));
    }
}
