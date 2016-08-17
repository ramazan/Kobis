package tr.com.kobis.fragments;

import java.util.ArrayList;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Fragment;
import android.content.Context;
import android.content.DialogInterface;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.TextView;
import tr.com.kobis.R;
import tr.com.kobis.adapter.ExpandListAdapter;
import tr.com.kobis.data.DataManager;
import tr.com.kobis.model.Station;
import tr.com.kobis.model.StationImage;

public class StationsFragment extends Fragment
{

    public StationsFragment()
    {
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {

        View rootView = inflater.inflate(R.layout.fragment_stations, container, false);
        Activity activity = (Activity) container.getContext();
        DataManager db = new DataManager(rootView.getContext());

        TextView lastUpdate = (TextView) rootView.findViewById(R.id.lastUpdate);
        ExpandableListView ExpandList = (ExpandableListView) rootView.findViewById(R.id.exp_list);
        ArrayList<Station> ExpListItems = SetStandardGroups(db);
        ExpandListAdapter ExpAdapter= new ExpandListAdapter(rootView.getContext(), ExpListItems);
        ExpandList.setAdapter(ExpAdapter);
        ExpandList.setGroupIndicator(null);
        
        lastUpdate.setText(db.getLastUpdate());

        checkInternetAndShowAlert(activity, db);
        return rootView;
    }

    private void checkInternetAndShowAlert(Activity activity, DataManager db)
    {
        if (!isConnectInternet(activity) && db.getStationsList().size() == 0)
        {
            final AlertDialog alertDialog = new AlertDialog.Builder(getActivity(),
                    AlertDialog.THEME_HOLO_LIGHT).create();
            alertDialog.setTitle("Bağlantı Hatası");
            alertDialog.setIcon(R.drawable.alert_icon);
            alertDialog.setCancelable(false);
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


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater)
    {
        inflater.inflate(R.menu.main, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    public ArrayList<Station> SetStandardGroups(DataManager db)
    {

        ArrayList<Station> stations = db.getStationsList();

        int Images[] = {R.drawable.s001, R.drawable.s002, R.drawable.s003, R.drawable.s004, R.drawable.s005,
                R.drawable.s006, R.drawable.s007, R.drawable.s008, R.drawable.s009,
                R.drawable.s010, R.drawable.s010, R.drawable.s011, R.drawable.s012,
                R.drawable.s013, R.drawable.s014, R.drawable.s015, R.drawable.s016,
                R.drawable.s015, R.drawable.s016, R.drawable.s017, R.drawable.s018,};

        for (int i = 0; i < stations.size(); i++)
        {
            StationImage stationImage = new StationImage();
            stationImage.setImage(Images[i]);
            stations.get(i).setStationImage(stationImage);
        }

        return stations;
    }

    public boolean isConnectInternet(Activity activity)
    {
        ConnectivityManager conMgr = (ConnectivityManager) activity.getSystemService(Context.CONNECTIVITY_SERVICE);

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
}
