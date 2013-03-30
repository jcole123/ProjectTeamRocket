package edu.gatech.oad.rocket.findmythings;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.EditText;

public class MyAccount extends Activity {
	
	/**
	 * References to the layout 
	 */
	private EditText mName, mEmail, mPhone, mAddy;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_my_account);
		
		
		mName = (EditText) findViewById(R.id.personname);
		mEmail = (EditText) findViewById(R.id.emailview);
		mPhone = (EditText) findViewById(R.id.phoneview);
		mAddy = (EditText) findViewById(R.id.addressview);
		
		if(Login.currUser!=null) {
			// Display user info
			mEmail.setText(Login.currUser.getUser());
			if(Login.currUser.getName()!=null)
				mName.setText(Login.currUser.getName());
			if(Login.currUser.getAddress()!=null)
				mAddy.setText(Login.currUser.getAddress());
			if(Login.currUser.getPhone()!=null)
				mPhone.setText(Login.currUser.getPhone());
			
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.my_account, menu);
		return true;
	}

}