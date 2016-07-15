package com.google.android.gms.samples.vision.barcodereader;

import android.util.Log;

import okhttp3.FormBody;
import okhttp3.HttpUrl;
import okhttp3.RequestBody;

/**
 * Created by tariqaziz on 2016-07-14.
 */
public class RequestBuilder {

    //Login request body
    public static RequestBody LoginBody(String username, String password, String token) {
        return new FormBody.Builder()
                .add("action", "login")
                .add("format", "json")
                .add("username", username)
                .add("password", password)
                .add("logintoken", token)
                .build();
    }

    //http://api.upcdatabase.org/json/APIKEY/CODE

    //http://www.searchupc.com/handlers/upcsearch.ashx?request_type=3&access_token=1E10F11F-5CD3-496C-A965-0DADE8E92BB7&upc=upc_code

    /*
    public static HttpUrl buildURL(String barcodeValue) {
        HttpUrl httpUrl = new HttpUrl.Builder()
                .scheme("http") //http
                .host("www.searchupc.com")
                .addPathSegment("handlers")
                .addPathSegment("upcsearch.ashx")//adds "/pathSegment" at the end of hostname
                .addQueryParameter("request_type", "3")
                .addEncodedQueryParameter("access_token", UPCDatabaseConfig.API_KEY)
                .addEncodedQueryParameter("upc", barcodeValue)
                .build();

        Log.v("httpUrl", String.valueOf(httpUrl));

        return httpUrl;
    }
    */

    public static HttpUrl buildURL(String barcodeValue) {
        HttpUrl httpUrl =  new HttpUrl.Builder()
                .scheme("http") //http
                .host("api.upcdatabase.org")
                .addPathSegment("json")
                .addPathSegment(UPCDatabaseConfig.API_KEY)//adds "/pathSegment" at the end of hostname
                .addPathSegment(barcodeValue)
                .build();
        Log.v("httpUrl", String.valueOf(httpUrl));
        return httpUrl;
        /**
         * The return URL:
         *  https://www.somehostname.com/pathSegment?param1=value1&encodedName=encodedValue
        */
    }

}