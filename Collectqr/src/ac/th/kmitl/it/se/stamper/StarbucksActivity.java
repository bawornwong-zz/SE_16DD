package ac.th.kmitl.it.se.stamper;

import java.util.Calendar;
import java.util.Timer;

import com.parse.*;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.res.Resources.Theme;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.Toast;
import java.util.TimerTask;

public class StarbucksActivity extends Activity {
	String recievecompany;
	String recieveobject_id;
	String recievepoint;
	String shop;
	int point;
	Timer timer;
	TimerTask timerTask;

	private ImageView img_point;
	private ImageView img_1;
	private ImageView img_2;
	private ImageView img_3;
	private ImageView img_4;
	private ImageView img_5;
	private static final String MY_PREFS = "my_prefs";

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.starbucks_layout);

		Intent b = getIntent();
		recievecompany = b.getExtras().getString("company");
		recieveobject_id = b.getExtras().getString("object_id");
		recievepoint = b.getExtras().getString("point");
		shop = recievecompany + "Shop";
		infoPoint();

		if (Integer.parseInt(recievepoint) == -1) {
			final SharedPreferences shared = getSharedPreferences(MY_PREFS,
					Context.MODE_PRIVATE);
			int a = shared.getInt("SavePoint", 0);

			if (a >= 3) {
				ShowPromotion1();
			}
			if (a >= 8) {
				ShowPromotion2();
			}
			if (a >= 12) {
				ShowPromotion3();
			}
			if (a >= 17) {
				ShowPromotion4();
			}
			if (a >= 20) {
				ShowPromotion5();
			}

			CheckImage();

		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater myMenuInflater = getMenuInflater();
		myMenuInflater.inflate(R.menu.mymenu, menu);
		return super.onCreateOptionsMenu(menu);
	}

	public void CheckImage() {
		final SharedPreferences shared = getSharedPreferences(MY_PREFS,
				Context.MODE_PRIVATE);
		int a = shared.getInt("SavePoint", 0);
		img_point = (ImageView) findViewById(R.id.zero_pointbar);
		int setImage[] = { R.drawable.one_pointbar, R.drawable.two_pointbar,
				R.drawable.three_pointbar, R.drawable.four_pointbar,
				R.drawable.five_pointbar, R.drawable.six_pointbar,
				R.drawable.seven_pointbar, R.drawable.eight_pointbar,
				R.drawable.nine_pointbar, R.drawable.ten_pointbar,
				R.drawable.eleven_pointbar, R.drawable.twelve_pointbar,
				R.drawable.thirteen_pointbar, R.drawable.fourteen_pointbar,
				R.drawable.fifteen_pointbar, R.drawable.sixteen_pointbar,
				R.drawable.seventeen_pointbar, R.drawable.eightteen_pointbar,
				R.drawable.nineteen_pointbar, R.drawable.twenty_pointbar };

		if (a == 0) {
			Toast.makeText(getApplicationContext(), "à¸„à¸¸à¸“à¸¢à¸±à¸‡à¹„à¸¡à¹ˆà¸¡à¸µà¹�à¸•à¹‰à¸¡à¹ƒà¸™à¸•à¸­à¸™à¸™à¸µà¹‰",
					Toast.LENGTH_SHORT).show();
		} else {
			img_point.setImageResource(setImage[a-1]);

		}

	}

	public void ShowPromotion1() {
		final SharedPreferences shared = getSharedPreferences(MY_PREFS,
				Context.MODE_PRIVATE);
		final int a1 = shared.getInt("SavePoint", 0);
		final int b1 = a1 + point;
		img_1 = (ImageView) findViewById(R.id.image1);
		img_1.setImageResource(R.drawable.tenpercent);
		img_1.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				AlertDialog.Builder builder = new AlertDialog.Builder(
						StarbucksActivity.this);
				String time = timestamp();
				builder.setTitle("à¸�à¸”à¸¢à¸·à¸™à¸¢à¸±à¸™à¹€à¸žà¸·à¹ˆà¸­à¸£à¸±à¸šà¸ªà¸´à¸—à¸˜à¸´à¹Œà¸ªà¹ˆà¸§à¸™à¸¥à¸”");
				builder.setMessage(time);
				builder.setPositiveButton("à¸¢à¸·à¸™à¸¢à¸±à¸™",
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog, int id) {
								Editor editor = shared.edit();
								editor.putInt("SavePoint", b1 - 3);
								editor.commit();
			 					setContentView(R.layout.starbucks_layout);
								CheckImage();
								Toast.makeText(getApplicationContext(),
										"à¸—à¸³à¸£à¸²à¸¢à¸�à¸²à¸£à¸ªà¸³à¹€à¸£à¹‡à¸ˆ", Toast.LENGTH_SHORT)
										.show();
							}
						});
				builder.setNegativeButton("à¸›à¸�à¸´à¹€à¸ªà¸˜", null);
				builder.create();

				builder.show();

			}
		});
	}

	public void ShowPromotion2() {
		final SharedPreferences shared = getSharedPreferences(MY_PREFS,
				Context.MODE_PRIVATE);
		final int a1 = shared.getInt("SavePoint", 0);
		final int b1 = a1 + point;
		img_2 = (ImageView) findViewById(R.id.image2);
		img_2.setImageResource(R.drawable.butterfly);
		img_2.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				AlertDialog.Builder builder = new AlertDialog.Builder(
						StarbucksActivity.this);
				String time = timestamp();
				builder.setTitle("à¸�à¸”à¸¢à¸·à¸™à¸¢à¸±à¸™à¹€à¸žà¸·à¹ˆà¸­à¸£à¸±à¸šà¸ªà¸´à¸—à¸˜à¸´à¹Œà¹�à¸¥à¸�à¸‚à¸­à¸‡à¸£à¸²à¸‡à¸§à¸±à¸¥");
				builder.setMessage(time);
				builder.setPositiveButton("à¸¢à¸·à¸™à¸¢à¸±à¸™",
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog, int id) {
								Editor editor = shared.edit();
								editor.putInt("SavePoint", b1 - 8);
								editor.commit();
								setContentView(R.layout.starbucks_layout);
								CheckImage();
								Toast.makeText(getApplicationContext(),
										"à¸—à¸³à¸£à¸²à¸¢à¸�à¸²à¸£à¸ªà¸³à¹€à¸£à¹‡à¸ˆ", Toast.LENGTH_SHORT)
										.show();
							}
						});
				builder.setNegativeButton("à¸›à¸�à¸´à¹€à¸ªà¸˜", null);
				builder.create();

				builder.show();

			}
		});
	}

	public void ShowPromotion3() {
		final SharedPreferences shared = getSharedPreferences(MY_PREFS,
				Context.MODE_PRIVATE);
		final int a1 = shared.getInt("SavePoint", 0);
		final int b1 = a1 + point;
		img_3 = (ImageView) findViewById(R.id.image3);
		img_3.setImageResource(R.drawable.hotcoffee);
		img_3.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				AlertDialog.Builder builder = new AlertDialog.Builder(
						StarbucksActivity.this);
				String time = timestamp();
				builder.setTitle("à¸�à¸”à¸¢à¸·à¸™à¸¢à¸±à¸™à¹€à¸žà¸·à¹ˆà¸­à¸£à¸±à¸šà¸ªà¸´à¸—à¸˜à¸´à¹Œà¹�à¸¥à¸�à¸‚à¸­à¸‡à¸£à¸²à¸‡à¸§à¸±à¸¥");
				builder.setMessage(time);
				builder.setPositiveButton("à¸¢à¸·à¸™à¸¢à¸±à¸™",
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog, int id) {
								Editor editor = shared.edit();
								editor.putInt("SavePoint", b1 - 12);
								editor.commit();
								setContentView(R.layout.starbucks_layout);
								CheckImage();
								Toast.makeText(getApplicationContext(),
										"à¸—à¸³à¸£à¸²à¸¢à¸�à¸²à¸£à¸ªà¸³à¹€à¸£à¹‡à¸ˆ", Toast.LENGTH_SHORT)
										.show();
							}
						});
				builder.setNegativeButton("à¸›à¸�à¸´à¹€à¸ªà¸˜", null);
				builder.create();

				builder.show();

			}
		});
	}

	public void ShowPromotion4() {
		final SharedPreferences shared = getSharedPreferences(MY_PREFS,
				Context.MODE_PRIVATE);
		final int a1 = shared.getInt("SavePoint", 0);
		final int b1 = a1 + point;
		img_4 = (ImageView) findViewById(R.id.image4);
		img_4.setImageResource(R.drawable.coldcoffee);
		img_4.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				AlertDialog.Builder builder = new AlertDialog.Builder(
						StarbucksActivity.this);
				String time = timestamp();
				builder.setTitle("à¸�à¸”à¸¢à¸·à¸™à¸¢à¸±à¸™à¹€à¸žà¸·à¹ˆà¸­à¸£à¸±à¸šà¸ªà¸´à¸—à¸˜à¸´à¹Œà¹�à¸¥à¸�à¸‚à¸­à¸‡à¸£à¸²à¸‡à¸§à¸±à¸¥");
				builder.setMessage(time);
				builder.setPositiveButton("à¸¢à¸·à¸™à¸¢à¸±à¸™",
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog, int id) {
								Editor editor = shared.edit();
								editor.putInt("SavePoint", b1 - 17);
								editor.commit();
								setContentView(R.layout.starbucks_layout);
								CheckImage();
								Toast.makeText(getApplicationContext(),
										"à¸—à¸³à¸£à¸²à¸¢à¸�à¸²à¸£à¸ªà¸³à¹€à¸£à¹‡à¸ˆ", Toast.LENGTH_SHORT)
										.show();
							}
						});
				builder.setNegativeButton("à¸›à¸�à¸´à¹€à¸ªà¸˜", null);
				builder.create();

				builder.show();

			}
		});
	}

	public void ShowPromotion5() {
		final SharedPreferences shared = getSharedPreferences(MY_PREFS,
				Context.MODE_PRIVATE);
		final int a1 = shared.getInt("SavePoint", 0);
		final int b1 = a1 + point;
		img_5 = (ImageView) findViewById(R.id.image5);
		img_5.setImageResource(R.drawable.starbuckscup);
		img_5.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				AlertDialog.Builder builder = new AlertDialog.Builder(
						StarbucksActivity.this);
				String time = timestamp();
				builder.setTitle("à¸�à¸”à¸¢à¸·à¸™à¸¢à¸±à¸™à¹€à¸žà¸·à¹ˆà¸­à¸£à¸±à¸šà¸ªà¸´à¸—à¸˜à¸´à¹Œà¹�à¸¥à¸�à¸‚à¸­à¸‡à¸£à¸²à¸‡à¸§à¸±à¸¥");
				builder.setMessage(time);
				builder.setPositiveButton("à¸¢à¸·à¸™à¸¢à¸±à¸™",
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog, int id) {
								Editor editor = shared.edit();
								editor.putInt("SavePoint", b1 - 20);
								editor.commit();
								setContentView(R.layout.starbucks_layout);
								CheckImage();
								Toast.makeText(getApplicationContext(),
										"à¸—à¸³à¸£à¸²à¸¢à¸�à¸²à¸£à¸ªà¸³à¹€à¸£à¹‡à¸ˆ", Toast.LENGTH_SHORT)
										.show();
							}
						});
				builder.setNegativeButton("à¸›à¸�à¸´à¹€à¸ªà¸˜", null);
				builder.create();

				builder.show();

			}
		});
	}

	public void infoPoint() {
		img_1 = (ImageView) findViewById(R.id.image1);
		img_1.setImageResource(R.drawable.tenpercentf);
		img_1.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				AlertDialog.Builder builder = new AlertDialog.Builder(
						StarbucksActivity.this);
				builder.setMessage("à¸ªà¸°à¸ªà¸¡à¸„à¸£à¸š 3 à¹�à¸•à¹‰à¸¡ à¸£à¸±à¸šà¸Ÿà¸£à¸µà¸ªà¹ˆà¸§à¸™à¸¥à¸” 10%");
				builder.setNegativeButton("à¸•à¸�à¸¥à¸‡", null);
				builder.create();
				builder.show();

			}
		});

		img_2 = (ImageView) findViewById(R.id.image2);
		img_2.setImageResource(R.drawable.butterflyf);
		img_2.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				AlertDialog.Builder builder = new AlertDialog.Builder(
						StarbucksActivity.this);
				builder.setMessage("à¸ªà¸°à¸ªà¸¡à¸„à¸£à¸š 8 à¹�à¸•à¹‰à¸¡ butterfly biscuit à¸žà¸£à¸µ 1 à¸Šà¸´à¹‰à¸™");
				builder.setNegativeButton("à¸•à¸�à¸¥à¸‡", null);
				builder.create();
				builder.show();

			}
		});

		img_3 = (ImageView) findViewById(R.id.image3);
		img_3.setImageResource(R.drawable.hotcoffeef);
		img_3.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				AlertDialog.Builder builder = new AlertDialog.Builder(
						StarbucksActivity.this);
				builder.setMessage("à¸ªà¸°à¸ªà¸¡à¸„à¸£à¸š 12 à¹�à¸•à¹‰à¸¡ à¸£à¸±à¸šà¹€à¸„à¸£à¸·à¹ˆà¸­à¸‡à¸”à¸·à¹ˆà¸¡à¸£à¹‰à¸­à¸™à¸Ÿà¸£à¸µ 1 à¹�à¸�à¹‰à¸§");
				builder.setNegativeButton("à¸•à¸�à¸¥à¸‡", null);
				builder.create();
				builder.show();

			}
		});

		img_4 = (ImageView) findViewById(R.id.image4);
		img_4.setImageResource(R.drawable.coldcoffeef);
		img_4.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				AlertDialog.Builder builder = new AlertDialog.Builder(
						StarbucksActivity.this);
				builder.setMessage("à¸ªà¸°à¸ªà¸¡à¸„à¸£à¸š 17 à¹�à¸•à¹‰à¸¡ à¸£à¸±à¸šà¹€à¸„à¸£à¸·à¹ˆà¸­à¸‡à¸”à¸·à¹ˆà¸¡à¸›à¸±à¹ˆà¸™à¸Ÿà¸£à¸µ 1 à¹�à¸�à¹‰à¸§");
				builder.setNegativeButton("à¸•à¸�à¸¥à¸‡", null);
				builder.create();
				builder.show();

			}
		});

		img_5 = (ImageView) findViewById(R.id.image5);
		img_5.setImageResource(R.drawable.starbuckscupf);
		img_5.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				AlertDialog.Builder builder = new AlertDialog.Builder(
						StarbucksActivity.this);
				builder.setMessage("à¸ªà¸°à¸ªà¸¡à¸„à¸£à¸š 20 à¹�à¸•à¹‰à¸¡ à¸£à¸±à¸š tumbler à¸Ÿà¸£à¸µ 1 à¹�à¸�à¹‰à¸§");
				builder.setNegativeButton("à¸•à¸�à¸¥à¸‡", null);
				builder.create();
				builder.show();

			}
		});
	}

	public String timestamp() {
		String mydate = java.text.DateFormat.getDateTimeInstance().format(
				Calendar.getInstance().getTime());
		return mydate;
	}

}
