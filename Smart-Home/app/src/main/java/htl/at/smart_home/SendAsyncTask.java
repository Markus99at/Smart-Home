package htl.at.smart_home;

import android.graphics.Color;
import android.os.AsyncTask;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.InetSocketAddress;
import java.net.Socket;

public class SendAsyncTask  extends AsyncTask<String, String, String> {

    TextView textView;
    String msg;

    public  SendAsyncTask(TextView textView, String msg) {
        this.textView=textView;
        this.msg=msg;
    }

    protected String doInBackground(String... params) {
        String response = "No response from the Server!";
        try {

            Socket s = new Socket();
            s.connect(new InetSocketAddress(MainActivity.ip, 50505), 1000);

            BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));

            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(s.getOutputStream()));

            //send output msg

            String outMsg = msg + System.getProperty("line.separator");

            out.write(outMsg);

            out.flush();

            response = in.readLine() + System.getProperty("line.separator");

            in.close();
            out.close();
            s.close();


        } catch (IOException e) {
            e.printStackTrace();
        }

        return response;
    }


    @Override
    protected void onPostExecute(String result) {
        String x = result.substring(0,2);
        textView.setText(result);
        if (x.equals("on")){
            textView.setText(result);
            textView.setBackgroundColor(Color.parseColor("#76039f00"));
        }else if(x.equals("of")) {
            textView.setText(result);
            textView.setBackgroundColor(Color.parseColor("#769F0300"));
        }
    }
}