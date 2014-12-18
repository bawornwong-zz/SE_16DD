package ac.th.kmitl.it.se.stamper;

import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.Toast;

public class CollectQRActivity extends Activity {

	String recievecompany;
	String recieveobject_id;
	String recievepoint;
	String shop;
	int point;
	private ImageView img_point;
	private static final String MY_PREFS = "my_prefs";

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.starbucksqr_layout);

		Intent b = getIntent();
		recievecompany = b.getExtras().getString("company");
		recieveobject_id = b.getExtras().getString("object_id");
		recievepoint = b.getExtras().getString("point");
		shop = recievecompany + "Shop";

		ParseQuery<ParseObject> query = ParseQuery.getQuery(shop);
		query.getInBackground(recieveobject_id, new GetCallback<ParseObject>() {
			public void done(ParseObject object, ParseException e) {
				if (e == null) {
					Log.d("******", "+++++++++++++++");
					boolean checkpoint = object.getBoolean("checkPoint");
					if (checkpoint == false) {
						point = object.getInt("point");
						// save session
						final SharedPreferences shared = getSharedPreferences(
								MY_PREFS, Context.MODE_PRIVATE);
						int a = shared.getInt("SavePoint", 0);
						a = a + point;

						Editor editor = shared.edit();
						editor.putInt("SavePoint", a);
						editor.commit();
						
						object.put("checkPoint", true);
						object.saveInBackground();
						Extent();

					} else {
						Toast.makeText(getApplicationContext(),
								"QrCode ไม่ถูกต้อง",
								Toast.LENGTH_SHORT).show();
					}

				} else {
					// something went wrong
					Log.d("******", "---------------");
				}
			}
		});
	}

	// create menu touch bar
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater myMenuInflater = getMenuInflater();
		myMenuInflater.inflate(R.menu.mymenu, menu);
		return super.onCreateOptionsMenu(menu);
	}

	public void Extent() {
		String company = "none";
		String object_id = "none";
		String point = "-1";

		Intent in = new Intent(getApplicationContext(), StarbucksActivity.class);

		Bundle data = new Bundle();
		data.putString("company", company);
		data.putString("object_id", object_id);
		data.putString("point", point);
		in.putExtras(data);
		startActivity(in);
		finish();
	}

}
