package ac.th.kmitl.it.se.stamper;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;


public class LoginActivity extends Activity {

	private EditText editText_username, editText_password;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);

		// set up form
		editText_username = (EditText) findViewById(R.id.editText_username);
		editText_password = (EditText) findViewById(R.id.editText_password);

		// set button
		findViewById(R.id.button_login).setOnClickListener(
				new OnClickListener() {

					@Override
					public void onClick(View v) {
						// Validate the log in data
						boolean validationError = false;
						if (isEmpty(editText_username)) {
							validationError = true;
						}
						if (isEmpty(editText_password)) {
							validationError = false;
						}
						if (validationError) {
							return;
						}
						
						// login
						ParseUser.logInInBackground(editText_username.getText()
								.toString(), editText_password.getText()
								.toString(), new LogInCallback() {

							@Override
							public void done(ParseUser arg0, ParseException e) {
								 if (e != null) {
			                            // Show the error message
			                            //Toast.makeText(LoginActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
			                        } else {
			                           // success						
			                        	Intent intent = new Intent(getBaseContext(), ShopActivity.class);
										intent.putExtra("shopName", ParseUser.getCurrentUser().get("shopName").toString());
										//intent.putExtra("branch", ParseUser.getCurrentUser().get("branch").toString());
			                        	startActivity(intent);
			                        	finish();
			                        }
								
								
							}
						});
					}
				});
	}
	
	private boolean isEmpty(EditText etText) {
		if (etText.getText().toString().trim().length() > 0) {
			return false;
		} else {
			return true;
		}
	}
}
