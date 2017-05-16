package com.mygdx.jumpfish.android;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.AdSize;
import com.mygdx.jumpfish.AdsController;
import com.mygdx.jumpfish.AlieInJumpFish;

public class AndroidLauncher extends AndroidApplication implements AdsController {
	private static final String BANNER_AD_UNIT_ID = "ca-app-pub-00000000000000000000000000"; // ����� �� �� ���� ������ ����� ����!
	AdView bannerAd;
	
	@Override
	protected void onCreate (Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
		 // Create a gameView and a bannerAd AdView
        View gameView = initializeForView(new AlieInJumpFish(this), config);
        setupAds();
	//	initialize(new AlieInJumpFish(), config);
		// Define the layout
        RelativeLayout layout = new RelativeLayout(this);
        layout.addView(gameView, ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT);
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        params.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        layout.addView(bannerAd, params);
         
        setContentView(layout);
    }
	public void setupAds() {
        bannerAd = new AdView(this);
        bannerAd.setVisibility(View.INVISIBLE);
        bannerAd.setBackgroundColor(0xff000000); // black
      
        bannerAd.setAdUnitId(BANNER_AD_UNIT_ID);
        bannerAd.setAdSize(AdSize.SMART_BANNER);
    }
	@Override
	public void showBannerAd() {
		// TODO Auto-generated method stub
		runOnUiThread(new Runnable() {
	        @Override
	        public void run() {
	            bannerAd.setVisibility(View.VISIBLE);
	          AdRequest.Builder builder = new AdRequest.Builder();
	            
	           AdRequest ad = builder.build();
	  //          AdRequest adRequest =new com.google.android.gms.ads.AdRequest.Builder()
	 //           .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
	  //          .addTestDevice("emulator-5554").build();
	    //        bannerAd.loadAd(adRequest);
	           bannerAd.loadAd(ad);
	        }
	    });
		
	}
	@Override
	public void showBannerAdmy() {
		runOnUiThread(new Runnable() {
	        @Override
	        public void run() {
	            bannerAd.setVisibility(View.VISIBLE);
	       
	        }
	    });
	}
	@Override
	public void hideBannerAd() {
		System.out.println("������� ����������!!");
		// TODO Auto-generated method stub
	    runOnUiThread(new Runnable() {
	        @Override
	        public void run() {
	            bannerAd.setVisibility(View.INVISIBLE);
	        }
	    });
	}
	@Override
	public boolean isWifiConnected() {
		
		ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
	//	NetworkInfo ni = cm.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
		NetworkInfo ni = cm.getActiveNetworkInfo();
		
		return (ni != null && ni.isConnected());
	}
	@Override
	public void hideBannerAdmy() {
		if(bannerAd!=null&&bannerAd.isShown()&&bannerAd.isEnabled())
		 bannerAd.setVisibility(View.INVISIBLE);
		// TODO Auto-generated method stub
		
	}
	
}
