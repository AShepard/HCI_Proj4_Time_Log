package edu.ucsb.cs.cs185.hw4;

import java.util.Vector;

import android.app.Activity;
import android.os.Bundle;

/*
 * This lists durations
 * stored as vector of durations
 */
public class Home extends Activity {
	private Vector<Duration> m_durations;
	
	@Override
    public void onCreate(Bundle savedInstanceState)
    {
		super.onCreate(savedInstanceState);
        setContentView(R.layout.home);
    }
}
