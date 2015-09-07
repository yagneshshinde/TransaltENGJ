package com.example.webview;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;

public class MainActivity extends Activity {

	private TextView transText;
	private String data, edtData;
	private EditText edtTxt;
	private Button btn;
	private Typeface myFont;
	private ProgressBar pb;
	private AdView mAdView;
	private InterstitialAd interstitial;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		transText = (TextView) findViewById(R.id.translatedText);
		edtTxt = (EditText) findViewById(R.id.editTextEN);
		myFont = Typeface.createFromAsset(getAssets(), "Shrutib.ttf");
		pb = (ProgressBar) findViewById(R.id.progressBarTranslate);
		pb.setVisibility(View.INVISIBLE);
		btn = (Button) findViewById(R.id.btnTranslate);
		btn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				edtData = edtTxt.getText().toString().trim();
				new translationClass().execute();

			}
		});
		// Prepare the Interstitial Ad
		interstitial = new InterstitialAd(MainActivity.this);
		// Insert the Ad Unit ID
		interstitial.setAdUnitId("ca-app-pub-6372592089538589/1298643354");

		// Locate the Banner Ad in activity_main.xml
		mAdView = (AdView) this.findViewById(R.id.ad_view);

		// Request for Ads
		AdRequest adRequest = new AdRequest.Builder()

		// Add a test device to show Test Ads
		// .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
				.build();

		// Load ads into Banner Ads
		mAdView.loadAd(adRequest);

		// Load ads into Interstitial Ads
		interstitial.loadAd(adRequest);

		// Prepare an Interstitial Ad Listener
		interstitial.setAdListener(new AdListener() {
			public void onAdLoaded() {
				// Call displayInterstitial() function
				displayInterstitial();
			}
		});
	}

	public void displayInterstitial() {
		// If Ads are loaded, show Interstitial else show nothing.
		if (interstitial.isLoaded()) {
			interstitial.show();
		}
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		if (mAdView != null) {
			mAdView.pause();
		}
		super.onPause();
	}

	/** Called when returning to the activity */
	@Override
	public void onResume() {
		super.onResume();
		if (mAdView != null) {
			mAdView.resume();
		}
	}

	/** Called before the activity is destroyed */
	@Override
	public void onDestroy() {
		if (mAdView != null) {
			mAdView.destroy();
		}
		super.onDestroy();
	}

	private class translationClass extends AsyncTask<String, Void, Void> {

		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();
			pb.setVisibility(View.VISIBLE);
			Toast.makeText(getApplicationContext(), "Translating  " + edtData,
					Toast.LENGTH_SHORT).show();
		}

		@Override
		protected Void doInBackground(String... params) {
			// TODO Auto-generated method stub
			try {
				data = WebServiceA.invokeAddressWS(edtData, "translation");
			} catch (Exception e) {
				// TODO: handle exception
				Log.v("Error", e.toString());
			}

			return null;
		}

		@Override
		protected void onPostExecute(Void result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			// Toast.makeText(getApplicationContext(), data, 2000).show();
			if (data != null) {
				transText.setTypeface(myFont);
				transText.setText(data);
			} else {
				Toast.makeText(getApplicationContext(), "Data Not Found!",
						Toast.LENGTH_SHORT).show();
			}
			pb.setVisibility(View.GONE);

		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			Intent i = new Intent(getApplicationContext(), WebviewConvert.class);
			startActivity(i);
			return true;

		}
		return super.onOptionsItemSelected(item);
	}
}
