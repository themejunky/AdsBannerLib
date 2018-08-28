package themejunky.com.adsbannerlib;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import themejunky.com.banner_lib.bannerAd.BannerAdmob;

public class MainActivity extends AppCompatActivity {

    private RelativeLayout containerBanner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        containerBanner = findViewById(R.id.containerBanner);
        new BannerAdmob(this,containerBanner);
    }
}
