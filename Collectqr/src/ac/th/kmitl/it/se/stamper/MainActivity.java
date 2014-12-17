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

	private GridView photoGrid;
	private int mPhotoSize, mPhotoSpacing;
	private ImageAdapter imageAdapter;
	String[] parts;
	String company;
	String object_id;
	String point;

	// Some items to add to the GRID
	private static final String[] CONTENT_Header = new String[] {
			"                     CollectQR", "                  Tap to Scan",
			"Starbucks", "FarmDesign", "Hermes", "KFC", "MC Donald", "Pronto",
			"Sizzler", "SP", "Yayoi", "Seven Eleven", "Black Canyon",
			"Burger King", "Cold Stone", "Dak Gai Bi", "Domino", "Kimju",
			"Krispy Kream", "Kyorollen", "MK", "Narai Pizza",
			"The Pizza Company", "Pizza Hut", "Swensen" };
	private static final int[] ICONS_Header = new int[] { R.drawable.collectqr,
			R.drawable.camera_icon, R.drawable.logo_starbucks,
			R.drawable.logo_farmdesign, R.drawable.logo_hermes,
			R.drawable.logo_kfc, R.drawable.logo_mc, R.drawable.logo_pronto,
			R.drawable.logo_sizzler, R.drawable.logo_sp, R.drawable.logo_yayoi,
			R.drawable.swensen_logo, R.drawable.blackcanyon_logo,
			R.drawable.burgerking_logo, R.drawable.coldstone_logo,
			R.drawable.dakgalbi_logo, R.drawable.domino_logo,
			R.drawable.kinju_logo, R.drawable.krispykream_logo,
			R.drawable.kyorollen_logo, R.drawable.mk_logo,
			R.drawable.narai_logo, R.drawable.pizzaconpany_logo,
			R.drawable.pizzahut_logo, R.drawable.swensen_logo };

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

		photoGrid = (GridView) findViewById(R.id.photoGrid);

		// Header
		SwingLeftInAnimationAdapter headerAdapter = new SwingLeftInAnimationAdapter(
				imageAdapter);
		headerAdapter.setAbsListView(photoGrid);
		photoGrid.setAdapter(headerAdapter);

		// get the view tree observer of the grid and set the height and numcols dynamically
		photoGrid.getViewTreeObserver().addOnGlobalLayoutListener(
				new ViewTreeObserver.OnGlobalLayoutListener() {
					@Override
					public void onGlobalLayout() {
						if (imageAdapter.getNumColumns() == 0) {
							final int numColumns = (int) Math.floor(photoGrid
									.getWidth() / (mPhotoSize + mPhotoSpacing));
							if (numColumns > 0) {
								final int columnWidth = (photoGrid.getWidth() / numColumns)
										- mPhotoSpacing;
								imageAdapter.setNumColumns(numColumns);
								imageAdapter.setItemHeight(columnWidth);

							}
						}
					}
				});

		photoGrid.setOnItemClickListener(new OnItemClickListener() {

			public void onItemClick(AdapterView<?> parent, View v,
					int position, long id) {

				switch (position) {
				case 0:
					break;
					// Click to Scan QR CODE
				case 1:
					Intent intent1 = new Intent(
							"com.google.zxing.client.android.SCAN");
					intent1.putExtra("SCAN_MODE", "QR_CODE_MODE");
					startActivityForResult(intent1, 0);
					break;
				case 2:
					company = "none";
					object_id = "none";
					point = "-1";

					Intent intent2 = new Intent(getApplicationContext(),
							StarbucksActivity.class);

					Bundle data = new Bundle();
					data.putString("company", company);
					data.putString("object_id", object_id);
					data.putString("point", point);
					intent2.putExtras(data);
					startActivity(intent2);
					break;
				default:
					Intent intent = new Intent(getApplicationContext(),
							ComingSoonActivity.class);

					startActivity(intent);
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
							CollectQRActivity.class);
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
