package edu.ucsb.cs.cs185.hw4;

import java.util.Vector;

import android.app.Activity;
import android.app.ListActivity;
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
        inflater.inflate(R.menu.actionbar, menu);
        return true;
    }
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.new_entry:
                // app icon in action bar clicked; go home
                Intent intent = new Intent(this, NewEntry.class);
               // intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                break;
            default:
                return false;
        }
        
        return true;
    }
    
    private void updateList() {
    	setListAdapter(new ArrayAdapter<String>(this, R.layout.home, m_durations));
    }
}
