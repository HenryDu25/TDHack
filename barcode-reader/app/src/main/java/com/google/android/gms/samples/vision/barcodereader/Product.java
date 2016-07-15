package com.google.android.gms.samples.vision.barcodereader;

/**
 * Created by tariqaziz on 2016-07-15.
 */
public class Product {
    public String storeName;
    public String price;
    public String distance;
    public String productName;

    public Product(String productName, String storeName, String price){
        this.productName = productName;
        this.storeName = storeName;
        this.price = price;
    }

    public void setDistance(String distance){
        this.distance = distance;
    }
}
