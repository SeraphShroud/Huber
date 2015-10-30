package com.example.seraphshroud.huber;

import com.parse.ParseObject;
import com.parse.ParseClassName;
/**
 * Created by SeraphShroud on 10/29/2015.
 * This class is not currently being used.
 */

@ParseClassName("Client")
public class Client extends ParseObject {

    //public static $parseClassName = "Client";

    // Default Constructor
    public Client() {
        put("phoneNumber", "NULL");
        put("location", "NULL");
    }

    protected void setPhoneNumber ( String number ) {
        put("phoneNumber", number);
    }

    protected void setLocation ( String location ) {
        put("location", location);
    }

    public String getPhoneNumber() {
        return getString("phoneNumber");
    }

    public String getLocation() {
        return getString("location");
    }

}
