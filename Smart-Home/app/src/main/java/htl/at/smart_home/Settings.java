package htl.at.smart_home;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class Settings extends AppCompatActivity {

    public EditText editTextIPAddress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        editTextIPAddress = (EditText)this.findViewById(R.id.editTextSettingsSaveIP);

        loadIP();
    }

    public void buttonSettingsSaveIP(View view){
        SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        SharedPreferences.Editor editor = settings.edit();

        ipAddress=editTextIPAddress.getText().toString();

        editor.putString("ipAddress", ipAddress);

        editor.commit();
        MainActivity.ip=ipAddress;
        finish();
    }
    public String ipAddress = "192.168.0.10";

    public void loadIP(){
        SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        ipAddress = settings.getString("ipAddress", null);
        editTextIPAddress.setText(ipAddress);
    }
}
