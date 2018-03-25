package htl.at.smart_home;

import android.app.Activity;
import android.graphics.Color;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.InetSocketAddress;
import java.net.Socket;

public class CurrentStatusAsyncTask  extends AsyncTask<String, String, String> {

        TextView textView;
        Activity mContex;

        public  CurrentStatusAsyncTask(Activity contex,TextView textView) {
            this.textView=textView;
            this.mContex=contex;
        }

        protected String doInBackground(String... params) {
            String response = "Couldn´t connect to Server!";
            try {


                Socket s = new Socket();
                s.connect(new InetSocketAddress(MainActivity.ip, 50505), 1000);

                BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));

                BufferedWriter out = new BufferedWriter(new OutputStreamWriter(s.getOutputStream()));

                //send output msg

                String outMsg = "currentStatus" + System.getProperty("line.separator");

                out.write(outMsg);

                out.flush();


                String serverResponse = in.readLine() + System.getProperty("line.separator");

                if(serverResponse!=null){
                    response=serverResponse;
                }


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

            String x = result.substring(0, 5);
            if (x.equals("Every")){
                MainActivity.connection=true;
                textView.setText(result.substring(0,28));
            textView.setBackgroundColor(Color.parseColor("#32019731"));
        }else if(x.equals("Could")) {
                MainActivity.connection=false;
                textView.setText(result);
                textView.setBackgroundColor(Color.parseColor("#3BFF0000"));
            }
        }
}