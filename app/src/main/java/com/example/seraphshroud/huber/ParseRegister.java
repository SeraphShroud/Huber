package com.example.seraphshroud.huber;

import android.app.Application;
import com.parse.Parse;
import com.parse.ParseObject;

/**
 * Created by SeraphShroud on 10/29/2015.
 */
public class ParseRegister extends Application {

    @Override
    public void onCreate(){
        super.onCreate();
        // Do not enable this or it will not connect to the database.
        // Parse.enableLocalDatastore(this);
        // ParseObject.registerSubclass(Client.class);

        Parse.initialize(this, "ENaimFMoqvCB6RgKMgPR8VD60OupUmE10R4atss5", "LqBZwLYBka8JOvGZKXvuBE91Ul5xbHnfq0ZrSqgY");

    }

}
