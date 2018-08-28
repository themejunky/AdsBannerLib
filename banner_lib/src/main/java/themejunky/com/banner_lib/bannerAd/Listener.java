package themejunky.com.banner_lib.bannerAd;

/**
 * Created by Junky2 on 8/9/2018.
 */

public class Listener {
    public interface AdsListener {
        void isBannerClosed();
        void isBannerLoaded(String theAd);
        //void somethingReloaded(String whatIsLoaded);
    }
    public interface AdsBannerListener{
        void afterBannerIsClosed(String action);
    }

    public interface ListenerLogs{
        void logs(String logs);
    }

    public interface NoAdsLoaded{
        void noAdsLoaded(String action);
    }

}
