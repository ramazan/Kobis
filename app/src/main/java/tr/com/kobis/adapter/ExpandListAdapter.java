package tr.com.kobis.adapter;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import tr.com.kobis.R;
import tr.com.kobis.model.Station;
import tr.com.kobis.model.StationImage;

public class ExpandListAdapter extends BaseExpandableListAdapter
{

    private Context context;
    private ArrayList<Station> stations = new ArrayList<Station>();

    public ExpandListAdapter(Context context, ArrayList<Station> stations)
    {
        this.context = context;
        this.stations = stations;
    }


    public StationImage getStationImage(int groupPosition)
    {
        StationImage stationImage = stations.get(groupPosition).getStationImage();
        return stationImage;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition)
    {
        return childPosition;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition,
                             boolean isLastChild, View convertView, ViewGroup parent)
    {

        StationImage stationImage = (StationImage) getStationImage(groupPosition);
        if (convertView == null)
        {
            LayoutInflater infalInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.child_item, null);
        }
        ImageView iv = (ImageView) convertView.findViewById(R.id.stationImage);

        iv.setImageResource(stationImage.getImage());

        return convertView;
    }


    public Object getStations(int groupPosition)
    {
        return stations.get(groupPosition);
    }

    @Override
    public int getGroupCount()
    {
        return stations.size();
    }

    @Override
    public int getChildrenCount(int i)
    {
        return 1;
    }

    @Override
    public Object getGroup(int groupPosition)
    {
        return stations.get(groupPosition);
    }

    @Override
    public Object getChild(int i, int i1)
    {
        return null;
    }

    @Override
    public long getGroupId(int groupPosition)
    {
        return groupPosition;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent)
    {
        Station group = (Station) getGroup(groupPosition);
        if (convertView == null) {
            LayoutInflater inf = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            convertView = inf.inflate(R.layout.group_item, null);
        }
        TextView stationName = (TextView) convertView.findViewById(R.id.STATION_NAME);
        TextView emptyPark = (TextView) convertView.findViewById(R.id.EMPTY_PARK);
        TextView bicycleCount = (TextView) convertView.findViewById(R.id.BICYCLE_COUNT);

        stationName.setText(group.getName());
        emptyPark.setText(group.getEmptyPark() + " Boş Park");
        bicycleCount.setText(group.getBicycleCount() + " Bisiklet");

        return convertView;
    }

    @Override
    public boolean hasStableIds()
    {
        return true;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition)
    {
        return true;
    }

}