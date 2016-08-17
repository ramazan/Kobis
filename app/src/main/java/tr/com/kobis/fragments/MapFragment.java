package tr.com.kobis.fragments;

import java.util.ArrayList;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import android.app.Fragment;
import android.os.Bundle;
import android.view.InflateException;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import tr.com.kobis.R;
import tr.com.kobis.data.DataManager;
import tr.com.kobis.model.Station;

public class MapFragment extends Fragment
{
    private static View view;
    private GoogleMap googleMap;

    public MapFragment()
    {
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        DataManager db = new DataManager(container.getContext());
        ArrayList<Station> stations = db.getStationsList();
        ArrayList<LatLng> posList = new ArrayList<LatLng>();
        float zoom = (float) 11.6;

        posList.add(new LatLng(40.7674733342, 29.9755885985)); // Yahya Kaptan Bisiklet Eğitim Parkı
        posList.add(new LatLng(40.7608319973, 29.9760654922)); // Yahya Kaptan D-100
        posList.add(new LatLng(40.7633682824, 29.9670024958)); // Kınalı Asker Parkı
        posList.add(new LatLng(40.7633926029, 29.9575118025)); // Demokrasi Kavşağı
        posList.add(new LatLng(40.7646453521, 29.9488294004)); // Doğu Kışla Parkı
        posList.add(new LatLng(40.7659524305, 29.940948882)); // Anıt Park
        posList.add(new LatLng(40.7645955614, 29.9299876317)); // İzmit Belediyesi
        posList.add(new LatLng(40.7609447739, 29.9359400256)); // Kocaeli Fuarı
        posList.add(new LatLng(40.7607092543, 29.93096346)); // Mimar Sinan Köprüsü
        posList.add(new LatLng(40.7601676444, 29.9230164124)); // Marina
        posList.add(new LatLng(40.7619807635, 29.9181424505)); // Tren Garı
        posList.add(new LatLng(40.760445688, 29.9093498343)); // Sekapark Palmiye
        posList.add(new LatLng(40.7604950462, 29.8991655623)); // Sekapark Uçurtma Tepesi
        posList.add(new LatLng(40.7597989638, 29.8920883144)); // Sekapark 2.Etap
        posList.add(new LatLng(40.7605222632, 29.8838579847)); // Plaj Yolu
        posList.add(new LatLng(40.772056, 29.984889)); // Yıldız Konutları
        posList.add(new LatLng(40.771027, 30.005577)); // Ali Kahya Merkez
        posList.add(new LatLng(40.749402, 30.021145)); // Köseköy
        posList.add(new LatLng(40.749402, 30.021145)); // Patlamasın diye
        posList.add(new LatLng(40.749402, 30.021145)); // Patlamasın diye


        if (view != null)
        {
            ViewGroup parent = (ViewGroup) view.getParent();
            if (parent != null)
                parent.removeView(view);
        }
        try
        {
            view = inflater.inflate(R.layout.map_fragment, container, false);
        }
        catch (InflateException e)
        {
            /* map is already there, just return view as it is */
        }


        googleMap = ((com.google.android.gms.maps.MapFragment) getFragmentManager().findFragmentById(R.id.Harita)).getMap();
        googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        googleMap.setMyLocationEnabled(true);
        googleMap.setTrafficEnabled(true);
        googleMap.setIndoorEnabled(true);
        googleMap.setBuildingsEnabled(true);
        googleMap.getUiSettings().setZoomControlsEnabled(true);
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(40.7633926029, 29.9575118025), zoom));

        setMarkers(stations, posList);

        return view;
    }

    public void createMarker(String StationName, LatLng Kordinat, String bicycleCount)
    {

        googleMap.addMarker(new MarkerOptions().position(Kordinat).title(StationName)
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)).snippet("Bisiklet sayısı : " + bicycleCount));
    }

    public void setMarkers(ArrayList<Station> stations, ArrayList<LatLng> pos)
    {
        for (int i = 0; i < stations.size(); i++)
        {
            createMarker(stations.get(i).getName(), pos.get(i), stations.get(i).getBicycleCount());
        }
    }
}
