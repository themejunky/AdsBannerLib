package themejunky.com.banner_lib.bannerAd;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import themejunky.com.banner_lib.R;

public class BannerAdmob {
    private AdView mAdView;
    private final String numeTag = "InfoAds";
        public BannerAdmob(Context context, RelativeLayout view){

        LayoutInflater inflater = LayoutInflater.from(context);
        View view1 = inflater.inflate(R.layout.container_banner_admob,null);

        mAdView = view1.findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        mAdView.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {
                Log.d(numeTag, "Admob Banner: Loaded!");
            }

            @Override
            public void onAdFailedToLoad(int errorCode) {
                Log.d(numeTag, "Admob Banner: Failed To Load: " + errorCode);
            }

            @Override
            public void onAdOpened() {
                Log.d(numeTag, "Admob Banner: onAdOpened!");
            }

            @Override
            public void onAdLeftApplication() {
                Log.d(numeTag, "Admob Banner: onAdLeftApplication!");
            }

            @Override
            public void onAdClosed() {
                Log.d(numeTag, "Admob Banner" + ": Closed!");
            }
        });

        view.removeAllViews();
        view.addView(view1);
    }
}
