package tr.com.kobis.model;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.os.Handler;
import android.view.Gravity;
import android.widget.Toast;
import tr.com.kobis.MainActivity;
import tr.com.kobis.R;

public class Splash extends Activity
{
    private static int SPLASH_SCREEN_TİME = 3000;

    public boolean isConnectInternet()
    {
        ConnectivityManager conMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        if (conMgr.getActiveNetworkInfo() != null
                && conMgr.getActiveNetworkInfo().isConnected()
                && conMgr.getActiveNetworkInfo().isAvailable())
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);


        new Handler().postDelayed(new Runnable()
        {
            @Override
            public void run()
            {
                Intent intent = new Intent(Splash.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        }, SPLASH_SCREEN_TİME);
        if (!isConnectInternet())
        {
            Toast toast = Toast.makeText(getApplicationContext(), "Uygulama Çevrimdışı açılıyor..", Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER, 0, 390);
            toast.show();

        }
    }
}
