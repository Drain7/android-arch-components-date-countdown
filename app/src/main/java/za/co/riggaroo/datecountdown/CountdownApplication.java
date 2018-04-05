package za.co.riggaroo.datecountdown;


import android.app.Activity;
import android.app.Application;

import com.jakewharton.threetenabp.AndroidThreeTen;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasActivityInjector;
import timber.log.Timber;
import za.co.riggaroo.datecountdown.injection.AppInjector;

import com.microsoft.appcenter.AppCenter;
import com.microsoft.appcenter.analytics.Analytics;
import com.microsoft.appcenter.crashes.Crashes;

public class CountdownApplication extends Application implements HasActivityInjector {

    @Inject
    DispatchingAndroidInjector<Activity> dispatchingAndroidInjector;


    @Override
    public void onCreate() {
        super.onCreate();
        //appCenter
        AppCenter.start(this, "bce9045d-1ec5-4a0a-910d-aefbe44303d7",
                Analytics.class, Crashes.class);
        AppCenter.start(this, "bce9045d-1ec5-4a0a-910d-aefbe44303d7", Analytics.class, Crashes.class);
        AndroidThreeTen.init(this);
        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());//TODO Install a Crashlytics tree in production
        }
        AppInjector.init(this);
        //added a line for tesh

    }

    @Override
    public AndroidInjector<Activity> activityInjector() {
        return dispatchingAndroidInjector;
    }
}
