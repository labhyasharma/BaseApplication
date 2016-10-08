package in.editsoft.baseapplication;

import android.app.Application;
import android.content.Context;

/**
 * Created by Devil Guru on 10/7/2016.
 */
public class BaseApplication extends Application{
    public static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
    }
}
