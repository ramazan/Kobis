package tr.com.kobis.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;

import android.view.View;
import android.view.ViewGroup;
import tr.com.kobis.R;

public class CallCenterFragment extends Fragment
{

    public CallCenterFragment()
    {

        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View rootView = inflater.inflate(R.layout.fragment_call_center, container, false);
        return rootView;
    }

}
