package com.google.android.gms.samples.vision.barcodereader;

import android.os.AsyncTask;
import android.util.Log;

import org.apache.commons.io.IOUtils;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class CallAPI extends AsyncTask<String, String, String> {

    String mBarcode;

    public CallAPI(String barcode) {
        //set context variables if required
        mBarcode = barcode;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }


    @Override
    protected String doInBackground(String... params) {

        //String urlString = params[0]; // URL to call
        String urlString = "http://api.upcdatabase.org/json/" + UPCDatabaseConfig.API_KEY+ "/" + mBarcode;

        String resultToDisplay = "";

        InputStream in = null;
        try {

            URL url = new URL(urlString);

            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

            in = new BufferedInputStream(urlConnection.getInputStream());


        } catch (Exception e) {

            System.out.println(e.getMessage());

            return e.getMessage();

        }

        try {
            resultToDisplay = IOUtils.toString(in, "UTF-8");
            //to [convert][1] byte stream to a string
        } catch (IOException e) {
            e.printStackTrace();
        }
        return resultToDisplay;
    }


    @Override
    protected void onPostExecute(String result) {
        Log.v("UPC_RESULT", result);
    }
}