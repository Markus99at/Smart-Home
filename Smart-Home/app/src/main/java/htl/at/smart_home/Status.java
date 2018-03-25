package htl.at.smart_home;


import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.net.URL;

public class Status extends Fragment {

    public TextView textView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View StatusView = inflater.inflate(R.layout.fragment_status, container, false);

        textView = (TextView)StatusView.findViewById(R.id.textViewStatusStatus);


        return StatusView;
    }

    public void onResume() {
        super.onResume();
        new CurrentStatusAsyncTask(getActivity(), textView).execute("");
    }
}