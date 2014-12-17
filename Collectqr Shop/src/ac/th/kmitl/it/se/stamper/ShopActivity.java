package ac.th.kmitl.it.se.stamper;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.SaveCallback;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.NumberPicker;
import android.widget.TextView;

public class ShopActivity extends Activity {
	String shopName , branch;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_shop);
		
		// get shopName
		Intent pintent = getIntent();
		shopName = pintent.getStringExtra("shopName");
		branch = pintent.getStringExtra("branch");
		
		// NumberPicker
		final NumberPicker amount = (NumberPicker) findViewById(R.id.numberPicker_amount);
		amount.setMaxValue(10);
		amount.setMinValue(1);

		// Button
		Button button_generate = (Button) findViewById(R.id.button_generate);
		button_generate.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				String qrInputText = String.valueOf(amount.getValue());

				// Connect to Parse
				final ParseObject newQR = new ParseObject(shopName+"Shop");
				newQR.put("CheckPoint", false);
				newQR.put("Point", Integer.parseInt(qrInputText));
				newQR.saveInBackground(new SaveCallback() {

					@Override
					public void done(ParseException arg0) {
						// TODO Auto-generated method stub
						// Success!
						String objId = newQR.getObjectId();

						// Find screen size
						WindowManager manager = (WindowManager) getSystemService(WINDOW_SERVICE);
						Display display = manager.getDefaultDisplay();
						Point point = new Point();
						display.getSize(point);
						int width = point.x;
						int height = point.y;
						int smallerDimension = width < height ? width : height;
						smallerDimension = smallerDimension * 3 / 4;

						// Encode with a QR Code image
						QRCodeEncoder qrCodeEncoder = new QRCodeEncoder(
								"Ducati/" + objId, null, Contents.Type.TEXT,
								BarcodeFormat.QR_CODE.toString(),
								smallerDimension);
						try {
							Bitmap bitmap = qrCodeEncoder.encodeAsBitmap();
							ImageView myImage = (ImageView) findViewById(R.id.imageView_qr);
							myImage.setImageBitmap(bitmap);

						} catch (WriterException e) {
							e.printStackTrace();
						}
					}
				});

				// TextView test = (TextView) findViewById(R.id.textView1);
				// test.setText(qrInputText);
			}
		});

	}
}
