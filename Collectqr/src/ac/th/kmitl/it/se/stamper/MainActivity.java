package ac.th.kmitl.it.se.stamper;

import com.haarman.listviewanimations.swinginadapters.prepared.SwingLeftInAnimationAdapter;
import com.parse.*;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
//import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewTreeObserver;
//import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

	private GridView HeaderGrid;
	private int mPhotoSize, mPhotoSpacing;
	private ImageAdapter imageAdapter;
	String[] parts;
	String company;
	String object_id;
	String point;

	// Some items to add to the GRID
	private static final String[] CONTENT_Header = new String[] {
			"Tap to Scan", "Ducati", "FarmDesign", "Hermes", "KFC",
			"MC Donald", "Pronto", "Shell", "Sizzler", "SP", "Starbucks",
			"Yayoi" };
	private static final int[] ICONS_Header = new int[] {
			R.drawable.camera_icon, R.drawable.logo_ducati,
			R.drawable.logo_farmdesign, R.drawable.logo_hermes,
			R.drawable.logo_kfc, R.drawable.logo_mc, R.drawable.logo_pronto,
			R.drawable.logo_shell, R.drawable.logo_sizzler, R.drawable.logo_sp,
			R.drawable.logo_starbucks, R.drawable.logo_yayoi };

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main);

		// get the photo size and spacing
		mPhotoSize = getResources().getDimensionPixelSize(R.dimen.photo_size);
		mPhotoSpacing = getResources().getDimensionPixelSize(
				R.dimen.photo_spacing);

		// initialize image adapter
		imageAdapter = new ImageAdapter();
		// Animation
		// SwingLeftInAnimationAdapter headerAdapter = new
		// SwingLeftInAnimationAdapter(
		// imageAdapter);

		HeaderGrid = (GridView) findViewById(R.id.HeaderGrid);

		// set image adapter to the GridView
		HeaderGrid.setAdapter(imageAdapter);

		// get the view tree observer of the grid and set the height and numcols dynamically
		HeaderGrid.getViewTreeObserver().addOnGlobalLayoutListener(
				new ViewTreeObserver.OnGlobalLayoutListener() {
					@Override
					public void onGlobalLayout() {
						if (imageAdapter.getNumColumns() == 0) {
							final int numColumns = (int) Math.floor(HeaderGrid
									.getWidth() / (mPhotoSize + mPhotoSpacing));
							if (numColumns > 0) {
								final int columnWidth = (HeaderGrid.getWidth() / numColumns)
										- mPhotoSpacing;
								imageAdapter.setNumColumns(numColumns);
								imageAdapter.setItemHeight(columnWidth);

							}
						}
					}
				});
		
		HeaderGrid.setOnItemClickListener(new OnItemClickListener() {

			public void onItemClick(AdapterView<?> parent, View v,
					int position, long id) {

				switch (position) {
				// Click to Scan QR CODE
				case 0:
					Intent intent = new Intent(
							"com.google.zxing.client.android.SCAN");
					intent.putExtra("SCAN_MODE", "QR_CODE_MODE");
					startActivityForResult(intent, 0);
					break;
				case 1:
					company = "none";
					object_id = "none";
					point = "-1";

					Intent in = new Intent(getApplicationContext(),
							DucatiActivity.class);

					Bundle data = new Bundle();
					data.putString("company", company);
					data.putString("object_id", object_id);
					data.putString("point", point);
					in.putExtras(data);
					startActivity(in);
					break;
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

	// intent value
	public void onActivityResult(int requestCode, int resultCode, Intent intent) {
		Log.d("3", "3");

		if (requestCode == 0) {
			if (resultCode == RESULT_OK) {
				String contents = intent.getStringExtra("SCAN_RESULT");
				parts = contents.split("/");
				company = parts[0];
				object_id = parts[1];
				point = "0";

				if (company.compareTo("Ducati") == 0) {

					Intent in = new Intent(getApplicationContext(),
							DucatiActivity.class);
					Bundle data = new Bundle();
					data.putString("company", company);
					data.putString("object_id", object_id);
					data.putString("point", point);
					in.putExtras(data);
					startActivity(in);
				}

			} else if (resultCode == RESULT_CANCELED) {

				Toast toast = Toast.makeText(this, "Scan was Cancelled!",
						Toast.LENGTH_LONG);
				toast.setGravity(Gravity.TOP, 25, 400);
				toast.show();

			}
		}
	}

	// ////////// ImageAdapter class /////////////////
	public class ImageAdapter extends BaseAdapter {
		private LayoutInflater mInflater;
		private int mItemHeight = 0;
		private int mNumColumns = 0;
		private RelativeLayout.LayoutParams mImageViewLayoutParams;

		public ImageAdapter() {
			mInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			mImageViewLayoutParams = new RelativeLayout.LayoutParams(
					LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
		}

		public int getCount() {
			return CONTENT_Header.length;
		}

		// set numcols
		public void setNumColumns(int numColumns) {
			mNumColumns = numColumns;
		}

		public int getNumColumns() {
			return mNumColumns;
		}

		// set photo item height
		public void setItemHeight(int height) {
			if (height == mItemHeight) {
				return;
			}
			mItemHeight = height;
			mImageViewLayoutParams = new RelativeLayout.LayoutParams(
					LayoutParams.MATCH_PARENT, mItemHeight);
			notifyDataSetChanged();
		}

		public Object getItem(int position) {
			return position;
		}

		public long getItemId(int position) {
			return position;
		}

		public View getView(final int position, View view, ViewGroup parent) {

			if (view == null)
				view = mInflater.inflate(R.layout.photo_item, null);

			ImageView cover = (ImageView) view.findViewById(R.id.cover);
			TextView title = (TextView) view.findViewById(R.id.title);

			cover.setLayoutParams(mImageViewLayoutParams);

			// Check the height matches our calculated column width
			if (cover.getLayoutParams().height != mItemHeight) {
				cover.setLayoutParams(mImageViewLayoutParams);
			}

			cover.setImageResource(ICONS_Header[position % ICONS_Header.length]);
			title.setText(CONTENT_Header[position % CONTENT_Header.length]);

			return view;
		}
	}

}
