package themejunky.com.banner_lib.bannerAd;


import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;

import com.facebook.ads.Ad;
import com.facebook.ads.AdError;
import com.facebook.ads.AdListener;
import com.facebook.ads.AdSize;
import com.facebook.ads.AdView;

import themejunky.com.banner_lib.R;


public class FacebookBannerAds {
    private AdView adViewFacebook;
    private Listener.AdsListener listener;
    private String numeTag;
    private RelativeLayout myView;
    private Context context;

    public FacebookBannerAds(Context context, String nameTag, String keyFacebook, RelativeLayout myView, Listener.AdsListener listener){
        this.context = context;
        this.numeTag = nameTag;
        this.listener = listener;
        this.myView = myView;
        initFacebookBanner(keyFacebook);
    }

    public void initFacebookBanner(String keyFacebook) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view1 = inflater.inflate(R.layout.container_banner_facebook, null);;

        adViewFacebook = new AdView(context, keyFacebook, AdSize.BANNER_HEIGHT_50);

        // Find the Ad Container
        //LinearLayout adContainer = (LinearLayout) findViewById(R.id.adViewFacebook);
        // Add the ad view to your activity layout
        //adContainer.addView(adViewFacebook);

        // Request an ad
        adViewFacebook.loadAd();

        adViewFacebook.setAdListener(new AdListener() {
            @Override
            public void onError(Ad ad, AdError adError) {
                Log.d(numeTag, "Facebook Banner: error: " + adError.getErrorMessage());
            }

            @Override
            public void onAdLoaded(Ad ad) {
                Log.d(numeTag, "Facebook Banner: is Loaded");
                listener.isBannerLoaded("facebook");
                //listener.somethingReloaded("facebook");
            }

            @Override
            public void onAdClicked(Ad ad) {
                Log.d(numeTag, "Facebook Banner: is Clicked");
            }

            @Override
            public void onLoggingImpression(Ad ad) {
                // Ad impression logged callback
            }
        });

        myView.removeAllViews();
        myView.addView(view1);
    }

}
