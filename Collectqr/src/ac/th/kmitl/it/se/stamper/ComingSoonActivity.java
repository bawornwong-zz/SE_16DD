package ac.th.kmitl.it.se.stamper;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.Window;

public class ComingSoonActivity extends Activity{
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.comingsoon_layout);
		
	}
	
	// create menu touch bar
		@Override
		public boolean onCreateOptionsMenu(Menu menu) {
			// TODO Auto-generated method stub

			MenuInflater myMenuInflater = getMenuInflater();
			myMenuInflater.inflate(R.menu.mymenu, menu);
			return super.onCreateOptionsMenu(menu);
		}

}
