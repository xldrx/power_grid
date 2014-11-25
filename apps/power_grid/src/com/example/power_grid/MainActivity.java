package com.example.power_grid;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class MainActivity extends Activity {
    /**
     * Called when the activity is first created.
     */


    public JSONObject getObject(String url) throws Exception {
        try {
            HttpClient httpclient = new DefaultHttpClient();
            HttpResponse response = httpclient.execute(new HttpGet(url));
            StatusLine statusLine = response.getStatusLine();
            if (statusLine.getStatusCode() == HttpStatus.SC_OK) {
                ByteArrayOutputStream out = new ByteArrayOutputStream();
                response.getEntity().writeTo(out);
                out.close();
                String responseString = out.toString();
                return new JSONObject(responseString);
            } else {
                //Closes the connection.
                response.getEntity().getContent().close();
                throw new IOException(statusLine.getReasonPhrase());
            }
        }
        catch (Exception ex){
            ex.printStackTrace();
            throw ex;
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        try {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.main);
            String message = getObject("http://10.39.210.174:8881/add-rps/").getString("message");
            ((TextView) findViewById(R.id.main_textbox)).setText(message);
        }
        catch (Exception ex){

        }
    }
}
