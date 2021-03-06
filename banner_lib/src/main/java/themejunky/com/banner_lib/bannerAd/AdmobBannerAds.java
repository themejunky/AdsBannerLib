package themejunky.com.banner_lib.bannerAd;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;

import themejunky.com.banner_lib.R;

public class AdmobBannerAds {
    private AdView mAdView;
    private Listener.AdsListener listener;
    private String numeTag;
    private RelativeLayout myView;
    private Context context;

    public AdmobBannerAds(Context context, String nameTag, String keyAdmob, RelativeLayout myView, Listener.AdsListener listener) {
        this.context = context;
        this.numeTag = nameTag;
        this.listener = listener;
        this.myView = myView;
        initAdmobBanner(keyAdmob);
    }


    public void initAdmobBanner(String keyAdmob){
        mAdView = new AdView(context);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.setAdUnitId(keyAdmob);
        mAdView.setAdSize(AdSize.SMART_BANNER);
        mAdView.loadAd(adRequest);

        mAdView.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {
                Log.d(numeTag, "Admob Banner: Loaded!");
                listener.isBannerLoaded("admob");
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
                listener.isBannerClosed();
            }
        });

        myView.removeAllViews();
        myView.addView(mAdView);
    }

}
