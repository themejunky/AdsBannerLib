package themejunky.com.banner_lib.bannerAd;


import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.facebook.ads.Ad;
import com.facebook.ads.AdChoicesView;
import com.facebook.ads.AdError;
import com.facebook.ads.AdIconView;
import com.facebook.ads.AdListener;
import com.facebook.ads.AdSettings;
import com.facebook.ads.AdSize;
import com.facebook.ads.AdView;
import com.facebook.ads.NativeAdListener;
import com.facebook.ads.NativeBannerAd;

import java.util.ArrayList;
import java.util.List;

import themejunky.com.banner_lib.R;


public class FacebookBannerAds {
    private Listener.AdsListener listener;
    private String numeTag;
    private RelativeLayout myView;
    private Context context;
    private View mAdView;
    private LinearLayout bannerAdContainer;
    private NativeBannerAd nativeBannerAd;
    private LinearLayout adView;

    public FacebookBannerAds(Context context, String nameTag, String keyFacebook, RelativeLayout myView, Listener.AdsListener listener){
        this.context = context;
        this.numeTag = nameTag;
        this.listener = listener;
        this.myView = myView;
        initFacebookBanner(keyFacebook, context);
    }

    public void initFacebookBanner(String keyFacebook, final Context context) {
        //LayoutInflater inflater = LayoutInflater.from(context);
        //View view1 = inflater.inflate(R.layout.container_banner_facebook, null);;

        nativeBannerAd = new NativeBannerAd(context, keyFacebook);
        AdSettings.addTestDevice("cc433d54-f202-4ab0-a1a1-1bc6d5f1fa7c");
        AdSettings.addTestDevice("e46ccfd7-8274-402f-b315-3f94d7e17f91");
        AdSettings.addTestDevice("ed135967-ba53-40aa-ab9c-9944ce920e2f");
        nativeBannerAd.setAdListener(new NativeAdListener() {
            @Override
            public void onMediaDownloaded(Ad ad) {
                // Native ad finished downloading all assets
                Log.e(numeTag, "Native ad finished downloading all assets.");
            }

            @Override
            public void onError(Ad ad, AdError adError) {
                // Native ad failed to load
                Log.e(numeTag, "Native ad failed to load: " + adError.getErrorMessage());
            }

            @Override
            public void onAdLoaded(Ad ad) {
                // Native ad is loaded and ready to be displayed
                Log.d(numeTag, "Native ad is loaded and ready to be displayed!");
                listener.isBannerLoaded("facebook");
                // Race condition, load() called again before last ad was displayed
                if (nativeBannerAd == null || nativeBannerAd != ad) {
                    return;
                }
                // Inflate Native Banner Ad into Container
                inflateAd(nativeBannerAd, context);
            }

            @Override
            public void onAdClicked(Ad ad) {
                // Native ad clicked
                Log.d(numeTag, "Native ad clicked!");
            }

            @Override
            public void onLoggingImpression(Ad ad) {
                // Native ad impression
                //Log.d(numeTag, "Native ad impression logged!");
            }
        });
        // load the ad
        nativeBannerAd.loadAd();
    }


    private void inflateAd(NativeBannerAd nativeBannerAd, Context context) {
        // Unregister last ad
        nativeBannerAd.unregisterView();

        View v = LayoutInflater.from(context).inflate(R.layout.container_banner_facebook, null);
        bannerAdContainer = v.findViewById(R.id.banner_ad_container);
        myView.removeAllViews();
        myView.addView(v);

        LayoutInflater inflater = LayoutInflater.from(context);
        adView = (LinearLayout) inflater.inflate(R.layout.banner_ad_facebook, bannerAdContainer, false);
        bannerAdContainer.removeAllViews();
        bannerAdContainer.addView(adView);

        // Add the AdChoices icon
        RelativeLayout adChoicesContainer = adView.findViewById(R.id.ad_choices_container);
        AdChoicesView adChoicesView = new AdChoicesView(context, nativeBannerAd, true);
        adChoicesContainer.addView(adChoicesView, 0);

        // Create native UI using the ad metadata.
        TextView nativeAdTitle = adView.findViewById(R.id.native_ad_title);
        TextView nativeAdSocialContext = adView.findViewById(R.id.native_ad_social_context);
        TextView sponsoredLabel = adView.findViewById(R.id.native_ad_sponsored_label);
        AdIconView nativeAdIconView = adView.findViewById(R.id.native_icon_view);
        Button nativeAdCallToAction = adView.findViewById(R.id.native_ad_call_to_action);

        // Set the Text.
        nativeAdCallToAction.setText(nativeBannerAd.getAdCallToAction());
        nativeAdCallToAction.setVisibility(
                nativeBannerAd.hasCallToAction() ? View.VISIBLE : View.INVISIBLE);
        nativeAdTitle.setText(nativeBannerAd.getAdvertiserName());
        nativeAdSocialContext.setText(nativeBannerAd.getAdSocialContext());
        sponsoredLabel.setText(nativeBannerAd.getSponsoredTranslation());

        // Register the Title and CTA button to listen for clicks.
        List<View> clickableViews = new ArrayList<>();
        clickableViews.add(nativeAdTitle);
        clickableViews.add(nativeAdCallToAction);
        nativeBannerAd.registerViewForInteraction(adView, nativeAdIconView, clickableViews);
    }

}
