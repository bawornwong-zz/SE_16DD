package ac.th.kmitl.it.se.stamper;

import com.parse.*;
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
import android.view.View.OnClickListener;
import android.view.Window;

import android.widget.ImageView;
import android.widget.Toast;

public class DucatiActivity extends Activity {

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
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.ducati_layout);

		Intent b = getIntent();
		recievecompany = b.getExtras().getString("company");
		recieveobject_id = b.getExtras().getString("object_id");
		recievepoint = b.getExtras().getString("point");
		// point = Integer.parseInt(recievepoint);
		shop = recievecompany + "Shop";

		if (Integer.parseInt(recievepoint) == -1) {
			final SharedPreferences shared = getSharedPreferences(MY_PREFS,
					Context.MODE_PRIVATE);
			int a = shared.getInt("SavePoint", 0);
			img_point = (ImageView) findViewById(R.id.imageDucati);
			if (a == 0) {
				Toast.makeText(getApplicationContext(),
						"คุณยังไม่มี�?ต้มในตอนนี้", Toast.LENGTH_SHORT).show();
			} else if (a == 1) {
				img_point.setImageResource(R.drawable.one_number);
			} else if (a == 2) {
				img_point.setImageResource(R.drawable.two_number);
			} else if (a == 3) {
				img_point.setImageResource(R.drawable.three_number);
			} else if (a == 4) {
				img_point.setImageResource(R.drawable.four_number);
			} else if (a == 5) {
				img_point.setImageResource(R.drawable.five_number);
			} else if (a == 6) {
				img_point.setImageResource(R.drawable.six_number);
			} else if (a == 7) {
				img_point.setImageResource(R.drawable.seven_number);
			} else if (a == 8) {
				img_point.setImageResource(R.drawable.eight_number);
			} else if (a == 9) {
				img_point.setImageResource(R.drawable.nine_number);
			} else if (a >= 10) {
				img_point.setImageResource(R.drawable.bingo_number);
				img_point.setOnClickListener(new OnClickListener() {
					@Override
					public void onClick(View v) {
						AlertDialog.Builder builder = new AlertDialog.Builder(
								DucatiActivity.this);
						builder.setMessage("�?ดยืนยันเพื่อรับสิทธิ์");
						builder.setPositiveButton("ยืนยัน",
								new DialogInterface.OnClickListener() {
									public void onClick(DialogInterface dialog,
											int id) {
										Editor editor = shared.edit();
										editor.putInt("SavePoint", 0);
										editor.commit();
										setContentView(R.layout.ducati_layout);
										Toast.makeText(
												getApplicationContext(),
												"ระบบได้ทำ�?ารรีเซ็ท�?ต้มของคุณ�?ล้ว",
												Toast.LENGTH_SHORT).show();
									}
								});
						builder.setNegativeButton("ป�?ิเสธ", null);
						builder.create();

						builder.show();

					}
				});

			}

		} else {

			// check database to get point
			ParseQuery<ParseObject> query = ParseQuery.getQuery(shop);
			query.getInBackground(recieveobject_id,
					new GetCallback<ParseObject>() {
						public void done(ParseObject object, ParseException e) {
							if (e == null) {
								Log.d("******", "+++++++++++++++");
								boolean checkpoint = object
										.getBoolean("CheckPoint");
								if (checkpoint == false) {
									point = object.getInt("Point");
									// save session
									final SharedPreferences shared = getSharedPreferences(
											MY_PREFS, Context.MODE_PRIVATE);
									int a = shared.getInt("SavePoint", 0);
									a = a + point;
									setContentView(R.layout.ducati_layout);
									img_point = (ImageView) findViewById(R.id.imageDucati);
									if (a == 1) {
										img_point
												.setImageResource(R.drawable.one_number);
										Editor editor = shared.edit();
										editor.putInt("SavePoint", a);
										editor.commit();
									} else if (a == 2) {
										img_point
												.setImageResource(R.drawable.two_number);
										Editor editor = shared.edit();
										editor.putInt("SavePoint", a);
										editor.commit();
									} else if (a == 3) {
										img_point
												.setImageResource(R.drawable.three_number);
										Editor editor = shared.edit();
										editor.putInt("SavePoint", a);
										editor.commit();
									} else if (a == 4) {
										img_point
												.setImageResource(R.drawable.four_number);
										Editor editor = shared.edit();
										editor.putInt("SavePoint", a);
										editor.commit();
									} else if (a == 5) {
										img_point
												.setImageResource(R.drawable.five_number);
										Editor editor = shared.edit();
										editor.putInt("SavePoint", a);
										editor.commit();
									} else if (a == 6) {
										img_point
												.setImageResource(R.drawable.six_number);
										Editor editor = shared.edit();
										editor.putInt("SavePoint", a);
										editor.commit();
									} else if (a == 7) {
										img_point
												.setImageResource(R.drawable.seven_number);
										Editor editor = shared.edit();
										editor.putInt("SavePoint", a);
										editor.commit();
									} else if (a == 8) {
										img_point
												.setImageResource(R.drawable.eight_number);
										Editor editor = shared.edit();
										editor.putInt("SavePoint", a);
										editor.commit();
									} else if (a == 9) {
										img_point
												.setImageResource(R.drawable.nine_number);
										Editor editor = shared.edit();
										editor.putInt("SavePoint", a);
										editor.commit();
									} else if (a >= 10) {
										img_point
												.setImageResource(R.drawable.bingo_number);
										Editor editor = shared.edit();
										editor.putInt("SavePoint", a);
										editor.commit();
										img_point
												.setOnClickListener(new OnClickListener() {
													@Override
													public void onClick(View v) {
														AlertDialog.Builder builder = new AlertDialog.Builder(
																DucatiActivity.this);
														builder.setMessage("�?ดยืนยันเพื่อรับสิทธิ์");
														builder.setPositiveButton(
																"ยืนยัน",
																new DialogInterface.OnClickListener() {
																	public void onClick(
																			DialogInterface dialog,
																			int id) {
																		Editor editor = shared
																				.edit();
																		editor.putInt(
																				"SavePoint",
																				0);
																		editor.commit();
																		setContentView(R.layout.ducati_layout);
																		Toast.makeText(
																				getApplicationContext(),
																				"ระบบได้ทำ�?ารรีเซ็ท�?ต้มของคุณ�?ล้ว",
																				Toast.LENGTH_SHORT)
																				.show();
																	}
																});
														builder.setNegativeButton(
																"ป�?ิเสธ", null);
														builder.create();

														// สุดท้ายอย่าลืม show()
														// ด้วย
														builder.show();

													}
												});

									}
									object.put("CheckPoint", true);
									object.saveInBackground();
								} else {
									Toast.makeText(getApplicationContext(),
											"QrCode ไม่ถู�?ต้อง",
											Toast.LENGTH_SHORT).show();
								}

							} else {
								// something went wrong
								Log.d("******", "---------------");
							}
						}
					});
		}

	}

	// create menu touch bar
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater myMenuInflater = getMenuInflater();
		myMenuInflater.inflate(R.menu.mymenu, menu);
		return super.onCreateOptionsMenu(menu);
	}
}
