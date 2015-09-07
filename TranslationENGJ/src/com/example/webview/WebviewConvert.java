package com.example.webview;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;

public class WebviewConvert extends Activity {
	private WebView webview;
	private ProgressDialog pd;

	@SuppressLint("SetJavaScriptEnabled")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.webview);
		webview = (WebView) findViewById(R.id.webView1);

		/*
		 * pd.setMessage("Loading..Please wait.");
		 * pd.setCanceledOnTouchOutside(false); pd.show();
		 */
		String url="http://mysamaj.co.in/HtmlPage.html";
		webview.loadDataWithBaseURL("file:///assets/android_asset.html",url,"text/html","utf-8",null);
		webview.loadUrl("http://mysamaj.co.in/HtmlPage.html");
		
		WebSettings webSettings = webview.getSettings();
		webSettings.setJavaScriptEnabled(true);
	}

}
