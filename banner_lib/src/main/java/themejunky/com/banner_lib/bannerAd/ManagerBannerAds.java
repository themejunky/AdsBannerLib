package themejunky.com.banner_lib.bannerAd;

import android.app.Activity;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

public class ManagerBannerAds  implements Listener.AdsListener {
    private final Context context;
    private static ManagerBannerAds instance;
    private final String tagName;
    private Listener.AdsListener listener;
    private Listener.NoAdsLoaded noAdsLoadedListener;
    //private FacebookBannerAds facebookBannerAds;
    private AdmobBannerAds admobBannerAds;
    private String loadedAd;

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


    public void initFacebook(String key) {
        if (key != null) {
            Log.d(tagName, "initFacebook context: "+context+" tagName: "+tagName+" key: "+key);
            //facebookBannerAds = new FacebookBannerAds(context, tagName, key, this);
        }
    }

    public void initAdmob(String key, RelativeLayout myView) {
        if (key != null) {
            Log.d(tagName, "initAdmob context: "+context+" tagName: "+tagName+" key: "+key);
            //admobBannerAds = new AdmobBannerAds(context, tagName, key, myView);
            //admobBannerAds = admobBannerAds.getInstance(mContext, key, this);
            //admobBannerAds = new AdmobBannerAds(context, tagName, key, myView, listener);
            admobBannerAds = new AdmobBannerAds(context, tagName, key, myView, this);
        }
    }


    /*

    @Override
    public void isBannerClosed() {
        if (listener != null) {
            Log.d(tagName,"isBannerClosed - ");
            //listener.afterBannerIsClosed(action);
        } else {
            Log.d(tagName, "listener null");
        }
    }

    @Override
    public void isBannerLoaded(String theAd) {
        Log.d("InfoAds","isBannerLoaded - "+theAd);

        if (theAd.equals("admob")){
            loadedAd = "admob";
            //showBannerAd(this, "admob");
        } else if (theAd.equals("facebook")){
            loadedAd = "facebook";
            //showBannerAd(this, "facebook");
        }
    }
    */

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
        Log.d("InfoAds","isBannerLoaded - "+theAd);
    }

}
