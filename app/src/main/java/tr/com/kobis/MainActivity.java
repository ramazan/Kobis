package tr.com.kobis;

import java.io.IOException;
import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.net.ConnectivityManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ExpandableListView;
import android.widget.ListView;
import android.widget.TextView;
import tr.com.kobis.adapter.ExpandListAdapter;
import tr.com.kobis.adapter.NavDrawerListAdapter;
import tr.com.kobis.data.DataManager;
import tr.com.kobis.fragments.AboutFragment;
import tr.com.kobis.fragments.CallCenterFragment;
import tr.com.kobis.fragments.LeaseFragment;
import tr.com.kobis.fragments.MapFragment;
import tr.com.kobis.fragments.PriceFragment;
import tr.com.kobis.fragments.StationsFragment;
import tr.com.kobis.model.NavDrawerItem;
import tr.com.kobis.model.Station;
import tr.com.kobis.model.StationImage;

@SuppressWarnings("NewApi")
public class MainActivity extends Activity
{

    String url = "http://www.kobis.com.tr/istasyonlar.aspx";
    private DrawerLayout mDrawerLayout;
    private ListView mDrawerList;
    private ActionBarDrawerToggle mDrawerToggle;
    private CharSequence mDrawerTitle;
    private CharSequence mTitle;
    private String[] navMenuTitles;
    private TypedArray navMenuIcons;
    private ArrayList<NavDrawerItem> navDrawerItems;
    private NavDrawerListAdapter adapter;
    private MenuItem refreshMenuItem;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {

        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_main);

            checkInternet();
            if (isConnectInternet(this))
            {
            new SaveStations().execute();
            }

        mTitle = mDrawerTitle = getTitle();
        navMenuTitles = getResources().getStringArray(R.array.nav_drawer_items);
        navMenuIcons = getResources().obtainTypedArray(R.array.nav_drawer_icons);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerList = (ListView) findViewById(R.id.list_slidermenu);
        navDrawerItems = new ArrayList<NavDrawerItem>();
        navDrawerItems.add(new NavDrawerItem(navMenuTitles[0], navMenuIcons.getResourceId(0, -1)));
        navDrawerItems.add(new NavDrawerItem(navMenuTitles[1], navMenuIcons.getResourceId(1, -1)));
        navDrawerItems.add(new NavDrawerItem(navMenuTitles[2], navMenuIcons.getResourceId(2, -1)));
        navDrawerItems.add(new NavDrawerItem(navMenuTitles[3], navMenuIcons.getResourceId(3, -1)));
        navDrawerItems.add(new NavDrawerItem(navMenuTitles[4], navMenuIcons.getResourceId(4, -1)));
        navDrawerItems.add(new NavDrawerItem(navMenuTitles[5], navMenuIcons.getResourceId(5, -1)));
        navMenuIcons.recycle();
        mDrawerList.setOnItemClickListener(new SlideMenuClickListener());
        adapter = new NavDrawerListAdapter(getApplicationContext(), navDrawerItems);
        mDrawerList.setAdapter(adapter);
        getActionBar().setDisplayHomeAsUpEnabled(true);
        getActionBar().setHomeButtonEnabled(true);
        getActionBar().setDisplayUseLogoEnabled(false);

        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout,
                R.drawable.ic_drawer,
                R.string.drawer_open,
                R.string.drawer_open
        )
        {
            public void onDrawerClosed(View view)
            {
                getActionBar().setTitle(mTitle);
                invalidateOptionsMenu();
            }

            public void onDrawerOpened(View drawerView)
            {
                getActionBar().setTitle("Kocaeli Bisikletli Ulaşım Sistemi");
                invalidateOptionsMenu();
            }
        };
        mDrawerLayout.setDrawerListener(mDrawerToggle);

        if (savedInstanceState == null)
        {
            displayView(0);
        }

    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        if (mDrawerToggle.onOptionsItemSelected(item))
        {
            return true;
        }
        switch (item.getItemId())
        {
            case R.id.action_refresh:
                if (isConnectInternet(this))
                {

                    refreshMenuItem = item;
                    new refreshStations().execute();


                }
                else
                {

                    final AlertDialog alertDialog = new AlertDialog.Builder(this,
                            AlertDialog.THEME_HOLO_LIGHT).create();
                    alertDialog.setTitle("Bağlantı Hatası");
                    alertDialog.setIcon(R.drawable.alert_icon);
                    alertDialog.setCancelable(false);
                    alertDialog.setMessage("\n\nLütfen internet bağlantınızı açtıktan sonra tekrar deneyiniz.\n\n");
                    alertDialog.setButton("Tamam", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            alertDialog.dismiss();
                        }
                    });
                    alertDialog.show();
                }

                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu)
    {

        return super.onPrepareOptionsMenu(menu);
    }




    private void displayView(int position)
    {
        Fragment fragment = null;
        switch (position)
        {
            case 0:
                fragment = new StationsFragment();
                break;
            case 1:
                fragment = new MapFragment();
                break;
            case 2:
                fragment = new LeaseFragment();
                break;
            case 3:
                fragment = new PriceFragment();
                break;
            case 4:
                fragment = new CallCenterFragment();
                break;
            case 5:
                fragment = new AboutFragment();
                break;

            default:
                break;
        }

        if (fragment != null)
        {
            FragmentManager fragmentManager = getFragmentManager();
            fragmentManager.beginTransaction()
                    .replace(R.id.frame_container, fragment).commit();

            mDrawerList.setItemChecked(position, true);
            mDrawerList.setSelection(position);
            setTitle(navMenuTitles[position]);
            mDrawerLayout.closeDrawer(mDrawerList);
        }
        else
        {
            Log.e("MainActivity", "Error in creating fragment");
        }
    }

    @Override
    public void setTitle(CharSequence title)
    {
        mTitle = title;
        getActionBar().setTitle(mTitle);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState)
    {
        super.onPostCreate(savedInstanceState);
        mDrawerToggle.syncState();
        //showStations();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig)
    {
        super.onConfigurationChanged(newConfig);
        mDrawerToggle.onConfigurationChanged(newConfig);
    }

    private void saveStations() throws IOException
    {
        ArrayList<Station> stationList = new ArrayList<Station>();

        String lastUpdateText = null;
        Document doc = Jsoup.connect(url).get();
        Elements stations = doc.getElementsByClass("box");
        DataManager db = new DataManager(getApplicationContext());

        for (Element e : stations)
        {

            lastUpdateText = e.select("td").get(9).text();
            Station station = new Station();
            station.setName(e.select("td").get(1).text());
            station.setEmptyPark(e.select("td").get(5).text());
            station.setBicycleCount(e.select("td").get(7).text());
            stationList.add(station);

        }
        db.updateStations(stationList, lastUpdateText);

    }

    public void showStations()
    {
        DataManager db = new DataManager(this);

        TextView lastUpdate = (TextView) findViewById(R.id.lastUpdate);
        ExpandableListView ExpandList = (ExpandableListView) findViewById(R.id.exp_list);
        ArrayList<Station> ExpListItems = SetStandardGroups(db);
        ExpandListAdapter ExpAdapter = new ExpandListAdapter(this, ExpListItems);
        ExpandList.setAdapter(ExpAdapter);
        ExpandList.setGroupIndicator(null);

        lastUpdate.setText(db.getLastUpdate());

        checkInternetAndShowAlert(this, db);
    }

    private void checkInternetAndShowAlert(Activity activity, DataManager db)
    {
        if (!isConnectInternet(activity) && db.getStationsList().size() == 0)
        {
            final AlertDialog alertDialog = new AlertDialog.Builder(getApplicationContext(),
                    AlertDialog.THEME_HOLO_LIGHT).create();
            alertDialog.setTitle("Bağlantı Hatası");
            alertDialog.setCancelable(false);
            alertDialog.setIcon(R.drawable.alert_icon);
            alertDialog.setMessage("İnternet bağlantınızı açtıktan sonra tekrar deneyiniz.");
            alertDialog.setButton("Çıkış", new DialogInterface.OnClickListener()
            {
                @Override
                public void onClick(DialogInterface dialog, int which)
                {
                    System.exit(0);
                }
            });
            alertDialog.show();
        }
    }

    public boolean isConnectInternet(Activity activity)
    {
        ConnectivityManager conMgr = (ConnectivityManager) activity.getSystemService(Context.CONNECTIVITY_SERVICE);

        return conMgr.getActiveNetworkInfo() != null
                && conMgr.getActiveNetworkInfo().isConnected()
                && conMgr.getActiveNetworkInfo().isAvailable();
    }

    private void checkInternet()
    {
        DataManager db = new DataManager(getApplicationContext());

        if (!isConnectInternet(this) && db.getStationsList().size() != 0)
        {
            final AlertDialog alertDialog = new AlertDialog.Builder(this, AlertDialog.THEME_HOLO_LIGHT).create();
            alertDialog.setTitle("Uyarı");
            alertDialog.setIcon(R.drawable.alert_icon);
            alertDialog.setCancelable(false);
            alertDialog.setMessage("Güncel istasyon bilgilerini görebilmek" +
                    " için internet bağlantınızın aktif olması gerekmektedir. ");
            alertDialog.setButton("Tamam", new DialogInterface.OnClickListener()
            {
                @Override
                public void onClick(DialogInterface dialog, int which)
                {
                    alertDialog.dismiss();
                }
            });
            alertDialog.show();
        }
        else if (!isConnectInternet(this) && db.getStationsList().size() == 0)
        {
            final AlertDialog alertDialog = new AlertDialog.Builder(this,
                    AlertDialog.THEME_HOLO_LIGHT).create();
            alertDialog.setTitle("Bağlantı Hatası");
            alertDialog.setIcon(R.drawable.alert_icon);
            alertDialog.setMessage("Lütfen internet bağlantınızı açtıktan sonra tekrar deneyiniz.");
            alertDialog.setButton("Çıkış", new DialogInterface.OnClickListener()
            {
                @Override
                public void onClick(DialogInterface dialog, int which)
                {
                    System.exit(0);
                }
            });
            alertDialog.show();
        }
    }

    private class SlideMenuClickListener implements ListView.OnItemClickListener
    {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id)
        {
            displayView(position);
        }
    }

    public ArrayList<Station> SetStandardGroups(DataManager db)
    {

        ArrayList<Station> stations = db.getStationsList();

        int Images[] = {R.drawable.s001, R.drawable.s002, R.drawable.s003, R.drawable.s004, R.drawable.s005,
                R.drawable.s006, R.drawable.s007, R.drawable.s008, R.drawable.s009,
                R.drawable.s010, R.drawable.s010, R.drawable.s011, R.drawable.s012,
                R.drawable.s013, R.drawable.s014, R.drawable.s015, R.drawable.s016, R.drawable.s015, R.drawable.s016,};

        if (stations.size() == 0)
            stationNotFound();

        for (int i = 0; i < stations.size(); i++)
        {
            StationImage stationImage = new StationImage();
            stationImage.setImage(Images[i]);
            stations.get(i).setStationImage(stationImage);
        }

        return stations;
    }

    void stationNotFound()
    {
        final AlertDialog alertDialog = new AlertDialog.Builder(getApplicationContext(),
                AlertDialog.THEME_HOLO_LIGHT).create();
        alertDialog.setTitle("");
        alertDialog.setIcon(R.drawable.alert_icon);
        alertDialog.setCancelable(false);
        alertDialog.setMessage("Durak Bulunamadı.");
        alertDialog.setButton("Çıkış", new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialog, int which)
            {
                System.exit(0);
            }
        });
        alertDialog.show();
    }

    private class SaveStations extends AsyncTask<Void, Void, Void>
    {

        @Override
        protected void onPreExecute()
        {
            progressDialog = new ProgressDialog(MainActivity.this);
            progressDialog.setMessage("Duraklar yükleniyor...");
            progressDialog.setIndeterminate(true);
            progressDialog.setCancelable(false);
            progressDialog.show();
        }

        @Override
        protected Void doInBackground(Void... params)
        {
            try
            {
                saveStations();

            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result)
        {
            progressDialog.dismiss();
            showStations();
        }
    }

    private ProgressDialog progressDialog;

    private class refreshStations extends AsyncTask<String, Void, String>
    {


        @Override
        protected void onPreExecute()
        {
            refreshMenuItem.setActionView(R.layout.action_progressbar);
            refreshMenuItem.expandActionView();

        }


        @Override
        protected String doInBackground(String... params)
        {
            try
            {
                if (!isConnectInternet(MainActivity.this))
                {
                    final AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this,
                            AlertDialog.THEME_HOLO_LIGHT).create();
                    alertDialog.setTitle("Bağlantı Hatası");
                    alertDialog.setCancelable(false);
                    alertDialog.setIcon(R.drawable.alert_icon);
                    alertDialog.setMessage("Lütfen internet bağlantınızı kontrol ediniz.");
                    alertDialog.setButton("Tamam", new DialogInterface.OnClickListener()
                    {
                        @Override
                        public void onClick(DialogInterface dialog, int which)
                        {
                            alertDialog.dismiss();
                        }
                    });
                    alertDialog.show();
                }
                else
                {
                    saveStations();

                }

            }

            catch (IOException e)
            {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String result)
        {
            refreshMenuItem.collapseActionView();
            refreshMenuItem.setActionView(null);
            showStations();
        }


    }
}
