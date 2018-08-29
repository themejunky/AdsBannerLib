package themejunky.com.banner_lib.bannerAd;

import android.content.Context;
import android.util.Log;
import android.widget.RelativeLayout;

public class ManagerBannerAds  implements Listener.AdsListener {
    private final Context context;
    private static ManagerBannerAds instance;
    private String tagName;
    private Listener.AdsListener listener;
    private Listener.NoAdsLoaded noAdsLoadedListener;
    private FacebookBannerAds facebookBannerAds;
    private AdmobBannerAds admobBannerAds;
    private MyListener mListener;

    public static synchronized ManagerBannerAds getInstance(Context context, String tagName) {
        if (instance == null) {
            return new ManagerBannerAds(context, tagName);
        } else {
            return instance;
        }
    }

    public ManagerBannerAds(Context context, String tagName) {
        this.context = context;
        this.tagName = tagName;
    }


    public void initFacebook(String keyFacebook, RelativeLayout myView, MyListener nListener) {
        if (keyFacebook != null) {
            mListener = nListener;
            Log.d(tagName, "initFacebook context: "+context+" key: "+keyFacebook+" nListener "+nListener);
            facebookBannerAds = new FacebookBannerAds(context, tagName, keyFacebook, myView, this);
        }
    }

    public void initAdmob(String keyAdmob, RelativeLayout myView, MyListener nListener) {
        if (keyAdmob != null) {
            mListener = nListener;
            Log.d(tagName, "initAdmob context: "+context+" key: "+keyAdmob+" nListener "+nListener);
            admobBannerAds = new AdmobBannerAds(context, tagName, keyAdmob, myView, this);
        }
    }


    public void setBannerAdsListener(Listener.AdsListener adsListener) {
        this.listener = adsListener;
    }

    public void setNoAdsLoadedListener(Listener.NoAdsLoaded noAdsLoadedListener) {
        this.noAdsLoadedListener = noAdsLoadedListener;
    }


    public void showBannerAd(Context context, String theAd){
        Log.d(tagName,"showBannerAd "+theAd);
    };

    @Override
    public void isBannerClosed() {

    }

    @Override
    public void isBannerLoaded(String theAd) {
        Log.d(tagName,"isBannerLoaded - "+theAd);
        mListener.whatAdIsLoaded(theAd);
    }


}
