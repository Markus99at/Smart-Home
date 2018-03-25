package htl.at.smart_home;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class Wohnzimmer extends Fragment {

    static TextView textViewMainLight;
    static TextView textViewWorkingLight;
    static TextView textViewTemperatur;
    static TextView textViewHumidity;
    static TextView textViewTime;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View viewWohnzimmer =  inflater.inflate(R.layout.fragment_wohnzimmer, container, false);

        textViewMainLight=(TextView)viewWohnzimmer.findViewById(R.id.textViewWohnzimmerLightState);
        textViewWorkingLight=(TextView)viewWohnzimmer.findViewById(R.id.textViewWohnzimmerWorkingLightState);
        textViewTemperatur=(TextView)viewWohnzimmer.findViewById(R.id.textViewWohnzimmerTemperatur);
        textViewHumidity=(TextView)viewWohnzimmer.findViewById(R.id.textViewWohnzimmerLuftfeuchtigkeit);
        textViewTime=(TextView)viewWohnzimmer.findViewById(R.id.textViewWohnzimmerLastUpdated);

        new SendAsyncTask(textViewTemperatur, "wohnzimmer/sensor/temperatur").execute("");
        new SendAsyncTask(textViewHumidity, "wohnzimmer/sensor/humidity").execute("");
        new SendAsyncTask(textViewTime, "wohnzimmer/sensor/time").execute("");

        new SendAsyncTask(textViewMainLight, "wohnzimmer/mainlight/state").execute("");
        new SendAsyncTask(textViewWorkingLight, "wohnzimmer/workinglight/state").execute("");

        return viewWohnzimmer;
    }

    public static void mainlightOn(View view){
        new SendAsyncTask(textViewMainLight, "wohnzimmer/mainlight/on").execute("");
    }

    public static void mainlightOff(View view){
        new SendAsyncTask(textViewMainLight, "wohnzimmer/mainlight/off").execute("");
    }

    public static void workinglightOn(View view){
        new SendAsyncTask(textViewWorkingLight, "wohnzimmer/workinglight/on").execute("");
    }

    public static void workinglightOff(View view){
        new SendAsyncTask(textViewWorkingLight, "wohnzimmer/workinglight/off").execute("");
    }
}