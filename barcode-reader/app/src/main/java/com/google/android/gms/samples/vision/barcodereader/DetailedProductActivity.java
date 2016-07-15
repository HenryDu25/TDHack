package com.google.android.gms.samples.vision.barcodereader;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class DetailedProductActivity extends AppCompatActivity {

    private OkHttpClient client;
    String barcodeValue;
    ImageView graphImageView;
    TextView budgetTextView;
    ListView listView;
    ListAdapter customAdapter;
    ArrayList<Product> productList;
    String productName;

    ArrayList<Integer> imageList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed_product);

        Intent receivedIntent = getIntent();
        barcodeValue = receivedIntent.getStringExtra("barcodeValue");

        productList = new ArrayList<Product>();
        imageList.add(R.drawable.cheap);
        imageList.add(R.drawable.moderate);
        imageList.add(R.drawable.expensive);

        listView = (ListView)findViewById(R.id.list_view);
        customAdapter = new ListAdapter(this, R.layout.product_list_item, productList);
        listView.setAdapter(customAdapter);

        //CallAPI callAPI = new CallAPI(barcodeValue);

        client = new OkHttpClient();
        loadContent();

        graphImageView = (ImageView)findViewById(R.id.graph_image_view);
        //graphImageView.setImageResource(R.drawable.under_best_price);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Product product = new Product(productName, "Best Buy", "$650");
        product.setDistance("8.7km");
        Product product2 = new Product(productName, "Amazon", "547.95");
        productList.add(product);
        productList.add(product2);
        customAdapter.notifyDataSetChanged();

    }

    private void loadContent() {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... params) {
                try {
                    String response = ApiCall.GET(client, RequestBuilder.buildURL(barcodeValue));
                    //Parse the response string here
                    Log.d("Response", response);

                    JSONObject mainObject = new JSONObject(response);
                    productName = mainObject.getString("itemname");
                    Log.v("PRODUCT_NAME", productName);


                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                setTitle(productName);
                graphImageView.setImageResource(R.drawable.moderate);

                if(productName.contains("Google")){
                    Product product = new Product(productName, "Best Buy", "$650");
                    product.setDistance("8.7km");
                    Product product2 = new Product(productName, "Amazon", "547.95");
                    productList.add(product);
                    productList.add(product2);
                    customAdapter.notifyDataSetChanged();
                }

            }
        }.execute();
    }


    public void backToScanner(View v){
        finish();
    }

}
