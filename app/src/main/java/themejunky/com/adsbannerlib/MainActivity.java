package themejunky.com.adsbannerlib;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.RelativeLayout;

import themejunky.com.banner_lib.bannerAd.Listener;
import themejunky.com.banner_lib.bannerAd.ManagerBannerAds;
import themejunky.com.banner_lib.bannerAd.MyListener;


public class MainActivity extends AppCompatActivity implements MyListener, Listener.AdsListener, Listener.NoAdsLoaded {
    private ManagerBannerAds managerBannerAds;
    private RelativeLayout containerBanner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        containerBanner = findViewById(R.id.containerBanner);

        managerBannerAds = ManagerBannerAds.getInstance(this,"InfoAds");
        managerBannerAds.initAdmob ("ca-app-pub-5322508131338449/4399656421", containerBanner, this);
        //managerBannerAds.initFacebook ("ca-app-pub-5322508131338449/4399656421", containerBanner, this);
        managerBannerAds.setBannerAdsListener(this);
        managerBannerAds.setNoAdsLoadedListener(this);
    }


    @Override
    public void whatAdIsLoaded(String theAd) {
        Log.d("InfoAds", "whatAdIsLoaded "+theAd);
    }


    @Override
    public void isBannerClosed() {
        Log.d("InfoAds", "isBannerClosed");
    }

    @Override
    public void isBannerLoaded(String theAd) {
        //this line is not reached
        Log.d("InfoAds", "isBannerLoaded "+theAd);
    }

    @Override
    public void noAdsLoaded(String action) {
        Log.d("InfoAds", "noAdsLoaded");
    }
}
