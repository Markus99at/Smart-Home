package htl.at.smart_home;

import android.app.DialogFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Markus on 22.02.2018.
 */

public class TimeoutDialog extends DialogFragment {

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_timeoutdialog, container, false);
            getDialog().setTitle("Simple Dialog");
            return rootView;

    }
}
