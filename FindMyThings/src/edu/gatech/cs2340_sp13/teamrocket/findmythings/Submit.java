package edu.gatech.cs2340_sp13.teamrocket.findmythings;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

public class Submit extends Activity {
	
	//UI references
	private EditText description;
	private EditText location;
	private EditText reward;
	private EditText iName;
	
	//Hold strings from the UI
	private String desc, loc, name;
	private int rward;
	
	private Controller control = new Controller();
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
				
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_submit);
		
		
		//References the layout in activity_submit
		iName = (EditText) findViewById(R.id.name);
		description = (EditText) findViewById(R.id.description);
		location = (EditText) findViewById(R.id.locationtext);
		reward = (EditText) findViewById(R.id.rewardtext);
				
		
		// Hide the Up button in the action bar.
		setupActionBar();
		
		setTitle("Submit an Item");
	}
		
	/**
	 * Set up the {@link android.app.ActionBar}.
	 */
	private void setupActionBar() {

		getActionBar().setDisplayHomeAsUpEnabled(false);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.submit, menu);
		return true;
	}
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event)  {
		//Tells Activity what to do when back key is pressed
	    if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
	    	return false;
	    }

	    return super.onKeyDown(keyCode, event);
	}
	
	/**
	 * Goes to ItemList Activity
	 */
	public void toItemList() {
		Intent goToNextActivity = new Intent(getApplicationContext(), ItemListActivity.class);
		finish();
		startActivity(goToNextActivity);
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
	 	case R.id.submit_ok:
	 		desc = description.getText().toString();
	 		loc = location.getText().toString();
	 		rward = reward.getText().toString()==null? 0:Integer.parseInt(reward.getText().toString());
	 		name = iName.getText().toString();
	 		
	 		Item temp = new Item(name,rward);
	 		temp.setDescription(desc);
	 		temp.setLoc(loc);
	 		//TODO: Get type and category from SubmitFrag
	 		control.addItem(temp);
	 		toItemList();
	 		
	 		return true;
	 	case R.id.submit_cancel:
	 		toItemList();
	 		return true;
		case android.R.id.home:
			// This ID represents the Home or Up button. In the case of this
			// activity, the Up button is shown. Use NavUtils to allow users
			// to navigate up one level in the application structure. For
			// more details, see the Navigation pattern on Android Design:
			//
			// http://developer.android.com/design/patterns/navigation.html#up-vs-back
			//
			
			return true;	
		}	
			
			return super.onOptionsItemSelected(item);
	}

}
