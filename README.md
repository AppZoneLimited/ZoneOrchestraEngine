## Zone Orchestra Engine
This is the base Engine Library for the Zone framework for Android.

### Contents
This library contains three(3) major modules :
1. The Workflow Engine
2. Script Interpreter (Engine)
3. Script Interpreter (Predefined functions)

This readme would be updated as time passes and more commits are made.

### Update One of Module 3 (3.0)  commit code [master 75426dc](https://github.com/AppZoneLimited/ZoneOrchestraEngine/commit/75426dceddab874645f6d0750f794905a0333bfb)

1. Added QR Code decoding with the ZXing library port.
..* This update allows for scanning of QRCodes and handling results as deemed appropriate.

#### Usage
```java
//This can easily be used by extending an Activity and implementing the ResultHandler interface for the ZXingScannerView.ResultHandler
import com.google.zxing.Result;
import com.appzone.zone.orchestra.engine.qrcodescanner.ZXingScannerView;

import android.app.Activity;
import android.app.AlertDialog;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends Activity implements
		ZXingScannerView.ResultHandler {

	private ZXingScannerView mScannerView;

	private String TAG = MainActivity.class.getSimpleName();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mScannerView = new ZXingScannerView(this); //Create instance of the scanner view
		setContentView(mScannerView); //Set contentview with instance
	}

	@Override
	public void onResume() {
		super.onResume();
		mScannerView.setResultHandler(this); // Register activity as a handler scan results.
		mScannerView.startCamera(); // Start camera on resume
	}

	@Override
	public void onPause() {
		super.onPause();
		mScannerView.stopCamera(); // Stop camera on pause
	}

	@Override
	public void handleResult(Result rawResult) {
		// TODO Auto-generated method stub
		Log.e(TAG, rawResult.getText()); // Prints scan results
		Log.e(TAG, rawResult.getBarcodeFormat().toString()); // Prints the scan

		new AlertDialog.Builder(this)
				.setTitle("Success!!")
				.setMessage(
						"Result : " + rawResult.getText() + "\n"
								+ "enCode type : "
								+ rawResult.getBarcodeFormat().toString())
				.create().show();

	}

}

```