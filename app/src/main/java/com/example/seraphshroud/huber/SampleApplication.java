package com.example.seraphshroud.huber;

import com.parse.Parse;
import android.app.Application;

/**
 * Created by SeraphShroud on 10/29/2015.
 */
public class SampleApplication extends Application {

    public void onCreate() {
        Parse.initialize(this, "ENaimFMoqvCB6RgKMgPR8VD60OupUmE10R4atss5", "LqBZwLYBka8JOvGZKXvuBE91Ul5xbHnfq0ZrSqgY");
    }
}
